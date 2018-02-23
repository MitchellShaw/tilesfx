package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import main.java.eu.hansolo.tilesfx.Tile;
import main.java.eu.hansolo.tilesfx.TileBuilder;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class posLine3Controller implements Initializable
{
    double sevenGoal;
    double eightGoal;
    double nineGoal;
    double tenGoal;
    double elevenGoal;
    double twelveGoal;
    double oneGoal;
    double twoGoal;
    double threeGoal;
    double fourGoal;
    double fiveGoal;
    double totalGoal;

    String sevenString;
    String eightString;
    String nineString;
    String tenString;
    String elevenString;
    String twelveString;
    String oneString;
    String twoString;
    String threeString;
    String fourString;
    String fiveString;
    String totalString;

    int sevenCurrent;
    int eightCurrent;
    int nineCurrent;
    int tenCurrent;
    int elevenCurrent;
    int twelveCurrent;
    int oneCurrent;
    int twoCurrent;
    int threeCurrent;
    int fourCurrent;
    int fiveCurrent;
    int totalCurrent;

    String sevenDifference;
    String eightDifference;
    String nineDifference;
    String tenDifference;
    String elevenDifference;
    String twelveDifference;
    String oneDifference;
    String twoDifference;
    String threeDifference;
    String fourDifference;
    String fiveDifference;
    String totalDifference;

    ArrayList<timeOrb> timeOrbs;

    DecimalFormat df;
    DecimalFormat zeroFormat;
    NumberFormat goalFormat;

    private final ObservableList<dataOrb> orbList = FXCollections.observableArrayList
            (
                    new dataOrb("7",sevenString,sevenCurrent,sevenDifference),
                    new dataOrb("8",eightString,eightCurrent,eightDifference),
                    new dataOrb("9",nineString,nineCurrent,nineDifference),
                    new dataOrb("10",tenString,tenCurrent,tenDifference),
                    new dataOrb("11",elevenString,elevenCurrent,elevenDifference),
                    new dataOrb("12",twelveString,twelveCurrent,twelveDifference),
                    new dataOrb("1",oneString,oneCurrent,oneDifference),
                    new dataOrb("2",twoString,twoCurrent,twoDifference),
                    new dataOrb("3",threeString,threeCurrent,threeDifference),
                    new dataOrb("4",fourString,fourCurrent,fourDifference),
                    new dataOrb("5",fiveString,fiveCurrent,fiveDifference),
                    new dataOrb("Total",totalString,totalCurrent,totalDifference)
            );

    HashMap<String,Integer> map;
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
    Tile table;


    int line1Total;
    int line2Total;

    HBox myBox;
    HBox hbox;

    ImageView stopView = new ImageView();
    final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    final ImageView logoView = new ImageView();
    final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

    String useDate = "0";

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    Messenger messenger;

    ArrayList<Tile> tiles;

    private TableView<dataOrb> buildTable;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        buildTable = new TableView<>();

        df = new DecimalFormat("+#;-#");
        goalFormat = new DecimalFormat("#");

        tiles = new ArrayList<>();

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.2))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.2))
                .roundedCorners(false)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.2))
                .roundedCorners(false)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        dept = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(Color.valueOf("#54B948"))
                .title("POS")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description("Line 3")
                .build();
        table = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(messenger.getResolutionizer().setTileWidth(.75), messenger.getResolutionizer().screenHeight)
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
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

        buildTable.setPrefHeight(table.getPrefHeight());
        buildTable.setPrefWidth(table.getPrefWidth());

        if(messenger.getResolutionizer().screenWidth == 1920)
        {
            table.setMaxWidth(1440);
            buildTable.setPrefWidth(1440);
            table.setMaxHeight(1080);
            buildTable.setPrefHeight(1080);
        }
        table.setGraphic(buildTable);

        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(dept,0,2,1,1);
        pane.add(stopLight,0,3,1,1);
        pane.add(daySince,0,4,1,1);
        pane.add(table,1,0,3,5);

        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(dept);
        tiles.add(daySince);
        tiles.add(table);

        createActions();

        if(pane != null)
        {
            tilesListeners(tiles);
            createTable();
        }
    }

    private void createTable()
    {
        buildTable.setFixedCellSize(messenger.getResolutionizer().screenHeight/13.15);

        TableColumn hourCol = new TableColumn("Hour");
        TableColumn goalCol = new TableColumn("Goal");
        TableColumn currentCol = new TableColumn("Current");
        TableColumn differential = new TableColumn("Difference");

        hourCol.setSortable(false);
        goalCol.setSortable(false);
        currentCol.setSortable(false);
        differential.setSortable(false);

        hourCol.setPrefWidth(buildTable.getPrefWidth() * .249);
        hourCol.setResizable(false);

        goalCol.setPrefWidth(buildTable.getPrefWidth() * .249);
        goalCol.setResizable(false);

        currentCol.setPrefWidth(buildTable.getPrefWidth() * .249);
        currentCol.setResizable(false);

        differential.setPrefWidth(buildTable.getPrefWidth() * .249);
        differential.setResizable(false);

        double actualGoal = messenger.getMainBuildController().getPosBar1Goal()/4;

        int minuteGoal = (int) actualGoal/450;

        sevenGoal = 60 * minuteGoal;
        eightGoal = 60 * minuteGoal;
        nineGoal = 45 * minuteGoal;
        tenGoal = 60 * minuteGoal;
        elevenGoal = 60 * minuteGoal;
        twelveGoal = 30 * minuteGoal;
        oneGoal = 45 * minuteGoal;
        twoGoal = 60 * minuteGoal;
        threeGoal = 30 * minuteGoal;

        hourCol.setCellValueFactory
                (
                new PropertyValueFactory<dataOrb,String>("hour")
                );
        goalCol.setCellValueFactory
                (
                        new PropertyValueFactory<dataOrb,String>("goal")
                );
        currentCol.setCellValueFactory
                (
                        new PropertyValueFactory<dataOrb,Integer>("actual")
                );
        differential.setCellValueFactory
                (
                        new PropertyValueFactory<dataOrb,String>("difference")
                );

        buildTable.setItems(orbList);

        buildTable.getColumns().addAll(hourCol,goalCol,currentCol,differential);

        buildTable.setEditable(false);

        buildTable.setSelectionModel(null);

        buildTable.setFocusTraversable(false);
    }
    private void differenceFormat(int i)
    {
        if(orbList.get(i).getActual() - Double.valueOf(orbList.get(i).getGoal()) == 0)
        {
            orbList.get(i).setDifference(goalFormat.format(orbList.get(i).getActual() - Double.valueOf(orbList.get(i).getGoal())));
        }else
        {
            orbList.get(i).setDifference(df.format(orbList.get(i).getActual() - Double.valueOf(orbList.get(i).getGoal())));

        }
    }
    private void tableRefresh() {
        timeOrbs = messenger.getOrbList();

        int index = 0;

        for (int i = 0; i < timeOrbs.size(); i++)
        {
            if(timeOrbs.get(i).getLine().equals("MIDLAND.POS_D"))
            {
                index = i;
                break;
            }
        }

        double actualGoal = messenger.getMainBuildController().getPosBar1Goal()/4;

        double minuteGoal = actualGoal/450;


        sevenGoal = 60 * minuteGoal;
        eightGoal = 60 * minuteGoal;
        nineGoal = 45 * minuteGoal;
        tenGoal = 60 * minuteGoal;
        elevenGoal = 60 * minuteGoal;
        twelveGoal = 30 * minuteGoal;
        oneGoal = 45 * minuteGoal;
        twoGoal = 60 * minuteGoal;
        threeGoal = 30 * minuteGoal;

        int currentTotal = 0;
        int currentDifference = 0;

        for(int i = 0; i<orbList.size();i++)
        {
            String header = orbList.get(i).getHour();

            switch(header)
            {
                case "7":
                    orbList.get(i).setGoal(sevenGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("7"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 7)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }

                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());
                    break;
                case "8":
                    orbList.get(i).setGoal(Double.valueOf(goalFormat.format(eightGoal)));

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("8"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 8)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "9":
                    orbList.get(i).setGoal(nineGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("9"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 9)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "10":
                    orbList.get(i).setGoal(tenGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("10"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 10)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "11":
                    orbList.get(i).setGoal(elevenGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("11"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 11)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "12":
                    orbList.get(i).setGoal(twelveGoal);
                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("12"));
                    currentTotal = currentTotal + orbList.get(i).getActual();
                    if(clock.getTime().getHour() < 12)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "1":
                    orbList.get(i).setGoal(oneGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("1"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 13)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "2":
                    orbList.get(i).setGoal(twoGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("2"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 14)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "3":
                    orbList.get(i).setGoal(threeGoal);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("3"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 15)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "4":
                    orbList.get(i).setGoal(0);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("4"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 16)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "5":
                    orbList.get(i).setGoal(0);

                    orbList.get(i).setActual(timeOrbs.get(index).getTimeMap().get("5"));

                    currentTotal = currentTotal + orbList.get(i).getActual();

                    if(clock.getTime().getHour() < 17)
                    {
                        orbList.get(i).setDifference(goalFormat.format(0));
                    }
                    else {
                        differenceFormat(i);
                    }
                    currentDifference = currentDifference + Integer.valueOf(orbList.get(i).getDifference());

                    break;
                case "Total":
                    orbList.get(i).setGoal(messenger.getMainBuildController().getPosBar1Goal()/4);
                    orbList.get(i).setActual(currentTotal);
                    if(currentDifference ==0)
                    {
                        orbList.get(i).setDifference(goalFormat.format(currentDifference));
                    }else {
                        orbList.get(i).setDifference(df.format(currentDifference));
                    }

                    break;
                default:
                    orbList.get(i).setGoal(0);
                    break;
            }
        }


        buildTable.refresh();
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

            tableRefresh();
        });
    }
    private void createActions()
    {
        buildTable.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    messenger.getPrimaryStage().setScene(messenger.getPosBuildOverview());
                }
            }
        });
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getPosBuildOverview());
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

    public ArrayList<timeOrb> getTimeOrbs() {
        return timeOrbs;
    }

    public void setTimeOrbs(ArrayList<timeOrb> timeOrbs) {
        this.timeOrbs = timeOrbs;
    }
}
