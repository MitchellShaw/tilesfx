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
import main.java.eu.hansolo.tilesfx.Tile;
import main.java.eu.hansolo.tilesfx.TileBuilder;
import main.java.eu.hansolo.tilesfx.skins.BarChartItem;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class PeriphBuildController extends Controller implements Initializable,Methods {
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
    Tile periphBuild;
    @FXML
    Tile periphBuildGauge;
    @FXML
    Tile periphFTT;


    @FXML
    Tile periphTest;
    @FXML
    Tile periphTestGauge;
    @FXML
    Tile periphTestFTT;

    private HBox myBox;
    private HBox hbox;
    private double kiwi4sCurrentBuild;
    private double kiwi4sGoalBuild;
    private double kiwi2XsCurrentBuild;
    private double kiwi2XsGoalBuild;
    private double bumpBarsCurrentBuild;
    private double bumpBarsGoalBuild;
    private double pantherEPC4sCurrentBuild;
    private double pantherEPC4sGoalBuild;
    private double periphGoalTotalBuild;
    private double periphCurrentTotalBuild;
    private double periphThrough;
    private double periphPercentTotalBuild;
    private double periphBar1Total;
    private double periphBar1Goal;
    private double periphBar2Total;
    private double periphBar2Goal;
    private double periphBar3Total;
    private double periphBar3Goal;
    private double periphCurrentTotalTest;
    private double periphPercentTotalTest;
    private double kiwi4sCurrentTest;
    private double kiwi2XsCurrentTest;
    private double bumpBarsCurrentTest;
    private double pantherEPC4sCurrentTest;
    private BarChartItem periphBar1Data;
    private BarChartItem periphBar2Data;
    private BarChartItem periphBar3Data;
    private BarChartItem periphBar1DataTest;
    private BarChartItem periphBar2DataTest;
    private BarChartItem periphBar3DataTest;
    private double x = 0;
    private double y = 0;
    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();
    private Messenger messenger;
    private String useDate = "0";
    private ImageView stopView = new ImageView();
    private final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    private final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    private final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");
    private final ImageView logoView = new ImageView();
    private final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");
    private ArrayList<Tile> tiles;
    private DecimalFormat df = new DecimalFormat("#.0");
    private DecimalFormat hundred = new DecimalFormat("#");

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

        kiwi4sCurrentBuild = mainBuildController.getKiwi4sCurrentBuild();
        kiwi4sGoalBuild = mainBuildController.getKiwi4sGoalBuild();
        kiwi2XsCurrentBuild = mainBuildController.getKiwi2XsCurrentBuild();
        kiwi2XsGoalBuild = mainBuildController.getKiwi2XsGoalBuild();
        bumpBarsCurrentBuild = mainBuildController.getBumpBarsCurrentBuild();
        bumpBarsGoalBuild = mainBuildController.getBumpBarsGoalBuild();
        pantherEPC4sCurrentBuild = mainBuildController.getPantherEPC4sCurrentBuild();
        pantherEPC4sGoalBuild = mainBuildController.getPantherEPC4sGoalBuild();
        periphGoalTotalBuild = mainBuildController.getPeriphGoalTotalBuild();
        periphCurrentTotalBuild = mainBuildController.getPeriphCurrentTotalBuild();
        periphThrough = mainBuildController.getPeriphThrough();
        periphPercentTotalBuild = mainBuildController.getPeriphPercentTotalBuild();
        periphBar1Total = mainBuildController.getPeriphBar1Total();
        periphBar1Goal = mainBuildController.getPeriphBar1Goal();
        periphBar2Total = mainBuildController.getPeriphBar2Total();
        periphBar2Goal = mainBuildController.getPeriphBar2Goal();
        periphBar3Total = mainBuildController.getPeriphBar3Total();
        periphBar3Goal = mainBuildController.getPeriphBar3Goal();
        periphCurrentTotalTest = mainTestController.getPeriphCurrentTotalTest();
        periphPercentTotalTest = mainTestController.getPeriphPercentTotalTest();
        kiwi4sCurrentTest = mainTestController.getKiwi4sCurrentTest();
        kiwi2XsCurrentTest = mainTestController.getKiwi2XsCurrentTest();
        bumpBarsCurrentTest = mainTestController.getBumpBarsCurrentTest();
        pantherEPC4sCurrentTest = mainTestController.getPantherEPC4sCurrentTest();

        //---------------------------------Creating the Bar Chart Items for Periph-------------------------------------
        periphBar1Data = new BarChartItem("1635", periphBar1Total, periphBar1Goal, Tile.BLUE);
        periphBar2Data = new BarChartItem("1642/1924", periphBar2Total, periphBar2Goal, Tile.ORANGE);
        periphBar3Data = new BarChartItem("1646/1651", periphBar3Total, periphBar3Goal, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Periph-------------------------------------
        periphBar1DataTest = new BarChartItem("1635",periphBar1Total,periphBar1Goal, Tile.BLUE);
        periphBar2DataTest = new BarChartItem("1642/1924",periphBar2Total,periphBar2Goal, Tile.ORANGE);
        periphBar3DataTest = new BarChartItem("1646/1651",periphBar3Total,periphBar3Goal, Tile.GREEN);

        periphBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(periphBar1Data, periphBar2Data, periphBar3Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(periphThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        periphTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(periphBar1DataTest,periphBar2DataTest,periphBar3DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphTestFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPeriphThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if(messenger.getMainBuildController().getPeriphThrough() == 100)
        {
            periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
        }
        if(messenger.getMainBuildController().getPeriphThrough() == 100)
        {
            periphTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
        }

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

        periphBuildGauge = TileBuilder.create()
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

        periphTestGauge = TileBuilder.create()
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


        logoView.setImage(logoImage);
        logoView.setPreserveRatio(true);

        logoView.setFitHeight(logo.getPrefHeight());
        logoView.setFitWidth(logo.getPrefWidth());

        logo.setGraphic(logoView);

        if (Integer.parseInt(useDate) < 30) {
            stopView.setImage(redImage);
        }
        if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
            stopView.setImage(yellowImage);
        }
        if (Integer.parseInt(useDate) >= 60) {
            stopView.setImage(greenImage);
        }

        stopView.setFitWidth(stopLight.getPrefWidth());
        stopView.setFitHeight(stopLight.getPrefHeight());

        stopView.setPreserveRatio(true);

        if (Integer.parseInt(useDate) < 30) {
            stopView.setImage(redImage);
        }
        if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
            stopView.setImage(yellowImage);
        }
        if (Integer.parseInt(useDate) >= 60) {
            stopView.setImage(greenImage);
        }

        stopView.setFitWidth(stopLight.getPrefWidth());
        stopView.setFitHeight(stopLight.getPrefHeight());

        stopView.setPreserveRatio(true);

        stopLight.setGraphic(stopView);


        pane.add(periphBuild, 1, 0, 1, 2);
        pane.add(periphBuildGauge, 2, 0, 1, 2);
        pane.add(periphFTT, 3, 0, 1, 2);
        pane.add(periphTest, 1, 2, 1, 2);
        pane.add(periphTestGauge, 2, 2, 1, 2);
        pane.add(periphTestFTT, 3, 2, 1, 2);
        pane.add(logo, 0, 0, 1, 1);
        pane.add(clock, 0, 1, 1, 1);
        pane.add(stopLight, 0, 2, 1, 1);
        pane.add(daySince, 0, 3, 1, 1);

        tiles.add(periphBuild);
        tiles.add(periphBuildGauge);
        tiles.add(periphFTT);
        tiles.add(periphTest);
        tiles.add(periphTestGauge);
        tiles.add(periphTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        createActions();
        if(pane != null)
        {
            super.tilesListeners(tiles,messenger);
            buildDifferential();
        }
        refresh();

    }

    public void refresh()
    {
        Platform.runLater( () ->
        {
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

            stopLight.setGraphic(stopView);

            periphCurrentTotalBuild = messenger.getMainBuildController().getPeriphCurrentTotalBuild();
            periphCurrentTotalTest = messenger.getMainTestController().getPeriphCurrentTotalTest();
            periphGoalTotalBuild = messenger.getMainBuildController().getPeriphGoalTotalBuild();

            //---------------------------------Updating the Peripheral Units------------------------------------
            periphBar1Data.setValue(messenger.getMainBuildController().getPeriphBar1Total());
            periphBar1Data.setMaxValue(messenger.getMainBuildController().getPeriphBar1Goal());
            periphBar2Data.setValue(messenger.getMainBuildController().getPeriphBar2Total());
            periphBar2Data.setMaxValue(messenger.getMainBuildController().getPeriphBar2Goal());
            periphBar3Data.setValue(messenger.getMainBuildController().getPeriphBar3Total());
            periphBar3Data.setMaxValue(messenger.getMainBuildController().getPeriphBar3Goal());

            periphBar1DataTest.setValue(messenger.getMainTestController().getPeriphBar1Total());
            periphBar1DataTest.setMaxValue(messenger.getMainTestController().getPeriphBar1Goal());
            periphBar2DataTest.setValue(messenger.getMainTestController().getPeriphBar2Total());
            periphBar2DataTest.setMaxValue(messenger.getMainTestController().getPeriphBar2Goal());
            periphBar3DataTest.setValue(messenger.getMainTestController().getPeriphBar3Total());
            periphBar3DataTest.setMaxValue(messenger.getMainTestController().getPeriphBar3Goal());

            periphFTT.setDescription(df.format(messenger.getMainBuildController().getPeriphThrough()) + "%");
            if(messenger.getMainBuildController().getPeriphThrough() == 100)
            {
                periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
            }
            periphTestFTT.setDescription(df.format(messenger.getMainBuildController().getPeriphThrough()) + "%");
            if(messenger.getMainBuildController().getPeriphThrough() == 100)
            {
                periphTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough())+"%");
            }

            buildDifferential();

        });
    }

    private void createActions() {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE)
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
        periphBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuildOverview());
            }
        });
        periphBuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPeriphBuildOverview());
            }
        });
        periphTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPeriphTestUser());
            }
        });
        periphTestGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getPeriphTestUser());
            }
        });
    }


    ArrayList<Tile> gauges;

    private void buildDifferential() {
        gauges = new ArrayList<>();

        double theGoal = periphGoalTotalBuild / 540;
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
                currentGoal = periphGoalTotalBuild;
            }
        }
        if (currentTime.getHour() > 15) {
            currentGoal = periphGoalTotalBuild;
        }
        periphBuildGauge.setValue(periphCurrentTotalBuild - currentGoal);
        periphTestGauge.setValue(periphCurrentTotalTest - currentGoal);

        gauges.add(periphTestGauge);
        gauges.add(periphBuildGauge);

        boolean flag = false;

        for(int i = 0;i<gauges.size();i++)
        {
            flag = false;
            if(gauges.get(i).getValue() > 0)
            {
                gauges.get(i).setValueColor(Color.valueOf("#54B948"));
                flag = true;
            }
            if(gauges.get(i).getValue() == 0 && !flag)
            {
                gauges.get(i).setValueColor(Color.WHITE);
                flag = true;
            }
            if(gauges.get(i).getValue() < 0 && !flag)
            {
                gauges.get(i).setValueColor(Tile.RED);
                flag = true;
            }
            gauges.get(i).setUnitColor(gauges.get(i).getValueColor());

        }
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

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String getUseDate() {
        return useDate;
    }

    @Override
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

}