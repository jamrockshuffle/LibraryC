package edu.bid.course.controller.rest;

import edu.bid.course.model.Genre;
import edu.bid.course.model.Penalty;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.service.genre.GenreServiceImpl;
import edu.bid.course.service.penalty.PenaltyServiceImpl;
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
 * API methods for Penalty model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltyController: 1.0
 */


@Tag(name = "PenaltyControllerAPI", description = "Mostly GET methods with Penalty collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/penalty")
public class PenaltyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltyController.class);

    @Autowired
    PenaltyRepository penaltyRepository;

    /**
     * Method to display all (raw) data from Penalty model
     */

    @Operation(summary = " Get all Penalties",
            description = " Finds and displays all Penalties")
    @RequestMapping("/get/all")
    public List<Penalty> getPenalties (){

        LOGGER.info(" Get all Penalties (API) request has been called. ");

        return penaltyRepository.findAll();

    }

    /**
     * Method to display raw data from Penalty model with specified id
     */

    @Operation(summary = " Get one Penalty",
            description = " Finds and displays Penalty with specified id")
    @GetMapping("/get/{id}")
    public Penalty getById(@PathVariable("id")
                         @Parameter(name = "id",
                                 description = " This is the first field in the list")
                                 String id) {

        LOGGER.info(" Get Penalty by id (API) with id " + id +  " request has been called. ");

        return penaltyRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Penalty model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Penalty deletion",
            description = " Deletes Penalty with specified id")
    @GetMapping("/delete/{id}")
    public List<Penalty> deleteById(@PathVariable("id")
                                  @Parameter(name = "id",
                                          description = " This is the first field in the list")
                                          String id) {

        LOGGER.info(" Delete Penalty by id (API) with id " + id +  " request has been called. ");

        penaltyRepository.deleteById(id);

        return penaltyRepository.findAll();
    }

    @Autowired
    PenaltyServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Penalty model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Penalty creation",
            description = " Adds new Penalty to the Penalty list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Penalty create(@RequestBody Penalty penalty) {

        LOGGER.info(" Create new Penalty (API) request has been called. ");

        return service.create(penalty);
    }

    /**
     * Method to update data with specified id from Penalty model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Penalty updation",
            description = " Updates Penalty with specified id")
    @PostMapping("/update")
    public Penalty update(@RequestBody Penalty penalty) {

        LOGGER.info(" Update existing Penalty (API) request has been called. ");

        return service.update(penalty);
    }

    /**
     * Method to count and sort all data in Renting model based on Penalty model
     */

    @Operation(summary = " Count all Rented Books based on Damages",
            description = " Finds and displays counted and sorted rented books")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortRentedBooksByNumberOfDamages(){

        LOGGER.info(" Count and sort all Rented Books based on Damages (API) query has been executed. ");

        return service.sortRentedBooksByNumberOfDamages();
    }
}
