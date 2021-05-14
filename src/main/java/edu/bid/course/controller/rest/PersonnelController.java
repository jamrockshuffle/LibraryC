package edu.bid.course.controller.rest;

import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.model.Personnel;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.service.penaltysystem.PenaltySystemServiceImpl;
import edu.bid.course.service.personnel.PersonnelServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * course.PersonnelController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelController: 1.0
 */



@Tag(name = "PersonnelControllerAPI", description = "Mostly GET methods with Personnel collection")
@RestController
@RequestMapping("api/personnel")
public class PersonnelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnelController.class);

    @Autowired
    PersonnelRepository personnelRepository;

    @Operation(summary = " Get all Personnel",
            description = " Finds and displays all Personnel")
    @RequestMapping("/get/all")
    public List<Personnel> getPersonnel (){

        LOGGER.info(" Get all Personnel (API) request has been called. ");

        return personnelRepository.findAll();

    }

    @Operation(summary = " Get one Worker",
            description = " Finds and displays Worker with specified id")
    @GetMapping("/get/{id}")
    public Personnel getById(@PathVariable("id")
                           @Parameter(name = "id",
                                   description = " This is the first field in the list")
                                   String id) {

        LOGGER.info(" Get Worker by id (API) with id " + id +  " request has been called. ");

        return personnelRepository.findById(id).orElse(null);
    }

    @Operation(summary = " Worker deletion",
            description = " Deletes Worker with specified id")
    @GetMapping("/delete/{id}")
    public List<Personnel> deleteById(@PathVariable("id")
                                    @Parameter(name = "id",
                                            description = " This is the first field in the list")
                                            String id) {

        LOGGER.info(" Delete Worker by id (API) with id " + id +  " request has been called. ");

        personnelRepository.deleteById(id);

        return personnelRepository.findAll();
    }

    @Autowired
    PersonnelServiceImpl service;

    @Operation(summary = " Worker creation",
            description = " Adds new Worker to the Personnel list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Personnel create(@RequestBody Personnel personnel) {

        LOGGER.info(" Create new Damage (API) request has been called. ");

        return service.create(personnel);
    }

    @Operation(summary = " Damage updation",
            description = " Updates Damage with specified id")
    @PostMapping("/update")
    public Personnel update(@RequestBody Personnel personnel) {

        LOGGER.info(" Update existing Damage (API) request has been called. ");

        return service.update(personnel);
    }

    @Operation(summary = " Count all renting workers based on Rented Books",
            description = " Finds and displays counted and sorted renting workers")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortWorkersByNumberORentedBooks(){

        LOGGER.info(" Count and sort all renting workers based on Books (API) query has been executed. ");

        return service.sortWorkersByNumberORentedBooks();
    }

    @Operation(summary = " Count all workers salary",
            description = " Finds and displays counted salary of workers")
    @RequestMapping("/get/allsalary")
    public Map<String, BigDecimal> getAllSalary(){

        LOGGER.info(" Count and workers salary (API) query has been executed. ");

        return service.getAllSalary();
    }

    @Operation(summary = " Find and sort average salary of workers",
            description = " Finds and displays sorted average salary of workers")
    @RequestMapping("/get/averagesalary")
    public Map<String, BigDecimal> getAverageSalary(){

        LOGGER.info(" Find average salary of workers (API) query has been executed. ");

        return service.getAverageSalary();
    }

    @Operation(summary = " Count all workers based on their birthday",
            description = " Finds and displays counted and sorted workers")
    @RequestMapping("/get/sortedbirth")
    public Map<LocalDate, Integer> sortWorkersByBirthday(){

        LOGGER.info(" Count and sort all workers based on their birthday (API) query has been executed. ");

        return service.sortWorkersByBirthday();
    }
}
