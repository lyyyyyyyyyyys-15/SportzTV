package com.sportztv;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class CryptoHelper {
    private static final byte[] KEY = "SportzTV2026!@#$".getBytes();
    private static final byte[] IV = "1234567890abcdef".getBytes();

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decoded = Base64.decode(encrypted, Base64.DEFAULT);
        return new String(cipher.doFinal(decoded));
    }
}
