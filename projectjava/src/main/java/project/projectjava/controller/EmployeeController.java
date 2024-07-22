package project.projectjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.projectjava.Exception.ResourceNotFoundExecption;
import project.projectjava.model.Employee;
import project.projectjava.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    @CrossOrigin(origins = "http://localhost:4200")  // Ajoutez cette ligne
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //creat employee rest api
    @PostMapping("/employees")
    @CrossOrigin(origins = "http://localhost:4200")  // Ajoutez cette ligne
    public Employee createEmployee(@RequestBody Employee employee){  // Ajoutez @RequestBody ici
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("employees not exist with id"+id));
        return ResponseEntity.ok(employee);
    }


    //update employee rest api
    @PutMapping ("/employees/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("employees not exist with id"+id));

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setEmailId(employeeDetails.getEmailId()); // Assurez-vous de mettre à jour emailId

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("employees not exist with id"+id));

        employeeRepository.delete(employee);
        Map<String,Boolean>response=new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
