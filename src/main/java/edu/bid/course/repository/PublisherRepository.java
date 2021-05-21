package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Position;
import edu.bid.course.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface representation of Publisher collection in MongoDB
 * course.PublisherRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PublisherRepository: 1.0
 */


@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
}
