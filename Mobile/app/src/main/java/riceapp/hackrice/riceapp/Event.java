package riceapp.hackrice.riceapp;

import java.util.Calendar;

/**
 * Created by Qi on 9/24/2017.
 */

public class Event {
    Event(String title, Calendar startTime, Calendar endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String title;
    public Calendar startTime;
    public Calendar endTime;
}
