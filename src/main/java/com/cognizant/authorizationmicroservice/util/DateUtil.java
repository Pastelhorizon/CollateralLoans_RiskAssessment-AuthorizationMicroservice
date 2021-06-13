package com.cognizant.authorizationmicroservice.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class DateUtil {
    
    public Date addMinutes(Date date, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MINUTE,minutes);
        Date newDate = cal.getTime();
        return newDate;
    }
}
