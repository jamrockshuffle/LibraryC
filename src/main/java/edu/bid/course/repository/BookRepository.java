package edu.bid.course.repository;

import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * course.BookRepository
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookRepository: 1.0
 */


@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
