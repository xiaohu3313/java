package com.youzan.ad.utils;

import com.youzan.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    /**
     * 获取字符串的md5值
     * @param str
     * @return
     */
    public static String md5(String str){
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    public static Date parseStringDate(String dateStr) throws AdException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
