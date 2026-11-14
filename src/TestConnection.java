
public class TestConnection {

    public static void main(String[] args) {

        if (DBConnection.getConnection() != null) {

            System.out.println("Connection Successful!");

        } else {

            System.out.println("Connection Failed!");
        }
    }
}
