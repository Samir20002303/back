package fst.examen.controller;

import fst.examen.model.Departement;
import fst.examen.model.Employe;
import fst.examen.repository.DepartementRepository;
import fst.examen.repository.EmployeRepository;
import fst.examen.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    EmployeService employeService;

    @Autowired
    DepartementRepository departementRepository;

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }


    @PostMapping
    public String addEmployeWithPhoto(
            @RequestParam String nom,
            @RequestParam String email,
            @RequestParam String dateNaissance,
            @RequestParam Long departementId,
            @RequestParam("photo") MultipartFile photo
            ) throws IllegalStateException, IOException {


        LocalDate date = LocalDate.parse(dateNaissance);
        String directory = "src/main/resources/static/images/"+nom+".png";
        photo.transferTo(Path.of(directory));
        String imageUrl = "http://localhost:8080/images/" + nom;

        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));

        Employe employe = new Employe();
        employe.setNom(nom);
        employe.setEmail(email);
        employe.setDateNaissance(date);
        employe.setDepartement(departement);
        employe.setPhotoPath(imageUrl);

        employeService.addEmploye(employe);

        return "Employé ajouté avec la photo!";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeById(@PathVariable Long id) {
        boolean isDeleted = employeService.deleteEmployeById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Employé supprimé avec succès.");
        } else {
            return ResponseEntity.status(404).body("Employé non trouvé.");
        }
    }

    @DeleteMapping("/age/{age}")
    public ResponseEntity<String> deleteEmployesByAge(@PathVariable int age) {
        int deletedCount = employeService.deleteEmployesByAge(age);

        if (deletedCount > 0) {
            return ResponseEntity.ok(deletedCount + " employés de plus de " + age + " ans ont été supprimés.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun employé de plus de " + age + " ans trouvé.");
        }
    }

    @PutMapping()
    public Employe updateEmploye(@RequestBody Employe employe){
        return employeService.updateEmploye(employe);
    }


}