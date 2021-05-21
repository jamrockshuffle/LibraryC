package edu.bid.course.dao.penalty;

import edu.bid.course.dao.book.BookDAOImpl;
import edu.bid.course.model.Book;
import edu.bid.course.model.Penalty;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.PenaltyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


/**
 * Data access object (DAO) for Penalty model
 * course.PenaltyDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltyDAOImpl: 1.0
 */

@Repository
public class PenaltyDAOImpl implements IPenaltyDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PenaltyDAOImpl.class);

    @Autowired
    PenaltyRepository penaltyRepository;

    /**
     * Create new Penalty method with auto-generated id and created_at
     */

    @Override
    public Penalty create(Penalty penalty) {

        LOGGER.info(" Create new Penalty (DAO) request has been called. ");

        String id = String.valueOf(penaltyRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        penalty.setId(id);
        penalty.setIntegerDescription(1);
        penalty.setCreated_at(LocalDateTime.now());
        penaltyRepository.save(penalty);
        return penalty;
    }

    /**
     * Update Genre method with auto-generated modified_at
     */

    @Override
    public Penalty update(Penalty penalty) {

        LOGGER.info(" Update existing Penalty (DAO) request has been called. ");


        Penalty penalty1 = penaltyRepository.findById(penalty.getId()).get();

        penalty.setId(penalty.getId());
        penalty.setIntegerDescription(1);

        penalty.setCreated_at(penalty1.getCreated_at());
        penalty.setModified_at(LocalDateTime.now());

        penaltyRepository.save(penalty);

        return penalty;

    }
}
