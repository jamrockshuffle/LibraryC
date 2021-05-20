package edu.bid.course.controller.ui;

import edu.bid.course.form.PenaltySystemForm;
import edu.bid.course.form.PositionForm;
import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.model.Position;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.repository.PenaltySystemRepository;
import edu.bid.course.service.penaltysystem.PenaltySystemServiceImpl;
import edu.bid.course.service.position.PositionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystemUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemUIController: 1.0
 */

@RequestMapping("/ui/penaltysystem")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class PenaltySystemUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltySystemUIController.class);

    @Autowired
    PenaltySystemRepository penaltySystemRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Damages (UI) request has been called. ");

        List<PenaltySystem> damages = penaltySystemRepository.findAll();

        model.addAttribute("damages", damages);

        return "penaltysystem/penaltysystem-page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Damage (UI) by id with id " + id +  " request has been called. ");

        penaltySystemRepository.deleteById(id);

        return "redirect:/ui/penaltysystem/get/all";
    }

    @Autowired
    PenaltySystemServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        PenaltySystemForm penaltySystemForm = new PenaltySystemForm();

        penaltySystemForm.setDamageDescription(" ");
        penaltySystemForm.setPenaltySum(" ");
        penaltySystemForm.setDescription(" ");

        model.addAttribute("penaltySystemForm", penaltySystemForm);

        LOGGER.info(" Create Damage Form (UI) has been called. ");

        return "penaltysystem/penaltysystem-create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("penaltySystemForm") PenaltySystemForm penaltySystemForm){

        PenaltySystem penaltySystem = new PenaltySystem();
        penaltySystem.setDamageDescription(penaltySystemForm.getDamageDescription());
        penaltySystem.setPenaltySum(new BigDecimal(penaltySystemForm.getPenaltySum()));
        penaltySystem.setDescription(penaltySystemForm.getDescription());

        service.create(penaltySystem);

        LOGGER.info(" Create Damage (UI) request has been called. A new Damage has been created. ");

        return "redirect:/ui/penaltysystem/get/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        PenaltySystemForm penaltySystemForm = new PenaltySystemForm();

        penaltySystemForm.setId(id);
        penaltySystemForm.setDamageDescription(penaltySystemRepository.findById(id).get().getDamageDescription());
        penaltySystemForm.setPenaltySum(String.valueOf(penaltySystemRepository.findById(id).get().getPenaltySum()));
        penaltySystemForm.setDescription(penaltySystemRepository.findById(id).get().getDescription());

        model.addAttribute("penaltySystemUpdForm", penaltySystemForm);

        LOGGER.info(" Update Damage Form (UI) has been called. ");

        return "penaltysystem/penaltysystem-update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("penaltySystemUpdForm") PenaltySystemForm penaltySystemForm, @PathVariable String id){

        PenaltySystem penaltySystem = new PenaltySystem();

        penaltySystem.setId(penaltySystemForm.getId());
        penaltySystem.setDamageDescription(penaltySystemForm.getDamageDescription());
        penaltySystem.setPenaltySum(new BigDecimal(penaltySystemForm.getPenaltySum()));
        penaltySystem.setDescription(penaltySystemForm.getDescription());

        service.update(penaltySystem);

        LOGGER.info(" Create Damage (UI) request has been called. ");

        return "redirect:/ui/penaltysystem/get/all";
    }
}
