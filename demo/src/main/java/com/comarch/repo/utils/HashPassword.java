package com.comarch.repo.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    private static final String SH = "SHA-256";

    public static String getPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SH);
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1,hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while(hexString.length() < 32){
            hexString.insert(0,'0');
        }
        return hexString.toString();
    }
}
