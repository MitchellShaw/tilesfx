package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.GoalTool;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainStageController implements Initializable
{
    @FXML
    private Tile posStage;
    @FXML
    private Tile retailStage;
    @FXML
    private Tile serversStage;
    @FXML
    private Tile periphStage;
    @FXML
    private Tile posStagePercent;
    @FXML
    private Tile posTotal;
    @FXML
    private Tile retailStagePercent;
    @FXML
    private Tile retailTotal;
    @FXML
    private Tile serversStagePercent;
    @FXML
    private Tile serversTotal;
    @FXML
    private Tile periphStagePercent;
    @FXML
    private Tile periphTotal;

    @FXML
    private GridPane pane;

    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    double p1x30CurrentStage;
    double p1x30GoalStage;
    double p1x35CurrentStage;
    double p1x35GoalStage;
    double p1532CurrentStage;
    double p1532GoalStage;
    double t1000sCurrentStage;
    double t1000sGoalStage;
    double questsCurrentStage;
    double questsGoalStage;

    double posBar1Total;
    double posBar1Goal;

    double posTotalGoalStage;
    double posTotalCurrentStage;


    //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    double xr7CurrentStage;
    double xr7GoalStage;
    double xr7PlusCurrentStage;
    double xr7PlusGoalStage;
    double xr5CurrentStage;
    double xr5GoalStage;
    double nextGenDisplayCurrentStage;
    double nextGenDisplayGoalsStage;

    double retailTotalGoalStage;
    double retailTotalCurrentStage;

    //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    double mediaPlayerCurrentStage;
    double mediaPlayerGoalStage;
    double n3000CurrentStage;
    double n3000GoalStage;
    double s500CurrentStage;
    double s500GoalStage;
    double serverGoalTotalStage;

    double serverCurrentStage;

    //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    double kiwi4sCurrentStage;
    double kiwi4sGoalStage;
    double kiwi2XsCurrentStage;
    double kiwi2XsGoalStage;
    double bumpBarsCurrentStage;
    double bumpBarsGoalStage;

    double pantherEPC4sCurrentStage;
    double pantherEPC4sGoalStage;

    double periphGoalTotalStage;
    double periphCurrentTotalStage;
    //---------------------------------Variables for Percentages --------------------------------------------------
    double posPercentTotalStage;
    double retailPercentTotalStage;
    double serversPercentTotalStage;
    double periphPercentTotalStage;

    //---------------------------------Creating the Bar Chart Items for Hosp-------------------------------------
    BarChartItem posBar1DataStage;
    BarChartItem p1x30DataStage;
    BarChartItem t1000DataStage;
    BarChartItem questDataStage;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem serverBar1DataStage;
    BarChartItem serverBar2DataStage;
    //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
    BarChartItem periphBar1DataStage;
    BarChartItem periphBar2DataStage;
    BarChartItem periphBar3DataStage;
    //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    BarChartItem nextGenDisplaysStage;
    BarChartItem retailBar1Stage;

    double retailBar1Total;
    double retailBar1Goal;

    double serverBar1Total;
    double serverBar1Goal;

    double serverBar2Total;
    double serverBar2Goal;

    double periphBar1Total;
    double periphBar1Goal;

    double periphBar2Total;
    double periphBar2Goal;

    double periphBar3Total;
    double periphBar3Goal;

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    ArrayList<Tile> tiles;

    Messenger messenger;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tiles = new ArrayList<>();

        posBar1DataStage = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30DataStage = new BarChartItem("7743", p1x30CurrentStage, p1x30GoalStage, Tile.RED);
        t1000DataStage = new BarChartItem("7744", t1000sCurrentStage, t1000sGoalStage, Tile.GREEN);
        questDataStage = new BarChartItem("7791/7792", questsCurrentStage,questsGoalStage, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1DataStage = new BarChartItem("1611/1612", serverBar1Total,serverBar1Goal, Tile.BLUE);
        serverBar2DataStage = new BarChartItem("1656/1657", serverBar2Total,serverBar2Goal, Tile.RED);
        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        periphBar1DataStage = new BarChartItem("1635",periphBar1Total,periphBar1Goal, Tile.BLUE);
        periphBar2DataStage = new BarChartItem("1642/1924",periphBar2Total,periphBar2Goal, Tile.RED);
        periphBar3DataStage = new BarChartItem("1646/1651",periphBar3Total,periphBar3Goal, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        retailBar1Stage = new BarChartItem("770X",retailBar1Total, retailBar1Goal, Tile.BLUE);
        nextGenDisplaysStage = new BarChartItem("5968/5985", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.RED);
        //---------------------------------Creating the Tiles-----------------------------------------------------------
        posStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 540)
                .barChartItems(posBar1DataStage, p1x30DataStage, t1000DataStage, questDataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posStagePercent = TileBuilder.create()
                .prefSize(480, 270)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .subText(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .value(posPercentTotalStage)
                .build();
        posTotal = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(480, 270)
                .title("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(posTotalCurrentStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(posStage,0,0,1,2);
        pane.add(posStagePercent,0,2,1,1);
        pane.add(posTotal,0,3,1,1);

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retailStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .title("Retail Stage")
                .prefSize(480, 540)
                .barChartItems(retailBar1Stage, nextGenDisplaysStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailStagePercent= TileBuilder.create()
                .prefSize(480, 270)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .subText(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(retailPercentTotalStage)
                .build();
        retailTotal = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(480, 270)
                .title("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(retailTotalCurrentStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(retailStage,1,0,1,2);
        pane.add(retailStagePercent,1,2,1,1);
        pane.add(retailTotal,1,3,1,1);
        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 540)
                .barChartItems(serverBar1DataStage,serverBar2DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversStagePercent = TileBuilder.create()
                .prefSize(480, 270)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .subText(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .value(serversPercentTotalStage)
                .build();
        serversTotal = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(480, 270)
                .title("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(serverCurrentStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(serversStage,2,0,1,2);
        pane.add(serversStagePercent,2,2,1,1);
        pane.add(serversTotal,2,3,1,1);
        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        periphStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 540)
                .barChartItems(periphBar1DataStage,periphBar2DataStage,periphBar3DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphStagePercent = TileBuilder.create()
                .prefSize(480, 270)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .subText(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .value(periphPercentTotalStage)
                .build();
        periphTotal = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(480, 270)
                .title("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(periphCurrentTotalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

       pane.add(periphStage,3,0,1,2);
       pane.add(periphStagePercent,3,2,1,1);
       pane.add(periphTotal,3,3,1,1);

       tiles.add(posStage);
       tiles.add(serversStage);
       tiles.add(retailStage);
       tiles.add(periphStage);

       refresh();
       createActions();
       tilesListeners(tiles);
    }

    public void refresh()
    {
        Platform.runLater(() ->
        {

            posBar1DataStage.setValue(posBar1Total);
            posBar1DataStage.setMaxValue(posBar1Goal);
            p1x30DataStage.setValue(p1x30CurrentStage);
            p1x30DataStage.setMaxValue(p1x30GoalStage);
            t1000DataStage.setValue(t1000sCurrentStage);
            t1000DataStage.setMaxValue(t1000sGoalStage);
            questDataStage.setValue(questsCurrentStage);
            questDataStage.setMaxValue(questsGoalStage);

            //---------------------------------Update the Server Units------------------------------------------
            serverBar1DataStage.setValue(serverBar1Total);
            serverBar1DataStage.setMaxValue(serverBar1Goal);
            serverBar2DataStage.setValue(serverBar2Total);
            serverBar2DataStage.setMaxValue(serverBar2Goal);

            //---------------------------------Updating the Peripheral Units------------------------------------
            periphBar1DataStage.setValue(periphBar1Total);
            periphBar1DataStage.setMaxValue(periphBar1Goal);
            periphBar2DataStage.setValue(periphBar2Total);
            periphBar2DataStage.setMaxValue(periphBar2Goal);
            periphBar3DataStage.setValue(periphBar3Total);
            periphBar3DataStage.setMaxValue(periphBar3Goal);
            //---------------------------------Updating the Retail Units----------------------------------------
            retailBar1Stage.setValue(retailBar1Total);
            retailBar1Stage.setMaxValue(retailBar1Goal);
            nextGenDisplaysStage.setValue(nextGenDisplayCurrentStage);
            nextGenDisplaysStage.setMaxValue(nextGenDisplayGoalsStage);
            //---------------------------------Creating Color Changes for POS Dial------------------------------------------
            posStagePercent.setValue(posPercentTotalStage);
            posTotal.setDescription(Double.toString(posTotalCurrentStage));

            changePercent(posStagePercent, posTotalCurrentStage, posTotalGoalStage, posPercentTotalStage);

            //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
            serversStagePercent.setValue(serversPercentTotalStage);
            serversTotal.setDescription(Double.toString(serverCurrentStage));

            changePercent(serversStagePercent, serverCurrentStage, serverGoalTotalStage, serversPercentTotalStage);


            periphStagePercent.setValue(periphPercentTotalStage);
            periphTotal.setDescription(Double.toString(periphCurrentTotalStage));


            changePercent(periphStagePercent, periphCurrentTotalStage, periphGoalTotalStage, periphPercentTotalStage);

            //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
            retailStagePercent.setValue(retailPercentTotalStage);
            retailTotal.setDescription(Double.toString(retailTotalCurrentStage));

            changePercent(retailStagePercent, retailTotalCurrentStage, retailTotalGoalStage, retailPercentTotalStage);

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

    public void screenMove(Stage primaryStage, Bounds allScreenBounds, ArrayList<Screen> screens)
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

    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                if(event.getCode() == KeyCode.LEFT)
                {
                    MainTestController buildController = messenger.getMainTestController();

                    FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
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
                if(event.getCode() == KeyCode.F4)
                {
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
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
            }
        });
       posStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               POSStageController buildController = messenger.getPosStageController();

               FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/posStageScreen.fxml"));
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
        retailStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RetailStageController buildController = messenger.getRetailStageController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/retailStageScreen.fxml"));
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
        serversStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                POSStageController buildController = messenger.getPosStageController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/posStageScreen.fxml"));
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
        periphStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PeriphStageController buildController = messenger.getPeriphStageController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/periphStageScreen.fxml"));
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
    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
    public double getP1x30CurrentStage() {
        return p1x30CurrentStage;
    }

    public void setP1x30CurrentStage(double p1x30CurrentStage) {
        this.p1x30CurrentStage = p1x30CurrentStage;
    }

    public double getP1x30GoalStage() {
        return p1x30GoalStage;
    }

    public void setP1x30GoalStage(double p1x30GoalStage) {
        this.p1x30GoalStage = p1x30GoalStage;
    }

    public double getP1x35CurrentStage() {
        return p1x35CurrentStage;
    }

    public void setP1x35CurrentStage(double p1x35CurrentStage) {
        this.p1x35CurrentStage = p1x35CurrentStage;
    }

    public double getP1x35GoalStage() {
        return p1x35GoalStage;
    }

    public void setP1x35GoalStage(double p1x35GoalStage) {
        this.p1x35GoalStage = p1x35GoalStage;
    }

    public double getP1532CurrentStage() {
        return p1532CurrentStage;
    }

    public void setP1532CurrentStage(double p1532CurrentStage) {
        this.p1532CurrentStage = p1532CurrentStage;
    }

    public double getP1532GoalStage() {
        return p1532GoalStage;
    }

    public void setP1532GoalStage(double p1532GoalStage) {
        this.p1532GoalStage = p1532GoalStage;
    }

    public double getT1000sCurrentStage() {
        return t1000sCurrentStage;
    }

    public void setT1000sCurrentStage(double t1000sCurrentStage) {
        this.t1000sCurrentStage = t1000sCurrentStage;
    }

    public double getT1000sGoalStage() {
        return t1000sGoalStage;
    }

    public void setT1000sGoalStage(double t1000sGoalStage) {
        this.t1000sGoalStage = t1000sGoalStage;
    }

    public double getQuestsCurrentStage() {
        return questsCurrentStage;
    }

    public void setQuestsCurrentStage(double questsCurrentStage) {
        this.questsCurrentStage = questsCurrentStage;
    }

    public double getQuestsGoalStage() {
        return questsGoalStage;
    }

    public void setQuestsGoalStage(double questsGoalStage) {
        this.questsGoalStage = questsGoalStage;
    }

    public double getPosTotalGoalStage() {
        return posTotalGoalStage;
    }

    public void setPosTotalGoalStage(double posTotalGoalStage) {
        this.posTotalGoalStage = posTotalGoalStage;
    }

    public double getPosTotalCurrentStage() {
        return posTotalCurrentStage;
    }

    public void setPosTotalCurrentStage(double posTotalCurrentStage) {
        this.posTotalCurrentStage = posTotalCurrentStage;
    }

    public double getXr7CurrentStage() {
        return xr7CurrentStage;
    }

    public void setXr7CurrentStage(double xr7CurrentStage) {
        this.xr7CurrentStage = xr7CurrentStage;
    }

    public double getXr7GoalStage() {
        return xr7GoalStage;
    }

    public void setXr7GoalStage(double xr7GoalStage) {
        this.xr7GoalStage = xr7GoalStage;
    }

    public double getXr7PlusCurrentStage() {
        return xr7PlusCurrentStage;
    }

    public void setXr7PlusCurrentStage(double xr7PlusCurrentStage) {
        this.xr7PlusCurrentStage = xr7PlusCurrentStage;
    }

    public double getXr7PlusGoalStage() {
        return xr7PlusGoalStage;
    }

    public void setXr7PlusGoalStage(double xr7PlusGoalStage) {
        this.xr7PlusGoalStage = xr7PlusGoalStage;
    }

    public double getXr5CurrentStage() {
        return xr5CurrentStage;
    }

    public void setXr5CurrentStage(double xr5CurrentStage) {
        this.xr5CurrentStage = xr5CurrentStage;
    }

    public double getXr5GoalStage() {
        return xr5GoalStage;
    }

    public void setXr5GoalStage(double xr5GoalStage) {
        this.xr5GoalStage = xr5GoalStage;
    }

    public double getNextGenDisplayCurrentStage() {
        return nextGenDisplayCurrentStage;
    }

    public void setNextGenDisplayCurrentStage(double nextGenDisplayCurrentStage) {
        this.nextGenDisplayCurrentStage = nextGenDisplayCurrentStage;
    }

    public double getNextGenDisplayGoalsStage() {
        return nextGenDisplayGoalsStage;
    }

    public void setNextGenDisplayGoalsStage(double nextGenDisplayGoalsStage) {
        this.nextGenDisplayGoalsStage = nextGenDisplayGoalsStage;
    }

    public double getRetailTotalGoalStage() {
        return retailTotalGoalStage;
    }

    public void setRetailTotalGoalStage(double retailTotalGoalStage) {
        this.retailTotalGoalStage = retailTotalGoalStage;
    }

    public double getRetailTotalCurrentStage() {
        return retailTotalCurrentStage;
    }

    public void setRetailTotalCurrentStage(double retailTotalCurrentStage) {
        this.retailTotalCurrentStage = retailTotalCurrentStage;
    }

    public double getMediaPlayerCurrentStage() {
        return mediaPlayerCurrentStage;
    }

    public void setMediaPlayerCurrentStage(double mediaPlayerCurrentStage) {
        this.mediaPlayerCurrentStage = mediaPlayerCurrentStage;
    }

    public double getMediaPlayerGoalStage() {
        return mediaPlayerGoalStage;
    }

    public void setMediaPlayerGoalStage(double mediaPlayerGoalStage) {
        this.mediaPlayerGoalStage = mediaPlayerGoalStage;
    }

    public double getN3000CurrentStage() {
        return n3000CurrentStage;
    }

    public void setN3000CurrentStage(double n3000CurrentStage) {
        this.n3000CurrentStage = n3000CurrentStage;
    }

    public double getN3000GoalStage() {
        return n3000GoalStage;
    }

    public void setN3000GoalStage(double n3000GoalStage) {
        this.n3000GoalStage = n3000GoalStage;
    }

    public double getS500CurrentStage() {
        return s500CurrentStage;
    }

    public void setS500CurrentStage(double s500CurrentStage) {
        this.s500CurrentStage = s500CurrentStage;
    }

    public double getS500GoalStage() {
        return s500GoalStage;
    }

    public void setS500GoalStage(double s500GoalStage) {
        this.s500GoalStage = s500GoalStage;
    }

    public double getServerGoalTotalStage() {
        return serverGoalTotalStage;
    }

    public void setServerGoalTotalStage(double serverGoalTotalStage) {
        this.serverGoalTotalStage = serverGoalTotalStage;
    }

    public double getServerCurrentStage() {
        return serverCurrentStage;
    }

    public void setServerCurrentStage(double serverCurrentStage) {
        this.serverCurrentStage = serverCurrentStage;
    }

    public double getKiwi4sCurrentStage() {
        return kiwi4sCurrentStage;
    }

    public void setKiwi4sCurrentStage(double kiwi4sCurrentStage) {
        this.kiwi4sCurrentStage = kiwi4sCurrentStage;
    }

    public double getKiwi4sGoalStage() {
        return kiwi4sGoalStage;
    }

    public void setKiwi4sGoalStage(double kiwi4sGoalStage) {
        this.kiwi4sGoalStage = kiwi4sGoalStage;
    }

    public double getKiwi2XsCurrentStage() {
        return kiwi2XsCurrentStage;
    }

    public void setKiwi2XsCurrentStage(double kiwi2XsCurrentStage) {
        this.kiwi2XsCurrentStage = kiwi2XsCurrentStage;
    }

    public double getKiwi2XsGoalStage() {
        return kiwi2XsGoalStage;
    }

    public void setKiwi2XsGoalStage(double kiwi2XsGoalStage) {
        this.kiwi2XsGoalStage = kiwi2XsGoalStage;
    }

    public double getBumpBarsCurrentStage() {
        return bumpBarsCurrentStage;
    }

    public void setBumpBarsCurrentStage(double bumpBarsCurrentStage) {
        this.bumpBarsCurrentStage = bumpBarsCurrentStage;
    }

    public double getBumpBarsGoalStage() {
        return bumpBarsGoalStage;
    }

    public void setBumpBarsGoalStage(double bumpBarsGoalStage) {
        this.bumpBarsGoalStage = bumpBarsGoalStage;
    }

    public double getPantherEPC4sCurrentStage() {
        return pantherEPC4sCurrentStage;
    }

    public void setPantherEPC4sCurrentStage(double pantherEPC4sCurrentStage) {
        this.pantherEPC4sCurrentStage = pantherEPC4sCurrentStage;
    }

    public double getPantherEPC4sGoalStage() {
        return pantherEPC4sGoalStage;
    }

    public void setPantherEPC4sGoalStage(double pantherEPC4sGoalStage) {
        this.pantherEPC4sGoalStage = pantherEPC4sGoalStage;
    }

    public double getPeriphGoalTotalStage() {
        return periphGoalTotalStage;
    }

    public void setPeriphGoalTotalStage(double periphGoalTotalStage) {
        this.periphGoalTotalStage = periphGoalTotalStage;
    }

    public double getPeriphCurrentTotalStage() {
        return periphCurrentTotalStage;
    }

    public void setPeriphCurrentTotalStage(double periphCurrentTotalStage) {
        this.periphCurrentTotalStage = periphCurrentTotalStage;
    }

    public double getPosPercentTotalStage() {
        return posPercentTotalStage;
    }

    public void setPosPercentTotalStage(double posPercentTotalStage) {
        this.posPercentTotalStage = posPercentTotalStage;
    }

    public double getRetailPercentTotalStage() {
        return retailPercentTotalStage;
    }

    public void setRetailPercentTotalStage(double retailPercentTotalStage) {
        this.retailPercentTotalStage = retailPercentTotalStage;
    }

    public double getServersPercentTotalStage() {
        return serversPercentTotalStage;
    }

    public void setServersPercentTotalStage(double serversPercentTotalStage) {
        this.serversPercentTotalStage = serversPercentTotalStage;
    }

    public double getPeriphPercentTotalStage() {
        return periphPercentTotalStage;
    }

    public void setPeriphPercentTotalStage(double periphPercentTotalStage) {
        this.periphPercentTotalStage = periphPercentTotalStage;
    }
    public double getPosBar1Total() {
        return posBar1Total;
    }

    public void setPosBar1Total(double posBar1Total) {
        this.posBar1Total = posBar1Total;
    }

    public double getPosBar1Goal() {
        return posBar1Goal;
    }

    public void setPosBar1Goal(double posBar1Goal) {
        this.posBar1Goal = posBar1Goal;
    }
    public double getRetailBar1Total() {
        return retailBar1Total;
    }

    public void setRetailBar1Total(double retailBar1Total) {
        this.retailBar1Total = retailBar1Total;
    }

    public double getRetailBar1Goal() {
        return retailBar1Goal;
    }

    public void setRetailBar1Goal(double retailBar1Goal) {
        this.retailBar1Goal = retailBar1Goal;
    }
    public double getServerBar1Total() {
        return serverBar1Total;
    }

    public void setServerBar1Total(double serverBar1Total) {
        this.serverBar1Total = serverBar1Total;
    }

    public double getServerBar1Goal() {
        return serverBar1Goal;
    }

    public void setServerBar1Goal(double serverBar1Goal) {
        this.serverBar1Goal = serverBar1Goal;
    }

    public double getServerBar2Total() {
        return serverBar2Total;
    }

    public void setServerBar2Total(double serverBar2Total) {
        this.serverBar2Total = serverBar2Total;
    }

    public double getServerBar2Goal() {
        return serverBar2Goal;
    }

    public void setServerBar2Goal(double serverBar2Goal) {
        this.serverBar2Goal = serverBar2Goal;
    }

    public double getPeriphBar1Total() {
        return periphBar1Total;
    }

    public void setPeriphBar1Total(double periphBar1Total) {
        this.periphBar1Total = periphBar1Total;
    }

    public double getPeriphBar1Goal() {
        return periphBar1Goal;
    }

    public void setPeriphBar1Goal(double periphBar1Goal) {
        this.periphBar1Goal = periphBar1Goal;
    }

    public double getPeriphBar2Total() {
        return periphBar2Total;
    }

    public void setPeriphBar2Total(double periphBar2Total) {
        this.periphBar2Total = periphBar2Total;
    }

    public double getPeriphBar2Goal() {
        return periphBar2Goal;
    }

    public void setPeriphBar2Goal(double periphBar2Goal) {
        this.periphBar2Goal = periphBar2Goal;
    }

    public double getPeriphBar3Total() {
        return periphBar3Total;
    }

    public void setPeriphBar3Total(double periphBar3Total) {
        this.periphBar3Total = periphBar3Total;
    }

    public double getPeriphBar3Goal() {
        return periphBar3Goal;
    }

    public void setPeriphBar3Goal(double periphBar3Goal) {
        this.periphBar3Goal = periphBar3Goal;
    }
}
