import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/canteen_db";

    private static final String USER = "root";

    public static Connection getConnection() {
        String password = System.getenv("CANTEEN_DB_PASSWORD");
        if (password == null || password.isBlank()) {
            System.err.println(
                    "Database password is not configured. Set CANTEEN_DB_PASSWORD in this IntelliJ run configuration."
            );
            return null;
        }

        try {
            Connection connection = DriverManager.getConnection(
                    URL, USER, password
            );
            System.out.println("Database Connected Successfully!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}
