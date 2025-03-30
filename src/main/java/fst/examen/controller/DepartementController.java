package fst.examen.controller;

import fst.examen.model.Departement;
import fst.examen.model.Employe;
import fst.examen.repository.EmployeRepository;
import fst.examen.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping ("/departements")
public class DepartementController {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    DepartementService departementService;

    @GetMapping("/{id}/employes")
    public List<Employe> getEmployeByDepartementId(@PathVariable long id) {
        List<Employe> employes = employeRepository.findByDepartementId(id);
        if (employes.isEmpty()) {
            throw new RuntimeException("Aucun employé trouvé pour le département avec l'ID " + id);
        }
        return employes;
    }

    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }


}
