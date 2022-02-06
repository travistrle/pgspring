package xyz.onegrid.pgspring.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import xyz.onegrid.pgspring.api.Employee;

@Repository
public class EmployeeRepository {
    private static Map<String, Employee> employeeMap;

    static {
        employeeMap = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = Employee.builder()
                    .username("username_" + i)
                    .firstName("firstName_" + i)
                    .lastName("lastName_" + i)
                    .email("email_" + i)
                    .build();
            employeeMap.put(employee.getUsername(), employee);
        }
    }

    public List<Employee> getAll() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Employee> get(String username) {
        return Optional.ofNullable(employeeMap.get(username));
    }

    public Employee create(Employee employee) {
        if (employee == null ||
                employeeMap.containsKey(employee.getUsername())) {
            return null;
        }
        employeeMap.put(employee.getUsername(), employee);
        return employee;
    }

    public Employee update(Employee employee, String username) {
        if (employee == null ||
                !employeeMap.containsKey(username)) {
            return null;
        }
        employeeMap.put(username, Employee.builder()
                        .username(username)
                        .lastName(employee.getLastName())
                        .firstName(employee.getFirstName())
                        .email(employee.getEmail())
                .build());
        return employee;
    }
}
