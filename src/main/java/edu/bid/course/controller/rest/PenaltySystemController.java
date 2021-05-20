package edu.bid.course.controller.rest;

import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.repository.PenaltySystemRepository;
import edu.bid.course.service.penalty.PenaltyServiceImpl;
import edu.bid.course.service.penaltysystem.PenaltySystemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystemController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemController: 1.0
 */


@Tag(name = "PenaltySystemControllerAPI", description = "Mostly GET methods with PenaltySystem collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/penaltysystem")
public class PenaltySystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltySystemController.class);

    @Autowired
    PenaltySystemRepository penaltySystemRepository;

    @Operation(summary = " Get all Damages",
            description = " Finds and displays all Damages")
    @RequestMapping("/get/all")
    public List<PenaltySystem> getDamages (){

        LOGGER.info(" Get all Damages (API) request has been called. ");

        return penaltySystemRepository.findAll();

    }

    @Operation(summary = " Get one Damage",
            description = " Finds and displays Damage with specified id")
    @GetMapping("/get/{id}")
    public PenaltySystem getById(@PathVariable("id")
                           @Parameter(name = "id",
                                   description = " This is the first field in the list")
                                   String id) {

        LOGGER.info(" Get Damage by id (API) with id " + id +  " request has been called. ");

        return penaltySystemRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Damage deletion",
            description = " Deletes Damage with specified id")
    @GetMapping("/delete/{id}")
    public List<PenaltySystem> deleteById(@PathVariable("id")
                                    @Parameter(name = "id",
                                            description = " This is the first field in the list")
                                            String id) {

        LOGGER.info(" Delete Damage by id (API) with id " + id +  " request has been called. ");

        penaltySystemRepository.deleteById(id);

        return penaltySystemRepository.findAll();
    }

    @Autowired
    PenaltySystemServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Damage creation",
            description = " Adds new Damage to the PenaltySystem list. Id to be created is UUID type ")
    @PostMapping("/create")
    public PenaltySystem create(@RequestBody PenaltySystem penalty) {

        LOGGER.info(" Create new Damage (API) request has been called. ");

        return service.create(penalty);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Damage updation",
            description = " Updates Damage with specified id")
    @PostMapping("/update")
    public PenaltySystem update(@RequestBody PenaltySystem penalty) {

        LOGGER.info(" Update existing Damage (API) request has been called. ");

        return service.update(penalty);
    }

    @Operation(summary = " Count all Damages based on Rented Books",
            description = " Finds and displays counted and sorted damages")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortDamagesByNumberOfRentedBooks(){

        LOGGER.info(" Count and sort all Damages based on Rented Books (API) query has been executed. ");

        return service.sortDamagesByNumberOfRentedBooks();
    }
}
