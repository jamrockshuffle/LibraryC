package edu.bid.course.dao.book;

import edu.bid.course.dao.author.AuthorDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Book;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.BookDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookDAOImpl: 1.0
 */

@Repository
public class BookDAOImpl implements IBookDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDAOImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book create(Book book) {

        LOGGER.info(" Create new Book (DAO) request has been called. ");

        String id = String.valueOf(bookRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        book.setId(id);
        book.setIntegerDescription(1);
        book.setCreated_at(LocalDateTime.now());
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(Book book) {

        LOGGER.info(" Update existing Book (DAO) request has been called. ");


        Book book1 = bookRepository.findById(book.getId()).get();

        book.setId(book.getId());
        book.setIntegerDescription(1);
        book.setCreated_at(book1.getCreated_at());
        book.setModified_at(LocalDateTime.now());

        bookRepository.save(book);

        return book;

    }
}
