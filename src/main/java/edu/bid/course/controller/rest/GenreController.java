package edu.bid.course.controller.rest;

import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Genre;
import edu.bid.course.repository.GenreRepository;
import edu.bid.course.service.book.BookServiceImpl;
import edu.bid.course.service.genre.GenreServiceImpl;
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
 * Created by IntelliJ IDEA.
 * course.GenreController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreController: 1.0
 */

@Tag(name = "GenreControllerAPI", description = "Mostly GET methods with DiscountSystem collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/genre")
public class GenreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    GenreRepository genreRepository;

    @Operation(summary = " Get all Genres",
            description = " Finds and displays all Genres")
    @RequestMapping("/get/all")
    public List<Genre> getGenres (){

        LOGGER.info(" Get all Genres (API) request has been called. ");

        return genreRepository.findAll();

    }

    @Operation(summary = " Get one Genre",
            description = " Finds and displays Genre with specified id")
    @GetMapping("/get/{id}")
    public Genre getById(@PathVariable("id")
                                  @Parameter(name = "id",
                                          description = " This is the first field in the list")
                                          String id) {

        LOGGER.info(" Get Genre by id (API) with id " + id +  " request has been called. ");

        return genreRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Genre deletion",
            description = " Deletes Genre with specified id")
    @GetMapping("/delete/{id}")
    public List<Genre> deleteById(@PathVariable("id")
                                           @Parameter(name = "id",
                                                   description = " This is the first field in the list")
                                                   String id) {

        LOGGER.info(" Delete Genre by id (API) with id " + id +  " request has been called. ");

        genreRepository.deleteById(id);

        return genreRepository.findAll();
    }

    @Autowired
    GenreServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Genre creation",
            description = " Adds new Genre to the Genre list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Genre create(@RequestBody Genre genre) {

        LOGGER.info(" Create new Genre (API) request has been called. ");

        return service.create(genre);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Genre updation",
            description = " Updates Genre with specified id")
    @PostMapping("/update")
    public Genre update(@RequestBody Genre genre) {

        LOGGER.info(" Update existing Genre (API) request has been called. ");

        return service.update(genre);
    }

    @Operation(summary = " Count all genres based on books",
            description = " Finds and displays counted and sorted genres")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortGenresByNumberOBooks(){

        LOGGER.info(" Count and sort all genres based on Books (API) query has been executed. ");

        return service.sortGenresByNumberOBooks();
    }
}
