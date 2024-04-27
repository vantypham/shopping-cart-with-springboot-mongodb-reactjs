package com.webstore.selenium;

public class Utils {
    private static String MacOS_URL_Driver = "/Users/xhuyen/3_WAA/chromedive/chromedriver-mac-arm64/chromedriver";
    private static String MacOS_URL_Binary = "/Users/xhuyen/3_WAA/chromedive/chrome-headless-shell-mac-arm64/chrome-headless-shell";

    private static String Windows_URL_Driver = "C:\\tmp\\chromedriver-win64\\chromedriver.exe";
    private static String Windows_URL_Binary = "C:\\tmp\\chrome-headless-shell-win64\\chrome-headless-shell.exe";

    public static String getDriverURL() {
        String s = System.getProperty("os.name");
        if (s.startsWith("Mac")) {
            return MacOS_URL_Driver;
        } else {
            return Windows_URL_Driver;
        }
    }
    public static String getBinaryURL() {
        String s = System.getProperty("os.name");
        if (s.startsWith("Mac")) {
            return MacOS_URL_Binary;
        } else {
            return Windows_URL_Binary;
        }
    }
}
