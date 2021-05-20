package edu.bid.course.controller.rest;

import edu.bid.course.model.Book;
import edu.bid.course.model.Position;
import edu.bid.course.model.Publisher;
import edu.bid.course.repository.PublisherRepository;
import edu.bid.course.service.position.PositionServiceImpl;
import edu.bid.course.service.publisher.PublisherServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "PublisherControllerAPI", description = "Mostly GET methods with Publisher collection")
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("api/publisher")
public class PublisherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    PublisherRepository publisherRepository;

    @Operation(summary = " Get all Publishers",
            description = " Finds and displays all Publishers")
    @RequestMapping("/get/all")
    public List<Publisher> getPublishers (){

        LOGGER.info(" Get all Publishers (API) request has been called. ");

        return publisherRepository.findAll();

    }

    @Operation(summary = " Get one Publisher",
            description = " Finds and displays Publisher with specified id")
    @GetMapping("/get/{id}")
    public Publisher getById(@PathVariable("id")
                        @Parameter(name = "id",
                                description = " This is the first field in the list")
                                String id) {

        LOGGER.info(" Get Publisher by id (API) with id " + id +  " request has been called. ");

        return publisherRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Publisher deletion",
            description = " Deletes Publisher with specified id")
    @GetMapping("/delete/{id}")
    public List<Publisher> deleteById(@PathVariable("id")
                                 @Parameter(name = "id",
                                         description = " This is the first field in the list")
                                         String id) {

        LOGGER.info(" Delete Publisher by id (API) with id " + id +  " request has been called. ");

        publisherRepository.deleteById(id);

        return publisherRepository.findAll();
    }

    @Autowired
    PublisherServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Publisher creation",
            description = " Adds new Publisher to the Publisher list. Id to be created is UUID type ")
    @PostMapping("/create")
    public Publisher create(@RequestBody Publisher publisher) {

        LOGGER.info(" Create new Position (API) request has been called. ");

        return service.create(publisher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = " Publisher updation",
            description = " Updates Publisher with specified id")
    @PostMapping("/update")
    public Publisher update(@RequestBody Publisher publisher) {

        LOGGER.info(" Update existing Publisher (API) request has been called. ");

        return service.update(publisher);
    }

    @Operation(summary = " Count all publishers based on books",
            description = " Finds and displays counted and sorted publishers")
    @RequestMapping("/get/sorted")
    public Map<String, Integer> sortPublishersByNumberOBooks(){

        LOGGER.info(" Count and sort all publishers based on Books (API) query has been executed. ");

        return service.sortPublishersByNumberOBooks();
    }
}
