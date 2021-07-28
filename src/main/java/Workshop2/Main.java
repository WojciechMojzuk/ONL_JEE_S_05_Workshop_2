package Workshop2;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static Workshop2.UserDAO.getData;

public class Main {
    private final static String DB_NAME = "Workshop2";
    private final static String FIND_ALL = "select * from users";
    private final static String ADD_ONE = "insert into users(email, username, password) values (?, ?, ?)";

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
        System.out.println("5. delete one record.");
        System.out.println("Please select an option: 1, 2, 3, 4, 5.");
    }

    public static void SelectOption() {
        int selOpt = getInt();

        switch (selOpt) {
            case 1:
                findAll();
                break;
            case 3:
                insertOne();
                break;

            default:
                System.out.println("No available option selected");
        }
    }

    public static void findAll() {

        try (Connection conn = DBUtil.connect(DB_NAME)) {
           // getData(conn, FIND_ALL, "id", "email", "username", "password");
            User[] allUsers = getData(conn, FIND_ALL, "id", "email", "username", "password");
            printData(allUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertOne() {

        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.insertData(conn, ADD_ONE, UserDAO.askParam());
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
            User user = tab[k];
            System.out.println(user.getID() + ", " + user.getEmail() + ", " + user.getUsername() + ", "
                    + user.getPassword());
        }
    }

    public static String[] askParam() {
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

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}