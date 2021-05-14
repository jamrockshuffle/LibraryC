package edu.bid.course.dao.discountsystem;

import edu.bid.course.model.Author;
import edu.bid.course.model.DiscountSystem;

public interface IDiscountSystemDAO {

    DiscountSystem create (DiscountSystem discountSystem);
    DiscountSystem update (DiscountSystem discountSystem);
}
