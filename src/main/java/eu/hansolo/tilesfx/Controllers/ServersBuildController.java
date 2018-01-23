package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.Messenger;
import eu.hansolo.tilesfx.tools.Tool;
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
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static javafx.scene.paint.Color.rgb;

public class ServersBuildController implements Initializable
{
    @FXML
    Tile logo;
    @FXML
    Tile clock;
    @FXML
    Tile dept;
    @FXML
    Tile stopLight;
    @FXML
    Tile daySince;

    @FXML
    Tile filler1;
    @FXML
    Tile filler2;
    @FXML
    Tile filler3;
    @FXML
    Tile filler4;
    @FXML
    Tile serversBuild;
    @FXML
    Tile serversBuildGauge;
    @FXML
    Tile serversFTT;


    @FXML
    Tile serversTest;
    @FXML
    Tile serversTestGauge;
    @FXML
    Tile serversTestFTT;

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    DecimalFormat df = new DecimalFormat("#.0");
    DecimalFormat hundred = new DecimalFormat("#");

    Messenger messenger;

    ArrayList<Tile> tiles;

    String useDate = "0";

    double mediaPlayerCurrentBuild;
    double mediaPlayerGoalBuild;
    double n3000CurrentBuild;
    double n3000GoalBuild;
    double s500CurrentBuild;
    double s500GoalBuild;
    double serverGoalTotalBuild;
    double serverCurrentBuild;
    double serversThrough;
    double serverBar1Total;
    double serverBar1Goal;
    double serverBar2Total;
    double serverBar2Goal;
    double serversPercentTotalBuild;
    double mediaPlayerCurrentTest;
    double n3000CurrentTest;
    double s500CurrentTest;
    double serverCurrentTest;
    double serversPercentTotalTest;


    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem serverBar1;
    BarChartItem serverBar2;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem serverBar1DataTest;
    BarChartItem serverBar2DataTest;

    ImageView stopView = new ImageView();
    final Image redImage = new Image("/Red Light.PNG");
    final Image yellowImage = new Image("/Yellow Light.PNG");
    final Image greenImage = new Image("/Green Light.PNG");

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

         mediaPlayerCurrentBuild = mainBuildController.getMediaPlayerCurrentBuild();
         mediaPlayerGoalBuild = mainBuildController.getMediaPlayerGoalBuild();
         n3000CurrentBuild = mainBuildController.getN3000CurrentBuild();
         n3000GoalBuild = mainBuildController.getN3000GoalBuild();
         s500CurrentBuild = mainBuildController.getS500CurrentBuild();
         s500GoalBuild = mainBuildController.getS500GoalBuild();
         serverGoalTotalBuild = mainBuildController.getServerGoalTotalBuild();
         serverCurrentBuild = mainBuildController.getServerCurrentBuild();
         serversThrough = mainBuildController.getServersThrough();
         serverBar1Total = mainBuildController.getServerBar1Total();
         serverBar1Goal = mainBuildController.getServerBar1Goal();
         serverBar2Total = mainBuildController.getServerBar2Total();
         serverBar2Goal = mainBuildController.getServerBar2Goal();
         serversPercentTotalBuild = mainBuildController.getServersPercentTotalBuild();
         mediaPlayerCurrentTest = mainTestController.getMediaPlayerCurrentTest();
         n3000CurrentTest = mainTestController.getN3000CurrentTest();
         s500CurrentTest = mainTestController.getS500CurrentTest();
         serverCurrentTest = mainTestController.getServerCurrentTest();
         serversPercentTotalTest = mainTestController.getServersPercentTotalTest();

        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1 = new BarChartItem("1611/1612", serverBar1Total, serverBar1Goal, Tile.BLUE);
        serverBar2 = new BarChartItem("1656/1657", serverBar2Total, serverBar2Goal, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1DataTest = new BarChartItem("1611/1612", serverBar1Total,serverBar1Goal, Tile.BLUE);
        serverBar2DataTest = new BarChartItem("1656/1657", serverBar2Total, serverBar2Goal, Tile.ORANGE);

        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(serverBar1, serverBar2)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(serversThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();
        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(serverBar1DataTest,serverBar2DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversTestFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getServersThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();


        if (serversThrough == 100) {
            serversFTT.setDescription(hundred.format(serversThrough) + "%");
            serversTestFTT.setDescription(hundred.format(serversThrough) + "%");
        }

        final ImageView logoView = new ImageView();
        final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");
        logoView.setImage(logoImage);
        logoView.setFitHeight(270);
        logoView.setFitWidth(384);
        logoView.setPreserveRatio(true);

        HBox hbox = new HBox(logoView);
        hbox.setPrefWidth(384);
        hbox.setPrefHeight(270);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color:#54B948");

        Tool dataBaseTool = new Tool();


        if (Integer.parseInt(useDate) < 30) {
            stopView.setImage(redImage);
        }
        if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
            stopView.setImage(yellowImage);
        }
        if (Integer.parseInt(useDate) >= 60) {
            stopView.setImage(greenImage);
        }
        stopView.setFitHeight(270);
        stopView.setFitWidth(384);
        stopView.setPreserveRatio(true);

        HBox myBox = new HBox(stopView);
        myBox.setPrefWidth(384);
        myBox.setPrefHeight(270);
        myBox.setAlignment(Pos.CENTER);
        myBox.setStyle("-fx-background-color:#54B948");

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .title("Current Time")
                .titleAlignment(TextAlignment.CENTER)
                .locale(Locale.US)
                .backgroundColor(Color.valueOf("#54B948"))
                .running(true)
                .dateVisible(false)
                .roundedCorners(false)
                .textAlignment(TextAlignment.CENTER)
                .build();

        logo = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .roundedCorners(false)
                .graphic(hbox)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .roundedCorners(false)
                .graphic(myBox)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        filler1  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();
        filler2  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .roundedCorners(false)
                .build();
        filler3  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();
        filler4  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .roundedCorners(false)
                .build();

        serversBuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit("")
                .valueVisible(false)
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-100)
                .maxValue(100)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Build Difference")
                .thresholdColor(Color.valueOf("#54B948"))
                .build();

        serversTestGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit("")
                .valueVisible(false)
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-100)
                .maxValue(100)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Test Difference")
                .thresholdColor(Color.valueOf("#54B948"))
                .build();



