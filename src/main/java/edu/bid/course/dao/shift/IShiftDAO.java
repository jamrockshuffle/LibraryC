package edu.bid.course.dao.shift;

import edu.bid.course.model.Author;
import edu.bid.course.model.Shift;

public interface IShiftDAO {

    Shift create (Shift shift);
    Shift update (Shift shift);
}
