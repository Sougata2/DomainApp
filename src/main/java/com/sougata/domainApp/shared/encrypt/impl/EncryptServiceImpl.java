package com.sougata.domainApp.shared.encrypt.impl;

import com.sougata.domainApp.shared.encrypt.EncryptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
public class EncryptServiceImpl implements EncryptService {
    @Value("${aes.secret.key}")
    private String SECRET_KEY;

    @Override
    public String decrypt(String encryptedPassword) throws Exception {
        byte[] keyBytes = Arrays.copyOf(SECRET_KEY.getBytes(StandardCharsets.UTF_8), 32);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
