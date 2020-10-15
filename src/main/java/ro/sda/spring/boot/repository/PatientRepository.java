package ro.sda.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.sda.spring.boot.entity.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    /*@Query(value = "SELECT (*) from patient where birth_Day>=:birthDay",nativeQuery = true)
    Patient selectPatientByBirthDayBefore(@Param("birthDay") LocalDate birthDay);*/

    List<Patient> findByBirthDayBefore(LocalDate dateBefore);

    Optional<Patient> findById(Long id);

}
