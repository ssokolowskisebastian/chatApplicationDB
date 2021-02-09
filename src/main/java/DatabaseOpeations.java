import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseOpeations {

    DatabaseConnector sendDataToDB = new DatabaseConnector();

    PreparedStatement pst = sendDataToDB.getPreparedStatement();

    PassEncrypting passEncrypting=new PassEncrypting();

    Statement st=null;

    public PassEncrypting getPassEncrypting() {
        return passEncrypting;
    }

    private String tableNewUserSql =
            "CREATE TABLE IF NOT EXISTS ChatUser " +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    "login VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "PRIMARY KEY ( id )) ";


    private String tableChatHistory =
            "CREATE TABLE IF NOT EXISTS ChatHistory " +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    "loginFrom VARCHAR(255), " +
                    "loginTo VARCHAR(255), " +
                    "text VARCHAR(255), " +
                    "PRIMARY KEY ( id ))";

    private String newUserSql = "insert into ChatUser (login,password) values (?,?);";

    private String removeUserSql = "DELETE FROM ChatUser  WHERE login=?;";

    private String chatHistorySql = "insert into ChatHistory (loginFrom,loginTo,text) values (?,?,?);";


    public void insertNewUser(String login, String password) {

        try {
            st=sendDataToDB.getConnection().createStatement();
            st.executeUpdate(tableNewUserSql);

            pst = sendDataToDB.getConnection().prepareStatement(newUserSql);
            pst.setString(1, login);
            pst.setString(2, password);
            pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void removeUser(String login) {

        try {
            st=sendDataToDB.getConnection().createStatement();
            st.executeUpdate(tableNewUserSql);

            pst = sendDataToDB.getConnection().prepareStatement(removeUserSql);
            pst.setString(1, login);
            pst.executeUpdate();




            System.out.println("user removed to ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void insertMessage(String from, String to, String text) {

        try {
            st=sendDataToDB.getConnection().createStatement();
            st.executeUpdate(tableChatHistory);

            pst = sendDataToDB.getConnection().prepareStatement(chatHistorySql);
            pst.setString(1, from);
            pst.setString(2, to);
            pst.setString(3, text);
            pst.executeUpdate();

            System.out.println("msg added to db ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void readUser() throws SQLException {
        String s = "select * from ChatUser";
        st=sendDataToDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(s);

        ArrayList<UserConnection> userConnections = ApplicationState.getInstance().userConnections;

        while (rs.next()) {
            String strLogin = rs.getString("login");
            String strPassword = rs.getString("password");

            System.out.print(strLogin + " ");

            UserConnection userConnection = new UserConnection();
            userConnection.setLogin(strLogin);
            userConnection.setPassword(passEncrypting.passDecrypting(strPassword));
            userConnections.add(userConnection);
        }
    }
}
