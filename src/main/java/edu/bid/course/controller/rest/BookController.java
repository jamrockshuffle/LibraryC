package edu.bid.course.controller.rest;

import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
import edu.bid.course.service.book.BookServiceImpl;
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
 * API methods for Book model
 * course.BookController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookController: 1.0
 */

@Tag(name = "BookControllerAPI", description = "Mostly GET methods with Book collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookRepository bookRepository;

    /**
     * Method to display all (raw) data from Book model
     */

    @Operation(summary = " Get all Books",
            description = " Finds and displays all Books")
    @RequestMapping("/get/all")
    public List<Book> getBooks (){

        LOGGER.info(" Get all Books (API) request has been called. ");

        return bookRepository.findAll();

    }

    /**
     * Method to display raw data from Book model with specified id
     */

    @Operation(summary = " Get one Book",
            description = " Finds and displays Book with specified id")
    @GetMapping("/get/{id}")
    public Book getById(@PathVariable("id")
                          @Parameter(name = "id",
                                  description = " This is the first field in the list")
                                  String id) {

        LOGGER.info(" Get Book by id (API) with id " + id +  " request has been called. ");

        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete data with specified id from Book model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Book deletion",
            description = " Deletes Book with specified id")
    @GetMapping("/delete/{id}")
    public List<Book> deleteById(@PathVariable("id")
                                   @Parameter(name = "id",
                                           description = " This is the first field in the list")
                                           String id) {

        LOGGER.info(" Delete Book by id (API) with id " + id +  " request has been called. ");

        bookRepository.deleteById(id);

        return bookRepository.findAll();
    }

    @Autowired
    BookServiceImpl service;

    /**
     * Method to create new data with auto-generated id in Book model
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Book creation",
            description = " Adds new book to the Book list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Book create(@RequestBody Book book) {

        LOGGER.info(" Create new Book (API) request has been called. ");

        return service.create(book);
    }

    /**
     * Method to update data with specified id from Book model
     */


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Book updation",
            description = " Updates Book with specified id")
    @PostMapping("/update")
    public Book update(@RequestBody Book book) {

        LOGGER.info(" Update existing Book (API) request has been called. ");

        return service.update(book);
    }

    /**
     * Method to count and sort all data in Book model based on renting model
     */

    @Operation(summary = " Count all books based on Rented Books",
            description = " Finds and displays counted and sorted books")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortBooksByNumberORentedOnes(){

        LOGGER.info(" Count and sort all books based on Books (API) query has been executed. ");

        return service.sortBooksByNumberORentedOnes();
    }
}
