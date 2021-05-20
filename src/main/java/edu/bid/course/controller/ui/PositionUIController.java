package edu.bid.course.controller.ui;

import edu.bid.course.form.PositionForm;
import edu.bid.course.model.Author;
import edu.bid.course.model.Position;
import edu.bid.course.repository.PositionRepository;
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
 * Created by IntelliJ IDEA.
 * course.PositionUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PositionUIController: 1.0
 */

@RequestMapping("/ui/position")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class PositionUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionUIController.class);

    @Autowired
    PositionRepository positionRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Positions (UI) request has been called. ");

        List<Position> positions = positionRepository.findAll();

        model.addAttribute("positions", positions);

        return "position/position-page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Position (UI) by id with id " + id +  " request has been called. ");

        positionRepository.deleteById(id);

        return "redirect:/ui/position/get/all";
    }

    @Autowired
    PositionServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        PositionForm positionForm = new PositionForm();
        positionForm.setName(" ");
        positionForm.setDescription(" ");
        model.addAttribute("positionForm", positionForm);

        LOGGER.info(" Create Position Form (UI) has been called. ");

        return "position/position-create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("positionForm") PositionForm positionForm){

        Position position = new Position();
        position.setName(positionForm.getName());
        position.setDescription(positionForm.getDescription());

        service.create(position);

        LOGGER.info(" Create Position (UI) request has been called. A new Position has been created. ");

        return "redirect:/ui/position/get/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        PositionForm positionForm = new PositionForm();
        positionForm.setId(id);
        positionForm.setName(positionRepository.findById(id).get().getName());
        positionForm.setDescription(positionRepository.findById(id).get().getDescription());
        model.addAttribute("positionUpdForm", positionForm);

        LOGGER.info(" Update Position Form (UI) has been called. ");

        return "position/position-update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("positionUpdForm") PositionForm positionForm, @PathVariable String id){

        Position position = new Position();

        position.setId(positionForm.getId());
        position.setName(positionForm.getName());
        position.setDescription(positionForm.getDescription());

        service.update(position);

        LOGGER.info(" Update Position (UI) request has been called. ");

        return "redirect:/ui/position/get/all";
    }
}
