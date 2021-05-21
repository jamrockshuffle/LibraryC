package edu.bid.course.service.penaltysystem;

import edu.bid.course.dao.penalty.PenaltyDAOImpl;
import edu.bid.course.dao.penaltysystem.PenaltySystemDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.repository.PenaltySystemRepository;
import edu.bid.course.service.penalty.PenaltyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for PenaltySystem model DAO methods
 * course.PenaltySystemServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemServiceImpl: 1.0
 */

@Repository
public class PenaltySystemServiceImpl implements IPenaltySystemService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltySystemServiceImpl.class);

    @Autowired
    PenaltySystemDAOImpl dao;

    @Autowired
    PenaltySystemRepository penaltySystemRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Override
    public PenaltySystem create(PenaltySystem penaltySystem) {

        LOGGER.info(" Create new Damage (Service) request has been called. ");

        return dao.create(penaltySystem);
    }

    @Override
    public PenaltySystem update(PenaltySystem penaltySystem) {

        LOGGER.info(" Update existing Damage (Service) request has been called. ");

        return dao.update(penaltySystem);
    }

    public PenaltySystem getByName(String name) {

        return penaltySystemRepository.findAll().stream()
                .filter(item -> item.getDamageDescription()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortDamagesByNumberOfRentedBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = penaltyRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getPenaltyClause().getDamageDescription(),
                                Collectors.summingInt(Penalty::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all Damages based on registered Rented Books query has been executed. ");

        return sorted;
    }
}
