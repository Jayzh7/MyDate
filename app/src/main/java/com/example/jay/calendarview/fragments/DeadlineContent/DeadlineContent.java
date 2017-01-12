package com.example.jay.calendarview.fragments.DeadlineContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2016/12/22.
 */

public class DeadlineContent {

    public static final List<DeadlineItem> ITEMS = new ArrayList<DeadlineItem>();
    public static  int numOfItems = 0;

    public static void addItem(DeadlineItem item){
        ITEMS.add(item);
        numOfItems ++;
    }

    static {
        addItem(new DeadlineItem("模电考试", "2016/12/18", "extremely hard"));
        addItem(new DeadlineItem("Java大作业", "2016/12/25", "very important"));
    }

    public static class DeadlineItem{
        public final String eventName;
        public String memo;
        public final String date;

        public DeadlineItem(String eventName, String date, String memo){
            this.eventName = eventName;
            this.memo = memo;
            this.date = date;
        }

    }
}


