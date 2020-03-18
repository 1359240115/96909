package com.yu.util;


import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

//用来获取当前的日期
public class TimeTool {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//用指定的格式去格式化日期对象
    static String getDate(){
        Date date = new Date();//构造一个date对象



        String dateStr = sdf.format(date);

        return dateStr;
    }

    static String parseDate(String date){
        Date date2 = new Date();
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date2.getTime());
    }
}
/*
class text{
    public static void main(String[] args) {
        System.out.println(TimeTool.getDate());
        System.out.println(TimeTool.parseDate("2019-10-18"));
    }
}*/

