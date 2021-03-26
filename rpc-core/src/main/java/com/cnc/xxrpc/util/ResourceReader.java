package com.cnc.xxrpc.util;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author tony
 * @desc TODO
 * @createDate 2021/3/26 11:47 上午
 */

public class ResourceReader {

    public static Properties getResource(String name) throws IOException {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream in = classLoader.getResourceAsStream(name);
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new IOException("failed to read resource from file: " + name);
        }
    }
}