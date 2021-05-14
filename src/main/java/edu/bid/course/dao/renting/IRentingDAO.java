package edu.bid.course.dao.renting;

import edu.bid.course.model.Author;
import edu.bid.course.model.Renting;

public interface IRentingDAO {

    Renting create (Renting renting);
    Renting update (Renting renting);
}
