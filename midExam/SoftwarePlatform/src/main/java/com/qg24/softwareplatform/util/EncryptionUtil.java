package com.qg24.softwareplatform.util;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PublicKey;

public class EncryptionUtil {

    // 公钥
    private static String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7LomyLVHeCzZJb+NFs0k8z8dWWNhCxNmERnGQ0GK25AOYn0Nf5lr9PwqVCA1g8CEOdx+psXJxvpX2jH44RUC51NkHk+/fS+GnnnhrystpGsSQJfOPdxfu8cZ8TYzkW1nB9IY1CK2qi9nM3YdqTCopu4TjPmBLyW7FPp4+Cx4vNkxj0W6BH0ndwjvc3JvmJPw73igKk2oLti7LiYJwYf74AkIrNAtADTOFBIy/Db/Wf968gqFEVa9m8gnwkunpIr8dAQnd2QZnwHt1EGCzWyY0lPpEmA8K3luxckRrG24T3V6tJJsnwiPZrplMOZt6CY6foyR0ZBUcFtHKwvvJUnsLwIDAQAB";
    // 密钥
    private static String privateKeyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDsuibItUd4LNklv40WzSTzPx1ZY2ELE2YRGcZDQYrbkA5ifQ1/mWv0/CpUIDWDwIQ53H6mxcnG+lfaMfjhFQLnU2QeT799L4aeeeGvKy2kaxJAl8493F+7xxnxNjORbWcH0hjUIraqL2czdh2pMKim7hOM+YEvJbsU+nj4LHi82TGPRboEfSd3CO9zcm+Yk/DveKAqTagu2LsuJgnBh/vgCQis0C0ANM4UEjL8Nv9Z/3ryCoURVr2byCfCS6ekivx0BCd3ZBmfAe3UQYLNbJjSU+kSYDwreW7FyRGsbbhPdXq0kmyfCI9mumUw5m3oJjp+jJHRkFRwW0crC+8lSewvAgMBAAECggEBALG4Ov+5YJNvEALP4Ks6jb/0JSODgUpgYOL7t6WsYgPeDX6RJX4i/zJn0Ztgpuxnrp0F1St9F7PDJAtSMuc/7/Mc3vAGrNrdwDHfjLgGMXjbTVOi5Bn7BQAjRG2bZHGMcaokZ7Fy8jGn7tFqnm/zcNoJqDHQ6tQrfl0MgZ7HPEmBT32YdkyP2TsOsIMyL3JVwxO2SBJpM64lCSih48y0ObHRISlmlP9L4BVUuQCkn7NknjZ6xvqwh8Ecm0fpWjWTTZWtgmeeaAeqFp9jYjprnBmUMawv2N/nMFYPhErC8PqxixjEie0l2ZozlIbK68XMq9Yd0vo80akgBloP6CI2uoECgYEA/GhiNviGumF+6UVecqyvgHCIzvN4jzT/DbNpU0Ql9zuU4iupq1YBlRfS6XiSmjVDMSS8ZXBOS71sqCSuj1BFuATrs0ToGffCt+nqwhq8nEuyeGYReIJHqHeeFtjWRrZvdOrhhytwkxl9oW7AxYNpbAHYm474HwxXukwcCk1VsO8CgYEA8BijNkOGtzQVAJ5RFHkwGfhjAsVDDMsp+JVYhiZC4rkNy1Jhc2ypw49GBgRhXx+VLaRclf4z/NS0Jc0tcf7RDzYJjo5A1I4mym53sM2r+4VNYx9CEXxVI6CvczcpuJy4YeHF5yo9q68TjU42pfhlMqoWKmfe93fkOuUTUOA4+MECgYA3x42k3klP2Qnl3TmhF/504Omq6m1DIJAY5AQrrYzCVT1yufl17rzMyQYIKGglgg3GR3sG48m5vTPM+MZGz5OItjWsWVb9nh4+7Ie/u47g722SY+QKgKf7V+vs2qCH4ftBmEKcz4HCUEjOKx6Yx5C9mK7YucClg+HDPtyXfj2oqwKBgQDrZGxX3jps0X4p3g3GEc6sF48S/01udJ1QnzNsSoTW/fFdPwZWANor5gMUESVZ+n/qgLNZRyG3Jt78FL4Sfm5/CE60WhmLi1bSrx1f1TVhYl7t6I6A0DI0vOxm/FVGLzjClnTN3+cYtIxpxJ3leLPWyVTz/GRooT281Cxs5XhVwQKBgQCbU/umyUVr7zs4YDU4sTJs+jBJBVvs9EiRW7SYNBFNM29t74v2OwOcA6h6Ri/z3GnXd7A/cprVSFIXy2h5TjqTVgSlrIlM+/4PTfE0hqxnruc6PrBgb1MTaflmtZYK3BMtcGbuW8waOruUNAnbgwi3b9PuIjurmjfbotiOjXHMFw==";
    // 公钥加密AES秘钥后的内容Base64编码
    private static String publicEncryptStr = "EHC8NuT/zHeZI0XKQFg/5NVBhpe6q0j+QGc7+Kt9qgQWWHe1XygGgSou9uZBKIT7XDWo58GKN9LbiQh23omWSM2b81FQIPytTSFgfHbZx03U9l4jCoOm7rRiUWRet8ktE8beP2NC22vMNMY013d76qHlK5Jn9eBSodmRONOcLoTf5Ue0nLXMOK1dXV1RJ43iCoQrGqXs0anV1Q2zlAR679Q5Ucrjdny/lBARq13rWAs/o5nChRJBIpTvl9AcDygkiaUcUTSvZsuKRLWLcB4ojjOvCZMto9qD2CCAacOcodyFnI00v6FND7MDgZPGK+6wHT6CyX0Jxqp+VRRXvFSy3w==";

