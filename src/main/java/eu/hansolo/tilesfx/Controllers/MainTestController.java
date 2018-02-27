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

public class MainTestController extends Controller implements Initializable,Methods
{
    @FXML
    Tile posTest;
    @FXML
    Tile retailTest;
    @FXML
    Tile serversTest;
    @FXML
    Tile periphTest;
    @FXML
    Tile opticTest;
    @FXML
    Tile posTestPercent;
    @FXML
    Tile posFTT;
    @FXML
    Tile retailTestPercent;
    @FXML
    Tile retailFTT;
    @FXML
    Tile serversTestPercent;
    @FXML
    Tile serversFTT;
    @FXML
    Tile periphTestPercent;
    @FXML
    Tile periphFTT;
    @FXML
    Tile opticTestPercent;
    @FXML
    Tile opticFTT;

    @FXML
    private GridPane pane;

    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    private double p1x30CurrentBuild;
    private double p1x30GoalBuild;
    private double p1x35CurrentBuild;
    private double p1x35GoalBuild;
    private double p1532CurrentBuild;
    private double p1532GoalBuild;
    private double t1000sCurrentBuild;
    private double t1000sGoalBuild;

    private double posBar1Total;
    private double posBar1Goal;
    private double questGoalBuild;
    private double questCurrentBuild;


    private double p1x30CurrentTest;
    private double p1x35CurrentTest;
    private double p1532CurrentTest;
    private double t1000sCurrentTest;
    private double questsCurrentTest;

    private double posTotalGoalBuild;
    private double posTotalCurrentBuild;
    private double posTotalCurrentTest;


     //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    private double xr7CurrentBuild;
    private double xr7GoalBuild;
    private double xr7PlusCurrentBuild;
    private double xr7PlusGoalBuild;
    private double xr5CurrentBuild;
    private double xr5GoalBuild;
    private double nextGenDisplayCurrentBuild;
    private double nextGenDisplayGoalsBuild;

    private double xr7CurrentTest;
    private double xr7PlusCurrentTest;
    private double xr5CurrentTest;
    private double nextGenDisplayCurrentTest;


    private double retailTotalGoalBuild;

    private double retailTotalCurrentBuild;
    private double retailTotalCurrentTest;

     //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    private double mediaPlayerCurrentBuild;
    private double mediaPlayerGoalBuild;
    private double n3000CurrentBuild;
    private double n3000GoalBuild;
    private double s500CurrentBuild;
    private double s500GoalBuild;


    private double mediaPlayerCurrentTest;
    private double n3000CurrentTest;
    private double s500CurrentTest;

    private double serverGoalTotalBuild;

    private double serverCurrentBuild;
    private double serverCurrentTest;

     //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    private double kiwi4sCurrentBuild;
    private double kiwi4sGoalBuild;
    private double kiwi2XsCurrentBuild;
    private double kiwi2XsGoalBuild;
    private double bumpBarsCurrentBuild;
    private double bumpBarsGoalBuild;
    private double pantherEPC4sCurrentBuild;
    private double pantherEPC4sGoalBuild;

    private double kiwi4sCurrentTest;
    private double kiwi2XsCurrentTest;
    private double bumpBarsCurrentTest;
    private double pantherEPC4sCurrentTest;

    private double periphGoalTotalBuild;
    private double periphGoalTotalStage;

    private double periphCurrentTotalBuild;
    private double periphCurrentTotalTest;
     //---------------------------------Variables for Query Data (Optic)--------------------------------------------
    private double optic5sCurrentBuild;
    private double optic5sGoalBuild;
    private double optic12sCurrentBuild;
    private double optic12sGoalBuild;
    private double cubCurrentBuild;
    private double cubGoalBuild;
    private double kitsCurrentBuild;
    private double kitsGoalBuild;
    private double printerCurrentBuild;
    private double printerGoalBuild;

