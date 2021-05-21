package edu.bid.course.controller.ui;

import edu.bid.course.form.PersonnelForm;
import edu.bid.course.model.*;
import edu.bid.course.repository.PenaltySystemRepository;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.repository.PositionRepository;
import edu.bid.course.repository.ShiftRepository;
import edu.bid.course.service.personnel.PersonnelServiceImpl;
import edu.bid.course.service.position.PositionServiceImpl;
import edu.bid.course.service.shift.ShiftServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UI methods for Personnel model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelUIController: 1.0
 */

@RequestMapping("/ui/personnel")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Controller
public class PersonnelUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnelUIController.class);

    @Autowired
    PersonnelRepository personnelRepository;

    /**
     * Method to display all data from Personnel model and return it in .ftlh-file
     */

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Workers (UI) request has been called. ");

        List<Personnel> workers = personnelRepository.findAll();

        model.addAttribute("workers", workers);

        return "personnel/personnel-page";
    }

    /**
     * Method to delete data with specified id from Personnel model and return previous method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Worker (UI) by id with id " + id +  " request has been called. ");

        personnelRepository.deleteById(id);

        return "redirect:/ui/personnel/get/all";
    }

    @Autowired
    PersonnelServiceImpl service;

    @Autowired
    PositionServiceImpl positionService;

    @Autowired
    ShiftServiceImpl shiftService;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ShiftRepository shiftRepository;

    /**
     * Method to display form where all creation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){

        PersonnelForm personnelForm = new PersonnelForm();
        personnelForm.setLastName(" ");
        personnelForm.setFirstName(" ");
        personnelForm.setPatronymic(" ");
        personnelForm.setPosition(" ");
        personnelForm.setShift(" ");
        personnelForm.setBirthday(" ");
        personnelForm.setHiringDate(" ");
        personnelForm.setAddress(" ");
        personnelForm.setPhoneNumber(" ");
        personnelForm.setSalaryUAH(" ");
        personnelForm.setDescription(" ");

        model.addAttribute("personnelForm", personnelForm);

        List<String> positions = positionRepository.findAll()
                .stream()
                .map(Position::getName)
                .collect(Collectors.toList());

        model.addAttribute("positions", positions);

        List<String> shifts = shiftRepository.findAll()
                .stream()
                .map(Shift::getName)
                .collect(Collectors.toList());

        model.addAttribute("shifts", shifts);

        LOGGER.info(" Create Worker Form (UI) has been called. ");

        return "personnel/personnel-create";
    }

    /**
     * Method to create new data (based of data from previous method) with auto-generated id in Personnel model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("personnelForm") PersonnelForm personnelForm){


        Personnel personnel = new Personnel();

        personnel.setLastName(personnelForm.getLastName());
        personnel.setFirstName(personnelForm.getFirstName());
        personnel.setPatronymic(personnelForm.getPatronymic());

        String positionName = personnelForm.getPosition();
        Position position = positionService.getByName(personnelForm.getPosition());
        personnel.setPosition(position);

        String shiftName = personnelForm.getShift();
        Shift shift = shiftService.getByName(personnelForm.getShift());
        personnel.setShift(shift);

        personnel.setBirthday(LocalDate.parse(personnelForm.getBirthday()));
        personnel.setHiringDate(LocalDate.parse(personnelForm.getHiringDate()));
        personnel.setAddress(personnelForm.getAddress());
        personnel.setPhoneNumber(personnelForm.getPhoneNumber());

        personnel.setSalaryUAH(new BigDecimal(personnelForm.getSalaryUAH()));
        personnel.setDescription(personnelForm.getDescription());

        service.create(personnel);

        LOGGER.info(" Create Worker (UI) request has been called. A new Library Member has been created. ");

        return "redirect:/ui/personnel/get/all";
    }

    /**
     * Method to display form where all updation data will be stored
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String create(Model model, @PathVariable String id){

        PersonnelForm personnelForm = new PersonnelForm();
        personnelForm.setId(id);

        personnelForm.setLastName(personnelRepository.findById(id).get().getLastName());
        personnelForm.setFirstName(personnelRepository.findById(id).get().getFirstName());
        personnelForm.setPatronymic(personnelRepository.findById(id).get().getPatronymic());

        String position = personnelRepository.findById(id)
                .get().getPosition().getName();

        String shift = personnelRepository.findById(id)
                .get().getShift().getName();

        personnelForm.setPosition(position);
        personnelForm.setShift(shift);

        personnelForm.setBirthday(String.valueOf(personnelRepository.findById(id).get().getBirthday()));
        personnelForm.setHiringDate(String.valueOf(personnelRepository.findById(id).get().getHiringDate()));
        personnelForm.setAddress(personnelRepository.findById(id).get().getAddress());
        personnelForm.setPhoneNumber(personnelRepository.findById(id).get().getPhoneNumber());
        personnelForm.setSalaryUAH(String.valueOf(personnelRepository.findById(id).get().getSalaryUAH()));
        personnelForm.setDescription(personnelRepository.findById(id).get().getDescription());

        model.addAttribute("personnelUpdForm", personnelForm);

        List<String> positions = positionRepository.findAll()
                .stream()
                .map(Position::getName)
                .collect(Collectors.toList());

        model.addAttribute("positionsUpd", positions);

        List<String> shifts = shiftRepository.findAll()
                .stream()
                .map(Shift::getName)
                .collect(Collectors.toList());

        model.addAttribute("shiftsUpd", shifts);

        LOGGER.info(" Update Worker Form (UI) has been called. ");

        return "personnel/personnel-update";
    }

    /**
     * Method to update new data (based of data from previous method) in Personnel model and return get/all method
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("personnelUpdForm") PersonnelForm personnelForm, @PathVariable String id){


        Personnel personnel = new Personnel();
        personnel.setId(personnelForm.getId());

        personnel.setLastName(personnelForm.getLastName());
        personnel.setFirstName(personnelForm.getFirstName());
        personnel.setPatronymic(personnelForm.getPatronymic());

        String positionName = personnelForm.getPosition();
        Position position = positionService.getByName(personnelForm.getPosition());
        personnel.setPosition(position);

        String shiftName = personnelForm.getShift();
        Shift shift = shiftService.getByName(personnelForm.getShift());
        personnel.setShift(shift);

        personnel.setBirthday(LocalDate.parse(personnelForm.getBirthday()));
        personnel.setHiringDate(LocalDate.parse(personnelForm.getHiringDate()));
        personnel.setAddress(personnelForm.getAddress());
        personnel.setPhoneNumber(personnelForm.getPhoneNumber());

        personnel.setSalaryUAH(new BigDecimal(personnelForm.getSalaryUAH()));
        personnel.setDescription(personnelForm.getDescription());

        service.update(personnel);

        LOGGER.info(" Update Worker (UI) request has been called. ");

        return "redirect:/ui/personnel/get/all";
    }
}
