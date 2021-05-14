package edu.bid.course.dao.author;

import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;

public interface IAuthorDAO {

    Author create (Author author);
    Author update (Author author);
}
