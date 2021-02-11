import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassEncrypting {

    String encryptedPass=null;

    public String passEncrypting(String pass) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(pass.getBytes(), 0, pass.length());
            encryptedPass = new BigInteger(messageDigest.digest()).toString();

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPass;
    }


}
