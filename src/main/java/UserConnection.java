import java.io.OutputStream;

public class UserConnection {

    private String login;

    private String password;

    private OutputStream outputStream;

    private boolean online=false;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }

    public UserConnection() {
    }

    public UserConnection(String login, String password, OutputStream outputStream) {
        this.login = login;
        this.password = password;
        this.outputStream = outputStream;
    }
}
