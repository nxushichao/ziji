package com.wechat.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Io {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		StringBuilder str =new StringBuilder();
		try {
			// 创建字节输入流
			fis = new FileInputStream("src/main/resources/public/zd.xml");
			
			// 创建字节输出流
			fos = new FileOutputStream("classpath");
			// 创建字节缓存输入流
			bis = new BufferedInputStream(fis);
			// 创建字节缓存输出流
			bos = new BufferedOutputStream(fos);

			byte[] b = new byte[1024];
			int hasRead = 0;
			// 循环从缓存流中读取数据
			while ((hasRead = bis.read(b)) > 0) {
				// 向缓存流中写入数据，读取多少写入多少
				System.out.println(b);
				bos.write(b, 0, hasRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bis.close();
			bos.close();
		}

	}
}
