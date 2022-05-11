package pl.wrona.nortwnd.employees.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class EmployeeResponse {

    private Long employeeID;

    private String firstName;

    private String lastName;

    private String title;

    private LocalDateTime birthDate;

    private String city;
}
