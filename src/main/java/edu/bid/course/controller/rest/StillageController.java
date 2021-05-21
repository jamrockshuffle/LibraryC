package edu.bid.course.controller.rest;

import edu.bid.course.model.Shift;
import edu.bid.course.model.Stillage;
import edu.bid.course.repository.StillageRepository;
import edu.bid.course.service.shift.ShiftServiceImpl;
import edu.bid.course.service.stillage.StillageServiceImpl;
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
 * API methods for Stillage model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageController: 1.0
 */


@Tag(name = "StillageControllerAPI", description = "Mostly GET methods with Stillage collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/stillage")
public class StillageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StillageController.class);

    @Autowired
    StillageRepository stillageRepository;

    /**
     * Method to display all (raw) data from Stillage model
     */

    @Operation(summary = " Get all Stillages",
            description = " Finds and displays all Stillages")
    @RequestMapping("/get/all")
    public List<Stillage> getStillages (){

        LOGGER.info(" Get all Stillages (API) request has been called. ");

        return stillageRepository.findAll();

    }

    /**
     * Method to display raw data from Stillage model with specified id
     */

    @Operation(summary = " Get one Stillage",
            description = " Finds and displays Stillage with specified id")
    @GetMapping("/get/{id}")
    public Stillage getById(@PathVariable("id")
                         @Parameter(name = "id",
                                 description = " This is the first field in the list")
                                 String id) {

        LOGGER.info(" Get Stillage by id (API) with id " + id +  " request has been called. ");

        return stillageRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Stillage model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Stillage deletion",
            description = " Deletes Stillage with specified id")
    @GetMapping("/delete/{id}")
    public List<Stillage> deleteById(@PathVariable("id")
                                  @Parameter(name = "id",
                                          description = " This is the first field in the list")
                                          String id) {

        LOGGER.info(" Delete Stillage by id (API) with id " + id +  " request has been called. ");

        stillageRepository.deleteById(id);

        return stillageRepository.findAll();
    }

    @Autowired
    StillageServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Stillage model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Stillage creation",
            description = " Adds new Stillage to the Stillage list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Stillage create(@RequestBody Stillage stillage) {

        LOGGER.info(" Create new Stillage (API) request has been called. ");

        return service.create(stillage);
    }

    /**
     * Method to update data with specified id from Stillage model
     */


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Stillage updation",
            description = " Updates Stillage with specified id")
    @PostMapping("/update")
    public Stillage update(@RequestBody Stillage stillage) {

        LOGGER.info(" Update existing Stillage (API) request has been called. ");

        return service.update(stillage);
    }

    /**
     * Method to count and sort all data in Stillage model based on Book model
     */

    @Operation(summary = " Count all stillages based on books",
            description = " Finds and displays counted and sorted stillages")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortStillagesByNumberOBooks(){

        LOGGER.info(" Count and sort all stillages based on Books (API) query has been executed. ");

        return service.sortStillagesByNumberOBooks();
    }
}
