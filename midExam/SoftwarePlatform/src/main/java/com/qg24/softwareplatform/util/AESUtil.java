package com.qg24.softwareplatform.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    /**
     * 生成AES秘钥，然后Base64编码
     * @return
     * @throws Exception
     */
    public static String genKeyAES() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        String base64Str = byte2Base64(key.getEncoded());
        return base64Str;
    }

    /**
     * 将Base64编码后的AES秘钥转换成SecretKey对象
     * @param base64Key
     * @return
     * @throws Exception
     */
    public static SecretKey loadKeyAES(String base64Key) throws Exception{
        SecretKeySpec key = new SecretKeySpec(base64Key.getBytes("utf-8"), "AES");
        return key;
    }

    /**
     * 字节数组转Base64编码
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64编码转字节数组
     * @param base64Key
     * @return
     */
    public static byte[] base642Byte(String base64Key) {
        return Base64.getDecoder().decode(base64Key);
    }

    /**
     * 加密
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);
    }

    /**
     * 解密
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(source);
    }

}
