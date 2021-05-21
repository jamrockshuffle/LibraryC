package edu.bid.course.controller.rest;

import edu.bid.course.model.Register;
import edu.bid.course.model.Shift;
import edu.bid.course.repository.ShiftRepository;
import edu.bid.course.service.register.RegisterServiceImpl;
import edu.bid.course.service.shift.ShiftServiceImpl;
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
 * API methods for Shift model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftController: 1.0
 */

@Tag(name = "ShiftControllerAPI", description = "Mostly GET methods with Shift collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/shift")
public class ShiftController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    ShiftRepository shiftRepository;

    /**
     * Method to display all (raw) data from Shift model
     */

    @Operation(summary = " Get all Shifts",
            description = " Finds and displays all Shifts")
    @RequestMapping("/get/all")
    public List<Shift> getShifts (){

        LOGGER.info(" Get all Shifts (API) request has been called. ");

        return shiftRepository.findAll();

    }

    /**
     * Method to display raw data from Shift model with specified id
     */

    @Operation(summary = " Get one Shift",
            description = " Finds and displays Shift with specified id")
    @GetMapping("/get/{id}")
    public Shift getById(@PathVariable("id")
                            @Parameter(name = "id",
                                    description = " This is the first field in the list")
                                    String id) {

        LOGGER.info(" Get Shift by id (API) with id " + id +  " request has been called. ");

        return shiftRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Shift model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Shift deletion",
            description = " Deletes Shift with specified id")
    @GetMapping("/delete/{id}")
    public List<Shift> deleteById(@PathVariable("id")
                                     @Parameter(name = "id",
                                             description = " This is the first field in the list")
                                             String id) {

        LOGGER.info(" Delete Shift by id (API) with id " + id +  " request has been called. ");

        shiftRepository.deleteById(id);

        return shiftRepository.findAll();
    }

    @Autowired
    ShiftServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Shift model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Shift creation",
            description = " Adds new Shift to the Shift list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Shift create(@RequestBody Shift shift) {

        LOGGER.info(" Create new Shift (API) request has been called. ");

        return service.create(shift);
    }

    /**
     * Method to update data with specified id from Shift model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Shift updation",
            description = " Updates Shift with specified id")
    @PostMapping("/update")
    public Shift update(@RequestBody Shift shift) {

        LOGGER.info(" Update existing Shift (API) request has been called. ");

        return service.update(shift);
    }

    /**
     * Method to count and sort all data in Shift model based on Personnel model
     */

    @Operation(summary = " Count all shifts based on library workers",
            description = " Finds and displays counted and sorted shifts")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortShiftsByNumberOfMembers(){

        LOGGER.info(" Count and sort all shifts based on Library Workers (API) query has been executed. ");

        return service.sortShiftsByNumberOfWorkers();
    }

    /**
     * Method to find average among all values in each salary field in Personnel model by their shift field
     */

    @Operation(summary = " Find and sort average salary of a shift based on library workers",
            description = " Finds and displays sorted average salary of a shift")
    @RequestMapping("/get/averagesalary")
    public Map<String, BigDecimal> sortShiftsByAverageSalary(){

        LOGGER.info(" Find and sort average salary of a shift based on Library Workers (API) query has been executed. ");

        return service.sortShiftsByAverageSalary();
    }

    /**
     * Method to count all values in each salary field in Personnel model by their shift field
     */

    @Operation(summary = " Find and sort all salary of a shift based on library workers",
            description = " Finds and displays sorted all salary of a shift")
    @RequestMapping("/get/allsalary")
    public Map<String, BigDecimal> sortShiftsByAllSalary(){

        LOGGER.info(" Find and sort all salary of a shift based on Library Workers (API) query has been executed. ");

        return service.sortShiftsByAllSalary();
    }
}
