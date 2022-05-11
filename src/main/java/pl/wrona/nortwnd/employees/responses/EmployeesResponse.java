package pl.wrona.nortwnd.employees.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeesResponse {

    private List<EmployeeResponse> employeeResponses;
    private long page;
    private long total;
    private long size;
}
