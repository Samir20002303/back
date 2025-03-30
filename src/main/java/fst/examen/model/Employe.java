package fst.examen.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String email;

    LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    Departement departement;
    String photoPath;


//    int getAge() {
//        if (dateNaissance != null) {
//            return Period.between(dateNaissance, LocalDate.now()).getYears();
//        }
//        return 0;
//    }
}
