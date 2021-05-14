package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Personnel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.PersonnelRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelRepository: 1.0
 */

@Repository
public interface PersonnelRepository extends MongoRepository<Personnel, String> {
}
