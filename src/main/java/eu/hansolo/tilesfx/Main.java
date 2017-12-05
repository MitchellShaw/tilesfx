package eu.hansolo.tilesfx;

import eu.hansolo.medusa.*;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.MapTool;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Ramon Johnson & Mitchell Shaw
 * 2017-11-22.
 */
public class Main extends Application
{
    Tile pos;
    Tile servers;
    Tile peripherals;
    Tile optic;
    Tile retail;
    Tile misc;

    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    double p1x30CurrentBuild;
    double p1x30GoalBuild;
    double p1x35CurrentBuild;
    double p1x35GoalBuild;
    double p1532CurrentBuild;
    double p1532GoalBuild;
    double t1000sCurrentBuild;
    double t1000sGoalBuild;
    double questsCurrentBuild;
    double questsGoalBuild;


    double p1x30CurrentTest;
    double p1x30GoalTest;
    double p1x35CurrentTest;
    double p1x35GoalTest;
    double p1532CurrentTest;
    double p1532GoalTest;
    double t1000sCurrentTest;
    double t1000sGoalTest;
    double questsCurrentTest;
    double questsGoalTest;


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
    double xr7GoalTest;
    double xr7PlusCurrentTest;
    double xr7PlusGoalTest;
    double xr5CurrentTest;
    double xr5GoalTest;
    double nextGenDisplayCurrentTest;
    double nextGenDisplayGoalsTest;

    double xr7CurrentStage;
    double xr7GoalStage;
    double xr7PlusCurrentStage;
    double xr7PlusGoalStage;
    double xr5CurrentStage;
    double xr5GoalStage;
    double nextGenDisplayCurrentStage;
    double nextGenDisplayGoalsStage;

    //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    double mediaPlayerCurrentBuild;
    double mediaPlayerGoalBuild;
    double n3000CurrentBuild;
    double n3000GoalBuild;
    double s500CurrentBuild;
    double s500GoalBuild;



    double mediaPlayerCurrentTest;
    double mediaPlayerGoalTest;
    double n3000CurrentTest;
    double n3000GoalTest;
    double s500CurrentTest;
    double s500GoalTest;


    double mediaPlayerCurrentStage;
    double mediaPlayerGoalStage;
    double n3000CurrentStage;
    double n3000GoalStage;
    double s500CurrentStage;
    double s500GoalStage;



    //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    double kiwi4sCurrentBuild;
    double kiwi4sGoalBuildBuild;
    double kiwi2XsCurrentBuild;
    double kiwi2XsGoalBuild;
    double bumpBarsCurrentBuild;
    double bumpBarsGoalBuild;
    double pantherEPC4sCurrentBuild;
    double pantherEPC4sGoalBuild;

    double kiwi4sCurrentTest;
    double kiwi4sGoalBuildTest;
    double kiwi2XsCurrentTest;
    double kiwi2XsGoalTest;
    double bumpBarsCurrentTest;
    double bumpBarsGoalTest;
    double pantherEPC4sCurrentTest;
    double pantherEPC4sGoalTest;

    double kiwi4sCurrentStage;
    double kiwi4sGoalBuildStage;
    double kiwi2XsCurrentStage;
    double kiwi2XsGoalStage;
    double bumpBarsCurrentStage;
    double bumpBarsGoalStage;
    double pantherEPC4sCurrentStage;
    double pantherEPC4sGoalStage;

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
    double optic5sGoalTest;
    double optic12sCurrentTest;
    double optic12sGoalTest;
    double cubCurrentTest;
    double cubGoalTest;
    double kitsCurrentTest;
    double kitsGoalTest;
    double printerCurrentTest;
    double printerGoalTest;

    //---------------------------------Variables for Map Creation for POS Database Call----------------------------
    HashMap<String,Integer> posBuildMap;
    HashMap<String,Integer> posTestMap;
    HashMap<String,Integer> posStageMap;

