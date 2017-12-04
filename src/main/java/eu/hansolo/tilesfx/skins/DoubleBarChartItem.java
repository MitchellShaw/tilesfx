/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.tilesfx.skins;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.DoubleChartData;
import eu.hansolo.tilesfx.events.ChartDataEvent;
import eu.hansolo.tilesfx.events.DoubleChartDataEvent.EventType;
import eu.hansolo.tilesfx.events.ChartDataEventListener;
import eu.hansolo.tilesfx.events.DoubleChartDataEvent;
import eu.hansolo.tilesfx.events.DoubleChartDataEventListener;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Locale;

import static eu.hansolo.tilesfx.tools.Helper.clamp;


/**
 * User: hansolo
 * Date: 23.12.16
 * Time: 13:10
 */
@DefaultProperty("children")
public class DoubleBarChartItem extends Region implements Comparable<DoubleBarChartItem>{
    private static final double                PREFERRED_WIDTH  = 250;
    private static final double                PREFERRED_HEIGHT = 30;
    private static final double                MINIMUM_WIDTH    = 25;
    private static final double                MINIMUM_HEIGHT   = 3.6;
    private static final double                MAXIMUM_WIDTH    = 1024;
    private static final double                MAXIMUM_HEIGHT   = 1024;
    private static final double                ASPECT_RATIO     = PREFERRED_HEIGHT / PREFERRED_WIDTH;
    private              double                width;
    private              double                height;
    private              double                size;
    private              double                parentWidth;
    private              double                parentHeight;
    private              Text                  nameText;
    private              Text                  topText;
    private              Text                  bottomText;
    private              Rectangle             barBackground;
    private              Rectangle             topBar;
    private              Rectangle             bottomBar;
    private              Pane                  pane;
    private              ObjectProperty<Color> nameColor;
    private              ObjectProperty<Color> valueColor;
    private              ObjectProperty<Color> barBackgroundColor;
    private              String                formatString;
    private              Locale                locale;
    private              double                maxValue;
    private              double                stepSize;
    private              DoubleChartData       doubleChartData;


    // ******************** Constructors **************************************
    public DoubleBarChartItem() { this("",0, 0, Tile.BLUE); }
    public DoubleBarChartItem(final String NAME) {
        this(NAME,0, 0, Tile.BLUE);
    }
    public DoubleBarChartItem(final String NAME, final double GOALVALUE) {
        this(NAME, GOALVALUE, 0, Tile.BLUE);
    }
    public DoubleBarChartItem(final String NAME, final double GOALVALUE, final double CURRENTVALUE, final Color COLOR) {
        nameColor          = new ObjectPropertyBase<Color>(Tile.FOREGROUND) {
            @Override protected void invalidated() { nameText.setFill(get()); }
            @Override public Object getBean() { return DoubleBarChartItem.this; }
            @Override public String getName() { return "nameColor"; }
        };
        valueColor         = new ObjectPropertyBase<Color>(Tile.FOREGROUND) {
            @Override protected void invalidated() {  topText.setFill(get()); bottomText.setFill(get()); }
            @Override public Object getBean() { return DoubleBarChartItem.this; }
            @Override public String getName() { return "valueColor"; }
        };
        barBackgroundColor = new ObjectPropertyBase<Color>(Color.rgb(72, 72, 72)) {
            @Override protected void invalidated() { barBackground.setFill(get()); }
            @Override public Object getBean() { return DoubleBarChartItem.this; }
            @Override public String getName() { return "barBackgroundColor"; }
        };
        formatString       = "%.0f";
        locale             = Locale.US;
        maxValue           = 100;
        doubleChartData          = new DoubleChartData(NAME, GOALVALUE, CURRENTVALUE, COLOR);
        stepSize           = PREFERRED_WIDTH * 0.85 / maxValue;
        parentWidth        = 250;
        parentHeight       = 250;
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initGraphics() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
                Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        nameText = new Text(getName());
        nameText.setTextOrigin(VPos.TOP);

        topText = new Text(String.format(locale, formatString, getPresentValue()));
        topText.setTextOrigin(VPos.TOP);

        bottomText = new Text(String.format(locale, formatString, getGoalValue()));
        bottomText.setTextOrigin(VPos.TOP);



        barBackground = new Rectangle();

        topBar = new Rectangle();
        bottomBar = new Rectangle();

        pane = new Pane(nameText, topText, bottomText, barBackground, topBar, bottomBar);
        pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
    }


