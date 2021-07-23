package Workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        String selectedOpt = scan.nextLine();
        int selOpt = Integer.parseInt(selectedOpt);

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
            UserDAO.printData(conn, FIND_ALL, "id", "email", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertOne (){
        
        try (Connection conn = DBUtil.connect(DB_NAME)) {
            UserDAO.insertData(conn, ADD_ONE, UserDAO.askParam());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}