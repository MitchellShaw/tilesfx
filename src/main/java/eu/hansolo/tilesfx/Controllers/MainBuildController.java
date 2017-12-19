package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
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

public class MainBuildController implements Initializable
{
    @FXML
    private GridPane pane;

    Tile posBuild;
    Tile serversBuild;
    Tile periphBuild;
    Tile opticBuild;
    Tile retailBuild;
    Tile posPercent;
    Tile serversPercent;
    Tile periphPercent;
    Tile opticPercent;
    Tile retailPercent;
    Tile posFTT;
    Tile serversFTT;
    Tile periphFTT;
    Tile opticFTT;
    Tile retailFTT;

    double p1x30CurrentBuild;
    double p1x30GoalBuild;
    double p1x35CurrentBuild;
    double p1x35GoalBuild;
    double p1532CurrentBuild;
    double p1532GoalBuild;
    double t1000sCurrentBuild;
    double t1000sGoalBuild;

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

    BarChartItem p1x35Data;
    BarChartItem p1532Data;
    BarChartItem p1x30Data;
    BarChartItem t1000Data;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem n3000Data;
    BarChartItem s500Data;
    BarChartItem mediaPlayer;
    //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
    BarChartItem kiwi4Data;
    BarChartItem kiwi25Data;
    BarChartItem bumpBarData;
    BarChartItem pantherEPC4Data;
    //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
    BarChartItem optic12Data;
    BarChartItem optic5Data;
    //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    BarChartItem xr5Data;
    BarChartItem xr7Data;
    BarChartItem xr7PlusData;
    BarChartItem nextGenDisplays;

    Messenger messenger;

