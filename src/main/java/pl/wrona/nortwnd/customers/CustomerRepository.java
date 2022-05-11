package pl.wrona.nortwnd.customers;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final HikariDataSource dataSource;

    public CustomerRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Customer> findAllCustomers(long page) throws SQLException {
        List<Customer> customers = new LinkedList<>();

        long size = 5;

        String query = "SELECT * FROM Customers";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(results.getString("CustomerID"));
                customer.setCompanyName(results.getString("CompanyName"));

                customers.add(customer);
            }
        }

        return customers;
    }

    public Customer findCustomerById(String customerId) throws SQLException {
        String query = "SELECT * FROM Customers WHERE CustomerID = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerId);

            ResultSet results = statement.executeQuery();

            results.next();

            Customer customer = new Customer();
            customer.setCustomerId(results.getString("CustomerID"));
            customer.setCompanyName(results.getString("CompanyName"));

            return customer;
        }

    }

    public void insert(Customer customer) {

        String query = "INSERT INTO Customers(CustomerID, CompanyName) VALUES(?,?)";

    }
}
