package edu.bid.course.controller.ui;

import edu.bid.course.form.AuthorForm;
import edu.bid.course.form.PositionForm;
import edu.bid.course.model.Author;
import edu.bid.course.model.Position;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
import edu.bid.course.service.position.PositionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.AuthorUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorUIController: 1.0
 */

@RequestMapping("/ui/author")
@Controller
public class AuthorUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorUIController.class);

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Authors (UI) request has been called. ");

        List<Author> authors = authorRepository.findAll();

        model.addAttribute("authors", authors);

        return "author/author-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Author (UI) by id with id " + id +  " request has been called. ");

        authorRepository.deleteById(id);

        return "redirect:/ui/author/get/all";
    }

    @Autowired
    AuthorServiceImpl service;

    @GetMapping("/create")
    public String create(Model model){

        AuthorForm authorForm = new AuthorForm();
        authorForm.setName(" ");
        authorForm.setDescription(" ");
        model.addAttribute("authorForm", authorForm);

        LOGGER.info(" Create Author Form (UI) has been called. ");

        return "author/author-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("positionForm") AuthorForm authorForm){

        Author author = new Author();
        author.setName(authorForm.getName());
        author.setDescription(authorForm.getDescription());

        service.create(author);

        LOGGER.info(" Create Author (UI) request has been called. A new Position has been created. ");

        return "redirect:/ui/author/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){


        AuthorForm authorForm = new AuthorForm();

        authorForm.setId(id);
        authorForm.setName(authorRepository.findById(id).get().getName());
        authorForm.setDescription(authorRepository.findById(id).get().getDescription());

        model.addAttribute("authorUpdForm", authorForm);

        LOGGER.info(" Update Author Form (UI) with id " + id +  " has been called. ");

        return "author/author-update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("authorUpdForm") AuthorForm authorForm, @PathVariable String id){


        Author author = new Author();

        author.setId(authorForm.getId());
        author.setName(authorForm.getName());
        author.setDescription(authorForm.getDescription());

        service.update(author);

        LOGGER.info(" Update Author (UI) by id with id " + id +  " request has been called. ");

        return "redirect:/ui/author/get/all";
    }
}
