package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.java.eu.hansolo.tilesfx.Tile;
import main.java.eu.hansolo.tilesfx.TileBuilder;
import main.java.eu.hansolo.tilesfx.skins.BarChartItem;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainBuildController implements Initializable
{

    @FXML
    private Tile posBuild;
    @FXML
    private Tile serversBuild;
    @FXML
    private Tile periphBuild;
    @FXML
    private Tile opticBuild;


    @FXML
    private Tile retailBuild;
    @FXML
    private Tile posPercent;
    @FXML
    private Tile serversPercent;
    @FXML
    private Tile periphPercent;
    @FXML
    private Tile opticPercent;
    @FXML
    private Tile retailPercent;
    @FXML
    private Tile posFTT;
    @FXML
    private Tile serversFTT;
    @FXML
    private Tile periphFTT;
    @FXML
    private Tile opticFTT;
    @FXML
    private Tile retailFTT;

    @FXML
    private GridPane pane;

    double p1x30CurrentBuild;
    double p1x30GoalBuild;
    double p1x35CurrentBuild;
    double p1x35GoalBuild;
    double p1532CurrentBuild;
    double p1532GoalBuild;
    double t1000sCurrentBuild;
    double t1000sGoalBuild;
    double questGoalBuild;
    double questCurrentBuild;

    double posTotalGoalBuild;
    double posTotalCurrentBuild;

    double xr7CurrentBuild;
    double xr7GoalBuild;
    double xr7PlusCurrentBuild;
    double xr7PlusGoalBuild;
    double xr5CurrentBuild;
    double xr5GoalBuild;
    double nextGenDisplayCurrentBuild;
    double nextGenDisplayGoalsBuild;

    double retailTotalGoalBuild;
    double retailTotalCurrentBuild;

    double mediaPlayerCurrentBuild;
    double mediaPlayerGoalBuild;
    double n3000CurrentBuild;
    double n3000GoalBuild;
    double s500CurrentBuild;
    double s500GoalBuild;

    double serverGoalTotalBuild;
    double serverCurrentBuild;

    double kiwi4sCurrentBuild;
    double kiwi4sGoalBuild;
    double kiwi2XsCurrentBuild;
    double kiwi2XsGoalBuild;
    double bumpBarsCurrentBuild;
    double bumpBarsGoalBuild;
    double pantherEPC4sCurrentBuild;
    double pantherEPC4sGoalBuild;

    double periphGoalTotalBuild;
    double periphCurrentTotalBuild;

    double optic5sCurrentBuild;
    double optic5sGoalBuild;
    double optic12sCurrentBuild;
    double optic12sGoalBuild;
    double cubCurrentBuild;
    double cubGoalBuild;
    double kitsCurrentBuild;
    double kitsGoalBuild;
    double printerCurrentBuild;
    double printerGoalBuild;

    double opticGoalTotalBuild;
    double opticCurrentTotalBuild;

    double posPercentTotalBuild;
    double retailPercentTotalBuild;
    double opticPercentTotalBuild;
    double serversPercentTotalBuild;
    double periphPercentTotalBuild;

    double opticThrough;
    double periphThrough;
    double serversThrough;
    double retailThrough;
    double posThrough;

    double posBar1Total;
    double posBar1Goal;

    double retailBar1Total;
    double retailBar1Goal;

    double serverBar1Total;
    double serverBar1Goal;

    double serverBar2Total;
    double serverBar2Goal;

    double periphBar1Total;
    double periphBar1Goal;

    double periphBar2Total;
    double periphBar2Goal;

    double periphBar3Total;
    double periphBar3Goal;

    //---------------------------------Creating the Bar Chart Items for Hosp-----------------------------------------
    BarChartItem posBar1Data;
    BarChartItem p1x30Data;
    BarChartItem t1000Data;
    BarChartItem questData;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem serverBar1;
    BarChartItem serverBar2;
    //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
    BarChartItem periphBar1Data;
    BarChartItem periphBar2Data;
    BarChartItem periphBar3Data;
    //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
    //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
    BarChartItem optic12Data;
    BarChartItem optic5Data;
    //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    BarChartItem retailBar1Data;
    BarChartItem nextGenDisplays;

    Messenger messenger;

    DecimalFormat df = new DecimalFormat("#.0");
    DecimalFormat hundred = new DecimalFormat("#");

    double x = 0;
    double y = 0;

    ArrayList<Tile> tiles;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tiles = new ArrayList<>();

        //---------------------------------Creating the Bar Chart Items for Hosp----------------------------------------
        posBar1Data = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30Data = new BarChartItem("7743", p1x30CurrentBuild, p1x30GoalBuild, Tile.ORANGE);
        t1000Data = new BarChartItem("7744", t1000sCurrentBuild, t1000sGoalBuild, Tile.GREEN);
        questData = new BarChartItem("7791/7792", questCurrentBuild, questGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1 = new BarChartItem("1611/1612", serverBar1Total, serverBar1Goal, Tile.BLUE);
        serverBar2 = new BarChartItem("1656/1657", serverBar2Total, serverBar2Goal, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        periphBar1Data = new BarChartItem("1635", periphBar1Total, periphBar1Goal, Tile.BLUE);
        periphBar2Data = new BarChartItem("1642/1924", periphBar2Total, periphBar2Goal, Tile.ORANGE);
        periphBar3Data = new BarChartItem("1646/1651", periphBar3Total, periphBar3Goal, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        optic12Data = new BarChartItem("6002", optic12sCurrentBuild, optic12sGoalBuild, Tile.ORANGE);
        optic5Data = new BarChartItem("6001", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        retailBar1Data = new BarChartItem("770X", retailBar1Total, retailBar1Goal, Tile.BLUE);
        nextGenDisplays = new BarChartItem("5968/5985", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.ORANGE);
        //---------------------------------Creating Tiles for Scene-----------------------------------------------------

        posBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(posBar1Data, p1x30Data, t1000Data, questData)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(posBuild, 0, 0, 1, 2);

        posPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .subText(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .value(posPercentTotalBuild)
                .build();


        posFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(posThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(posPercent, 0, 2, 1, 1);
        pane.add(posFTT, 0, 3, 1, 1);

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retailBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Retail Build")
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(retailBar1Data, nextGenDisplays)
                .decimals(0)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(retailBuild, 1, 0, 1, 2);

        retailPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentBuild) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(retailPercentTotalBuild)
                .build();

        retailFTT = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(retailThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (retailFTT.getDescription().equals("100.0")) {
            retailFTT.setDescription(hundred.format(retailThrough) + "%");
        }

        pane.add(retailPercent, 1, 2, 1, 1);
        pane.add(retailFTT, 1, 3, 1, 1);


        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(serverBar1, serverBar2)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(serversBuild, 2, 0, 1, 2);

        serversPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .subText(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalBuild)
                .build();

        serversFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(serversThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (serversFTT.getDescription().equals("100.0")) {
            serversFTT.setDescription(hundred.format(serversThrough) + "%");
        }

        pane.add(serversPercent, 2, 2, 1, 1);
        pane.add(serversFTT, 2, 3, 1, 1);


        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        periphBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(periphBar1Data, periphBar2Data, periphBar3Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(periphBuild, 3, 0, 1, 2);

        periphPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(periphPercentTotalBuild)
                .build();

        periphFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(periphThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (periphFTT.getDescription().equals("100.0")) {
            periphFTT.setDescription(hundred.format(periphThrough) + "%");
        }

        pane.add(periphPercent, 3, 2, 1, 1);
        pane.add(periphFTT, 3, 3, 1, 1);


        //---------------------------------Creating the Tiles for Optic-------------------------------------------------
        opticBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(optic5Data, optic12Data)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(opticBuild, 4, 0, 1, 2);

        opticPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalBuild) + "/" + Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(opticPercentTotalBuild)
                .build();

        opticFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.21), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("FTT")
                .description(df.format(opticThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (opticFTT.getDescription().equals("100.0")) {
            opticFTT.setDescription(hundred.format(opticThrough) + "%");
        }

        pane.add(opticPercent, 4, 2, 1, 1);
        pane.add(opticFTT, 4, 3, 1, 1);

        createActions();
        tiles.add(posBuild);
        tiles.add(serversBuild);
        tiles.add(periphBuild);
        tiles.add(opticBuild);
        tiles.add(retailBuild);
        tiles.add(posPercent);
        tiles.add(serversPercent);
        tiles.add(periphPercent);
        tiles.add(opticPercent);
        tiles.add(retailPercent);
        tiles.add(posFTT);
        tiles.add(serversFTT);
        tiles.add(periphFTT);
        tiles.add(opticFTT);
        tiles.add(retailFTT);

        if (posThrough == 100) {
            posFTT.setDescription(hundred.format(posThrough) + "%");
        }
        if (serversThrough == 100) {
            serversFTT.setDescription(hundred.format(serversThrough) + "%");
        }
        if (periphThrough == 100) {
            periphFTT.setDescription(hundred.format(periphThrough) + "%");
        }
        if (opticThrough == 100) {
            opticFTT.setDescription(hundred.format(opticThrough) + "%");
        }
        if (retailThrough == 100) {
            retailFTT.setDescription(hundred.format(retailThrough) + "%");
        }


        tilesListeners(tiles);
        refresh();
    }

    public void refresh()
    {
        Platform.runLater( ()->
        {
            posBar1Data.setValue(posBar1Total);
            posBar1Data.setMaxValue(posBar1Goal);

            p1x30Data.setValue(p1x30CurrentBuild);
            p1x30Data.setMaxValue(p1x30GoalBuild);

            t1000Data.setValue(t1000sCurrentBuild);
            t1000Data.setMaxValue(t1000sGoalBuild);

            questData.setValue(questCurrentBuild);
            questData.setMaxValue(questGoalBuild);

            //---------------------------------Update the Server Units------------------------------------------
            serverBar1.setValue(serverBar1Total);
            serverBar1.setMaxValue(serverBar1Goal);
            serverBar2.setValue(serverBar2Total);
            serverBar2.setMaxValue(serverBar2Goal);

            //---------------------------------Updating the Peripheral Units------------------------------------
            periphBar1Data.setValue(periphBar1Total);
            periphBar1Data.setMaxValue(periphBar1Goal);
            periphBar2Data.setValue(periphBar2Total);
            periphBar2Data.setMaxValue(periphBar2Goal);
            periphBar3Data.setValue(periphBar3Total);
            periphBar3Data.setMaxValue(periphBar3Goal);
            //---------------------------------Updating the Optic Units------------------------------------------
            optic5Data.setValue(optic5sCurrentBuild);
            optic5Data.setMaxValue(optic5sGoalBuild);
            optic12Data.setValue(optic12sCurrentBuild);
            optic12Data.setMaxValue(optic12sGoalBuild);
            //---------------------------------Updating the Retail Units----------------------------------------
            retailBar1Data.setValue(retailBar1Total);
            retailBar1Data.setMaxValue(retailBar1Goal);
            nextGenDisplays.setValue(nextGenDisplayCurrentBuild);
            nextGenDisplays.setMaxValue(nextGenDisplayGoalsBuild);

            //---------------------------------Creating Color Changes for POS Dial------------------------------------------
            posPercent.setValue(posPercentTotalBuild);
            posFTT.setDescription(df.format(posThrough) + "%");
            if(posThrough == 100)
            {
                posFTT.setDescription(hundred.format(posThrough)+"%");
            }

            changePercent(posPercent, posTotalCurrentBuild, posTotalGoalBuild, posPercentTotalBuild);
            changePercent(posFTT, posTotalCurrentBuild, posTotalGoalBuild, posPercentTotalBuild);

            //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
            serversPercent.setValue(serversPercentTotalBuild);
            serversFTT.setDescription(df.format(serversThrough) + "%");
            if(serversThrough == 100)
            {
                serversFTT.setDescription(hundred.format(serversThrough)+"%");
            }

            changePercent(serversPercent, serverCurrentBuild, serverGoalTotalBuild, serversPercentTotalBuild);
            changePercent(serversFTT, serverCurrentBuild, serverGoalTotalBuild, serversPercentTotalBuild);

            //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
            periphPercent.setValue(periphPercentTotalBuild);
            periphFTT.setDescription(df.format(periphThrough) + "%");
            if(periphThrough == 100)
            {
                periphFTT.setDescription(hundred.format(periphThrough)+"%");
            }


            changePercent(periphPercent, periphCurrentTotalBuild, periphGoalTotalBuild, periphPercentTotalBuild);
            changePercent(periphFTT, periphCurrentTotalBuild, periphGoalTotalBuild, periphPercentTotalBuild);

            //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
            opticPercent.setValue(opticPercentTotalBuild);
            opticFTT.setDescription(df.format(opticThrough) + "%");
            if(opticThrough == 100)
            {
                opticFTT.setDescription(hundred.format(opticThrough)+"%");
            }

            changePercent(opticPercent, opticCurrentTotalBuild, opticGoalTotalBuild, opticPercentTotalBuild);
            changePercent(opticFTT, opticCurrentTotalBuild, opticGoalTotalBuild, opticPercentTotalBuild);

            //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
            retailPercent.setValue(retailPercentTotalBuild);
            retailFTT.setDescription(df.format(retailThrough) + "%");
            if(retailThrough == 100)
            {
                retailFTT.setDescription(hundred.format(retailThrough)+"%");
            }


            changePercent(retailPercent, retailTotalCurrentBuild, retailTotalGoalBuild, retailPercentTotalBuild);
            changePercent(retailFTT, retailTotalCurrentBuild, retailTotalGoalBuild, retailPercentTotalBuild);

        });
    }
    private void changePercent(Tile main, double current, double goal, double total)
    {
        if (Double.compare(total, 60) < 0) {
            main.setBarColor(Tile.RED);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
        if (Double.compare(total, 90) < 0 && Double.compare(total, 60) > 0) {
            main.setBarColor(Tile.YELLOW);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
        if (Double.compare(total, 90) > 0) {
            main.setBarColor(Tile.GREEN);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
    }
    private Bounds computeAllScreenBounds() {
        double minX = Double.POSITIVE_INFINITY ;
        double minY = Double.POSITIVE_INFINITY ;
        double maxX = Double.NEGATIVE_INFINITY ;
        double maxY = Double.NEGATIVE_INFINITY ;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D screenBounds = screen.getBounds();
            if (screenBounds.getMinX() < minX) {
                minX = screenBounds.getMinX();
            }
            if (screenBounds.getMinY() < minY) {
                minY = screenBounds.getMinY() ;
            }
            if (screenBounds.getMaxX() > maxX) {
                maxX = screenBounds.getMaxX();
            }
            if (screenBounds.getMaxY() > maxY) {
                maxY = screenBounds.getMaxY() ;
            }
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }

    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getNavigationScene());
                }
                if(event.getCode() == KeyCode.F4)
                {
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.RIGHT)
                {
                    messenger.getPrimaryStage().setScene(messenger.getMainTest());
                }
                if(event.getCode() == KeyCode.T && event.isControlDown())
                {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initStyle(StageStyle.UNDECORATED);

                    dialog.initOwner(messenger.getPrimaryStage());

                    dialog.setScene(messenger.getTimelineScene());
                    dialog.show();
                }
                if(event.getCode() == KeyCode.X && event.isControlDown())
                {
                    Timeline temp = messenger.getTimeLineController().getTimeline();

                    if(temp.getStatus() == Animation.Status.RUNNING && temp != null)
                    {
                        temp.stop();
                    }
                }
            }
        });
        posPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosBuild());
            }
        });
        retailPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
            }
        });
        serversPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerBuild());
            }
        });
        periphPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
            }
        });
        opticPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
            }
        });
        posBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosBuild());
            }
        });
        retailBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
            }
        });
        serversBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerBuild());
            }
        });
        periphBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
            }
        });
        opticBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event)
        {
            messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
        }
    });
        posFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
        retailFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
        serversFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
        periphFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
        opticFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
    }
    private void tilesListeners(ArrayList<Tile> tileList)
    {
        Bounds allScreenBounds = computeAllScreenBounds();

        for(int i =0;i<tileList.size();i++)
        {
            Tile temp = tileList.get(i);

            temp.toFront();

            temp.setAnimated(true);
            temp.setAnimationDuration(3000);

            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();

                }
            });
            tileList.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    temp.setBorderColor(Tile.GRAY);
                    PauseTransition idle = new PauseTransition(Duration.millis(1000));
                    temp.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                        temp.setCursor(Cursor.HAND);
                        idle.playFromStart();
                        temp.setBorderColor(Tile.GRAY);
                    });
                    idle.setOnFinished(e ->
                    {
                        temp.setCursor(Cursor.NONE);
                        temp.setBorderColor(Color.TRANSPARENT);
                    });
                }
            });
            tileList.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    temp.setBorderColor(Color.TRANSPARENT);
                }
            });
            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
