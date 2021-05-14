package edu.bid.course.service.renting;

import edu.bid.course.dao.author.AuthorDAOImpl;
import edu.bid.course.dao.renting.RentingDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Register;
import edu.bid.course.model.Renting;
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
 * course.RentingServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingServiceImpl: 1.0
 */


@Repository
public class RentingServiceImpl implements IRentingService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RentingServiceImpl.class);

    @Autowired
    RentingDAOImpl dao;

    @Autowired
    RentingRepository rentingRepository;

    @Override
    public Renting create(Renting renting) {

        LOGGER.info(" Create new Rented Book (Service) request has been called. ");

        return dao.create(renting);
    }

    @Override
    public Renting update(Renting renting) {

        LOGGER.info(" Update existing Rented Book (Service) request has been called. ");

        return dao.update(renting);
    }

    public Renting getByName(String name) {

        return rentingRepository.findAll().stream()
                .filter(item -> item.getBook().getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortAuthorsByRentedBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = rentingRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getBook().getAuthorName().getName(),
                                Collectors.summingInt(Renting::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all authors based on Rented Books (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, Integer> sortGenresByRentedBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = rentingRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getBook().getGenreName().getName(),
                                Collectors.summingInt(Renting::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all genres based on Rented Books (Service) query has been executed. ");

        return sorted;
    }
}
