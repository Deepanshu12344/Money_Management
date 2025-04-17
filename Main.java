import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Create users
        dao.createUser(new User("Alice", "alice@example.com"));
        dao.createUser(new User("Bob", "bob@example.com"));

        // Read users
        List<User> users = dao.getAllUsers();
        users.forEach(System.out::println);

        // Update user
        dao.updateUser(1, "Alice Smith", "alice.smith@example.com");

        // Delete user
        dao.deleteUser(2);

        // Read again
        users = dao.getAllUsers();
        users.forEach(System.out::println);
    }
}
