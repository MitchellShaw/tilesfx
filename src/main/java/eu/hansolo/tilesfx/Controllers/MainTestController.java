package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.Messenger;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainTestController implements Initializable
{
    Tile posTest;
    Tile retailTest;
    Tile serversTest;
    Tile periphTest;
    Tile opticTest;
    Tile posTestPercent;
    Tile posFTT;
    Tile retailTestPercent;
    Tile retailFTT;
    Tile serversTestPercent;
    Tile serversFTT;
    Tile periphTestPercent;
    Tile periphFTT;
    Tile opticTestPercent;
    Tile opticFTT;

    @FXML
    private GridPane pane;

    double x = 0;
    double y = 0;
    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    double p1x30CurrentBuild;
    double p1x30GoalBuild;
    double p1x35CurrentBuild;
    double p1x35GoalBuild;
    double p1532CurrentBuild;
    double p1532GoalBuild;
    double t1000sCurrentBuild;
    double t1000sGoalBuild;


    double p1x30CurrentTest;
    double p1x35CurrentTest;
    double p1532CurrentTest;
    double t1000sCurrentTest;
    double questsCurrentTest;

    double posTotalGoalBuild;
    double posTotalCurrentBuild;
    double posTotalCurrentTest;


    //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    double xr7CurrentBuild;
    double xr7GoalBuild;
    double xr7PlusCurrentBuild;
    double xr7PlusGoalBuild;
    double xr5CurrentBuild;
    double xr5GoalBuild;
    double nextGenDisplayCurrentBuild;
    double nextGenDisplayGoalsBuild;

    double xr7CurrentTest;
    double xr7PlusCurrentTest;
    double xr5CurrentTest;
    double nextGenDisplayCurrentTest;


    double retailTotalGoalBuild;

    double retailTotalCurrentBuild;
    double retailTotalCurrentTest;

    //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    double mediaPlayerCurrentBuild;
    double mediaPlayerGoalBuild;
    double n3000CurrentBuild;
    double n3000GoalBuild;
    double s500CurrentBuild;
    double s500GoalBuild;


    double mediaPlayerCurrentTest;
    double n3000CurrentTest;
    double s500CurrentTest;

    double serverGoalTotalBuild;

    double serverCurrentBuild;
    double serverCurrentTest;

    //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    double kiwi4sCurrentBuild;
    double kiwi4sGoalBuild;
    double kiwi2XsCurrentBuild;
    double kiwi2XsGoalBuild;
    double bumpBarsCurrentBuild;
    double bumpBarsGoalBuild;
    double pantherEPC4sCurrentBuild;
    double pantherEPC4sGoalBuild;

    double kiwi4sCurrentTest;
    double kiwi2XsCurrentTest;
    double bumpBarsCurrentTest;
    double pantherEPC4sCurrentTest;

    double periphGoalTotalBuild;
    double periphGoalTotalStage;

    double periphCurrentTotalBuild;
    double periphCurrentTotalTest;
    //---------------------------------Variables for Query Data (Optic)--------------------------------------------
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

    double optic5sCurrentTest;
    double optic12sCurrentTest;
    double cubCurrentTest;
    double cubGoalTest;
    double kitsCurrentTest;
    double kitsGoalTest;
    double printerCurrentTest;
    double printerGoalTest;

    double opticGoalTotalBuild;
    double opticGoalTotalStage;

    double opticCurrentTotalBuild;
    double opticCurrentTotalTest;

    //---------------------------------Variables for Percentages --------------------------------------------------
    double posPercentTotalTest;
    double retailPercentTotalTest;
    double opticPercentTotalTest;
    double serversPercentTotalTest;
    double periphPercentTotalTest;
    //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
    BarChartItem p1x35DataTest;
    BarChartItem p1532DataTest;
    BarChartItem p1x30DataTest;
    BarChartItem t1000DataTest;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem n3000Test;
    BarChartItem s500DataTest;
    BarChartItem mediaPlayerTest;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem kiwi4DataTest;
    BarChartItem kiwi25DataTest;
    BarChartItem bumpBarDataTest;
    BarChartItem pantherEPC4Test;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem optic12DataTest;
    BarChartItem optic5DataTest;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem xr5DataTest;
    BarChartItem xr7DataTest;
    BarChartItem xr7PlusDataTest;
    BarChartItem nextGenDisplaysTest;

    double opticThrough;
    double periphThrough;
    double serversThrough;
    double retailThrough;
    double posThrough;

    Messenger messenger;

    DecimalFormat df = new DecimalFormat("#.0");

    ArrayList<Tile> tiles;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tiles = new ArrayList<>();
        MainBuildController buildController = messenger.getMainBuildController();

        //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
        p1x35DataTest = new BarChartItem("P1X35", p1x35CurrentTest, p1x35GoalBuild, Tile.RED);
        p1532DataTest = new BarChartItem("P1532", p1532CurrentTest, p1532GoalBuild, Tile.GREEN);
        p1x30DataTest = new BarChartItem("P1X30", p1x30CurrentTest, p1x30GoalBuild, Tile.BLUE);
        t1000DataTest = new BarChartItem("T1000", t1000sCurrentTest, t1000sGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        n3000Test = new BarChartItem("N3000", n3000CurrentTest, n3000GoalBuild, Tile.RED);
        s500DataTest = new BarChartItem("S500", s500CurrentTest, s500GoalBuild, Tile.BLUE);
        mediaPlayerTest = new BarChartItem("Media Player", mediaPlayerCurrentTest, mediaPlayerGoalBuild, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        kiwi4DataTest = new BarChartItem("Kiwi 4", kiwi4sCurrentTest, kiwi4sGoalBuild, Tile.BLUE);
        kiwi25DataTest = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentTest, kiwi2XsGoalBuild, Tile.RED);
        bumpBarDataTest = new BarChartItem("Bumpbar", bumpBarsCurrentTest, bumpBarsGoalBuild, Tile.GREEN);
        pantherEPC4Test = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentTest, pantherEPC4sGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        optic12DataTest = new BarChartItem("Optic 12", optic12sCurrentTest, optic12sGoalBuild, Tile.RED);
        optic5DataTest = new BarChartItem("Optic 5", optic5sCurrentTest, optic5sGoalBuild, Tile.BLUE);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        xr5DataTest = new BarChartItem("7701", xr5CurrentTest, xr5GoalBuild, Tile.BLUE);
        xr7DataTest = new BarChartItem("7702", xr7CurrentTest, xr7GoalBuild, Tile.RED);
        xr7PlusDataTest = new BarChartItem("7703", xr7PlusCurrentTest, xr7PlusGoalBuild, Tile.GREEN);
        nextGenDisplaysTest = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.YELLOW);
        //---------------------------------Creating the Tiles-----------------------------------------------------------
        posTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(384, 470)
                .barChartItems(p1x30DataTest, p1x35DataTest, p1532DataTest, t1000DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posTestPercent = TileBuilder.create()
            .prefSize(384, 440)
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
        System.out.println(posTotalGoalBuild);

        posFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 440)
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
                .prefSize(384, 640)
                .barChartItems(xr5DataTest, xr7DataTest, xr7PlusDataTest, nextGenDisplaysTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailTestPercent = TileBuilder.create()
                .prefSize(384, 440)
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
                .prefSize(384, 440)
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
                .prefSize(384, 200)
                .barChartItems(s500DataTest, n3000Test, mediaPlayerTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversTestPercent = TileBuilder.create()
                .prefSize(384, 440)
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
                .prefSize(384, 440)
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
                .prefSize(384, 640)
                .barChartItems(kiwi4DataTest, kiwi25DataTest, bumpBarDataTest, pantherEPC4Test)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphTestPercent = TileBuilder.create()
                .prefSize(384, 440)
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
                .prefSize(384, 440)
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
                .prefSize(384, 640)
                .barChartItems(optic5DataTest, optic12DataTest)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticTestPercent = TileBuilder.create()
                .prefSize(384, 440)
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
                .prefSize(384, 440)
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

        tilesListeners(tiles);
        refresh();
    }

    public void refresh()
    {
        Platform.runLater(() ->
        {
            p1x35DataTest.setValue(p1x35CurrentTest);
            p1x35DataTest.setMaxValue(p1x35GoalBuild);
            p1532DataTest.setValue(p1532CurrentTest);
            p1532DataTest.setMaxValue(p1532GoalBuild);
            p1x30DataTest.setValue(p1x30CurrentTest);
            p1x30DataTest.setMaxValue(p1x30GoalBuild);
            t1000DataTest.setValue(t1000sCurrentTest);
            t1000DataTest.setMaxValue(t1000sGoalBuild);

            //---------------------------------Update the Server Units------------------------------------------
            n3000Test.setValue(n3000CurrentTest);
            n3000Test.setMaxValue(n3000GoalBuild);
            s500DataTest.setValue(s500CurrentTest);
            s500DataTest.setMaxValue(s500GoalBuild);
            mediaPlayerTest.setValue(mediaPlayerCurrentTest);
            mediaPlayerTest.setMaxValue(mediaPlayerGoalBuild);

            //---------------------------------Updating the Peripheral Units------------------------------------
            kiwi4DataTest.setValue(kiwi4sCurrentTest);
            kiwi4DataTest.setMaxValue(kiwi4sGoalBuild);
            kiwi25DataTest.setValue(kiwi2XsCurrentTest);
            kiwi25DataTest.setMaxValue(kiwi2XsGoalBuild);
            bumpBarDataTest.setValue(bumpBarsCurrentTest);
            bumpBarDataTest.setMaxValue(bumpBarsGoalBuild);
            pantherEPC4Test.setValue(pantherEPC4sCurrentTest);
            pantherEPC4Test.setMaxValue(pantherEPC4sGoalBuild);
            optic5DataTest.setValue(optic5sCurrentTest);
            optic5DataTest.setMaxValue(optic5sGoalBuild);
            optic12DataTest.setValue(optic12sCurrentTest);
            optic12DataTest.setMaxValue(optic12sGoalBuild);
            xr5DataTest.setValue(xr5CurrentTest);
            xr5DataTest.setMaxValue(xr5GoalBuild);
            xr7DataTest.setValue(xr7CurrentTest);
            xr7DataTest.setMaxValue(xr7GoalBuild);
            xr7PlusDataTest.setValue(xr7PlusCurrentTest);
            xr7PlusDataTest.setMaxValue(xr7PlusGoalBuild);
            nextGenDisplaysTest.setValue(nextGenDisplayCurrentTest);
            nextGenDisplaysTest.setMaxValue(nextGenDisplayGoalsBuild);

            //---------------------------------Creating Color Changes for POS Dial------------------------------------------
            posTestPercent.setValue(posPercentTotalTest);
            posFTT.setDescription(df.format(messenger.getMainBuildController().getPosThrough())+"%");

            changePercent(posTestPercent, posTotalCurrentTest, posTotalGoalBuild, posPercentTotalTest);
            changePercent(posFTT, posTotalCurrentTest, posTotalGoalBuild, posPercentTotalTest);

            //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
            serversTestPercent.setValue(serversPercentTotalTest);
            serversFTT.setDescription(df.format(messenger.getMainBuildController().getServersThrough())+"%");

            changePercent(serversTestPercent, serverCurrentTest, serverGoalTotalBuild, serversPercentTotalTest);
            changePercent(serversFTT, serverCurrentTest, serverGoalTotalBuild, serversPercentTotalTest);

            //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
            periphTestPercent.setValue(periphPercentTotalTest);
            periphFTT.setDescription(df.format(messenger.getMainBuildController().getPeriphThrough())+"%");

            changePercent(periphTestPercent, periphCurrentTotalTest, periphGoalTotalBuild, periphPercentTotalTest);
            changePercent(periphFTT, periphCurrentTotalTest, periphGoalTotalBuild, periphPercentTotalTest);

            //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
            opticTestPercent.setValue(opticPercentTotalTest);
            opticFTT.setDescription(df.format(messenger.getMainBuildController().getOpticThrough())+"%");

            changePercent(opticTestPercent, opticCurrentTotalTest, opticGoalTotalBuild, opticPercentTotalTest);
            changePercent(opticFTT, opticCurrentTotalTest, opticGoalTotalBuild, opticPercentTotalTest);

            //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
            retailTestPercent.setValue(retailPercentTotalTest);
            retailFTT.setDescription(df.format(messenger.getMainBuildController().getRetailThrough())+"%");

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
                    NavigationController buildController = messenger.getNavigationController();

                    FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/NavigationScreen.fxml"));
                    root.setController(buildController);
                    GridPane buildPane = null;
                    try {
                        buildPane = root.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene buildScene = new Scene(buildPane, 1920, 1080);
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setScene(buildScene);
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
                if(event.getCode() == KeyCode.T && event.isControlDown())
                {
                    TimeLineController timeLineController = messenger.getTimeLineController();

                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initStyle(StageStyle.UNDECORATED);

                    dialog.initOwner(messenger.getPrimaryStage());

                    FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/timeLine.fxml"));

                    root.setController(timeLineController);
                    GridPane buildPane = null;
                    try {
                        buildPane = root.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene buildScene = new Scene(buildPane, 800, 600);

                    timeLineController.setStage(dialog);

                    dialog.setScene(buildScene);
                    dialog.show();
                }
                if(event.getCode() == KeyCode.X && event.isControlDown())
                {
                    TimeLineController timeLineController = messenger.getTimeLineController();

                    Timeline temp = timeLineController.getTimeline();

                    if(temp.getStatus() == Animation.Status.RUNNING && temp != null)
                    {
                        temp.stop();
                    }
                }
            }
        });
        posTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                POSBuildController buildController = messenger.getPosBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/posBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);
            }
        });
        retailTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RetailBuildController buildController = messenger.getRetailBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/retailBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);

            }
        });
        serversTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ServersBuildController buildController = messenger.getServersBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/serversBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);

            }
        });
        periphTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PeriphBuildController buildController = messenger.getPeriphBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/periphBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);

            }
        });
        opticTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OpticBuildController buildController = messenger.getOpticBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/opticBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);

            }
        });
    }
    private void tilesListeners(ArrayList<Tile> tileList)
    {
        Bounds allScreenBounds = computeAllScreenBounds();

        for(int i =0;i<tileList.size();i++)
        {
            Tile temp = tileList.get(i);

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
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();

                    temp.getScene().getWindow().setX(event.getScreenX() - x);
                    temp.getScene().getWindow().setY(event.getScreenY() - y);
                    if(temp.getScene().getWindow().getX() < allScreenBounds.getMinX())
                    {
                        temp.getScene().getWindow().setX(allScreenBounds.getMinX());

                    }
                    if(temp.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-1920))
                    {
                        temp.getScene().getWindow().setX(allScreenBounds.getMaxX()-1920);
                    }
                }
            });
        }
    }
    public void screenMove(Stage primaryStage, Bounds allScreenBounds, ArrayList<Screen> screens)
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

    public Messenger getMessenger() {
        return messenger;
    }

    public Tile getPosTest() {
        return posTest;
    }

    public void setPosTest(Tile posTest) {
        this.posTest = posTest;
    }

    public Tile getRetailTest() {
        return retailTest;
    }

    public void setRetailTest(Tile retailTest) {
        this.retailTest = retailTest;
    }

    public Tile getServersTest() {
        return serversTest;
    }

    public void setServersTest(Tile serversTest) {
        this.serversTest = serversTest;
    }

    public Tile getPeriphTest() {
        return periphTest;
    }

    public void setPeriphTest(Tile periphTest) {
        this.periphTest = periphTest;
    }

    public Tile getOpticTest() {
        return opticTest;
    }

    public void setOpticTest(Tile opticTest) {
        this.opticTest = opticTest;
    }

    public Tile getPosTestPercent() {
        return posTestPercent;
    }

    public void setPosTestPercent(Tile posTestPercent) {
        this.posTestPercent = posTestPercent;
    }

    public Tile getPosFTT() {
        return posFTT;
    }

    public void setPosFTT(Tile posFTT) {
        this.posFTT = posFTT;
    }

    public Tile getRetailTestPercent() {
        return retailTestPercent;
    }

    public void setRetailTestPercent(Tile retailTestPercent) {
        this.retailTestPercent = retailTestPercent;
    }

    public Tile getRetailFTT() {
        return retailFTT;
    }

    public void setRetailFTT(Tile retailFTT) {
        this.retailFTT = retailFTT;
    }

    public Tile getServersTestPercent() {
        return serversTestPercent;
    }

    public void setServersTestPercent(Tile serversTestPercent) {
        this.serversTestPercent = serversTestPercent;
    }

    public Tile getServersFTT() {
        return serversFTT;
    }

    public void setServersFTT(Tile serversFTT) {
        this.serversFTT = serversFTT;
    }

    public Tile getPeriphTestPercent() {
        return periphTestPercent;
    }

    public void setPeriphTestPercent(Tile periphTestPercent) {
        this.periphTestPercent = periphTestPercent;
    }

    public Tile getPeriphFTT() {
        return periphFTT;
    }

    public void setPeriphFTT(Tile periphFTT) {
        this.periphFTT = periphFTT;
    }

    public Tile getOpticTestPercent() {
        return opticTestPercent;
    }

    public void setOpticTestPercent(Tile opticTestPercent) {
        this.opticTestPercent = opticTestPercent;
    }

    public Tile getOpticFTT() {
        return opticFTT;
    }

    public void setOpticFTT(Tile opticFTT) {
        this.opticFTT = opticFTT;
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

    public double getP1x30CurrentTest() {
        return p1x30CurrentTest;
    }

    public void setP1x30CurrentTest(double p1x30CurrentTest) {
        this.p1x30CurrentTest = p1x30CurrentTest;
    }

    public double getP1x35CurrentTest() {
        return p1x35CurrentTest;
    }

    public void setP1x35CurrentTest(double p1x35CurrentTest) {
        this.p1x35CurrentTest = p1x35CurrentTest;
    }

    public double getP1532CurrentTest() {
        return p1532CurrentTest;
    }

    public void setP1532CurrentTest(double p1532CurrentTest) {
        this.p1532CurrentTest = p1532CurrentTest;
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

    public double getPosTotalCurrentBuild() {
        return posTotalCurrentBuild;
    }

    public void setPosTotalCurrentBuild(double posTotalCurrentBuild) {
        this.posTotalCurrentBuild = posTotalCurrentBuild;
    }

    public double getPosTotalCurrentTest() {
        return posTotalCurrentTest;
    }

    public void setPosTotalCurrentTest(double posTotalCurrentTest) {
        this.posTotalCurrentTest = posTotalCurrentTest;
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

    public double getXr7CurrentTest() {
        return xr7CurrentTest;
    }

    public void setXr7CurrentTest(double xr7CurrentTest) {
        this.xr7CurrentTest = xr7CurrentTest;
    }

    public double getXr7PlusCurrentTest() {
        return xr7PlusCurrentTest;
    }

    public void setXr7PlusCurrentTest(double xr7PlusCurrentTest) {
        this.xr7PlusCurrentTest = xr7PlusCurrentTest;
    }

    public double getXr5CurrentTest() {
        return xr5CurrentTest;
    }

    public void setXr5CurrentTest(double xr5CurrentTest) {
        this.xr5CurrentTest = xr5CurrentTest;
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

    public double getRetailTotalCurrentBuild() {
        return retailTotalCurrentBuild;
    }

    public void setRetailTotalCurrentBuild(double retailTotalCurrentBuild) {
        this.retailTotalCurrentBuild = retailTotalCurrentBuild;
    }

    public double getRetailTotalCurrentTest() {
        return retailTotalCurrentTest;
    }

    public void setRetailTotalCurrentTest(double retailTotalCurrentTest) {
        this.retailTotalCurrentTest = retailTotalCurrentTest;
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

    public double getMediaPlayerCurrentTest() {
        return mediaPlayerCurrentTest;
    }

    public void setMediaPlayerCurrentTest(double mediaPlayerCurrentTest) {
        this.mediaPlayerCurrentTest = mediaPlayerCurrentTest;
    }

    public double getN3000CurrentTest() {
        return n3000CurrentTest;
    }

    public void setN3000CurrentTest(double n3000CurrentTest) {
        this.n3000CurrentTest = n3000CurrentTest;
    }

    public double getS500CurrentTest() {
        return s500CurrentTest;
    }

    public void setS500CurrentTest(double s500CurrentTest) {
        this.s500CurrentTest = s500CurrentTest;
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

    public double getServerCurrentTest() {
        return serverCurrentTest;
    }

    public void setServerCurrentTest(double serverCurrentTest) {
        this.serverCurrentTest = serverCurrentTest;
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

    public double getKiwi4sCurrentTest() {
        return kiwi4sCurrentTest;
    }

    public void setKiwi4sCurrentTest(double kiwi4sCurrentTest) {
        this.kiwi4sCurrentTest = kiwi4sCurrentTest;
    }

    public double getKiwi2XsCurrentTest() {
        return kiwi2XsCurrentTest;
    }

    public void setKiwi2XsCurrentTest(double kiwi2XsCurrentTest) {
        this.kiwi2XsCurrentTest = kiwi2XsCurrentTest;
    }

    public double getBumpBarsCurrentTest() {
        return bumpBarsCurrentTest;
    }

    public void setBumpBarsCurrentTest(double bumpBarsCurrentTest) {
        this.bumpBarsCurrentTest = bumpBarsCurrentTest;
    }

    public double getPantherEPC4sCurrentTest() {
        return pantherEPC4sCurrentTest;
    }

    public void setPantherEPC4sCurrentTest(double pantherEPC4sCurrentTest) {
        this.pantherEPC4sCurrentTest = pantherEPC4sCurrentTest;
    }

    public double getPeriphGoalTotalBuild() {
        return periphGoalTotalBuild;
    }

    public void setPeriphGoalTotalBuild(double periphGoalTotalBuild) {
        this.periphGoalTotalBuild = periphGoalTotalBuild;
    }

    public double getPeriphGoalTotalStage() {
        return periphGoalTotalStage;
    }

    public void setPeriphGoalTotalStage(double periphGoalTotalStage) {
        this.periphGoalTotalStage = periphGoalTotalStage;
    }

    public double getPeriphCurrentTotalBuild() {
        return periphCurrentTotalBuild;
    }

    public void setPeriphCurrentTotalBuild(double periphCurrentTotalBuild) {
        this.periphCurrentTotalBuild = periphCurrentTotalBuild;
    }

    public double getPeriphCurrentTotalTest() {
        return periphCurrentTotalTest;
    }

    public void setPeriphCurrentTotalTest(double periphCurrentTotalTest) {
        this.periphCurrentTotalTest = periphCurrentTotalTest;
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

    public double getOptic5sCurrentTest() {
        return optic5sCurrentTest;
    }

    public void setOptic5sCurrentTest(double optic5sCurrentTest) {
        this.optic5sCurrentTest = optic5sCurrentTest;
    }

    public double getOptic12sCurrentTest() {
        return optic12sCurrentTest;
    }

    public void setOptic12sCurrentTest(double optic12sCurrentTest) {
        this.optic12sCurrentTest = optic12sCurrentTest;
    }

    public double getCubCurrentTest() {
        return cubCurrentTest;
    }

    public void setCubCurrentTest(double cubCurrentTest) {
        this.cubCurrentTest = cubCurrentTest;
    }

    public double getCubGoalTest() {
        return cubGoalTest;
    }

    public void setCubGoalTest(double cubGoalTest) {
        this.cubGoalTest = cubGoalTest;
    }

    public double getKitsCurrentTest() {
        return kitsCurrentTest;
    }

    public void setKitsCurrentTest(double kitsCurrentTest) {
        this.kitsCurrentTest = kitsCurrentTest;
    }

    public double getKitsGoalTest() {
        return kitsGoalTest;
    }

    public void setKitsGoalTest(double kitsGoalTest) {
        this.kitsGoalTest = kitsGoalTest;
    }

    public double getPrinterCurrentTest() {
        return printerCurrentTest;
    }

    public void setPrinterCurrentTest(double printerCurrentTest) {
        this.printerCurrentTest = printerCurrentTest;
    }

    public double getPrinterGoalTest() {
        return printerGoalTest;
    }

    public void setPrinterGoalTest(double printerGoalTest) {
        this.printerGoalTest = printerGoalTest;
    }

    public double getOpticGoalTotalBuild() {
        return opticGoalTotalBuild;
    }

    public void setOpticGoalTotalBuild(double opticGoalTotalBuild) {
        this.opticGoalTotalBuild = opticGoalTotalBuild;
    }

    public double getOpticGoalTotalStage() {
        return opticGoalTotalStage;
    }

    public void setOpticGoalTotalStage(double opticGoalTotalStage) {
        this.opticGoalTotalStage = opticGoalTotalStage;
    }

    public double getOpticCurrentTotalBuild() {
        return opticCurrentTotalBuild;
    }

    public void setOpticCurrentTotalBuild(double opticCurrentTotalBuild) {
        this.opticCurrentTotalBuild = opticCurrentTotalBuild;
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

}
