package fst.examen.aspect;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* fst.examen.service.EmployeService.addEmploye(..))")
    public void logBeforeAddEmployee() {
        System.out.println("Tentative d'ajout d'un employé.");
    }

    @Before("execution(* fst.examen.service.EmployeService.deleteEmployeById(..))")
    public void logBeforeDeleteEmployee() {
        System.out.println("Tentative de suppression d'un employé.");
    }

    @Before("execution(* fst.examen.service.EmployeService.updateEmploye(..))")
    public void logBeforeUpdateEmployee() {
        System.out.println("Tentative de mise à jour d'un employé.");
    }

    @AfterReturning(
            value = "execution(* fst.examen.service.EmployeService.addEmploye(..))",
            returning = "result"
    )
    public void logAfterAddEmployee(Object result) {
        if (result != null) {
            System.out.println("Employé ajouté avec succès : " + result);
        } else {
            System.out.println("L'ajout de l'employé a échoué.");
        }
    }

    @AfterReturning(
            value = "execution(* fst.examen.service.EmployeService.updateEmploye(..))",
            returning = "result"
    )
    public void logAfterUpdateEmployee(Object result) {
        if (result != null) {
            System.out.println("Employé mis à jour avec succès : " + result);
        } else {
            System.out.println("La mise à jour de l'employé a échoué.");
        }
    }


    @AfterReturning(
            value = "execution(* fst.examen.service.EmployeService.deleteEmployeById(..))",
            returning = "result"
    )
    public void logAfterDeleteEmployee(Object result) {
        if (result != null) {
            System.out.println("Employé supprimé avec succès : " + result);
        } else {
            System.out.println("La suppression de l'employé a échoué.");
        }
    }

    @AfterThrowing(
            value = "execution(* fst.examen.service.EmployeService.*(..))",
            throwing = "exception"
    )
    public void logAfterException(Exception exception) {
        String methodName = exception.getStackTrace()[0].getMethodName();
        System.out.println("Erreur dans la méthode " + methodName + " : " + exception.getMessage());
    }
}