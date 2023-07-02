package com.example.PecetCalc.util;

import com.example.PecetCalc.api.InvoiceRepository;
import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private List<Computer> computers = new ArrayList<>();
    private Date firstDate = new Date();

    @Bean
    CommandLineRunner initializeDatabase(InvoiceRepository invoiceRepository) {

        return args -> {
            log.info("Preloading " + invoiceRepository.save(new Invoice(1, "First", computers, 123, 321, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(2, "Second", computers, 456, 654, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(3, "Third", computers, 789, 987, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(4, "Fourth", computers, 0, 0, firstDate)));
        };
    }
}
