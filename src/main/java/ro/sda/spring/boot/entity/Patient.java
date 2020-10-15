package ro.sda.spring.boot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Patient extends BaseEntity {

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @Column(nullable = false, length = 128)
    private String street;
    @Column(nullable = false)
    private Long streetNr;
    @Column(nullable = false, length = 32)
    private String postCode;

    @Column(nullable = true)
    private LocalDate birthDay;

    @Column(nullable = true)
    private Long height;

    @Column(nullable = true)
    private BigDecimal weight;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctor;

    public Patient(String firstName, String lastName, String street, Long streetNr, String postCode, LocalDate birthDay, Long height, BigDecimal weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.postCode = postCode;
        this.birthDay = birthDay;
        this.height = height;
        this.weight = weight;
    }

    public Patient() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(Long streetNr) {
        this.streetNr = streetNr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(street, patient.street) &&
                Objects.equals(streetNr, patient.streetNr) &&
                Objects.equals(postCode, patient.postCode) &&
                Objects.equals(height, patient.height) &&
                Objects.equals(weight, patient.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, street, streetNr, postCode, height, weight);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", streetNr=" + streetNr +
                ", postCode='" + postCode + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
