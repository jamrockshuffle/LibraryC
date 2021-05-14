package edu.bid.course.dao.genre;

import edu.bid.course.model.Genre;
import edu.bid.course.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


/**
 * Created by IntelliJ IDEA.
 * course.GenreDAOImpl
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreDAOImpl: 1.0
 */


@Repository
public class GenreDAOImpl implements IGenreDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreDAOImpl.class);

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre create(Genre genre) {

        LOGGER.info(" Create new Genre (DAO) request has been called. ");

        String id = String.valueOf(genreRepository.findAll().stream()
                .mapToInt(el -> Integer.parseInt(el.getId())).max().orElse(0) + 1);
        genre.setId(id);
        genre.setIntegerDescription(1);
        genre.setCreated_at(LocalDateTime.now());
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public Genre update(Genre genre) {

        LOGGER.info(" Update existing Genre (DAO) request has been called. ");


        Genre genre1 = genreRepository.findById(genre.getId()).get();

        /*if(genre1 == null){
            return null;
        }*/

        genre.setId(genre.getId());
        genre.setIntegerDescription(1);

        genre.setCreated_at(genre1.getCreated_at());
        genre.setModified_at(LocalDateTime.now());

        genreRepository.save(genre);

        return genre;

    }
}
