package edu.bid.course.dao.shift;

import edu.bid.course.dao.position.PositionDAOImpl;
import edu.bid.course.model.Position;
import edu.bid.course.model.Shift;
import edu.bid.course.repository.PositionRepository;
import edu.bid.course.repository.ShiftRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Shift model
 * course.ShiftDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftDAOImpl: 1.0
 */

@Repository
public class ShiftDAOImpl implements IShiftDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftDAOImpl.class);

    @Autowired
    ShiftRepository shiftRepository;

    /**
     * Create new Shift method with auto-generated id and created_at
     */

    @Override
    public Shift create(Shift shift) {

        LOGGER.info(" Create new Shift (DAO) request has been called. ");

        String id = String.valueOf(shiftRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        shift.setId(id);
        shift.setIntegerDescription(1);
        shift.setCreated_at(LocalDateTime.now());
        shiftRepository.save(shift);
        return shift;
    }

    /**
     * Update Shift method with auto-generated modified_at
     */

    @Override
    public Shift update(Shift shift) {

        LOGGER.info(" Update existing Shift (DAO) request has been called. ");


        Shift shift1 = shiftRepository.findById(shift.getId()).get();

        shift.setId(shift.getId());
        shift.setIntegerDescription(1);

        shift.setCreated_at(shift1.getCreated_at());
        shift.setModified_at(LocalDateTime.now());

        shiftRepository.save(shift);

        return shift;

    }
}
