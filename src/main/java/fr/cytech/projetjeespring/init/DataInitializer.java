package fr.cytech.projetjeespring.init;

import fr.cytech.projetjeespring.model.entities.*;
import fr.cytech.projetjeespring.enums.*;
import fr.cytech.projetjeespring.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;
    private final RoleRepository roleRepo;
    private final ProjectRepository projRepo;
    private final AbsenceRepository absRepo;
    private final BonusRepository bonusRepo;
    private final PasswordEncoder encoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (empRepo.count() > 0) return;

            System.out.println(">>> GENERATING TEST DATA...");

            Role adminRole = roleRepo.findById("ROLE_ADMIN").orElseThrow();
            Role hrRole = roleRepo.findById("ROLE_HR").orElseThrow();
            Role mgrRole = roleRepo.findById("ROLE_MANAGER").orElseThrow();
            Role empRole = roleRepo.findById("ROLE_EMPLOYEE").orElseThrow();

            Department it = saveDept("IT Department", "Hardware, Software & Coffee");
            Department hr = saveDept("Human Resources", "People management");
            Department sales = saveDept("Sales", "Revenue generation");
            Department marketing = saveDept("Marketing", "Making things look pretty");

            saveEmp("Alice", "Admin", "admin@cy.fr", "90000", EmployeeType.SENIOR, it, List.of(adminRole));

            Employee itHead = saveEmp("Bob", "Bit", "bob@cy.fr", "85000", EmployeeType.SENIOR, it, List.of(mgrRole, empRole));
            setHead(it, itHead);
            Employee dev1 = saveEmp("Charlie", "Code", "charlie@cy.fr", "45000", EmployeeType.JUNIOR, it, List.of(empRole));
            Employee dev2 = saveEmp("Dave", "Debug", "dave@cy.fr", "52000", EmployeeType.INTERMEDIATE, it, List.of(empRole));
            Employee dev3 = saveEmp("Eve", "Exception", "eve@cy.fr", "60000", EmployeeType.SENIOR, it, List.of(empRole));

            Employee hrHead = saveEmp("Helen", "Human", "helen@cy.fr", "75000", EmployeeType.SENIOR, hr, List.of(hrRole, mgrRole));
            setHead(hr, hrHead);
            saveEmp("Harry", "Hiring", "harry@cy.fr", "40000", EmployeeType.JUNIOR, hr, List.of(hrRole));

            Employee salesHead = saveEmp("Sam", "Seller", "sam@cy.fr", "95000", EmployeeType.SENIOR, sales, List.of(mgrRole));
            setHead(sales, salesHead);
            Employee sale1 = saveEmp("Sarah", "Sold", "sarah@cy.fr", "50000", EmployeeType.INTERMEDIATE, sales, List.of(empRole));
            Employee sale2 = saveEmp("Steve", "Stock", "steve@cy.fr", "48000", EmployeeType.JUNIOR, sales, List.of(empRole));

            Employee mktHead = saveEmp("Mike", "Media", "mike@cy.fr", "70000", EmployeeType.SENIOR, marketing, List.of(mgrRole));
            setHead(marketing, mktHead);

            saveProj("Migration to Java 21", "Updating legacy systems", ProjectState.IN_PROGRESS, it);
            saveProj("Internal Wiki", "Documentation platform", ProjectState.FINISHED, it);
            saveProj("AI Overlord", "Skynet implementation", ProjectState.CANCELED, it);
            saveProj("Recruitment Drive 2025", "Hiring new grads", ProjectState.IN_PROGRESS, hr);
            saveProj("Q4 Sales Push", "Call everyone", ProjectState.IN_PROGRESS, sales);
            saveProj("Rebranding", "New Logo", ProjectState.FINISHED, marketing);

            LocalDate today = LocalDate.now();
            saveAbsence(dev1, today.minusDays(10), today.minusDays(5), "Flu", false, true); // Past
            saveAbsence(dev2, today.minusDays(2), null, "Broken Leg", false, true); // Ongoing
            saveAbsence(sale1, today.plusDays(10), today.plusDays(20), "Honeymoon", true, true); // Future
            saveAbsence(sale2, today.minusDays(50), today.minusDays(48), "Unpaid Leave", true, false); // Unpaid

            saveBonus(dev3, "2000.00", "Critical Bug Fix", today.minusMonths(1));
            saveBonus(sale1, "5000.50", "Top Seller Q3", today.minusMonths(2));
            saveBonus(itHead, "1500.00", "Yearly Performance", today.minusDays(5));

            System.out.println(">>> TEST DATA GENERATED. ALL PASSWORDS ARE 'password'");
        };
    }

    private Department saveDept(String name, String desc) {
        Department d = new Department();
        d.setName(name);
        d.setDescription(desc);
        return deptRepo.save(d);
    }

    private void setHead(Department d, Employee e) {
        d.setHead(e);
        deptRepo.save(d);
    }

    private Employee saveEmp(String first, String last, String email, String salary,
                             EmployeeType type, Department dept, List<Role> roles) {
        Employee e = new Employee();
        e.setFirstName(first);
        e.setLastName(last);
        e.setEmail(email);
        e.setAddress("123 Test St, Paris");
        e.setGender("Test Gender");
        e.setSalary(new BigDecimal(salary));
        e.setType(type);
        e.setPassword(encoder.encode("password"));
        e.setDepartment(dept);
        e.getRoles().addAll(roles);
        return empRepo.save(e);
    }

    private void saveProj(String name, String desc, ProjectState state, Department dept) {
        Project p = new Project();
        p.setName(name);
        p.setDescription(desc);
        p.setState(state);
        p.setDepartment(dept);
        projRepo.save(p);
    }

    private void saveAbsence(Employee e, LocalDate start, LocalDate end, String reason, boolean expected, boolean paid) {
        Absence a = new Absence();
        a.setEmployee(e);
        a.setStartDate(start);
        a.setEndDate(end);
        a.setReason(reason);
        a.setExpected(expected);
        a.setPaidLeave(paid);
        absRepo.save(a);
    }

    private void saveBonus(Employee e, String amount, String reason, LocalDate date) {
        Bonus b = new Bonus();
        b.setEmployee(e);
        b.setAmount(new BigDecimal(amount));
        b.setReason(reason);
        b.setAwardDate(date);
        bonusRepo.save(b);
    }
}