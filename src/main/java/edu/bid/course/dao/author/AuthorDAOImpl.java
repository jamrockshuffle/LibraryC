package edu.bid.course.dao.author;

import edu.bid.course.dao.genre.GenreDAOImpl;
import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;
import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Data access object (DAO) for Author model
 * course.AuthorDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorDAOImpl: 1.0
 */



@Repository
public class AuthorDAOImpl implements IAuthorDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDAOImpl.class);

    @Autowired
    AuthorRepository authorRepository;

    /**
     * Create new Author method with auto-generated id and created_at
     */

    @Override
    public Author create(Author author) {

        LOGGER.info(" Create new Author (DAO) request has been called. ");

        String id = String.valueOf(authorRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        author.setId(id);

        author.setIntegerDescription(1);
        author.setCreated_at(LocalDateTime.now());
        authorRepository.save(author);
        return author;
    }

    /**
     * Update Author method with auto-generated modified_at
     */

    @Override
    public Author update(Author author) {

        LOGGER.info(" Update existing Author (DAO) request has been called. ");

        Author author1 = authorRepository.findById(author.getId()).get();

        author.setId(author.getId());
        author.setIntegerDescription(1);
        author.setCreated_at(author1.getCreated_at());
        author.setModified_at(LocalDateTime.now());

        authorRepository.save(author);

        return author;

    }
}
