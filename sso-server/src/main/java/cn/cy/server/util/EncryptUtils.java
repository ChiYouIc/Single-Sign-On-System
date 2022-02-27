package cn.cy.server.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 开水白菜
 * @description: 加解密工具
 * @create: 2022-02-27 14:57
 **/
public class EncryptUtils {

    private static final byte[] DES_KEY = "12345678".getBytes(StandardCharsets.UTF_8);

    /**
     * Des 加密
     *
     * @param originalText 原文
     * @return 密文
     */
    public static byte[] desEncode(byte[] originalText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 获取加密对象
        Cipher des = Cipher.getInstance("DES");

        // 指定密钥规则
        SecretKeySpec spec = new SecretKeySpec(DES_KEY, "DES");

        // 初始化
        des.init(Cipher.ENCRYPT_MODE, spec);

        // 加密
        return des.doFinal(originalText);
    }

    /**
     * DES 解密
     *
     * @param ciphertext 密文
     * @return
     */
    public static byte[] desDecode(byte[] ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 获取加密对象
        Cipher des = Cipher.getInstance("DES");

        // 指定密钥规则
        SecretKeySpec spec = new SecretKeySpec(DES_KEY, "DES");

        // 初始化
        des.init(Cipher.DECRYPT_MODE, spec);

        // 解密
        return des.doFinal(ciphertext);
    }
}
