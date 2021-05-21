package edu.bid.course.service.discountsystem;

import edu.bid.course.dao.book.BookDAOImpl;
import edu.bid.course.dao.discountsystem.DiscountSystemDAOImpl;
import edu.bid.course.model.Book;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Register;
import edu.bid.course.repository.DiscountSystemRepository;
import edu.bid.course.repository.RegisterRepository;
import edu.bid.course.service.book.BookServiceImpl;
import edu.bid.course.service.register.RegisterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for DiscountSystem model DAO methods
 * course.DiscountSystemServiceImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemServiceImpl: 1.0
 */


@Repository
public class DiscountSystemServiceImpl implements IDiscountSystemService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSystemServiceImpl.class);

    @Autowired
    DiscountSystemDAOImpl dao;

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    RegisterServiceImpl registerService;

    @Override
    public DiscountSystem create(DiscountSystem discountSystem) {

        LOGGER.info(" Create new Discount Category (Service) request has been called. ");

        return dao.create(discountSystem);
    }

    @Override
    public DiscountSystem update(DiscountSystem discountSystem) {

        LOGGER.info(" Update existing Discount Category (Service) request has been called. ");

        return dao.update(discountSystem);
    }

    public DiscountSystem getByCategory(String category) {

        return discountSystemRepository.findAll().stream()
                .filter(item -> item.getCategory()
                        .equals(category))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> sortCategoriesByNumberOfMembers(){

        Map<String, Integer> sorted = new LinkedHashMap<>();

        Map<String, Integer> map = registerRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(r -> r.getCategory().getCategory(),
                                Collectors.summingInt(Register::getIntegerDescription)
                        )
                );

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sorted.put(entry.getKey(), entry.getValue()));

        LOGGER.info(" Count and sort all categories based on registered Library Members (Service) query has been executed. ");

        return sorted;
    }

}
