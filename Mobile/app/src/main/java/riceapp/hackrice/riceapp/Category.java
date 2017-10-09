package riceapp.hackrice.riceapp;

import android.graphics.Color;

/**
 * Created by Admin on 2017/9/24.
 */

public class Category {
    public final static Category CATEGORY_CASUAL = new Category("Casual", "Notification", MainPage.LOW_PRIORITY, MainPage.EVENT_COLOR_1);
    public final static Category CATEGORY_OFFICIAL = new Category("Official", "Alarm", MainPage.LOW_PRIORITY, MainPage.EVENT_COLOR_2);
    public final static Category CATEGORY_DEADLINE = new Category("Deadline", "Notification", MainPage.HIGH_PRIORITY, MainPage.EVENT_COLOR_3);

    private String name;
    private String method;
    private int priority;
    private int color;

    public Category(){}

    private Category(String name, String method, int priority, int color) {
        this.name = name;
        this.method = method;
        this.priority = priority;
        this.color = color;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Category))
            return false;
        if (obj == this)
            return true;

        Category rhs = (Category) obj;
        return name.equals(rhs.name);
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
