package edu.bid.course.controller.ui;

import edu.bid.course.form.PositionForm;
import edu.bid.course.form.ShiftForm;
import edu.bid.course.model.Author;
import edu.bid.course.model.Position;
import edu.bid.course.model.Shift;
import edu.bid.course.repository.ShiftRepository;
import edu.bid.course.service.position.PositionServiceImpl;
import edu.bid.course.service.shift.ShiftServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.ShiftUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftUIController: 1.0
 */

@RequestMapping("/ui/shift")
@Controller
public class ShiftUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftUIController.class);

    @Autowired
    ShiftRepository shiftRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Shifts (UI) request has been called. ");

        List<Shift> shifts = shiftRepository.findAll();

        model.addAttribute("shifts", shifts);

        return "shift/shift-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Shift (UI) by id with id " + id +  " request has been called. ");

        shiftRepository.deleteById(id);

        return "redirect:/ui/shift/get/all";
    }

    @Autowired
    ShiftServiceImpl service;

    @GetMapping("/create")
    public String create(Model model){

        ShiftForm shiftForm = new ShiftForm();
        shiftForm.setName(" ");
        shiftForm.setDescription(" ");
        model.addAttribute("shiftForm", shiftForm);

        LOGGER.info(" Create Shift Form (UI) has been called. ");

        return "shift/shift-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("shiftForm") ShiftForm shiftForm){

        Shift shift = new Shift();
        shift.setName(shiftForm.getName());
        shift.setDescription(shiftForm.getDescription());

        service.create(shift);

        LOGGER.info(" Create Shift (UI) request has been called. A new Shift has been created. ");

        return "redirect:/ui/shift/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        ShiftForm shiftForm = new ShiftForm();
        shiftForm.setId(id);
        shiftForm.setName(shiftRepository.findById(id).get().getName());
        shiftForm.setDescription(shiftRepository.findById(id).get().getDescription());
        model.addAttribute("shiftUpdForm", shiftForm);

        LOGGER.info(" Update Shift Form (UI) has been called. ");

        return "shift/shift-update";
    }

    @PostMapping("/update/{id}")
    public String create(Model model, @ModelAttribute("shiftUpdForm") ShiftForm shiftForm, @PathVariable String id){

        Shift shift = new Shift();
        shift.setId(shiftForm.getId());
        shift.setName(shiftForm.getName());
        shift.setDescription(shiftForm.getDescription());

        service.update(shift);

        LOGGER.info(" Update Shift (UI) request has been called. ");

        return "redirect:/ui/shift/get/all";
    }
}
