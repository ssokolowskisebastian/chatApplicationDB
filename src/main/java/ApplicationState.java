import java.util.ArrayList;

public class ApplicationState {



    ArrayList<UserConnection> userConnections =new ArrayList<>();
    ArrayList<UserConnection> userConnectionOnline=new ArrayList<>();
    ArrayList<UserConnection> userLoginList=new ArrayList<>();

    private static ApplicationState instance;
    private MessageWindowProvider messageWindowProvider=new MessageWindowProvider();

    private ApplicationState() {
    }

    public static ApplicationState getInstance(){
        if (instance == null) {
            instance = new ApplicationState();
        }
        return instance;
    }

    public ArrayList<UserConnection> getUsers() {
        return userConnections;
    }

    public ArrayList<UserConnection> getUserList() {
        return userLoginList;
    }

    public ArrayList<UserConnection> getUsersOnline() {
        for(UserConnection u:userConnections){
            if(u.isOnline() && !userConnectionOnline.contains(u)){
                userConnectionOnline.add(u);
            }
        }
        return userConnectionOnline;
    }
    public UserConnection getUserLogin(String login) {
        for(UserConnection u: userConnections){
            if(u.getLogin().equals(login)){
                return u;
            }
        }
        return null;
    }

    public MessageWindowProvider getMessageWindowProvider() {
        return messageWindowProvider;
    }

    public UserConnection getCurrentUser(String login){
        return  null;
    }


}
