package project.projectjava.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.projectjava.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
