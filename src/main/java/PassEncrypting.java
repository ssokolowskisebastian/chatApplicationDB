public class PassEncrypting {

    public String passEncrypting(String pass) {
        char[] chars = pass.toCharArray();
        String passEncrypted = new String();

        for (char c : chars) {
            c += 15;
            passEncrypted+=c;
        }
        return passEncrypted;
    }

    public String passDecrypting(String pass) {
        char[] chars = pass.toCharArray();
        String passDecrypted = new String();

        for (char c : chars) {
            c -= 15;
            passDecrypted+=c;
        }
        return passDecrypted;
    }
}
