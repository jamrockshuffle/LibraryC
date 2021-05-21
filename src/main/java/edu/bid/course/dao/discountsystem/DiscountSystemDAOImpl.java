package edu.bid.course.dao.discountsystem;

import edu.bid.course.dao.book.BookDAOImpl;
import edu.bid.course.model.Book;
import edu.bid.course.model.DiscountSystem;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.DiscountSystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for DiscountSystem model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemDAOImpl: 1.0
 */


@Repository
public class DiscountSystemDAOImpl implements IDiscountSystemDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountSystemDAOImpl.class);

    @Autowired
    DiscountSystemRepository discountSystemRepository;

    /**
     * Create new Discount Category method with auto-generated id and created_at
     */

    @Override
    public DiscountSystem create(DiscountSystem discountSystem) {

        LOGGER.info(" Create new Discount Category (DAO) request has been called. ");

        String id = String.valueOf(discountSystemRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        discountSystem.setId(id);
        discountSystem.setIntegerDescription(1);
        discountSystem.setCreated_at(LocalDateTime.now());
        discountSystemRepository.save(discountSystem);
        return discountSystem;
    }

    /**
     * Update Discount Category method with auto-generated modified_at
     */

    @Override
    public DiscountSystem update(DiscountSystem discountSystem) {

        LOGGER.info(" Update existing Discount Category (DAO) request has been called. ");


        DiscountSystem discountSystem1 = discountSystemRepository.findById(discountSystem.getId()).get();

        discountSystem.setId(discountSystem.getId());
        discountSystem.setIntegerDescription(1);

        discountSystem.setCreated_at(discountSystem1.getCreated_at());
        discountSystem.setModified_at(LocalDateTime.now());

        discountSystemRepository.save(discountSystem);

        return discountSystem;

    }
}
