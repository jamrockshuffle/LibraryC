package edu.bid.course.service.author;

import edu.bid.course.dao.author.AuthorDAOImpl;
import edu.bid.course.dao.genre.GenreDAOImpl;
import edu.bid.course.model.*;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.service.genre.GenreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * course.AuthorServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorServiceImpl: 1.0
 */


@Repository
public class AuthorServiceImpl implements IAuthorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    AuthorDAOImpl dao;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Author create(Author author) {

        LOGGER.info(" Create new Author (Service) request has been called. ");

        return dao.create(author);
    }

    @Override
    public Author update(Author author) {

        LOGGER.info(" Update existing Author (Service) request has been called. ");

        return dao.update(author);
    }

    public Author getByName(String name) {

        return authorRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortBooksByNumberOAuthors(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = bookRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getAuthorName().getName(),
                                Collectors.summingInt(Book::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all authors based on Books (Service) query has been executed. ");

        return sorted;
    }
}
