package edu.bid.course.controller.ui;

import edu.bid.course.form.RegisterForm;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Publisher;
import edu.bid.course.model.Register;
import edu.bid.course.repository.DiscountSystemRepository;
import edu.bid.course.repository.RegisterRepository;
import edu.bid.course.service.discountsystem.DiscountSystemServiceImpl;
import edu.bid.course.service.register.RegisterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * course.RegisterUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterUIController: 1.0
 */


@RequestMapping("/ui/register")
@Controller
public class RegisterUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUIController.class);

    @Autowired
    RegisterRepository registerRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Library Members (UI) request has been called. ");

        List<Register> members = registerRepository.findAll();

        model.addAttribute("members", members);

        return "register/register-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Library Member (UI) by id with id " + id +  " request has been called. ");

        registerRepository.deleteById(id);

        return "redirect:/ui/register/get/all";
    }

    @Autowired
    RegisterServiceImpl service;

    @Autowired
    DiscountSystemServiceImpl discountSystemService;

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    @GetMapping("/create")
    public String create(Model model){

        RegisterForm registerForm = new RegisterForm();
        registerForm.setLastName(" ");
        registerForm.setFirstName(" ");
        registerForm.setPatronymic(" ");

        registerForm.setAddress(" ");
        registerForm.setBirthday(" ");
        registerForm.setDescription(" ");
        registerForm.setPhoneNumber(" ");
        registerForm.setCategory(" ");

        model.addAttribute("registerForm", registerForm);

        List<String> categories = discountSystemRepository.findAll()
                .stream()
                .map(DiscountSystem::getCategory)
                .collect(Collectors.toList());

        model.addAttribute("categories", categories);

        LOGGER.info(" Create Library Member Form (UI) has been called. ");

        return "register/register-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("registerForm") RegisterForm registerForm){


        Register register = new Register();

        register.setLastName(registerForm.getLastName());
        register.setFirstName(registerForm.getFirstName());
        register.setPatronymic(registerForm.getPatronymic());

        register.setBirthday(LocalDate.parse(registerForm.getBirthday()));
        register.setAddress(registerForm.getAddress());
        register.setPhoneNumber(registerForm.getPhoneNumber());


        String categoryName = registerForm.getCategory();
        DiscountSystem discountSystem = discountSystemService.getByCategory(registerForm.getCategory());
        register.setCategory(discountSystem);

        service.create(register);

        LOGGER.info(" Create Library Member (UI) request has been called. A new Library Member has been created. ");

        return "redirect:/ui/register/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        RegisterForm registerForm = new RegisterForm();
        registerForm.setId(id);
        registerForm.setLastName(registerRepository.findById(id).get().getLastName());
        registerForm.setFirstName(registerRepository.findById(id).get().getFirstName());
        registerForm.setPatronymic(registerRepository.findById(id).get().getPatronymic());

        registerForm.setAddress(registerRepository.findById(id).get().getAddress());
        registerForm.setBirthday(String.valueOf(registerRepository.findById(id).get().getBirthday()));
        registerForm.setDescription(registerRepository.findById(id).get().getDescription());
        registerForm.setPhoneNumber(registerRepository.findById(id).get().getPhoneNumber());

        String category = registerRepository.findById(id)
                .stream()
                .map(r -> r.getCategory().getCategory())
                .collect(Collectors.joining());

        registerForm.setCategory(category);

        model.addAttribute("registerUpdForm", registerForm);

        List<String> categories = discountSystemRepository.findAll()
                .stream()
                .map(DiscountSystem::getCategory)
                .collect(Collectors.toList());

        model.addAttribute("categoriesUpd", categories);

        LOGGER.info(" Update Library Member Form (UI) has been called. ");

        return "register/register-update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("registerUpdForm") RegisterForm registerForm, @PathVariable String id){


        Register register = new Register();

        register.setId(registerForm.getId());

        register.setLastName(registerForm.getLastName());
        register.setFirstName(registerForm.getFirstName());
        register.setPatronymic(registerForm.getPatronymic());

        register.setBirthday(LocalDate.parse(registerForm.getBirthday()));
        register.setAddress(registerForm.getAddress());
        register.setPhoneNumber(registerForm.getPhoneNumber());


        String categoryName = registerForm.getCategory();
        DiscountSystem discountSystem = discountSystemService.getByCategory(registerForm.getCategory());
        register.setCategory(discountSystem);

        service.update(register);

        LOGGER.info(" Update Library Member (UI) request has been called. ");

        return "redirect:/ui/register/get/all";
    }
}
