package cn.xiaojii.cashgift.util.gesture;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Wjyyy
 * @date 2018/8/9
 */

public class Md5Util {

    /**
     * first argument is the password need MD5
     * second argument is algorithm
     * third argument is separate symbol
     *
     * @param original
     * @param separator
     * @return
     */

    public String toMd5(String original, String separator) {
        try {
            String result;
            byte[] bytes = original.getBytes();
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(bytes);
            result = toHexString(algorithm.digest(), separator);
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String toHexString(byte[] bytes, String separator) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", 0xFF & b)).append(separator);
        }
        return hexString.toString();
    }
}
