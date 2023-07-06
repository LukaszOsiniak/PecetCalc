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

    @Bean
    CommandLineRunner initializeComputersDatabase(ComputerRepository computerRepository) {

        return args -> {
            log.info("Preloading " + computerRepository.save(new Computer.ComputerBuilder().cpuId(1L).name("1").priceInUSD(345).build()));
            log.info("Preloading " + computerRepository.save(new Computer.ComputerBuilder().cpuId(2L).name("2").priceInUSD(543).build()));
            log.info("Preloading " + computerRepository.save(new Computer.ComputerBuilder().cpuId(3L).name("3").priceInUSD(346).build()));
        };
    }

    @Bean
    CommandLineRunner initializeDatabase(InvoiceRepository invoiceRepository) {

        return args -> {
            log.info("Preloading " + invoiceRepository.save(new Invoice(1L, "First", list, 321, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(2L, "Second", list, 654, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(3L, "Third", list, 987, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(4L, "Fourth", list, 0, firstDate)));
            log.info(Util.getRateAtDate(firstDate).toString());
        };
    }
}
