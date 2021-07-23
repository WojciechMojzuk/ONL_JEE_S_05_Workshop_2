package Workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDAO {
    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                int j = 0;
                int i = 0;
                User user = new User();
                User[] tabUser = new User[0];
                for (String columnName : columnNames) {
                    // System.out.println(resultSet.getString(columnName));
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
                // System.out.println(user.getID() + ", " + user.getEmail() + ", " + user.getUsername() + ", "
                //       + user.getPassword());
                User[] newtabUser = Arrays.copyOf(tabUser, tabUser.length + 1);
                tabUser = newtabUser;
                tabUser[j] = user;
                j++;
                for (int k = 0; k < tabUser.length; k++) {
                    System.out.println(user.getID() + ", " + user.getEmail() + ", " + user.getUsername() + ", "
                            + user.getPassword());
                    System.out.println(Arrays.toString(tabUser));
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}
