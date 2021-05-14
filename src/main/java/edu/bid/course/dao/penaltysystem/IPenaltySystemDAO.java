package edu.bid.course.dao.penaltysystem;

import edu.bid.course.model.Author;
import edu.bid.course.model.PenaltySystem;

public interface IPenaltySystemDAO {

    PenaltySystem create (PenaltySystem penaltySystem);
    PenaltySystem update (PenaltySystem penaltySystem);
}
