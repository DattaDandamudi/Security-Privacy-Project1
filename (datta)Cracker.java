import java.math.BigInteger;
import java.io.*;
import java.lang.Object;
import java.security.*;
import java.util.*;
import javax.crypto.*;
public class Cracker {
/* public static String EncryptingHash(String pass, String salt) throws
NoSuchAlgorithmException {
//encrypting the password using salt to get the hash
String message = salt + pass;
MessageDigest msgd = MessageDigest.getInstance("MD5");
//pass byte array of salt + password to digest
msgd.update(message.getBytes());
byte[] digest = msgd.digest();
return toHex(digest);
}*/
//using the given toHex method
public static String toHex(byte[] bytes) {
BigInteger bi = new BigInteger(1, bytes);
return String.format("%0" + (bytes.length << 1) + "X", bi);
}
// main driver method
public static void main(String[] args) throws Exception {
try {
File file1=new File("common-passwords.txt");
File file2=new File("shadow");
BufferedReader buffr1
= new BufferedReader(new FileReader(file1));
// A variable to hold a String of the file data
String string= null;
String password= null;
int count;
while ((password=buffr1.readLine())!=null) {
// Creating an object of BuffferedReader class
BufferedReader buffr2
= new BufferedReader(new FileReader(file2));
while (buffr2.ready()) {
string =buffr2.readLine();
count=0;
String hash_shadow = string.split(":")[1];
String[] password_shadow =
hash_shadow.replace("$",":").split(":"); //replacing $ with :and then splitting
String salt = password_shadow[2];
String shadowHexPwd = password_shadow[3];
//String hash= GetCrypHashpass(password, salt);
//String passWord=buffr1.readLine();
String hash = MD5Shadow.crypt(password, salt);
if(shadowHexPwd.equals(hash) ) {
System.out.println("Common Password Matches for
user - " + string.split(":")[0] + " : " + password);
//count++;
}
}
}
}
catch ( FileNotFoundException e) {
System.out.println("File not found");
}
}
}
