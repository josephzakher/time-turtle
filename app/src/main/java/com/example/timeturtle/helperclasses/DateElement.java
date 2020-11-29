package com.example.timeturtle.helperclasses;

public class DateElement {

    private String date;
    private boolean selected;

    public DateElement(String date, boolean selected){

        this.date = date;
        this.selected = selected;
    }

    public String getDate(){return date;}
    public boolean getSelected(){return selected;}

    public void setSelected(boolean Selected) {
        this.selected = Selected;
    }
    @Override
    public String toString() {
        return "Date: " + date +
                ", selected: " + selected;
    }
}
