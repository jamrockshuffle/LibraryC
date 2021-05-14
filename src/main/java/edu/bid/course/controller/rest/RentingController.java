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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * course.RentingController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingController: 1.0
 */

@Tag(name = "RentingControllerAPI", description = "Mostly GET methods with Renting collection")
@RestController
@RequestMapping("api/renting")
public class RentingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentingController.class);

    @Autowired
    RentingRepository rentingRepository;

    @Operation(summary = " Get all Rented Books",
            description = " Finds and displays all Rented Books")
    @RequestMapping("/get/all")
    public List<Renting> getRented (){

        LOGGER.info(" Get all Rented Books (API) request has been called. ");

        return rentingRepository.findAll();

    }

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

    @Operation(summary = " Rented Book creation",
            description = " Adds new Rented Book to the Renting list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Renting create(@RequestBody Renting renting) {

        LOGGER.info(" Create new Rented Book (API) request has been called. ");

        return service.create(renting);
    }

    @Operation(summary = " Rented Book updation",
            description = " Updates Rented Book with specified id")
    @PostMapping("/update")
    public Renting update(@RequestBody Renting renting) {

        LOGGER.info(" Update existing Rented Book (API) request has been called. ");

        return service.update(renting);
    }

    @Operation(summary = " Count all authors based on rented books",
            description = " Finds and displays counted and sorted authors")
    @RequestMapping("/get/allauthors")
    public Map<String, Integer> sortAuthorsByRentedBooks(){

        LOGGER.info(" Count and sort all authors based on Rented Books (API) query has been executed. ");

        return service.sortAuthorsByRentedBooks();
    }

    @Operation(summary = " Count all genres based on rented books",
            description = " Finds and displays counted and sorted genres")
    @RequestMapping("/get/allgenres")
    public Map<String, Integer> sortGenresByRentedBooks(){

        LOGGER.info(" Count and sort all genres based on Rented Books (API) query has been executed. ");

        return service.sortGenresByRentedBooks();
    }
}
