package com.example.timeturtle.helperclasses;

public class Task {
    private String date;
    private String time;
    private String name;
    private String description;
//    private int iconID;

    public Task(String date, String time, String name, String description){

        this.date = date;
        this.time = time;
        this.name = name;
        this.description = description;
//        this.iconID = iconID;
    }

    public String getDate(){return date;}
    public String getTime(){return time;}
    public String getName(){return name;}
    public String getDescription(){return description;}
//    public int getIconID(){return iconID;}

    @Override
    public String toString() {
        return "Task Date: " + date +
                ", time: " + time +
                ", name: " + name +
                ", description: " + description;
//                ", icon: " + iconID;
    }
}
