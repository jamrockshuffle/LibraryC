package edu.bid.course.form;

import edu.bid.course.model.Author;
import edu.bid.course.model.Genre;
import edu.bid.course.model.Publisher;
import edu.bid.course.model.Stillage;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Year;

/**
 * Created by IntelliJ IDEA.
 * course.BookForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: BookForm: 1.0
 */

public class BookForm {

    private String id;

    private String name;
    private String authorName;
    private String genreName;
    private String yearOfPublishing;
    private String publisherName;
    private String stillageID;
    private String collateralCost;
    private String costPerDay;
    private String description;

    public BookForm() {
    }

    public BookForm(String id, String name, String authorName, String genreName, String yearOfPublishing, String publisherName, String stillageID, String collateralCost, String costPerDay, String description) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.genreName = genreName;
        this.yearOfPublishing = yearOfPublishing;
        this.publisherName = publisherName;
        this.stillageID = stillageID;
        this.collateralCost = collateralCost;
        this.costPerDay = costPerDay;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(String yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getStillageID() {
        return stillageID;
    }

    public void setStillageID(String stillageID) {
        this.stillageID = stillageID;
    }

    public String getCollateralCost() {
        return collateralCost;
    }

    public void setCollateralCost(String collateralCost) {
        this.collateralCost = collateralCost;
    }

    public String getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(String costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
