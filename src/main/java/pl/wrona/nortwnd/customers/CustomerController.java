package pl.wrona.nortwnd.customers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(@RequestParam("page") long page) {
        return customerService.findAllCustomers(page);
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/customers")
    public void creatCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

}
