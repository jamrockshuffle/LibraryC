package edu.bid.course.repository;

import edu.bid.course.model.DiscountSystem;
import edu.bid.course.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.GenreRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreRepository: 1.0
 */

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}
