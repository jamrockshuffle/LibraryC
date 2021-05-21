package edu.bid.course.dao.personnel;

import edu.bid.course.dao.author.AuthorDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Personnel;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.repository.PersonnelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Personnel model
 * course.PersonnelDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelDAOImpl: 1.0
 */


@Repository
public class PersonnelDAOImpl implements IPersonnelDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnelDAOImpl.class);

    @Autowired
    PersonnelRepository personnelRepository;

    /**
     * Create new Worker method with auto-generated id and created_at
     */

    @Override
    public Personnel create(Personnel personnel) {

        LOGGER.info(" Create new Worker (DAO) request has been called. ");

        String id = String.valueOf(personnelRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        personnel.setId(id);
        personnel.setIntegerDescription(1);
        personnel.setCreated_at(LocalDateTime.now());
        personnelRepository.save(personnel);
        return personnel;
    }

    /**
     * Update Worker method with auto-generated modified_at
     */

    @Override
    public Personnel update(Personnel personnel) {

        LOGGER.info(" Update existing Worker (DAO) request has been called. ");


        Personnel personnel1 = personnelRepository.findById(personnel.getId()).get();

        personnel.setId(personnel.getId());
        personnel.setIntegerDescription(1);

        personnel.setCreated_at(personnel1.getCreated_at());
        personnel.setModified_at(LocalDateTime.now());

        personnelRepository.save(personnel);

        return personnel;

    }
}
