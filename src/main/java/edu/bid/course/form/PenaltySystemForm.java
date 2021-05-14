package edu.bid.course.form;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystemForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystemForm: 1.0
 */


public class PenaltySystemForm {

    private String id;
    private String damageDescription;
    private String penaltySum;
    private String description;

    public PenaltySystemForm() {
    }

    public PenaltySystemForm(String id, String damageDescription, String penaltySum, String description) {
        this.id = id;
        this.damageDescription = damageDescription;
        this.penaltySum = penaltySum;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public String getPenaltySum() {
        return penaltySum;
    }

    public void setPenaltySum(String penaltySum) {
        this.penaltySum = penaltySum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
