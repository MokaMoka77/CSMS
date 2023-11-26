package com.csms.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecretHolder {

    private static final Logger logger = LogManager.getLogger();
    private static final SecretKey SECRET_KEY = generateSecret();

    private SecretHolder() {
    }

    private static SecretKey generateSecret() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error at SecretHolder: {}", e.getMessage());
            return null;
        }
    }

    public static String getSecretKeyAsString() {
        return SECRET_KEY != null ?
                Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded()) : null;
    }
}
