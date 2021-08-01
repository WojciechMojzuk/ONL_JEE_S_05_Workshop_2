package Workshop2;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static Workshop2.UserDAO.getData;

public class Main {
    private final static String DB_NAME = "Workshop2";
    private final static String FIND_ALL = "select * from users";
    private static final String DELETE_ONE = "delete from users where id = ?";
    private final static String ADD_ONE = "insert into users (email, username, password) values (?, ?, ?)";
    private final static String UPDATE_ONE = "update users set email = ?, username = ?, password = ? where id = ?";
    private final static String DELETE_ALL = "truncate table users";

    public static void main(String[] args) {
        listOptions();
        SelectOption();
    }

    public static void listOptions() {
        System.out.println("Available options:");
        System.out.println("1. findAll,");
        System.out.println("2. deleteAll,");
        System.out.println("3. add one record,");
        System.out.println("4. update one record,");
        System.out.println("5. delete one record,");
        System.out.println("6. break.");
        System.out.println("Please select an option: 1, 2, 3, 4, 5, 6.");
    }

    public static void SelectOption() {
        int selOpt = getInt();

        switch (selOpt) {
            case 1:
                findAll();
                listOptions();
                SelectOption();
            case 2:
                findAll();
                deleteAll();
                findAll();
                listOptions();
                SelectOption();
            case 3:
                findAll();
                insertOne();
                findAll();
                listOptions();
                SelectOption();
            case 4:
                findAll();
                updateOne();
                findAll();
                listOptions();
                SelectOption();
            case 5:
                findAll();
                deleteOne();
                findAll();
                listOptions();
                SelectOption();
            case 6:
                System.out.println("Break.");
                break;
            default:
                System.out.println("No available option selected");
        }
    }

    public static void findAll() {

        try (Connection conn = DBUtil.connect(DB_NAME)) {
            User[] allUsers = getData(conn, FIND_ALL, "id", "email", "username", "password");
            printData(allUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertOne() {

        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.insertData(conn, ADD_ONE, askParamAdd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOne() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ID?");
        String idString = scan.nextLine();
        int idInt = Integer.parseInt(idString);
        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.removeRecord(conn, DELETE_ONE, idInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll() {
        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.removeAll(conn, DELETE_ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateOne() {

        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.insertData(conn, UPDATE_ONE, askParamUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getInt() {
        Scanner scan = new Scanner(System.in);
        int param;
        while (!scan.hasNextInt()) {
            System.out.println("Waiting for a number.");
            scan.next();
        }
        param = scan.nextInt();
        return param;
    }

    public static void printData(User[] tab) {
        System.out.println("Data:");
        for (int k = 0; k < tab.length; k++) {
            User us = tab[k];
            System.out.println(us.getID() + ", " + us.getEmail() + ", " + us.getUsername() + ", "
                    + us.getPassword() + " ");
        }
    }

    public static String[] askParamAdd() {
        User user = new User();
        Scanner scan = new Scanner(System.in);
        int i = 1;
        user.setId(i);
        System.out.println("Email?");
        user.setEmail(scan.nextLine());
        System.out.println("Username?");
        user.setUsername(scan.nextLine());
        System.out.println("Password?");
        String hashed = hashPassword(scan.nextLine());
        user.setPassword(hashed);
        String[] param = new String[]{user.getEmail(), user.getUsername(), user.getPassword()};
        return param;
    }

    public static String[] askParamUpdate() {
        User user = new User();
        Scanner scan = new Scanner(System.in);
        System.out.println("ID?");
        String id = scan.nextLine();
        int i = Integer.parseInt(id);
        user.setId(i);
        System.out.println("Email?");
        user.setEmail(scan.nextLine());
        System.out.println("Username?");
        user.setUsername(scan.nextLine());
        System.out.println("Password?");
        String hashed = hashPassword(scan.nextLine());
        user.setPassword(hashed);
        String[] param = new String[]{user.getEmail(), user.getUsername(), user.getPassword(), i + ""};
        return param;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}