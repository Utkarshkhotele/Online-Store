package org.example;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR"); // Hide Hibernate logs

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Online Store Menu =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Category");
            System.out.println("3. Add Product");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addUser(sc);
                case 2 -> addCategory(sc);
                case 3 -> addProduct(sc);
                case 4 -> placeOrder(sc);
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        HibernateUtil.shutdown();
    }


    private static void addUser(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        session.persist(user);
        tx.commit();
        session.close();

        System.out.println("✅ User added successfully!");

        //..
    }


    private static void addCategory(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter category name: ");
        String name = sc.nextLine();

        Category category = new Category();
        category.setName(name);

        session.persist(category);
        tx.commit();
        session.close();

        System.out.println("✅ Category added successfully!");
    }


    private static void addProduct(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        System.out.print("Enter category ID: ");
        int catId = sc.nextInt();
        sc.nextLine();

        Category category = session.get(Category.class, catId);
        if (category == null) {
            System.out.println("❌ Category not found!");
            tx.rollback();
            session.close();
            return;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        session.persist(product);
        tx.commit();
        session.close();

        System.out.println("✅ Product added successfully!");
    }

    private static void placeOrder(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        User user = session.get(User.class, userId);
        Product product = session.get(Product.class, productId);

        if (user == null || product == null) {
            System.out.println("❌ User or Product not found!");
            tx.rollback();
            session.close();
            return;
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        session.persist(order);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(qty);
        session.persist(item);

        tx.commit();
        session.close();

        System.out.println("✅ Order placed successfully!");
    }
}

