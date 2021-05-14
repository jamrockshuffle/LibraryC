package edu.bid.course.dao.position;

import edu.bid.course.model.Author;
import edu.bid.course.model.Position;

public interface IPositionDAO {

    Position create (Position position);
    Position update (Position position);
}
