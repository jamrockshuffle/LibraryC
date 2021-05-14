package edu.bid.course.form;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Created by IntelliJ IDEA.
 * course.DiscountSystemForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystemForm: 1.0
 */

public class DiscountSystemForm {

    private String id;
    private String category;
    private String discountPercentage;
    private String description;

    public DiscountSystemForm() {
    }

    public DiscountSystemForm(String id, String category, String discountPercentage, String description) {
        this.id = id;
        this.category = category;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
