package ro.sda.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.DoctorDTO;
import ro.sda.spring.boot.dto.PageableDoctorResponseDTO;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.service.DoctorService;
import ro.sda.spring.boot.transformer.DoctorTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;
    private  final DoctorTransformer doctorTransformer;

    @Autowired
    public DoctorController(DoctorService doctorService,DoctorTransformer doctorTransformer) {
        this.doctorService = doctorService;
        this.doctorTransformer=doctorTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DoctorDTO> findDoctorById(@PathVariable("id") Long id){
        ResponseEntity.noContent();
        // gets value from service
        Doctor doctor= doctorService.findDoctorbyId(id);
        //transform from entity to DTO( data transfer object)
        DoctorDTO doctorDTO= doctorTransformer.transformReversed(doctor);
        // put doctorDTO into the response entity
      return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable("id") Long id){
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        //construct the requered entity object
        Doctor doctor= doctorTransformer.transform(doctorDTO);
        // asssign saved entity to new object
        Doctor savedoctor= doctorService.saveDoctor(doctor);
        //transform from entity to DTO
        DoctorDTO saveDoctorDTO= doctorTransformer.transformReversed(savedoctor);
        //put doctorDTO into the response entity
        return ResponseEntity.ok(saveDoctorDTO);
    }

    @PutMapping
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO){
        //construct the requered entity object
        Doctor doctor= doctorTransformer.transform(doctorDTO);
        // asssign saved entity to new object
        Doctor savedoctor= doctorService.saveDoctor(doctor);
        //transform from entity to DTO
        DoctorDTO saveDoctorDTO= doctorTransformer.transformReversed(savedoctor);
        //put doctorDTO into the response entity
        return ResponseEntity.ok(saveDoctorDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getByFirstName(@RequestParam(value = "first-name") String firstName){
        List<Doctor> doctors= doctorService.findByFirstName(firstName);
        //stream version
        //List<DoctorDTO> doctorDTOS= doctors.stream().map(DoctorTransformer::transformReversed).collect(Collectors.toList());
        List<DoctorDTO> doctorDTOS= new ArrayList<>();
        //enhanced for version
        for(Doctor d: doctors){
            doctorDTOS.add(doctorTransformer.transformReversed(d));
        }
        return  ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<PageableDoctorResponseDTO> getDoctorsPageable(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
        List<Doctor> doctors= doctorService.findAllDoctorsPageable(page,size);
        List<DoctorDTO> doctorDTOS= doctors.stream().map(doctorTransformer::transformReversed).collect(Collectors.toList());
        PageableDoctorResponseDTO responseDTO= new PageableDoctorResponseDTO();
        responseDTO.setDoctors(doctorDTOS);
        responseDTO.setPage(page);
        responseDTO.setSize(size);
        responseDTO.setTotal(doctorService.countDoctors());
        return ResponseEntity.ok(responseDTO);
    }



}
