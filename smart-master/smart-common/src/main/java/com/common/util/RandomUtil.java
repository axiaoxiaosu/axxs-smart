package com.common.util;

/**
 * @author lwq
 */
public class RandomUtil {

    static String[] str = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q",
            "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    static int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};


    public static String generatePassword() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int strMark = (int) (Math.random() * str.length);
            int intMark = (int) (Math.random() * num.length);
            msg.append(str[strMark]).append(num[intMark]);
        }
        return msg.toString();

    }


}
