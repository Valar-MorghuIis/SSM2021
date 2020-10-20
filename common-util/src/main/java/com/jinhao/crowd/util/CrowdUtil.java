package com.jinhao.crowd.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 *
 * 尚筹网的通用工具类
 */
public class CrowdUtil {


    /**
     * 对普通字符串进行MD5加密
     * @param source
     * @return
     */
    public static String md5(String source){

        // 判断输入的字符串是否合法
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_ILLEGAL);
        }

        try {

            // 确定算法
            String algorithm = "MD5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 将字符串转换成字节数组
            byte[] input = source.getBytes();

            // 进行加密
            byte[] output = messageDigest.digest(input);

            // 将结果转换成bigdecimal
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 将结果转换成16进制字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断请求类型
     * @param request
     * @return true：Ajax请求
     *          false：普通请求
     */
    public static boolean judgeResultType(HttpServletRequest request){

        String acceptHeader = request.getHeader("Accept");
        String requestHeader = request.getHeader("X-Requested-With");

        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (requestHeader != null && requestHeader.equals("XMLHttpRequest"));
    }
}
