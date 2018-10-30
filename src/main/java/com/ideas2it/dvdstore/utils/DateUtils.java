package com.ideas2it.dvdstore.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.ideas2it.dvdstore.common.Constants;

public class DateUtils {
    
    /** 
     * Used to calculate dvd available days 
     *
     * @param date
     *        Used to get the date of dvd
     */
    public static String calculateDays(LocalDate date) {
        StringBuilder days = new StringBuilder();
        LocalDate todayDate = LocalDate.now();
        Period period = Period.between(date, todayDate);
        days.append(period.getMonths()).append(Constants.LABEL_MONTHS)
        .append(period.getDays()).append(Constants.LABEL_DAYS);
        return days.toString();
    }
    
    /**
     * Used to check the date in future
     */
    public static Boolean checkDateInFuture(LocalDate date) {
       return (date.compareTo(LocalDate.now()) < 0 ||
           date.compareTo(LocalDate.now()) == 0);
    }
    
    /**
     * Used to check date is equal
     */
    public static Boolean checkDateEqual(String date) {
       return (LocalDate.parse(date).compareTo(LocalDate.now()) == 0);
    }
    
}
