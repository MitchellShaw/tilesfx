import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.MapTool;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import static java.time.temporal.ChronoUnit.DAYS;


/**
 * Created by Mitchell Shaw
 * Framework by hansolo
 * 2017-11-22.
 */
public class GoodVersionofMain extends Application {
    //------------------------------------Variables Block---------------------------------------------------------------
    Tile pos;
    Tile servers;
    Tile peripherals;
    Tile optic;
    Tile retail;
    Tile posPercent;
    Tile serversPercent;
    Tile periphPercent;
    Tile opticPercent;
    Tile retailPercent;

    Tile posTest;
    Tile serversTest;
    Tile peripheralsTest;
    Tile opticTest;
    Tile retailTest;
    Tile posPercentTest;
    Tile serversPercentTest;
    Tile periphPercentTest;
    Tile opticPercentTest;
    Tile retailPercentTest;

    Tile posStage;
    Tile serversStage;
    Tile peripheralsStage;
    Tile retailStage;
    Tile posPercentStage;
    Tile serversPercentStage;
    Tile periphPercentStage;
    Tile retailPercentStage;

    Tile posBuildFocused;
    Tile posTestFocused;
    Tile serversBuildFocused;
    Tile serversTestFocused;
    Tile periphBuildFocused;
    Tile periphTestFocused;
    Tile retailBuildFocused;
    Tile retailTestFocused;
    Tile opticBuildFocused;
    Tile opticTestFocused;

    Tile posPercentFocused;
    Tile serversPercentFocused;
    Tile periphPercentFocused;
    Tile opticPercentFocused;
    Tile retailPercentFocused;

    Tile posPercentTestFocused;
    Tile serversPercentTestFocused;
    Tile periphPercentTestFocused;
    Tile opticPercentTestFocused;
    Tile retailPercentTestFocused;

    Tile posStageFocused;
    Tile serversStageFocused;
    Tile periphStageFocused;
    Tile retailStageFocused;
    Tile serversPercentStageFocused;
    Tile periphPercentStageFocused;
    Tile retailPercentStageFocused;
    Tile posPercentStageFocused;


    boolean firstTime;

    double x = 0;
    double y = 0;

    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    double p1x30CurrentBuild;
    double p1x30GoalBuild;
    double p1x35CurrentBuild;
    double p1x35GoalBuild;
    double p1532CurrentBuild;
    double p1532GoalBuild;
    double t1000sCurrentBuild;
    double t1000sGoalBuild;


    double p1x30CurrentTest;
    double p1x35CurrentTest;
    double p1532CurrentTest;
    double t1000sCurrentTest;
    double questsCurrentTest;


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

    double posTotalGoalBuild;
    double posTotalGoalStage;

    double posTotalCurrentBuild;
    double posTotalCurrentTest;
    double posTotalCurrentStage;


    //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    double xr7CurrentBuild;
    double xr7GoalBuild;
    double xr7PlusCurrentBuild;
    double xr7PlusGoalBuild;
    double xr5CurrentBuild;
    double xr5GoalBuild;
    double nextGenDisplayCurrentBuild;
    double nextGenDisplayGoalsBuild;

    double xr7CurrentTest;
    double xr7PlusCurrentTest;
    double xr5CurrentTest;
    double nextGenDisplayCurrentTest;

    double xr7CurrentStage;
    double xr7GoalStage;
    double xr7PlusCurrentStage;
    double xr7PlusGoalStage;
    double xr5CurrentStage;
    double xr5GoalStage;
    double nextGenDisplayCurrentStage;
    double nextGenDisplayGoalsStage;

    double retailTotalGoalBuild;
    double retailTotalGoalStage;

    double retailTotalCurrentBuild;
    double retailTotalCurrentTest;
    double retailTotalCurrentStage;

    //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    double mediaPlayerCurrentBuild;
    double mediaPlayerGoalBuild;
    double n3000CurrentBuild;
    double n3000GoalBuild;
    double s500CurrentBuild;
    double s500GoalBuild;


    double mediaPlayerCurrentTest;
    double n3000CurrentTest;
    double s500CurrentTest;


    double mediaPlayerCurrentStage;
    double mediaPlayerGoalStage;
    double n3000CurrentStage;
    double n3000GoalStage;
    double s500CurrentStage;
    double s500GoalStage;

    double serverGoalTotalBuild;
    double serverGoalTotalStage;

    double serverCurrentBuild;
    double serverCurrentTest;
    double serverCurrentStage;

    //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    double kiwi4sCurrentBuild;
    double kiwi4sGoalBuild;
    double kiwi2XsCurrentBuild;
    double kiwi2XsGoalBuild;
    double bumpBarsCurrentBuild;
    double bumpBarsGoalBuild;
    double pantherEPC4sCurrentBuild;
    double pantherEPC4sGoalBuild;

    double kiwi4sCurrentTest;
    double kiwi2XsCurrentTest;
    double bumpBarsCurrentTest;
    double pantherEPC4sCurrentTest;

    double kiwi4sCurrentStage;
    double kiwi4sGoalStage;
    double kiwi2XsCurrentStage;
    double kiwi2XsGoalStage;
    double bumpBarsCurrentStage;
    double bumpBarsGoalStage;
    double pantherEPC4sCurrentStage;
    double pantherEPC4sGoalStage;

    double periphGoalTotalBuild;
    double periphGoalTotalStage;

    double periphCurrentTotalBuild;
    double periphCurrentTotalTest;
    double periphCurrentTotalStage;

    //---------------------------------Variables for Query Data (Optic)--------------------------------------------
    double optic5sCurrentBuild;
    double optic5sGoalBuild;
    double optic12sCurrentBuild;
    double optic12sGoalBuild;
    double cubCurrentBuild;
    double cubGoalBuild;
    double kitsCurrentBuild;
    double kitsGoalBuild;
    double printerCurrentBuild;
    double printerGoalBuild;

    double optic5sCurrentTest;
    double optic12sCurrentTest;
    double cubCurrentTest;
    double cubGoalTest;
    double kitsCurrentTest;
    double kitsGoalTest;
    double printerCurrentTest;
    double printerGoalTest;

    double opticGoalTotalBuild;
    double opticGoalTotalStage;

    double opticCurrentTotalBuild;
    double opticCurrentTotalTest;

    //---------------------------------Variables for Percentages --------------------------------------------------
    GoalTool goalTool;
    boolean flag;

    double posPercentTotalBuild;
    double retailPercentTotalBuild;
    double opticPercentTotalBuild;
    double serversPercentTotalBuild;
    double periphPercentTotalBuild;

    double posPercentTotalTest;
    double retailPercentTotalTest;
    double opticPercentTotalTest;
    double serversPercentTotalTest;
    double periphPercentTotalTest;

    double posPercentTotalStage;
    double retailPercentTotalStage;
    double serversPercentTotalStage;
    double periphPercentTotalStage;


    //---------------------------------Variables for Map Creation for POS Database Call----------------------------
    HashMap<String, Integer> posBuildMap;
    HashMap<String, Integer> posTestMap;
    HashMap<String, Integer> posStageMap;
    HashMap<String, Integer> posUserStageMap;

    //---------------------------------Variables for Map Creation for Retail Database Call-------------------------
    HashMap<String, Integer> retailBuildMap;
    HashMap<String, Integer> retailTestMap;
    HashMap<String, Integer> retailStageMap;
    HashMap<String, Integer> retailUserStageMap;

    //---------------------------------Variables for Map Creation for Servers Database Call------------------------
    HashMap<String, Integer> serversBuildMap;
    HashMap<String, Integer> serversTestMap;
    HashMap<String, Integer> serversStageMap;
    HashMap<String, Integer> serversUserStageMap;

    //---------------------------------Variables for Map Creation for Peripherals Database Call--------------------
    HashMap<String, Integer> periphBuildMap;
    HashMap<String, Integer> periphTestMap;
    HashMap<String, Integer> periphStageMap;
    HashMap<String, Integer> periphUserStageMap;

    //---------------------------------Variables for Map Creation for Optic Database Call----------------------------
    HashMap<String, Integer> opticBuildMap;
    HashMap<String, Integer> opticTestMap;

    //---------------------------------Variables for Map Creation for Document Reader---------------------------------
    ArrayList<HashMap<String, Integer>> mapList;
    ArrayList<HashMap<String, Integer>> stageMapList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //---------------------------------Stage Handlers and Show------------------------------------------------------

//        Stage tempStage = new Stage();
//        FXMLLoader root = new FXMLLoader(getClass().getResource("LoadingScreen.fxml"));
//
//        eu.hansolo.tilesfx.Controllers.LoadingController loadingController = new eu.hansolo.tilesfx.Controllers.LoadingController();
//        root.setController(loadingController);
//
//        GridPane loadPane = root.load();
//        Scene loadScene = new Scene(loadPane, 1920, 1080);
//
//        tempStage.initStyle(StageStyle.UNDECORATED);
//        tempStage.setScene(loadScene);
        //---------------------------------Special Tiles----------------------------------------------------------------
        final CountDownLatch myLatch = new CountDownLatch(1);

        //---------------------------------Variables for Tiles----------------------------------------------------------
        FlowPane flowPane = new FlowPane();
        FlowPane flowPaneTest = new FlowPane();
        FlowPane flowPaneStage = new FlowPane();
        FlowPane flowPanePOSBuildOrTestFocus = new FlowPane();
        FlowPane flowPaneRetailBuildOrTestFocus = new FlowPane();
        FlowPane flowPaneServersBuildOrTestFocus = new FlowPane();
        FlowPane flowPanePeriphBuildOrTestFocus = new FlowPane();
        FlowPane flowPaneOpticBuildOrTestFocus = new FlowPane();
        FlowPane flowPanePOSStageFocus = new FlowPane();
        FlowPane flowPaneRetailStageFocus = new FlowPane();
        FlowPane flowPaneServersStageFocus = new FlowPane();
        FlowPane flowPanePeriphStageFocus = new FlowPane();
        FlowGridPane posGridPane = new FlowGridPane(4,5);
        FlowGridPane retailGridPane = new FlowGridPane(4,5);
        FlowGridPane serversGridPane = new FlowGridPane(4,5);
        FlowGridPane periphGridPane = new FlowGridPane(4,5);

        goalTool = new GoalTool();

        //---------------------------------Creating the Tools for the graphs--------------------------------------------
        Tool dataBaseTool = new Tool();
        MapTool mapTool = new MapTool();

        //---------------------------------List Storage Area for Running Group Values ----------------------------------
        ArrayList<String> p1x35ProdList = new ArrayList<>();
        p1x35ProdList.add("7745");
        p1x35ProdList.add("7761");

