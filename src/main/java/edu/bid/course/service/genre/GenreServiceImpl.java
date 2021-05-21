package edu.bid.course.service.genre;

import edu.bid.course.dao.genre.GenreDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.Genre;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for Genre model DAO methods
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreServiceImpl: 1.0
 */


@Repository
public class GenreServiceImpl implements IGenreService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    GenreDAOImpl dao;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Genre create(Genre genre) {

        LOGGER.info(" Create new Genre (Service) request has been called. ");

        return dao.create(genre);
    }

    @Override
    public Genre update(Genre genre) {

        LOGGER.info(" Update existing Genre (Service) request has been called. ");

        return dao.update(genre);
    }

    public Genre getByName(String name) {

        return genreRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortGenresByNumberOBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = bookRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getGenreName().getName(),
                                Collectors.summingInt(Book::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all genres based on Books (Service) query has been executed. ");

        return sorted;
    }
}
