package edu.bid.course.controller.rest;

import edu.bid.course.model.Author;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
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
 * API methods for Author model
 * course.AuthorController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorController: 1.0
 */

@Tag(name = "AuthorControllerAPI", description = "Mostly GET methods with Author collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/author")
public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    AuthorRepository authorRepository;

    /**
     * Method to display all (raw) data from Author model
     */

    @Operation(summary = " Get all Authors",
            description = " Finds and displays all Authors")
    @RequestMapping("/get/all")
    public List<Author> getAuthors (){

        LOGGER.info(" Get all Authors (API) request has been called. ");

        return authorRepository.findAll();

    }

    /**
     * Method to display raw data from Author model with specified id
     */

    @Operation(summary = " Get one Author",
            description = " Finds and displays Author with specified id")
    @GetMapping("/get/{id}")
    public Author getById(@PathVariable("id")
                          @Parameter(name = "id",
                                  description = " This is the first field in the list")
                                  String id) {

        LOGGER.info(" Get Author by id (API) with id " + id +  " request has been called. ");

        return authorRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Author model
     */

    @Operation(summary = " Author deletion",
            description = " Deletes Author with specified id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public List<Author> deleteById(@PathVariable("id")
                             @Parameter(name = "id",
                                     description = " This is the first field in the list")
                                     String id) {

        LOGGER.info(" Delete Author by id (API) with id " + id +  " request has been called. ");

        authorRepository.deleteById(id);

        return authorRepository.findAll();
    }

    /**
     * Method to create new data with auto-generated id in Author model
     */

    @Autowired
    AuthorServiceImpl service;

    @Operation(summary = " Author creation",
            description = " Adds new author to the Author list. Id to be created is UUID type ")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Author create(@RequestBody Author author) {

        LOGGER.info(" Create new Author (API) request has been called. ");

        return service.create(author);
    }

    /**
     * Method to update data with specified id from Author model
     */

    @Operation(summary = " Author updation",
            description = " Updates Author with specified id")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public Author update(@RequestBody Author author) {

        LOGGER.info(" Update existing Author (API) request has been called. ");

        return service.update(author);
    }

    /**
     * Method to count and sort all data in Author model based on Book model
     */

    @Operation(summary = " Count all authors based on books",
            description = " Finds and displays counted and sorted authors")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortBooksByNumberOAuthors(){

        LOGGER.info(" Count and sort all authors based on Books (API) query has been executed. ");

        return service.sortBooksByNumberOAuthors();
    }
}
