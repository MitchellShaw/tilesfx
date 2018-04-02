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

public class OpticBuildController extends Controller implements Initializable,Methods
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
    Tile opticBuild;
    @FXML
    Tile opticFTT;


    @FXML
    Tile opticTest;
    @FXML
    Tile opticBuildGauge;
    @FXML
    Tile opticTestGauge;

    @FXML
    Tile opticTestFTT;

    private HBox myBox;
    private HBox hbox;

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    private String useDate = "0";

    private DecimalFormat df = new DecimalFormat("#.0");
    private DecimalFormat hundred = new DecimalFormat("#");

    private ImageView stopView = new ImageView();

    private final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    private final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    private final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    private final ImageView logoView = new ImageView();
    private final Image logoImage = new Image("/eu/hansolo/tilesfx/Optic.png");

    private Messenger messenger;

    private ArrayList<Tile> tiles;

    private BarChartItem optic12Data;
    private BarChartItem optic5Data;
    private BarChartItem optic12DataTest;

    private double opticGoalTotalBuild;
    private double opticCurrentTotalBuild;
    private double opticThrough;
    private double optic5sCurrentBuild;
    private double optic5sGoalBuild;
    private double optic12sCurrentBuild;
    private double optic12sGoalBuild;
    private double opticPercentTotalBuild;

    private double optic12sCurrentTest;
    private double opticCurrentTotalTest;
    private double opticPercentTotalTest;

    @FXML
    private GridPane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Build Variables
        opticGoalTotalBuild = messenger.getMainBuildController().getOpticGoalTotalBuild();
        opticCurrentTotalBuild = messenger.getMainBuildController().getOpticCurrentTotalBuild();
        opticThrough = messenger.getMainBuildController().getOpticThrough();
        optic5sCurrentBuild = messenger.getMainBuildController().getOptic5sCurrentBuild();
        optic5sGoalBuild = messenger.getMainBuildController().getOptic5sGoalBuild();
        optic12sCurrentBuild = messenger.getMainBuildController().getOptic12sCurrentBuild();
        optic12sGoalBuild = messenger.getMainBuildController().getOptic12sGoalBuild();
        opticPercentTotalBuild = messenger.getMainBuildController().getOpticPercentTotalBuild();

        optic12Data = new BarChartItem("6002", optic12sCurrentBuild, optic12sGoalBuild, Tile.ORANGE);
        optic5Data = new BarChartItem("6001", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        optic12DataTest = new BarChartItem("6002", optic12sCurrentTest, optic12sGoalBuild, Tile.ORANGE);

        //Test Variables
        optic12sCurrentTest = messenger.getMainTestController().getOptic12sCurrentTest();
        opticCurrentTotalTest = messenger.getMainTestController().getOpticCurrentTotalTest();
        opticPercentTotalTest = messenger.getMainTestController().getOpticPercentTotalTest();




        opticBuild = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(optic5Data, optic12Data)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();


        opticFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("FTT")
                .description(df.format(opticThrough) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();


        opticBuildGauge = TileBuilder.create()
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

        opticTestGauge = TileBuilder.create()
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

        if (opticThrough == 100) {
            opticFTT.setDescription(hundred.format(opticThrough) + "%");
        }

        pane.add(opticBuild, 1, 0, 1, 2);
        pane.add(opticBuildGauge, 2, 0, 1, 2);
        pane.add(opticFTT, 3, 0, 1, 2);

        opticTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Test")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.50))
                .barChartItems(optic12DataTest)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticTestFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("FTT")
                .description(df.format(messenger.getMainBuildController().getOpticThrough())+"%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

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

        pane.add(opticTest, 1, 2, 1, 2);
        pane.add(opticTestGauge, 2, 2, 1, 2);
        pane.add(opticTestFTT, 3, 2, 1, 2);
        pane.add(logo, 0, 0, 1, 1);
        pane.add(clock, 0, 1, 1, 1);
        pane.add(stopLight, 0, 2, 1, 1);
        pane.add(daySince, 0, 3, 1, 1);

        tiles = new ArrayList<>();

        tiles.add(opticBuild);
        tiles.add(opticBuildGauge);
        tiles.add(opticFTT);
        tiles.add(opticTest);
        tiles.add(opticTestGauge);
        tiles.add(opticTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);

        createActions();
        if (pane != null) {
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

            opticGoalTotalBuild = messenger.getMainBuildController().getOpticGoalTotalBuild();
            opticCurrentTotalBuild = messenger.getMainBuildController().getOpticCurrentTotalBuild();
            opticCurrentTotalTest = messenger.getMainTestController().getOpticCurrentTotalTest();

            optic5Data.setValue(messenger.getMainBuildController().getOptic5sCurrentBuild());
            optic5Data.setMaxValue(messenger.getMainBuildController().getOptic5sGoalBuild());
            optic12Data.setValue(messenger.getMainBuildController().getOptic12sCurrentBuild());
            optic12Data.setMaxValue(messenger.getMainBuildController().getOptic12sGoalBuild());
            optic12DataTest.setValue(messenger.getMainTestController().getOptic12sCurrentTest());
            optic12DataTest.setMaxValue(messenger.getMainBuildController().getOptic12sGoalBuild());

            opticFTT.setDescription(df.format(messenger.getMainBuildController().getOpticThrough()) + "%");
            if(messenger.getMainBuildController().getOpticThrough() == 100)
            {
                opticFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough())+"%");
            }
            opticTestFTT.setDescription(df.format(messenger.getMainBuildController().getOpticThrough()) + "%");
            if(messenger.getMainBuildController().getOpticThrough() == 100)
            {
                opticTestFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough())+"%");
            }
            if(opticBuildGauge!=null && opticTestGauge != null)
            {
                buildDifferential();
            }
        });
    }

    ArrayList<Tile> gauges;

    private void buildDifferential() {
        gauges = new ArrayList<>();

        double theGoal = opticGoalTotalBuild / 540;
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
                currentGoal = opticGoalTotalBuild;
            }
        }
        if (currentTime.getHour() > 15) {
            currentGoal = opticGoalTotalBuild;
        }
        opticBuildGauge.setValue(opticCurrentTotalBuild - currentGoal);
        opticTestGauge.setValue(opticCurrentTotalTest - currentGoal);

        gauges.add(opticTestGauge);
        gauges.add(opticBuildGauge);

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
        opticBuild.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuildOverview());
            }
        });
        opticBuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticBuildOverview());
            }
        });
        opticTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticTestUser());
            }
        });
        opticTestGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticTestUser());
            }
        });
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
