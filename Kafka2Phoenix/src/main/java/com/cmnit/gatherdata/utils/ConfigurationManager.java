package com.cmnit.gatherdata.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * HF
 * 2020-06-07 16:41
 * 读取配置文件工具类
 */
@Slf4j
public class ConfigurationManager {
  //创建配置对象
  private static Properties prop = new Properties();
  //静态代码块
  static {
    try {
      //通过流读取配置文件
      InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream("cluster.properties");

      if (inputStream == null) {
        inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream("local.properties");
      }

      //加载配置文件
      prop.load(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //获取配置项
  public static String getProperty(String key) {
    return prop.getProperty(key);
  }

  //获取配置项
  public static String getProperty(String key, String defaultValue) {
    return prop.getProperty(key, defaultValue);
  }

  //获取布尔类型的配置项
  public static boolean getBoolean(String key) {
    String value = prop.getProperty(key);
    try {
      return Boolean.valueOf(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
