package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.model.Invoice;
import com.example.PecetCalc.util.ComputerModelAssembler;
import com.example.PecetCalc.util.RecordNotFoundException;
import com.example.PecetCalc.util.Util;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping
public class ComputerController {

    private final ComputerRepository computerRepository;

    private final ComputerModelAssembler computerAssembler;

    public ComputerController(ComputerRepository computerRepository, ComputerModelAssembler computerAssembler) {
        this.computerRepository = computerRepository;
        this.computerAssembler = computerAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<Computer> getComputer(@PathVariable Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return computerAssembler.toModel(computer);
    }

    @GetMapping("/computers")
    public CollectionModel<Computer> getAllComputers() {
        List<Computer> computers = computerRepository.findAll();
        return CollectionModel.of(computers, linkTo(methodOn(ComputerController.class).getAllComputers()).withSelfRel());
    }

    @PostMapping("/addCPU")
    public ResponseEntity addComputer(@RequestBody Computer computer) throws URISyntaxException {
        Computer savedComputer = computerRepository.save(computer);
        return ResponseEntity.created(new URI("/invoices" + savedComputer.getCpuId())).body(savedComputer);
    }
}
