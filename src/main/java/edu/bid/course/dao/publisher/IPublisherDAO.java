package edu.bid.course.dao.publisher;

import edu.bid.course.model.Author;
import edu.bid.course.model.Publisher;

public interface IPublisherDAO {

    Publisher create (Publisher publisher);
    Publisher update (Publisher publisher);
}
