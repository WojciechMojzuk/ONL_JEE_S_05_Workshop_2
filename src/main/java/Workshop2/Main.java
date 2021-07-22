package Workshop2;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private final static String GET_MOVIES = "select * from movies where rating > (select avg(rating) from movies)";

    public static void main(String[] args) {

        try (Connection conn = DBUtil.connect("cinemas")) {
            DBUtil.printData(conn, GET_MOVIES, "id", "title", "description", "rating");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
