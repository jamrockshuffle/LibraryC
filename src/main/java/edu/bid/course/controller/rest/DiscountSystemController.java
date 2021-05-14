package edu.bid.course.controller.rest;

import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.repository.DiscountSystemRepository;
import edu.bid.course.service.book.BookServiceImpl;
import edu.bid.course.service.discountsystem.DiscountSystemServiceImpl;
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
 * course.DiscountSystemController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemController: 1.0
 */


@Tag(name = "DiscountSystemControllerAPI", description = "Mostly GET methods with DiscountSystem collection")
@RestController
@RequestMapping("api/discountsystem")
public class DiscountSystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSystemController.class);

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    @Operation(summary = " Get all Discount Categories",
            description = " Finds and displays all Discount Categories")
    @RequestMapping("/get/all")
    public List<DiscountSystem> getCategories (){

        LOGGER.info(" Get all Discount Categories (API) request has been called. ");

        return discountSystemRepository.findAll();

    }

    @Operation(summary = " Get one Discount Category",
            description = " Finds and displays Discount Category with specified id")
    @GetMapping("/get/{id}")
    public DiscountSystem getById(@PathVariable("id")
                        @Parameter(name = "id",
                                description = " This is the first field in the list")
                                String id) {

        LOGGER.info(" Get Discount Category by id (API) with id " + id +  " request has been called. ");

        return discountSystemRepository.findById(id).orElse(null);
    }

    @Operation(summary = " Discount Category deletion",
            description = " Deletes Discount Category with specified id")
    @GetMapping("/delete/{id}")
    public List<DiscountSystem> deleteById(@PathVariable("id")
                                 @Parameter(name = "id",
                                         description = " This is the first field in the list")
                                         String id) {

        LOGGER.info(" Delete Discount Category by id (API) with id " + id +  " request has been called. ");

        discountSystemRepository.deleteById(id);

        return discountSystemRepository.findAll();
    }

    @Autowired
    DiscountSystemServiceImpl service;

    @Operation(summary = " Discount Category creation",
            description = " Adds new Discount Category to the DiscountSystem list. Id to be created is UUID type ")
    @PostMapping("/create")
    public DiscountSystem create(@RequestBody DiscountSystem discountSystem) {

        LOGGER.info(" Create new Discount Category (API) request has been called. ");

        return service.create(discountSystem);
    }

    @Operation(summary = " Book updation",
            description = " Updates Book with specified id")
    @PostMapping("/update")
    public DiscountSystem update(@RequestBody DiscountSystem discountSystem) {

        LOGGER.info(" Update existing Discount Category (API) request has been called. ");

        return service.update(discountSystem);
    }

    @Operation(summary = " Count all categories based on registered library members",
            description = " Finds and displays counted and sorted categories")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortCategoriesByNumberOfMembers(){

        LOGGER.info(" Count and sort all categories based on registered Library Members (API) query has been executed. ");

        return service.sortCategoriesByNumberOfMembers();
    }
}
