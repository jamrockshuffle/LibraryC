package edu.bid.course.controller.ui;

import edu.bid.course.form.PositionForm;
import edu.bid.course.form.StillageForm;
import edu.bid.course.model.Position;
import edu.bid.course.model.Shift;
import edu.bid.course.model.Stillage;
import edu.bid.course.repository.StillageRepository;
import edu.bid.course.service.position.PositionServiceImpl;
import edu.bid.course.service.stillage.StillageServiceImpl;
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
 * course.StillageUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageUIController: 1.0
 */

@RequestMapping("/ui/stillage")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class StillageUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StillageUIController.class);

    @Autowired
    StillageRepository stillageRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Stillages (UI) request has been called. ");

        List<Stillage> stillages = stillageRepository.findAll();

        model.addAttribute("stillages", stillages);

        return "stillage/stillage-page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Stillage (UI) by id with id " + id +  " request has been called. ");

        stillageRepository.deleteById(id);

        return "redirect:/ui/stillage/get/all";
    }

    @Autowired
    StillageServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        StillageForm stillageForm = new StillageForm();
        stillageForm.setStillageId(" ");
        stillageForm.setStillageDescription(" ");
        stillageForm.setDescription(" ");
        model.addAttribute("stillageForm", stillageForm);

        LOGGER.info(" Create Stillage Form (UI) has been called. ");

        return "stillage/stillage-create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("stillageForm") StillageForm stillageForm){

        Stillage stillage = new Stillage();
        stillage.setStillageId(stillageForm.getStillageId());
        stillage.setStillageDescription(stillageForm.getStillageDescription());
        stillage.setDescription(stillageForm.getDescription());

        service.create(stillage);

        LOGGER.info(" Create Stillage (UI) request has been called. A new Stillage has been created. ");

        return "redirect:/ui/stillage/get/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        StillageForm stillageForm = new StillageForm();

        stillageForm.setId(id);
        stillageForm.setStillageId(stillageRepository.findById(id).get().getStillageId());
        stillageForm.setStillageDescription(stillageRepository.findById(id).get().getStillageDescription());
        stillageForm.setDescription(stillageRepository.findById(id).get().getDescription());
        model.addAttribute("stillageUpdForm", stillageForm);

        LOGGER.info(" Update Stillage Form (UI) has been called. ");

        return "stillage/stillage-update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("stillageUpdForm") StillageForm stillageForm, @PathVariable String id){

        Stillage stillage = new Stillage();
        stillage.setId(stillageForm.getId());
        stillage.setStillageId(stillageForm.getStillageId());
        stillage.setStillageDescription(stillageForm.getStillageDescription());
        stillage.setDescription(stillageForm.getDescription());

        service.update(stillage);

        LOGGER.info(" Update Stillage (UI) request has been called. ");

        return "redirect:/ui/stillage/get/all";
    }
}
