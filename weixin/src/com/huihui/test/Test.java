package com.huihui.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {
	public static void main(String[] args) throws IOException {
		Properties pp = new Properties();
		String filePath = "E:\\javaEE´úÂë\\weixin\\files\\token.properties";
		FileInputStream fis = new FileInputStream(filePath);
		FileOutputStream fos = new FileOutputStream(filePath);
		pp.load(fis);
		String myToken = pp.getProperty("token");
		String myExpiresIn = pp.getProperty("expiresIn");
		String time = pp.getProperty("time");
		System.out.println("++++++++++++++++++");
		System.out.println(myToken);
		System.out.println(myExpiresIn);
		System.out.println(time);
		System.out.println("++++++++++++++++++");
	}
}
