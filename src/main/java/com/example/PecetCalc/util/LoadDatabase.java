package com.example.PecetCalc.util;

import com.example.PecetCalc.api.ComputerRepository;
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
    private Date firstDate = new Date();
    List<Computer> list = new ArrayList<>();

//    @Bean
//    CommandLineRunner initializeDatabasee(ComputerRepository computerRepository) {
//
//        return args -> {
//            log.info("Preloading " + computerRepository.save(new Computer(1L, "CPU1", 1)));
//            log.info("Preloading " + computerRepository.save(new Computer(2L, "CPU2", 2)));
//            log.info("Preloading " + computerRepository.save(new Computer(3L, "CPU3", 3)));
//            log.info("Preloading " + computerRepository.save(new Computer(4L, "CPU4", 1)));
//            List<Computer> computers = List.of(computerRepository.getReferenceById(1L), computerRepository.getReferenceById(2L), computerRepository.getReferenceById(3L), computerRepository.getReferenceById(4L));
//            log.info(Util.getRateAtDate(firstDate));
//        };
//    }
//    @Bean
//    CommandLineRunner initializeDatabase(InvoiceRepository invoiceRepository) {
//
//        return args -> {
//            log.info("Preloading " + invoiceRepository.save(new Invoice(1L, "First", list, 123, 321, firstDate)));
//            log.info("Preloading " + invoiceRepository.save(new Invoice(2L, "Second",list, 456, 654, firstDate)));
//            log.info("Preloading " + invoiceRepository.save(new Invoice(3L, "Third", list,789, 987, firstDate)));
//            log.info("Preloading " + invoiceRepository.save(new Invoice(4L, "Fourth",list, 0, 0, firstDate)));
//            log.info(Util.getRateAtDate(firstDate));
//        };
//    }
}
