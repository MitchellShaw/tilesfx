package eu.hansolo.tilesfx.chart;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.events.ChartDataEvent;
import eu.hansolo.tilesfx.events.ChartDataEvent.EventType;
import eu.hansolo.tilesfx.events.ChartDataEventListener;
import eu.hansolo.tilesfx.tools.Location;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static eu.hansolo.tilesfx.tools.Helper.clamp;


/**
 * Created by hansolo on 17.02.17.
 */
public class DoubleChartData implements Comparable<DoubleChartData> {
    private final ChartDataEvent               UPDATE_EVENT   = new ChartDataEvent(EventType.UPDATE, DoubleChartData.this);
    private final ChartDataEvent               FINISHED_EVENT = new ChartDataEvent(EventType.FINISHED, DoubleChartData.this);
    private       String                       name;
    private       double                       goalValue;
    private       double                       presentValue;
    private       double                       oldValue;
    private       Color                        fillColor;
    private       Color                        strokeColor;
    private       Color                        textColor;
    private       Instant                      timestamp;
    private       Location                     location;
    private       boolean                      animated;
    private       long                         animationDuration;
    private       List<ChartDataEventListener> listenerList = new CopyOnWriteArrayList<>();
    private       DoubleProperty               goalValueProp;
    private       DoubleProperty               presentValueProp;
    private       Timeline                     timeline;


