package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.Messenger;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
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

    BarChartItem p1x35DataStage;
    BarChartItem p1532DataStage;
    BarChartItem p1x30DataStage;
    BarChartItem t1000DataStage;
    //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
    BarChartItem n3000Stage;
    BarChartItem s500DataStage;
    BarChartItem mediaPlayerStage;
    //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
    BarChartItem kiwi4DataStage;
    BarChartItem kiwi25DataStage;
    BarChartItem bumpBarDataStage;
    BarChartItem pantherEPC4Stage;
    //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
    BarChartItem xr5DataStage;
    BarChartItem xr7DataStage;
    BarChartItem xr7PlusDataStage;
    BarChartItem nextGenDisplaysStage;

    Messenger messenger;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        p1x35DataStage = new BarChartItem("P1X35", p1x35CurrentStage, p1x35GoalStage, Tile.RED);
        p1532DataStage = new BarChartItem("P1532", p1532CurrentStage, p1532GoalStage, Tile.GREEN);
        p1x30DataStage = new BarChartItem("P1X30", p1x30CurrentStage, p1x30GoalStage, Tile.BLUE);
        t1000DataStage = new BarChartItem("T1000", t1000sCurrentStage, t1000sGoalStage, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        n3000Stage = new BarChartItem("N3000", n3000CurrentStage, n3000GoalStage, Tile.RED);
        s500DataStage = new BarChartItem("S500", s500CurrentStage, s500GoalStage, Tile.BLUE);
        mediaPlayerStage = new BarChartItem("Media Player", mediaPlayerCurrentStage, mediaPlayerGoalStage, Tile.GREEN);
        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        kiwi4DataStage = new BarChartItem("Kiwi 4", kiwi4sCurrentStage, kiwi4sGoalStage, Tile.BLUE);
        kiwi25DataStage = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentStage, kiwi2XsGoalStage, Tile.RED);
        bumpBarDataStage = new BarChartItem("Bumpbar", bumpBarsCurrentStage, bumpBarsGoalStage, Tile.GREEN);
        pantherEPC4Stage = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentStage, pantherEPC4sGoalStage, Tile.YELLOW);
        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        xr5DataStage = new BarChartItem("7701", xr5CurrentStage, xr5GoalStage, Tile.BLUE);
        xr7DataStage = new BarChartItem("7702", xr7CurrentStage, xr7GoalStage, Tile.RED);
        xr7PlusDataStage = new BarChartItem("7703", xr7PlusCurrentStage, xr7PlusGoalStage, Tile.GREEN);
        nextGenDisplaysStage = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.YELLOW);
        //---------------------------------Creating the Tiles-----------------------------------------------------------
        posStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 640)
                .barChartItems(p1x30DataStage, p1x35DataStage, p1532DataStage, t1000DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posStagePercent = TileBuilder.create()
                .prefSize(480, 440)
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
                .prefSize(384, 270)
                .title("Total Built")
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(posStage,0,0,1,2);
        pane.add(posStagePercent,0,2,1,1);

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retailStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .title("Retail Stage")
                .prefSize(480, 640)
                .barChartItems(xr5DataStage, xr7DataStage, xr7PlusDataStage, nextGenDisplaysStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailStagePercent= TileBuilder.create()
                .prefSize(480, 440)
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
                .prefSize(384, 270)
                .subText("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(retailTotalCurrentStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(retailStage,1,0,1,2);
        pane.add(retailStagePercent,1,2,1,1);
        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        serversStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 200)
                .barChartItems(s500DataStage, n3000Stage, mediaPlayerStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversStagePercent = TileBuilder.create()
                .prefSize(480, 440)
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
                .prefSize(384, 270)
                .subText("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(serverCurrentStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        pane.add(serversStage,2,0,1,2);
        pane.add(serversStagePercent,2,2,1,1);
        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        periphStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Stage")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .prefSize(480, 640)
                .barChartItems(kiwi4DataStage, kiwi25DataStage, bumpBarDataStage, pantherEPC4Stage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphStagePercent = TileBuilder.create()
                .prefSize(480, 440)
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
                .prefSize(384, 270)
                .subText("Total Staged")
                .titleAlignment(TextAlignment.CENTER)
                .description(Double.toString(periphCurrentTotalStage))
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

       pane.add(periphStage,3,0,1,2);
       pane.add(periphStagePercent,3,2,1,1);

       refresh();
       createActions();
    }

    public void refresh()
    {

        p1x35DataStage.setValue(p1x35CurrentStage);
        p1x35DataStage.setMaxValue(p1x35GoalStage);
        p1532DataStage.setValue(p1532CurrentStage);
        p1532DataStage.setMaxValue(p1532GoalStage);
        p1x30DataStage.setValue(p1x30CurrentStage);
        p1x30DataStage.setMaxValue(p1x30GoalStage);
        t1000DataStage.setValue(t1000sCurrentStage);
        t1000DataStage.setMaxValue(t1000sGoalStage);
        //---------------------------------Update the Server Units------------------------------------------
        n3000Stage.setValue(n3000CurrentStage);
        n3000Stage.setMaxValue(n3000GoalStage);
        s500DataStage.setValue(s500CurrentStage);
        s500DataStage.setMaxValue(s500GoalStage);
        mediaPlayerStage.setValue(mediaPlayerCurrentStage);
        mediaPlayerStage.setMaxValue(mediaPlayerGoalStage);
        //---------------------------------Updating the Peripheral Units------------------------------------
        kiwi4DataStage.setValue(kiwi4sCurrentStage);
        kiwi4DataStage.setMaxValue(kiwi4sGoalStage);
        kiwi25DataStage.setValue(kiwi2XsCurrentStage);
        kiwi25DataStage.setMaxValue(kiwi2XsGoalStage);
        bumpBarDataStage.setValue(bumpBarsCurrentStage);
        bumpBarDataStage.setMaxValue(bumpBarsGoalStage);
        pantherEPC4Stage.setValue(pantherEPC4sCurrentStage);
        pantherEPC4Stage.setMaxValue(pantherEPC4sGoalStage);
        //---------------------------------Updating the Retail Units----------------------------------------
        xr5DataStage.setValue(xr5CurrentStage);
        xr5DataStage.setMaxValue(xr5GoalStage);
        xr7DataStage.setValue(xr7CurrentStage);
        xr7DataStage.setMaxValue(xr7GoalStage);
        xr7PlusDataStage.setValue(xr7PlusCurrentStage);
        xr7PlusDataStage.setMaxValue(xr7PlusGoalStage);
        nextGenDisplaysStage.setValue(nextGenDisplayCurrentStage);
        nextGenDisplaysStage.setMaxValue(nextGenDisplayGoalsStage);
        //---------------------------------Creating Color Changes for POS Dial------------------------------------------
        posStagePercent.setValue(posPercentTotalStage);

        changePercent(posStagePercent,posTotalCurrentStage, posTotalGoalStage, posPercentTotalStage);

        //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
        serversStagePercent.setValue(serversPercentTotalStage);

        changePercent(serversStagePercent, serverCurrentStage, serverGoalTotalStage, serversPercentTotalStage);


        periphStagePercent.setValue(periphPercentTotalStage);


        changePercent(periphStagePercent, periphCurrentTotalStage, periphGoalTotalStage, periphPercentTotalStage);

        //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
        retailStagePercent.setValue(retailPercentTotalStage);

        changePercent(retailStagePercent, retailTotalCurrentStage, retailTotalGoalStage, retailPercentTotalStage);
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

           }
       });
        retailStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        serversStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        periphStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
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
}