    DecimalFormat df = new DecimalFormat("#.0");

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
        p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, p1x35GoalBuild, Tile.RED);
        p1532Data = new BarChartItem("P1532", p1532CurrentBuild, p1532GoalBuild, Tile.GREEN);
        p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, p1x30GoalBuild, Tile.BLUE);
        t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, t1000sGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        n3000Data = new BarChartItem("N3000", n3000CurrentBuild, n3000GoalBuild, Tile.RED);
        s500Data = new BarChartItem("S500", s500CurrentBuild, s500GoalBuild, Tile.BLUE);
        mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, mediaPlayerGoalBuild, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, kiwi4sGoalBuild, Tile.BLUE);
        kiwi25Data = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentBuild, kiwi2XsGoalBuild, Tile.RED);
        bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, bumpBarsGoalBuild, Tile.GREEN);
        pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, pantherEPC4sGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, optic12sGoalBuild, Tile.RED);
        optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        xr5Data = new BarChartItem("7701", xr5CurrentBuild, xr5GoalBuild, Tile.BLUE);
        xr7Data = new BarChartItem("7702", xr7CurrentBuild, xr7GoalBuild, Tile.RED);
        xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, xr7PlusGoalBuild, Tile.GREEN);
        nextGenDisplays = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.YELLOW);
        //---------------------------------Creating Tiles for Scene-----------------------------------------------------



        posBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(384, 540)
                .barChartItems(p1x30Data, p1x35Data, p1532Data, t1000Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(posBuild,0,0,1,2);

        posPercent = TileBuilder.create()
                .prefSize(384, 270)
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
                .prefSize(384, 270)
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(posThrough)+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(posPercent,0,2,1,1);
        pane.add(posFTT,0,3,1,1);

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retailBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Retail Build")
                .prefSize(384, 540)
                .barChartItems(xr5Data, xr7Data, xr7PlusData, nextGenDisplays)
                .decimals(0)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(retailBuild,1,0,1,2);

        retailPercent = TileBuilder.create()
                .prefSize(384, 270)
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

        retailFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 270)
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(retailThrough)+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(retailPercent,1,2,1,1);
        pane.add(retailFTT,1,3,1,1);


        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(384, 540)
                .barChartItems(s500Data, n3000Data, mediaPlayer)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(serversBuild,2,0,1,2);

        serversPercent = TileBuilder.create()
                .prefSize(384, 270)
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
                .prefSize(384, 270)
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(serversThrough)+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();


        pane.add(serversPercent,2,2,1,1);
        pane.add(serversFTT,2,3,1,1);


        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        periphBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(384, 540)
                .barChartItems(kiwi4Data, kiwi25Data, bumpBarData, pantherEPC4Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(periphBuild,3,0,1,2);

        periphPercent = TileBuilder.create()
                .prefSize(384, 270)
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
                .prefSize(384, 270)
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(periphThrough)+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(periphPercent,3,2,1,1);
        pane.add(periphFTT,3,3,1,1);


        //---------------------------------Creating the Tiles for Optic-------------------------------------------------
        opticBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(384, 540)
                .barChartItems(optic5Data, optic12Data)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        pane.add(opticBuild,4,0,1,2);

        opticPercent = TileBuilder.create()
                .prefSize(384, 270)
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
                .prefSize(384, 270)
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("FTT")
                .description(df.format(opticThrough)+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(opticPercent,4,2,1,1);
        pane.add(opticFTT,4,3,1,1);

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

        tilesListeners(tiles);
        refresh();

    }

    public void refresh()
    {
        Platform.runLater( ()->
        {
            p1x35Data.setValue(p1x35CurrentBuild);
            p1x35Data.setMaxValue(p1x35GoalBuild);
            p1532Data.setValue(p1532CurrentBuild);
            p1532Data.setMaxValue(p1532GoalBuild);

            p1x30Data.setValue(p1x30CurrentBuild);
            p1x30Data.setMaxValue(p1x30GoalBuild);

            t1000Data.setValue(t1000sCurrentBuild);
            t1000Data.setMaxValue(t1000sGoalBuild);
            //---------------------------------Update the Server Units------------------------------------------
            n3000Data.setValue(n3000CurrentBuild);
            n3000Data.setMaxValue(n3000GoalBuild);
            s500Data.setValue(s500CurrentBuild);
            s500Data.setMaxValue(s500GoalBuild);
            mediaPlayer.setValue(mediaPlayerCurrentBuild);
            mediaPlayer.setMaxValue(mediaPlayerGoalBuild);

            //---------------------------------Updating the Peripheral Units------------------------------------
            kiwi4Data.setValue(kiwi4sCurrentBuild);
            kiwi4Data.setMaxValue(kiwi4sGoalBuild);
            kiwi25Data.setValue(kiwi2XsCurrentBuild);
            kiwi25Data.setMaxValue(kiwi2XsGoalBuild);
            bumpBarData.setValue(bumpBarsCurrentBuild);
            bumpBarData.setMaxValue(bumpBarsGoalBuild);
            pantherEPC4Data.setValue(pantherEPC4sCurrentBuild);
            pantherEPC4Data.setMaxValue(pantherEPC4sGoalBuild);
            //---------------------------------Updating the Optic Units------------------------------------------
            optic5Data.setValue(optic5sCurrentBuild);
            optic5Data.setMaxValue(optic5sGoalBuild);
            optic12Data.setValue(optic12sCurrentBuild);
            optic12Data.setMaxValue(optic12sGoalBuild);
            //---------------------------------Updating the Retail Units----------------------------------------
            xr5Data.setValue(xr5CurrentBuild);
            xr5Data.setMaxValue(xr5GoalBuild);
            xr7Data.setValue(xr7CurrentBuild);
            xr7Data.setMaxValue(xr7GoalBuild);
            xr7PlusData.setValue(xr7PlusCurrentBuild);
            xr7PlusData.setMaxValue(xr7PlusGoalBuild);
            nextGenDisplays.setValue(nextGenDisplayCurrentBuild);
            nextGenDisplays.setMaxValue(nextGenDisplayGoalsBuild);

            //---------------------------------Creating Color Changes for POS Dial------------------------------------------
            posPercent.setValue(posPercentTotalBuild);
            posFTT.setDescription(df.format(posThrough) + "%");

            changePercent(posPercent, posTotalCurrentBuild, posTotalGoalBuild, posPercentTotalBuild);
            changePercent(posFTT, posTotalCurrentBuild, posTotalGoalBuild, posPercentTotalBuild);

            //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
            serversPercent.setValue(serversPercentTotalBuild);
            serversFTT.setDescription(df.format(serversThrough) + "%");

            changePercent(serversPercent, serverCurrentBuild, serverGoalTotalBuild, serversPercentTotalBuild);
            changePercent(serversFTT, serverCurrentBuild, serverGoalTotalBuild, serversPercentTotalBuild);

            //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
            periphPercent.setValue(periphPercentTotalBuild);
            periphFTT.setDescription(df.format(periphThrough) + "%");

            changePercent(periphPercent, periphCurrentTotalBuild, periphGoalTotalBuild, periphPercentTotalBuild);
            changePercent(periphFTT, periphCurrentTotalBuild, periphGoalTotalBuild, periphPercentTotalBuild);

            //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
            opticPercent.setValue(opticPercentTotalBuild);
            opticFTT.setDescription(df.format(opticThrough) + "%");

            changePercent(opticPercent, opticCurrentTotalBuild, opticGoalTotalBuild, opticPercentTotalBuild);
            changePercent(opticFTT, opticCurrentTotalBuild, opticGoalTotalBuild, opticPercentTotalBuild);

            //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
            retailPercent.setValue(retailPercentTotalBuild);
            retailFTT.setDescription(df.format(retailThrough) + "%");

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
                if (event.getCode() == KeyCode.F5) {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
            }
        });
        posBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        retailBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        serversBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        periphBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        opticBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
}
