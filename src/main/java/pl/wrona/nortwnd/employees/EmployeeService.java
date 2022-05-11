package pl.wrona.nortwnd.employees;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.wrona.nortwnd.employees.factory.EmployeeFactory;
import pl.wrona.nortwnd.employees.responses.EmployeeResponse;
import pl.wrona.nortwnd.employees.responses.EmployeesResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    public static final int PAGE_SIZE = 5;

    private final EmployeeRepository employeeRepository;

    public EmployeesResponse getEmployees(int page) {
        EmployeeFactory employeeFactory = new EmployeeFactory();

        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by("lastName"));

        List<EmployeeResponse> employees = employeeRepository.findAll(pageRequest).stream()
                .map(employeeFactory)
                .collect(Collectors.toList());

        return EmployeesResponse.builder()
                .employeeResponses(employees)
                .page(page)
                .total(employeeRepository.count() / PAGE_SIZE)
                .build();
    }
}
