package pl.wrona.nortwnd.employees.factory;

import org.springframework.stereotype.Component;
import pl.wrona.nortwnd.employees.Employees;
import pl.wrona.nortwnd.employees.responses.EmployeeResponse;

import java.util.function.Function;

@Component
public class EmployeeFactory implements Function<Employees, EmployeeResponse> {

    @Override
    public EmployeeResponse apply(Employees employee) {
        return EmployeeResponse.builder()
                .employeeID(employee.getEmployeeID())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .title(employee.getTitle())
                .birthDate(employee.getBirthDate())
                .city(employee.getCity())
                .build();
    }
}
