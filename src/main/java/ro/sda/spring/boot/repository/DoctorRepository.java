package ro.sda.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.sda.spring.boot.entity.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findByFirstName(String firstName);

    List<Doctor> findByStreetNrGreaterThan(Long streetNr);

    //value is an SQL query
    @Query(value = "SELECT count(*) from doctor where first_name=:firstName", nativeQuery = true)
    Integer countDoctorsByFirstName(@Param("firstName") String firstName);

    //value is HQL query
    @Query(value = "SELECT count(d)from Doctor as d where d.lastName=:lastName", nativeQuery = false)
    Integer countDoctorsByLastName(@Param("lastName") String lastName);

    @Query(value = "SELECT d from Doctor as d left join fetch d.patients where d.id=:id", nativeQuery = false)
    Optional<Doctor> findByIdFull(@Param("id") Long id);
}


