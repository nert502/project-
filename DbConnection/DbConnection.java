import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/java_project_library";
        String user = "root";
        String password = "";  

        try {
             Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Σύνδεση επιτυχής!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Πρόβλημα σύνδεσης:");
            e.printStackTrace();
        }
    }
}

