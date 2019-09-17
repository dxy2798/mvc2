package com.atguigu.mvcapp.test;

import java.io.UnsupportedEncodingException;

public class 是否为中文检测 {

	/**  
	 *  用getBytes(encoding)：返回字符串的一个byte数组  
	 *  当b[0]为  63时，应该是转码错误  
	 *  A、不乱码的汉字字符串：  
	 *  1、encoding用UTF8时，每byte是负数；  
	 *  2、encoding用ISO8859_1时，b[i]全是63。  
	 *  B、乱码的汉字字符串：  
	 *  1、encoding用ISO8859_1时，每byte也是负数；  
	 *  2、encoding用UTF8时，b[i]大部分是63。  
	 *  C、英文字符串  
	 *  1、encoding用ISO8859_1和UTF8时，每byte都大于0；  
	 *  总结：给定一个字符串，用getBytes("iso8859_1")  
	 *  1、如果b[i]有63，不用转码；  A-2  
	 *  2、如果b[i]全大于0，那么为英文字符串，不用转码；  B-1  
	 *  3、如果b[i]有小于0的，那么已经乱码，要转码。  C-1  
	 */ 
	private static String toUTF8(String str) {
		if (str == null)
			return null;
		String retStr = str;
		byte b[];
		try {
			b = str.getBytes("ISO8859_1");
			for (int i = 0; i < b.length; i++) {
				byte b1 = b[i];
				if (b1 == 63)
					break; // 1
				else if (b1 > 0)
					continue;// 2
				else if (b1 < 0) { // 不可能为0，0为字符串结束符
					// 小于0乱码
					retStr = new String(b, "UTF8");
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return retStr;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] a = new byte[] { -61, -90, -62, -100, -62, -128, -61, -92, -62,
				-69, -62, -93, -61, -89, -62, -96, -62, -127 };

		String b = new String(a, "utf-8");
		System.out.println(b);
		System.out.println(b + " 转换 " + toUTF8(b));

		String c = new String(b.getBytes("ISO8859-1"), "utf-8");
		System.out.println(c);
		System.out.println(c + " 转换 " + toUTF8(c));

		String d = "æä»£ç ";
		System.out.println(d + " 转换 " + toUTF8(d));

		printArray(d.getBytes());

		String e = "最代码网";
		System.out.println(e + " 转换 " + toUTF8(e));

		printArray(e.getBytes());

		String f = "《》";
		System.out.println(f + " 转换 " + toUTF8(f));

		String g = "￥";
		System.out.println(g + " 转换 " + toUTF8(g));

		String h = "abcedf1234<>d?";
		System.out.println(h + " 转换 " + toUTF8(h));

		String i = "やめて";

		System.out.println(i + " 转换 " + toUTF8(i));

		String j = new String(e.getBytes("utf-8"), "iso8859-1");
		System.out.println(j);
		printArray(j.getBytes());

	}

	public static void printArray(byte[] bs) {
		System.out.println("----");
		for (byte _bs : bs) {
			System.out.print(_bs + ",");
		}
		System.out.println("\n----");
	}
}