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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainStageController extends Controller implements Initializable,Methods
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
    private double p1x30CurrentStage;
    private double p1x30GoalStage;
    private double t1000sCurrentStage;
    private double t1000sGoalStage;
    private double questsCurrentStage;
    private double questsGoalStage;

    private double posBar1Total;
    private double posBar1Goal;

    private double posTotalGoalStage;
    private double posTotalCurrentStage;


     //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    private double nextGenDisplayCurrentStage;
    private double nextGenDisplayGoalsStage;

    private double retailTotalGoalStage;
    private double retailTotalCurrentStage;

     //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    private double serverGoalTotalStage;
    private double serverCurrentStage;

     //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    private double periphGoalTotalStage;
    private double periphCurrentTotalStage;
     //---------------------------------Variables for Percentages --------------------------------------------------
    private double posPercentTotalStage;
    private double retailPercentTotalStage;
    private double serversPercentTotalStage;
    private double periphPercentTotalStage;

     //---------------------------------Creating the Bar Chart Items for Hosp-------------------------------------
    private BarChartItem posBar1DataStage;
    private BarChartItem p1x30DataStage;
    private BarChartItem t1000DataStage;
    private BarChartItem questDataStage;
     //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    private BarChartItem serverBar1DataStage;
    private BarChartItem serverBar2DataStage;
     //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
    private BarChartItem periphBar1DataStage;
    private BarChartItem periphBar2DataStage;
    private BarChartItem periphBar3DataStage;
     //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    private BarChartItem nextGenDisplaysStage;
    private BarChartItem retailBar1Stage;

    private double retailBar1Total;
    private double retailBar1Goal;

    private double serverBar1Total;
    private double serverBar1Goal;

    private double serverBar2Total;
    private double serverBar2Goal;

    private double periphBar1Total;
    private double periphBar1Goal;

    private double periphBar2Total;
    private double periphBar2Goal;

    private double periphBar3Total;
    private double periphBar3Goal;

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    private ArrayList<Tile> tiles;
    private Messenger messenger;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tiles = new ArrayList<>();

        posBar1DataStage = new BarChartItem("7734/7745/7761", posBar1Total, posBar1Goal, Tile.BLUE);
        p1x30DataStage = new BarChartItem("7743", p1x30CurrentStage, p1x30GoalStage, Tile.ORANGE);
        t1000DataStage = new BarChartItem("7744", t1000sCurrentStage, t1000sGoalStage, Tile.GREEN);
        questDataStage = new BarChartItem("7791/7792", questsCurrentStage,questsGoalStage, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        serverBar1DataStage = new BarChartItem("1611/1612", serverBar1Total,serverBar1Goal, Tile.BLUE);
        serverBar2DataStage = new BarChartItem("1656/1657", serverBar2Total,serverBar2Goal, Tile.ORANGE);
        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        periphBar1DataStage = new BarChartItem("1635",periphBar1Total,periphBar1Goal, Tile.BLUE);
        periphBar2DataStage = new BarChartItem("1642/1924",periphBar2Total,periphBar2Goal, Tile.ORANGE);
        periphBar3DataStage = new BarChartItem("1646/1651",periphBar3Total,periphBar3Goal, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        retailBar1Stage = new BarChartItem("770X",retailBar1Total, retailBar1Goal, Tile.BLUE);
        nextGenDisplaysStage = new BarChartItem("5968/5985", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.ORANGE);
        //---------------------------------Creating the Tiles-----------------------------------------------------------
        posStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(posBar1DataStage, p1x30DataStage, t1000DataStage, questDataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posStagePercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(retailBar1Stage, nextGenDisplaysStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailStagePercent= TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(serverBar1DataStage,serverBar2DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversStagePercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.5))
                .barChartItems(periphBar1DataStage,periphBar2DataStage,periphBar3DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphStagePercent = TileBuilder.create()
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.25), messenger.getResolutionizer().setTileHeight(.25))
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
       tiles.add(posStagePercent);
       tiles.add(retailStagePercent);
       tiles.add(periphStagePercent);
       tiles.add(serversStagePercent);
       tiles.add(posTotal);
       tiles.add(retailTotal);
       tiles.add(periphTotal);
       tiles.add(serversTotal);

       refresh();
       createActions();
       super.tilesListeners(tiles,messenger);
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




    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getNavigationScene());
                }
                if(event.getCode() == KeyCode.LEFT)
                {
                    messenger.getPrimaryStage().setScene(messenger.getMainTest());
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
            }
        });
       posStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event)
           {
               messenger.getPrimaryStage().setScene(messenger.getPosStage());
           }
       });
        retailStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailStage());
            }
        });
        serversStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerStage());
            }
        });
        periphStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphStage());
            }
        });
        posStagePercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosStage());
            }
        });
        retailStagePercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailStage());
            }
        });
        serversStagePercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerStage());
            }
        });
        periphStagePercent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphStage());
            }
        });
        posTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPosStage());
            }
        });
        retailTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getRetailStage());
            }
        });
        serversTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getServerStage());
            }
        });
        periphTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getPeriphStage());
            }
        });
    }


    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public void setUseDate(String useDate) {

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

    public void setPosPercentTotalStage(double posPercentTotalStage) {
        this.posPercentTotalStage = posPercentTotalStage;
    }
    public void setRetailPercentTotalStage(double retailPercentTotalStage) {
        this.retailPercentTotalStage = retailPercentTotalStage;
    }

    public void setServersPercentTotalStage(double serversPercentTotalStage) {
        this.serversPercentTotalStage = serversPercentTotalStage;
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
