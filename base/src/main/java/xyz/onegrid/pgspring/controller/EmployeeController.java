package xyz.onegrid.pgspring.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.onegrid.pgspring.api.Employee;
import xyz.onegrid.pgspring.exception.EmployeeNotFoundException;
import xyz.onegrid.pgspring.repository.EmployeeRepository;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.getAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.create(newEmployee);
    }

    @GetMapping("/employees/{username}")
    Employee get(@PathVariable String username) {
        return repository.get(username).orElseThrow(
                () -> new EmployeeNotFoundException(username)
        );
    }

    @PutMapping("/employees/{username}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable String username) {
        return repository.update(newEmployee, username);
    }
}
