package edu.bid.course.controller.rest;

import edu.bid.course.model.Register;
import edu.bid.course.model.Renting;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.register.RegisterServiceImpl;
import edu.bid.course.service.renting.RentingServiceImpl;
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
 * API methods for Renting model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingController: 1.0
 */

@Tag(name = "RentingControllerAPI", description = "Mostly GET methods with Renting collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/renting")
public class RentingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentingController.class);

    @Autowired
    RentingRepository rentingRepository;

    /**
     * Method to display all (raw) data from Renting model
     */

    @Operation(summary = " Get all Rented Books",
            description = " Finds and displays all Rented Books")
    @RequestMapping("/get/all")
    public List<Renting> getRented (){

        LOGGER.info(" Get all Rented Books (API) request has been called. ");

        return rentingRepository.findAll();

    }

    /**
     * Method to display raw data from Renting model with specified id
     */


    @Operation(summary = " Get one Rented Book",
            description = " Finds and displays Rented Book with specified id")
    @GetMapping("/get/{id}")
    public Renting getById(@PathVariable("id")
                            @Parameter(name = "id",
                                    description = " This is the first field in the list")
                                    String id) {

        LOGGER.info(" Get Rented Book by id (API) with id " + id +  " request has been called. ");

        return rentingRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Renting model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Rented Book deletion",
            description = " Deletes Rented Book with specified id")
    @GetMapping("/delete/{id}")
    public List<Renting> deleteById(@PathVariable("id")
                                     @Parameter(name = "id",
                                             description = " This is the first field in the list")
                                             String id) {

        LOGGER.info(" Delete Rented Book by id (API) with id " + id +  " request has been called. ");

        rentingRepository.deleteById(id);

        return rentingRepository.findAll();
    }

    @Autowired
    RentingServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Renting model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Rented Book creation",
            description = " Adds new Rented Book to the Renting list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Renting create(@RequestBody Renting renting) {

        LOGGER.info(" Create new Rented Book (API) request has been called. ");

        return service.create(renting);
    }

    /**
     * Method to update data with specified id from Renting model
     */


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Rented Book updation",
            description = " Updates Rented Book with specified id")
    @PostMapping("/update")
    public Renting update(@RequestBody Renting renting) {

        LOGGER.info(" Update existing Rented Book (API) request has been called. ");

        return service.update(renting);
    }

    /**
     * Method to count and sort all data in Renting model based on Author model
     */

    @Operation(summary = " Count all authors based on rented books",
            description = " Finds and displays counted and sorted authors")
    @RequestMapping("/get/allauthors")
    public Map<String, Integer> sortAuthorsByRentedBooks(){

        LOGGER.info(" Count and sort all authors based on Rented Books (API) query has been executed. ");

        return service.sortAuthorsByRentedBooks();
    }

    /**
     * Method to count and sort all data in Renting model based on Genre model
     */

    @Operation(summary = " Count all genres based on rented books",
            description = " Finds and displays counted and sorted genres")
    @RequestMapping("/get/allgenres")
    public Map<String, Integer> sortGenresByRentedBooks(){

        LOGGER.info(" Count and sort all genres based on Rented Books (API) query has been executed. ");

        return service.sortGenresByRentedBooks();
    }
}
