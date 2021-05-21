package edu.bid.course.dao.position;

import edu.bid.course.dao.book.BookDAOImpl;
import edu.bid.course.model.Book;
import edu.bid.course.model.Position;
import edu.bid.course.repository.BookRepository;
import edu.bid.course.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Position model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PositionDAOImpl: 1.0
 */

@Repository
public class PositionDAOImpl implements IPositionDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionDAOImpl.class);

    @Autowired
    PositionRepository positionRepository;

    /**
     * Create new Position method with auto-generated id and created_at
     */

    @Override
    public Position create(Position position) {

        LOGGER.info(" Create new Position (DAO) request has been called. ");

        String id = String.valueOf(positionRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        position.setId(id);
        position.setIntegerDescription(1);
        position.setCreated_at(LocalDateTime.now());
        positionRepository.save(position);
        return position;
    }

    /**
     * Update Position method with auto-generated modified_at
     */

    @Override
    public Position update(Position position) {

        LOGGER.info(" Update existing Position (DAO) request has been called. ");


        Position position1 = positionRepository.findById(position.getId()).get();

        position.setId(position.getId());
        position.setIntegerDescription(1);

        position.setIntegerDescription(1);
        position.setCreated_at(position1.getCreated_at());
        position.setModified_at(LocalDateTime.now());

        positionRepository.save(position);

        return position;

    }
}
