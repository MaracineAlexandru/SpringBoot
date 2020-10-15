package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.exception.NotFoundException;
import ro.sda.spring.boot.repository.DoctorRepository;
import ro.sda.spring.boot.repository.PatientRepository;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    public void init() {
        this.createDefaultPatients();
        //this.createPatient();
        //this.findPatientById();
        //this.deletePatientById();
        //patientRepository.selectPatientByBirthDayBefore(LocalDate.of(1980,2,20));
        //this.findPatientWithBirthdayBefore(LocalDate.of(1980,2,22));

    }

    public List<Patient> findPatientWithBirthdayBefore(LocalDate dateBefore){
        return patientRepository.findByBirthDayBefore(dateBefore);
    }

    public void deletePatientById(Long id) {
       /* Optional<Patient> patientOptional= patientRepository.findById(id);
        if(patientOptional.isPresent()){
            Patient patient= patientOptional.get();
            patientRepository.delete(patient);
        } else {
            System.out.println("Patient with ID " + id + " does not exist.");
            throw new RuntimeException();
        }*/
        this.findPatientById(id);
        patientRepository.deleteById(id);

    }

    public Patient savePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        Optional<Patient> patientOptional= patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            System.out.println(patient.toString());
            return patient;
        } else {
            throw new NotFoundException("Patient with ID " + id + " does not exist.");
        }
    }

    private void createPatient() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Insert first name : ");
        String firstName= scanner.next();
        System.out.println("Insert last name : ");
        String lastName= scanner.next();
        System.out.println("Insert Street : ");
        String street= scanner.next();
        System.out.println("Insert Street number : ");
        Long streetNre= scanner.nextLong();
        System.out.println("Insert postcode : ");
        String postCode= scanner.next();
        System.out.println("Insert birth year:");
        int y = Integer.parseInt(scanner.next());
        System.out.println("Insert birth month:");
        int m = Integer.parseInt(scanner.next());
        System.out.println("Insert birth day:");
        int d = Integer.parseInt(scanner.next());
        LocalDate birthDay=LocalDate.of(y,m,d);
        System.out.println("Insert weight : ");
        BigDecimal weight= scanner.nextBigDecimal();
        System.out.println("Insert height : ");
        Long height= scanner.nextLong();
        Patient patient= new Patient(firstName,lastName,street,streetNre,postCode,birthDay,height,weight);
        patientRepository.save(patient);
    }

    private void createDefaultPatients() {
        List<Patient> patients= new ArrayList<>();
        patients.add(new Patient("Adrian", "Avion", "Str. Carpenului", 12l, "500412",LocalDate.of(1990,01,18), 179l,BigDecimal.valueOf(83.4)));
        patients.add(new Patient("Adrian", "Masina", "Str. Socului", 45l, "500435",LocalDate.of(1981,02,20), 169l,BigDecimal.valueOf(70.4)));
        patients.add(new Patient("Bogdan", "Aliaj", "Str. Nucului", 5l, "500987",LocalDate.of(1990,01,10), 150l,BigDecimal.valueOf(64.4)));
        patients.add(new Patient("Constantin", "Thor", "Str. Ciresului", 59l, "500654",LocalDate.of(1970,10,9), 191l,BigDecimal.valueOf(100.4)));
        patients.add(new Patient("George", "Tanc", "Str. Calea Bucuresti", 255l, "500487",LocalDate.of(1980,12,8), 180l,BigDecimal.valueOf(75.4)));
        patientRepository.saveAll(patients);
    }
}
