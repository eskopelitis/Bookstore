package edu.psu.bookstore;

public class DateTime {
    private int day;
    private int month;
    private String OGString;
    private int year;
    public void DateTime(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void DateTime(String date) {
        OGString=date;
        String split[] = date.split("-", 0);
        for (int x=0; x<3; x++){
            if (x==0)
                year = Integer.parseInt(split[0]);
            if (x==1)
                month = Integer.parseInt(split[1]);
            if (x==2)
                day = Integer.parseInt(split[2]);
        }
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String getDateTime() {
        return month+"-"+day+"-"+year;
    }
    public String getDateTimeSTD() {
        return OGString;
    }
}
