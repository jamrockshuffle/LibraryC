package edu.bid.course.dao.renting;

import edu.bid.course.dao.publisher.PublisherDAOImpl;
import edu.bid.course.model.Publisher;
import edu.bid.course.model.Renting;
import edu.bid.course.repository.PublisherRepository;
import edu.bid.course.repository.RentingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Renting model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingDAOImpl: 1.0
 */

@Repository
public class RentingDAOImpl implements IRentingDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(RentingDAOImpl.class);

    @Autowired
    RentingRepository rentingRepository;

    /**
     * Create new Rented Book method with auto-generated id and created_at
     */

    @Override
    public Renting create(Renting renting) {

        LOGGER.info(" Create new Rented Book (DAO) request has been called. ");

        String id = String.valueOf(rentingRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        renting.setId(id);
        renting.setIntegerDescription(1);
        renting.setCreated_at(LocalDateTime.now());
        rentingRepository.save(renting);
        return renting;
    }

    /**
     * Update Rented Book method with auto-generated modified_at
     */

    @Override
    public Renting update(Renting renting) {

        LOGGER.info(" Update existing Rented Book (DAO) request has been called. ");


        Renting renting1 = rentingRepository.findById(renting.getId()).get();

        renting.setId(renting.getId());
        renting.setIntegerDescription(1);

        renting.setCreated_at(renting1.getCreated_at());
        renting.setModified_at(LocalDateTime.now());

        rentingRepository.save(renting);

        return renting;

    }
}
