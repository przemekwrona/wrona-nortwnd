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
        long offset = (page - 1) * size;

        String query = "SELECT * FROM Customers";
//        String query = String.format("SELECT * FROM Customers ORDER BY CustomerID OFFSET %s ROWS FETCH NEXT %s ROWS ONLY", offset, size);
//        String query = "SELECT * FROM Customers ORDER BY CustomerID OFFSET " + offset + " ROWS FETCH NEXT " + size + " ROWS ONLY");


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

    public void insert(Customer customer) throws SQLException {

        String query = "INSERT INTO Customers(CustomerID, CompanyName) VALUES(?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getCompanyName());

            statement.executeUpdate();
        }

    }

    public void delete(String customerId) throws SQLException {

        String query = "DELETE FROM Customers WHERE CustomerId = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerId);
            statement.executeUpdate();
        }
    }

    public void update(String customerId, Customer customer) throws SQLException {

        String query = "UPDATE Customers SET CompanyName = ? WHERE CustomerID = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getCompanyName());
            statement.setString(2, customer.getCustomerId());

            statement.executeUpdate();
        }

    }

}
