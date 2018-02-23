package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Resolutionizer
{
    Dimension screenSize;
    double screenWidth;
    double screenHeight;

    public Resolutionizer()
    {
        detectResolution();
    }

    public void detectResolution() {

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
    }

    public double setTileHeight(double modifier)
    {
        double tileHeight = screenHeight * modifier;
        //"Height of Tile: "+tileHeight);

        return tileHeight;
    }
    public double setTileWidth(double modifier)
    {
        double tileWidth = screenWidth * modifier;
        //"Width of Tile: "+tileWidth);

        return tileWidth;
    }
    public int setPaneHeight()
    {
        int paneHeight = (int) screenHeight;

        return paneHeight;
    }
    public int setPaneWidth()
    {
        int paneWidth = (int) screenWidth;

        return paneWidth;
    }
    public void setImageWidth(ImageView image)
    {
        if(screenWidth != 1080)
        {
            image.setFitWidth(image.getFitWidth()*.6);
        }

    }
    public void setImageHeight(ImageView image)
    {
        if(screenHeight != 1080)
        {
            image.setFitHeight(image.getFitHeight()*.6);
        }
    }
    public void setLabelSize(Label label)
    {
        if(screenWidth != 1920)
        {

            label.setStyle("-fx-font-size: 48px");
        }
    }
    public void setLoader(ProgressIndicator indicator)
    {
        if (screenWidth != 1920)
        {
           indicator.setPrefHeight(indicator.getPrefHeight()*.6);
           indicator.setPrefWidth(indicator.getPrefWidth()*.6);
        }
    }
}