    private double optic5sCurrentTest;
    private double optic12sCurrentTest;
    private double cubCurrentTest;
    private double cubGoalTest;
    private double kitsCurrentTest;
    private double kitsGoalTest;
    private double printerCurrentTest;
    private double printerGoalTest;

    private double opticGoalTotalBuild;
    private double opticGoalTotalStage;


    private double opticCurrentTotalBuild;
    private double opticCurrentTotalTest;

     //---------------------------------Variables for Percentages --------------------------------------------------
    private double posPercentTotalTest;
    private double retailPercentTotalTest;
    private double opticPercentTotalTest;
    private double serversPercentTotalTest;
    private double periphPercentTotalTest;
     //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
    private BarChartItem posBar1DataTest;
    private BarChartItem p1x30DataTest;
    private BarChartItem t1000DataTest;
    private BarChartItem questDataTest;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem serverBar1DataTest;
    private BarChartItem serverBar2DataTest;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem periphBar1DataTest;
    private BarChartItem periphBar2DataTest;
    private BarChartItem periphBar3DataTest;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem optic12DataTest;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem retailBar1DataTest;
    private BarChartItem nextGenDisplaysTest;

    private double opticThrough;
    private double periphThrough;
    private double serversThrough;
    private double retailThrough;
    private double posThrough;

    private double retailBar1Total;
    private double retailBar1Goal;

    private double serverBar1Total;
    private double serverBar1Goal;

    private double serverBar2Total;
    private double serverBar2Goal;

    private double periphBar1Total;
    private double periphBar1Goal;

    private double periphBar2Total;
    private double periphBar2Goal;

    private double periphBar3Total;
    private double periphBar3Goal;

    private Messenger messenger;

    private DecimalFormat df = new DecimalFormat("#.0");
    private DecimalFormat hundred = new DecimalFormat("#");

    private ArrayList<Tile> tiles;

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tiles = new ArrayList<>();
        MainBuildController buildController = messenger.getMainBuildController();

