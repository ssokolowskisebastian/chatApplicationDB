import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss= new ServerSocket(8818);
        UserListWindow userListWindow=new UserListWindow();
        SendDataToDB sendDataToDB=new SendDataToDB();


        while(true){
            Socket s=ss.accept();
            UserHandler uh=new UserHandler(s);

            uh.start();

        }

        //

    }
}
