import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DatabaseConnector {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/chat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private PreparedStatement preparedStatement;

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }


    public  Connection getConnection() {
        try {

            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}