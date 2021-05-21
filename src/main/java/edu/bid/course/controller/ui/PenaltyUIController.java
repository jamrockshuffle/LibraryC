package edu.bid.course.controller.ui;

import edu.bid.course.form.PenaltyForm;
import edu.bid.course.form.RegisterForm;
import edu.bid.course.model.*;
import edu.bid.course.repository.GenreRepository;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.repository.PenaltySystemRepository;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.penalty.PenaltyServiceImpl;
import edu.bid.course.service.penaltysystem.PenaltySystemServiceImpl;
import edu.bid.course.service.renting.RentingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UI methods for Penalty model
 * course.PenaltyUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltyUIController: 1.0
 */

@RequestMapping("/ui/penalty")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class PenaltyUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltyUIController.class);

    @Autowired
    PenaltyRepository penaltyRepository;

    /**
     * Method to display all data from Penalty model and return it in .ftlh-file
     */

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Penalties (UI) request has been called. ");

        List<Penalty> penalties = penaltyRepository.findAll();

        model.addAttribute("penalties", penalties);

        return "penalty/penalty-page";
    }

    /**
     * Method to delete data with specified id from Penalty model and return previous method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Penalty (UI) by id with id " + id +  " request has been called. ");

        penaltyRepository.deleteById(id);

        return "redirect:/ui/penalty/get/all";
    }

    @Autowired
    PenaltyServiceImpl service;

    @Autowired
    RentingRepository rentingRepository;

    @Autowired
    RentingServiceImpl rentingService;

    @Autowired
    PenaltySystemRepository penaltySystemRepository;

    @Autowired
    PenaltySystemServiceImpl penaltySystemService;

    /**
     * Method to display form where all creation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        PenaltyForm penaltyForm = new PenaltyForm();
        penaltyForm.setRentedBook(" ");
        penaltyForm.setPenaltyClause(" ");
        penaltyForm.setDescription(" ");

        model.addAttribute("penaltyForm", penaltyForm);

        List<String> renteds = rentingRepository.findAll()
                .stream()
                .map(r -> r.getBook().getName())
                .collect(Collectors.toList());

        model.addAttribute("renteds", renteds);

        List<String> penalties = penaltySystemRepository.findAll()
                .stream()
                .map(PenaltySystem::getDamageDescription)
                .collect(Collectors.toList());

        model.addAttribute("penalties", penalties);

        LOGGER.info(" Create Penalty Form (UI) has been called. ");

        return "penalty/penalty-create";
    }

    /**
     * Method to create new data (based of data from previous method) with auto-generated id in Penalty model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("penaltyForm") PenaltyForm penaltyForm){


        Penalty penalty = new Penalty();

        String bookName = penaltyForm.getRentedBook();
        Renting renting = rentingService.getByName(penaltyForm.getRentedBook());
        penalty.setRentedBook(renting);

        String penaltyName = penaltyForm.getPenaltyClause();
        PenaltySystem penaltySystem = penaltySystemService.getByName(penaltyForm.getPenaltyClause());
        penalty.setPenaltyClause(penaltySystem);

        penalty.setDescription(penaltyForm.getDescription());

        service.create(penalty);

        LOGGER.info(" Create Penalty (UI) request has been called. A new Penalty has been created. ");

        return "redirect:/ui/penalty/get/all";
    }

    /**
     * Method to display form where all updation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        PenaltyForm penaltyForm = new PenaltyForm();
        penaltyForm.setId(id);


        String rented = penaltyRepository.findById(id)
                .get().getRentedBook().getBook().getName();

        String penalty = penaltyRepository.findById(id)
                .get().getPenaltyClause().getDamageDescription();

        penaltyForm.setRentedBook(rented);
        penaltyForm.setPenaltyClause(penalty);

        penaltyForm.setDescription(penaltyRepository.findById(id).get().getDescription());

        model.addAttribute("penaltyUpdForm", penaltyForm);

        List<String> renteds = rentingRepository.findAll()
                .stream()
                .map(r -> r.getBook().getName())
                .collect(Collectors.toList());

        model.addAttribute("rentedsUpd", renteds);

        List<String> penalties = penaltySystemRepository.findAll()
                .stream()
                .map(PenaltySystem::getDamageDescription)
                .collect(Collectors.toList());

        model.addAttribute("penaltiesUpd", penalties);

        LOGGER.info(" Update Penalty Form (UI) has been called. ");

        return "penalty/penalty-update";
    }

    /**
     * Method to update new data (based of data from previous method) in Penalty model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("penaltyUpdForm") PenaltyForm penaltyForm, @PathVariable String id){


        Penalty penalty = new Penalty();
        penalty.setId(penaltyForm.getId());

        String bookName = penaltyForm.getRentedBook();
        Renting renting = rentingService.getByName(penaltyForm.getRentedBook());
        penalty.setRentedBook(renting);

        String penaltyName = penaltyForm.getPenaltyClause();
        PenaltySystem penaltySystem = penaltySystemService.getByName(penaltyForm.getPenaltyClause());
        penalty.setPenaltyClause(penaltySystem);

        penalty.setDescription(penaltyForm.getDescription());

        service.update(penalty);

        LOGGER.info(" Update Penalty (UI) request has been called. ");

        return "redirect:/ui/penalty/get/all";
    }
}
