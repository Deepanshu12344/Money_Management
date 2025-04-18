package app;

import services.CustomerService;
import models.Customer;
import java.util.Scanner;
import java.util.List;

public class CustomerAPI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Register\n2. Login\n3. List all\n4. Get by ID");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("First name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last name: ");
                    String ln = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String pwd = sc.nextLine();

                    int id = CustomerService.register(fn, ln, email, pwd);
                    System.out.println("Registered with ID: " + id);
                }

                case 2 -> {
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String pwd = sc.nextLine();

                    Customer c = CustomerService.login(email, pwd);
                    System.out.println(c != null ? "Welcome " + c.firstName : "Invalid login");
                }

                case 3 -> {
                    List<Customer> all = CustomerService.getAll();
                    all.forEach(c -> System.out.println(c.id + ": " + c.firstName + " " + c.lastName));
                }

                case 4 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    Customer c = CustomerService.getById(id);
                    if (c != null) System.out.println(c.firstName + " " + c.email);
                    else System.out.println("Not found");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