        //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
        posBar1DataTest = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30DataTest = new BarChartItem("7743", p1x30CurrentTest, p1x30GoalBuild, Tile.ORANGE);
        t1000DataTest = new BarChartItem("7744", t1000sCurrentTest, t1000sGoalBuild, Tile.GREEN);
        questDataTest = new BarChartItem("7791/7792", questsCurrentTest, questGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1DataTest = new BarChartItem("1611/1612", serverBar1Total,serverBar1Goal, Tile.BLUE);
        serverBar2DataTest = new BarChartItem("1656/1657", serverBar2Total, serverBar2Goal, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Periph-------------------------------------
        periphBar1DataTest = new BarChartItem("1635",periphBar1Total,periphBar1Goal, Tile.BLUE);
        periphBar2DataTest = new BarChartItem("1642/1924",periphBar2Total,periphBar2Goal, Tile.ORANGE);
        periphBar3DataTest = new BarChartItem("1646/1651",periphBar3Total,periphBar3Goal, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Optic-------------------------------------
        optic12DataTest = new BarChartItem("6002", optic12sCurrentTest, optic12sGoalBuild, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Retail-------------------------------------
        retailBar1DataTest = new BarChartItem("770X", retailBar1Total,retailBar1Goal, Tile.BLUE);
        nextGenDisplaysTest = new BarChartItem("5968/5985", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.ORANGE);
        //---------------------------------Creating the Tiles-----------------------------------------------------------
        posTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(posBar1DataTest, p1x30DataTest, t1000DataTest, questDataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posTestPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
            .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
            .textAlignment(TextAlignment.CENTER)
            .text("Percentage to Goal")
            .unit(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
            .animated(true)
            .animationDuration(3000)
            .roundedCorners(false)
            .subText(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
            .value(posPercentTotalTest)
            .build();

        posFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPosThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(posTest,0,0,1,2);
        pane.add(posTestPercent,0,2,1,1);
        pane.add(posFTT,0,3,1,1);

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retailTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .title("Retail Test")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(retailBar1DataTest, nextGenDisplaysTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailTestPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(retailPercentTotalTest)
                .build();

        retailFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getRetailThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();


        pane.add(retailTest,1,0,1,2);
        pane.add(retailTestPercent,1,2,1,1);
        pane.add(retailFTT,1,3,1,1);

        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(serverBar1DataTest,serverBar2DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversTestPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .subText(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalTest)
                .build();

        serversFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getServersThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();


        pane.add(serversTest,2,0,1,2);
        pane.add(serversTestPercent,2,2,1,1);
        pane.add(serversFTT,2,3,1,1);

        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        periphTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(periphBar1DataTest,periphBar2DataTest,periphBar3DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphTestPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(periphPercentTotalTest)
                .build();

        periphFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPeriphThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(periphTest,3,0,1,2);
        pane.add(periphTestPercent,3,2,1,1);
        pane.add(periphFTT,3,3,1,1);


        //---------------------------------Creating the Tiles for Optic-------------------------------------------------
        opticTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(optic12DataTest)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticTestPercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalTest) + "/" + Double.toString(buildController.getOpticGoalTotalBuild()))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(opticPercentTotalTest)
                .build();

        opticFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("FTT")
                .description(df.format(messenger.getMainBuildController().getOpticThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(opticTest,4,0,1,2);
        pane.add(opticTestPercent,4,2,1,1);
        pane.add(opticFTT,4,3,1,1);

        createActions();

        tiles.add(posTest);
        tiles.add(retailTest);
        tiles.add(serversTest);
        tiles.add(periphTest);
        tiles.add(opticTest);
        tiles.add(posTestPercent);
        tiles.add(posFTT);
        tiles.add(retailTestPercent);
        tiles.add(retailFTT);
        tiles.add(serversTestPercent);
        tiles.add(serversFTT);
        tiles.add(periphTestPercent);
        tiles.add(periphFTT);
        tiles.add(opticTestPercent);
        tiles.add(opticFTT);

        if(messenger.getMainBuildController().getPosThrough() == 100)
        {
            posFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough())+"%");
        }
        if(messenger.getMainBuildController().getServersThrough() == 100)
        {
            serversFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough())+"%");
        }
        if(messenger.getMainBuildController().getPeriphThrough() == 100)
        {
            periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
        }
        if(messenger.getMainBuildController().getOpticThrough() == 100)
        {
            opticFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough())+"%");
        }
        if(messenger.getMainBuildController().getRetailThrough() == 100)
        {
            retailFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough())+"%");
        }

        super.tilesListeners(tiles,messenger);
        refresh();
    }

    public void refresh()
    {
        Platform.runLater(() ->
        {
            posBar1DataTest.setValue(posBar1Total);
            posBar1DataTest.setMaxValue(posBar1Goal);
            p1x30DataTest.setValue(p1x30CurrentTest);
            p1x30DataTest.setMaxValue(p1x30GoalBuild);
            t1000DataTest.setValue(t1000sCurrentTest);
            t1000DataTest.setMaxValue(t1000sGoalBuild);
            questDataTest.setValue(questsCurrentTest);
            questDataTest.setMaxValue(questGoalBuild);

            //---------------------------------Update the Server Units------------------------------------------
            serverBar1DataTest.setValue(serverBar1Total);
            serverBar1DataTest.setMaxValue(serverBar1Goal);
            serverBar2DataTest.setValue(serverBar2Total);
            serverBar2DataTest.setMaxValue(serverBar2Goal);

            //---------------------------------Updating the Peripheral Units------------------------------------
            periphBar1DataTest.setValue(periphBar1Total);
            periphBar1DataTest.setMaxValue(periphBar1Goal);
            periphBar2DataTest.setValue(periphBar2Total);
            periphBar2DataTest.setMaxValue(periphBar2Goal);
            periphBar3DataTest.setValue(periphBar3Total);
            periphBar3DataTest.setMaxValue(periphBar3Goal);

            //---------------------------------Updating the Optic Units------------------------------------
            optic12DataTest.setValue(optic12sCurrentTest);
            optic12DataTest.setMaxValue(optic12sGoalBuild);

            //---------------------------------Updating the Retail Units------------------------------------
            retailBar1DataTest.setValue(retailBar1Total);
            retailBar1DataTest.setMaxValue(retailBar1Goal);
            nextGenDisplaysTest.setValue(nextGenDisplayCurrentTest);
            nextGenDisplaysTest.setMaxValue(nextGenDisplayGoalsBuild);

            //---------------------------------Creating Color Changes for POS Dial------------------------------------------
            posTestPercent.setValue(posPercentTotalTest);
            posFTT.setDescription(df.format(messenger.getMainBuildController().getPosThrough())+"%");
            if(messenger.getMainBuildController().getPosThrough() == 100)
            {
                posFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough())+"%");
            }

            changePercent(posTestPercent, posTotalCurrentTest, posTotalGoalBuild, posPercentTotalTest);
            changePercent(posFTT, posTotalCurrentTest, posTotalGoalBuild, posPercentTotalTest);

            //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
            serversTestPercent.setValue(serversPercentTotalTest);
            serversFTT.setDescription(df.format(messenger.getMainBuildController().getServersThrough())+"%");
            if(messenger.getMainBuildController().getServersThrough() == 100)
            {
                serversFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough())+"%");
            }

