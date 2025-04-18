package services;

import db.DBConnection;
import java.sql.*;
import java.util.*;
import models.Customer;
import queries.CustomerQueries;

public class CustomerService {

    public static int register(String firstName, String lastName, String email, String password) throws SQLException {
        // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute(CustomerQueries.CREATE_CUSTOMER_TABLE);

            PreparedStatement ps = conn.prepareStatement(CustomerQueries.INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public static Customer login(String email, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(CustomerQueries.GET_CUSTOMER_WITH_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Directly comparing the plaintext password
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {  // Plaintext comparison
                    return new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        email,
                        null
                    );
                }
            }
        }
        return null;
    }

    public static List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(CustomerQueries.GET_ALL_CUSTOMERS);

            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    null
                ));
            }
        }
        return customers;
    }

    public static Customer getById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(CustomerQueries.GET_CUSTOMER_WITH_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    null
                );
            }
        }
        return null;
    }
}
