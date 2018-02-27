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
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.java.eu.hansolo.tilesfx.Tile;
import main.java.eu.hansolo.tilesfx.TileBuilder;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.io.File;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class opticBuildOverviewController extends Controller implements Initializable,Methods
{
    @FXML
    Tile logo;
    @FXML
    Tile clock;
    @FXML
    Tile stopLight;
    @FXML
    Tile daySince;
    @FXML
    Tile dept;

    @FXML
    Tile line1;
    @FXML
    Tile line2;
    @FXML
    Tile line3;
    @FXML
    Tile line4;

    @FXML
    Tile line1BuildGauge;
    @FXML
    Tile line2BuildGauge;
    @FXML
    Tile line3BuildGauge;
    @FXML
    Tile line4BuildGauge;

    @FXML
    Line middleLine;
    @FXML
    Line leftLine;

    private int line1Total;
    private int line2Total;
    private int line3Total;
    private int line4Total;

    private HBox myBox;
    private HBox hbox;

    private ImageView stopView = new ImageView();
    private final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    private final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    private final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    private final ImageView logoView = new ImageView();
    private final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

    private String useDate = "0";

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    private Messenger messenger;

    private ArrayList<Tile> tiles;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

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

        dept  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .description("Retail")
                .roundedCorners(false)
                .build();

        line1  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 1")
                .description(Integer.toString(line1Total))
                .roundedCorners(false)
                .build();

        line1BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-75)
                .maxValue(75)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Build Difference")
                .thresholdColor(Color.valueOf("#54B948"))
                .build();

        line2  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 2")
                .description(Integer.toString(line2Total))
                .roundedCorners(false)
                .build();
        line2BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-75)
                .maxValue(75)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Build Difference")
                .thresholdColor(Color.valueOf("#54B948"))
                .build();

        line3  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 3")
                .description(Integer.toString(line3Total))
                .roundedCorners(false)
                .build();

        line3BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-75)
                .maxValue(75)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Build Difference")
                .thresholdColor(Color.valueOf("#54B948"))
                .build();

        line4  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 4")
                .description("").description(Integer.toString(line4Total))
                .roundedCorners(false)
                .build();

        line4BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.375), messenger.getResolutionizer().setTileHeight(.25))
                .backgroundColor(rgb(42, 42, 42))
                .unit(" units")
                .roundedCorners(false)
                .barColor(Tile.RED)
                .minValue(-75)
                .maxValue(75)
                .threshold(0)
                .thresholdVisible(false)
                .titleAlignment(TextAlignment.CENTER)
                .title("Hourly Build Difference")
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

        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);

        pane.add(line1,1,0,1,1);
        pane.add(line1BuildGauge,1,1,1,1);

        pane.add(line2,2,0,1,1);
        pane.add(line2BuildGauge,2,1,1,1);

        pane.add(line3,1,2,1,1);
        pane.add(line3BuildGauge,1,3,1,1);

        pane.add(line4,2,2,2,1);
        pane.add(line4BuildGauge,2,3,1,1);
        leftLine.toFront();
        middleLine.toFront();

        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(line1);
        tiles.add(line1BuildGauge);
        tiles.add(line2);
        tiles.add(line2BuildGauge);
        tiles.add(line3);
        tiles.add(line3BuildGauge);
        tiles.add(line4);
        tiles.add(line4BuildGauge);

        createActions();
        if(pane != null)
        {
            super.tilesListeners(tiles,messenger);
            buildDifferential();
        }
    }

    ArrayList<Tile> conversionList;
    private void buildDifferential()
    {
        conversionList = new ArrayList<>();

        double theGoal = (messenger.getMainBuildController().getOpticGoalTotalBuild()/ 540)/4;
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
            if(currentTime.getMinute()< 30)
            {
                modifier = 480;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + (minute*2));
            }
            else
            {
                currentGoal = (messenger.getMainBuildController().getOpticGoalTotalBuild()/ 540)/4;
            }
        }
        if (currentTime.getHour() > 15)
        {
            currentGoal = (messenger.getMainBuildController().getOpticGoalTotalBuild()/ 540)/4;
        }

        line1BuildGauge.setValue(line1Total - currentGoal);
        line2BuildGauge.setValue(line2Total - currentGoal);
        line3BuildGauge.setValue(line3Total - currentGoal);
        line4BuildGauge.setValue(line4Total - currentGoal);

        conversionList.add(line1BuildGauge);
        conversionList.add(line2BuildGauge);
        conversionList.add(line3BuildGauge);
        conversionList.add(line4BuildGauge);

        boolean flag = false;

        for(int i = 0;i<conversionList.size();i++)
        {
            flag = false;
            if(conversionList.get(i).getValue() > 0)
            {
                conversionList.get(i).setValueColor(Color.valueOf("#54B948"));
                flag = true;
            }
            if(conversionList.get(i).getValue() == 0 && !flag)
            {
                conversionList.get(i).setValueColor(Color.WHITE);
                flag = true;
            }
            if(conversionList.get(i).getValue() < 0 && !flag)
            {
                conversionList.get(i).setValueColor(Tile.RED);
                flag = true;
            }
            conversionList.get(i).setUnitColor(conversionList.get(i).getValueColor());

        }
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

            line1.setDescription(Integer.toString(line1Total));
            line2.setDescription(Integer.toString(line2Total));
            line3.setDescription(Integer.toString(line3Total));
            line4.setDescription(Integer.toString(line4Total));

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
                    messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
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
        line1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine1());
            }
        });
        line1BuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine1());
            }
        });
        line2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine2());
            }
        });
        line2BuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine2());
            }
        });
        line3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine3());
            }
        });
        line3BuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine3());
            }
        });
        line4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine4());
            }
        });
        line4BuildGauge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getOpticLine4());
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

    public int getLine1Total() {
        return line1Total;
    }

    public void setLine1Total(int line1Total) {
        this.line1Total = line1Total;
    }

    public int getLine2Total() {
        return line2Total;
    }

    public void setLine2Total(int line2Total) {
        this.line2Total = line2Total;
    }


    public void setLine3Total(int line3Total) {
        this.line3Total = line3Total;
    }


    public void setLine4Total(int line4Total) {
        this.line4Total = line4Total;
    }

    @Override
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }
}
