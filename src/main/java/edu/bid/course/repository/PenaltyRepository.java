package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Penalty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltyRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltyRepository: 1.0
 */

@Repository
public interface PenaltyRepository extends MongoRepository<Penalty, String> {
}
