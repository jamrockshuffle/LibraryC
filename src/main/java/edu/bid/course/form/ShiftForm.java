package edu.bid.course.form;

/**
 * This is a model to hold String information about Worker Shifts
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: ShiftForm: 1.0
 */

public class ShiftForm {

    private String id;
    private String name;
    private String description;

    public ShiftForm() {
    }

    public ShiftForm(String id, String name, String description) {
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
