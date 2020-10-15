package ro.sda.spring.boot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Doctor extends BaseEntity{



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

    @Column(nullable = true, length = 64)
    private String email;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private List<Patient> patients= new ArrayList<>();

    public Doctor(String firstName, String lastName, String street, Long streetNr, String postCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.postCode = postCode;
        this.email = email;
    }

    public Doctor() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(getId(), doctor.getId()) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName) &&
                Objects.equals(street, doctor.street) &&
                Objects.equals(streetNr, doctor.streetNr) &&
                Objects.equals(postCode, doctor.postCode) &&
                Objects.equals(email, doctor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), firstName, lastName, street, streetNr, postCode, email);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", streetNr=" + streetNr +
                ", postCode='" + postCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
