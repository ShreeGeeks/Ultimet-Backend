package com.ultimet.user.utils;

public class Utils {

    public static Double minuteToHour(Double minutes) {
        double hours = minutes / 60;
        hours = (double) Math.round(hours * 10) / 10;
        return hours;
    }
}
