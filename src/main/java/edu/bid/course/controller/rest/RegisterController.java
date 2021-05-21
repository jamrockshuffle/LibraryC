package edu.bid.course.controller.rest;

import edu.bid.course.model.Publisher;
import edu.bid.course.model.Register;
import edu.bid.course.repository.RegisterRepository;
import edu.bid.course.service.publisher.PublisherServiceImpl;
import edu.bid.course.service.register.RegisterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * API methods for Register model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterController: 1.0
 */


@Tag(name = "RegisterControllerAPI", description = "Mostly GET methods with Register collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/register")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    RegisterRepository registerRepository;

    /**
     * Method to display all (raw) data from Register model
     */

    @Operation(summary = " Get all Members",
            description = " Finds and displays all Members")
    @RequestMapping("/get/all")
    public List<Register> getMembers (){

        LOGGER.info(" Get all Members (API) request has been called. ");

        return registerRepository.findAll();

    }

    /**
     * Method to display raw data from Register model with specified id
     */

    @Operation(summary = " Get one Member",
            description = " Finds and displays Member with specified id")
    @GetMapping("/get/{id}")
    public Register getById(@PathVariable("id")
                             @Parameter(name = "id",
                                     description = " This is the first field in the list")
                                     String id) {

        LOGGER.info(" Get Member by id (API) with id " + id +  " request has been called. ");

        return registerRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Register model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Member deletion",
            description = " Deletes Member with specified id")
    @GetMapping("/delete/{id}")
    public List<Register> deleteById(@PathVariable("id")
                                      @Parameter(name = "id",
                                              description = " This is the first field in the list")
                                              String id) {

        LOGGER.info(" Delete Member by id (API) with id " + id +  " request has been called. ");

        registerRepository.deleteById(id);

        return registerRepository.findAll();
    }

    @Autowired
    RegisterServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Register model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Member creation",
            description = " Adds new Member to the Register list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Register create(@RequestBody Register register) {

        LOGGER.info(" Create new Member (API) request has been called. ");

        return service.create(register);
    }

    /**
     * Method to update data with specified id from Register model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Member updation",
            description = " Updates Member with specified id")
    @PostMapping("/update")
    public Register update(@RequestBody Register register) {

        LOGGER.info(" Update existing Member (API) request has been called. ");

        return service.update(register);
    }

    /**
     * Method to count and sort all data in Register model based on Renting model
     */

    @Operation(summary = " Count all readers based on Rented Books",
            description = " Finds and displays counted and sorted readers")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortReadersByNumberORentedBooks(){

        LOGGER.info(" Count and sort all readers based on Books (API) query has been executed. ");

        return service.sortReadersByNumberORentedBooks();
    }

    /**
     * Method to count and sort all data in Register model based on its birthday field
     */

    @Operation(summary = " Count all readers based on their birthday",
            description = " Finds and displays counted and sorted readers")
    @RequestMapping("/get/sortedbirth")
    public Map<LocalDate, Integer> sortReadersByBirthday(){

        LOGGER.info(" Count and sort all readers based on their birthday (API) query has been executed. ");

        return service.sortReadersByBirthday();
    }
}
