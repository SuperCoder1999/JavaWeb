package com.atguigu;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * 演示 各种编码之间的转变
 */

public class ChangeEncode {
    public static void main(String[] args) throws IOException {
        String content = "中文字符";
        byte[] bytes = content.getBytes("utf-8");
        //utf-8字符串变成 base64编码的字符串
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newContent = base64Encoder.encode(bytes);
        System.out.println(newContent);

        //再转变回utf-8字符串
        //创建base64解码器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        //base64字符串不能通过getBytes获取字节数组,需要通过base64自己的方法获取
        byte[] newBytes = base64Decoder.decodeBuffer(newContent);
        String str = new String(newBytes, "utf-8");
        System.out.println(str);
    }
}
