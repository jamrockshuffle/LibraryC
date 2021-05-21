package edu.bid.course.dao.publisher;

import edu.bid.course.dao.position.PositionDAOImpl;
import edu.bid.course.model.Position;
import edu.bid.course.model.Publisher;
import edu.bid.course.repository.PositionRepository;
import edu.bid.course.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Publisher model
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PublisherDAOImpl: 1.0
 */

@Repository
public class PublisherDAOImpl implements IPublisherDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDAOImpl.class);

    @Autowired
    PublisherRepository publisherRepository;

    /**
     * Create new Publisher method with auto-generated id and created_at
     */

    @Override
    public Publisher create(Publisher publisher) {

        LOGGER.info(" Create new Publisher (DAO) request has been called. ");

        String id = String.valueOf(publisherRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        publisher.setId(id);
        publisher.setIntegerDescription(1);
        publisher.setCreated_at(LocalDateTime.now());
        publisherRepository.save(publisher);
        return publisher;
    }

    /**
     * Update Publisher method with auto-generated modified_at
     */

    @Override
    public Publisher update(Publisher publisher) {

        LOGGER.info(" Update existing Publisher (DAO) request has been called. ");


        Publisher publisher1 = publisherRepository.findById(publisher.getId()).get();

        publisher.setId(publisher.getId());
        publisher.setIntegerDescription(1);

        publisher.setCreated_at(publisher1.getCreated_at());
        publisher.setModified_at(LocalDateTime.now());

        publisherRepository.save(publisher);

        return publisher;

    }
}
