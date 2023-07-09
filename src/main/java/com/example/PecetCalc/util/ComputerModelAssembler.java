package com.example.PecetCalc.util;

import com.example.PecetCalc.api.ComputerController;
import com.example.PecetCalc.model.Computer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComputerModelAssembler implements RepresentationModelAssembler<Computer, EntityModel<Computer>> {

    @Override
    public EntityModel<Computer> toModel(Computer computer) {
        //return EntityModel.of(computer, linkTo(methodOn(ComputerController.class).getComputer(computer.getCpuId())).withSelfRel(), linkTo(methodOn(ComputerController.class).getAllComputers()).withRel("computers"));
        Object EntityModel = null;
        return (org.springframework.hateoas.EntityModel<Computer>) EntityModel;
    }
}
