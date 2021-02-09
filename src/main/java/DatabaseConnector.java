import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DatabaseConnector {

    private PreparedStatement preparedStatement;

    private Statement st;

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public Statement getStatement() {
        return st;
    }

    public  Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/chat";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);

            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }

}