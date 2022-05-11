package pl.wrona.nortwnd.employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wrona.nortwnd.employees.responses.EmployeesResponse;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public EmployeesResponse getEmployees(@RequestParam(defaultValue = "0") int page) {
        return employeeService.getEmployees(page);
    }
}