    // ******************** Methods *******************************************
    @Override protected double computeMinWidth(final double HEIGHT) { return MINIMUM_WIDTH; }
    @Override protected double computeMinHeight(final double WIDTH) { return MINIMUM_HEIGHT; }
    @Override protected double computePrefWidth(final double HEIGHT) { return super.computePrefWidth(HEIGHT); }
    @Override protected double computePrefHeight(final double WIDTH) { return super.computePrefHeight(WIDTH); }
    @Override protected double computeMaxWidth(final double HEIGHT) { return MAXIMUM_WIDTH; }
    @Override protected double computeMaxHeight(final double WIDTH) { return MAXIMUM_HEIGHT; }

    @Override public ObservableList<Node> getChildren() { return super.getChildren(); }

    public String getName() { return doubleChartData.getName(); }
    public void setName(final String NAME) { doubleChartData.setName(NAME); }

    public double getGoalValue() { return doubleChartData.getGoalValue(); }
    public void setGoalValue(final double GOALVALUE) { doubleChartData.setGoalValue(GOALVALUE); }

    public double getPresentValue() { return doubleChartData.getPresentValue(); }
    public void setPresentValue(final double PRESENTVALUE) { doubleChartData.setPresentValue(PRESENTVALUE); }

    public Color getNameColor() { return nameColor.get(); }
    public void setNameColor(final Color COLOR) { nameColor.set(COLOR); }
    public ObjectProperty<Color> nameColorProperty() { return nameColor; }

    public Color getValueColor() { return valueColor.get(); }
    public void setValueColor(final Color COLOR) { valueColor.set(COLOR); }
    public ObjectProperty<Color> valueColorProperty() { return valueColor; }

    public DoubleChartData getChartData() { return doubleChartData; }
    public void setChartData(final DoubleChartData DATA) {
        doubleChartData = DATA;
        /////////////////////////check into this line//////////////////////////////////////////////////////////////////////
        doubleChartData.fireChartDataEvent(new DoubleChartDataEvent(EventType.UPDATE, doubleChartData));
    }

    public Color getBarBackgroundColor() { return barBackgroundColor.get(); }
    public void setBarBackgroundColor(final Color COLOR) { barBackgroundColor.set(COLOR); }
    public ObjectProperty<Color> barBackgroundColorProperty() { return barBackgroundColor; }

    public Color getBarColor() { return doubleChartData.getFillColor(); }
    public void setBarColor(final Color COLOR) { doubleChartData.setFillColor(COLOR); }

    /////////////////////////check into this line//////////////////////////////////////////////////////////////////////
    @Override public int compareTo(final DoubleBarChartItem ITEM) { return Double.compare(getGoalValue(), ITEM.getPresentValue()); }

    public void setStepSize(final double STEP_SIZE) {
        stepSize = STEP_SIZE;
        updateTopBar(getPresentValue());
        updateBottomBar(getGoalValue());
    }

    public void setMaxValue(final double MAX_VALUE) {
        maxValue = MAX_VALUE;
        stepSize = (parentWidth - size * 0.15) / maxValue;
        updateTopBar(getPresentValue());
        updateBottomBar(getGoalValue());
    }

    public void setLocale(final Locale LOCALE) {
        locale = LOCALE;
        topText.setText(String.format(locale, formatString, getPresentValue()));
        bottomText.setText(String.format(locale, formatString, getGoalValue()));
    }

