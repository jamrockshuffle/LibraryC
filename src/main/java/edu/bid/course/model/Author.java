package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.Author
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Author: 1.0
 */

/**
 * This is a model to hold information about Authors
 */

@Schema(description = " Author List? Entity")
@Document("Author")
public class Author {

    @Schema(description = " Author's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Author's full name", example = "Вернор Віндж", defaultValue = "Ім'я Прізвище")
    private String name;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Author() {
    }


    public Author(String id, String name, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
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

    public int getIntegerDescription() {
        return integerDescription;
    }

    public void setIntegerDescription(int integerDescription) {
        this.integerDescription = integerDescription;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
