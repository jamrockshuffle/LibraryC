package edu.bid.course.service.position;

import edu.bid.course.dao.personnel.PersonnelDAOImpl;
import edu.bid.course.dao.position.PositionDAOImpl;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Personnel;
import edu.bid.course.model.Position;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.repository.PositionRepository;
import edu.bid.course.service.personnel.PersonnelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Service Implementation for Position model DAO methods
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PositionServiceImpl: 1.0
 */

@Repository
public class PositionServiceImpl implements IPositionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    PositionDAOImpl dao;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Override
    public Position create(Position position) {

        LOGGER.info(" Create new Position (Service) request has been called. ");

        return dao.create(position);
    }

    @Override
    public Position update(Position position) {

        LOGGER.info(" Update existing Position (Service) request has been called. ");

        return dao.update(position);
    }

    public Position getByName(String name) {

        return positionRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortPositionsByNumberOfWorkers(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getPosition().getName(),
                                Collectors.summingInt(Personnel::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all shifts based on Library Workers (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> sortPositionsByAverageSalary(){

        Collector<BigDecimal, BigDecimal[], BigDecimal> decimalToDouble = Collector.of
                (
                () -> new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO},
                (pair, val) -> {
                    pair[0] = pair[0].add(val);
                    pair[1] = pair[1].add(BigDecimal.ONE);
                    },
                (pair1, pair2) -> new BigDecimal[]{pair1[0].add(pair2[0]), pair1[1].add(pair2[1])},
                (pair) -> pair[0].divide(pair[1], 2, RoundingMode.HALF_UP)
        );

        Map<String, BigDecimal> sorted = new LinkedHashMap<>();

        Map<String, BigDecimal> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getPosition().getName(),
                                Collectors.mapping(Personnel::getSalaryUAH, decimalToDouble)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Find and sort average salary of a position based on Library Workers (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> sortPositionsByAllSalary(){


        Map<String, BigDecimal> sorted = new LinkedHashMap<>();

        Map<String, BigDecimal> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getPosition().getName(),
                                Collectors.reducing(BigDecimal.ZERO, Personnel::getSalaryUAH, BigDecimal::add)

                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all salary of a position based on Lbrary Workers (Service) query has been executed. ");

        return sorted;
    }
}
