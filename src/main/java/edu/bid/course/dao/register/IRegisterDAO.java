package edu.bid.course.dao.register;

import edu.bid.course.model.Author;
import edu.bid.course.model.Register;

public interface IRegisterDAO {

    Register create (Register register);
    Register update (Register register);
}
