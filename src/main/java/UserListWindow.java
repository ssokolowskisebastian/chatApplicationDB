import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class UserListWindow implements MouseListener {

    public UserListWindow() {
        JFrame jUsersListFrame = new JFrame("User list");


        jUsersListFrame.getContentPane().setBackground(new Color(222,111,111));
        jUsersListFrame.getContentPane().setLayout(new FlowLayout());

        jUsersListFrame.getContentPane().add(new JScrollPane(userJList));
        jUsersListFrame.getContentPane().add(new JScrollPane(userJListOnline));

        userJList.setBackground(new Color(22, 222, 90));
        userJList.setModel(userListModel);
        userJListOnline.setBackground(new Color(0, 120, 0));
        userJListOnline.setModel(userOnlineListModel);

        jUsersListFrame.setSize(1000, 800);
        jUsersListFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jUsersListFrame.setVisible(true);
        userJListOnline.addMouseListener(this);


    }


    JList<String> userJList = new JList<>();
    JList<String> userJListOnline = new JList<>();

    DefaultListModel<String> userListModel = new DefaultListModel<>();
    DefaultListModel<String> userOnlineListModel = new DefaultListModel<>();

    public void updateUsersView(ArrayList<UserConnection> users){
        userListModel.clear(); // TODO do not clear but just check and add
        //userOnlineListModel.clear();

        for (UserConnection user : users) {
            userListModel.addElement(user.getLogin());
        }
    }
    public void updateUsersOnlineView(ArrayList<UserConnection> usersOnline){

        //userListModel.clear();
        userOnlineListModel.clear();

        for (UserConnection user : usersOnline) {
            userOnlineListModel.addElement(user.getLogin());
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        UserConnection user = ApplicationState.getInstance().getUserLogin(userJListOnline.getSelectedValue());
       UserConnection myself = ApplicationState.getInstance().getUserList().get(0);

        ApplicationState.getInstance().getMessageWindowProvider().getMessageWindow(myself, user.getLogin());


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    };
}
