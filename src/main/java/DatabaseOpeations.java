import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseOpeations {

    private DatabaseConnector sendDataToDB = new DatabaseConnector();

    private PreparedStatement preparedStatement = sendDataToDB.getPreparedStatement();

    PassEncrypting passEncrypting = new PassEncrypting();

    Statement statement = null;

    public PassEncrypting getPassEncrypting() {
        return passEncrypting;
    }

    private static final String TABLE_NEW_USER_SQL =
            "CREATE TABLE IF NOT EXISTS ChatUser" +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    "login VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "online BOOLEAN,"+
                    "PRIMARY KEY ( id )) ";


    private static final String TABLE_CHAT_HISTORY =
            "CREATE TABLE IF NOT EXISTS ChatHistory " +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    "loginFrom VARCHAR(255), " +
                    "loginTo VARCHAR(255), " +
                    "text VARCHAR(255), " +
                    "PRIMARY KEY ( id ))";

    private static final String NEW_USER_SQL = "insert into ChatUser (login,password) values (?,?);";

    private static final String REMOVE_USER_SQL = "DELETE FROM ChatUser  WHERE login=?;";

    private static final String CHAT_HISTORY_SQL = "insert into ChatHistory (loginFrom,loginTo,text) values (?,?,?);";

    public void createTableNewUser(){
        try {
            statement =sendDataToDB.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(TABLE_NEW_USER_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void insertNewUser(String login, String password) {

        try {
            statement =sendDataToDB.getConnection().createStatement();
            statement.executeUpdate(TABLE_NEW_USER_SQL);

            preparedStatement = sendDataToDB.getConnection().prepareStatement(NEW_USER_SQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void removeUser(String login) {

        try {
            statement =sendDataToDB.getConnection().createStatement();
            statement.executeUpdate(TABLE_NEW_USER_SQL);

            preparedStatement = sendDataToDB.getConnection().prepareStatement(REMOVE_USER_SQL);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();




            System.out.println("user removed to ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void insertMessage(String from, String to, String text) {

        try {
            statement =sendDataToDB.getConnection().createStatement();
            statement.executeUpdate(TABLE_CHAT_HISTORY);

            preparedStatement = sendDataToDB.getConnection().prepareStatement(CHAT_HISTORY_SQL);
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();

            System.out.println("msg added to db ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void readUser() throws SQLException {
        String s = "select * from ChatUser";
        statement =sendDataToDB.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(s);

        ArrayList<UserConnection> userConnections = ApplicationState.getInstance().getUsers();

        while (rs.next()) {
            String strLogin = rs.getString("login");
            String strPassword = rs.getString("password");

            System.out.print(strLogin + " ");

            UserConnection userConnection = new UserConnection();
            userConnection.setLogin(strLogin);
            userConnection.setPassword(strPassword);
            userConnections.add(userConnection);
        }
    }
}
