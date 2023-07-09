package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.util.ComputerModelAssembler;
import com.example.PecetCalc.util.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("/record/{id}")
    public EntityModel<Computer> getComputer(@PathVariable Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return computerAssembler.toModel(computer);
    }

    @GetMapping()
    public Page<Computer> getAllComputers(Pageable pageable) {
        return computerRepository.findAll(pageable);
    }

    @GetMapping("/{keyword}")
    public Page<Computer> getComputersWithName(Pageable pageable, @PathVariable String keyword) {
        return computerRepository.findAll(pageable, keyword);
    }

//    @GetMapping("test")
//    public List<Computer> getAllComputers() {//        List<Computer> computers = computerRepository.findAll();
//        // return CollectionModel.of(computers, linkTo(methodOn(ComputerController.class).getAllComputers()).withSelfRel());
//        return computers;
//    }

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
        currentComputer.setPriceInPln(computer.getPriceInPln());
        currentComputer.setExchangeRate(computer.getExchangeRate());
        currentComputer.setInvoice(computer.getInvoice());
        computerRepository.save(currentComputer);

        return ResponseEntity.ok(currentComputer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComputer(@PathVariable Long id) {
        computerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