    //---------------------------------Variables for Map Creation for Retail Database Call-------------------------
    HashMap<String,Integer> retailBuildMap;
    HashMap<String,Integer> retailTestMap;
    HashMap<String,Integer> retailStageMap;

    //---------------------------------Variables for Map Creation for Servers Database Call------------------------
    HashMap<String,Integer> serversBuildMap;
    HashMap<String,Integer> serversTestMap;
    HashMap<String,Integer> serversStageMap;

    //---------------------------------Variables for Map Creation for Peripherals Database Call--------------------
    HashMap<String,Integer> periphBuildMap;
    HashMap<String,Integer> periphTestMap;
    HashMap<String,Integer> periphStageMap;

    //---------------------------------Variables for Map Creation for Optic Database Call----------------------------
    HashMap<String,Integer> opticBuildMap;
    HashMap<String,Integer> opticTestMap;

    //---------------------------------Variables for Map Creation for Document Reader---------------------------------
    ArrayList<HashMap<String,Integer>> mapList;



    public static void main(String[] args)
    {
        launch(args);
    }



    //---------------------------------Application Launcher----------------------------------------------------------
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //---------------------------------Variables for Tiles----------------------------------------------------
        FlowPane flowPane = new FlowPane();


        //---------------------------------Scheduled Executors for Updating Variables---------------------------------
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(24);
        Tool dataBaseTool = new Tool();


        //---------------------------------Scheduled Executors for Build Variables-------------------------------------
        final CountDownLatch buildLatch = new CountDownLatch(1);

