package com.atguigu.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 测试 i18n国际化 的步骤
 */
public class I18nDemo {
    public static void main(String[] args) {
        //获取系统默认的语言--地区信息
        Locale localeDefault = Locale.getDefault();
        System.out.println(localeDefault);

        //遍历 Locale中提供的 常量的Locale对象
        /*for (Locale availableLocale : Locale.getAvailableLocales()) {
            System.out.println(availableLocale);
        }*/

        //获取中文 常量的 Locale对象
        System.out.println(Locale.CHINA);
        //获取英文,美国常量的 Locale对象
        System.out.println(Locale.US);

        testI18n();

    }

    /**
     * 读取 Properties配置文件
     */
    public static void testI18n() {
        //1.获取需要的Locale对象
        Locale locale = Locale.US;

        //2.通过ResourceBundle.getBundle()和指定的baseName 和Locale对象,读取响应的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);

        //3.输出bundle中读取的信息. 类似 Properties类的get()方法
        System.out.println("username=" + bundle.getString("username"));
        System.out.println("password=" + bundle.getString("password"));
        System.out.println("Sex=" + bundle.getString("sex"));

    }
}
