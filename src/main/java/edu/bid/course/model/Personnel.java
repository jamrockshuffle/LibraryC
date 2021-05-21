package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is a model to hold information about Library Workers
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Personnel: 1.0
 */

@Schema(description = " Personnel List? Entity")
@Document("Personnel")
public class Personnel {

    @Schema(description = " Personnel's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Last name", example = " Петров")
    private String lastName;
    @Schema(description = " First name", example = " Олег")
    private String firstName;
    @Schema(description = " Patronymic", example = " Миколайович")
    private String patronymic;

    @Schema(description = " Position (from Position collection)")
    private Position position;
    @Schema(description = " Shift (from Shift collection)")
    private Shift shift;

    @Schema(description = " Birthday", example = " 1975.10.10")
    private LocalDate birthday;
    @Schema(description = " Hiring date", example = " 2010.10.10")
    private LocalDate hiringDate;
    @Schema(description = " Home address", example = " Showa st, 62")
    private String address;
    @Schema(description = " Phone number", example = " +380 95 838 1233")
    private String phoneNumber;
    @Schema(description = " Salary (in UAH)", example = " 3000")
    private BigDecimal salaryUAH;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Personnel() {
    }

    public Personnel(String id, String lastName, String firstName, String patronymic, Position position, Shift shift, LocalDate birthday, LocalDate hiringDate, String address, String phoneNumber, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.position = position;
        this.shift = shift;
        this.birthday = birthday;
        this.hiringDate = hiringDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Personnel(String id, String lastName, String firstName, String patronymic, Position position, Shift shift, LocalDate birthday, LocalDate hiringDate, String address, String phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.position = position;
        this.shift = shift;
        this.birthday = birthday;
        this.hiringDate = hiringDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Personnel(String id, String lastName, String firstName, String patronymic, Position position, Shift shift, LocalDate birthday, LocalDate hiringDate, String address, String phoneNumber, BigDecimal salaryUAH, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.position = position;
        this.shift = shift;
        this.birthday = birthday;
        this.hiringDate = hiringDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salaryUAH = salaryUAH;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public BigDecimal getSalaryUAH() {
        return salaryUAH;
    }

    public void setSalaryUAH(BigDecimal salaryUAH) {
        this.salaryUAH = salaryUAH;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
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
        return "Personnel{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position=" + position +
                ", shift=" + shift +
                ", birthday=" + birthday +
                ", hiringDate=" + hiringDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
