package com.weimi.wmmess.utils;


import android.util.Base64;

import com.blankj.utilcode.util.StringUtils;


import javax.crypto.Cipher;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;


/**
 * RSA帮助类
 *
 * @author liww
 */
public class RSAEncryptUtils {

    /**
     * 模
     */
    private static String module = "ybhndrU9d3UJzvD8WSS1chQ2ZLcn7Y/ZF0SZFJbdWxkvZYAcICUzfJBF4SecxG+ia9GseNDoQ30q+mSM5Ae1NaAy2bjmR1esS17AyX/TnSvtZIm0ACIVLIM6ShM+ukMj/TEhugarwHXhiHmEd8ZkMrVu4SW2XPgWjX7yPoEKLFs=";

    /**
     * 公钥指数
     */
    private static String exponentString = "AQAB";

    private static String delement = "NXVBNhxh5b6GkukadyVbUJg6sgY39qUgiyIKz4ILt5C9FtEUoxA4zNIPMtQkn4pWKOywIHR8mSYatbDgBa5lPxBemwvu5cMHVIh0sD25AL+jXk29alVOIPVTpZ/0TDgy7jd7psYUIX7EO80TnvJIOaNcGUNo060H9qpo19x2iYE=";

    /**
     * 生成加密后的字节数组
     *
     * @param value        待加密字符串
     * @param keyXmlString 加密字符串
     * @return
     */
    public static ArrayList<byte[]> encryptToByteArrayList(String value, String keyXmlString) {
        try {
//            byte[] modulusBytes = Base64.decodeBase64(module);
//            byte[] exponentBytes = Base64.decodeBase64(exponentString);
            byte[] modulusBytes = Base64.decode(module, Base64.DEFAULT);
            byte[] exponentBytes = Base64.decode(exponentString, Base64.DEFAULT);
            BigInteger modulus = new BigInteger(1, modulusBytes);
            BigInteger exponent = new BigInteger(1, exponentBytes);

            RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = fact.generatePublic(rsaPubKey);

//            Cipher cipher = Cipher.getInstance("RSA");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            int splitLength = 39;
            ArrayList<byte[]> byteArrayList = new ArrayList<byte[]>();
            int i = 0;
            do {
                int Length = ((i + 1) * splitLength) >= value.length() ? (value.length() - i * splitLength) : splitLength;
                byte[] byteArray = cipher.doFinal(value.substring(i * splitLength, Length).getBytes("UTF-8"));
                byteArrayList.add(byteArray);
                i++;
            } while (i * splitLength < value.length());

            return byteArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * RSA加密字符串
     *
     * @param value        需要加密的字符串
     * @param keyXmlString 加密key字符串
     * @return
     */
    public static String encrypt(String value, String keyXmlString) {
        ArrayList<byte[]> byteArrayList = encryptToByteArrayList(value, keyXmlString);
        StringBuilder sb = new StringBuilder();
        for (byte[] byteArray : byteArrayList) {
            sb.append(bytesToHexString(byteArray));
            sb.append(",");
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * RSA加密字符串
     *
     * @param value 需要加密的字符串
     * @return
     */
    public static String encrypt(String value) {
        return encrypt(value, null);
    }

    public static byte[] Dencrypt(byte[] encrypted) {
        try {
//            byte[] expBytes = org.apache.commons.codec.binary.Base64.decodeBase64(delement);
//            byte[] modBytes = org.apache.commons.codec.binary.Base64.decodeBase64(module);

            byte[] expBytes = Base64.decode(delement, Base64.DEFAULT);
            byte[] modBytes = Base64.decode(module, Base64.DEFAULT);


            BigInteger modules = new BigInteger(1, modBytes);
            BigInteger exponent = new BigInteger(1, expBytes);

            KeyFactory factory = KeyFactory.getInstance("RSA");
//            Cipher cipher = Cipher.getInstance("RSA");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules, exponent);

            PrivateKey privKey = factory.generatePrivate(privSpec);
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA解密字节流
     *
     * @param byteArrayList 需要解密字节流泛型
     * @param keyXmlString  加密key字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decrypt(ArrayList<byte[]> byteArrayList, String keyXmlString) throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();
        for (byte[] byteArray : byteArrayList) {
            sb.append(new String(Dencrypt(byteArray), "UTF-8"));
        }
        return sb.toString();
    }

    /**
     * RSA解密字符串
     *
     * @param value        需要解密的字符串
     * @param keyXmlString 加密key字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decrypt(String value, String keyXmlString) throws UnsupportedEncodingException {
        ArrayList<byte[]> byteArrayList = new ArrayList<byte[]>();
        if (!StringUtils.isEmpty(value)) {
            String[] strArray = value.split(",");
            int byteArrayLength = 0;
            byte[] byteArray;
            for (String str : strArray) {
                byteArrayLength = str.length() / 2;
                byteArray = new byte[byteArrayLength];
                for (int i = 0; i < byteArrayLength; i++) {
                    try {
                        byteArray[i] = Integer.valueOf(str.substring(i * 2, (i + 1) * 2), 16).byteValue();
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                byteArrayList.add(byteArray);
            }
        }
        return decrypt(byteArrayList, keyXmlString);
    }

    /**
     * RSA解密字符串
     *
     * @param value 需要解密的字符串
     * @return
     */
    public static String decrypt(String value) {
        try {
            return decrypt(value, null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节数组转换为16进制字符串. 可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src 字节数组
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将16进制字符串转换为数组
     *
     * @param hexString 16进制字符串
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 将字符转换为字节
     *
     * @param c 待转换的字符
     * @return byte 转换后的字节
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
