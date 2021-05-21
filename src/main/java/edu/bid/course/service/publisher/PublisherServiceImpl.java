package edu.bid.course.service.publisher;

import edu.bid.course.dao.position.PositionDAOImpl;
import edu.bid.course.dao.publisher.PublisherDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.Position;
import edu.bid.course.model.Publisher;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.PublisherRepository;
import edu.bid.course.service.position.PositionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for Publisher model DAO methods
 * course.PublisherServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PublisherServiceImpl: 1.0
 */


@Repository
public class PublisherServiceImpl implements IPublisherService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Autowired
    PublisherDAOImpl dao;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Publisher create(Publisher publisher) {

        LOGGER.info(" Create new Publisher (Service) request has been called. ");

        return dao.create(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {

        LOGGER.info(" Update existing Publisher (Service) request has been called. ");

        return dao.update(publisher);
    }

    public Publisher getByName(String name) {

        return publisherRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortPublishersByNumberOBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = bookRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getPublisherName().getName(),
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
