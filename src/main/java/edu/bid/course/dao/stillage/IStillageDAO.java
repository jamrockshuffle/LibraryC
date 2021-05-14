package edu.bid.course.dao.stillage;

import edu.bid.course.model.Author;
import edu.bid.course.model.Stillage;

public interface IStillageDAO {

    Stillage create (Stillage stillage);
    Stillage update (Stillage stillage);
}
