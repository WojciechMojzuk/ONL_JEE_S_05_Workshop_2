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


}
