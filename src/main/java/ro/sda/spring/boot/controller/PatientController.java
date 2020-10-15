package ro.sda.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.PatientDTO;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.service.PatientService;
import ro.sda.spring.boot.transformer.PatientTransformer;

@RestController
@RequestMapping(path = "/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientTransformer patientTransformer;

    @Autowired
    public PatientController(PatientService patientService,PatientTransformer patientTransformer){
        this.patientService=patientService;
        this.patientTransformer=patientTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientDTO> findPatientById(@PathVariable("id") Long id){
        ResponseEntity.noContent();
        Patient patient= patientService.findPatientById(id);
        PatientDTO patientDTO= patientTransformer.transformReversed(patient);
        return ResponseEntity.ok(patientDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("id") Long id){
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        Patient patient= patientTransformer.transform(patientDTO);
        Patient savePatient =patientService.savePatient(patient);
        PatientDTO savedpatientDTO = patientTransformer.transformReversed(savePatient);
        return ResponseEntity.ok(savedpatientDTO);
    }

    @PutMapping
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO){
        Patient patient= patientTransformer.transform(patientDTO);
        Patient savePatient =patientService.savePatient(patient);
        PatientDTO savedpatientDTO = patientTransformer.transformReversed(savePatient);
        return ResponseEntity.ok(savedpatientDTO);
    }
}
