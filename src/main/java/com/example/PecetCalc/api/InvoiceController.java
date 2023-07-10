package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Invoice;
import com.example.PecetCalc.util.InvoiceModelAssembler;
import com.example.PecetCalc.util.RecordNotFoundException;
import com.example.PecetCalc.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceModelAssembler invoiceAssembler;

    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceModelAssembler invoiceAssembler) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceAssembler = invoiceAssembler;
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Invoice> getInvoice(@PathVariable Long id) {
        return new ResponseEntity<Invoice>(invoiceRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping()
    public Page<Invoice> getAllComputers(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @GetMapping("/search/{keyword}")
    public Page<Invoice> getComputersWithName(Pageable pageable, @PathVariable String keyword) {
        return invoiceRepository.findAll(pageable, keyword);
    }

    @PostMapping()
    public ResponseEntity addInvoice(@RequestBody Invoice invoice, @RequestParam Date date) throws URISyntaxException, IOException {
        Double rate = Util.getRateAtDate(date).doubleValue();
        double invoiceSumUSD = Util.sumPriceOfAllComputers(invoice.getComputers());
        invoice.setInvPriceInUsd(Util.sumPriceOfAllComputers(invoice.getComputers()));
        invoice.setInvPriceInPln(invoiceSumUSD * rate);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        Util.serializeToXML(invoice);
        return ResponseEntity.created(new URI("/invoices" + savedInvoice.getInvId())).body(savedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        Invoice currentInvoice = invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        currentInvoice.setInvId(invoice.getInvId());
        currentInvoice.setName(invoice.getName());
        currentInvoice.setComputers(invoice.getComputers());
        currentInvoice.setInvPriceInPln(invoice.getInvPriceInPln());
        currentInvoice.setInvPriceInUsd(invoice.getInvPriceInUsd());
        currentInvoice.setInvDate(invoice.getInvDate());
        invoiceRepository.save(currentInvoice);
        return ResponseEntity.ok(currentInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInvoice(@PathVariable Long id) {
        invoiceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
