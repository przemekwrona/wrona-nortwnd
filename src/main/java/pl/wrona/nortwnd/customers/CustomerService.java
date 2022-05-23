package pl.wrona.nortwnd.customers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers(long page) {
        try {
            return customerRepository.findAllCustomers(page);
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }

        return List.of();
    }

    public Customer findCustomerById(String customerId) {
        try {
            return customerRepository.findCustomerById(customerId);
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }

        return null;
    }

    public void createCustomer(Customer customer) {
        try {
            customerRepository.insert(customer);
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    public void deleteCustomer(String customerId) {
        try {
            customerRepository.delete(customerId);
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    public void updateCustomer(String customerId, Customer customer) {
        try {
            customerRepository.update(customerId, customer);
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

}