        ArrayList<String> kiwi2XsProdList = new ArrayList<>();
        kiwi2XsProdList.add("1640");
        kiwi2XsProdList.add("1642");

        ArrayList<String> pantherEPC4sProdList = new ArrayList<>();
        pantherEPC4sProdList.add("1646");
        pantherEPC4sProdList.add("1651");

        ArrayList<String> nextGenProdList = new ArrayList<>();
        nextGenProdList.add("5968");
        nextGenProdList.add("5985");

        ArrayList<String> s500ProdList = new ArrayList<>();
        s500ProdList.add("1611");
        s500ProdList.add("1612");

        //---------------------------------Creating the Bar Chart Items Creation----------------------------------------
        BarChartItem p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataFocused = new BarChartItem("P1X35", p1x35CurrentBuild, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataTest = new BarChartItem("P1X35", p1x35CurrentTest, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataTestFocused = new BarChartItem("P1X35", p1x35CurrentTest, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataStage = new BarChartItem("P1X35", p1x35CurrentStage, p1x35GoalStage, Tile.RED);
        BarChartItem p1x35DataStageFocused = new BarChartItem("P1X35", p1x35CurrentStage, p1x35GoalStage, Tile.RED);


        BarChartItem p1532Data = new BarChartItem("P1532", p1532CurrentBuild, p1532GoalBuild, Tile.GREEN);
        BarChartItem p1532DataFocused = new BarChartItem("P1532", p1532CurrentBuild, p1532GoalBuild, Tile.GREEN);
        BarChartItem p1532DataTest = new BarChartItem("P1532", p1532CurrentTest, p1532GoalBuild, Tile.GREEN);
        BarChartItem p1532DataTestFocused = new BarChartItem("P1532", p1532CurrentTest, p1532GoalBuild, Tile.GREEN);
        BarChartItem p1532DataStage = new BarChartItem("P1532", p1532CurrentStage, p1532GoalStage, Tile.GREEN);
        BarChartItem p1532DataStageFocused = new BarChartItem("P1532", p1532CurrentStage, p1532GoalStage, Tile.GREEN);

        BarChartItem p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataFocused = new BarChartItem("P1X30", p1x30CurrentBuild, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataTest = new BarChartItem("P1X30", p1x30CurrentTest, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataTestFocused = new BarChartItem("P1X30", p1x30CurrentTest, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataStage = new BarChartItem("P1X30", p1x30CurrentStage, p1x30GoalStage, Tile.BLUE);
        BarChartItem p1x30DataStageFocused = new BarChartItem("P1X30", p1x30CurrentStage, p1x30GoalStage, Tile.BLUE);


        BarChartItem t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataFocused = new BarChartItem("T1000", t1000sCurrentBuild, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataTest = new BarChartItem("T1000", t1000sCurrentTest, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataTestFocused = new BarChartItem("T1000", t1000sCurrentTest, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataStage = new BarChartItem("T1000", t1000sCurrentStage, t1000sGoalStage, Tile.YELLOW);
        BarChartItem t1000DataStageFocused = new BarChartItem("T1000", t1000sCurrentStage, t1000sGoalStage, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        BarChartItem n3000Data = new BarChartItem("N3000", n3000CurrentBuild, n3000GoalBuild, Tile.RED);
        BarChartItem n3000DataFocused = new BarChartItem("N3000", n3000CurrentBuild, n3000GoalBuild, Tile.RED);
        BarChartItem n3000Test = new BarChartItem("N3000", n3000CurrentTest, n3000GoalBuild, Tile.RED);
        BarChartItem n3000TestFocused = new BarChartItem("N3000", n3000CurrentTest, n3000GoalBuild, Tile.RED);
        BarChartItem n3000Stage = new BarChartItem("N3000", n3000CurrentStage, n3000GoalStage, Tile.RED);
        BarChartItem n3000StageFocused = new BarChartItem("N3000", n3000CurrentStage, n3000GoalStage, Tile.RED);

        BarChartItem s500Data = new BarChartItem("S500", s500CurrentBuild, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataFocused = new BarChartItem("S500", s500CurrentBuild, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataTest = new BarChartItem("S500", s500CurrentTest, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataTestFocused = new BarChartItem("S500", s500CurrentTest, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataStage = new BarChartItem("S500", s500CurrentStage, s500GoalStage, Tile.BLUE);
        BarChartItem s500DataStageFocused = new BarChartItem("S500", s500CurrentStage, s500GoalStage, Tile.BLUE);


        BarChartItem mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerFocused = new BarChartItem("Media Player", mediaPlayerCurrentBuild, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerTest = new BarChartItem("Media Player", mediaPlayerCurrentTest, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerTestFocused = new BarChartItem("Media Player", mediaPlayerCurrentTest, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerStage = new BarChartItem("Media Player", mediaPlayerCurrentStage, mediaPlayerGoalStage, Tile.GREEN);
        BarChartItem mediaPlayerStageFocused = new BarChartItem("Media Player", mediaPlayerCurrentStage, mediaPlayerGoalStage, Tile.GREEN);

        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataFocused = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataTest = new BarChartItem("Kiwi 4", kiwi4sCurrentTest, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataTestFocused = new BarChartItem("Kiwi 4", kiwi4sCurrentTest, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataStage = new BarChartItem("Kiwi 4", kiwi4sCurrentStage, kiwi4sGoalStage, Tile.BLUE);
        BarChartItem kiwi4DataStageFocused = new BarChartItem("Kiwi 4", kiwi4sCurrentStage, kiwi4sGoalStage, Tile.BLUE);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentBuild, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataFocused = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentBuild, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataTest = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentTest, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataTestFocused = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentTest, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataStage = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentStage, kiwi2XsGoalStage, Tile.RED);
        BarChartItem kiwi25DataStageFocused = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentStage, kiwi2XsGoalStage, Tile.RED);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataFocused = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataTest = new BarChartItem("Bumpbar", bumpBarsCurrentTest, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataTestFocused = new BarChartItem("Bumpbar", bumpBarsCurrentTest, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataStage = new BarChartItem("Bumpbar", bumpBarsCurrentStage, bumpBarsGoalStage, Tile.GREEN);
        BarChartItem bumpBarDataStageFocused = new BarChartItem("Bumpbar", bumpBarsCurrentStage, bumpBarsGoalStage, Tile.GREEN);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4DataFocused = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Test = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentTest, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4TestFocused = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentTest, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Stage = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentStage, pantherEPC4sGoalStage, Tile.YELLOW);
        BarChartItem pantherEPC4StageFocused = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentStage, pantherEPC4sGoalStage, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, optic12sGoalBuild, Tile.RED);
        BarChartItem optic12DataFocused = new BarChartItem("Optic 12", optic12sCurrentBuild, optic12sGoalBuild, Tile.RED);
        BarChartItem optic12DataTest = new BarChartItem("Optic 12", optic12sCurrentTest, optic12sGoalBuild, Tile.RED);
        BarChartItem optic12DataTestFocused = new BarChartItem("Optic 12", optic12sCurrentTest, optic12sGoalBuild, Tile.RED);


        BarChartItem optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        BarChartItem optic5DataFocused = new BarChartItem("Optic 5", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        BarChartItem optic5DataTest = new BarChartItem("Optic 5", optic5sCurrentTest, optic5sGoalBuild, Tile.BLUE);
        BarChartItem optic5DataTestFocused = new BarChartItem("Optic 5", optic5sCurrentTest, optic5sGoalBuild, Tile.BLUE);

        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        BarChartItem xr5Data = new BarChartItem("7701", xr5CurrentBuild, xr5GoalBuild, Tile.BLUE);
        BarChartItem xr5DataFocused = new BarChartItem("7701", xr5CurrentBuild, xr5GoalBuild, Tile.BLUE);
        BarChartItem xr5DataTest = new BarChartItem("7701", xr5CurrentTest, xr5GoalBuild, Tile.BLUE);
        BarChartItem xr5DataTestFocused = new BarChartItem("7701", xr5CurrentTest, xr5GoalBuild, Tile.BLUE);
        BarChartItem xr5DataStage = new BarChartItem("7701", xr5CurrentStage, xr5GoalStage, Tile.BLUE);
        BarChartItem xr5DataStageFocused = new BarChartItem("7701", xr5CurrentStage, xr5GoalStage, Tile.BLUE);

        BarChartItem xr7Data = new BarChartItem("7702", xr7CurrentBuild, xr7GoalBuild, Tile.RED);
        BarChartItem xr7DataFocused = new BarChartItem("7702", xr7CurrentBuild, xr7GoalBuild, Tile.RED);
        BarChartItem xr7DataTest = new BarChartItem("7702", xr7CurrentTest, xr7GoalBuild, Tile.RED);
        BarChartItem xr7DataTestFocused = new BarChartItem("7702", xr7CurrentTest, xr7GoalBuild, Tile.RED);
        BarChartItem xr7DataStage = new BarChartItem("7702", xr7CurrentStage, xr7GoalStage, Tile.RED);
        BarChartItem xr7DataStageFocused = new BarChartItem("7702", xr7CurrentStage, xr7GoalStage, Tile.RED);

        BarChartItem xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataFocused = new BarChartItem("7703", xr7PlusCurrentBuild, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataTest = new BarChartItem("7703", xr7PlusCurrentTest, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataTestFocused = new BarChartItem("7703", xr7PlusCurrentTest, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataStage = new BarChartItem("7703", xr7PlusCurrentStage, xr7PlusGoalStage, Tile.GREEN);
        BarChartItem xr7PlusDataStageFocused = new BarChartItem("7703", xr7PlusCurrentStage, xr7PlusGoalStage, Tile.GREEN);


        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysFocused = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysTest = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysTestFocused = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysStage = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.YELLOW);
        BarChartItem nextGenDisplaysStageFocused = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.YELLOW);

        posGridPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        posGridPane.setPrefSize(1920, 1080);

        retailGridPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        retailGridPane.setPrefSize(1920, 1080);

        serversGridPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        serversGridPane.setPrefSize(1920, 1080);

        periphGridPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        periphGridPane.setPrefSize(1920, 1080);

        //---------------------------------Scheduled Service for All Updates--------------------------------------------
        ScheduledService buildVariables = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {

                        System.out.println("\n***********Running Build Block.***********\n");

                        //---------------------------------Hosp Build---------------------------------------------------
                        posBuildMap = dataBaseTool.hospBuildDataBase();

                        p1532CurrentBuild = mapTool.getCurrentSingleValue("7734", posBuildMap);
                        p1x30CurrentBuild = mapTool.getCurrentSingleValue("7743", posBuildMap);
                        p1x35CurrentBuild = mapTool.getCurrentGroupValue(p1x35ProdList, posBuildMap);
                        t1000sCurrentBuild = mapTool.getCurrentSingleValue("7744", posBuildMap);

                        //---------------------------------Retail Build-------------------------------------------------
                        retailBuildMap = dataBaseTool.retailBuildDataBase();

                        xr7CurrentBuild = mapTool.getCurrentSingleValue("7702", retailBuildMap);
                        xr7PlusCurrentBuild = mapTool.getCurrentSingleValue("7703", retailBuildMap);
                        xr5CurrentBuild = mapTool.getCurrentSingleValue("7701", retailBuildMap);
                        nextGenDisplayCurrentBuild = mapTool.getCurrentGroupValue(nextGenProdList, retailBuildMap);

                        //---------------------------------Servers Build------------------------------------------------
                        serversBuildMap = dataBaseTool.serversBuildDataBase();

                        mediaPlayerCurrentBuild = mapTool.getCurrentSingleValue("1656", serversBuildMap);
                        n3000CurrentBuild = mapTool.getCurrentSingleValue("1657", serversBuildMap);
                        s500CurrentBuild = mapTool.getCurrentGroupValue(s500ProdList, serversBuildMap);

                        //---------------------------------Periph Build-------------------------------------------------
                        periphBuildMap = dataBaseTool.periphBuildDataBase();

                        kiwi4sCurrentBuild = mapTool.getCurrentSingleValue("1924", periphBuildMap);
                        kiwi2XsCurrentBuild = mapTool.getCurrentGroupValue(kiwi2XsProdList, periphBuildMap);
                        bumpBarsCurrentBuild = mapTool.getCurrentSingleValue("1635", periphBuildMap);
                        pantherEPC4sCurrentBuild = mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphBuildMap);

                        //---------------------------------Optic Build--------------------------------------------------
                        opticBuildMap = dataBaseTool.opticBuildDataBase();
                        optic5sCurrentBuild = mapTool.getCurrentSingleValue("6001", opticBuildMap);
                        optic12sCurrentBuild = mapTool.getCurrentSingleValue("6002", opticBuildMap);
                        kitsCurrentBuild = mapTool.getCurrentSingleValue("6003", opticBuildMap);

                        System.out.println("\n***********Running Test Block.***********\n");
                        //---------------------------------Hosp Test-----------------------------------------------------------------------
                        posTestMap = dataBaseTool.hospTestDataBase();

                        p1532CurrentTest = mapTool.getCurrentSingleValue("7734", posTestMap);
                        p1x30CurrentTest = mapTool.getCurrentSingleValue("7743", posTestMap);
                        p1x35CurrentTest = mapTool.getCurrentGroupValue(p1x35ProdList, posTestMap);
                        t1000sCurrentTest = mapTool.getCurrentSingleValue("7744", posTestMap);

                        //---------------------------------Retail Test-----------------------------------------------------------------------
                        retailTestMap = dataBaseTool.retailTestDataBase();

                        xr7CurrentTest = mapTool.getCurrentSingleValue("7702", retailTestMap);
                        xr7PlusCurrentTest = mapTool.getCurrentSingleValue("7703", retailTestMap);
                        xr5CurrentTest = mapTool.getCurrentSingleValue("7701", retailTestMap);
                        nextGenDisplayCurrentTest = mapTool.getCurrentGroupValue(nextGenProdList, retailTestMap);

                        //---------------------------------Server Test-----------------------------------------------------------------------
                        serversTestMap = dataBaseTool.serversTestDataBase();


                        mediaPlayerCurrentTest = mapTool.getCurrentSingleValue("1656", serversTestMap);
                        n3000CurrentTest = mapTool.getCurrentSingleValue("1657", serversTestMap);
                        s500CurrentTest = mapTool.getCurrentGroupValue(s500ProdList, serversTestMap);

                        //---------------------------------Periph Test-----------------------------------------------------------------------
                        periphTestMap = dataBaseTool.periphTestDataBase();


                        kiwi4sCurrentTest = mapTool.getCurrentSingleValue("1924", periphTestMap);
                        kiwi2XsCurrentTest = mapTool.getCurrentGroupValue(kiwi2XsProdList, periphTestMap);
                        bumpBarsCurrentTest = mapTool.getCurrentSingleValue("1635", periphTestMap);
                        pantherEPC4sCurrentTest = mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphTestMap);

                        //---------------------------------Optic Test----------------------------------------------------------------------
                        opticTestMap = dataBaseTool.opticTestDataBase();


                        optic5sCurrentTest = mapTool.getCurrentSingleValue("6001", opticTestMap);
                        optic12sCurrentTest = mapTool.getCurrentSingleValue("6002", opticTestMap);
                        kitsCurrentTest = mapTool.getCurrentSingleValue("6003", opticTestMap);

                        System.out.println("\n***********Running Stage Block.***********\n");

                        //---------------------------------Hosp Staging-----------------------------------------------------------------------
                        posStageMap = dataBaseTool.hospStageDataBase();

                        posUserStageMap = dataBaseTool.hospStageDataBaseUsers();

                        p1532CurrentStage = mapTool.getCurrentSingleValue("7734", posStageMap);
                        p1x30CurrentStage = mapTool.getCurrentSingleValue("7743", posStageMap);
                        p1x35CurrentStage = mapTool.getCurrentGroupValue(p1x35ProdList, posStageMap);
                        t1000sCurrentStage = mapTool.getCurrentSingleValue("7744", posStageMap);

                        //---------------------------------Retail Stage-----------------------------------------------------------------------
                        retailStageMap = dataBaseTool.retailStageDataBase();
                        retailUserStageMap = dataBaseTool.retailStageDataBaseUsers();


                        xr7CurrentStage = mapTool.getCurrentSingleValue("7702", retailStageMap);
                        xr7PlusCurrentStage = mapTool.getCurrentSingleValue("7703", retailStageMap);
                        xr5CurrentStage = mapTool.getCurrentSingleValue("7701", retailStageMap);
                        nextGenDisplayCurrentStage = mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap);

                        //---------------------------------Servers Stage-----------------------------------------------------------------------
                        serversStageMap = dataBaseTool.serversStageDataBase();

                        serversUserStageMap = dataBaseTool.serverStageDataBaseUsers();


                        mediaPlayerCurrentStage = mapTool.getCurrentSingleValue("1656", serversStageMap);
                        n3000CurrentStage = mapTool.getCurrentSingleValue("1657", serversStageMap);
                        s500CurrentStage = mapTool.getCurrentGroupValue(s500ProdList, serversStageMap);

                        //---------------------------------Periph Stage-----------------------------------------------------------------------
                        periphStageMap = dataBaseTool.periphStageDataBase();

                        periphUserStageMap = dataBaseTool.periphStageDataBaseUsers();

                        kiwi4sCurrentStage = mapTool.getCurrentSingleValue("1924", periphStageMap);
                        kiwi2XsCurrentStage = mapTool.getCurrentGroupValue(kiwi2XsProdList, periphStageMap);
                        bumpBarsCurrentStage = mapTool.getCurrentSingleValue("1635", periphStageMap);
                        pantherEPC4sCurrentStage = mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphStageMap);

                        System.out.println("\n***********Running Doc Block.***********\n");
                        mapList = dataBaseTool.documentReader();
                        stageMapList = dataBaseTool.stageDocumentReader();



                        p1532GoalBuild = goalTool.getGoal(mapList.get(0), "7734");
                        p1x30GoalBuild = goalTool.getGoal(mapList.get(0), "7743");
                        p1x35GoalBuild = goalTool.getListGoal(mapList.get(0), p1x35ProdList);
                        t1000sGoalBuild = goalTool.getGoal(mapList.get(0), "7744");

                        p1532GoalStage = goalTool.getGoal(stageMapList.get(0), "7734");
                        p1x30GoalStage = goalTool.getGoal(stageMapList.get(0), "7743");
                        p1x35GoalStage = goalTool.getListGoal(stageMapList.get(0), p1x35ProdList);
                        t1000sGoalStage = goalTool.getGoal(stageMapList.get(0), "7744");


                        //---------------------------------Percent Calculation and Update for POS-------------------------------
                        posTotalGoalBuild = p1532GoalBuild + p1x30GoalBuild + p1x35GoalBuild + t1000sGoalBuild;
                        posTotalCurrentBuild = p1532CurrentBuild + p1x35CurrentBuild + p1x30CurrentBuild + t1000sCurrentBuild;

                        posTotalCurrentTest = p1532CurrentTest + p1x35CurrentTest + p1x30CurrentTest + t1000sCurrentTest;

                        posTotalGoalStage = p1532GoalStage + p1x30GoalStage + p1x35GoalStage + t1000sGoalStage;
                        posTotalCurrentStage = p1532CurrentStage + p1x35CurrentStage + p1x30CurrentStage + t1000sCurrentStage;

                        posPercentTotalBuild = goalTool.getPercentTotal(posTotalCurrentBuild, posTotalGoalBuild);
                        posPercentTotalTest = goalTool.getPercentTotal(posTotalCurrentTest, posTotalGoalBuild);
                        posPercentTotalStage = goalTool.getPercentTotal(posTotalCurrentStage, posTotalGoalStage);

                        //---------------------------------Retail Build---------------------------------------------------------
                        xr7GoalBuild = goalTool.getGoal(mapList.get(4), "7702");
                        xr7PlusGoalBuild = goalTool.getGoal(mapList.get(4), "7703");
                        xr5GoalBuild = goalTool.getGoal(mapList.get(4), "7701");
                        nextGenDisplayGoalsBuild = goalTool.getListGoal(mapList.get(4), nextGenProdList);

                        xr7GoalStage = goalTool.getGoal(stageMapList.get(3), "7702");
                        xr7PlusGoalStage = goalTool.getGoal(stageMapList.get(3), "7703");
                        xr5GoalStage = goalTool.getGoal(stageMapList.get(3), "7701");
                        nextGenDisplayGoalsStage = goalTool.getListGoal(stageMapList.get(3), nextGenProdList);

                        //---------------------------------Percent Calculation and Update for Retail----------------------------
                        retailTotalGoalBuild = xr7GoalBuild + xr7PlusGoalBuild + xr5GoalBuild + nextGenDisplayGoalsBuild;
                        retailTotalCurrentBuild = xr7CurrentBuild + xr7PlusCurrentBuild + xr5CurrentBuild + nextGenDisplayCurrentBuild;

                        retailTotalCurrentTest = xr7CurrentTest + xr7PlusCurrentTest + xr5CurrentTest + nextGenDisplayCurrentTest;

                        retailTotalGoalStage = xr7GoalStage + xr7PlusGoalStage + xr5GoalStage + nextGenDisplayGoalsStage;
                        retailTotalCurrentStage = xr7CurrentStage + xr7PlusCurrentStage + xr5CurrentStage + nextGenDisplayCurrentStage;

                        retailPercentTotalBuild = goalTool.getPercentTotal(retailTotalCurrentBuild, retailTotalGoalBuild);
                        retailPercentTotalTest = goalTool.getPercentTotal(retailTotalCurrentTest, retailTotalGoalBuild);
                        retailPercentTotalStage = goalTool.getPercentTotal(retailTotalCurrentStage, retailTotalGoalStage);

                        //---------------------------------Servers Build--------------------------------------------------------
                        mediaPlayerGoalBuild = goalTool.getGoal(mapList.get(1), "1656");
                        n3000GoalBuild = goalTool.getGoal(mapList.get(1), "1657");
                        s500GoalBuild = goalTool.getGoal(mapList.get(1), "1611");

                        mediaPlayerGoalStage = goalTool.getGoal(stageMapList.get(1), "1656");
                        n3000GoalStage= goalTool.getGoal(stageMapList.get(1), "1657");
                        s500GoalStage = goalTool.getGoal(stageMapList.get(1), "1611");

                        //---------------------------------Percent Calculation and Update for Servers---------------------------
                        serverGoalTotalBuild = mediaPlayerGoalBuild + n3000GoalBuild + s500GoalBuild;
                        serverCurrentBuild = mediaPlayerCurrentBuild + n3000CurrentBuild + s500CurrentBuild;

                        serverCurrentTest = mediaPlayerCurrentTest + n3000CurrentTest + s500CurrentTest;

                        serverGoalTotalStage = mediaPlayerGoalStage + n3000GoalStage + s500GoalStage;
                        serverCurrentStage = mediaPlayerCurrentStage + n3000CurrentStage + s500CurrentStage;


                        serversPercentTotalBuild = goalTool.getPercentTotal(serverCurrentBuild, serverGoalTotalBuild);
                        serversPercentTotalTest = goalTool.getPercentTotal(serverCurrentTest, serverGoalTotalBuild);
                        serversPercentTotalStage = goalTool.getPercentTotal(serverCurrentStage, serverGoalTotalStage);

                        //---------------------------------Periph Build---------------------------------------------------------
                        kiwi4sGoalBuild = goalTool.getGoal(mapList.get(3), "1924");
                        kiwi2XsGoalBuild = goalTool.getListGoal(mapList.get(3), kiwi2XsProdList);
                        bumpBarsGoalBuild = goalTool.getGoal(mapList.get(3), "1635");
                        pantherEPC4sGoalBuild = goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList);

                        kiwi4sGoalStage = goalTool.getGoal(stageMapList.get(2), "1924");
                        kiwi2XsGoalStage = goalTool.getListGoal(stageMapList.get(2), kiwi2XsProdList);
                        bumpBarsGoalStage = goalTool.getGoal(stageMapList.get(2), "1635");
                        pantherEPC4sGoalStage = goalTool.getListGoal(stageMapList.get(2), pantherEPC4sProdList);

                        //---------------------------------Percent Calculation and Update for Periph----------------------------
                        periphGoalTotalBuild = kiwi4sGoalBuild + kiwi2XsGoalBuild + bumpBarsGoalBuild + pantherEPC4sGoalBuild;
                        periphCurrentTotalBuild = kiwi4sCurrentBuild + kiwi2XsCurrentBuild + bumpBarsCurrentBuild + pantherEPC4sCurrentBuild;

                        periphCurrentTotalTest = kiwi4sCurrentTest + kiwi2XsCurrentTest + bumpBarsCurrentTest + pantherEPC4sCurrentTest;

                        periphGoalTotalStage = kiwi4sGoalStage + kiwi2XsGoalStage + bumpBarsGoalStage + pantherEPC4sGoalStage;
                        periphCurrentTotalStage = kiwi4sCurrentStage + kiwi2XsCurrentStage + bumpBarsCurrentStage + pantherEPC4sCurrentStage;


                        periphPercentTotalBuild = goalTool.getPercentTotal(periphCurrentTotalBuild, periphGoalTotalBuild);
                        periphPercentTotalTest = goalTool.getPercentTotal(periphCurrentTotalTest, periphGoalTotalBuild);
                        periphPercentTotalStage = goalTool.getPercentTotal(periphCurrentTotalStage, periphGoalTotalStage);

                        //---------------------------------Optic Build----------------------------------------------------------
                        optic5sGoalBuild = goalTool.getGoal(mapList.get(2), "6001");
                        optic12sGoalBuild = goalTool.getGoal(mapList.get(2), "6002");
                        kitsGoalBuild = goalTool.getGoal(mapList.get(2), "6003");

                        //---------------------------------Percent Calculation and Update for Optic-----------------------------
                        opticGoalTotalBuild = optic5sGoalBuild + optic12sGoalBuild + kitsGoalBuild;
                        opticCurrentTotalBuild = optic5sCurrentBuild + optic12sCurrentBuild + kitsCurrentBuild;

                        opticCurrentTotalTest = optic5sCurrentTest + optic12sCurrentTest + kitsCurrentTest;


                        opticPercentTotalBuild = goalTool.getPercentTotal(opticCurrentTotalBuild, opticGoalTotalBuild);
                        opticPercentTotalTest = goalTool.getPercentTotal(opticCurrentTotalTest, opticGoalTotalBuild);

                        //---------------------------------Update Bar Values--------------------------------------------
                        p1x35Data.setValue(p1x35CurrentBuild);
                        p1x35Data.setMaxValue(p1x35GoalBuild);
                        p1x35DataFocused.setValue(p1x35CurrentBuild);
                        p1x35DataFocused.setMaxValue(p1x35GoalBuild);
                        p1x35DataTest.setValue(p1x35CurrentTest);
                        p1x35DataTest.setMaxValue(p1x35GoalBuild);
                        p1x35DataTestFocused.setValue(p1x35CurrentTest);
                        p1x35DataTestFocused.setMaxValue(p1x35GoalBuild);
                        p1x35DataStage.setValue(p1x35CurrentStage);
                        p1x35DataStage.setMaxValue(p1x35GoalStage);
                        p1x35DataStageFocused.setValue(p1x35CurrentStage);
                        p1x35DataStageFocused.setMaxValue(p1x35GoalStage);

                        p1532Data.setValue(p1532CurrentBuild);
                        p1532Data.setMaxValue(p1532GoalBuild);
                        p1532DataTest.setValue(p1532CurrentTest);
                        p1532DataTest.setMaxValue(p1532GoalBuild);
                        p1532DataFocused.setValue(p1532CurrentBuild);
                        p1532DataFocused.setMaxValue(p1532GoalBuild);
                        p1532DataTestFocused.setValue(p1532CurrentTest);
                        p1532DataTestFocused.setMaxValue(p1532GoalBuild);
                        p1532DataStage.setValue(p1532CurrentStage);
                        p1532DataStage.setMaxValue(p1532GoalStage);
                        p1532DataStageFocused.setValue(p1532CurrentStage);
                        p1532DataStageFocused.setMaxValue(p1532GoalStage);

                        p1x30Data.setValue(p1x30CurrentBuild);
                        p1x30Data.setMaxValue(p1x30GoalBuild);
                        p1x30DataTest.setValue(p1x30CurrentTest);
                        p1x30DataTest.setMaxValue(p1x30GoalBuild);
                        p1x30DataFocused.setValue(p1x30CurrentBuild);
                        p1x30DataFocused.setMaxValue(p1x30GoalBuild);
                        p1x30DataTestFocused.setValue(p1x30CurrentTest);
                        p1x30DataTestFocused.setMaxValue(p1x30GoalBuild);
                        p1x30DataStage.setValue(p1x30CurrentStage);
                        p1x30DataStage.setMaxValue(p1x30GoalStage);
                        p1x30DataStageFocused.setValue(p1x30CurrentStage);
                        p1x30DataStageFocused.setMaxValue(p1x30GoalStage);

                        t1000Data.setValue(t1000sCurrentBuild);
                        t1000Data.setMaxValue(t1000sGoalBuild);
                        t1000DataTest.setValue(t1000sCurrentTest);
                        t1000DataTest.setMaxValue(t1000sGoalBuild);
                        t1000DataFocused.setValue(t1000sCurrentBuild);
                        t1000DataFocused.setMaxValue(t1000sGoalBuild);
                        t1000DataTestFocused.setValue(t1000sCurrentTest);
                        t1000DataTestFocused.setMaxValue(t1000sGoalBuild);
                        t1000DataStage.setValue(t1000sCurrentStage);
                        t1000DataStage.setMaxValue(t1000sGoalStage);
                        t1000DataStageFocused.setValue(t1000sCurrentStage);
                        t1000DataStageFocused.setMaxValue(t1000sGoalStage);

                        //---------------------------------Update the Server Units------------------------------------------
                        n3000Data.setValue(n3000CurrentBuild);
                        n3000Data.setMaxValue(n3000GoalBuild);
                        n3000Test.setValue(n3000CurrentTest);
                        n3000Test.setMaxValue(n3000GoalBuild);
                        n3000DataFocused.setValue(n3000CurrentBuild);
                        n3000DataFocused.setMaxValue(n3000GoalBuild);
                        n3000TestFocused.setValue(n3000CurrentTest);
                        n3000TestFocused.setMaxValue(n3000GoalBuild);
                        n3000Stage.setValue(n3000CurrentStage);
                        n3000Stage.setMaxValue(n3000GoalStage);
                        n3000StageFocused.setValue(n3000CurrentStage);
                        n3000StageFocused.setMaxValue(n3000GoalStage);

                        s500Data.setValue(s500CurrentBuild);
                        s500Data.setMaxValue(s500GoalBuild);
                        s500DataTest.setValue(s500CurrentTest);
                        s500DataTest.setMaxValue(s500GoalBuild);
                        s500DataFocused.setValue(s500CurrentBuild);
                        s500DataFocused.setMaxValue(s500GoalBuild);
                        s500DataTestFocused.setValue(s500CurrentTest);
                        s500DataTestFocused.setMaxValue(s500GoalBuild);
                        s500DataStage.setValue(s500CurrentStage);
                        s500DataStage.setMaxValue(s500GoalStage);
                        s500DataStageFocused.setValue(s500CurrentStage);
                        s500DataStageFocused.setMaxValue(s500GoalStage);

                        mediaPlayer.setValue(mediaPlayerCurrentBuild);
                        mediaPlayer.setMaxValue(mediaPlayerGoalBuild);
                        mediaPlayerTest.setValue(mediaPlayerCurrentTest);
                        mediaPlayerTest.setMaxValue(mediaPlayerGoalBuild);
                        mediaPlayerFocused.setValue(mediaPlayerCurrentBuild);
                        mediaPlayerFocused.setMaxValue(mediaPlayerGoalBuild);
                        mediaPlayerTestFocused.setValue(mediaPlayerCurrentTest);
                        mediaPlayerTestFocused.setMaxValue(mediaPlayerGoalBuild);
                        mediaPlayerStage.setValue(mediaPlayerCurrentStage);
                        mediaPlayerStage.setMaxValue(mediaPlayerGoalStage);
                        mediaPlayerStageFocused.setValue(mediaPlayerCurrentStage);
                        mediaPlayerStageFocused.setMaxValue(mediaPlayerGoalStage);

                        //---------------------------------Updating the Peripheral Units------------------------------------
                        kiwi4Data.setValue(kiwi4sCurrentBuild);
                        kiwi4Data.setMaxValue(kiwi4sGoalBuild);
                        kiwi4DataTest.setValue(kiwi4sCurrentTest);
                        kiwi4DataTest.setMaxValue(kiwi4sGoalBuild);
                        kiwi4DataFocused.setValue(kiwi4sCurrentBuild);
                        kiwi4DataFocused.setMaxValue(kiwi4sGoalBuild);
                        kiwi4DataTestFocused.setValue(kiwi4sCurrentTest);
                        kiwi4DataTestFocused.setMaxValue(kiwi4sGoalBuild);
                        kiwi4DataStage.setValue(kiwi4sCurrentStage);
                        kiwi4DataStage.setMaxValue(kiwi4sGoalStage);
                        kiwi4DataStageFocused.setValue(kiwi4sCurrentStage);
                        kiwi4DataStageFocused.setMaxValue(kiwi4sGoalStage);

                        kiwi25Data.setValue(kiwi2XsCurrentBuild);
                        kiwi25Data.setMaxValue(kiwi2XsGoalBuild);
                        kiwi25DataTest.setValue(kiwi2XsCurrentTest);
                        kiwi25DataTest.setMaxValue(kiwi2XsGoalBuild);
                        kiwi25DataFocused.setValue(kiwi2XsCurrentBuild);
                        kiwi25DataFocused.setMaxValue(kiwi2XsGoalBuild);
                        kiwi25DataTestFocused.setValue(kiwi2XsCurrentTest);
                        kiwi25DataTestFocused.setMaxValue(kiwi2XsGoalBuild);
                        kiwi25DataStage.setValue(kiwi2XsCurrentStage);
                        kiwi25DataStage.setMaxValue(kiwi2XsGoalStage);
                        kiwi25DataStageFocused.setValue(kiwi2XsCurrentStage);
                        kiwi25DataStageFocused.setMaxValue(kiwi2XsGoalStage);

                        bumpBarData.setValue(bumpBarsCurrentBuild);
                        bumpBarData.setMaxValue(bumpBarsGoalBuild);
                        bumpBarDataTest.setValue(bumpBarsCurrentTest);
                        bumpBarDataTest.setMaxValue(bumpBarsGoalBuild);
                        bumpBarDataFocused.setValue(bumpBarsCurrentBuild);
                        bumpBarDataFocused.setMaxValue(bumpBarsGoalBuild);
                        bumpBarDataTestFocused.setValue(bumpBarsCurrentTest);
                        bumpBarDataTestFocused.setMaxValue(bumpBarsGoalBuild);
                        bumpBarDataStage.setValue(bumpBarsCurrentStage);
                        bumpBarDataStage.setMaxValue(bumpBarsGoalStage);
                        bumpBarDataStageFocused.setValue(bumpBarsCurrentStage);
                        bumpBarDataStageFocused.setMaxValue(bumpBarsGoalStage);

                        pantherEPC4Data.setValue(pantherEPC4sCurrentBuild);
                        pantherEPC4Data.setMaxValue(pantherEPC4sGoalBuild);
                        pantherEPC4Test.setValue(pantherEPC4sCurrentTest);
                        pantherEPC4Test.setMaxValue(pantherEPC4sGoalBuild);
                        pantherEPC4DataFocused.setValue(pantherEPC4sCurrentBuild);
                        pantherEPC4DataFocused.setMaxValue(pantherEPC4sGoalBuild);
                        pantherEPC4TestFocused.setValue(pantherEPC4sCurrentTest);
                        pantherEPC4TestFocused.setMaxValue(pantherEPC4sGoalBuild);
                        pantherEPC4Stage.setValue(pantherEPC4sCurrentStage);
                        pantherEPC4Stage.setMaxValue(pantherEPC4sGoalStage);
                        pantherEPC4StageFocused.setValue(pantherEPC4sCurrentStage);
                        pantherEPC4StageFocused.setMaxValue(pantherEPC4sGoalStage);

                        //---------------------------------Updating the Optic Units------------------------------------------
                        optic5Data.setValue(optic5sCurrentBuild);
                        optic5Data.setMaxValue(optic5sGoalBuild);
                        optic5DataTest.setValue(optic5sCurrentTest);
                        optic5DataTest.setMaxValue(optic5sGoalBuild);
                        optic5DataFocused.setValue(optic5sCurrentBuild);
                        optic5DataFocused.setMaxValue(optic5sGoalBuild);
                        optic5DataTestFocused.setValue(optic5sCurrentTest);
                        optic5DataTestFocused.setMaxValue(optic5sGoalBuild);

                        optic12Data.setValue(optic12sCurrentBuild);
                        optic12Data.setMaxValue(optic12sGoalBuild);
                        optic12DataTest.setValue(optic12sCurrentTest);
                        optic12DataTest.setMaxValue(optic12sGoalBuild);
                        optic12DataFocused.setValue(optic12sCurrentBuild);
                        optic12DataFocused.setMaxValue(optic12sGoalBuild);
                        optic12DataTestFocused.setValue(optic12sCurrentTest);
                        optic12DataTestFocused.setMaxValue(optic12sGoalBuild);

                        //---------------------------------Updating the Retail Units----------------------------------------
                        xr5Data.setValue(xr5CurrentBuild);
                        xr5Data.setMaxValue(xr5GoalBuild);
                        xr5DataTest.setValue(xr5CurrentTest);
                        xr5DataTest.setMaxValue(xr5GoalBuild);
                        xr5DataFocused.setValue(xr5CurrentBuild);
                        xr5DataFocused.setMaxValue(xr5GoalBuild);
                        xr5DataTestFocused.setValue(xr5CurrentTest);
                        xr5DataTestFocused.setMaxValue(xr5GoalBuild);
                        xr5DataStage.setValue(xr5CurrentStage);
                        xr5DataStage.setMaxValue(xr5GoalStage);
                        xr5DataStageFocused.setValue(xr5CurrentStage);
                        xr5DataStageFocused.setMaxValue(xr5GoalStage);

                        xr7Data.setValue(xr7CurrentBuild);
                        xr7Data.setMaxValue(xr7GoalBuild);
                        xr7DataTest.setValue(xr7CurrentTest);
                        xr7DataTest.setMaxValue(xr7GoalBuild);
                        xr7DataFocused.setValue(xr7CurrentBuild);
                        xr7DataFocused.setMaxValue(xr7GoalBuild);
                        xr7DataTestFocused.setValue(xr7CurrentTest);
                        xr7DataTestFocused.setMaxValue(xr7GoalBuild);
                        xr7DataStage.setValue(xr7CurrentStage);
                        xr7DataStage.setMaxValue(xr7GoalStage);
                        xr7DataStageFocused.setValue(xr7CurrentStage);
                        xr7DataStageFocused.setMaxValue(xr7GoalStage);

                        xr7PlusData.setValue(xr7PlusCurrentBuild);
                        xr7PlusData.setMaxValue(xr7PlusGoalBuild);
                        xr7PlusDataTest.setValue(xr7PlusCurrentTest);
                        xr7PlusDataTest.setMaxValue(xr7PlusGoalBuild);
                        xr7PlusDataFocused.setValue(xr7PlusCurrentBuild);
                        xr7PlusDataFocused.setMaxValue(xr7PlusGoalBuild);
                        xr7PlusDataTestFocused.setValue(xr7PlusCurrentTest);
                        xr7PlusDataTestFocused.setMaxValue(xr7PlusGoalBuild);
                        xr7PlusDataStage.setValue(xr7PlusCurrentStage);
                        xr7PlusDataStage.setMaxValue(xr7PlusGoalStage);
                        xr7PlusDataStageFocused.setValue(xr7PlusCurrentStage);
                        xr7PlusDataStageFocused.setMaxValue(xr7PlusGoalStage);

                        nextGenDisplays.setValue(nextGenDisplayCurrentBuild);
                        nextGenDisplays.setMaxValue(nextGenDisplayGoalsBuild);
                        nextGenDisplaysTest.setValue(nextGenDisplayCurrentTest);
                        nextGenDisplaysTest.setMaxValue(nextGenDisplayGoalsBuild);
                        nextGenDisplaysFocused.setValue(nextGenDisplayCurrentBuild);
                        nextGenDisplaysFocused.setMaxValue(nextGenDisplayGoalsBuild);
                        nextGenDisplaysTestFocused.setValue(nextGenDisplayCurrentTest);
                        nextGenDisplaysTestFocused.setMaxValue(nextGenDisplayGoalsBuild);
                        nextGenDisplaysStage.setValue(nextGenDisplayCurrentStage);
                        nextGenDisplaysStage.setMaxValue(nextGenDisplayGoalsStage);
                        nextGenDisplaysStageFocused.setValue(nextGenDisplayCurrentStage);
                        nextGenDisplaysStageFocused.setMaxValue(nextGenDisplayGoalsStage);

                        //---------------------------------Creating Color Changes for POS Dial------------------------------------------
                        posPercent.setValue(posPercentTotalBuild);
                        posPercentTest.setValue(posPercentTotalTest);
                        posPercentStage.setValue(posPercentTotalStage);
                        posPercentFocused.setValue(posPercentTotalBuild);
                        posPercentTestFocused.setValue(posPercentTotalTest);
                        posPercentStageFocused.setValue(posPercentTotalStage);

                        changePercent(posPercent,posPercentFocused,posTotalCurrentBuild,posTotalGoalBuild,posPercentTotalBuild);
                        changePercent(posPercentTest,posPercentTestFocused,posTotalCurrentTest,posTotalGoalBuild,posPercentTotalTest);
                        changePercent(posPercentStage,posPercentStageFocused,posTotalCurrentStage,posTotalGoalStage,posPercentTotalStage);

                        //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
                        serversPercent.setValue(serversPercentTotalBuild);
                        serversPercentTest.setValue(serversPercentTotalTest);
                        serversPercentFocused.setValue(serversPercentTotalBuild);
                        serversPercentTestFocused.setValue(serversPercentTotalTest);
                        serversPercentStage.setValue(serversPercentTotalStage);
                        serversPercentStageFocused.setValue(serversPercentTotalStage);

                        changePercent(serversPercent,serversPercentFocused,serverCurrentBuild,serverGoalTotalBuild,serversPercentTotalBuild);
                        changePercent(serversPercentTest,serversPercentTestFocused,serverCurrentTest,serverGoalTotalBuild,serversPercentTotalTest);
                        changePercent(serversPercentStage,serversPercentStageFocused,serverCurrentStage,serverGoalTotalStage,serversPercentTotalStage);

                        //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
                        periphPercent.setValue(periphPercentTotalBuild);
                        periphPercentTest.setValue(periphPercentTotalTest);
                        periphPercentFocused.setValue(periphPercentTotalBuild);
                        periphPercentTestFocused.setValue(periphPercentTotalTest);
                        periphPercentStage.setValue(periphPercentTotalStage);
                        periphPercentStageFocused.setValue(periphPercentTotalStage);

                        changePercent(periphPercent,periphPercentFocused,periphCurrentTotalBuild,periphGoalTotalBuild,periphPercentTotalBuild);
                        changePercent(periphPercentTest,periphPercentFocused,periphCurrentTotalTest,periphGoalTotalBuild,periphPercentTotalTest);
                        changePercent(periphPercentStage,periphPercentStageFocused,periphCurrentTotalStage,periphGoalTotalStage,periphPercentTotalStage);

                        //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
                        opticPercent.setValue(opticPercentTotalBuild);
                        opticPercentTest.setValue(opticPercentTotalTest);
                        opticPercentFocused.setValue(opticPercentTotalBuild);
                        opticPercentTestFocused.setValue(opticPercentTotalTest);

                        changePercent(opticPercent,opticPercentFocused,opticCurrentTotalBuild,opticGoalTotalBuild,opticPercentTotalBuild);
                        changePercent(opticPercentTest,opticPercentTestFocused,opticCurrentTotalTest,opticGoalTotalBuild,opticPercentTotalTest);

                        //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
                        retailPercent.setValue(retailPercentTotalBuild);
                        retailPercentTest.setValue(retailPercentTotalTest);
                        retailPercentFocused.setValue(retailPercentTotalBuild);
                        retailPercentTestFocused.setValue(retailPercentTotalTest);
                        retailPercentStage.setValue(retailPercentTotalStage);
                        retailPercentStageFocused.setValue(retailPercentTotalStage);

                        changePercent(retailPercent,retailPercentFocused,retailTotalCurrentBuild,retailTotalGoalBuild,retailPercentTotalBuild);
                        changePercent(retailPercentTest,retailPercentTestFocused,retailTotalCurrentTest,retailTotalGoalBuild,retailPercentTotalTest);
                        changePercent(retailPercentStage,retailPercentStageFocused,retailTotalCurrentStage,retailTotalGoalStage,retailPercentTotalStage);

                        //---------------------------------This is some hacky shit-------------------------------------------
                        System.out.println("\n***********Dynamic Creation Block***********\n");

                        Tile posClock = TileBuilder.create()
                                .skinType(Tile.SkinType.CLOCK)
                                .prefSize(480, 217)
                                .title("Current Time")
                                .titleAlignment(TextAlignment.CENTER)
                                .locale(Locale.US)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .running(true)
                                .dateVisible(false)
                                .roundedCorners(false)
                                .textAlignment(TextAlignment.CENTER)
                                .build();

                        Tile retailClock = TileBuilder.create()
                                .skinType(Tile.SkinType.CLOCK)
                                .prefSize(480, 217)
                                .title("Current Time")
                                .titleAlignment(TextAlignment.CENTER)
                                .locale(Locale.US)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .running(true)
                                .dateVisible(false)
                                .roundedCorners(false)
                                .textAlignment(TextAlignment.CENTER)
                                .build();

                        Tile serversClock = TileBuilder.create()
                                .skinType(Tile.SkinType.CLOCK)
                                .prefSize(480, 217)
                                .title("Current Time")
                                .titleAlignment(TextAlignment.CENTER)
                                .locale(Locale.US)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .running(true)
                                .dateVisible(false)
                                .roundedCorners(false)
                                .textAlignment(TextAlignment.CENTER)
                                .build();

                        Tile periphClock = TileBuilder.create()
                                .skinType(Tile.SkinType.CLOCK)
                                .prefSize(480, 217)
                                .title("Current Time")
                                .titleAlignment(TextAlignment.CENTER)
                                .locale(Locale.US)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .running(true)
                                .dateVisible(false)
                                .roundedCorners(false)
                                .textAlignment(TextAlignment.CENTER)
                                .build();

                        final ImageView logoView = new ImageView();
                        final Image logoImage = new Image("NCR Brand Block Logo JPG.jpg");
                        logoView.setImage(logoImage);
                        logoView.setFitHeight(217);
                        logoView.setFitWidth(480);
                        logoView.setPreserveRatio(true);

                        HBox hbox = new HBox(logoView);
                        hbox.setPrefWidth(480);
                        hbox.setPrefHeight(217);
                        hbox.setAlignment(Pos.CENTER);
                        hbox.setStyle("-fx-background-color:#54B948");


                        Tile posLogo  = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(hbox)
                                .build();

                        Tile retailLogo  = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(hbox)
                                .build();
                        Tile serversLogo  = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(hbox)
                                .build();
                        Tile periphLogo  = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(hbox)
                                .build();

                        String date = dataBaseTool.incidentReader();

                        LocalDate currentDate = LocalDate.now();
                        LocalDate incidentDate = LocalDate.parse(date);

                        long daysBetween = DAYS.between(incidentDate, currentDate);

                        int counter = Math.toIntExact(daysBetween);

                        String useDate = Integer.toString(counter);

                        ImageView stopView = new ImageView();
                        final Image redImage = new Image("Red Light.PNG");
                        final Image yellowImage = new Image("Yellow Light.PNG");
                        final Image greenImage = new Image("Green Light.PNG");

                        if(counter <30)
                        {
                            stopView.setImage(redImage);
                        }
                        if(counter > 30 && counter <60)
                        {
                            stopView.setImage(yellowImage);
                        }
                        if(counter >= 60)
                        {
                            stopView.setImage(greenImage);
                        }
                        stopView.setFitHeight(217);
                        stopView.setFitWidth(480);
                        stopView.setPreserveRatio(true);

                        HBox myBox = new HBox(stopView);
                        myBox.setPrefWidth(480);
                        myBox.setPrefHeight(217);
                        myBox.setAlignment(Pos.CENTER);
                        myBox.setStyle("-fx-background-color:#54B948");

                        Tile posStopLight = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(myBox)
                                .build();

                        Tile retailStopLight = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(myBox)
                                .build();

                        Tile serversStopLight = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(myBox)
                                .build();

                        Tile periphStopLight = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .prefSize(480,217)
                                .roundedCorners(false)
                                .graphic(myBox)
                                .build();

                        Tile posQuality = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480, 217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Days Since Last Safety Incident")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description(useDate)
                                .build();

                        Tile retailQuality = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480, 217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Days Since Last Safety Incident")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description(useDate)
                                .build();

                        Tile serversQuality = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480, 217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Days Since Last Safety Incident")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description(useDate)
                                .build();

                        Tile periphQuality = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480, 217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Days Since Last Safety Incident")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description(useDate)
                                .build();


                        Tile posGoalTile = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480,217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Department")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description("POS")
                                .build();

                        Tile retailGoalTile = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480,217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Department")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description("Retail")
                                .build();

                        Tile serversGoalTile = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480,217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Department")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description("Servers")
                                .build();

                        Tile periphGoalTile = TileBuilder.create()
                                .skinType(Tile.SkinType.CHARACTER)
                                .prefSize(480,217)
                                .backgroundColor(Color.valueOf("#54B948"))
                                .title("Department")
                                .titleAlignment(TextAlignment.CENTER)
                                .roundedCorners(false)
                                .description("Periph")
                                .build();


                        ArrayList<Tile> posTiles = new ArrayList<>();

                        ArrayList<Tile> retailTiles = new ArrayList<>();

                        ArrayList<Tile> serversTiles = new ArrayList<>();

                        ArrayList<Tile> periphTiles = new ArrayList<>();


                        posTiles = getCharTiles(posUserStageMap,480,217);
                        retailTiles = getCharTiles(retailUserStageMap,480,217);
                        serversTiles = getCharTiles(serversUserStageMap,480,217);
                        periphTiles = getCharTiles(periphUserStageMap,480,217);

                        posTiles.sort(Comparator.comparing(Tile::getDescription));
                        retailTiles.sort(Comparator.comparing(Tile::getDescription));
                        serversTiles.sort(Comparator.comparing(Tile::getDescription));
                        periphTiles.sort(Comparator.comparing(Tile::getDescription));

                        Collections.reverse(posTiles);
                        Collections.reverse(retailTiles);
                        Collections.reverse(serversTiles);
                        Collections.reverse(periphTiles);

                        for(int i = posTiles.size(); i < 15;i++)
                        {
                            Tile tile = new Tile();
                            tile.setVisible(false);
                            posTiles.add(tile);
                        }
                        for(int i = retailTiles.size(); i < 15;i++)
                        {
                            Tile tile = new Tile();
                            tile.setVisible(false);
                            retailTiles.add(tile);
                        }
                        for(int i = serversTiles.size(); i < 15;i++)
                        {
                            Tile tile = new Tile();
                            tile.setVisible(false);
                            serversTiles.add(tile);
                        }
                        for(int i = periphTiles.size(); i < 15;i++)
                        {
                            Tile tile = new Tile();
                            tile.setVisible(false);
                            periphTiles.add(tile);
                        }

                        int retailSize = retailTiles.size();
                        int serversSize = serversTiles.size();
                        int periphSize = periphTiles.size();

                        posTiles.add(0,posLogo);
                        posTiles.add(4,posClock);
                        posTiles.add(8,posGoalTile);
                        posTiles.add(12,posStopLight);
                        posTiles.add(16,posQuality);

                        retailTiles.add(0,retailLogo);
                        retailTiles.add(4,retailClock);
                        retailTiles.add(8,retailGoalTile);
                        retailTiles.add(12,retailStopLight);
                        retailTiles.add(16,retailQuality);

                        serversTiles.add(0,serversLogo);
                        serversTiles.add(4,serversClock);
                        serversTiles.add(8,serversGoalTile);
                        serversTiles.add(12,serversStopLight);
                        serversTiles.add(16,serversQuality);


                        periphTiles.add(0,periphLogo);
                        periphTiles.add(4,periphClock);
                        periphTiles.add(8,periphGoalTile);
                        periphTiles.add(12,periphStopLight);
                        periphTiles.add(16,periphQuality);

                        Platform.runLater(()->posGridPane.getChildren().clear());
                        ArrayList<Tile> finalPosTiles = posTiles;
                        Platform.runLater(()->posGridPane.getChildren().addAll(finalPosTiles));

                        Platform.runLater(()->retailGridPane.getChildren().clear());
                        ArrayList<Tile> finalRetailTiles = retailTiles;
                        Platform.runLater(()->retailGridPane.getChildren().addAll(finalRetailTiles));

                        Platform.runLater(()->serversGridPane.getChildren().clear());
                        ArrayList<Tile> finalServersTiles = serversTiles;
                        Platform.runLater(()->serversGridPane.getChildren().addAll(finalServersTiles));

                        Platform.runLater(()->periphGridPane.getChildren().clear());
                        ArrayList<Tile> finalPeriphTiles = periphTiles;
                        Platform.runLater(()->periphGridPane.getChildren().addAll(finalPeriphTiles));

                        int fuckchris = 0;


                        myLatch.countDown();
                        return null;
                    }
                };
            }
        };

        //---------------------------------Creating the Tiles-----------------------------------------------------------
        pos = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(p1x30Data, p1x35Data, p1532Data, t1000Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posPercent = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .value(posPercentTotalBuild)
                .build();

        posTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Test")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(p1x30DataTest, p1x35DataTest, p1532DataTest, t1000DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posPercentTest = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
                .value(posPercentTotalTest)
                .build();

        posStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Stage")
                .roundedCorners(true)
                .prefSize(480, 640)
                .barChartItems(p1x30DataStage, p1x35DataStage, p1532DataStage, t1000DataStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posPercentStage = TileBuilder.create()
                .prefSize(480, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .animated(true)
                .subText(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .value(posPercentTotalStage)
                .build();

        posBuildFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Build")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(p1x30DataFocused, p1x35DataFocused, p1532DataFocused, t1000DataFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posTestFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Test")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(p1x30DataTestFocused, p1x35DataTestFocused, p1532DataTestFocused, t1000DataTestFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posStageFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("POS Stage")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(p1x30DataStageFocused, p1x35DataStageFocused, p1532DataStageFocused, t1000DataStageFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posPercentFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentBuild) + "/" + Double.toString(posTotalGoalBuild))
                .value(posPercentTotalBuild)
                .build();

        posPercentTestFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild))
                .value(posPercentTotalTest)
                .build();

        posPercentStageFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .animated(true)
                .subText(Double.toString(posTotalCurrentStage) + "/" + Double.toString(posTotalGoalStage))
                .value(posPercentTotalStage)
                .build();


        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(true)
                .prefSize(384, 200)
                .barChartItems(s500Data, n3000Data, mediaPlayer)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercent = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalBuild)
                .build();

        serversTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Test")
                .roundedCorners(true)
                .prefSize(384, 200)
                .barChartItems(s500DataTest, n3000Test, mediaPlayerTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercentTest = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalTest)
                .build();

        serversStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Stage")
                .roundedCorners(true)
                .prefSize(480, 200)
                .barChartItems(s500DataStage, n3000Stage, mediaPlayerStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercentStage = TileBuilder.create()
                .prefSize(480, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .animated(true)
                .subText(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .value(serversPercentTotalStage)
                .build();

        serversBuildFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(s500DataFocused, n3000DataFocused, mediaPlayerFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercentFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentBuild) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalBuild)
                .build();

        serversTestFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Test")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(s500DataTestFocused, n3000TestFocused, mediaPlayerTestFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercentTestFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentTest) + "/" + Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalTest)
                .build();

        serversStageFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Stage (ERP Data)")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(s500DataStageFocused, n3000StageFocused, mediaPlayerStageFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        serversPercentStageFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .animated(true)
                .subText(Double.toString(serverCurrentStage) + "/" + Double.toString(serverGoalTotalStage))
                .value(serversPercentTotalStage)
                .build();


        //---------------------------------Creating the Tiles for Peripherals-------------------------------------------
        peripherals = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(kiwi4Data, kiwi25Data, bumpBarData, pantherEPC4Data)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercent = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .value(periphPercentTotalBuild)
                .build();

        peripheralsTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Test")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(kiwi4DataTest, kiwi25DataTest, bumpBarDataTest, pantherEPC4Test)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercentTest = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .value(periphPercentTotalTest)
                .build();

        peripheralsStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Stage")
                .roundedCorners(true)
                .prefSize(480, 640)
                .barChartItems(kiwi4DataStage, kiwi25DataStage, bumpBarDataStage, pantherEPC4Stage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercentStage = TileBuilder.create()
                .prefSize(480, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .subText(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .animated(true)
                .value(periphPercentTotalStage)
                .build();

        periphBuildFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(kiwi4DataFocused, kiwi25DataFocused, bumpBarDataFocused, pantherEPC4DataFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercentFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalBuild) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .value(periphPercentTotalBuild)
                .build();

        periphTestFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Test")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(kiwi4DataTestFocused, kiwi25DataTestFocused, bumpBarDataTestFocused, pantherEPC4TestFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercentTestFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalTest) + "/" + Double.toString(periphGoalTotalBuild))
                .animated(true)
                .value(periphPercentTotalTest)
                .build();

        periphStageFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Stage (ERP Data)")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(kiwi4DataStageFocused, kiwi25DataStageFocused, bumpBarDataStageFocused, pantherEPC4StageFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        periphPercentStageFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .subText(Double.toString(periphCurrentTotalStage) + "/" + Double.toString(periphGoalTotalStage))
                .animated(true)
                .value(periphPercentTotalTest)
                .build();

        //---------------------------------Creating the Tiles for Optic-------------------------------------------------
        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5Data, optic12Data)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticPercent = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalBuild) + "/" + Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .value(opticPercentTotalBuild)
                .build();

        opticTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Test")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5DataTest, optic12DataTest)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticPercentTest = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalTest) + "/" + Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .value(opticPercentTotalTest)
                .build();

        opticBuildFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(optic5DataFocused, optic12DataFocused)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticPercentFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalBuild) + "/" + Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .value(opticPercentTotalBuild)
                .build();

        opticTestFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Test")
                .roundedCorners(true)
                .prefSize(960, 640)
                .barChartItems(optic5DataTestFocused, optic12DataTestFocused)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticPercentTestFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalTest) + "/" + Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .value(opticPercentTotalTest)
                .build();

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Build")
                .prefSize(384, 640)
                .barChartItems(xr5Data, xr7Data, xr7PlusData, nextGenDisplays)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercent = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentBuild) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentBuild))
                .animated(true)
                .value(retailPercentTotalBuild)
                .build();

        retailTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Test")
                .prefSize(384, 640)
                .barChartItems(xr5DataTest, xr7DataTest, xr7PlusDataTest, nextGenDisplaysTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentTest = TileBuilder.create()
                .prefSize(384, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .animated(true)
                .value(retailPercentTotalTest)
                .build();

        retailStage = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Stage")
                .prefSize(480, 640)
                .barChartItems(xr5DataStage, xr7DataStage, xr7PlusDataStage, nextGenDisplaysStage)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentStage = TileBuilder.create()
                .prefSize(480, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .subText(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .animated(true)
                .value(retailPercentTotalStage)
                .build();

        retailBuildFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Build")
                .prefSize(960, 640)
                .barChartItems(xr5DataFocused, xr7DataFocused, xr7PlusDataFocused, nextGenDisplaysFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentBuild) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentBuild))
                .animated(true)
                .value(retailPercentTotalBuild)
                .build();

        retailTestFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Test")
                .prefSize(960, 640)
                .barChartItems(xr5DataTestFocused, xr7DataTestFocused, xr7PlusDataTestFocused, nextGenDisplaysTestFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentTestFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentTest) + "/" + Double.toString(retailTotalGoalBuild))
                .animated(true)
                .value(retailPercentTotalTest)
                .build();

        retailStageFocused = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Stage (ERP Data)")
                .prefSize(960, 640)
                .barChartItems(xr5DataStageFocused, xr7DataStageFocused, xr7PlusDataStageFocused, nextGenDisplaysStageFocused)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentStageFocused = TileBuilder.create()
                .prefSize(960, 440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .subText(Double.toString(retailTotalCurrentStage) + "/" + Double.toString(retailTotalGoalStage))
                .animated(true)
                .value(retailPercentTotalStage)
                .build();
        //---------------------------------Creating Animations for Graphs-----------------------------------------------


        //--------------------------------Scheduled State Params--------------------------------------------------------
        ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
        Bounds allScreenBounds = computeAllScreenBounds();

        buildVariables.setPeriod(Duration.seconds(10));

        buildVariables.setRestartOnFailure(true);

        buildVariables.start();
        myLatch.await();

        //---------------------------------General Tile Handlers--------------------------------------------------------
        ArrayList<Tile> tileList = new ArrayList<>();

        tileList.add(pos);
        tileList.add(posPercent);
        tileList.add(retail);
        tileList.add(retailPercent);
        tileList.add(servers);
        tileList.add(serversPercent);
        tileList.add(peripherals);
        tileList.add(periphPercent);
        tileList.add(optic);
        tileList.add(opticPercent);

        tileList.add(posBuildFocused);
        tileList.add(posPercentFocused);
        tileList.add(posTestFocused);
        tileList.add(posPercentTestFocused);
        tileList.add(retailBuildFocused);
        tileList.add(retailPercentFocused);
        tileList.add(serversBuildFocused);
        tileList.add(serversPercentFocused);
        tileList.add(periphBuildFocused);
        tileList.add(periphPercentFocused);
        tileList.add(opticBuildFocused);
        tileList.add(opticPercentFocused);


        tileList.add(posTest);
        tileList.add(posPercentTest);
        tileList.add(retailTest);
        tileList.add(retailPercentTest);
        tileList.add(serversTest);
        tileList.add(serversPercentTest);
        tileList.add(peripheralsTest);
        tileList.add(periphPercentTest);
        tileList.add(opticTest);
        tileList.add(opticPercentTest);

        tileList.add(posTestFocused);
        tileList.add(posPercentTestFocused);
        tileList.add(retailTestFocused);
        tileList.add(retailPercentTestFocused);
        tileList.add(serversTestFocused);
        tileList.add(serversPercentTestFocused);
        tileList.add(periphTestFocused);
        tileList.add(periphPercentTestFocused);
        tileList.add(opticTestFocused);
        tileList.add(opticPercentTestFocused);


        tileList.add(posStage);
        tileList.add(posPercentStage);
        tileList.add(retailStage);
        tileList.add(retailPercentStage);
        tileList.add(serversStage);
        tileList.add(serversPercentStage);
        tileList.add(peripheralsStage);
        tileList.add(periphPercentStage);

        tileList.add(posStageFocused);
        tileList.add(posPercentStageFocused);
        tileList.add(retailStageFocused);
        tileList.add(retailPercentStageFocused);
        tileList.add(serversStageFocused);
        tileList.add(serversPercentStageFocused);
        tileList.add(periphStageFocused);
        tileList.add(periphPercentStageFocused);


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

        //---------------------------------Unique Tile Handlers---------------------------------------------------------


        //---------------------------------Scene and Pane Creation------------------------------------------------------
        flowPane.getChildren().addAll(pos,retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent, periphPercent,opticPercent);
        flowPaneTest.getChildren().addAll(posTest,retailTest, serversTest, peripheralsTest, opticTest, posPercentTest, retailPercentTest,serversPercentTest, periphPercentTest,opticPercentTest);
        flowPaneStage.getChildren().addAll(posStage,retailStage, serversStage, peripheralsStage, posPercentStage, retailPercentStage,serversPercentStage, periphPercentStage);
        flowPanePOSBuildOrTestFocus.getChildren().addAll(posBuildFocused,posTestFocused,posPercentFocused,posPercentTestFocused);
        flowPaneRetailBuildOrTestFocus.getChildren().addAll(retailBuildFocused,retailTestFocused,retailPercentFocused,retailPercentTestFocused);
        flowPaneServersBuildOrTestFocus.getChildren().addAll(serversBuildFocused,serversTestFocused,serversPercentFocused,serversPercentTestFocused);
        flowPanePeriphBuildOrTestFocus.getChildren().addAll(periphBuildFocused,periphTestFocused,periphPercentFocused,periphPercentTestFocused);
        flowPaneOpticBuildOrTestFocus.getChildren().addAll(opticBuildFocused,opticTestFocused,opticPercentFocused,opticPercentTestFocused);
        flowPanePOSStageFocus.getChildren().addAll(posStageFocused);
        flowPaneRetailStageFocus.getChildren().addAll(retailStageFocused);
        flowPaneServersStageFocus.getChildren().addAll(serversStageFocused);
        flowPanePeriphStageFocus.getChildren().addAll(periphStageFocused);



        ArrayList<FlowPane> flowList = new ArrayList<>();

        flowList.add(flowPane);
        flowList.add(flowPaneTest);
        flowList.add(flowPaneStage);
        flowList.add(flowPanePOSBuildOrTestFocus);
        flowList.add(flowPaneRetailBuildOrTestFocus);
        flowList.add(flowPaneServersBuildOrTestFocus);
        flowList.add(flowPanePeriphBuildOrTestFocus);
        flowList.add(flowPaneOpticBuildOrTestFocus);
        flowList.add(flowPanePOSStageFocus);
        flowList.add(flowPaneRetailStageFocus);
        flowList.add(flowPaneServersStageFocus);
        flowList.add(flowPanePeriphStageFocus);

        for(int i = 0; i < flowList.size();i++)
        {
            flowList.get(i).setStyle("-fx-background-color: rgb(42, 42, 42)");
            flowList.get(i).setPrefSize(1920, 1080);
        }

        Scene buildScene = new Scene(flowPane);
        Scene testScene = new Scene (flowPaneTest);
        Scene stageScene = new Scene (flowPaneStage);
        Scene posBuildOrTestScene = new Scene(flowPanePOSBuildOrTestFocus);
        Scene retailBuildOrTestScene = new Scene(flowPaneRetailBuildOrTestFocus);
        Scene serversBuildOrTestScene = new Scene(flowPaneServersBuildOrTestFocus);
        Scene periphBuildOrTestScene = new Scene(flowPanePeriphBuildOrTestFocus);
        Scene opticBuildOrTestScene = new Scene(flowPaneOpticBuildOrTestFocus);
        Scene posStageScene = new Scene(flowPanePOSStageFocus);
        Scene retailStageScene = new Scene(flowPaneRetailStageFocus);
        Scene serversStageScene = new Scene(flowPaneServersStageFocus);
        Scene periphStageScene = new Scene(flowPanePeriphStageFocus);
        Scene posUserScene = new Scene(posGridPane);
        Scene retailUserScene = new Scene(retailGridPane);
        Scene serversUserScene = new Scene(serversGridPane);
        Scene periphUserScene = new Scene(periphGridPane);

        final Scene[] previousScene = new Scene[1];

        ArrayList<Scene> sceneList = new ArrayList<>();
        sceneList.add(buildScene);
        sceneList.add(testScene);
        sceneList.add(stageScene);
        //sceneList.add(clockOrQualityScene);

        //---------------------------------General Scene Handlers-------------------------------------------------------
        int previous = 0;
        int next = 0;
        for(int i = 0; i<sceneList.size();i++)
        {
            if(i != 0 && (i+1) < sceneList.size())
            {
                previous = i-1;
                next = i + 1;
            }
            if(i == 0 && (i+1) <sceneList.size())
            {
                next = i + 1;
            }
            if(i == sceneList.size()-1)
            {
                previous = i-1;
            }
            int finalPrevious = previous;
            int finalNext = next;
            sceneList.get(i).setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode() == KeyCode.LEFT)
                    {
                        primaryStage.setScene(sceneList.get(finalPrevious));
                    }
                    if(event.getCode() == KeyCode.RIGHT)
                    {
                        primaryStage.setScene(sceneList.get(finalNext));
                    }
                    if (event.getCode() == KeyCode.F4) {
                        primaryStage.setIconified(true);
                    }
                    if (event.getCode() == KeyCode.F5) {
                        screenMove(primaryStage,allScreenBounds,screens);
                    }
                }
            });
        }

        //---------------------------------Unique Scene Handlers--------------------------------------------------------
        pos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = buildScene;
                primaryStage.setScene(posBuildOrTestScene);
            }
        });
        posTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = testScene;
                primaryStage.setScene(posBuildOrTestScene);
            }
        });
        posStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = stageScene;
                primaryStage.setScene(posUserScene);
                posUserScene.setCursor(Cursor.NONE);
            }
        });
        posBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }
            }
        });
        posStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        posUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });

        retail.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = buildScene;
                primaryStage.setScene(retailBuildOrTestScene);
            }
        });
        retailTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = testScene;
                primaryStage.setScene(retailBuildOrTestScene);
            }
        });
        retailStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = stageScene;
                primaryStage.setScene(retailUserScene);
                retailUserScene.setCursor(Cursor.NONE);
            }
        });
        retailBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        retailStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        retailUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });

        servers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = buildScene;
                primaryStage.setScene(serversBuildOrTestScene);
            }
        });
        serversTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = testScene;
                primaryStage.setScene(serversBuildOrTestScene);
            }
        });
        serversStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = stageScene;
                primaryStage.setScene(serversUserScene);
                serversUserScene.setCursor(Cursor.NONE);
            }
        });
        serversBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        serversStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        serversUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });

        peripherals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = buildScene;
                primaryStage.setScene(periphBuildOrTestScene);
            }
        });
        peripheralsTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = testScene;
                primaryStage.setScene(periphBuildOrTestScene);
            }
        });
        peripheralsStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = stageScene;
                primaryStage.setScene(periphUserScene);
                periphUserScene.setCursor(Cursor.NONE);
            }
        });
        periphBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        periphStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });
        periphUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });

        optic.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = buildScene;
                primaryStage.setScene(opticBuildOrTestScene);
            }
        });
        opticTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                previousScene[0] = testScene;
                primaryStage.setScene(opticBuildOrTestScene);
            }
        });
        opticBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.F5) {
                    screenMove(primaryStage,allScreenBounds,screens);
                }
                if(event.getCode() == KeyCode.ESCAPE)
                {
                    primaryStage.setScene(previousScene[0]);
                }
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }

            }
        });


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(buildScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });


    }

    public static void main(String[] args) { launch(args); }

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

    private ArrayList<Tile> getCharTiles (HashMap<String,Integer> userMap, double width, double height)
    {
        ArrayList<Tile> tiles = new ArrayList<>();

        ArrayList<ChartData> chartItems = new ArrayList<>();

        int i = 1;
        for(Map.Entry<String,Integer> entry : userMap.entrySet())
        {
            String user = entry.getKey();
            int total = entry.getValue();

            String formatted = String.format("%02d",total);

            Tile characterTile = TileBuilder.create()
                    .skinType(Tile.SkinType.CHARACTER)
                    .prefSize(width, height)
                    .title(user)
                    .titleAlignment(TextAlignment.CENTER)
                    .description(formatted)
                    .build();

            tiles.add(characterTile);
        }

        return tiles;
    }





    private void changePercent(Tile main, Tile focused, double current, double goal, double total)
    {
        if (Double.compare(total, 60) < 0) {
            main.setBarColor(Tile.RED);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
            focused.setBarColor(Tile.RED);
            focused.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
        if (Double.compare(total, 90) < 0 && Double.compare(total, 60) > 0) {
            main.setBarColor(Tile.YELLOW);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
            focused.setBarColor(Tile.YELLOW);
            focused.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
        if (Double.compare(total, 90) > 0) {
            main.setBarColor(Tile.GREEN);
            main.setUnit(Double.toString(current) + "/" + Double.toString(goal));
            focused.setBarColor(Tile.GREEN);
            focused.setUnit(Double.toString(current) + "/" + Double.toString(goal));
        }
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
}