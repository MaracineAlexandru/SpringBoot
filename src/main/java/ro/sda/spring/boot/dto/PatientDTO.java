package ro.sda.spring.boot.dto;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientDTO extends BaseDTO{

    private String firstName;

    private String lastName;

    private String street;

    private Long streetNr;

    private String postCode;

    private LocalDate birthDay;

    private Long height;

    private BigDecimal weight;

    private Long doctorId;

    public PatientDTO(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime, String firstName, String lastName, String street, Long streetNr, String postCode, LocalDate birthDay, Long height, BigDecimal weight, Long doctorId) {
        super(id, createdTime, lastModifiedTime);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.postCode = postCode;
        this.birthDay = birthDay;
        this.height = height;
        this.weight = weight;
        this.doctorId=doctorId;
    }

    public PatientDTO() {

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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
