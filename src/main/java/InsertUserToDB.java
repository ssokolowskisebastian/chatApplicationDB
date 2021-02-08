import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserToDB {

    SendDataToDB sendDataToDB=new SendDataToDB();

    PreparedStatement pst=sendDataToDB.getPreparedStatement();




    public void insertUser(String login,String password){
        String sql="insert into ChatUsers2 (login,password) values (?,?);";

        try {
            pst=sendDataToDB.getConnection().prepareStatement(sql);
            pst.setString(1,login);
            pst.setString(2, password);
            pst.executeUpdate();

            System.out.println("user added to ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
