package com.tencent.java.algorithm.encryptanddecrept;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

/**
 * 加密解密 分为：对称加密和非对称加密，对称加密就是公钥和私钥相同，非对称加密 是公钥公开，私钥自己保留。
 */
public class EncreptTest {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }



    public static final String KEY_MD5 = "MD5";

    public static  String  getMD5Result(String inputStr)
    {
        System.out.println("=======加密前的数据:"+inputStr);
        BigInteger bigInteger=null;

        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("MD5加密后:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }

    public static final String KEY_SHA = "SHA";

    public static  String  getSHAResult(String inputStr)
    {
        BigInteger sha =null;
        System.out.println("=======加密前的数据:"+inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("SHA加密后:" + sha.toString(32));
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }

    //生成密钥对
    public static KeyPair genKeyPair(int keyLength) throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }


}
