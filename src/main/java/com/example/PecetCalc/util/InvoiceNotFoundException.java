package com.example.PecetCalc.util;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(Long id) {
        super("Could not find invoice with id: " + id);
    }
}
