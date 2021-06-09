package com.example.shop.util;

import java.util.*;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactory {
	private static Logger logger = LoggerFactory.getLogger(CommandFactory.class);

	/** 储存属性文件中的映射 */
	private static Map<String, Command> commands = new HashMap<String, Command>();
	/** 使用ResourceBundle读取的属性文件名 */
	private static final String BUNDLE_NAME = "actionMapping";
	/** 使用getResourceAsStream()方法读取的文件名 */
	private static final String ACTION_CONFIG = "actionMapping.properties";

	static {
		loadFile();
	}

	/**
	 * 载入属性文件
	 */
	public static void loadFile() {
		ResourceBundle rb = null;
		try {// 属性文件的初始化
			rb = ResourceBundle.getBundle(BUNDLE_NAME);
			// 属性设置信息的取得
			Enumeration<String> e = rb.getKeys();
			while (e.hasMoreElements()) {
				String action = (String)e.nextElement();
				Command cmd = (Command)ObjectCreator.createObject(rb.getString(action));
				commands.put(action, cmd);
			}
		} catch (MissingResourceException ex) {
			logger.error(ex.getMessage());
			System.out.println("读取属性文件错误: actionMapping.properties");
			ex.printStackTrace();
			return;
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("Error:" + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 载入属性文件
	 *
	 */
	public static void loadFile2() {
		try {
			Properties properties = new Properties();
			InputStream input = CommandFactory.class.getResourceAsStream(ACTION_CONFIG);
			if (input == null) throw new Exception("未找到属性文件!");
			properties.load(input);
			// 读取配置信息
			Enumeration<?> e = properties.keys();
			while (e.hasMoreElements()) {
				String action = (String)e.nextElement();
				Command cmd = (Command)ObjectCreator.createObject(properties.getProperty(action));
				commands.put(action, cmd);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("Error:" + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Creates a Command instance for a specified action and a
	 * specified set of properties.
	 * @param action can be a string defined command.
	 */
	public static Command createCommand(String action) {
		// action形式如: "/user.do"，转化为"user"
		String act = action.substring(1, action.indexOf('.'));
		if (commands.get(act) == null) {
			throw new IllegalArgumentException("Invalid path:" + action);
		}
		return commands.get(act);
	}

}
