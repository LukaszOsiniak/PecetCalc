package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.util.ComputerModelAssembler;
import com.example.PecetCalc.util.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/computers")
@CrossOrigin(origins = "http://localhost:3000")
public class ComputerController {

    private final ComputerRepository computerRepository;

    private final ComputerModelAssembler computerAssembler;

    public ComputerController(ComputerRepository computerRepository, ComputerModelAssembler computerAssembler) {
        this.computerRepository = computerRepository;
        this.computerAssembler = computerAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable Long id) {
        return new ResponseEntity<Computer>(computerRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping()
    public Page<Computer> getAllComputers(Pageable pageable) {
        return computerRepository.findAll(pageable);
    }

    @GetMapping("/search/{keyword}")
    public Page<Computer> getComputersWithName(Pageable pageable, @PathVariable String keyword) {
        return computerRepository.findAll(pageable, keyword);
    }

    @PostMapping()
    public ResponseEntity addComputer(@RequestBody Computer computer) throws URISyntaxException {
        Computer savedComputer = computerRepository.save(computer);
        return ResponseEntity.created(new URI("/computers" + savedComputer.getCpuId())).body(savedComputer);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateComputer(@PathVariable Long id, @RequestBody Computer computer) {
        Computer currentComputer = computerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        currentComputer.setCpuId(computer.getCpuId());
        currentComputer.setName(computer.getName());
        currentComputer.setPriceInUSD(computer.getPriceInUSD());
        currentComputer.setPriceInPLN(computer.getPriceInPLN());
        currentComputer.setExchangeRate(computer.getExchangeRate());
        currentComputer.setInvoice(computer.getInvoice());
        currentComputer.setAccDate(computer.getAccDate());
        computerRepository.save(currentComputer);

        return ResponseEntity.ok(currentComputer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComputer(@PathVariable Long id) {
        computerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
