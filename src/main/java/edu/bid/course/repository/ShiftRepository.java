package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface representation of Shift collection in MongoDB
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftRepository: 1.0
 */


@Repository
public interface ShiftRepository extends MongoRepository<Shift, String> {
}
