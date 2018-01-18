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
        screenHeight = gd.getDisplayMode().getHeight();
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        screenWidth = screenSize.getWidth();
//        System.out.println("Screen Width: "+screenWidth);
//        screenHeight = screenSize.getHeight();
//        System.out.println("Screen Height: "+screenHeight);
    }

    public double setTileHeight(int numberOfRows, double modifier)
    {
        double tileHeight = screenHeight/numberOfRows;
        tileHeight = tileHeight * modifier;

        return tileHeight;
    }
    public double setTileWidth(int numberOfColumns, double modifier)
    {
        double tileWidth = screenWidth/numberOfColumns;
        tileWidth = tileWidth * modifier;

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
//    public int setImageHeight(int currentHeight)
//    {
//
//    }
//    public int setImageWidth(int currentWidth)
//    {
//
//    }

}
