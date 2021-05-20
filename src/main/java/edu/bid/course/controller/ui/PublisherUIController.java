package edu.bid.course.controller.ui;

import edu.bid.course.form.PositionForm;
import edu.bid.course.form.PublisherForm;
import edu.bid.course.model.Position;
import edu.bid.course.model.Publisher;
import edu.bid.course.repository.PublisherRepository;
import edu.bid.course.service.position.PositionServiceImpl;
import edu.bid.course.service.publisher.PublisherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.PublisherUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PublisherUIController: 1.0
 */

@RequestMapping("/ui/publisher")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class PublisherUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherUIController.class);

    @Autowired
    PublisherRepository publisherRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Publishers (UI) request has been called. ");

        List<Publisher> publishers = publisherRepository.findAll();

        model.addAttribute("publishers", publishers);

        return "publisher/publisher-page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Publisher (UI) by id with id " + id +  " request has been called. ");

        publisherRepository.deleteById(id);

        return "redirect:/ui/publisher/get/all";
    }

    @Autowired
    PublisherServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        PublisherForm publisherForm = new PublisherForm();
        publisherForm.setName(" ");
        publisherForm.setDescription(" ");
        model.addAttribute("publisherForm", publisherForm);

        LOGGER.info(" Create Publisher Form (UI) has been called. ");

        return "publisher/publisher-create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("publisherForm") PublisherForm publisherForm){

        Publisher publisher = new Publisher();
        publisher.setName(publisherForm.getName());
        publisher.setDescription(publisherForm.getDescription());

        service.create(publisher);

        LOGGER.info(" Create Publisher (UI) request has been called. A new Publisher has been created. ");

        return "redirect:/ui/publisher/get/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        PublisherForm publisherForm = new PublisherForm();

        publisherForm.setId(id);
        publisherForm.setName(publisherRepository.findById(id).get().getName());
        publisherForm.setDescription(publisherRepository.findById(id).get().getDescription());
        model.addAttribute("publisherUpdForm", publisherForm);

        LOGGER.info(" Update Publisher Form (UI) has been called. ");

        return "publisher/publisher-update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("publisherUpdForm") PublisherForm publisherForm, @PathVariable String id){

        Publisher publisher = new Publisher();

        publisher.setId(publisherForm.getId());
        publisher.setName(publisherForm.getName());
        publisher.setDescription(publisherForm.getDescription());

        service.update(publisher);

        LOGGER.info(" Update Publisher (UI) request has been called. ");

        return "redirect:/ui/publisher/get/all";
    }
}
