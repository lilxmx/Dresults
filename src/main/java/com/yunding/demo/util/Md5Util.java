package com.yunding.demo.util;

import java.security.MessageDigest;

/**
 * @Author: Cui
 * @Date: 2020/3/8
 * @Description:
 */
public class Md5Util {
//声明字符串数组
    private static final String hexDigIts[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };



    /** MD5加密 */
    public static String MD5Encode(String origin) {
        String charsetname = "utf-8";
        String resultString = null;
        try {
            resultString = new String(origin);
            //获取MessageDigest对象，getInstance系列静态函数来进行实例化和初始化
            //MessageDigest 对象通过使用 update 方法处理数据。不论什么时候都能够调用 reset 方法重置摘要。一旦全部须要更新的数据都已经被更新了，应该调用 digest 方法之中的一个完毕哈希计算并返回结果。
            //
            //对于给定数量的更新数据，digest 方法仅仅能被调用一次。digest 方法被调用后，MessageDigest  对象被又一次设置成其初始状态。
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                //这里可能是因为担心不同系统有一些字符集不支持，所以采用系统默认的字符集，一层层的函数查找会自动定位系统支持的字符集
                //String.getBytes();返回字符串的bytes表示形式，可以按照默认或指定的字符集
                /**
                  *@TODO
                 * 这里我想指定某一个字符集，
                 */
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {

                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception e) {
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5Encode("123456"));
    }
}
