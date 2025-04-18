package queries;

public class CustomerQueries {
    public static final String CREATE_CUSTOMER_TABLE = 
        "CREATE TABLE IF NOT EXISTS customers (" +
        "id INT AUTO_INCREMENT PRIMARY KEY, " +
        "first_name VARCHAR(30), " +
        "last_name VARCHAR(30), " +
        "email VARCHAR(50) UNIQUE, " +
        "password VARCHAR(255))";

    public static final String INSERT_CUSTOMER = 
        "INSERT INTO customers (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

    public static final String GET_CUSTOMER_WITH_EMAIL = 
        "SELECT * FROM customers WHERE email = ?";

    public static final String GET_ALL_CUSTOMERS = 
        "SELECT * FROM customers";

    public static final String GET_CUSTOMER_WITH_ID = 
        "SELECT * FROM customers WHERE id = ?";
}
