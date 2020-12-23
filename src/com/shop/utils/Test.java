package com.shop.utils;

import com.shop.dao.GoodsDaoImpl;
import com.shop.model.Goods;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Test {
	public static void main(String[] args) {
//		try {
//			Properties properties = new Properties();
//			// 使用ClassLoader加载properties配置文件生成对应的输入流
//			InputStream in = Test.class.getClassLoader().getResourceAsStream("db.properties");
//			// 使用properties对象加载输入流
//			properties.load(in);
//			//获取key对应的value值
//			System.out.println(properties.getProperty("user"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


		Connection connection = JDBCUtil.getInstance().getConnection();

		Goods goods = new Goods();
		goods.setName("aaa");
		goods.setDescription("aaa");
		goods.setImg("ssss");
		goods.setCount(55);
		goods.setPrice(55);

		new GoodsDaoImpl().add(goods);
		System.out.println(connection==null);
	}
}
