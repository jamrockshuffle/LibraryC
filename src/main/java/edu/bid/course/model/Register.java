package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.Register
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Register: 1.0
 */


@Schema(description = " Register List? Entity")
@Document("Register")
public class Register {

    @Schema(description = " Register's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Last name", example = " Петров")
    private String lastName;
    @Schema(description = " First name", example = " Олег")
    private String firstName;
    @Schema(description = " Patronymic", example = " Миколайович")
    private String patronymic;

    @Schema(description = " Birthday", example = " 1975.10.10")
    private LocalDate birthday;
    @Schema(description = " Home address", example = " Showa st, 62")
    private String address;
    @Schema(description = " Phone number", example = " +380 95 838 1233")
    private String phoneNumber;
    @Schema(description = " Discount category (from DiscountSystem collection)")
    private DiscountSystem category;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Register() {
    }

    public Register(String id, String lastName, String firstName, String patronymic, LocalDate birthday, String address, String phoneNumber, DiscountSystem category) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.category = category;
    }

    public Register(String id, String lastName, String firstName, String patronymic, LocalDate birthday, String address, String phoneNumber, DiscountSystem category, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    public DiscountSystem getCategory() {
        return category;
    }

    public void setCategory(DiscountSystem category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIntegerDescription() {
        return integerDescription;
    }

    public void setIntegerDescription(int integerDescription) {
        this.integerDescription = integerDescription;
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
        return "Register{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