            changePercent(serversTestPercent, serverCurrentTest, serverGoalTotalBuild, serversPercentTotalTest);
            changePercent(serversFTT, serverCurrentTest, serverGoalTotalBuild, serversPercentTotalTest);

            //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
            periphTestPercent.setValue(periphPercentTotalTest);
            periphFTT.setDescription(df.format(messenger.getMainBuildController().getPeriphThrough())+"%");
            if(messenger.getMainBuildController().getPeriphThrough() == 100)
            {
                periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
            }

            changePercent(periphTestPercent, periphCurrentTotalTest, periphGoalTotalBuild, periphPercentTotalTest);
            changePercent(periphFTT, periphCurrentTotalTest, periphGoalTotalBuild, periphPercentTotalTest);

            //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
            opticTestPercent.setValue(opticPercentTotalTest);
            opticFTT.setDescription(df.format(messenger.getMainBuildController().getOpticThrough())+"%");
            if(messenger.getMainBuildController().getOpticThrough() == 100)
            {
                opticFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough())+"%");
            }

            changePercent(opticTestPercent, opticCurrentTotalTest, opticGoalTotalBuild, opticPercentTotalTest);
            changePercent(opticFTT, opticCurrentTotalTest, opticGoalTotalBuild, opticPercentTotalTest);

            //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
            retailTestPercent.setValue(retailPercentTotalTest);
            retailFTT.setDescription(df.format(messenger.getMainBuildController().getRetailThrough())+"%");
            if(messenger.getMainBuildController().getRetailThrough() == 100)
            {
               retailFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough())+"%");
            }

            changePercent(retailTestPercent, retailTotalCurrentTest, retailTotalGoalBuild, retailPercentTotalTest);
            changePercent(retailFTT, retailTotalCurrentTest, retailTotalGoalBuild, retailPercentTotalTest);
        });
    }
    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                if(event.getCode() == KeyCode.LEFT)
                {
                    messenger.getPrimaryStage().setScene(messenger.getMainBuild());
                }
                if(event.getCode() == KeyCode.RIGHT)
                {
                    messenger.getPrimaryStage().setScene(messenger.getMainStage());
                }
                if(event.getCode() == KeyCode.T && event.isControlDown())
                { final Stage dialog = new Stage();
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
        posTestPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosBuild());
            }
        });
        retailTestPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
            }
        });
        serversTestPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerBuild());
            }
        });
        periphTestPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
            }
        });
        opticTestPercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
            }
        });
        posTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPosBuild());
            }
        });
        retailTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuild());

            }
        });
        serversTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerBuild());
            }
        });
        periphTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
            }
        });
        opticTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuild());

            }
        });
        posFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosBuild());
            }
        });
        retailFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
            }
        });
        serversFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerBuild());
            }
        });
        periphFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
            }
        });
        opticFTT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
            }
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


    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public double getP1x30GoalBuild() {
        return p1x30GoalBuild;
    }

    public void setP1x30GoalBuild(double p1x30GoalBuild) {
        this.p1x30GoalBuild = p1x30GoalBuild;
    }

    public double getT1000sGoalBuild() {
        return t1000sGoalBuild;
    }

    public void setT1000sGoalBuild(double t1000sGoalBuild) {
        this.t1000sGoalBuild = t1000sGoalBuild;
    }

    public double getP1x30CurrentTest() {
        return p1x30CurrentTest;
    }

    public void setP1x30CurrentTest(double p1x30CurrentTest) {
        this.p1x30CurrentTest = p1x30CurrentTest;
    }

    public double getP1x35CurrentTest() {
        return p1x35CurrentTest;
    }

    public double getP1532CurrentTest() {
        return p1532CurrentTest;
    }

    public double getT1000sCurrentTest() {
        return t1000sCurrentTest;
    }

    public void setT1000sCurrentTest(double t1000sCurrentTest) {
        this.t1000sCurrentTest = t1000sCurrentTest;
    }

    public double getQuestsCurrentTest() {
        return questsCurrentTest;
    }

    public void setQuestsCurrentTest(double questsCurrentTest) {
        this.questsCurrentTest = questsCurrentTest;
    }

    public double getPosTotalGoalBuild() {
        return posTotalGoalBuild;
    }

    public void setPosTotalGoalBuild(double posTotalGoalBuild) {
        this.posTotalGoalBuild = posTotalGoalBuild;
    }

    public double getPosTotalCurrentTest() {
        return posTotalCurrentTest;
    }

    public void setPosTotalCurrentTest(double posTotalCurrentTest) {
        this.posTotalCurrentTest = posTotalCurrentTest;
    }

    public double getNextGenDisplayGoalsBuild() {
        return nextGenDisplayGoalsBuild;
    }

    public void setNextGenDisplayGoalsBuild(double nextGenDisplayGoalsBuild) {
        this.nextGenDisplayGoalsBuild = nextGenDisplayGoalsBuild;
    }

    public double getXr7CurrentTest() {
        return xr7CurrentTest;
    }

    public double getXr7PlusCurrentTest() {
        return xr7PlusCurrentTest;
    }

    public double getXr5CurrentTest() {
        return xr5CurrentTest;
    }

    public double getNextGenDisplayCurrentTest() {
        return nextGenDisplayCurrentTest;
    }

    public void setNextGenDisplayCurrentTest(double nextGenDisplayCurrentTest) {
        this.nextGenDisplayCurrentTest = nextGenDisplayCurrentTest;
    }

    public double getRetailTotalGoalBuild() {
        return retailTotalGoalBuild;
    }

    public void setRetailTotalGoalBuild(double retailTotalGoalBuild) {
        this.retailTotalGoalBuild = retailTotalGoalBuild;
    }

    public double getRetailTotalCurrentTest() {
        return retailTotalCurrentTest;
    }

    public void setRetailTotalCurrentTest(double retailTotalCurrentTest) {
        this.retailTotalCurrentTest = retailTotalCurrentTest;
    }

    public double getMediaPlayerCurrentTest() {
        return mediaPlayerCurrentTest;
    }

    public double getN3000CurrentTest() {
        return n3000CurrentTest;
    }

    public double getS500CurrentTest() {
        return s500CurrentTest;
    }

    public void setServerGoalTotalBuild(double serverGoalTotalBuild) {
        this.serverGoalTotalBuild = serverGoalTotalBuild;
    }

    public double getServerCurrentTest() {
        return serverCurrentTest;
    }

    public void setServerCurrentTest(double serverCurrentTest) {
        this.serverCurrentTest = serverCurrentTest;
    }

    public double getKiwi4sCurrentTest() {
        return kiwi4sCurrentTest;
    }

    public double getKiwi2XsCurrentTest() {
        return kiwi2XsCurrentTest;
    }

    public double getBumpBarsCurrentTest() {
        return bumpBarsCurrentTest;
    }

    public double getPantherEPC4sCurrentTest() {
        return pantherEPC4sCurrentTest;
    }

    public void setPeriphGoalTotalBuild(double periphGoalTotalBuild) {
        this.periphGoalTotalBuild = periphGoalTotalBuild;
    }

    public double getPeriphCurrentTotalTest() {
        return periphCurrentTotalTest;
    }

    public void setPeriphCurrentTotalTest(double periphCurrentTotalTest) {
        this.periphCurrentTotalTest = periphCurrentTotalTest;
    }

    public void setOptic5sGoalBuild(double optic5sGoalBuild) {
        this.optic5sGoalBuild = optic5sGoalBuild;
    }

    public void setOptic12sGoalBuild(double optic12sGoalBuild) {
        this.optic12sGoalBuild = optic12sGoalBuild;
    }

    public void setKitsGoalBuild(double kitsGoalBuild) {
        this.kitsGoalBuild = kitsGoalBuild;
    }

    public double getOptic12sCurrentTest() {
        return optic12sCurrentTest;
    }

    public void setOptic12sCurrentTest(double optic12sCurrentTest) {
        this.optic12sCurrentTest = optic12sCurrentTest;
    }

    public void setKitsCurrentTest(double kitsCurrentTest) {
        this.kitsCurrentTest = kitsCurrentTest;
    }

    public void setOpticGoalTotalBuild(double opticGoalTotalBuild) {
        this.opticGoalTotalBuild = opticGoalTotalBuild;
    }

    public double getOpticCurrentTotalTest() {
        return opticCurrentTotalTest;
    }

    public void setOpticCurrentTotalTest(double opticCurrentTotalTest) {
        this.opticCurrentTotalTest = opticCurrentTotalTest;
    }

    public double getPosPercentTotalTest() {
        return posPercentTotalTest;
    }

    public void setPosPercentTotalTest(double posPercentTotalTest) {
        this.posPercentTotalTest = posPercentTotalTest;
    }

    public double getRetailPercentTotalTest() {
        return retailPercentTotalTest;
    }

    public void setRetailPercentTotalTest(double retailPercentTotalTest) {
        this.retailPercentTotalTest = retailPercentTotalTest;
    }

    public double getOpticPercentTotalTest() {
        return opticPercentTotalTest;
    }

    public void setOpticPercentTotalTest(double opticPercentTotalTest) {
        this.opticPercentTotalTest = opticPercentTotalTest;
    }

    public double getServersPercentTotalTest() {
        return serversPercentTotalTest;
    }

    public void setServersPercentTotalTest(double serversPercentTotalTest) {
        this.serversPercentTotalTest = serversPercentTotalTest;
    }

    public double getPeriphPercentTotalTest() {
        return periphPercentTotalTest;
    }

    public void setPeriphPercentTotalTest(double periphPercentTotalTest) {
        this.periphPercentTotalTest = periphPercentTotalTest;
    }

    public double getPosBar1Total() {
        return posBar1Total;
    }

    public void setPosBar1Total(double posBar1Total) {
        this.posBar1Total = posBar1Total;
    }

    public double getPosBar1Goal() {
        return posBar1Goal;
    }

    public void setPosBar1Goal(double posBar1Goal) {
        this.posBar1Goal = posBar1Goal;
    }

    public double getQuestGoalBuild() {
        return questGoalBuild;
    }

    public void setQuestGoalBuild(double questGoalBuild) {
        this.questGoalBuild = questGoalBuild;
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

}
