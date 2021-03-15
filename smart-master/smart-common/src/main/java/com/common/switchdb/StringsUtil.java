package com.common.switchdb;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 扩展字符串
 */
public class StringsUtil extends StringUtils {
    /**
     * 反转字符内容  解法1
     *
     * @param str
     * @return
     */
    public static String reversal(String str) {
        if (StringUtils.isBlank(str)) return null;
        char [] strArgs = str.toCharArray();
        char temp;
        for (int i = 0, end = str.length() - 1; i <= end; i++, end--) {
            temp = strArgs[i];
            strArgs[i] = strArgs[end];
            strArgs[end] = temp;
        }
        return Arrays.toString(strArgs);
    }


    /**
     * 有空在看看
     * @param str
     * @return
     */
    public static  String reversal3(String str){
        char[] count = str.toCharArray();
        int n = count.length - 1;
        for (int j = (n-1) >> 1; j >= 0; j--) {
            int k = n - j;
            char cj = count[j];
            char ck = count[k];
            count[j] = ck;
            count[k] = cj;
        }
        return Arrays.toString(count);
    }

    public static String reversalV2(String str){
        if (StringUtils.isBlank(str)) return null;
        int length = str.length();
        char[] strArgs1 = new char[str.length()];
        char[] strArgs2 = str.toCharArray();
        for (int i = length-1; i >= 0 ; i--) {
            strArgs1[length-i-1]=strArgs2[i];
        }
        return Arrays.toString(strArgs1);
    }

    public static void main(String[] args) {

        System.out.println( reversal3("李文强nihao啊，hello,word"));
    }

}
