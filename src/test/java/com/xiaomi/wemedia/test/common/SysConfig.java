package com.xiaomi.wemedia.test.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;



/**
 * @author wangmeng
 * @date 19/3/6
 */
public class SysConfig {
    private static Properties sysConfig = new Properties();

    private static final Logger LOGGER = LoggerFactory.getLogger(SysConfig.class);

    //读取配置文件
    static {
//        String path = SysConfig.class.getClassLoader().getResource(ConfigConstants.SYSCONFIG_PATH).getPath();
//        System.out.println("path: "+path);
        InputStream inputStream = null;
        try {
            inputStream =  SysConfig.class.getClassLoader().getResourceAsStream(ConfigConstants.SYSCONFIG_PATH);
            sysConfig.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("读取" + ConfigConstants.SYSCONFIG_PATH + "失败", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error("读取" + ConfigConstants.SYSCONFIG_PATH + "失败", e);
            }
        }
    }

    /**
     * 根据属性读取值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return sysConfig.getProperty(key);
    }

    /**
     * 写入值
     *
     * @param key
     * @param value
     */
    public static void setProperty(String key, String value) {

        sysConfig.setProperty(key, value);
    }

    /**
     * 读取指定文件
     *
     * @param filePath
     * @return
     */
    public static String readerFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                StringBuilder stringBuilder = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineTxt);
                }
                return stringBuilder.toString();
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            LOGGER.error("Cannot find the file specified!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading file content!", e);
        }
        return null;
    }

}
