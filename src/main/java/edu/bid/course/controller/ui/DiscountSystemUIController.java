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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UI methods for DiscountSystem model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemUIController: 1.0
 */

@RequestMapping("/ui/discountsystem")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class DiscountSystemUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSystemUIController.class);

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    /**
     * Method to display all data from DiscountSystem model and return it in .ftlh-file
     */

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Discount Categories (UI) request has been called. ");

        List<DiscountSystem> categories = discountSystemRepository.findAll();

        model.addAttribute("categories", categories);

        return "discountsystem/discountsystem-page";
    }

    /**
     * Method to delete data with specified id from DiscountSystem model and return previous method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Discount Category (UI) by id with id " + id +  " request has been called. ");

        discountSystemRepository.deleteById(id);

        return "redirect:/ui/discountsystem/get/all";
    }

    @Autowired
    DiscountSystemServiceImpl service;

    /**
     * Method to display form where all creation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
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

    /**
     * Method to create new data (based of data from previous method) with auto-generated id in DiscountSystem model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
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

    /**
     * Method to display form where all updation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
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

    /**
     * Method to update new data (based of data from previous method) in DiscountSystem model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
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
