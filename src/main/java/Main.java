import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(8818);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserListWindow userListWindow=new UserListWindow();
        DatabaseConnector databaseConnector=new DatabaseConnector();
        DatabaseOpeations databaseOpeations=new DatabaseOpeations();
        try {
            databaseOpeations.createTableNewUser();
            databaseOpeations.readUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        while(true){
            Socket socket= null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserHandler userHandler=new UserHandler(socket);

            userHandler.start();

        }

        //

    }
}
