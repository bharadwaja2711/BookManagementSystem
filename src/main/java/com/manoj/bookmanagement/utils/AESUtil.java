package com.manoj.bookmanagement.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.util.Base64;

public class AESUtil {

    private static final String AES = "AES";

    public static String encrypt(String data) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data: " + e.getMessage());
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data: " + e.getMessage());
        }
    }

    private static Key generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        return keyGenerator.generateKey();
    }
}
