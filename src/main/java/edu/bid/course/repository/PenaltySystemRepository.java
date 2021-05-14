package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.PenaltySystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystemRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemRepository: 1.0
 */


@Repository
public interface PenaltySystemRepository extends MongoRepository<PenaltySystem, String> {
}
