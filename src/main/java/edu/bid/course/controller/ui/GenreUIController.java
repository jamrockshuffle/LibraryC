package edu.bid.course.controller.ui;

import edu.bid.course.form.GenreForm;
import edu.bid.course.form.PositionForm;
import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;
import edu.bid.course.model.Position;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.repository.GenreRepository;
import edu.bid.course.service.genre.GenreServiceImpl;
import edu.bid.course.service.position.PositionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UI methods for Genre model
 * course.GenreUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreUIController: 1.0
 */

@RequestMapping("/ui/genre")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class GenreUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreUIController.class);

    @Autowired
    GenreRepository genreRepository;

    /**
     * Method to display all data from Genre model and return it in .ftlh-file
     */

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Genres (UI) request has been called. ");

        List<Genre> genres = genreRepository.findAll();

        model.addAttribute("genres", genres);

        return "genre/genre-page";
    }

    /**
     * Method to delete data with specified id from Genre model and return previous method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Genre (UI) by id with id " + id +  " request has been called. ");

        genreRepository.deleteById(id);

        return "redirect:/ui/genre/get/all";
    }

    @Autowired
    GenreServiceImpl service;

    /**
     * Method to display form where all creation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        GenreForm genreForm = new GenreForm();
        genreForm.setName(" ");
        genreForm.setDescription(" ");
        model.addAttribute("genreForm", genreForm);

        LOGGER.info(" Create Genre Form (UI) has been called. ");

        return "genre/genre-create";
    }

    /**
     * Method to create new data (based of data from previous method) with auto-generated id in Genre model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("genreForm") GenreForm genreForm){

        Genre genre = new Genre();
        genre.setName(genreForm.getName());
        genre.setDescription(genreForm.getDescription());

        service.create(genre);

        LOGGER.info(" Create Genre (UI) request has been called. A new Genre has been created. ");

        return "redirect:/ui/genre/get/all";
    }

    /**
     * Method to display form where all updation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        GenreForm genreForm = new GenreForm();
        genreForm.setId(id);
        genreForm.setName(genreRepository.findById(id).get().getName());
        genreForm.setDescription(genreRepository.findById(id).get().getDescription());
        model.addAttribute("genreUpdForm", genreForm);

        LOGGER.info(" Update Genre Form (UI) has been called. ");

        return "genre/genre-update";
    }

    /**
     * Method to update new data (based of data from previous method) in Genre model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("genreUpdForm") GenreForm genreForm, @PathVariable String id){

        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(genreForm.getName());
        genre.setDescription(genreForm.getDescription());

        service.update(genre);

        LOGGER.info(" Update Genre (UI) request has been called. ");

        return "redirect:/ui/genre/get/all";
    }
}
