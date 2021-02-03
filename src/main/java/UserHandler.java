import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class UserHandler extends Thread {


    UserConnection userConnection=new UserConnection();
    UserListWindow userListWindow=new UserListWindow();
    OutputStream os;
    String thisUserLogin;




    @Override
    public void run() {
        try {
            handleUser();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }

    Socket s;
    public UserHandler(Socket s) {
        this.s = s;
    }
    public void setS(Socket s) {
        this.s = s;
    }

    public void handleUser() throws IOException, InterruptedException {
        InputStream is=s.getInputStream();
        os=s.getOutputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String line;
        while((line= br.readLine())!=null) {
            String[] tokens = line.split(" ", 3);
            String cmd = tokens[0];
            String login = tokens[1];
            String password = tokens[2];
            String sendTo = tokens[1];
            String msg = tokens[2];

            if (cmd.equals("msg")) {
                handleMessage(sendTo, msg, os);
            } else if (cmd.equals("new")) {
                handleNewUser(login, password, os);
            } else if (cmd.equals("login")) {
                handleUserLogin(login, password, os);
            } else {
                s.close();
            }
            userListWindow.updateUsersView(ApplicationState.getInstance().getUsers());
            userListWindow.updateUsersOnlineView(ApplicationState.getInstance().getUsersOnline());

        }
    }



    private void handleNewUser(String login, String password, OutputStream os) throws IOException {

        userConnection = ApplicationState.getInstance().getUserLogin(login);

        if(userConnection ==null){
            UserConnection u=new UserConnection(login,password,os);
            u.setOnline(false);
            ApplicationState.getInstance().getUsers().add(u);
            String msg=" added\n";
            msgTerm(msg,login,os);
        }else{
            String msg=" exist\n";
            msgTerm(msg,login,os);
        }
    }

    private void handleUserLogin(String login, String password, OutputStream os) throws IOException {
        UserConnection userConnection =ApplicationState.getInstance().getUserLogin(login);
        if(userConnection ==null){
            String msg=" not exist\n";
            msgTerm(msg,login,os);

        }else if(userConnection.getLogin().equals(login)&& userConnection.getPassword().equals(password)){
            String msg=" logged successfully\n";
            userConnection.setOnline(true);
            msgTerm(msg, login,os);
            thisUserLogin=login;
            UserConnection thisUser=ApplicationState.getInstance().getUserLogin(thisUserLogin);
            //ApplicationState.getInstance().getUserHandlerList().clear();
            ApplicationState.getInstance().getUserList().add(thisUser);

        }else{
            String msg=" wrong credentials\n";
            msgTerm(msg, login,os);
        }


    }



    private void handleMessage(String sendTo, String msg, OutputStream os) throws IOException {
        UserConnection user = ApplicationState.getInstance().getUserLogin(sendTo);
        if(user==null){
            String message=" not exist\n";
            msgTerm(message,sendTo,os);
        }else if(user.getLogin().equals(thisUserLogin)){
            String message=" is a sender\n";
            msgTerm(message,sendTo,os);
        }else{
            String message=" send: "+msg+"\n";
            msgTerm(message,thisUserLogin,os);
        }





    }
    private void msgTerm(String msg, String login,OutputStream outputStream) throws IOException {
        outputStream.write(("user " + login+ " " +msg).getBytes());

    }
}
