package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is an interface representation of Register collection in MongoDB
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterRepository: 1.0
 */


@Repository
public interface RegisterRepository extends MongoRepository<Register, String> {

}
