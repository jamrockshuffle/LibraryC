package edu.bid.course.dao.personnel;

import edu.bid.course.model.Author;
import edu.bid.course.model.Personnel;

public interface IPersonnelDAO {

    Personnel create (Personnel personnel);
    Personnel update (Personnel personnel);
}
