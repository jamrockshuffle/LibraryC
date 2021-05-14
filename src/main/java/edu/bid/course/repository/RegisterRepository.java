package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.RegisterRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterRepository: 1.0
 */


@Repository
public interface RegisterRepository extends MongoRepository<Register, String> {

}
