package Workshop2;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class UserDAO {
    public static User[] printData(Connection conn, String query, String... columnNames) throws SQLException {
        User[] tabUser = new User[0];
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            System.out.println("Data:");
            while (resultSet.next()) {
                int j = 0;
                int i = 0;
                User user = new User();
                tabUser = new User[0];
                for (String columnName : columnNames) {
                    switch (i) {
                        case 0:
                            user.setId(resultSet.getInt(columnName));
                        case 1:
                            user.setEmail(resultSet.getString(columnName));
                        case 2:
                            user.setUsername(resultSet.getString(columnName));
                        case 3:
                            user.setPassword(resultSet.getString(columnName));
                    }
                    i++;
                }
                User[] newtabUser = Arrays.copyOf(tabUser, tabUser.length + 1);
                tabUser = newtabUser;
                tabUser[j] = user;
                j++;
                for (int k = 0; k < tabUser.length; k++) {
                    System.out.println(user.getID() + ", " + user.getEmail() + ", " + user.getUsername() + ", "
                            + user.getPassword());
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return tabUser;
    }

    public static void insertData(Connection conn, String query, String... params) {

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            int result = 0;
            try (PreparedStatement statement1 = conn.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    statement1.setString(i + 1, params[i]);
                }
                result = statement1.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        String[] param = new String[] {user.getEmail(), user.getUsername(), user.getPassword()};
        return param;
    }

    public static String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }
}