package edu.bid.course.dao.penalty;

import edu.bid.course.model.Author;
import edu.bid.course.model.Penalty;

public interface IPenaltyDAO {

    Penalty create (Penalty penalty);
    Penalty update (Penalty penalty);
}
