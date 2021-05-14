package edu.bid.course.service.shift;

import edu.bid.course.dao.publisher.PublisherDAOImpl;
import edu.bid.course.dao.shift.ShiftDAOImpl;
import edu.bid.course.model.*;
import edu.bid.course.repository.PersonnelRepository;
import edu.bid.course.repository.ShiftRepository;
import edu.bid.course.service.publisher.PublisherServiceImpl;
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
 * Created by IntelliJ IDEA.
 * course.ShiftServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftServiceImpl: 1.0
 */


@Repository
public class ShiftServiceImpl implements IShiftService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftServiceImpl.class);

    @Autowired
    ShiftDAOImpl dao;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Override
    public Shift create(Shift shift) {

        LOGGER.info(" Create new Shift (Service) request has been called. ");

        return dao.create(shift);
    }

    @Override
    public Shift update(Shift shift) {

        LOGGER.info(" Update existing Shift (Service) request has been called. ");

        return dao.update(shift);
    }

    public Shift getByName(String name) {

        return shiftRepository.findAll().stream()
                .filter(item -> item.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortShiftsByNumberOfWorkers(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getShift().getName(),
                                Collectors.summingInt(Personnel::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all shifts based on Library Workers (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> sortShiftsByAverageSalary(){

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
                        Collectors.groupingBy(r -> r.getShift().getName(),
                                Collectors.mapping(Personnel::getSalaryUAH, decimalToDouble)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Find and sort average salary of a position based on Library Workers (Service) query has been executed. ");

        return sorted;
    }

    public Map<String, BigDecimal> sortShiftsByAllSalary(){


        Map<String, BigDecimal> sorted = new LinkedHashMap<>();

        Map<String, BigDecimal> map = personnelRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getShift().getName(),
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
