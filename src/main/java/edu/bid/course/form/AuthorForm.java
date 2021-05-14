package edu.bid.course.form;


/**
 * Created by IntelliJ IDEA.
 * course.AuthorForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: AuthorForm: 1.0
 */

public class AuthorForm {

    private String id;
    private String name;
    private String description;

    public AuthorForm() {
    }

    public AuthorForm(String id, String name, String description) {
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
