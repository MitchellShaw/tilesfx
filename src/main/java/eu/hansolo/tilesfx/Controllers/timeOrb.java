package main.java.eu.hansolo.tilesfx.Controllers;

import java.util.HashMap;

public class timeOrb {

    String line;
    HashMap<String,Integer> timeMap;

    public timeOrb(String line, HashMap<String,Integer> timeMap)
    {
        this.line = line;
        this.timeMap = timeMap;
    }
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public HashMap<String, Integer> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(HashMap<String, Integer> timeMap) {
        this.timeMap = timeMap;
    }
    public int grabTime(String time)
    {
        int returnMe = this.timeMap.get(time);
        return returnMe;
    }

}
