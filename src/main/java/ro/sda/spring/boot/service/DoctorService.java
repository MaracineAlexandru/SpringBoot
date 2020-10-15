package ro.sda.spring.boot.service;


import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.exception.NotFoundException;
import ro.sda.spring.boot.repository.DoctorRepository;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostConstruct
    public void init() {
        this.createDefaultDoctors();

    }

    private void createDefaultDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Adrian", "Bobocel", "Str. Carpenului", 12l, "500412", "a.bobocel@gmail.com"));
        doctors.add(new Doctor("Adrian", "Rotila", "Str. Socului", 45l, "500435", "a.rotila@gmail.com"));
        doctors.add(new Doctor("Bogdan", "Gabor", "Str. Nucului", 5l, "500987", "bogdan.gabor@yahoo.com"));
        doctors.add(new Doctor("Constantin", "Juncu", "Str. Ciresului", 59l, "500654", "c.juncu@yahoo.com"));
        doctors.add(new Doctor("George", "Niculae", "Str. Calea Bucuresti", 255l, "500487", "g.nc12@gmail.com"));
        doctorRepository.saveAll(doctors);
    }

    public List<Doctor> findByStreetNumberGreaterThan(Long id) {
        Doctor doctor = this.findDoctorbyId(id);
        List<Doctor> doctors = doctorRepository.findByStreetNrGreaterThan(doctor.getStreetNr());
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    public List<Doctor> findByFirstName(String firstName) {
        List<Doctor> doctors = doctorRepository.findByFirstName(firstName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    /*public List<Doctor> findByFirstNameOrLastName(String firstName, String lastName) {
        List<Doctor> doctors = doctorRepository.findByFirstNameOrLastName(firstName, lastName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }*/

    public List<Doctor> findAllDoctorsPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Doctor> doctors = doctorRepository.findAll(pageable).get().collect(Collectors.toList());
        doctors.forEach(doctor ->
                System.out.println(doctor.toString())
        );
        return doctors;
    }

    public Long countDoctors() {
        System.out.println("There are " + doctorRepository.count() + " doctors.");
        return doctorRepository.count();
    }

    public Doctor findDoctorbyId(Long id) {
        Optional<Doctor> optDoctor = doctorRepository.findById(id);
        if (optDoctor.isPresent()) {
            Doctor doctor = optDoctor.get();
            System.out.println(doctor.toString());
            return doctor;
        } else {
            throw new NotFoundException("Doctor with ID " + id + " does not exist.");
        }
    }

    public Doctor saveDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id){
        this.findDoctorbyId(id);
        doctorRepository.deleteById(id);
    }



}
