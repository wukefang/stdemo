package com.st.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TraceIdUtils {


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static String templateStr = "1234567890";

    private static Random random = new Random();

    public static String randomStr(Integer n){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(templateStr.charAt(random.nextInt(10)));
        }
        return stringBuilder.toString();
    }

    public static String get(){
        if(threadLocal.get()!=null){
            return threadLocal.get();
        }
        String traceId = simpleDateFormat.format(new Date()) + randomStr(3);
        threadLocal.set(traceId);
        return threadLocal.get();
    }

    public static void set(){
        String traceId = simpleDateFormat.format(new Date()) + randomStr(3);
        threadLocal.set(traceId);
    }

    public static void clear(){
        threadLocal.remove();
    }
}