//
                    temp.getScene().getWindow().setX(event.getScreenX() - x);
                    temp.getScene().getWindow().setY(event.getScreenY() - y);
                    if(temp.getScene().getWindow().getX() < allScreenBounds.getMinX())
                    {
                        temp.getScene().getWindow().setX(allScreenBounds.getMinX());

                    }
                    if(temp.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth))
                    {
                        temp.getScene().getWindow().setX(allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth);
                    }
                }
            });
        }
    }

    private void screenMove(Stage primaryStage, Bounds allScreenBounds, ArrayList<Screen> screens)
{
    if (screens.size() == 1) {
        primaryStage.setX(allScreenBounds.getMinX());
        primaryStage.setY(allScreenBounds.getMinY());
    }
    if (screens.size() == 2) {

        if (primaryStage.getX() < 0) {
            primaryStage.setX(allScreenBounds.getMinX());
            primaryStage.setY(allScreenBounds.getMinY());
        } else {
            primaryStage.setX(allScreenBounds.getMaxX() - primaryStage.getWidth());
            primaryStage.setY(allScreenBounds.getMinY());
        }
    } else {
        if (primaryStage.getX() < 0 && primaryStage.getX() < allScreenBounds.getMinX() + (primaryStage.getWidth() / 2)) {
            primaryStage.setX(allScreenBounds.getMinX());
            primaryStage.setY(allScreenBounds.getMinY());
        }
        if (primaryStage.getX() > allScreenBounds.getMinX() + (primaryStage.getWidth() / 2) && primaryStage.getX() < allScreenBounds.getMaxX() - (1.5 * (primaryStage.getWidth()))) {
            primaryStage.setX(allScreenBounds.getMinX() + primaryStage.getWidth());
            primaryStage.setY(allScreenBounds.getMinY());
        }
        if (primaryStage.getX() > (allScreenBounds.getMaxX() - (primaryStage.getWidth() / 2) - (primaryStage.getWidth()))) {
            primaryStage.setX(allScreenBounds.getMaxX() - primaryStage.getWidth());
            primaryStage.setY(allScreenBounds.getMinY());
        }
    }
}
    public Messenger getMessenger() {
        return messenger;
    }

    public Tile getPosBuild() { return  posBuild; }

    public Tile getServersBuild() {
        return serversBuild;
    }

    public Tile getPeriphBuild() {
        return periphBuild;
    }

    public Tile getOpticBuild() {
        return opticBuild;
    }

    public Tile getRetailBuild() {
        return retailBuild;
    }

    public Tile getPosPercent() {
        return posPercent;
    }

    public Tile getServersPercent() {
        return serversPercent;
    }

    public Tile getPeriphPercent() {
        return periphPercent;
    }

    public Tile getOpticPercent() {
        return opticPercent;
    }

    public Tile getRetailPercent() {
        return retailPercent;
    }

    public Tile getPosFTT() {
        return posFTT;
    }

    public Tile getServersFTT() {
        return serversFTT;
    }

    public Tile getPeriphFTT() {
        return periphFTT;
    }

    public Tile getOpticFTT() {
        return opticFTT;
    }

    public Tile getRetailFTT() {
        return retailFTT;
    }
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public double getP1x30CurrentBuild() {
        return p1x30CurrentBuild;
    }

    public void setP1x30CurrentBuild(double p1x30CurrentBuild) {
        this.p1x30CurrentBuild = p1x30CurrentBuild;
    }

    public double getP1x30GoalBuild() {
        return p1x30GoalBuild;
    }

    public void setP1x30GoalBuild(double p1x30GoalBuild) {
        this.p1x30GoalBuild = p1x30GoalBuild;
    }

    public double getP1x35CurrentBuild() {
        return p1x35CurrentBuild;
    }

    public void setP1x35CurrentBuild(double p1x35CurrentBuild) {
        this.p1x35CurrentBuild = p1x35CurrentBuild;
    }

    public double getP1x35GoalBuild() {
        return p1x35GoalBuild;
    }

    public void setP1x35GoalBuild(double p1x35GoalBuild) {
        this.p1x35GoalBuild = p1x35GoalBuild;
    }

    public double getP1532CurrentBuild() {
        return p1532CurrentBuild;
    }

    public void setP1532CurrentBuild(double p1532CurrentBuild) {
        this.p1532CurrentBuild = p1532CurrentBuild;
    }

    public double getP1532GoalBuild() {
        return p1532GoalBuild;
    }

    public void setP1532GoalBuild(double p1532GoalBuild) {
        this.p1532GoalBuild = p1532GoalBuild;
    }

    public double getT1000sCurrentBuild() {
        return t1000sCurrentBuild;
    }

    public void setT1000sCurrentBuild(double t1000sCurrentBuild) {
        this.t1000sCurrentBuild = t1000sCurrentBuild;
    }

    public double getT1000sGoalBuild() {
        return t1000sGoalBuild;
    }

    public void setT1000sGoalBuild(double t1000sGoalBuild) {
        this.t1000sGoalBuild = t1000sGoalBuild;
    }

    public double getPosTotalGoalBuild() {
        return posTotalGoalBuild;
    }

    public void setPosTotalGoalBuild(double posTotalGoalBuild) {
        this.posTotalGoalBuild = posTotalGoalBuild;
    }

    public double getPosTotalCurrentBuild() {
        return posTotalCurrentBuild;
    }

    public void setPosTotalCurrentBuild(double posTotalCurrentBuild) {
        this.posTotalCurrentBuild = posTotalCurrentBuild;
    }

    public double getXr7CurrentBuild() {
        return xr7CurrentBuild;
    }

    public void setXr7CurrentBuild(double xr7CurrentBuild) {
        this.xr7CurrentBuild = xr7CurrentBuild;
    }

    public double getXr7GoalBuild() {
        return xr7GoalBuild;
    }

    public void setXr7GoalBuild(double xr7GoalBuild) {
        this.xr7GoalBuild = xr7GoalBuild;
    }

    public double getXr7PlusCurrentBuild() {
        return xr7PlusCurrentBuild;
    }

    public void setXr7PlusCurrentBuild(double xr7PlusCurrentBuild) {
        this.xr7PlusCurrentBuild = xr7PlusCurrentBuild;
    }

    public double getXr7PlusGoalBuild() {
        return xr7PlusGoalBuild;
    }

    public void setXr7PlusGoalBuild(double xr7PlusGoalBuild) {
        this.xr7PlusGoalBuild = xr7PlusGoalBuild;
    }

    public double getXr5CurrentBuild() {
        return xr5CurrentBuild;
    }

    public void setXr5CurrentBuild(double xr5CurrentBuild) {
        this.xr5CurrentBuild = xr5CurrentBuild;
    }

    public double getXr5GoalBuild() {
        return xr5GoalBuild;
    }

    public void setXr5GoalBuild(double xr5GoalBuild) {
        this.xr5GoalBuild = xr5GoalBuild;
    }

    public double getNextGenDisplayCurrentBuild() {
        return nextGenDisplayCurrentBuild;
    }

    public void setNextGenDisplayCurrentBuild(double nextGenDisplayCurrentBuild) {
        this.nextGenDisplayCurrentBuild = nextGenDisplayCurrentBuild;
    }

    public double getNextGenDisplayGoalsBuild() {
        return nextGenDisplayGoalsBuild;
    }

    public void setNextGenDisplayGoalsBuild(double nextGenDisplayGoalsBuild) {
        this.nextGenDisplayGoalsBuild = nextGenDisplayGoalsBuild;
    }

    public double getRetailTotalGoalBuild() {
        return retailTotalGoalBuild;
    }

    public void setRetailTotalGoalBuild(double retailTotalGoalBuild) {
        this.retailTotalGoalBuild = retailTotalGoalBuild;
    }

    public double getRetailTotalCurrentBuild() {
        return retailTotalCurrentBuild;
    }

    public void setRetailTotalCurrentBuild(double retailTotalCurrentBuild) {
        this.retailTotalCurrentBuild = retailTotalCurrentBuild;
    }

    public double getMediaPlayerCurrentBuild() {
        return mediaPlayerCurrentBuild;
    }

    public void setMediaPlayerCurrentBuild(double mediaPlayerCurrentBuild) {
        this.mediaPlayerCurrentBuild = mediaPlayerCurrentBuild;
    }

    public double getMediaPlayerGoalBuild() {
        return mediaPlayerGoalBuild;
    }

    public void setMediaPlayerGoalBuild(double mediaPlayerGoalBuild) {
        this.mediaPlayerGoalBuild = mediaPlayerGoalBuild;
    }

    public double getN3000CurrentBuild() {
        return n3000CurrentBuild;
    }

    public void setN3000CurrentBuild(double n3000CurrentBuild) {
        this.n3000CurrentBuild = n3000CurrentBuild;
    }

    public double getN3000GoalBuild() {
        return n3000GoalBuild;
    }

    public void setN3000GoalBuild(double n3000GoalBuild) {
        this.n3000GoalBuild = n3000GoalBuild;
    }

    public double getS500CurrentBuild() {
        return s500CurrentBuild;
    }

    public void setS500CurrentBuild(double s500CurrentBuild) {
        this.s500CurrentBuild = s500CurrentBuild;
    }

    public double getS500GoalBuild() {
        return s500GoalBuild;
    }

    public void setS500GoalBuild(double s500GoalBuild) {
        this.s500GoalBuild = s500GoalBuild;
    }

    public double getServerGoalTotalBuild() {
        return serverGoalTotalBuild;
    }

    public void setServerGoalTotalBuild(double serverGoalTotalBuild) {
        this.serverGoalTotalBuild = serverGoalTotalBuild;
    }

    public double getServerCurrentBuild() {
        return serverCurrentBuild;
    }

    public void setServerCurrentBuild(double serverCurrentBuild) {
        this.serverCurrentBuild = serverCurrentBuild;
    }

    public double getKiwi4sCurrentBuild() {
        return kiwi4sCurrentBuild;
    }

    public void setKiwi4sCurrentBuild(double kiwi4sCurrentBuild) {
        this.kiwi4sCurrentBuild = kiwi4sCurrentBuild;
    }

    public double getKiwi4sGoalBuild() {
        return kiwi4sGoalBuild;
    }

    public void setKiwi4sGoalBuild(double kiwi4sGoalBuild) {
        this.kiwi4sGoalBuild = kiwi4sGoalBuild;
    }

    public double getKiwi2XsCurrentBuild() {
        return kiwi2XsCurrentBuild;
    }

    public void setKiwi2XsCurrentBuild(double kiwi2XsCurrentBuild) {
        this.kiwi2XsCurrentBuild = kiwi2XsCurrentBuild;
    }

    public double getKiwi2XsGoalBuild() {
        return kiwi2XsGoalBuild;
    }

    public void setKiwi2XsGoalBuild(double kiwi2XsGoalBuild) {
        this.kiwi2XsGoalBuild = kiwi2XsGoalBuild;
    }

    public double getBumpBarsCurrentBuild() {
        return bumpBarsCurrentBuild;
    }

    public void setBumpBarsCurrentBuild(double bumpBarsCurrentBuild) {
        this.bumpBarsCurrentBuild = bumpBarsCurrentBuild;
    }

    public double getBumpBarsGoalBuild() {
        return bumpBarsGoalBuild;
    }

    public void setBumpBarsGoalBuild(double bumpBarsGoalBuild) {
        this.bumpBarsGoalBuild = bumpBarsGoalBuild;
    }

    public double getPantherEPC4sCurrentBuild() {
        return pantherEPC4sCurrentBuild;
    }

    public void setPantherEPC4sCurrentBuild(double pantherEPC4sCurrentBuild) {
        this.pantherEPC4sCurrentBuild = pantherEPC4sCurrentBuild;
    }

    public double getPantherEPC4sGoalBuild() {
        return pantherEPC4sGoalBuild;
    }

    public void setPantherEPC4sGoalBuild(double pantherEPC4sGoalBuild) {
        this.pantherEPC4sGoalBuild = pantherEPC4sGoalBuild;
    }

    public double getPeriphGoalTotalBuild() {
        return periphGoalTotalBuild;
    }

    public void setPeriphGoalTotalBuild(double periphGoalTotalBuild) {
        this.periphGoalTotalBuild = periphGoalTotalBuild;
    }

    public double getPeriphCurrentTotalBuild() {
        return periphCurrentTotalBuild;
    }

    public void setPeriphCurrentTotalBuild(double periphCurrentTotalBuild) {
        this.periphCurrentTotalBuild = periphCurrentTotalBuild;
    }

    public double getOptic5sCurrentBuild() {
        return optic5sCurrentBuild;
    }

    public void setOptic5sCurrentBuild(double optic5sCurrentBuild) {
        this.optic5sCurrentBuild = optic5sCurrentBuild;
    }

    public double getOptic5sGoalBuild() {
        return optic5sGoalBuild;
    }

    public void setOptic5sGoalBuild(double optic5sGoalBuild) {
        this.optic5sGoalBuild = optic5sGoalBuild;
    }

    public double getOptic12sCurrentBuild() {
        return optic12sCurrentBuild;
    }

    public void setOptic12sCurrentBuild(double optic12sCurrentBuild) {
        this.optic12sCurrentBuild = optic12sCurrentBuild;
    }

    public double getOptic12sGoalBuild() {
        return optic12sGoalBuild;
    }

    public void setOptic12sGoalBuild(double optic12sGoalBuild) {
        this.optic12sGoalBuild = optic12sGoalBuild;
    }

    public double getCubCurrentBuild() {
        return cubCurrentBuild;
    }

    public void setCubCurrentBuild(double cubCurrentBuild) {
        this.cubCurrentBuild = cubCurrentBuild;
    }

    public double getCubGoalBuild() {
        return cubGoalBuild;
    }

    public void setCubGoalBuild(double cubGoalBuild) {
        this.cubGoalBuild = cubGoalBuild;
    }

    public double getKitsCurrentBuild() {
        return kitsCurrentBuild;
    }

    public void setKitsCurrentBuild(double kitsCurrentBuild) {
        this.kitsCurrentBuild = kitsCurrentBuild;
    }

    public double getKitsGoalBuild() {
        return kitsGoalBuild;
    }

    public void setKitsGoalBuild(double kitsGoalBuild) {
        this.kitsGoalBuild = kitsGoalBuild;
    }

    public double getPrinterCurrentBuild() {
        return printerCurrentBuild;
    }

    public void setPrinterCurrentBuild(double printerCurrentBuild) {
        this.printerCurrentBuild = printerCurrentBuild;
    }

    public double getPrinterGoalBuild() {
        return printerGoalBuild;
    }

    public void setPrinterGoalBuild(double printerGoalBuild) {
        this.printerGoalBuild = printerGoalBuild;
    }

    public double getOpticGoalTotalBuild() {
        return opticGoalTotalBuild;
    }

    public void setOpticGoalTotalBuild(double opticGoalTotalBuild) {
        this.opticGoalTotalBuild = opticGoalTotalBuild;
    }

    public double getOpticCurrentTotalBuild() {
        return opticCurrentTotalBuild;
    }

    public void setOpticCurrentTotalBuild(double opticCurrentTotalBuild) {
        this.opticCurrentTotalBuild = opticCurrentTotalBuild;
    }

    public double getPosPercentTotalBuild() {
        return posPercentTotalBuild;
    }

    public void setPosPercentTotalBuild(double posPercentTotalBuild) {
        this.posPercentTotalBuild = posPercentTotalBuild;
    }

    public double getRetailPercentTotalBuild() {
        return retailPercentTotalBuild;
    }

    public void setRetailPercentTotalBuild(double retailPercentTotalBuild) {
        this.retailPercentTotalBuild = retailPercentTotalBuild;
    }

    public double getOpticPercentTotalBuild() {
        return opticPercentTotalBuild;
    }

    public void setOpticPercentTotalBuild(double opticPercentTotalBuild) {
        this.opticPercentTotalBuild = opticPercentTotalBuild;
    }

    public double getServersPercentTotalBuild() {
        return serversPercentTotalBuild;
    }

    public void setServersPercentTotalBuild(double serversPercentTotalBuild) {
        this.serversPercentTotalBuild = serversPercentTotalBuild;
    }

    public double getPeriphPercentTotalBuild() {
        return periphPercentTotalBuild;
    }

    public void setPeriphPercentTotalBuild(double periphPercentTotalBuild) {
        this.periphPercentTotalBuild = periphPercentTotalBuild;
    }
    public double getOpticThrough() {
        return opticThrough;
    }

    public void setOpticThrough(double opticThrough) {
        this.opticThrough = opticThrough;
    }

    public double getPeriphThrough() {
        return periphThrough;
    }

    public void setPeriphThrough(double periphThrough) {
        this.periphThrough = periphThrough;
    }

    public double getServersThrough() {
        return serversThrough;
    }

    public void setServersThrough(double serversThrough) {
        this.serversThrough = serversThrough;
    }

    public double getRetailThrough() {
        return retailThrough;
    }

    public void setRetailThrough(double retailThrough) {
        this.retailThrough = retailThrough;
    }

    public double getPosThrough() {
        return posThrough;
    }

    public void setPosThrough(double posThrough) {
        this.posThrough = posThrough;
    }

    public double getQuestCurrentBuild() {
        return questCurrentBuild;
    }

    public void setQuestCurrentBuild(double questCurrentBuild) {
        this.questCurrentBuild = questCurrentBuild;
    }
    public double getQuestGoalBuild() {
        return questGoalBuild;
    }

    public void setQuestGoalBuild(double questGoalBuild) {
        this.questGoalBuild = questGoalBuild;
    }
    public double getPosBar1Goal() {
        return posBar1Goal;
    }

    public void setPosBar1Goal(double posBar1Goal) {
        this.posBar1Goal = posBar1Goal;
    }
    public double getPosBar1Total() {
        return posBar1Total;
    }

    public void setPosBar1Total(double posBar1Total) {
        this.posBar1Total = posBar1Total;
    }

    public double getRetailBar1Total() {
        return retailBar1Total;
    }

    public void setRetailBar1Total(double retailBar1Total) {
        this.retailBar1Total = retailBar1Total;
    }

    public double getRetailBar1Goal() {
        return retailBar1Goal;
    }

    public void setRetailBar1Goal(double retailBar1Goal) {
        this.retailBar1Goal = retailBar1Goal;
    }

    public double getServerBar1Total() {
        return serverBar1Total;
    }

    public void setServerBar1Total(double serverBar1Total) {
        this.serverBar1Total = serverBar1Total;
    }

    public double getServerBar1Goal() {
        return serverBar1Goal;
    }

    public void setServerBar1Goal(double serverBar1Goal) {
        this.serverBar1Goal = serverBar1Goal;
    }

    public double getServerBar2Total() {
        return serverBar2Total;
    }

    public void setServerBar2Total(double serverBar2Total) {
        this.serverBar2Total = serverBar2Total;
    }

    public double getServerBar2Goal() {
        return serverBar2Goal;
    }

    public void setServerBar2Goal(double serverBar2Goal) {
        this.serverBar2Goal = serverBar2Goal;
    }

    public double getPeriphBar1Total() {
        return periphBar1Total;
    }

    public void setPeriphBar1Total(double periphBar1Total) {
        this.periphBar1Total = periphBar1Total;
    }

    public double getPeriphBar1Goal() {
        return periphBar1Goal;
    }

    public void setPeriphBar1Goal(double periphBar1Goal) {
        this.periphBar1Goal = periphBar1Goal;
    }

    public double getPeriphBar2Total() {
        return periphBar2Total;
    }

    public void setPeriphBar2Total(double periphBar2Total) {
        this.periphBar2Total = periphBar2Total;
    }

    public double getPeriphBar2Goal() {
        return periphBar2Goal;
    }

    public void setPeriphBar2Goal(double periphBar2Goal) {
        this.periphBar2Goal = periphBar2Goal;
    }

    public double getPeriphBar3Total() {
        return periphBar3Total;
    }

    public void setPeriphBar3Total(double periphBar3Total) {
        this.periphBar3Total = periphBar3Total;
    }

    public double getPeriphBar3Goal() {
        return periphBar3Goal;
    }

    public void setPeriphBar3Goal(double periphBar3Goal) {
        this.periphBar3Goal = periphBar3Goal;
    }
    public void setPosBuild(Tile posBuild) {
        this.posBuild = posBuild;
    }

    public void setServersBuild(Tile serversBuild) {
        this.serversBuild = serversBuild;
    }

    public void setPeriphBuild(Tile periphBuild) {
        this.periphBuild = periphBuild;
    }

    public void setOpticBuild(Tile opticBuild) {
        this.opticBuild = opticBuild;
    }

    public void setRetailBuild(Tile retailBuild) {
        this.retailBuild = retailBuild;
    }

    public void setPosPercent(Tile posPercent) {
        this.posPercent = posPercent;
    }

    public void setServersPercent(Tile serversPercent) {
        this.serversPercent = serversPercent;
    }

    public void setPeriphPercent(Tile periphPercent) {
        this.periphPercent = periphPercent;
    }

    public void setOpticPercent(Tile opticPercent) {
        this.opticPercent = opticPercent;
    }

    public void setRetailPercent(Tile retailPercent) {
        this.retailPercent = retailPercent;
    }

    public void setPosFTT(Tile posFTT) {
        this.posFTT = posFTT;
    }

    public void setServersFTT(Tile serversFTT) {
        this.serversFTT = serversFTT;
    }

    public void setPeriphFTT(Tile periphFTT) {
        this.periphFTT = periphFTT;
    }

    public void setOpticFTT(Tile opticFTT) {
        this.opticFTT = opticFTT;
    }

    public void setRetailFTT(Tile retailFTT) {
        this.retailFTT = retailFTT;
    }
    public BarChartItem getPosBar1Data() {
        return posBar1Data;
    }

    public void setPosBar1Data(BarChartItem posBar1Data) {
        this.posBar1Data = posBar1Data;
    }

    public BarChartItem getP1x30Data() {
        return p1x30Data;
    }

    public void setP1x30Data(BarChartItem p1x30Data) {
        this.p1x30Data = p1x30Data;
    }

    public BarChartItem getT1000Data() {
        return t1000Data;
    }

    public void setT1000Data(BarChartItem t1000Data) {
        this.t1000Data = t1000Data;
    }

    public BarChartItem getQuestData() {
        return questData;
    }

    public void setQuestData(BarChartItem questData) {
        this.questData = questData;
    }

    public BarChartItem getServerBar1() {
        return serverBar1;
    }

    public void setServerBar1(BarChartItem serverBar1) {
        this.serverBar1 = serverBar1;
    }

    public BarChartItem getServerBar2() {
        return serverBar2;
    }

    public void setServerBar2(BarChartItem serverBar2) {
        this.serverBar2 = serverBar2;
    }

    public BarChartItem getPeriphBar1Data() {
        return periphBar1Data;
    }

    public void setPeriphBar1Data(BarChartItem periphBar1Data) {
        this.periphBar1Data = periphBar1Data;
    }

    public BarChartItem getPeriphBar2Data() {
        return periphBar2Data;
    }

    public void setPeriphBar2Data(BarChartItem periphBar2Data) {
        this.periphBar2Data = periphBar2Data;
    }

    public BarChartItem getPeriphBar3Data() {
        return periphBar3Data;
    }

    public void setPeriphBar3Data(BarChartItem periphBar3Data) {
        this.periphBar3Data = periphBar3Data;
    }

    public BarChartItem getOptic12Data() {
        return optic12Data;
    }

    public void setOptic12Data(BarChartItem optic12Data) {
        this.optic12Data = optic12Data;
    }

    public BarChartItem getOptic5Data() {
        return optic5Data;
    }

    public void setOptic5Data(BarChartItem optic5Data) {
        this.optic5Data = optic5Data;
    }

    public BarChartItem getRetailBar1Data() {
        return retailBar1Data;
    }

    public void setRetailBar1Data(BarChartItem retailBar1Data) {
        this.retailBar1Data = retailBar1Data;
    }

    public BarChartItem getNextGenDisplays() {
        return nextGenDisplays;
    }

    public void setNextGenDisplays(BarChartItem nextGenDisplays) {
        this.nextGenDisplays = nextGenDisplays;
    }
}
