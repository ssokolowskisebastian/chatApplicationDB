import java.util.HashMap;
import java.util.Map;

public class MessageWindowProvider {
    private Map<MessageWindowPair, MessageWindow> windowMap=new HashMap<>();

    public MessageWindow getMessageWindow(UserConnection from,String to){

        MessageWindowPair key = new MessageWindowPair(from.getLogin(), to);
        MessageWindowPair keyReversed = new MessageWindowPair(to, from.getLogin());

        if(windowMap.containsKey(key)){
            return windowMap.get(key);
        }else if(windowMap.containsKey(keyReversed)){
            return windowMap.get(keyReversed);
        }
        MessageWindow messageWindow = new MessageWindow(from,to);
        windowMap.put(key,messageWindow);
        return messageWindow;
    }
}
