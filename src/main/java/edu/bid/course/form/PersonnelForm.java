package edu.bid.course.form;

import edu.bid.course.model.Position;
import edu.bid.course.model.Shift;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This is a model to hold String information about Library Workers
 * course.PersonnelForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PersonnelForm: 1.0
 */


public class PersonnelForm {

    private String id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String position;
    private String shift;
    private String birthday;
    private String hiringDate;
    private String address;
    private String phoneNumber;
    private String salaryUAH;
    private String description;

    public PersonnelForm() {
    }

    public PersonnelForm(String id, String lastName, String firstName, String patronymic, String position, String shift, String birthday, String hiringDate, String address, String phoneNumber, String salaryUAH, String description) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
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

    public String getSalaryUAH() {
        return salaryUAH;
    }

    public void setSalaryUAH(String salaryUAH) {
        this.salaryUAH = salaryUAH;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
