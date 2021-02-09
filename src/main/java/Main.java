import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        ServerSocket ss= new ServerSocket(8818);
        UserListWindow userListWindow=new UserListWindow();
        DatabaseConnector databaseConnector=new DatabaseConnector();
        DatabaseOpeations databaseOpeations=new DatabaseOpeations();
        databaseOpeations.readUser();



        while(true){
            Socket s=ss.accept();
            UserHandler uh=new UserHandler(s);

            uh.start();

        }

        //

    }
}