        // message需要加密的实际内容
    public static String Encyotion(String message) throws Exception {
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
        //生成AES秘钥，并Base64编码
        String aesKeyStr = "u/2dLpwGTD0MrLFJdMmMCg==";
//        System.out.println("AES秘钥Base64编码:" + aesKeyStr);
        //用公钥加密AES秘钥
        //将AES加密
        byte[] publicEncrypt = RSAUtil.publicEncrypt(aesKeyStr.getBytes(), publicKey);
        //公钥加密AES秘钥后的内容Base64编码
        //转Base64
        //    公钥加密AES秘钥后的内容Base64编码
//        String publicEncryptStr = "EHC8NuT/zHeZI0XKQFg/5NVBhpe6q0j+QGc7+Kt9qgQWWHe1XygGgSou9uZBKIT7XDWo58GKN9LbiQh23omWSM2b81FQIPytTSFgfHbZx03U9l4jCoOm7rRiUWRet8ktE8beP2NC22vMNMY013d76qHlK5Jn9eBSodmRONOcLoTf5Ue0nLXMOK1dXV1RJ43iCoQrGqXs0anV1Q2zlAR679Q5Ucrjdny/lBARq13rWAs/o5nChRJBIpTvl9AcDygkiaUcUTSvZsuKRLWLcB4ojjOvCZMto9qD2CCAacOcodyFnI00v6FND7MDgZPGK+6wHT6CyX0Jxqp+VRRXvFSy3w==";
//        System.out.println("公钥加密AES秘钥并Base64编码的结果：" + publicEncryptStr);

        //将Base64编码后的AES秘钥转换成SecretKey对象
        SecretKey aesKey = AESUtil.loadKeyAES(aesKeyStr);
        //用AES秘钥加密实际的内容
        byte[] encryptAES = AESUtil.encryptAES(message.getBytes(), aesKey);
        //AES秘钥加密后的内容Base64编码
        String encryptAESStr = AESUtil.byte2Base64(encryptAES);
//        System.out.println("AES秘钥加密实际的内容并Base64编码的结果：" + encryptAESStr);
        return encryptAESStr;
    }
}
