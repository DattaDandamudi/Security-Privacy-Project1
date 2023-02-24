import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class cracker {
    public static void main(String[] args) throws IOException {
        // Read shadow file
        BufferedReader shadowReader = new BufferedReader(new FileReader("shadow"));
        String shadowLine = shadowReader.readLine();
        ArrayList<String> shadowList = new ArrayList<>();
        while (shadowLine != null) {
            shadowList.add(shadowLine);
            shadowLine = shadowReader.readLine();
        }
        shadowReader.close();

        // Read common passwords file
        BufferedReader passReader = new BufferedReader(new FileReader("common-passwords.txt"));
        String passLine = passReader.readLine();
        ArrayList<String> passList = new ArrayList<>();
        while (passLine != null) {
            passList.add(passLine);
            passLine = passReader.readLine();
        }
        passReader.close();

        // Crack passwords
        for (String shadow : shadowList) {
            String[] shadowFields = shadow.split(":");
            String user = shadowFields[0];
            String salt = shadowFields[1].split("\\$")[2];
            String hash = shadowFields[1].split("\\$")[3];
            for (String pass : passList) {
                String cryptPass = MD5Shadow.crypt(pass, "$1$" + salt);
                if (cryptPass.equals("$1$" + salt + "$" + hash)) {
                    System.out.println(user + ":" + pass);
                    break;
                }
            }
        }
    }
}
