package ro.sda.spring.boot.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

public class DoctorDTO extends BaseDTO{

    private String firstName;

    private String lastName;

    private String street;

    private Long streetNr;

    private String postCode;

    private String email;



    private List<PatientDTO> patients;
    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }

    public void addPatient(PatientDTO patientDTO){
        this.patients.add(patientDTO);
    }

    public DoctorDTO(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime, String firstName, String lastName, String street, Long streetNr, String postCode, String email, List<PatientDTO> patients) {
        super(id, createdTime, lastModifiedTime);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.postCode = postCode;
        this.email = email;
        this.patients= patients;
    }

    public DoctorDTO() {
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
}
