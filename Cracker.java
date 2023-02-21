import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;

public class noLoad {
    public static void main(String[] args) throws Exception {
        String shadowFile = "shadow-simple";
        String commonPasswordsFile = "common-passwords.txt";

        // For each user in the shadow file, check if their password matches a common password

        try (BufferedReader reader = new BufferedReader(new FileReader(shadowFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
		String[] parts = line.split(":");
                String username = parts[0];
                //change the way that the line splits the hash and password
		String salt = parts[1];
                String hash = parts[2];
		try (BufferedReader passwordReader = new BufferedReader(new FileReader(commonPasswordsFile))){
			String password = "";
			while((password = passwordReader.readLine()) != null){
				//inclduing that other class to be the hash calculation method.
				
				String hashedPassword = computeHash(salt, password);
				if(hashedPassword.equals(hash)){
					System.out.println(username + ":" + password);
					break;
				}
			}
		}
            }
        }
    } 
}

