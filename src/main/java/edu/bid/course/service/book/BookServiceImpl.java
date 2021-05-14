package edu.bid.course.service.book;

import edu.bid.course.dao.author.AuthorDAOImpl;
import edu.bid.course.dao.book.BookDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.Genre;
import edu.bid.course.model.Renting;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * course.BookServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookServiceImpl: 1.0
 */


@Repository
public class BookServiceImpl implements IBookService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookDAOImpl dao;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentingRepository rentingRepository;

    @Override
    public Book create(Book book) {

        LOGGER.info(" Create new Book (Service) request has been called. ");

        return dao.create(book);
    }

    @Override
    public Book update(Book book) {

        LOGGER.info(" Update existing Book (Service) request has been called. ");

        return dao.update(book);
    }

    public Book getByName(String name) {

        return bookRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortBooksByNumberORentedOnes(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = rentingRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getBook().getName(),
                                Collectors.summingInt(Renting::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all books based on Rented Books (Service) query has been executed. ");

        return sorted;
    }
}
