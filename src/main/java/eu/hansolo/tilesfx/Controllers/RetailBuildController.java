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
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
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

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static javafx.scene.paint.Color.rgb;

public class RetailBuildController implements Initializable
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
    Tile retailBuild;
    @FXML
    Tile retailBuildGauge;

    @FXML
    Tile retailFTT;
    @FXML
    Tile retailTest;
    @FXML
    Tile retailTestFTT;
    @FXML
    Tile retailTestGauge;

    HBox hbox;
    HBox myBox;

    DecimalFormat df = new DecimalFormat("#.0");
    DecimalFormat hundred = new DecimalFormat("#");

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
    double retailThrough;
    double retailBar1Total;
    double retailBar1Goal;
    double xr7CurrentTest;
    double xr7PlusCurrentTest;
    double xr5CurrentTest;
    double nextGenDisplayCurrentTest;
    double retailTotalCurrentTest;
    double retailPercentTotalBuild;
    double retailPercentTotalTest;

    //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    BarChartItem retailBar1Data;
    BarChartItem nextGenDisplays;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem retailBar1DataTest;
    BarChartItem nextGenDisplaysTest;


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

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

         xr7CurrentBuild = mainBuildController.getXr7CurrentBuild();
         xr7GoalBuild = mainBuildController.getXr7GoalBuild();
         xr7PlusCurrentBuild = mainBuildController.getXr7PlusCurrentBuild();
         xr7PlusGoalBuild = mainBuildController.getXr7PlusGoalBuild();
         xr5CurrentBuild = mainBuildController.getXr5CurrentBuild();
         xr5GoalBuild = mainBuildController.getXr5GoalBuild();
         nextGenDisplayCurrentBuild = mainBuildController.getNextGenDisplayCurrentBuild();
         nextGenDisplayGoalsBuild = mainBuildController.getNextGenDisplayGoalsBuild();
         retailTotalGoalBuild = mainBuildController.getRetailTotalGoalBuild();
         retailTotalCurrentBuild = mainBuildController.getRetailTotalCurrentBuild();
         retailThrough = mainBuildController.getRetailThrough();
         retailBar1Total = mainBuildController.getRetailBar1Total();
         retailBar1Goal = mainBuildController.getRetailBar1Goal();
         xr7CurrentTest = mainTestController.getXr7CurrentTest();
         xr7PlusCurrentTest = mainTestController.getXr7PlusCurrentTest();
         xr5CurrentTest = mainTestController.getXr5CurrentTest();
         nextGenDisplayCurrentTest = mainTestController.getNextGenDisplayCurrentTest();
         retailTotalCurrentTest = mainTestController.getRetailTotalCurrentTest();
         retailPercentTotalBuild = mainBuildController.getRetailPercentTotalBuild();
         retailPercentTotalTest = mainTestController.getRetailPercentTotalTest();

        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        retailBar1Data = new BarChartItem("770X", retailBar1Total, retailBar1Goal, Tile.BLUE);
        nextGenDisplays = new BarChartItem("5968/5985", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Retail-------------------------------------
        retailBar1DataTest = new BarChartItem("770X", retailBar1Total,retailBar1Goal, Tile.BLUE);
        nextGenDisplaysTest = new BarChartItem("5968/5985", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.ORANGE);

        retailBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Retail Build")
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(retailBar1Data, nextGenDisplays)
                .decimals(0)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailFTT = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(retailThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();
        retailTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .title("Retail Test")
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(retailBar1DataTest, nextGenDisplaysTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailTestFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getRetailThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (retailThrough == 100) {
            retailFTT.setDescription(hundred.format(retailThrough) + "%");
            retailTestFTT.setDescription(hundred.format(retailThrough) + "%");
        }


        final ImageView logoView = new ImageView();
        final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");
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

        myBox = new HBox(stopView);
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

        retailBuildGauge = TileBuilder.create()
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

        retailTestGauge = TileBuilder.create()
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

        pane.add(retailBuild,1,0,1,2);
        pane.add(retailBuildGauge,2,0,1,2);
        pane.add(retailFTT,3,0,1,2);
        pane.add(retailTest,1,2,1,2);
        pane.add(retailTestGauge,2,2,1,2);
        pane.add(retailTestFTT,3,2,1,2);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);


        tiles.add(retailBuild);
        tiles.add(retailFTT);
        tiles.add(retailTest);
        tiles.add(retailTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(retailBuildGauge);
        tiles.add(retailTestGauge);

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
            retailTotalGoalBuild = messenger.getMainBuildController().getRetailTotalGoalBuild();
            retailTotalCurrentBuild = messenger.getMainBuildController().getRetailTotalCurrentBuild();
            retailTotalCurrentTest = messenger.getMainTestController().getRetailTotalCurrentTest();
            //---------------------------------Updating the Retail Units----------------------------------------
            retailBar1Data.setValue(messenger.getMainBuildController().getRetailBar1Total());
            retailBar1Data.setMaxValue(messenger.getMainBuildController().getRetailBar1Goal());
            nextGenDisplays.setValue(messenger.getMainBuildController().getNextGenDisplayCurrentBuild());
            nextGenDisplays.setMaxValue(messenger.getMainBuildController().getNextGenDisplayGoalsBuild());
            //---------------------------------Updating the Retail Units------------------------------------
            retailBar1DataTest.setValue(messenger.getMainTestController().getRetailBar1Total());
            retailBar1DataTest.setMaxValue(messenger.getMainTestController().getRetailBar1Goal());
            nextGenDisplaysTest.setValue(messenger.getMainTestController().getNextGenDisplayCurrentTest());
            nextGenDisplaysTest.setMaxValue(messenger.getMainTestController().getNextGenDisplayGoalsBuild());

            retailFTT.setDescription(df.format(messenger.getMainBuildController().getRetailThrough())+"%");
            retailTestFTT.setDescription(df.format(messenger.getMainBuildController().getRetailThrough())+"%");
            if(messenger.getMainBuildController().getRetailThrough() == 100)
            {
                retailFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough())+"%");
                retailTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough())+"%");
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
        retailBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuildOverview());
            }
        });
        retailBuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getRetailBuildOverview());
            }
        });
    }

    private void tilesListeners(ArrayList<Tile> tileList) {
        for (int i = 0; i < tileList.size(); i++) {
            tileList.get(i).setAnimated(true);
            tileList.get(i).setAnimationDuration(3000);
            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                    }});
            int finalI = i;
            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tileList.get(finalI).getScene().getWindow().setX(event.getScreenX() - x);
                    tileList.get(finalI).getScene().getWindow().setY(event.getScreenY() - y);
                    if (tileList.get(finalI).getScene().getWindow().getX() < allScreenBounds.getMinX()) {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMinX());
                        }
                        if (tileList.get(finalI).getScene().getWindow().getX() > (allScreenBounds.getMaxX() - messenger.getResolutionizer().screenWidth)) {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMaxX() - messenger.getResolutionizer().screenWidth);
                        } }});
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
                    }});
            tileList.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tileList.get(finalI).setBorderColor(Color.TRANSPARENT);
                    }});
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
    private void screenMove(Stage primaryStage, Bounds allScreenBounds, ArrayList<Screen> screens) {
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

    ArrayList<Tile> gauges;

    private void buildDifferential() {
        gauges = new ArrayList<>();

        double theGoal = retailTotalGoalBuild / 540;
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
                currentGoal = retailTotalGoalBuild;
            }
        }
        if (currentTime.getHour() > 15) {
            currentGoal = retailTotalGoalBuild;
        }
        retailBuildGauge.setValue(retailTotalCurrentBuild - currentGoal);
        retailTestGauge.setValue(retailTotalCurrentTest - currentGoal);

        gauges.add(retailTestGauge);
        gauges.add(retailBuildGauge);

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
   public Tile getRetailBuild() {
       return retailBuild;
   }
   public void setRetailBuild(Tile retailBuild) {
       this.retailBuild = retailBuild;
   }
   public Tile getRetailFTT() {
       return retailFTT;
   }
   public void setRetailFTT(Tile retailFTT) {
       this.retailFTT = retailFTT;
   }
   public Tile getRetailTest() {
       return retailTest;
   }
   public void setRetailTest(Tile retailTest) {
       this.retailTest = retailTest;
   }
   public Tile getRetailTestFTT() {
       return retailTestFTT;
   }
   public void setRetailTestFTT(Tile retailTestFTT) {
       this.retailTestFTT = retailTestFTT;
   }
   public String getUseDate() {
       return useDate;
   }
   public void setUseDate(String useDate) {
       this.useDate = useDate;
   }
}
