package edu.bid.course.form;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This is a model to hold String information about Publishers
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PublisherForm: 1.0
 */

public class PublisherForm {

    private String id;
    private String name;
    private String description;

    public PublisherForm() {
    }

    public PublisherForm(String id, String name, String description) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
