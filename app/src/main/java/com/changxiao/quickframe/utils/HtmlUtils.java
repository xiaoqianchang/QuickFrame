package com.changxiao.quickframe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Html utils used for application.
 * <p/>
 * Created by Chang.Xiao on 2016/7/4.
 *
 * @version 1.0
 */
public class HtmlUtils {

    final static String REG_TITLE       = "<title>(.+)</title>";
    final static String REG_IMG         = "<img.+src=\"(.+)\".+>";
    final static String REG_DESCRIPTION = ".*<meta name=\"description\" content=\"([^>]+)\">.*";

    /**
     * Get title from html
     *
     * @param html
     * @return
     */
    public static String getTitle(String html) {
        String str = null;
        Matcher matcher = Pattern.compile(REG_TITLE).matcher(html);
        if (matcher.find()) {
            str = matcher.group(1);
        }

        return str;
    }

    /**
     * Get img from html
     *
     * @param html
     * @return
     */
    public static String[] getImgUrl(String html) {
        String[] str = null;

        Matcher matcher = Pattern.compile(REG_IMG).matcher(html);
        if (matcher.find()) {
            str = new String[matcher.groupCount()];
            for (int i = 1; i <= matcher.groupCount(); i++)
                str[i - 1] = matcher.group(i);
        }
        return str;
    }

    /**
     * Get description from html
     *
     * @param html
     * @return
     */
    public static String getDescription(String html) {
        String str = null;

        Matcher matcher = Pattern.compile(REG_DESCRIPTION).matcher(html);
        if (matcher.find()) {
            str = matcher.group(1);
        }
        return str;
    }
}

