package fst.examen.repository;

import fst.examen.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> findByDepartementId(Long departementId);

    @Query("SELECT e FROM Employe e WHERE e.dateNaissance <= :date")
    List<Employe> findByAgeGreaterThan(@Param("date") LocalDate date);
}
