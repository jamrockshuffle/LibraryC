package edu.bid.course.service.personnel;

import edu.bid.course.dao.penaltysystem.PenaltySystemDAOImpl;
import edu.bid.course.dao.personnel.PersonnelDAOImpl;
import edu.bid.course.model.*;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.penaltysystem.PenaltySystemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Service Implementation for Personnel model DAO methods
 * course.PersonnelServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelServiceImpl: 1.0
 */

@Repository
public class PersonnelServiceImpl implements IPersonnelService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnelServiceImpl.class);

    @Autowired
    PersonnelDAOImpl dao;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    RentingRepository rentingRepository;

    @Override
    public Personnel create(Personnel personnel) {

        LOGGER.info(" Create new Worker (Service) request has been called. ");

        return dao.create(personnel);
    }

    @Override
    public Personnel update(Personnel personnel) {

        LOGGER.info(" Update existing Worker (Service) request has been called. ");

        return dao.update(personnel);
    }

    public Personnel getByName(String name) {

        return personnelRepository.findAll().stream()
                .filter(item -> item.getLastName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortWorkersByNumberORentedBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = rentingRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getRentingPerson().getLastName(),
                                Collectors.summingInt(Renting::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all workers based on Rented Books (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> getAllSalary(){


        Map<String, BigDecimal> sorted = new LinkedHashMap<>();

        Map<String, BigDecimal> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(Personnel::getDescription,
                                Collectors.reducing(BigDecimal.ZERO, Personnel::getSalaryUAH, BigDecimal::add)

                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all salary of workers (Service) query has been executed. ");

        return sorted;
    }

    public Map<LocalDate, Integer> sortWorkersByBirthday(){

        Map<LocalDate, Integer> sorted = new LinkedHashMap<>();

        Map<LocalDate, Integer> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(Personnel::getBirthday,
                                Collectors.summingInt(Personnel::getIntegerDescription)

                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<LocalDate, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all workers based on their birthday (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> getAverageSalary(){

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
                        Collectors.groupingBy(Personnel::getDescription,
                                Collectors.mapping(Personnel::getSalaryUAH, decimalToDouble)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Find and sort average salary of a position based on Library Workers (Service) query has been executed. ");

        return sorted;
    }
}
