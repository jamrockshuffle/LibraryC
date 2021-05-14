package edu.bid.course.controller.ui;

import edu.bid.course.form.DiscountSystemForm;
import edu.bid.course.form.PositionForm;
import edu.bid.course.model.Book;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Position;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.DiscountSystemRepository;
import edu.bid.course.service.discountsystem.DiscountSystemServiceImpl;
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
 * course.DiscountSystemUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemUIController: 1.0
 */

@RequestMapping("/ui/discountsystem")
@Controller
public class DiscountSystemUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSystemUIController.class);

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Discount Categories (UI) request has been called. ");

        List<DiscountSystem> categories = discountSystemRepository.findAll();

        model.addAttribute("categories", categories);

        return "discountsystem/discountsystem-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Discount Category (UI) by id with id " + id +  " request has been called. ");

        discountSystemRepository.deleteById(id);

        return "redirect:/ui/discountsystem/get/all";
    }

    @Autowired
    DiscountSystemServiceImpl service;

    @GetMapping("/create")
    public String create(Model model){

        DiscountSystemForm discountSystemForm = new DiscountSystemForm();
        discountSystemForm.setCategory(" ");
        discountSystemForm.setDiscountPercentage(" ");
        discountSystemForm.setDescription(" ");
        model.addAttribute("discountSystemForm", discountSystemForm);

        LOGGER.info(" Create Discount Category Form (UI) has been called. ");

        return "discountsystem/discountsystem-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("discountSystemForm") DiscountSystemForm discountSystemForm){

        DiscountSystem discountSystem = new DiscountSystem();
        discountSystem.setCategory(discountSystemForm.getCategory());
        discountSystem.setDiscountPercentage(discountSystemForm.getDiscountPercentage());
        discountSystem.setDescription(discountSystemForm.getDescription());

        service.create(discountSystem);

        LOGGER.info(" Create Discount Category (UI) request has been called. A new Discount Category has been created. ");

        return "redirect:/ui/discountsystem/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        DiscountSystemForm discountSystemForm = new DiscountSystemForm();

        discountSystemForm.setId(id);
        discountSystemForm.setCategory(discountSystemRepository.findById(id).get().getCategory());
        discountSystemForm.setDiscountPercentage(discountSystemRepository.findById(id).get().getDiscountPercentage());
        discountSystemForm.setDescription(discountSystemRepository.findById(id).get().getDescription());
        model.addAttribute("discountSystemUpdForm", discountSystemForm);

        LOGGER.info(" Update Discount Category Form (UI) has been called. ");

        return "discountsystem/discountsystem-update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("discountSystemUpdForm") DiscountSystemForm discountSystemForm, @PathVariable String id){

        DiscountSystem discountSystem = new DiscountSystem();

        discountSystem.setId(discountSystemForm.getId());
        discountSystem.setCategory(discountSystemForm.getCategory());
        discountSystem.setDiscountPercentage(discountSystemForm.getDiscountPercentage());
        discountSystem.setDescription(discountSystemForm.getDescription());

        service.update(discountSystem);

        LOGGER.info(" Update Discount Category (UI) request has been called. ");

        return "redirect:/ui/discountsystem/get/all";
    }
}
