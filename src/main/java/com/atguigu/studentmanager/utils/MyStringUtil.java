package com.atguigu.studentmanager.utils;

import java.util.Date;

public class MyStringUtil {

    public static String generateSN(){
        return "S"+new Date().getTime();
    }
}
