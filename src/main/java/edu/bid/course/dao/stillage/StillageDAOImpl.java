package edu.bid.course.dao.stillage;

import edu.bid.course.dao.shift.ShiftDAOImpl;
import edu.bid.course.model.Shift;
import edu.bid.course.model.Stillage;
import edu.bid.course.repository.ShiftRepository;
import edu.bid.course.repository.StillageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Stillage model
 * course.StillageDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageDAOImpl: 1.0
 */

@Repository
public class StillageDAOImpl implements IStillageDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(StillageDAOImpl.class);

    @Autowired
    StillageRepository stillageRepository;

    /**
     * Create new Stillage method with auto-generated id and created_at
     */

    @Override
    public Stillage create(Stillage stillage) {

        LOGGER.info(" Create new Stillage (DAO) request has been called. ");

        String id = String.valueOf(stillageRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        stillage.setId(id);
        stillage.setIntegerDescription(1);
        stillage.setCreated_at(LocalDateTime.now());
        stillageRepository.save(stillage);
        return stillage;
    }

    /**
     * Update Stillage method with auto-generated modified_at
     */

    @Override
    public Stillage update(Stillage stillage) {

        LOGGER.info(" Update existing Stillage (DAO) request has been called. ");


        Stillage stillage1 = stillageRepository.findById(stillage.getId()).get();

        stillage.setId(stillage.getId());
        stillage.setIntegerDescription(1);

        stillage.setCreated_at(stillage1.getCreated_at());
        stillage.setModified_at(LocalDateTime.now());

        stillageRepository.save(stillage);

        return stillage;

    }
}