        pane.add(serversBuild,1,0,1,2);
        pane.add(serversBuildGauge,2,0,1,1);
        pane.add(serversFTT,3,0,1,1);
        pane.add(serversTest,1,2,1,2);
        pane.add(serversTestGauge,2,2,1,1);
        pane.add(serversTestFTT,3,2,1,1);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);
        pane.add(filler1,2,1,1,1);
        pane.add(filler2,3,1,1,1);
        pane.add(filler3,2,3,1,1);
        pane.add(filler4,3,3,1,1);

        tiles.add(serversBuild);
        tiles.add(serversBuildGauge);
        tiles.add(serversFTT);
        tiles.add(serversTest);
        tiles.add(serversTestGauge);
        tiles.add(serversTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(filler1);
        tiles.add(filler2);
        tiles.add(filler3);
        tiles.add(filler4);

        createActions();
        if(pane != null)
        {
            tilesListeners(tiles);
            buildDifferential();
            testDifferential();
        }

    }

    public void refresh()
    {
        Platform.runLater( () ->
        {
            serverGoalTotalBuild = messenger.getMainBuildController().getServerGoalTotalBuild();
            serverCurrentBuild = messenger.getMainBuildController().getServerCurrentBuild();
            serverCurrentTest = messenger.getMainTestController().getServerCurrentTest();
            //---------------------------------Update the Server Units------------------------------------------
            serverBar1.setValue(messenger.getMainBuildController().getServerBar1Total());
            serverBar1.setMaxValue(messenger.getMainBuildController().getServerBar1Goal());
            serverBar2.setValue(messenger.getMainBuildController().getServerBar2Total());
            serverBar2.setMaxValue(messenger.getMainBuildController().getServerBar2Goal());

            //---------------------------------Update the Server Units------------------------------------------
            serverBar1DataTest.setValue(messenger.getMainTestController().getServerBar1Total());
            serverBar1DataTest.setMaxValue(messenger.getMainTestController().getServerBar1Goal());
            serverBar2DataTest.setValue(messenger.getMainTestController().getServerBar2Total());
            serverBar2DataTest.setMaxValue(messenger.getMainTestController().getServerBar2Goal());

            serversFTT.setDescription(df.format(messenger.getMainBuildController().getServersThrough()) + "%");
            serversTestFTT.setDescription(df.format(messenger.getMainBuildController().getServersThrough()) + "%");
            if(messenger.getMainBuildController().getServersThrough() == 100)
            {
                serversFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough())+"%");
                serversTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough())+"%");
            }
            daySince.setDescription(useDate);

            if (Integer.parseInt(useDate) < 30) {
                stopView.setImage(redImage);
            }
            if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
                stopView.setImage(yellowImage);
            }
            if (Integer.parseInt(useDate) >= 60) {
                stopView.setImage(greenImage);
            }
            stopView.setFitHeight(270);
            stopView.setFitWidth(384);
            stopView.setPreserveRatio(true);

            HBox myBox = new HBox(stopView);
            myBox.setPrefWidth(384);
            myBox.setPrefHeight(270);
            myBox.setAlignment(Pos.CENTER);
            myBox.setStyle("-fx-background-color:#54B948");

            stopLight.setGraphic(myBox);

            buildDifferential();
            testDifferential();

        });
    }
    private void buildDifferential()
    {
        Platform.runLater( () ->
        {
            double theGoal = serverGoalTotalBuild/540;
            double modifier = 0;
            double currentGoal = 0;
            double minute = 0;
            ZonedDateTime currentTime = clock.getTime();
            if(currentTime.getHour() ==7)
            {
                modifier = 0;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
                System.out.println(currentGoal);
            }
            if(currentTime.getHour() ==8)
            {
                modifier = 60;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
                System.out.println(currentGoal);
            }
            if(currentTime.getHour() ==9)
            {
                modifier = 120;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
            }
            if(currentTime.getHour() ==10)
            {
                modifier = 180;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
            }
            if(currentTime.getHour() == 11)
            {
                modifier = 240;
                minute = currentTime.getMinute();

                currentGoal = theGoal * (modifier + minute);
            }
            if(currentTime.getHour() == 12 )
            {
                modifier = 300;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
            }
            if(currentTime.getHour() == 13)
            {
                modifier = 360;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
            }
            if(currentTime.getHour() ==14)
            {
                modifier = 420;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + minute);
            }
            if (currentTime.getHour() == 15) {
                if(currentTime.getMinute()< 30)
                {
                    modifier = 480;
                    minute = currentTime.getMinute();
                    currentGoal = theGoal * (modifier + (minute*2));
                }
                else
                {
                    currentGoal = serverGoalTotalBuild;
                }
            }
            if(currentTime.getHour() >15)
            {
                currentGoal = serverGoalTotalBuild;
            }

            serversBuildGauge.setValue(serverCurrentBuild - currentGoal);


            int displayBuildValue = (int) (serverCurrentBuild - currentGoal);

            String returnBuildString = "";

            if (displayBuildValue > 0) {
                returnBuildString = "+" + Integer.toString(displayBuildValue) + " units" + "\n\n";
                filler1.setTextColor(Color.valueOf("#54B948"));
            }
            if (displayBuildValue == 0) {
                returnBuildString = Integer.toString(displayBuildValue) + " units" + "\n\n";
                filler1.setTextColor(Color.WHITE);
            }

            if (displayBuildValue < 0) {
                returnBuildString = Integer.toString(displayBuildValue) + " units" + "\n\n";
                filler1.setTextColor(Tile.RED);
            }
            filler1.setDescription(returnBuildString);
        });


    }
    private void testDifferential()
    {
        double theGoal = serverGoalTotalBuild/540;
        double modifier = 0;
        double currentGoal = 0;
        double minute = 0;
        ZonedDateTime currentTime = clock.getTime();
        if(currentTime.getHour() ==7)
        {
            modifier = 0;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() ==8)
        {
            modifier = 60;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() ==9)
        {
            modifier = 120;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() ==10)
        {
            modifier = 180;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() == 11)
        {
            modifier = 240;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() == 12 )
        {
            modifier = 300;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() == 13)
        {
            modifier = 360;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if(currentTime.getHour() ==14)
        {
            modifier = 420;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 15) {
            if(currentTime.getMinute()< 30)
            {
                modifier = 480;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + (minute*2));
            }
            else
            {
                currentGoal = serverGoalTotalBuild;
            }
        }
        if(currentTime.getHour() >15)
        {
            currentGoal = serverGoalTotalBuild;
        }

        serversTestGauge.setValue(serverCurrentTest-currentGoal);

        int displayTestValue = (int) (serverCurrentTest-currentGoal);

        String returnTestString = "";

        if(displayTestValue > 0)
        {
            returnTestString = "+"+Integer.toString(displayTestValue)+" units"+"\n\n";
            filler3.setTextColor(Color.valueOf("#54B948"));
        }
        if(displayTestValue == 0)
        {
            returnTestString = Integer.toString(displayTestValue)+" units"+"\n\n";
            filler3.setTextColor(Color.WHITE);
        }
        if(displayTestValue < 0)
        {
            returnTestString = Integer.toString(displayTestValue)+" units"+"\n\n";
            filler3.setTextColor(Tile.RED);
        }
        filler3.setDescription(returnTestString);
    }
    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getMainBuild());
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
                if (event.getCode() == KeyCode.F4) {
                    messenger.getPrimaryStage().setIconified(true);
                }
                if (event.getCode() == KeyCode.F5) {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
            }
        });
        serversBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getServersBuildOverview());
            }
        });
        serversBuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getServersBuildOverview());
            }
        });
        filler1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getServersBuildOverview());
            }
        });
    }
    private void tilesListeners(ArrayList<Tile> tileList)
    {

        for(int i =0;i<tileList.size();i++)
        {
            tileList.get(i).setAnimated(true);
            tileList.get(i).setAnimationDuration(3000);

            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();

                }
            });
            int finalI = i;
            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    tileList.get(finalI).getScene().getWindow().setX(event.getScreenX() - x);
                    tileList.get(finalI).getScene().getWindow().setY(event.getScreenY() - y);
                    if(tileList.get(finalI).getScene().getWindow().getX() < allScreenBounds.getMinX())
                    {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMinX());

                    }
                    if(tileList.get(finalI).getScene().getWindow().getX() > (allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth))
                    {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth);
                    }
                }
            });
            tileList.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tileList.get(finalI).setBorderColor(Tile.GRAY);
                    PauseTransition idle = new PauseTransition(Duration.millis(1000));
                    tileList.get(finalI).addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                        tileList.get(finalI).setCursor(Cursor.HAND);
                        idle.playFromStart();
                        tileList.get(finalI).setBorderColor(Tile.GRAY);
                    });
                    idle.setOnFinished(e ->
                    {
                        tileList.get(finalI).setCursor(Cursor.NONE);
                        tileList.get(finalI).setBorderColor(Color.TRANSPARENT);
                    });
                }
            });
            tileList.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tileList.get(finalI).setBorderColor(Color.TRANSPARENT);
                }
            });
        }
    }
    private Bounds computeAllScreenBounds() {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D screenBounds = screen.getBounds();
            if (screenBounds.getMinX() < minX) {
                minX = screenBounds.getMinX();
            }
            if (screenBounds.getMinY() < minY) {
                minY = screenBounds.getMinY();
            }
            if (screenBounds.getMaxX() > maxX) {
                maxX = screenBounds.getMaxX();
            }
            if (screenBounds.getMaxY() > maxY) {
                maxY = screenBounds.getMaxY();
            }
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
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

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
    public Tile getLogo() {
        return logo;
    }

    public void setLogo(Tile logo) {
        this.logo = logo;
    }

    public Tile getClock() {
        return clock;
    }

    public void setClock(Tile clock) {
        this.clock = clock;
    }

    public Tile getDept() {
        return dept;
    }

    public void setDept(Tile dept) {
        this.dept = dept;
    }

    public Tile getStopLight() {
        return stopLight;
    }

    public void setStopLight(Tile stopLight) {
        this.stopLight = stopLight;
    }

    public Tile getDaySince() {
        return daySince;
    }

    public void setDaySince(Tile daySince) {
        this.daySince = daySince;
    }

    public Tile getServersBuild() {
        return serversBuild;
    }

    public void setServersBuild(Tile serversBuild) {
        this.serversBuild = serversBuild;
    }

    public Tile getServersFTT() {
        return serversFTT;
    }

    public void setServersFTT(Tile serversFTT) {
        this.serversFTT = serversFTT;
    }

    public Tile getServersTest() {
        return serversTest;
    }

    public void setServersTest(Tile serversTest) {
        this.serversTest = serversTest;
    }

    public Tile getServersTestFTT() {
        return serversTestFTT;
    }

    public void setServersTestFTT(Tile serversTestFTT) {
        this.serversTestFTT = serversTestFTT;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

}

