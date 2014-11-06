/*
Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.

A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.

For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.

Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.

Your task has been made easy, as the encryption key consists of three lower case characters. Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.
*/
import java.io.*;
import java.util.LinkedList;
class Problem59 {
  private static char[] original;
  private static final String FILE_NAME = "p059_cipher.txt";
  public static void readFile() {
    try {
			InputStreamReader in = new FileReader(FILE_NAME);
			int lastCharRead = in.read();
			LinkedList<Character> list = new LinkedList<>();
			int buffer = 0;
			while(lastCharRead != -1) {
				if(lastCharRead  >= '0' && lastCharRead <= '9') {
				  buffer = buffer * 10 + lastCharRead - '0';
				} else {
				  list.addLast((char) buffer);
				  buffer = 0;
				}
				lastCharRead = in.read();
			}
			original = new char[list.size()];
			for(int i = 0; i < original.length; ++i) {
			  original[i] = list.removeFirst();
			}
		} catch (Exception e) {
			throw new RuntimeException("Problem reading from file "+FILE_NAME+ "\n" + e);
		}
  }
  
  private static boolean valid(char c) {
    return c >= 32 && c < 127;
  }
  
  public static char[] decode(char key[]) {
    char[] decoded = new char[original.length];
    boolean stillValid = true;
    int limit = original.length - (original.length % key.length);
    for(int i = 0; stillValid && i < limit; i = i + 3) {
      decoded[i] = (char) (original[i] ^ key[0]);
      decoded[i+1] = (char) (original[i+1] ^ key[1]);
      decoded[i+2] = (char) (original[i+2] ^ key[2]); 
      stillValid =  valid(decoded[i]) && valid(decoded[i+1]) && valid(decoded[i+2]);
    }
    if(original.length % key.length > 0) {
      for(int i = 0; stillValid && i < original.length % key.length; ++i){
        decoded[i+limit] = (char) (original[i+limit] ^ key[i]);
        stillValid = valid(decoded[i+limit]);
      }
    }
    if(stillValid) {
      return decoded;
    } else {
      return null;
    }
  }
  
  public static char[] actualDecode(char[] key) {
    char[] decoded = new char[original.length];
    boolean stillValid = true;
    int limit = original.length - (original.length % key.length);
    for(int i = 0; i < limit; i = i + 3) {
      decoded[i] = (char) (original[i] ^ key[0]);
      decoded[i+1] = (char) (original[i+1] ^ key[1]);
      decoded[i+2] = (char) (original[i+2] ^ key[2]); 
      stillValid =  valid(decoded[i]) && valid(decoded[i+1]) && valid(decoded[i+2]);
    }
    if(original.length % key.length > 0) {
      for(int i = 0; i < original.length % key.length; ++i){
        decoded[i+limit] = (char) (original[i+limit] ^ key[i]);
        stillValid = valid(decoded[i+limit]);
      }
    }
    return decoded;
  }
  public static void printArr(char[] v) {
    for(int i = 0; i < v.length; ++i) {
      System.out.print(v[i] + " ");
    }
    System.out.println();
  }
  
  public static void main(String argv[]) {
    readFile();
    char[] key = {'g', 'o', 'd'};
    char[] decoded = decode(key);
    int sum = 0;
    for(int i = 0; i < decoded.length; ++i) 
      sum = sum + decoded[i];
    System.out.println(sum);
  }
  
  public static void main1(String arg[]) {
    readFile();
    final char LOWER = 'a';
    final char UPPER = 'z';

    for(char first = LOWER; first <= UPPER; first++)
      for(char second = LOWER; second <= UPPER ; second++)
        for(char third = LOWER; third <= UPPER ; third++) {
          char[] key = {first,second,third};
          char[] decoded = decode(key);
          if(decoded != null) {
            System.out.println("key = '" + first + second + third + "'");
            printArr(decoded);
          }
        } 
  }
}
