package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;

public class dataOrb
{
    private final StringProperty hour;
    private final StringProperty goal;
    private final IntegerProperty actual;
    private final StringProperty difference;
    private DecimalFormat goalFormat;

    public dataOrb(String hour,String goal,int actual, String difference)
    {
        goalFormat = new DecimalFormat("#");
        this.hour = new SimpleStringProperty(hour);
        this.goal = new SimpleStringProperty(goal);
        this.actual = new SimpleIntegerProperty(actual);
        this.difference = new SimpleStringProperty(difference);
    }
    public String getHour() {
        return hour.get();
    }

    public StringProperty hourProperty() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour.set(hour);
    }

    public String getGoal() {
        return goal.get();
    }

    public StringProperty goalProperty() {
        return goal;
    }

    public void setGoal(double goal) {
        double temp = 0;
        double remainder = goal - Math.floor(goal);

        if(remainder < 0.5)
        {
            temp = Math.floor(goal);
        }
        if(remainder >= 0.5)
        {
            temp = Math.ceil(goal);
        }
        this.goal.set(goalFormat.format(temp));
    }

    public int getActual() {
        return actual.get();
    }

    public IntegerProperty actualProperty() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual.set(actual);
    }

    public String getDifference() {
        return difference.get();
    }

    public StringProperty differenceProperty() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference.set(difference);
    }


}