    // ******************** Constructors **************************************
    public DoubleChartData() {
        this("", 0,0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final String NAME) {
        this(NAME,0, 0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(double GOALVALUE) {
        this("", GOALVALUE, 0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final double GOALVALUE, final Instant TIMESTAMP) {
        this("", GOALVALUE, 0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, TIMESTAMP, true, 800);
    }
    public DoubleChartData(final String NAME, final Instant TIMESTAMP) {
        this(NAME, 0,0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, TIMESTAMP, true, 800);
    }
    public DoubleChartData(final String NAME, final Color FILL_COLOR) {
        this(NAME, 0,0, FILL_COLOR, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE) {
        this(NAME, GOALVALUE,0, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE,final double CURRENTVALUE) {
        this(NAME, GOALVALUE,CURRENTVALUE, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Instant TIMESTAMP) {
        this(NAME, GOALVALUE, CURRENTVALUE, Tile.BLUE, Color.TRANSPARENT, Tile.FOREGROUND, TIMESTAMP, true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color FILL_COLOR) {
        this(NAME, GOALVALUE, CURRENTVALUE, FILL_COLOR, Color.TRANSPARENT, Tile.FOREGROUND, Instant.now(), true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color FILL_COLOR, final Instant TIMESTAMP) {
        this(NAME, GOALVALUE, CURRENTVALUE, FILL_COLOR, Color.TRANSPARENT, Tile.FOREGROUND, TIMESTAMP, true, 800);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color FILL_COLOR, final Instant TIMESTAMP, final boolean ANIMATED, final long ANIMATION_DURATION) {
        this(NAME, GOALVALUE, CURRENTVALUE, FILL_COLOR, Color.TRANSPARENT, Tile.FOREGROUND, TIMESTAMP, ANIMATED, ANIMATION_DURATION);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color FILL_COLOR, final Color STROKE_COLOR, final Instant TIMESTAMP, final boolean ANIMATED, final long ANIMATION_DURATION) {
        this(NAME, GOALVALUE, CURRENTVALUE, FILL_COLOR, STROKE_COLOR, Tile.FOREGROUND, TIMESTAMP, ANIMATED, ANIMATION_DURATION);
    }
    public DoubleChartData(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color FILL_COLOR, final Color STROKE_COLOR, final Color TEXT_COLOR, final Instant TIMESTAMP, final boolean ANIMATED, final long ANIMATION_DURATION) {
        name              = NAME;
        goalValue         = GOALVALUE;
        presentValue      = CURRENTVALUE;
        oldValue          = 0;
        fillColor         = FILL_COLOR;
        strokeColor       = STROKE_COLOR;
        textColor         = TEXT_COLOR;
        timestamp         = TIMESTAMP;
        goalValueProp      = new DoublePropertyBase(goalValue) {
            @Override protected void invalidated() {
                oldValue = goalValue;
                goalValue    = get();
                fireChartDataEvent(UPDATE_EVENT);
            }
            @Override public Object getBean() { return DoubleChartData.this; }
            @Override public String getName() { return "goalValue"; }
        };
        presentValueProp      = new DoublePropertyBase(presentValue) {
            @Override protected void invalidated() {
                oldValue = goalValue;
                goalValue    = get();
                fireChartDataEvent(UPDATE_EVENT);
            }
            @Override public Object getBean() { return DoubleChartData.this; }
            @Override public String getName() { return "presentValue"; }
        };
        timeline          = new Timeline();
        animated          = ANIMATED;
        animationDuration = ANIMATION_DURATION;

        timeline.setOnFinished(e -> fireChartDataEvent(FINISHED_EVENT));
    }


    // ******************** Methods *******************************************
    public String getName() { return name; }
    public void setName(final String NAME) {
        name = NAME;
        if (null != location) { location.setName(NAME); }
        fireChartDataEvent(UPDATE_EVENT);
    }

    public double getGoalValue() { return goalValue; }
    public double getPresentValue() { return presentValue; }
    public void setGoalValue(final double GOALVALUE) {
        if (animated) {
            timeline.stop();
            KeyValue kv1 = new KeyValue(goalValueProp, goalValue, Interpolator.EASE_BOTH);
            KeyValue kv2 = new KeyValue(goalValueProp, GOALVALUE, Interpolator.EASE_BOTH);
            KeyFrame kf1 = new KeyFrame(Duration.ZERO, kv1);
            KeyFrame kf2 = new KeyFrame(Duration.millis(animationDuration), kv2);
            timeline.getKeyFrames().setAll(kf1, kf2);
            timeline.play();
        } else {
            oldValue = goalValue;
            goalValue= GOALVALUE;
            fireChartDataEvent(FINISHED_EVENT);
        }
    }
    public void setPresentValue(final double PRESENTVALUE) {
        if (animated) {
            timeline.stop();
            KeyValue kv1 = new KeyValue(presentValueProp, presentValue, Interpolator.EASE_BOTH);
            KeyValue kv2 = new KeyValue(presentValueProp, PRESENTVALUE, Interpolator.EASE_BOTH);
            KeyFrame kf1 = new KeyFrame(Duration.ZERO, kv1);
            KeyFrame kf2 = new KeyFrame(Duration.millis(animationDuration), kv2);
            timeline.getKeyFrames().setAll(kf1, kf2);
            timeline.play();
        } else {
            oldValue = presentValue;
            presentValue= PRESENTVALUE;
            fireChartDataEvent(FINISHED_EVENT);
        }
    }

    public double getOldValue() { return oldValue; }

    public Color getFillColor() { return fillColor; }
    public void setFillColor(final Color COLOR) {
        fillColor = COLOR;
        if (null != location) { location.setColor(COLOR); }
        fireChartDataEvent(UPDATE_EVENT);
    }

    public Color getStrokeColor() { return strokeColor; }
    public void setStrokeColor(final Color COLOR) {
        strokeColor = COLOR;
        fireChartDataEvent(UPDATE_EVENT);
    }

    public Color getTextColor() { return textColor; }
    public void setTextColor(final Color COLOR) {
        textColor = COLOR;
        fireChartDataEvent(UPDATE_EVENT);
    }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(final Instant TIMESTAMP) {
        timestamp = TIMESTAMP;
        fireChartDataEvent(UPDATE_EVENT);
    }

    public Location getLocation() { return location; }
    public void setLocation(final Location LOCATION) {
        location = LOCATION;
        location.setName(getName());
        location.setColor(getFillColor());
        fireChartDataEvent(UPDATE_EVENT);
    }

    public ZonedDateTime getTimestampAdDateTime() { return getTimestampAsDateTime(ZoneId.systemDefault()); }
    public ZonedDateTime getTimestampAsDateTime(final ZoneId ZONE_ID) { return ZonedDateTime.ofInstant(timestamp, ZONE_ID); }

    public LocalDate getTimestampAsLocalDate() { return getTimestampAsLocalDate(ZoneId.systemDefault()); }
    public LocalDate getTimestampAsLocalDate(final ZoneId ZONE_ID) { return getTimestampAsDateTime(ZONE_ID).toLocalDate(); }

    public boolean isAnimated() { return animated; }
    public void setAnimated(final boolean ANIMATED) { animated = ANIMATED; }

    public long getAnimationDuration() { return animationDuration; }
    public void setAnimationDuration(final long DURATION) { animationDuration = clamp(10, 10000, DURATION); }

    @Override public String toString() {
        return new StringBuilder().append("{\n")
                .append("  \"name\":").append(name).append(",\n")
                .append("  \"value\":").append(goalValue).append(",\n")
                .append("  \"value\":").append(presentValue).append(",\n")
                .append("  \"fillColor\":").append(fillColor.toString().replace("0x", "#")).append(",\n")
                .append("  \"strokeColor\":").append(strokeColor.toString().replace("0x", "#")).append(",\n")
                .append("  \"timestamp\":").append(timestamp.toEpochMilli()).append(",\n")
                .append("}")
                .toString();
    }


    /// investigate these further from source.
    @Override public int compareTo(final DoubleChartData DATA){return Double.compare(getGoalValue(),DATA.getPresentValue());}
     public int compareToGoal(final DoubleChartData DATA,double goalValue) { return Double.compare(getGoalValue(), DATA.getGoalValue()); }
     public int compareToPresent(final DoubleChartData DATA, double presentValue) { return Double.compare(getPresentValue(), DATA.getPresentValue()); }


    // ******************** Event Handling ************************************
    public void setOnChartDataEvent(final ChartDataEventListener LISTENER) { addChartDataEventListener(LISTENER); }
    public void addChartDataEventListener(final ChartDataEventListener LISTENER) { if (!listenerList.contains(LISTENER)) listenerList.add(LISTENER); }
    public void removeChartDataEventListener(final ChartDataEventListener LISTENER) { if (listenerList.contains(LISTENER)) listenerList.remove(LISTENER); }

    public void fireChartDataEvent(final ChartDataEvent EVENT) {
        for (ChartDataEventListener listener : listenerList) { listener.onChartDataEvent(EVENT); }
    }
}
