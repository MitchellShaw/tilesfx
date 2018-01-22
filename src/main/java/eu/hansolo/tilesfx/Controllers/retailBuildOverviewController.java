package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Messenger;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import sun.plugin2.util.ColorUtil;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class retailBuildOverviewController implements Initializable
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
    Tile filler;

    @FXML
    Tile line1;
    @FXML
    Tile line2;
    @FXML
    Tile line3;
    @FXML
    Tile line4;
    @FXML
    Tile line5;
    @FXML
    Tile line6;

    @FXML
    Tile line1BuildGauge;
    @FXML
    Tile line2BuildGauge;
    @FXML
    Tile line3BuildGauge;
    @FXML
    Tile line4BuildGauge;
    @FXML
    Tile line5BuildGauge;
    @FXML
    Tile line6BuildGauge;

    @FXML
    Tile line1Differential;
    @FXML
    Tile line2Differential;
    @FXML
    Tile line3Differential;
    @FXML
    Tile line4Differential;
    @FXML
    Tile line5Differential;
    @FXML
    Tile line6Differential;

    int line1Total;
    int line2Total;
    int line3Total;
    int line4Total;
    int line5Total;
    int line6Total;



    HBox myBox;
    HBox hbox;

    ImageView stopView = new ImageView();
    final Image redImage = new Image("/Red Light.PNG");
    final Image yellowImage = new Image("/Yellow Light.PNG");
    final Image greenImage = new Image("/Green Light.PNG");

    final ImageView logoView = new ImageView();
    final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");

    String useDate = "0";

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    Messenger messenger;

    ArrayList<Tile> tiles;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .roundedCorners(false)
                .graphic(hbox)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.334))
                .roundedCorners(false)
                .graphic(myBox)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        dept  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("Retail")
                .roundedCorners(false)
                .build();

        filler  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .build();

        line1  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 1")
                .description(Integer.toString(line1Total))
                .roundedCorners(false)
                .build();

        line1BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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
        line1Differential  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();


        line2  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 2")
                .description(Integer.toString(line2Total))
                .roundedCorners(false)
                .build();
        line2BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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

        line2Differential  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();

        line3  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 3")
                .description(Integer.toString(line3Total))
                .roundedCorners(false)
                .build();

        line3BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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

        line3Differential  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();

        line4  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 4")
                .description("").description(Integer.toString(line4Total))
                .roundedCorners(false)
                .build();

        line4BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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

        line4Differential  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();

        line5  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Line 5")
                .description(Integer.toString(line5Total))
                .roundedCorners(false)
                .build();

        line5BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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

        line5Differential  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();

        line6  = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .title("Next Gen Display")
                .description(Integer.toString(line6Total))
                .roundedCorners(false)
                .build();

        line6BuildGauge = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
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

        line6Differential= TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.167))
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .roundedCorners(false)
                .build();

        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(dept,0,2,1,1);
        pane.add(stopLight,0,3,1,2);
        pane.add(daySince,0,5,1,1);

        pane.add(line1,1,0,1,1);
        pane.add(line1BuildGauge,1,1,1,1);
        pane.add(line1Differential,1,2,1,1);
        pane.add(line2,2,0,1,1);
        pane.add(line2BuildGauge,2,1,1,1);
        pane.add(line2Differential,2,2,1,1);
        pane.add(line3,3,0,1,1);
        pane.add(line3BuildGauge,3,1,1,1);
        pane.add(line3Differential,3,2,1,1);
        pane.add(line4,1,3,1,1);
        pane.add(line4BuildGauge,1,4,1,1);
        pane.add(line4Differential,1,5,1,1);
        pane.add(line5,2,3,1,1);
        pane.add(line5BuildGauge,2,4,1,1);
        pane.add(line5Differential,2,5,1,1);
        pane.add(line6,3,3,1,1);
        pane.add(line6BuildGauge,3,4,1,1);
        pane.add(line6Differential,3,5,1,1);

        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(dept);
        tiles.add(line1);
        tiles.add(line1BuildGauge);
        tiles.add(line1Differential);
        tiles.add(line2);
        tiles.add(line2BuildGauge);
        tiles.add(line2Differential);
        tiles.add(line3);
        tiles.add(line3BuildGauge);
        tiles.add(line3Differential);
        tiles.add(line4);
        tiles.add(line4BuildGauge);
        tiles.add(line4Differential);
        tiles.add(line5);
        tiles.add(line5BuildGauge);
        tiles.add(line5Differential);
        tiles.add(line6);
        tiles.add(line6BuildGauge);
        tiles.add(line6Differential);

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

            line1.setDescription(Integer.toString(line1Total));
            line2.setDescription(Integer.toString(line2Total));
            line3.setDescription(Integer.toString(line3Total));
            line4.setDescription(Integer.toString(line4Total));
            line5.setDescription(Integer.toString(line5Total));
            line6.setDescription(Integer.toString(line6Total));

            buildDifferential();

        });
    }
    ArrayList<Integer> conversionList;
    private void buildDifferential()
    {
        conversionList = new ArrayList<>();

        double theGoal = (messenger.getMainBuildController().getRetailBar1Goal() / 540)/5;
        double theDisplayGoal = (messenger.getMainBuildController().getNextGenDisplayGoalsBuild() / 540);
        double modifier = 0;
        double currentGoal = 0;
        double currentDisplayGoal=0;
        double minute = 0;
        ZonedDateTime currentTime = clock.getTime();
        if (currentTime.getHour() == 7) {
            modifier = 0;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 8) {
            modifier = 60;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 9) {
            modifier = 120;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 10) {
            modifier = 180;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 11) {
            modifier = 240;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 12) {
            modifier = 300;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 13) {
            modifier = 360;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 14) {
            modifier = 420;
            minute = currentTime.getMinute();
            currentGoal = theGoal * (modifier + minute);
            currentDisplayGoal = theDisplayGoal * (modifier + minute);
        }
        if (currentTime.getHour() == 15) {
            if(currentTime.getMinute()< 30)
            {
                modifier = 480;
                minute = currentTime.getMinute();
                currentGoal = theGoal * (modifier + (minute*2));
                currentDisplayGoal = theDisplayGoal * (modifier + minute);
            }
            else
            {
                currentGoal = messenger.getMainBuildController().getRetailBar1Goal();
                currentDisplayGoal = messenger.getMainBuildController().getNextGenDisplayGoalsBuild();
            }
        }
        if (currentTime.getHour() > 15)
        {
            currentGoal = messenger.getMainBuildController().getRetailBar1Goal();
            currentDisplayGoal = messenger.getMainBuildController().getNextGenDisplayGoalsBuild();
        }

        line1.setTextColor(Color.GREEN);
        line1BuildGauge.setValue(line1Total - currentGoal);
        line2BuildGauge.setValue(line2Total - currentGoal);
        line3BuildGauge.setValue(line3Total - currentGoal);
        line4BuildGauge.setValue(line4Total - currentGoal);
        line5BuildGauge.setValue(line5Total - currentGoal);
        line6BuildGauge.setValue(line6Total - currentDisplayGoal);

        int line1Display = (int)(line1Total - currentGoal);
        int line2Display = (int)(line2Total - currentGoal);
        int line3Display = (int)(line3Total - currentGoal);
        int line4Display = (int)(line4Total - currentGoal);
        int line5Display = (int)(line5Total - currentGoal);
        int line6Display = (int)(line6Total - currentDisplayGoal);

        conversionList.add(line1Display);
        conversionList.add(line2Display);
        conversionList.add(line3Display);
        conversionList.add(line4Display);
        conversionList.add(line5Display);
        conversionList.add(line6Display);

        String line1Return = "";
        String line2Return = "";
        String line3Return = "";
        String line4Return = "";
        String line5Return = "";
        String line6Return = "";

        for(int i = 0;i<conversionList.size();i++)
        {
            if(conversionList.get(i) > 0)
            {
                if(i ==0)
                {
                    line1Return = "+" + Integer.toString(line1Display) + " units" + "\n\n";
                    line1Differential.setTextColor(Color.valueOf("#54B948"));
                    System.out.println(line1Differential.getTextColor());
                }
                if(i ==1)
                {
                    line2Return = "+" + Integer.toString(line2Display) + " units" + "\n\n";
                    line2Differential.setTextColor(Color.valueOf("#54B948"));
                }
                if(i ==2)
                {
                    line3Return = "+" + Integer.toString(line3Display) + " units" + "\n\n";
                    line3Differential.setTextColor(Color.valueOf("#54B948"));
                }
                if(i ==3)
                {
                    line4Return = "+" + Integer.toString(line4Display) + " units" + "\n\n";
                    line4Differential.setTextColor(Color.valueOf("#54B948"));
                }
                if(i ==4)
                {
                    line5Return = "+" + Integer.toString(line5Display) + " units" + "\n\n";
                    line5Differential.setTextColor(Color.valueOf("#54B948"));
                }
                if(i ==5)
                {
                    line6Return = "+" + Integer.toString(line6Display) + " units" + "\n\n";
                    line6Differential.setTextColor(Color.valueOf("#54B948"));
                }
            }
            if(conversionList.get(i) == 0)
            {
                if(i ==0)
                {
                    line1Return =Integer.toString(line1Display) + " units" + "\n\n";
                    line1Differential.setTextColor(Color.WHITE);
                }
                if(i ==1)
                {
                    line2Return =Integer.toString(line2Display) + " units" + "\n\n";
                    line2Differential.setTextColor(Color.WHITE);
                }
                if(i ==2)
                {
                    line3Return =Integer.toString(line3Display) + " units" + "\n\n";
                    line3Differential.setTextColor(Color.WHITE);
                }
                if(i ==3)
                {
                    line4Return =Integer.toString(line4Display) + " units" + "\n\n";
                    line4Differential.setTextColor(Color.WHITE);
                }
                if(i ==4)
                {
                    line5Return =Integer.toString(line5Display) + " units" + "\n\n";
                    line5Differential.setTextColor(Color.WHITE);
                }
                if(i ==5)
                {
                    line6Return =Integer.toString(line6Display) + " units" + "\n\n";
                    line6Differential.setTextColor(Color.WHITE);
                }
            }
            if(conversionList.get(i) < 0)
            {
                if(i ==0)
                {
                    line1Return = Integer.toString(line1Display) + " units" + "\n\n";
                    line1Differential.setTextColor(Tile.RED);
                }
                if(i ==1)
                {
                    line2Return = Integer.toString(line2Display) + " units" + "\n\n";
                    line2Differential.setTextColor(Tile.RED);
                }
                if(i ==2)
                {
                    line3Return = Integer.toString(line3Display) + " units" + "\n\n";
                    line3Differential.setTextColor(Tile.RED);
                }
                if(i ==3)
                {
                    line4Return = Integer.toString(line4Display) + " units" + "\n\n";
                    line4Differential.setTextColor(Tile.RED);
                }
                if(i ==4)
                {
                    line5Return = Integer.toString(line5Display) + " units" + "\n\n";
                    line5Differential.setTextColor(Tile.RED);
                }
                if(i ==5)
                {
                    line6Return = Integer.toString(line6Display) + " units" + "\n\n";
                    line6Differential.setTextColor(Tile.RED);
                }
            }
        }

        line1Differential.setDescription(line1Return);
        line2Differential.setDescription(line2Return);
        line3Differential.setDescription(line3Return);
        line4Differential.setDescription(line4Return);
        line5Differential.setDescription(line5Return);
        line6Differential.setDescription(line6Return);
    }
    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
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

    public void setUseDate(String useDate) {
        this.useDate = useDate;
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

    public int getLine3Total() {
        return line3Total;
    }

    public void setLine3Total(int line3Total) {
        this.line3Total = line3Total;
    }

    public int getLine4Total() {
        return line4Total;
    }

    public void setLine4Total(int line4Total) {
        this.line4Total = line4Total;
    }

    public int getLine5Total() {
        return line5Total;
    }

    public void setLine5Total(int line5Total) {
        this.line5Total = line5Total;
    }

    public int getLine6Total() {
        return line6Total;
    }

    public void setLine6Total(int line6Total) {
        this.line6Total = line6Total;
    }
}
