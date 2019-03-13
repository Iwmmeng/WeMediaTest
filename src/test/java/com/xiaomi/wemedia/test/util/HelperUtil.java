package com.xiaomi.wemedia.test.util;

import java.util.Random;

/**
 * @author wangmeng
 * @date 19/3/11
 */
public class HelperUtil {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);// [0,62)
            sb.append(str.charAt(number));
        }
        System.out.println("sb:   "+sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        getRandomString(11);
    }
}
