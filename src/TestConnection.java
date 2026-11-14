import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection == null) {
                System.out.println("Connection Failed. See the database error above.");
                return;
            }

            System.out.println("Connection Successful!");
        } catch (SQLException e) {
            System.err.println("Could not close the database connection: " + e.getMessage());
        }
    }
}