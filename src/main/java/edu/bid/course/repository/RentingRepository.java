package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Renting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface representation of Renting collection in MongoDB
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingRepository: 1.0
 */

@Repository
public interface RentingRepository extends MongoRepository<Renting, String> {
}
