import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MessageWindow implements MouseListener {

    private UserConnection userConnection;

    private JFrame jMessageFrame;

    private JPanel jMessagePanel;

    private JTextField jMessageField;

    private JTextField getjMessageFieldTip;

    private JButton jMessageButton;

    private JTextArea jMessageArea;

    private HashMap<String,String> messageHistory;

    private int WIDTH=400, HEIGHT=400;

    private String text="type message here";

    public MessageWindow(UserConnection userConnection,String to){

        this.userConnection=userConnection;

        jMessageFrame =new JFrame("message from: "+userConnection.getLogin()+" to " + to);
        jMessageFrame.setBackground(new Color(1, 11, 111));
        jMessageFrame.setSize(WIDTH, HEIGHT);
        jMessageFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMessageFrame.setVisible(true);

        jMessagePanel=new JPanel();
        jMessagePanel.setBackground(new Color(111,151,211));
        jMessagePanel.setBounds(0, 0, WIDTH, HEIGHT);
        jMessagePanel.setLayout(null);

        jMessageField=new JTextField("");
        jMessageField.setBounds(10, HEIGHT-80, WIDTH-100, 30);
        jMessageField.setText(text);
        jMessageField.addMouseListener(this);

        messageHistory=new HashMap<>();


        jMessageButton=new JButton("Send");
        jMessageButton.setBounds(WIDTH-85, HEIGHT-80, 80, 30);
        jMessageButton.addActionListener(this::sendButtonListener);

        jMessageArea=new JTextArea();
        jMessageArea.setBounds(10, 10, WIDTH-100, HEIGHT-120);

        jMessagePanel.add(jMessageArea);
        jMessagePanel.add(jMessageButton);
        jMessagePanel.add(jMessageField);
        jMessageFrame.add(jMessagePanel);



    }



    public void sendButtonListener(ActionEvent e) {

        String message=jMessageField.getText();
        jMessageField.setText("");
        if(!message.equals(text)) {
            //jMessageArea.setText(userConnection.getLogin() + " " + message);
            messageHistory.put(userConnection.getLogin(), message);

            for(Map.Entry<String,String> msgHist: messageHistory.entrySet()){
                jMessageArea.append(msgHist.getKey()+"  "+msgHist.getValue()+"\n");
            }
        }
        jMessageField.setText(text);



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(jMessageField.getText().equals(text))
        jMessageField.setText("");
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

    }
}
