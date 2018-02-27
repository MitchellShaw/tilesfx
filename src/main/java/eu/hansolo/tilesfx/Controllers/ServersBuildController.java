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

public class ServersBuildController extends Controller implements Initializable,Methods
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

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    private DecimalFormat df = new DecimalFormat("#.0");
    private DecimalFormat hundred = new DecimalFormat("#");

    private Messenger messenger;

    private ArrayList<Tile> tiles;

    private String useDate = "0";

    private double mediaPlayerCurrentBuild;
    private double mediaPlayerGoalBuild;
    private double n3000CurrentBuild;
    private double n3000GoalBuild;
    private double s500CurrentBuild;
    private double s500GoalBuild;
    private double serverGoalTotalBuild;
    private double serverCurrentBuild;
    private double serversThrough;
    private double serverBar1Total;
    private double serverBar1Goal;
    private double serverBar2Total;
    private double serverBar2Goal;
    private double serversPercentTotalBuild;
    private double mediaPlayerCurrentTest;
    private double n3000CurrentTest;
    private double s500CurrentTest;
    private double serverCurrentTest;
    private double serversPercentTotalTest;


     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem serverBar1;
    private BarChartItem serverBar2;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem serverBar1DataTest;
    private BarChartItem serverBar2DataTest;

    private ImageView stopView = new ImageView();
    private final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    private final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    private final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    private final ImageView logoView = new ImageView();
    private final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

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
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .roundedCorners(false)
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

        serversBuildGauge = TileBuilder.create()
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

        serversTestGauge = TileBuilder.create()
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

        pane.add(serversBuild,1,0,1,2);
        pane.add(serversBuildGauge,2,0,1,2);
        pane.add(serversFTT,3,0,1,2);
        pane.add(serversTest,1,2,1,2);
        pane.add(serversTestGauge,2,2,1,2);
        pane.add(serversTestFTT,3,2,1,2);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);

        tiles.add(serversBuild);
        tiles.add(serversBuildGauge);
        tiles.add(serversFTT);
        tiles.add(serversTest);
        tiles.add(serversTestGauge);
        tiles.add(serversTestFTT);
        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);

        createActions();
        if(pane != null)
        {
            super.tilesListeners(tiles,messenger);
            buildDifferential();
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

            stopLight.setGraphic(stopView);

            buildDifferential();

        });
    }
    ArrayList<Tile> gauges;

    private void buildDifferential() {
        gauges = new ArrayList<>();

        double theGoal = serverGoalTotalBuild / 540;
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
                currentGoal = serverGoalTotalBuild;
            }
        }
        if (currentTime.getHour() > 15) {
            currentGoal = serverGoalTotalBuild;
        }
        serversBuildGauge.setValue(serverCurrentBuild - currentGoal);
        serversTestGauge.setValue(serverCurrentTest - currentGoal);

        gauges.add(serversTestGauge);
        gauges.add(serversBuildGauge);

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
        serversTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getServersTestUser());
            }
        });
        serversTestGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getServersTestUser());
            }
        });
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

    @Override
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }


}

