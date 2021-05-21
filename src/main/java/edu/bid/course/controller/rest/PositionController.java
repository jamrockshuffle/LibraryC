package edu.bid.course.controller.rest;

import edu.bid.course.model.Author;
import edu.bid.course.model.Personnel;
import edu.bid.course.model.Position;
import edu.bid.course.repository.PositionRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
import edu.bid.course.service.position.PositionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * API methods for Position model
 * course.PositionController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PositionController: 1.0
 */


@Tag(name = "PositionControllerAPI", description = "Mostly GET methods with Position collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/position")
public class PositionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);

    @Autowired
    PositionRepository positionRepository;

    /**
     * Method to display all (raw) data from Position model
     */

    @Operation(summary = " Get all Positions",
            description = " Finds and displays all Positions")
    @RequestMapping("/get/all")
    public List<Position> getPositions (){

        LOGGER.info(" Get all Positions (API) request has been called. ");

        return positionRepository.findAll();

    }

    /**
     * Method to display raw data from Position model with specified id
     */

    @Operation(summary = " Get one Position",
            description = " Finds and displays Position with specified id")
    @GetMapping("/get/{id}")
    public Position getById(@PathVariable("id")
                             @Parameter(name = "id",
                                     description = " This is the first field in the list")
                                     String id) {

        LOGGER.info(" Get Position by id (API) with id " + id +  " request has been called. ");

        return positionRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Position model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Position deletion",
            description = " Deletes Position with specified id")
    @GetMapping("/delete/{id}")
    public List<Position> deleteById(@PathVariable("id")
                                      @Parameter(name = "id",
                                              description = " This is the first field in the list")
                                              String id) {

        LOGGER.info(" Delete Position by id (API) with id " + id +  " request has been called. ");

        positionRepository.deleteById(id);

        return positionRepository.findAll();
    }

    @Autowired
    PositionServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Position model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Position creation",
            description = " Adds new Position to the Position list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Position create(@RequestBody Position position) {

        LOGGER.info(" Create new Position (API) request has been called. ");

        return service.create(position);
    }

    /**
     * Method to update data with specified id from Position model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Position updation",
            description = " Updates Position with specified id")
    @PostMapping("/update")
    public Position update(@RequestBody Position position) {

        LOGGER.info(" Update existing Author (API) request has been called. ");

        return service.update(position);
    }

    /**
     * Method to count and sort all data in Position model based on Personnel model
     */

    @Operation(summary = " Count all positions based on library workers",
            description = " Finds and displays counted and sorted positions")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortPositionsByNumberOfWorkers(){

        LOGGER.info(" Count and sort all positions based on Library Workers (API) query has been executed. ");

        return service.sortPositionsByNumberOfWorkers();
    }

    /**
     * Method to find average among all values in each salary field in Personnel model by their position field
     */

    @Operation(summary = " Find and sort average salary of a position based on library workers",
            description = " Finds and displays sorted average salary of a position")
    @RequestMapping("/get/averagesalary")
    public Map<String, BigDecimal> sortPositionsByAverageSalary(){

        LOGGER.info(" Find and sort average salary of a position based on Library Workers (API) query has been executed. ");

        return service.sortPositionsByAverageSalary();
    }

    /**
     * Method to count all values in each salary field in Personnel model by their position field
     */

    @Operation(summary = " Find and sort all salary of a position based on library workers",
            description = " Finds and displays sorted all salary of a position")
    @RequestMapping("/get/allsalary")
    public Map<String, BigDecimal> sortPositionsByAllSalary(){

        LOGGER.info(" Find and sort all salary of a position based on Library Workers (API) query has been executed. ");

        return service.sortPositionsByAllSalary();
    }
}
