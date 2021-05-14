package edu.bid.course.dao.genre;

import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;

public interface IGenreDAO {

    Genre create (Genre genre);
    Genre update (Genre genre);
}
