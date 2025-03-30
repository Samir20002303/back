package fst.examen.service;

import fst.examen.model.Employe;
import fst.examen.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public boolean deleteEmployeById(Long id) {
        Optional<Employe> employeOptional = employeRepository.findById(id);

        if (employeOptional.isPresent()) {
            Employe employe = employeOptional.get();

            if (employe.getPhotoPath() != null) {
                String filePath = "src/main/resources/static/images/" + employe.getNom() + ".png";
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        System.err.println("Erreur lors de la suppression de l'image : " + filePath);
                    }
                }
            }

                employeRepository.deleteById(id);
                return true;
            }

            return false;
        }

    public int deleteEmployesByAge(int age) {
        LocalDate dateLimite = LocalDate.now().minusYears(age);

        List<Employe> employes = employeRepository.findByAgeGreaterThan(dateLimite);
        int deletedCount = 0;

        for (Employe employe : employes) {
            if (employe.getPhotoPath() != null) {
                String photoPath = "src/main/resources/static/images/" + employe.getPhotoPath();
                File photoFile = new File(photoPath);
                if (photoFile.exists()) {
                    photoFile.delete();
                }
            }
            employeRepository.delete(employe);
            deletedCount++;
        }

        return deletedCount;
    }


    public Employe updateEmploye(Employe employe) {
        return  employeRepository.save(employe);
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}
