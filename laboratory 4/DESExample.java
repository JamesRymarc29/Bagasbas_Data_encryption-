import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class DESExample {

    public static void main(String[] args) throws Exception {
        // Generate a DES key
        Key key = generateDESKey();

        // Original plaintext
        String plaintext = "James Rymarc Bagasbas.";

        // Encrypt the plaintext
        byte[] ciphertext = encrypt(plaintext, key);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);

        // Print results
        System.out.println("Original message: " + plaintext);
        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(ciphertext));
        System.out.println("Decrypted message: " + decryptedText);
    }

    // Method to generate a DES key
    public static Key generateDESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // DES keys are 56 bits long
        SecretKey secretKey = keyGen.generateKey();
        return secretKey;
    }

    // Method to encrypt plaintext using DES
    public static byte[] encrypt(String plaintext, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        return cipher.doFinal(plaintextBytes);
    }

    // Method to decrypt ciphertext using DES
    public static String decrypt(byte[] ciphertext, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
