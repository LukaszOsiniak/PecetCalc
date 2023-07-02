package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Invoice;
import com.example.PecetCalc.util.InvoiceModelAssembler;
import com.example.PecetCalc.util.InvoiceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceModelAssembler invoiceAssembler;

    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceModelAssembler invoiceAssembler) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceAssembler = invoiceAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<Invoice> getInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));
        return invoiceAssembler.toModel(invoice);
    }

    @GetMapping("/invoices")
    public CollectionModel<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return CollectionModel.of(invoices, linkTo(methodOn(InvoiceController.class).getAllInvoices()).withSelfRel());
    }
}
