package edu.bid.course.controller.ui;

import edu.bid.course.form.RegisterForm;
import edu.bid.course.form.RentingForm;
import edu.bid.course.model.*;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.repository.RegisterRepository;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.book.BookServiceImpl;
import edu.bid.course.service.personnel.PersonnelServiceImpl;
import edu.bid.course.service.register.RegisterServiceImpl;
import edu.bid.course.service.renting.RentingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * course.RentingUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingUIController: 1.0
 */

@RequestMapping("/ui/renting")
@Controller
public class RentingUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentingUIController.class);

    @Autowired
    RentingRepository rentingRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Rented Books (UI) request has been called. ");

        List<Renting> renteds = rentingRepository.findAll();

        model.addAttribute("renteds", renteds);

        return "renting/renting-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Rented Book (UI) by id with id " + id +  " request has been called. ");

        rentingRepository.deleteById(id);

        return "redirect:/ui/renting/get/all";
    }

    @Autowired
    RentingServiceImpl service;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    RegisterServiceImpl registerService;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    PersonnelServiceImpl personnelService;

    @GetMapping("/create")
    public String create(Model model){

        RentingForm rentingForm = new RentingForm();

        rentingForm.setBook(" ");
        rentingForm.setReader(" ");
        rentingForm.setRentingDate(" ");
        rentingForm.setExpectedReturnDate(" ");
        rentingForm.setActualReturnDate(" ");
        rentingForm.setPenaltySum(" ");
        rentingForm.setRentingPerson(" ");
        rentingForm.setDescription(" ");


        model.addAttribute("rentingForm", rentingForm);

        List<String> books = bookRepository.findAll()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());

        model.addAttribute("books", books);

        List<String> members = registerRepository.findAll()
                .stream()
                .map(Register::getLastName)
                .collect(Collectors.toList());

        model.addAttribute("members", members);

        List<String> workers = personnelRepository.findAll()
                .stream()
                .map(Personnel::getLastName)
                .collect(Collectors.toList());

        model.addAttribute("workers", workers);

        LOGGER.info(" Create Rented Book Form (UI) has been called. ");

        return "renting/renting-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("rentingForm") RentingForm rentingForm){


        Renting renting = new Renting();

        String bookName = rentingForm.getBook();
        Book book = bookService.getByName(rentingForm.getBook());
        renting.setBook(book);

        String readerName = rentingForm.getReader();
        Register register = registerService.getByName(rentingForm.getReader());
        renting.setReader(register);

        renting.setRentingDate(LocalDate.parse(rentingForm.getRentingDate()));
        renting.setExpectedReturnDate(LocalDate.parse(rentingForm.getExpectedReturnDate()));
        renting.setActualReturnDate(LocalDate.parse(rentingForm.getActualReturnDate()));
        renting.setPenaltySum(new BigDecimal(rentingForm.getPenaltySum()));

        String workerName = rentingForm.getRentingPerson();
        Personnel personnel = personnelService.getByName(rentingForm.getRentingPerson());
        renting.setRentingPerson(personnel);

        renting.setDescription(rentingForm.getDescription());

        service.create(renting);

        LOGGER.info(" Create RentedBook (UI) request has been called. A new Library Member has been created. ");

        return "redirect:/ui/renting/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        RentingForm rentingForm = new RentingForm();

        rentingForm.setId(id);

        String book = rentingRepository.findById(id)
                .stream()
                .map(r -> r.getBook().getName())
                .collect(Collectors.joining());

        String reader = rentingRepository.findById(id)
                .stream()
                .map(r -> r.getReader().getLastName())
                .collect(Collectors.joining());

        rentingForm.setBook(book);
        rentingForm.setReader(reader);

        rentingForm.setRentingDate(String.valueOf(rentingRepository.findById(id).get().getRentingDate()));
        rentingForm.setExpectedReturnDate(String.valueOf(rentingRepository.findById(id).get().getExpectedReturnDate()));
        rentingForm.setActualReturnDate(String.valueOf(rentingRepository.findById(id).get().getActualReturnDate()));
        rentingForm.setPenaltySum(String.valueOf(rentingRepository.findById(id).get().getPenaltySum()));

        String person = rentingRepository.findById(id)
                .stream()
                .map(r -> r.getRentingPerson().getLastName())
                .collect(Collectors.joining());

        rentingForm.setRentingPerson(person);

        rentingForm.setDescription(rentingRepository.findById(id).get().getDescription());


        model.addAttribute("rentingUpdForm", rentingForm);

        List<String> books = bookRepository.findAll()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());

        model.addAttribute("booksUpd", books);

        List<String> members = registerRepository.findAll()
                .stream()
                .map(Register::getLastName)
                .collect(Collectors.toList());

        model.addAttribute("membersUpd", members);

        List<String> workers = personnelRepository.findAll()
                .stream()
                .map(Personnel::getLastName)
                .collect(Collectors.toList());

        model.addAttribute("workersUpd", workers);

        LOGGER.info(" Update Rented Book Form (UI) has been called. ");

        return "renting/renting-update";
    }

    @PostMapping("/update/{id}")
    public String create(Model model, @ModelAttribute("rentingUpdForm") RentingForm rentingForm, @PathVariable String id){


        Renting renting = new Renting();

        renting.setId(rentingForm.getId());

        String bookName = rentingForm.getBook();
        Book book = bookService.getByName(rentingForm.getBook());
        renting.setBook(book);

        String readerName = rentingForm.getReader();
        Register register = registerService.getByName(rentingForm.getReader());
        renting.setReader(register);

        renting.setRentingDate(LocalDate.parse(rentingForm.getRentingDate()));
        renting.setExpectedReturnDate(LocalDate.parse(rentingForm.getExpectedReturnDate()));
        renting.setActualReturnDate(LocalDate.parse(rentingForm.getActualReturnDate()));
        renting.setPenaltySum(new BigDecimal(rentingForm.getPenaltySum()));

        String workerName = rentingForm.getRentingPerson();
        Personnel personnel = personnelService.getByName(rentingForm.getRentingPerson());
        renting.setRentingPerson(personnel);

        renting.setDescription(rentingForm.getDescription());

        service.update(renting);

        LOGGER.info(" Update RentedBook (UI) request has been called. ");

        return "redirect:/ui/renting/get/all";
    }
}
