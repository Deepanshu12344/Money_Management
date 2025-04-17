import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LoginSignupForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;

    // DB credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/testdb";  // Use your DB
    private final String DB_USER = "root";
    private final String DB_PASS = "tribbiani@12";

    public LoginSignupForm() {
        setTitle("Login / Signup");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null); // center on screen

        // UI components
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        add(loginButton);
        add(signupButton);

        // Button actions
        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> signup());

        setVisible(true);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try (Connection conn = connect()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // You can now open another window or continue the app
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void signup() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try (Connection conn = connect()) {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Signup successful!");

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSignupForm::new);
    }
}
