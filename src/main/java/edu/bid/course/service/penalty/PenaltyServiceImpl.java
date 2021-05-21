package edu.bid.course.service.penalty;

import edu.bid.course.dao.genre.GenreDAOImpl;
import edu.bid.course.dao.penalty.PenaltyDAOImpl;
import edu.bid.course.model.Genre;
import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.model.Register;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.service.genre.GenreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for Penalty model DAO methods
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltyServiceImpl: 1.0
 */


@Repository
public class PenaltyServiceImpl implements IPenaltyService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltyServiceImpl.class);

    @Autowired
    PenaltyDAOImpl dao;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Override
    public Penalty create(Penalty penalty) {

        LOGGER.info(" Create new Penalty (Service) request has been called. ");

        return dao.create(penalty);
    }

    @Override
    public Penalty update(Penalty penalty) {

        LOGGER.info(" Update existing Penalty (Service) request has been called. ");

        return dao.update(penalty);
    }

    public Map<String, Integer> sortRentedBooksByNumberOfDamages(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = penaltyRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getRentedBook().getBook().getName(),
                                Collectors.summingInt(r -> r.getPenaltyClause().getIntegerDescription())
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all rented books based on registered Damages query has been executed. ");

        return sorted;
    }
}
