package com.fuyang.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String str) {

        try {
            SimpleDateFormat sdf=null;
            if(str.length()>10)
            {
                sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }else {
                sdf  = new SimpleDateFormat("yyyy-MM-dd");
            }
            return sdf.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
