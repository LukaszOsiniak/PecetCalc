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
            log.info("Preloading " + computerRepository.save(new Computer(1L, "Fir", 100.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(2L, "Sec", 200.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(3L, "Thi", 300.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Fou", 400.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Fif", 500.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Six", 600.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Sev", 700.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Eig", 800.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Nin", 900.0) ));
            log.info("Preloading " + computerRepository.save(new Computer(4L, "Ten", 1000.0) ));
        };
    }

    @Bean
    CommandLineRunner initializeDatabase(InvoiceRepository invoiceRepository) {

        return args -> {
            log.info("Preloading " + invoiceRepository.save(new Invoice(1L, "First", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(2L, "Second", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(3L, "Third", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(4L, "Fourth", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(5L, "Fifth", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(6L, "Sixth", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(7L, "Seventh", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(8L, "Eighth", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(9L, "Ninth", list, 0D, firstDate)));
            log.info("Preloading " + invoiceRepository.save(new Invoice(10L, "Tenth", list, 0D, firstDate)));
            log.info(Util.getRateAtDate(firstDate).toString());
        };
    }
}
