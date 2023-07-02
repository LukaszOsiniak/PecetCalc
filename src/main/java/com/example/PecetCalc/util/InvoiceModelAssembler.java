package com.example.PecetCalc.util;

import com.example.PecetCalc.api.InvoiceController;
import com.example.PecetCalc.model.Invoice;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InvoiceModelAssembler implements RepresentationModelAssembler<Invoice, EntityModel<Invoice>> {

    @Override
    public EntityModel<Invoice> toModel(Invoice invoice) {
        return EntityModel.of(invoice, linkTo(methodOn(InvoiceController.class).getInvoice(invoice.getInvId())).withSelfRel(), linkTo(methodOn(InvoiceController.class).getAllInvoices()).withRel("invoices"));
    }
}
