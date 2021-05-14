package edu.bid.course.controller.ui;

import edu.bid.course.form.BookForm;
import edu.bid.course.form.RegisterForm;
import edu.bid.course.model.*;
import edu.bid.course.repository.*;
import edu.bid.course.service.author.AuthorServiceImpl;
import edu.bid.course.service.book.BookServiceImpl;
import edu.bid.course.service.genre.GenreServiceImpl;
import edu.bid.course.service.publisher.PublisherServiceImpl;
import edu.bid.course.service.stillage.StillageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * course.BookUIController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookUIController: 1.0
 */

@RequestMapping("/ui/book")
@Controller
public class BookUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookUIController.class);

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/get/all")
    public String showAll(Model model){

        LOGGER.info(" Get all Books (UI) request has been called. ");

        List<Book> books = bookRepository.findAll();

        model.addAttribute("books", books);

        return "book/book-page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){

        LOGGER.info(" Delete Book (UI) by id with id " + id +  " request has been called. ");

        bookRepository.deleteById(id);

        return "redirect:/ui/book/get/all";
    }

    @Autowired
    BookServiceImpl service;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    GenreServiceImpl genreService;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    PublisherServiceImpl publisherService;

    @Autowired
    StillageRepository stillageRepository;

    @Autowired
    StillageServiceImpl stillageService;

    @GetMapping("/create")
    public String create(Model model){

        BookForm bookForm = new BookForm();
        bookForm.setName(" ");
        bookForm.setAuthorName(" ");
        bookForm.setGenreName(" ");
        bookForm.setYearOfPublishing(" ");
        bookForm.setPublisherName(" ");
        bookForm.setStillageID(" ");
        bookForm.setCollateralCost(" ");
        bookForm.setCostPerDay(" ");
        bookForm.setDescription(" ");

        model.addAttribute("bookForm", bookForm);

        List<String> authors = authorRepository.findAll()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());

        model.addAttribute("authors", authors);

        List<String> genres = genreRepository.findAll()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toList());

        model.addAttribute("genres", genres);

        List<String> publishers = publisherRepository.findAll()
                .stream()
                .map(Publisher::getName)
                .collect(Collectors.toList());

        model.addAttribute("publishers", publishers);

        List<String> stillages = stillageRepository.findAll()
                .stream()
                .map(Stillage::getStillageId)
                .collect(Collectors.toList());

        model.addAttribute("stillages", stillages);

        LOGGER.info(" Create Book Form (UI) has been called. ");

        return "book/book-create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("bookForm") BookForm bookForm){


        Book book = new Book();

        book.setName(bookForm.getName());

        String authorName = bookForm.getAuthorName();
        Author author = authorService.getByName(bookForm.getAuthorName());
        book.setAuthorName(author);

        String genreName = bookForm.getGenreName();
        Genre genre = genreService.getByName(bookForm.getGenreName());
        book.setGenreName(genre);

        book.setYearOfPublishing(Integer.parseInt(bookForm.getYearOfPublishing()));

        String publisherName = bookForm.getPublisherName();
        Publisher publisher = publisherService.getByName(bookForm.getPublisherName());
        book.setPublisherName(publisher);

        String stillageID = bookForm.getStillageID();
        Stillage stillage = stillageService.getByID(bookForm.getStillageID());
        book.setStillageID(stillage);

        book.setCollateralCost(new BigDecimal(bookForm.getCollateralCost()));
        book.setCostPerDay(new BigDecimal(bookForm.getCostPerDay()));

        book.setDescription(bookForm.getDescription());

        service.create(book);

        LOGGER.info(" Create Book (UI) request has been called. A new Library Member has been created. ");

        return "redirect:/ui/book/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id){

        BookForm bookForm = new BookForm();
        bookForm.setId(id);
        bookForm.setName(bookRepository.findById(id).get().getName());

        String author = bookRepository.findById(id)
                .stream()
                .map(r -> r.getAuthorName().getName())
                .collect(Collectors.joining());

        String genre = bookRepository.findById(id)
                .stream()
                .map(r -> r.getGenreName().getName())
                .collect(Collectors.joining());

        bookForm.setAuthorName(author);
        bookForm.setGenreName(genre);

        bookForm.setYearOfPublishing(String.valueOf(bookRepository.findById(id).get().getYearOfPublishing()));

        String publisher = bookRepository.findById(id)
                .stream()
                .map(r -> r.getPublisherName().getName())
                .collect(Collectors.joining());

        String stillage = bookRepository.findById(id)
                .stream()
                .map(r -> r.getStillageID().getStillageId())
                .collect(Collectors.joining());

        bookForm.setPublisherName(publisher);
        bookForm.setStillageID(stillage);

        bookForm.setCollateralCost(String.valueOf(bookRepository.findById(id).get().getCollateralCost()));
        bookForm.setCostPerDay(String.valueOf(bookRepository.findById(id).get().getCostPerDay()));
        bookForm.setDescription(bookRepository.findById(id).get().getDescription());

        model.addAttribute("bookUpdForm", bookForm);

        List<String> authors = authorRepository.findAll()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());

        model.addAttribute("authorsUpd", authors);

        List<String> genres = genreRepository.findAll()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toList());

        model.addAttribute("genresUpd", genres);

        List<String> publishers = publisherRepository.findAll()
                .stream()
                .map(Publisher::getName)
                .collect(Collectors.toList());

        model.addAttribute("publishersUpd", publishers);

        List<String> stillages = stillageRepository.findAll()
                .stream()
                .map(Stillage::getStillageId)
                .collect(Collectors.toList());

        model.addAttribute("stillagesUpd", stillages);

        LOGGER.info(" Update Book Form (UI) has been called. ");

        return "book/book-update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("bookUpdForm") BookForm bookForm, @PathVariable String id){


        Book book = new Book();

        book.setId(bookForm.getId());
        book.setName(bookForm.getName());

        String authorName = bookForm.getAuthorName();
        Author author = authorService.getByName(bookForm.getAuthorName());
        book.setAuthorName(author);

        String genreName = bookForm.getGenreName();
        Genre genre = genreService.getByName(bookForm.getGenreName());
        book.setGenreName(genre);

        book.setYearOfPublishing(Integer.parseInt(bookForm.getYearOfPublishing()));

        String publisherName = bookForm.getPublisherName();
        Publisher publisher = publisherService.getByName(bookForm.getPublisherName());
        book.setPublisherName(publisher);

        String stillageID = bookForm.getStillageID();
        Stillage stillage = stillageService.getByID(bookForm.getStillageID());
        book.setStillageID(stillage);

        book.setCollateralCost(new BigDecimal(bookForm.getCollateralCost()));
        book.setCostPerDay(new BigDecimal(bookForm.getCostPerDay()));

        book.setDescription(bookForm.getDescription());

        service.update(book);

        LOGGER.info(" Update Book (UI) request has been called. ");

        return "redirect:/ui/book/get/all";
    }

}