    public String getFormatString() { return formatString; }
    public void setFormatString(final String FORMAT_STRING) {
        formatString = FORMAT_STRING;
        topText.setText(String.format(locale, formatString, getPresentValue()));
        bottomText.setText(String.format(locale, formatString, getGoalValue()));
    }

    protected void setParentSize(final double WIDTH, final double HEIGHT) {
        parentWidth  = WIDTH;
        parentHeight = HEIGHT;
        resize();
    }

    private void updateTopBar(final double VALUE) {
        topText.setText(String.format(locale, formatString, VALUE));
        topText.setX((parentWidth - size * 0.05) - topText.getLayoutBounds().getWidth());
        topBar.setWidth(clamp(0, (parentWidth - size * 0.15), VALUE * stepSize));
        topBar.setFill(getBarColor());
    }
    private void updateBottomBar(final double VALUE) {
        bottomText.setText(String.format(locale, formatString, VALUE));
        bottomText.setX((parentWidth - size * 0.05) - bottomText.getLayoutBounds().getWidth());
        bottomBar.setWidth(clamp(0, (parentWidth - size * 0.15), VALUE * stepSize));
        bottomBar.setFill(getBarColor());
    }


    // ******************** Event Handling ************************************
    public void setOnChartDataEvent(final DoubleChartDataEventListener LISTENER) { doubleChartData.addChartDataEventListener(LISTENER); }
    public void addChartDataEventListener(final DoubleChartDataEventListener LISTENER) { doubleChartData.addChartDataEventListener(LISTENER); }
    public void removeChartDataEventListener(final DoubleChartDataEventListener LISTENER) { doubleChartData.removeChartDataEventListener(LISTENER); }


    // ******************** Resizing ******************************************
    private void resize() {
        width  = getWidth() - getInsets().getLeft() - getInsets().getRight();
        height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size   = parentWidth < parentHeight ? parentWidth : parentHeight;

        if (ASPECT_RATIO * width > height) {
            width = 1 / (ASPECT_RATIO / height);
        } else if (1 / (ASPECT_RATIO / height) > width) {
            height = ASPECT_RATIO * width;
        }

        if (width > 0 && height > 0) {
            stepSize = (parentWidth - size * 0.15) / maxValue;

            pane.setMaxSize(parentWidth, height * 0.12);
            pane.setPrefSize(parentWidth, height * 0.12);

            nameText.setFont(Fonts.latoRegular(size * 0.06));
            nameText.setX(size * 0.05);
            nameText.setY(0);

            topText.setFont(Fonts.latoRegular(size * 0.06));
            topText.setX((parentWidth - size * 0.05) - topText.getLayoutBounds().getWidth());
            topText.setY(0);

            bottomText.setFont(Fonts.latoRegular(size * 0.06));
            bottomText.setX((parentWidth - size * 0.05) - bottomText.getLayoutBounds().getWidth());
            bottomText.setY(0);

            barBackground.setX(size * 0.075);
            barBackground.setY(size * 0.10333333);
            barBackground.setWidth(parentWidth - size * 0.15);
            barBackground.setHeight(size * 0.01);

            topBar.setX(size * 0.075);
            bottomBar.setX(size * 0.075);
            topBar.setY(size * 0.09666667);
            bottomBar.setY(size * 0.09666667);
            topBar.setWidth(clamp(0, (parentWidth - size * 0.15), getPresentValue() * stepSize));
            bottomBar.setWidth(clamp(0, (parentWidth - size * 0.15), getGoalValue() * stepSize));
            topBar.setHeight(size * 0.02333333);
            bottomBar.setHeight(size * 0.02333333);

            redraw();
        }
    }

    private void redraw() {
        nameText.setFill(getNameColor());
        topText.setFill(getValueColor());
        bottomText.setFill(getValueColor());
        barBackground.setFill(getBarBackgroundColor());
        topBar.setFill(getBarColor());
        bottomBar.setFill(getBarColor());
    }

    @Override public String toString() {
        return new StringBuilder(getName()).append(",").append(getPresentValue()).append(getGoalValue()).append(",").append(getBarColor()).toString();
    }
}
