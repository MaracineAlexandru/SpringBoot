package ro.sda.spring.boot.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sda.spring.boot.dto.PatientDTO;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.repository.DoctorRepository;


import java.util.Optional;

@Component
public class PatientTransformer {

    @Autowired
    DoctorRepository doctorRepository;

    public  Patient transform(PatientDTO patientDTO){
        Patient patient= new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        if(patientDTO.getDoctorId() != null){
            Optional<Doctor> optionalDoctor= doctorRepository.findById(patientDTO.getDoctorId());
            if(optionalDoctor.isPresent()){
                patient.setDoctor(optionalDoctor.get());
            }
        }
        return patient;
    }


    public PatientDTO transformReversed(Patient patient){
        PatientDTO patientDTO= new PatientDTO();
        BeanUtils.copyProperties(patient,patientDTO);
        if(patient.getDoctor() != null && patient.getDoctor().getId() != null){
            patientDTO.setDoctorId(patient.getDoctor().getId());
        }
        return patientDTO;
    }
}
