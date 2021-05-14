package edu.bid.course.form;

import edu.bid.course.model.DiscountSystem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * course.RegisterForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RegisterForm: 1.0
 */

public class RegisterForm {

    private String id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String category;
    private String description;

    public RegisterForm() {
    }

    public RegisterForm(String id, String lastName, String firstName, String patronymic, String birthday, String address, String phoneNumber, String category, String description) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
