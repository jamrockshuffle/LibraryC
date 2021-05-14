package edu.bid.course.repository;

import edu.bid.course.model.Author;
import edu.bid.course.model.DiscountSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.DiscountSystemRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemRepository: 1.0
 */

@Repository
public interface DiscountSystemRepository extends MongoRepository<DiscountSystem, String> {
}
