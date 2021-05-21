package edu.bid.course.service.stillage;

import edu.bid.course.dao.shift.ShiftDAOImpl;
import edu.bid.course.dao.stillage.StillageDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.model.Shift;
import edu.bid.course.model.Stillage;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.StillageRepository;
import edu.bid.course.service.shift.ShiftServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for Stillage model DAO methods
 * course.StillageServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageServiceImpl: 1.0
 */


@Repository
public class StillageServiceImpl implements IStillageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(StillageServiceImpl.class);

    @Autowired
    StillageDAOImpl dao;

    @Autowired
    StillageRepository stillageRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Stillage create(Stillage stillage) {

        LOGGER.info(" Create new Stillage (Service) request has been called. ");

        return dao.create(stillage);
    }

    @Override
    public Stillage update(Stillage stillage) {

        LOGGER.info(" Update existing Stillage (Service) request has been called. ");

        return dao.update(stillage);
    }

    public Stillage getByID(String name) {

        return stillageRepository.findAll().stream()
                .filter(item -> item.getStillageId()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortStillagesByNumberOBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = bookRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getStillageID().getStillageId(),
                                Collectors.summingInt(Book::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all stillages based on Books (Service) query has been executed. ");

        return sorted;
    }
}
