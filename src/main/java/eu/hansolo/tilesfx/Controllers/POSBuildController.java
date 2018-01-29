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

public class POSBuildController implements Initializable
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
    Tile posBuild;
    @FXML
    Tile posBuildGauge;
    @FXML
    Tile posFTT;

    @FXML
    Tile posTest;
    @FXML
    Tile posTestGauge;
    @FXML
    Tile posTestFTT;

    HBox myBox;
    HBox hbox;

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    Messenger messenger;

    ArrayList<Tile> tiles;

    String useDate = "0";

    ImageView stopView = new ImageView();
    final Image redImage = new Image("/Red Light.PNG");
    final Image yellowImage = new Image("/Yellow Light.PNG");
    final Image greenImage = new Image("/Green Light.PNG");

    final ImageView logoView = new ImageView();
    final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");

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
    double posPercentTotalBuild;
    double posThrough;
    double posBar1Total;
    double posBar1Goal;
    double posPercentTotalTest;
    double p1x30CurrentTest;
    double p1x35CurrentTest;
    double p1532CurrentTest;
    double t1000sCurrentTest;
    double questsCurrentTest;
    double posTotalCurrentTest;


    BarChartItem posBar1Data;
    BarChartItem p1x30Data;
    BarChartItem t1000Data;
    BarChartItem questData;
    BarChartItem posBar1DataTest;
    BarChartItem p1x30DataTest;
    BarChartItem t1000DataTest;
    BarChartItem questDataTest;

    DecimalFormat df = new DecimalFormat("#.0");
    DecimalFormat hundred = new DecimalFormat("#");

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

         p1x30CurrentBuild = mainBuildController.getP1x30CurrentBuild();
         p1x30GoalBuild = mainBuildController.getP1x30GoalBuild();
         p1x35CurrentBuild = mainBuildController.getP1x35CurrentBuild();
         p1x35GoalBuild = mainBuildController.getP1x35GoalBuild();
         p1532CurrentBuild = mainBuildController.getP1532CurrentBuild();
         p1532GoalBuild = mainBuildController.getP1532GoalBuild();
         t1000sCurrentBuild = mainBuildController.getT1000sCurrentBuild();
         t1000sGoalBuild = mainBuildController.getT1000sGoalBuild();
         questGoalBuild = mainBuildController.getQuestGoalBuild();
         questCurrentBuild = mainBuildController.getQuestCurrentBuild();
         posTotalGoalBuild = mainBuildController.getPosTotalGoalBuild();
         posTotalCurrentBuild = mainBuildController.getPosTotalCurrentBuild();
         posPercentTotalBuild = mainBuildController.getPosPercentTotalBuild();
         posThrough = mainBuildController.getPosThrough();
         posBar1Total = mainBuildController.getPosBar1Total();
         posBar1Goal = mainBuildController.getPosBar1Goal();
         posPercentTotalTest = mainTestController.getPosPercentTotalTest();
         p1x30CurrentTest = mainTestController.getP1x30CurrentTest();
         p1x35CurrentTest = mainTestController.getP1x35CurrentTest();
         p1532CurrentTest = mainTestController.getP1532CurrentTest();
         t1000sCurrentTest = mainTestController.getT1000sCurrentTest();
         questsCurrentTest = mainTestController.getQuestsCurrentTest();
         posTotalCurrentTest = mainTestController.getPosTotalCurrentTest();



        //---------------------------------Creating the Bar Chart Items for Hosp----------------------------------------
        posBar1Data = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30Data = new BarChartItem("7743", p1x30CurrentBuild, p1x30GoalBuild, Tile.ORANGE);
        t1000Data = new BarChartItem("7744", t1000sCurrentBuild, t1000sGoalBuild, Tile.GREEN);
        questData = new BarChartItem("7791/7792", questCurrentBuild, questGoalBuild, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
        posBar1DataTest = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30DataTest = new BarChartItem("7743", p1x30CurrentTest, p1x30GoalBuild, Tile.ORANGE);
        t1000DataTest = new BarChartItem("7744", t1000sCurrentTest, t1000sGoalBuild, Tile.GREEN);
        questDataTest = new BarChartItem("7791/7792", questsCurrentTest, questGoalBuild, Tile.YELLOW);

        posBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(posBar1Data, p1x30Data, t1000Data, questData)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(posThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        posTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(posBar1DataTest, p1x30DataTest, t1000DataTest, questDataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posTestFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPosThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (posThrough == 100) {
            posFTT.setDescription(hundred.format(posThrough) + "%");
            posTestFTT.setDescription(hundred.format(posThrough) + "%");
        }


        logoView.setImage(logoImage);
        logoView.setFitHeight(270);
        logoView.setFitWidth(384);
        logoView.setPreserveRatio(true);

        hbox = new HBox(logoView);
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

        posBuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
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

        posTestGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
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

        pane.add(posBuild,1,0,1,2);
        pane.add(posBuildGauge,2,0,1,2);
        pane.add(posFTT,3,0,1,2);
        pane.add(posTest,1,2,1,2);
        pane.add(posTestGauge,2,2,1,2);
        pane.add(posTestFTT,3,2,1,2);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);

        tiles.add(posBuild);
        tiles.add(posBuildGauge);
        tiles.add(posFTT);
        tiles.add(posTest);
        tiles.add(posTestGauge);
        tiles.add(posTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);

        createActions();
        if(pane != null)
        {
            tilesListeners(tiles);
            buildDifferential();
        }

    }

    public void refresh()
    {
        Platform.runLater( () ->
        {

            posTotalCurrentBuild = messenger.getMainBuildController().getPosTotalCurrentBuild();
            posTotalGoalBuild = messenger.getMainBuildController().getPosTotalGoalBuild();
            posTotalCurrentTest = messenger.getMainTestController().getPosTotalCurrentTest();

            posBar1Data.setValue(messenger.getMainBuildController().getPosBar1Total());
            posBar1Data.setMaxValue(messenger.getMainBuildController().getPosBar1Goal());

            p1x30Data.setValue(messenger.getMainBuildController().getP1x30CurrentBuild());
            p1x30Data.setMaxValue(messenger.getMainBuildController().getP1x30GoalBuild());

            t1000Data.setValue(messenger.getMainBuildController().getT1000sCurrentBuild());
            t1000Data.setMaxValue(messenger.getMainBuildController().getT1000sGoalBuild());

            questData.setValue(messenger.getMainBuildController().getQuestCurrentBuild());
            questData.setMaxValue(messenger.getMainBuildController().getQuestGoalBuild());

            posBar1DataTest.setValue(messenger.getMainTestController().getPosBar1Total());
            posBar1DataTest.setMaxValue(messenger.getMainTestController().getPosBar1Goal());

            p1x30DataTest.setValue(messenger.getMainTestController().getP1x30CurrentTest());
            p1x30DataTest.setMaxValue(messenger.getMainTestController().getP1x30GoalBuild());

            t1000DataTest.setValue(messenger.getMainTestController().getT1000sCurrentTest());
            t1000DataTest.setMaxValue(messenger.getMainTestController().getT1000sGoalBuild());

            questDataTest.setValue(messenger.getMainTestController().getQuestsCurrentTest());
            questDataTest.setMaxValue(messenger.getMainTestController().getQuestGoalBuild());

            posFTT.setDescription(df.format(messenger.getMainBuildController().getPosThrough())+"%");
            posTestFTT.setDescription(df.format(messenger.getMainBuildController().getPosThrough())+"%");
            if(messenger.getMainBuildController().getPosThrough() == 100)
            {
                posFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough())+"%");
                posTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough())+"%");
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

            myBox = new HBox(stopView);
            myBox.setPrefWidth(384);
            myBox.setPrefHeight(270);
            myBox.setAlignment(Pos.CENTER);
            myBox.setStyle("-fx-background-color:#54B948");


            stopLight.setGraphic(myBox);

            buildDifferential();

        });
    }
    ArrayList<Tile> gauges;

    private void buildDifferential() {
        gauges = new ArrayList<>();

        double theGoal = posTotalGoalBuild / 540;
        double modifier = 0;
        double currentGoal = 0;
        double minute = 0;
        ZonedDateTime currentTime = clock.getTime();
        if (currentTime.getHour() == 7) {
            modifier = 0;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 8) {
            modifier = 60;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 9) {
            modifier = 120;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 10) {
            modifier = 180;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 11) {
            modifier = 240;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 12) {
            modifier = 300;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 13) {
            modifier = 360;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 14) {
            modifier = 420;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 15) {
            if (currentTime.getMinute() < 30) {
                modifier = 480;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + (minute * 2));
            } else {
                currentGoal = posTotalGoalBuild;
            }
        }
        if (currentTime.getHour() > 15) {
            currentGoal = posTotalGoalBuild;
        }
        posBuildGauge.setValue(posTotalCurrentBuild - currentGoal);
        posTestGauge.setValue(posTotalCurrentTest - currentGoal);

        gauges.add(posTestGauge);
        gauges.add(posBuildGauge);

        for (int i = 0; i < gauges.size(); i++) {
            if (gauges.get(i).getValue() > 0) {
                gauges.get(i).setValueColor(Color.valueOf("#54B948"));
                gauges.get(i).setUnitColor(Color.valueOf("#54B948"));
            }
            if (gauges.get(i).getValue() == 0) {
                gauges.get(i).setValueColor(Color.WHITE);
                gauges.get(i).setUnitColor(Color.WHITE);
            }
            if (gauges.get(i).getValue() < 0) {
                gauges.get(i).setValueColor(Tile.RED);
                gauges.get(i).setUnitColor(Tile.RED);
            }
        }
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
                if(event.getCode() == KeyCode.F4)
                {
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
            }
        });
        posBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPosBuildOverview());
            }
        });
        posBuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPosBuildOverview());
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

    public Tile getClock() {
        return clock;
    }

    public Tile getDept() {
        return dept;
    }

    public Tile getStopLight() {
        return stopLight;
    }

    public Tile getDaySince() {
        return daySince;
    }

    public Tile getPosBuild() {
        return posBuild;
    }

    public Tile getPosFTT() {
        return posFTT;
    }

    public Tile getPosTest() {
        return posTest;
    }

    public Tile getPosTestFTT() {
        return posTestFTT;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }
}
