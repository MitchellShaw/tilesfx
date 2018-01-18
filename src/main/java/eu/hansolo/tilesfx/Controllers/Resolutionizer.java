package eu.hansolo.tilesfx.Controllers;

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

    public double setTileHeight(int numberOfRows, double modifier)
    {
        double tileHeight = screenHeight * modifier;
        //System.out.println("Height of Tile: "+tileHeight);

        return tileHeight;
    }
    public double setTileWidth(int numberOfColumns, double modifier)
    {
        double tileWidth = screenWidth * modifier;
        //System.out.println("Width of Tile: "+tileWidth);

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
}
