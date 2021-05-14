package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Stillage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.StillageRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageRepository: 1.0
 */


@Repository
public interface StillageRepository extends MongoRepository<Stillage, String> {
}
