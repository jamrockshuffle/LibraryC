package edu.bid.course.dao.book;

import edu.bid.course.model.Author;
import edu.bid.course.model.Book;

public interface IBookDAO {

    Book create (Book book);
    Book update (Book book);
}
