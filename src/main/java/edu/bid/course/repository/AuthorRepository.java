package edu.bid.course.repository;

import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface representation of Author collection in MongoDB
 * course.AuthorRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorRepository: 1.0
 */

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
