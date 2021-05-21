package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * This is an interface representation of Position collection in MongoDB
 * course.PositionRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PositionRepository: 1.0
 */


@Repository
public interface PositionRepository extends MongoRepository<Position, String> {
}
