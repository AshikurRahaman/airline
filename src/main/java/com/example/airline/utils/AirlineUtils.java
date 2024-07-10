package com.example.airline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class AirlineUtils {
    public static String convertLocalDate(LocalDate localDate){
        if(localDate==null){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedDateStr = localDate.format(dateTimeFormatter);

        return formattedDateStr;
    }

    public static String convertDate(Date date) {
        if(date==null){
            return null;
        }
        String pattern = "dd-MMM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date);
        return formattedDate;
    }

    public static String getTraceId(){
        return UUID.randomUUID().toString();
    }
}
