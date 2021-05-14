package edu.bid.course.dao.penaltysystem;

import edu.bid.course.dao.penalty.PenaltyDAOImpl;
import edu.bid.course.model.Penalty;
import edu.bid.course.model.PenaltySystem;
import edu.bid.course.repository.PenaltyRepository;
import edu.bid.course.repository.PenaltySystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystemDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemDAOImpl: 1.0
 */

@Repository
public class PenaltySystemDAOImpl implements IPenaltySystemDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltySystemDAOImpl.class);

    @Autowired
    PenaltySystemRepository penaltySystemRepository;

    @Override
    public PenaltySystem create(PenaltySystem penaltySystem) {

        LOGGER.info(" Create new Damage (DAO) request has been called. ");

        String id = String.valueOf(penaltySystemRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        penaltySystem.setId(id);
        penaltySystem.setIntegerDescription(1);
        penaltySystem.setCreated_at(LocalDateTime.now());
        penaltySystemRepository.save(penaltySystem);
        return penaltySystem;
    }

    @Override
    public PenaltySystem update(PenaltySystem penaltySystem) {

        LOGGER.info(" Update existing Damage (DAO) request has been called. ");


        PenaltySystem penaltySystem1 = penaltySystemRepository.findById(penaltySystem.getId()).get();

        penaltySystem.setId(penaltySystem.getId());
        penaltySystem.setIntegerDescription(1);

        penaltySystem.setCreated_at(penaltySystem1.getCreated_at());
        penaltySystem.setModified_at(LocalDateTime.now());

        penaltySystemRepository.save(penaltySystem);

        return penaltySystem;

    }
}
