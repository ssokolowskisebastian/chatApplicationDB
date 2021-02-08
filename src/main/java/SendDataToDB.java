import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class SendDataToDB {
    private String sql = "CREATE TABLE ChatUsers2 " +
                "(id int NOT NULL AUTO_INCREMENT, " +
            " login VARCHAR(255), " +
            " password VARCHAR(255), " +
            " PRIMARY KEY ( id ))";

    private PreparedStatement preparedStatement;




    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
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