        System.out.println("Begin Build Queries.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                System.out.println("Running Build Block.\n");
                posBuildMap = dataBaseTool.hospBuildDataBase();
                retailBuildMap = dataBaseTool.retailBuildDataBase();
                serversBuildMap = dataBaseTool.serversBuildDataBase();
                periphBuildMap = dataBaseTool.periphBuildDataBase();
                opticBuildMap = dataBaseTool.opticBuildDataBase();
                buildLatch.countDown();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }, 0, 300, TimeUnit.SECONDS);
        buildLatch.await();

        //---------------------------------Scheduled Executors for Test Variables-------------------------------------
        final CountDownLatch testLatch = new CountDownLatch(1);

        System.out.println("\nBegin Test Queries.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                System.out.println("Running Test Block.\n");
                posTestMap = dataBaseTool.hospTestDataBase();
                retailTestMap = dataBaseTool.retailTestDataBase();
                serversTestMap = dataBaseTool.serversTestDataBase();
                periphTestMap = dataBaseTool.periphTestDataBase();
                opticTestMap = dataBaseTool.opticTestDataBase();
                testLatch.countDown();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }, 0, 300, TimeUnit.SECONDS);
        testLatch.await();

        //---------------------------------Scheduled Executors for Stage Variables------------------------------------
        final CountDownLatch stageLatch = new CountDownLatch(1);

        System.out.println("\nBegin Stage Queries.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                System.out.println("Running Stage Block.\n");
                posStageMap = dataBaseTool.hospStageDataBase();
                retailStageMap = dataBaseTool.retailStageDataBase();
                serversStageMap = dataBaseTool.serversStageDataBase();
                periphStageMap = dataBaseTool.periphStageDataBase();
                stageLatch.countDown();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }, 0, 300, TimeUnit.SECONDS);
        stageLatch.await();

        //---------------------------------Scheduled Executors for Document Variables------------------------------------
        final CountDownLatch documentLatch = new CountDownLatch(1);

        System.out.println("\nBegin Document Read.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try {
                System.out.println("Running Doc Block.\n");
                mapList = dataBaseTool.documentReader();
                documentLatch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

        }, 0, 30, TimeUnit.SECONDS);
        documentLatch.await();

        //---------------------------------Creating the Chart Data for the graphs-------------------------------------


        //---------------------------------Creating the Tools for the graphs------------------------------------------
        MapTool mapTool = new MapTool();

        //---------------------------------Using the Tools for the graphs---------------------------------------------

        //---------------------------------Optic Build----------------------------------------------------------------------
        optic5sCurrentBuild =  mapTool.getCurrentSingleValue("6001",opticBuildMap);
        optic12sCurrentBuild = mapTool.getCurrentSingleValue("6002",opticBuildMap);
        kitsCurrentBuild = mapTool.getCurrentSingleValue("6003",opticBuildMap);

        //---------------------------------Optic Test----------------------------------------------------------------------
        optic5sCurrentTest =  mapTool.getCurrentSingleValue("6001",opticTestMap);
        optic12sCurrentTest = mapTool.getCurrentSingleValue("6002",opticTestMap);
        kitsCurrentTest = mapTool.getCurrentSingleValue("6003",opticTestMap);

        //---------------------------------Hosp Build-----------------------------------------------------------------------
        p1532CurrentBuild = mapTool.getCurrentSingleValue("7734",posBuildMap);
        p1x30CurrentBuild = mapTool.getCurrentSingleValue("7743",posBuildMap);

        ArrayList<String> p1x35ProdList = new ArrayList<>();
        p1x35ProdList.add("7745");
        p1x35ProdList.add("7761");

        p1x35CurrentBuild = mapTool.getCurrentGroupValue(p1x35ProdList,posBuildMap);

        questsCurrentBuild = mapTool.getCurrentSingleValue("7791",posBuildMap);
        t1000sCurrentBuild = mapTool.getCurrentSingleValue("7744",posBuildMap);

        //---------------------------------Hosp Test-----------------------------------------------------------------------
        p1532CurrentTest = mapTool.getCurrentSingleValue("7734",posTestMap);
        p1x30CurrentTest = mapTool.getCurrentSingleValue("7743",posTestMap);
        p1x35CurrentTest = mapTool.getCurrentGroupValue(p1x35ProdList,posTestMap);
        questsCurrentTest = mapTool.getCurrentSingleValue("7791",posTestMap);
        t1000sCurrentTest = mapTool.getCurrentSingleValue("7744",posTestMap);

        //---------------------------------Hosp Staging-----------------------------------------------------------------------
        p1532CurrentStage = mapTool.getCurrentSingleValue("7734",posStageMap);
        p1x30CurrentStage = mapTool.getCurrentSingleValue("7743",posStageMap);
        p1x35CurrentStage = mapTool.getCurrentGroupValue(p1x35ProdList,posStageMap);
        questsCurrentStage = mapTool.getCurrentSingleValue("7791",posStageMap);
        t1000sCurrentStage = mapTool.getCurrentSingleValue("7744",posStageMap);

        //---------------------------------Periph Build-----------------------------------------------------------------------
        kiwi4sCurrentBuild = mapTool.getCurrentSingleValue("1924",periphBuildMap);

        ArrayList<String> kiwi2XsProdList = new ArrayList<>();
        kiwi2XsProdList.add("1640");
        kiwi2XsProdList.add("1642");

        kiwi2XsCurrentBuild = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphBuildMap);
        bumpBarsCurrentBuild = mapTool.getCurrentSingleValue("1635",periphBuildMap);

        ArrayList<String> pantherEPC4sProdList = new ArrayList<>();
        pantherEPC4sProdList.add("1646");
        pantherEPC4sProdList.add("1651");

        pantherEPC4sCurrentBuild = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphBuildMap);

        //---------------------------------Periph Test-----------------------------------------------------------------------
        kiwi4sCurrentTest = mapTool.getCurrentSingleValue("1924",periphTestMap);
        kiwi2XsCurrentTest = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphTestMap);
        bumpBarsCurrentTest = mapTool.getCurrentSingleValue("1635",periphTestMap);
        pantherEPC4sCurrentTest = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphTestMap);

        //---------------------------------Periph Stage-----------------------------------------------------------------------
        kiwi4sCurrentStage = mapTool.getCurrentSingleValue("1924",periphStageMap);
        kiwi2XsCurrentStage = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphStageMap);
        bumpBarsCurrentStage = mapTool.getCurrentSingleValue("1635",periphStageMap);
        pantherEPC4sCurrentStage = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphStageMap);

        //---------------------------------Retail Build-----------------------------------------------------------------------
        xr7CurrentBuild = mapTool.getCurrentSingleValue("7702",retailBuildMap);
        xr7PlusCurrentBuild = mapTool.getCurrentSingleValue("7703",retailBuildMap);
        xr5CurrentBuild = mapTool.getCurrentSingleValue("7701",retailBuildMap);

        ArrayList<String> nextGenProdList = new ArrayList<>();
        nextGenProdList.add("5968");
        nextGenProdList.add("5985");

        nextGenDisplayCurrentBuild = mapTool.getCurrentGroupValue(nextGenProdList,retailBuildMap);

        //---------------------------------Retail Test-----------------------------------------------------------------------
        xr7CurrentTest = mapTool.getCurrentSingleValue("7702",retailBuildMap);
        xr7PlusCurrentTest = mapTool.getCurrentSingleValue("7703",retailBuildMap);
        xr5CurrentTest = mapTool.getCurrentSingleValue("7701",retailBuildMap);
        nextGenDisplayCurrentTest = mapTool.getCurrentGroupValue(nextGenProdList,retailStageMap);

        //---------------------------------Retail Stage-----------------------------------------------------------------------
        xr7CurrentStage = mapTool.getCurrentSingleValue("7702",retailBuildMap);
        xr7PlusCurrentStage = mapTool.getCurrentSingleValue("7703",retailBuildMap);
        xr5CurrentStage = mapTool.getCurrentSingleValue("7701",retailBuildMap);
        nextGenDisplayCurrentStage = mapTool.getCurrentGroupValue(nextGenProdList,retailStageMap);

        //---------------------------------Servers Build-----------------------------------------------------------------------
        ArrayList<String> s500ProdList = new ArrayList<>();
        s500ProdList.add("1611");
        s500ProdList.add("1612");


        ArrayList<String> mediaPlayerProdList = new ArrayList<>();
        mediaPlayerProdList.add("1656");
        mediaPlayerProdList.add("1930");

        mediaPlayerCurrentBuild = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversBuildMap);
        n3000CurrentBuild = mapTool.getCurrentSingleValue("1657",serversBuildMap);
        s500CurrentBuild = mapTool.getCurrentGroupValue(s500ProdList,serversBuildMap);

        mediaPlayerCurrentTest = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversTestMap);
        n3000CurrentTest = mapTool.getCurrentSingleValue("1657",serversTestMap);
        s500CurrentTest = mapTool.getCurrentGroupValue(s500ProdList,serversTestMap);

        mediaPlayerCurrentStage = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversStageMap);
        n3000CurrentStage = mapTool.getCurrentSingleValue("1657",serversStageMap);
        s500CurrentStage = mapTool.getCurrentGroupValue(s500ProdList,serversStageMap);


        //---------------------------------Creating the Bar Chart Items for POS---------------------------------------
        BarChartItem p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, Tile.RED);
        BarChartItem p1x35DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem p132Data = new BarChartItem("P1532", p1532CurrentBuild, Tile.BLUE);
        BarChartItem p132DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, Tile.GREEN);
        BarChartItem p1x30DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, Tile.YELLOW);
        BarChartItem t1000Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        //---------------------------------Creating the Bar Chart Items for Servers---------------------------------
        BarChartItem n3000Data = new BarChartItem("N3000", n3000CurrentBuild, Tile.RED);
        BarChartItem n3000Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        BarChartItem s500Data = new BarChartItem("S500", s500CurrentBuild, Tile.GREEN);
        BarChartItem s500Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, Tile.BLUE);
        BarChartItem mediaGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        //---------------------------------Creating the Bar Chart Items for Peripherals-----------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, Tile.RED);
        BarChartItem kiwi4Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2.5", kiwi2XsCurrentBuild, Tile.GREEN);
        BarChartItem kiwi25Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, Tile.BLUE);
        BarChartItem bumpBarGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        //---------------------------------Creating the Bar Chart Items for Optic-----------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, Tile.RED);
        BarChartItem optic12Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, Tile.GREEN);
        BarChartItem optic5Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        BarChartItem opticKitsData = new BarChartItem("Optic Kits", kitsCurrentBuild, Tile.BLUE);
        BarChartItem opticKitsGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        BarChartItem xr7Data = new BarChartItem("7702", xr7CurrentBuild, Tile.RED);
        BarChartItem xr7DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, Tile.GREEN);
        BarChartItem xr7PlusDataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem xr5Data = new BarChartItem("7701", xr5CurrentBuild, Tile.BLUE);
        BarChartItem xr5DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        //---------------------------------Creating the Bar Chart for POS-----------------------------------
        pos = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Point of Sales Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(p1x30Data, p1x30DataGoal, p1x35Data, p1x35DataGoal, p132Data, p132DataGoal, t1000Data,t1000Goal)
                .decimals(0)
                .build();

        Gauge posGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
                //.skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("POS")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile posPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(true)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(posGauge)
                .build();


        //---------------------------------Creating the Bar Chart Items for Servers-----------------------------------
        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(s500Data, s500Goal, n3000Data, n3000Goal, mediaPlayer, mediaGoal)
                .decimals(0)
                .build();

        Gauge serversGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Servers")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile serversPercent = TileBuilder.create()
                .prefSize(384,440 )
                .skinType(Tile.SkinType.CUSTOM)
                .roundedCorners(true)
                .text("Percentage to Goal")
                .graphic(serversGauge)
                .build();


        //---------------------------------Creating the Bar Chart for Peripherals-----------------------------------
        peripherals = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(kiwi4Data, kiwi4Goal, kiwi25Data,kiwi25Goal, bumpBarData, bumpBarGoal, pantherEPC4Data,pantherEPC4Goal)
                .decimals(0)
                .build();

        Gauge periphGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Peripherals")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile periphPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(true)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(periphGauge)
                .build();


        //---------------------------------Creating the Bar Chart for Optic-----------------------------------
        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5Data, optic5Goal, optic12Data, optic12Goal, opticKitsData,opticKitsGoal)
                .decimals(0)
                .build();

        Gauge opticGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Optic")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile opticPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(true)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(opticGauge)
                .build();


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Build")
                .prefSize(384, 640)
                .barChartItems(xr5Data, xr5DataGoal, xr7Data,xr7DataGoal, xr7PlusData, xr7PlusDataGoal, nextGenDisplays, nextGenDisplaysGoal)
                .decimals(0)
                .build();

        Gauge retailGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Retail")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile retailPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(true)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(retailGauge)
                .build();

        //---------------------------------Creating Animations for Graphs------------------------------------------
        pos.setAnimated(true);
        pos.setAnimationDuration(2);
        servers.setAnimated(true);
        servers.setAnimationDuration(2);
        retail.setAnimated(true);
        retail.setAnimationDuration(2);
        peripherals.setAnimated(true);
        peripherals.setAnimationDuration(2);
        optic.setAnimated(true);
        optic.setAnimationDuration(2);

        //---------------------------------------------------------------------------------------------------------

        flowPane.getChildren().addAll(pos,retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent,periphPercent,opticPercent);

        flowPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        Scene scene = new Scene(flowPane);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F5)
                {
                    primaryStage.setFullScreen(true);
                    primaryStage.setFullScreenExitHint("");
                }
            }
        });


        primaryStage.setMaximized(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

        /*
        radialChart = TileBuilder.create().skinType(Tile.SkinType.RADIAL_CHART)
                //.prefSize(250,250)
                .title("BarChart")
                .text("Production")
                .prefSize(384,270)
                .chartData(hospitalityData, retailData, pcrData, peripheralsData)
                .decimals(0)
                .build();

        radialChart.setAnimated(true);
        radialChart.setAnimationDuration(2);

        donutChart = TileBuilder.create().skinType(Tile.SkinType.DONUT_CHART)
                //.prefSize(250,250)
                .title("BarChart")
                .text("Production")
                .prefSize(384,270)
                .chartData(hospitalityData, retailData, pcrData, peripheralsData)
                .decimals(0)
                .maxValue(700)
                .minValue(120)
                .build();

        donutChart.setAnimated(true);
        donutChart.setAnimationDuration(2);

        mccChart = TileBuilder.create()
                .skinType(Tile.SkinType.RADIAL_CHART)
                .title("MCC Production")
                .prefSize(384, 270)
                .chartData(mccOpen, mccTested, mccClosed)
                .maxValue(400)
                .minValue(100)
                .decimals(0)
                .text("Today's Production")
                .build();

        mccChart.setAnimated(true);
        mccChart.setAnimationDuration(2);*/




        /*Tile stockTile;

        stockTile = TileBuilder.create()
                .skinType(Tile.SkinType.STOCK)
                .prefSize(384,270)
                .title("NCR Stock")
                .minValue(20)
                .maxValue(40)
                .value(30)
                .unit("$")
                .averagingPeriod(100)
                .build();

        stockTile.setAnimated(true);
        stockTile.setAnimationDuration(2);

        Gauge pastDueGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(700)
                .unit("MCC's")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        //slimGauge.setSections(new Section(1,250, "UA" ,Tile.BLUE), new Section(251,500,"A", Tile.RED));

        Tile medusaDashboard = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Raw Material")
                .text("MCC's Older than 30 days")
                .graphic(pastDueGauge)
                .build();

        Tile safetyDashboard = TileBuilder.create().skinType(Tile.SkinType.NUMBER)
                .prefSize(384,270)
                .title("Safety")
                .description("\n# of days since last incident")
                .textVisible(true)
                .maxValue(200)
                .decimals(0)
                .value(150)
                .text("Safety Safety Safety")
                .build();

        Tile assignedJobs = TileBuilder.create()
                .skinType(Tile.SkinType.NUMBER)
                .prefSize(384,270)
                .title("Unassigned Jobs")
                .description("jobs not assigned to an order")
                .textVisible(true)
                .value(50)
                .maxValue(2000)
                .decimals(0)
                .text("Alert scheduling if number greater than 0")
                .build();

        Clock slimClock = ClockBuilder.create()
                .prefSize(384, 270)
                .skinType(Clock.ClockSkinType.SLIM)
                .secondColor(Tile.FOREGROUND)
                .minuteColor(Tile.BLUE)
                .hourColor(Tile.FOREGROUND)
                .dateColor(Tile.FOREGROUND)
                .running(true)
                .build();

        Tile slimClockTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Time")
                .graphic(slimClock)
                .textVisible(false)
                .build();

        Tile quoteTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.TEXT)
                .title("Quote of the Week")
                .description("\nI see only my objective,\n\nall obstacles must give way")
                .text("Napoleon Bonaparte")
                .textVisible(true)
                .build();

        Gauge slimGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(250)
                .unit("past dues")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Color.DARKRED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        //slimGauge.setSections(new Section(1,250, "UA" ,Tile.BLUE), new Section(251,500,"A", Tile.RED));

        Tile slimTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Medusa Slim")
                .text("Past Dues")
                .graphic(slimGauge)
                .build();*/


//flowPane.getChildren().addAll(pos, retail, servers, peripherals, optic, misc, donutChart, slimTile, medusaDashboard, slimClockTile, safetyDashboard, radialChart, quoteTile, mccChart, assignedJobs);
