package edu.bid.course.service.register;

import edu.bid.course.dao.publisher.PublisherDAOImpl;
import edu.bid.course.dao.register.RegisterDAOImpl;
import edu.bid.course.model.Personnel;
import edu.bid.course.model.Publisher;
import edu.bid.course.model.Register;
import edu.bid.course.model.Renting;
import edu.bid.course.repository.RegisterRepository;
import edu.bid.course.repository.RentingRepository;
import edu.bid.course.service.publisher.PublisherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for Register model DAO methods
 * course.RegisterServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterServiceImpl: 1.0
 */


@Repository
public class RegisterServiceImpl implements IRegisterService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    RegisterDAOImpl dao;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    RentingRepository rentingRepository;

    @Override
    public Register create(Register register) {

        LOGGER.info(" Create new Library Member (Service) request has been called. ");

        return dao.create(register);
    }

    @Override
    public Register update(Register register) {

        LOGGER.info(" Update existing Library Member (Service) request has been called. ");

        return dao.update(register);
    }

    public Register getByName(String name) {

        return registerRepository.findAll().stream()
                .filter(item -> item.getLastName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortReadersByNumberORentedBooks(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = rentingRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getReader().getLastName(),
                                Collectors.summingInt(Renting::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all readers based on Rented Books (Service) query has been executed. ");

        return sorted;
    }

    public Map<LocalDate, Integer> sortReadersByBirthday(){

        Map<LocalDate, Integer> sorted = new LinkedHashMap<>();

        Map<LocalDate, Integer> map = registerRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(Register::getBirthday,
                                Collectors.summingInt(Register::getIntegerDescription)

                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<LocalDate, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all readers based on their birthday (Service) query has been executed. ");

        return sorted;
    }
}
