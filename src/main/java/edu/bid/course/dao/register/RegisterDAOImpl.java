package edu.bid.course.dao.register;

import edu.bid.course.dao.publisher.PublisherDAOImpl;
import edu.bid.course.model.Publisher;
import edu.bid.course.model.Register;
import edu.bid.course.repository.PublisherRepository;
import edu.bid.course.repository.RegisterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.RegisterDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterDAOImpl: 1.0
 */

@Repository
public class RegisterDAOImpl implements IRegisterDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterDAOImpl.class);

    @Autowired
    RegisterRepository registerRepository;

    @Override
    public Register create(Register register) {

        LOGGER.info(" Create new Library Member (DAO) request has been called. ");

        String id = String.valueOf(registerRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        register.setId(id);
        register.setIntegerDescription(1);
        register.setCreated_at(LocalDateTime.now());
        registerRepository.save(register);
        return register;
    }

    @Override
    public Register update(Register register) {

        LOGGER.info(" Update existing Library Member (DAO) request has been called. ");


        Register register1 = registerRepository.findById(register.getId()).get();

        register.setId(register.getId());
        register.setIntegerDescription(1);

        register.setCreated_at(register1.getCreated_at());
        register.setModified_at(LocalDateTime.now());

        registerRepository.save(register);

        return register;

    }
}
