package Workshop2;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class UserDAO {
    public static User[] getData(Connection conn, String query, String... columnNames) throws SQLException {
        User[] tabUser = new User[0];
        try (
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int i = 0;
                User user = new User();

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
                tabUser[tabUser.length - 1] = user;
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return tabUser;
    }

    public static void insertData(Connection conn, String query, String... params) {

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeRecord(Connection conn, String query, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(query);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeAll(Connection conn, String query) {
        try (PreparedStatement statement =
                     conn.prepareStatement(query);) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData(Connection conn, String query, String... params) {

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}