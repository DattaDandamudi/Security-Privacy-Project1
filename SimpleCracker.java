/*
 * Logan DesRochers
 * Janak Datta Dandamudi
 * Dan Watson
 */ 

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SimpleCracker {
    public static void main(String[] args) throws Exception {
        String shadowFile = "shadow-simple";
        String commonPasswordsFile = "common-passwords.txt";

        // Load the common passwords into an array

        String[] commonPasswords = loadCommonPasswords(commonPasswordsFile);

        // For each user in the shadow file, check if their password matches a common password

        try (BufferedReader reader = new BufferedReader(new FileReader(shadowFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];
                String salt = parts[1];
                String hash = parts[2];
                for (String password : commonPasswords) {
                    String hashedPassword = computeHash(salt, password);
                    if (hashedPassword.equals(hash)) {
                        System.out.println(username + ":" + password);
                        break;
                    }
                }
            }
        }
    }

    private static String[] loadCommonPasswords(String filename) throws Exception {

        // Load the common passwords from a file into an array
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().toArray(String[]::new);
        }
    }

    private static String computeHash(String salt, String password) throws Exception {
        // Compute the MD5 hash of the salt and password

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((salt + password).getBytes());
        byte[] digest = md.digest();
        return String.format("%032X", new BigInteger(1, digest));
    }
}

