import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "tribbiani@12";

        // Query name to run (change to the desired query name like "Insert", "CreateTable", etc.)
        String queryToRun = "Insert";  // You can change this to "CreateTable", "UseDatabase", etc.

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");

            // Create a Statement object to execute queries
            Statement stmt = conn.createStatement();

            // Read the SQL queries from the file
            String sqlQueries = new String(Files.readAllBytes(Paths.get("src/queries/queries.sql")));

            // Get the query by its name
            String queryToExecute = getQueryByName(sqlQueries, queryToRun);

            if (queryToExecute != null && !queryToExecute.trim().isEmpty()) {
                // Execute the query
                stmt.executeUpdate(queryToExecute.trim());
                System.out.println("Executed query: " + queryToRun);
            } else {
                System.out.println("Query not found: " + queryToRun);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading SQL file.");
            e.printStackTrace();
        }
    }

    // Function to get the SQL query by its name
    public static String getQueryByName(String sqlQueries, String queryName) {
        // Split the queries based on lines
        String[] queries = sqlQueries.split("\n");

        Map<String, String> queryMap = new HashMap<>();

        // Parse the queries and map their names to the query string
        for (String line : queries) {
            if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String sql = parts[1].trim().replace(";", "");
                    queryMap.put(name, sql);
                }
            }
        }

        // Return the query based on the provided name
        return queryMap.get(queryName);
    }
}
