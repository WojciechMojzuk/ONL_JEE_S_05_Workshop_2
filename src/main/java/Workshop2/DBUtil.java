package Workshop2;

import java.sql.*;

public class DBUtil {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/";
    private final static String DB_PARAMS = "?useSSL=false&characterEncoding=utf8";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "coderslab";

    public static Connection connect(String dbName) throws SQLException {
        return DriverManager.getConnection(DB_URL+dbName+DB_PARAMS, DB_USER, DB_PASS);
    }

    public static int insert (Connection conn, String query, String...params){
        int result = 0;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.println(resultSet.getString(columnName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}