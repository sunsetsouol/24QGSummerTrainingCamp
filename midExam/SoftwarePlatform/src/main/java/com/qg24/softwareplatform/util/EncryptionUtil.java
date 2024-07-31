package com.qg24.softwareplatform.util;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import static com.qg24.softwareplatform.util.RSAUtil.*;

public class EncryptionUtil {

    // 公钥
    private static String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7LomyLVHeCzZJb+NFs0k8z8dWWNhCxNmERnGQ0GK25AOYn0Nf5lr9PwqVCA1g8CEOdx+psXJxvpX2jH44RUC51NkHk+/fS+GnnnhrystpGsSQJfOPdxfu8cZ8TYzkW1nB9IY1CK2qi9nM3YdqTCopu4TjPmBLyW7FPp4+Cx4vNkxj0W6BH0ndwjvc3JvmJPw73igKk2oLti7LiYJwYf74AkIrNAtADTOFBIy/Db/Wf968gqFEVa9m8gnwkunpIr8dAQnd2QZnwHt1EGCzWyY0lPpEmA8K3luxckRrG24T3V6tJJsnwiPZrplMOZt6CY6foyR0ZBUcFtHKwvvJUnsLwIDAQAB";

        // message需要加密的实际内容
    public static String Encyotion(String message) throws Exception {
        PublicKey publicKey = string2PublicKey(publicKeyStr);
        // 需要加密的实际内容
        // 使用公钥加密数据
        byte[] encryptedMessage = encrypt(message.getBytes(), publicKey);
        String encryptedMessageStr = Base64.getEncoder().encodeToString(encryptedMessage);
        return encryptedMessageStr;
    }
}
