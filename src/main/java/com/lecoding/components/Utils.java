package com.lecoding.components;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午9:34
 */
public class Utils {
    public static List<Date> getNextWeek() {
        List<Date> list = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; ++i) {
            calendar.add(Calendar.DATE, 1);
            list.add(calendar.getTime());
        }
        return list;
    }
}
