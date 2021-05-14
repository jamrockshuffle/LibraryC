package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

/**
 * Created by IntelliJ IDEA.
 * course.Book
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Book: 1.0
 */

@Schema(description = " Book List? Entity")
@Document("Book")
public class Book {

    @Schema(description = " Book's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Book's name", example = "Володар Перснів")
    private String name;
    @Schema(description = " Author's name (from Author collection)")
    private Author authorName;
    @Schema(description = " Genre (from Genre collection)")
    private Genre genreName;
    @Schema(description = " Year of publishing")
    private int yearOfPublishing;
    @Schema(description = " Publisher's name (from Publisher collection)")
    private Publisher publisherName;
    @Schema(description = " Book's location (from Stillage collection)")
    private Stillage stillageID;
    @Schema(description = " Collateral cost", example = "99.99")
    private BigDecimal collateralCost;
    @Schema(description = " Cost per day", example = "9.99")
    private BigDecimal costPerDay;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Book() {
    }

    public Book(String id, String name, Author authorName, Genre genreName, int yearOfPublishing, Publisher publisherName, Stillage stillageID, BigDecimal collateralCost, BigDecimal costPerDay, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
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
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Book(String id, String name, Author authorName, Genre genreName, int yearOfPublishing, Publisher publisherName, Stillage stillageID, BigDecimal collateralCost, BigDecimal costPerDay) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.genreName = genreName;
        this.yearOfPublishing = yearOfPublishing;
        this.publisherName = publisherName;
        this.stillageID = stillageID;
        this.collateralCost = collateralCost;
        this.costPerDay = costPerDay;
    }

    public int getIntegerDescription() {
        return integerDescription;
    }

    public void setIntegerDescription(int integerDescription) {
        this.integerDescription = integerDescription;
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

    public Author getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Author authorName) {
        this.authorName = authorName;
    }

    public Genre getGenreName() {
        return genreName;
    }

    public void setGenreName(Genre genreName) {
        this.genreName = genreName;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Publisher getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(Publisher publisherName) {
        this.publisherName = publisherName;
    }

    public Stillage getStillageID() {
        return stillageID;
    }

    public void setStillageID(Stillage stillageID) {
        this.stillageID = stillageID;
    }

    public BigDecimal getCollateralCost() {
        return collateralCost;
    }

    public void setCollateralCost(BigDecimal collateralCost) {
        this.collateralCost = collateralCost;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authorName=" + authorName +
                ", genreName=" + genreName +
                ", yearOfPublishing=" + yearOfPublishing +
                ", publisherName=" + publisherName +
                ", stillageID=" + stillageID +
                ", collateralCost=" + collateralCost +
                ", costPerDay=" + costPerDay +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
