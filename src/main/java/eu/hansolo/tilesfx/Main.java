package eu.hansolo.tilesfx;

import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.MapTool;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    double kiwi4sGoalBuild;
    double kiwi2XsCurrentBuild;
    double kiwi2XsGoalBuild;
    double bumpBarsCurrentBuild;
    double bumpBarsGoalBuild;
    double pantherEPC4sCurrentBuild;
    double pantherEPC4sGoalBuild;

    double kiwi4sCurrentTest;
    double kiwi4sGoalTest;
    double kiwi2XsCurrentTest;
    double kiwi2XsGoalTest;
    double bumpBarsCurrentTest;
    double bumpBarsGoalTest;
    double pantherEPC4sCurrentTest;
    double pantherEPC4sGoalTest;

    double kiwi4sCurrentStage;
    double kiwi4sGoalStage;
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

    //---------------------------------Variables for Percentages --------------------------------------------------
    GoalTool goalTool;
    double posPercentTotal;
    double retailPercentTotal;
    double opticPercentTotal;
    double serversPercentTotal;
    double periphPercentTotal;

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
        percentageTool = new GoalTool();


        //---------------------------------Scheduled Executors for Updating Variables---------------------------------
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(24);
        Tool dataBaseTool = new Tool();

        //---------------------------------Creating the Tools for the graphs------------------------------------------
        MapTool mapTool = new MapTool();

        //---------------------------------List Storage Area for Running Group Values --------------------------------
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

        ArrayList<String> mediaPlayerProdList = new ArrayList<>();
        mediaPlayerProdList.add("1656");
        mediaPlayerProdList.add("1930");




        //---------------------------------Scheduled Executors for Build Variables-------------------------------------
        final CountDownLatch buildLatch = new CountDownLatch(1);

        System.out.println("Begin Build Queries.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                System.out.println("Running Build Block.\n");

                //---------------------------------Hosp Build-----------------------------------------------------------------------
                posBuildMap = dataBaseTool.hospBuildDataBase();

                p1532CurrentBuild = mapTool.getCurrentSingleValue("7734",posBuildMap);
                p1x30CurrentBuild = mapTool.getCurrentSingleValue("7743",posBuildMap);
                p1x35CurrentBuild = mapTool.getCurrentGroupValue(p1x35ProdList,posBuildMap);
                t1000sCurrentBuild = mapTool.getCurrentSingleValue("7744",posBuildMap);

                //---------------------------------Retail Build-----------------------------------------------------------------------
                retailBuildMap = dataBaseTool.retailBuildDataBase();

                xr7CurrentBuild = mapTool.getCurrentSingleValue("7702",retailBuildMap);
                xr7PlusCurrentBuild = mapTool.getCurrentSingleValue("7703",retailBuildMap);
                xr5CurrentBuild = mapTool.getCurrentSingleValue("7701",retailBuildMap);
                nextGenDisplayCurrentBuild = mapTool.getCurrentGroupValue(nextGenProdList,retailBuildMap);

                //---------------------------------Servers Build-----------------------------------------------------------------------
                serversBuildMap = dataBaseTool.serversBuildDataBase();

                mediaPlayerCurrentBuild = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversBuildMap);
                n3000CurrentBuild = mapTool.getCurrentSingleValue("1657",serversBuildMap);
                s500CurrentBuild = mapTool.getCurrentGroupValue(s500ProdList,serversBuildMap);

                //---------------------------------Periph Build-----------------------------------------------------------------------
                periphBuildMap = dataBaseTool.periphBuildDataBase();

                kiwi4sCurrentBuild = mapTool.getCurrentSingleValue("1924",periphBuildMap);
                kiwi2XsCurrentBuild = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphBuildMap);
                bumpBarsCurrentBuild = mapTool.getCurrentSingleValue("1635",periphBuildMap);
                pantherEPC4sCurrentBuild = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphBuildMap);

                //---------------------------------Optic Build----------------------------------------------------------------------
                opticBuildMap = dataBaseTool.opticBuildDataBase();
                optic5sCurrentBuild =  mapTool.getCurrentSingleValue("6001",opticBuildMap);
                optic12sCurrentBuild = mapTool.getCurrentSingleValue("6002",opticBuildMap);
                kitsCurrentBuild = mapTool.getCurrentSingleValue("6003",opticBuildMap);

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


                mediaPlayerCurrentTest = mapTool.getCurrentGroupValue(mediaPlayerProdList, serversTestMap);
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

                //---------------------------------Hosp Staging-----------------------------------------------------------------------
                posStageMap = dataBaseTool.hospStageDataBase();

                p1532CurrentStage = mapTool.getCurrentSingleValue("7734", posStageMap);
                p1x30CurrentStage = mapTool.getCurrentSingleValue("7743", posStageMap);
                p1x35CurrentStage = mapTool.getCurrentGroupValue(p1x35ProdList, posStageMap);
                t1000sCurrentStage = mapTool.getCurrentSingleValue("7744", posStageMap);

                //---------------------------------Retail Stage-----------------------------------------------------------------------
                retailStageMap = dataBaseTool.retailStageDataBase();

                xr7CurrentStage = mapTool.getCurrentSingleValue("7702", retailBuildMap);
                xr7PlusCurrentStage = mapTool.getCurrentSingleValue("7703", retailBuildMap);
                xr5CurrentStage = mapTool.getCurrentSingleValue("7701", retailBuildMap);
                nextGenDisplayCurrentStage = mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap);

                //---------------------------------Servers Stage-----------------------------------------------------------------------
                serversStageMap = dataBaseTool.serversStageDataBase();

                mediaPlayerCurrentStage = mapTool.getCurrentGroupValue(mediaPlayerProdList, serversStageMap);
                n3000CurrentStage = mapTool.getCurrentSingleValue("1657", serversStageMap);
                s500CurrentStage = mapTool.getCurrentGroupValue(s500ProdList, serversStageMap);

                //---------------------------------Periph Stage-----------------------------------------------------------------------
                periphStageMap = dataBaseTool.periphStageDataBase();

                kiwi4sCurrentStage = mapTool.getCurrentSingleValue("1924",periphStageMap);
                kiwi2XsCurrentStage = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphStageMap);
                bumpBarsCurrentStage = mapTool.getCurrentSingleValue("1635",periphStageMap);
                pantherEPC4sCurrentStage = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphStageMap);


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

                //---------------------------------Goal Set-------------------------------------------------------------------------

                //---------------------------------Hosp Build-----------------------------------------------------------------------

                p1532GoalBuild = goalTool.getGoal(mapList.get(0),"7734");
                p1x30GoalBuild = goalTool.getGoal(mapList.get(0),"7743");
                //p1x35CurrentBuild = mapTool.getCurrentGroupValue(p1x35ProdList,posBuildMap);
                t1000sGoalBuild = goalTool.getGoal(mapList.get(0),"7744");

                //---------------------------------Retail Build-----------------------------------------------------------------------
                xr7GoalBuild = goalTool.getGoal(mapList.get(1),"7702");
                xr7PlusGoalBuild = goalTool.getGoal(mapList.get(1),"7703");
                xr5GoalBuild = goalTool.getGoal(mapList.get(1),"7701");
                //nextGenDisplayCurrentBuild = mapTool.getCurrentGroupValue(nextGenProdList,retailBuildMap);

                //---------------------------------Servers Build-----------------------------------------------------------------------
                ///mediaPlayerCurrentBuild = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversBuildMap);
                n3000GoalBuild = goalTool.getGoal(mapList.get(2),"1657");
                // s500CurrentBuild = mapTool.getCurrentGroupValue(s500ProdList,serversBuildMap);

                //---------------------------------Periph Build-----------------------------------------------------------------------
                kiwi4sGoalBuild = goalTool.getGoal(mapList.get(3),"1924");
                //kiwi2XsCurrentBuild = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphBuildMap);
                bumpBarsGoalBuild = goalTool.getGoal(mapList.get(3),"1635");
                //pantherEPC4sCurrentBuild = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphBuildMap);

                //---------------------------------Optic Build----------------------------------------------------------------------
                optic5sGoalBuild = goalTool.getGoal(mapList.get(4),"6001");
                optic12sGoalBuild = goalTool.getGoal(mapList.get(4),"6002");
                kitsGoalBuild = goalTool.getGoal(mapList.get(4),"6003");

                //---------------------------------Hosp Test-----------------------------------------------------------------------

                p1532GoalTest = goalTool.getGoal(mapList.get(0),"7734");
                p1x30GoalTest = goalTool.getGoal(mapList.get(0),"7743");
                //p1x35CurrentTest = mapTool.getCurrentGroupValue(p1x35ProdList,posBuildMap);
                t1000sGoalTest = goalTool.getGoal(mapList.get(0),"7744");

                //---------------------------------Retail Test-----------------------------------------------------------------------
                xr7GoalTest = goalTool.getGoal(mapList.get(1),"7702");
                xr7PlusGoalTest = goalTool.getGoal(mapList.get(1),"7703");
                xr5GoalTest = goalTool.getGoal(mapList.get(1),"7701");
                //nextGenDisplayCurrentTest = mapTool.getCurrentGroupValue(nextGenProdList,retailBuildMap);

                //---------------------------------Servers Test-----------------------------------------------------------------------
                ///mediaPlayerCurrentTest = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversBuildMap);
                n3000GoalTest = goalTool.getGoal(mapList.get(2),"1657");
                // s500CurrentTest = mapTool.getCurrentGroupValue(s500ProdList,serversBuildMap);

                //---------------------------------Periph Test-----------------------------------------------------------------------
                kiwi4sGoalTest = goalTool.getGoal(mapList.get(3),"1924");
                //kiwi2XsCurrentTest = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphBuildMap);
                bumpBarsGoalTest = goalTool.getGoal(mapList.get(3),"1635");
                //pantherEPC4sCurrentTest = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphBuildMap);

                //---------------------------------Optic Test----------------------------------------------------------------------
                optic5sGoalTest = goalTool.getGoal(mapList.get(4),"6001");
                optic12sGoalTest = goalTool.getGoal(mapList.get(4),"6002");
                kitsGoalTest = goalTool.getGoal(mapList.get(4),"6003");

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

        //---------------------------------Creating the Bar Chart Items for POS---------------------------------------
        BarChartItem p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, Tile.RED);
        BarChartItem p1x35DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem p132Data = new BarChartItem("P1532", p1532CurrentBuild, Tile.GREEN);
        BarChartItem p132DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, Tile.BLUE);
        BarChartItem p1x30DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, Tile.YELLOW);
        BarChartItem t1000Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        //---------------------------------Creating the Bar Chart Items for Servers---------------------------------
        BarChartItem n3000Data = new BarChartItem("N3000", n3000CurrentBuild, Tile.RED);
        BarChartItem n3000Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        BarChartItem s500Data = new BarChartItem("S500", s500CurrentBuild, Tile.BLUE);
        BarChartItem s500Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, Tile.GREEN);
        BarChartItem mediaGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        //---------------------------------Creating the Bar Chart Items for Peripherals-----------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, Tile.BLUE);
        BarChartItem kiwi4Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2.5", kiwi2XsCurrentBuild, Tile.RED);
        BarChartItem kiwi25Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, Tile.GREEN);
        BarChartItem bumpBarGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        //---------------------------------Creating the Bar Chart Items for Optic-----------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, Tile.RED);
        BarChartItem optic12Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, Tile.BLUE);
        BarChartItem optic5Goal = new BarChartItem("Goal", 100, Tile.BACKGROUND);

        BarChartItem opticKitsData = new BarChartItem("Optic Kits", kitsCurrentBuild, Tile.GREEN);
        BarChartItem opticKitsGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        BarChartItem xr5Data = new BarChartItem("7701", xr5CurrentBuild, Tile.BLUE);
        BarChartItem xr5DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem xr7Data = new BarChartItem("7702", xr7CurrentBuild, Tile.RED);
        BarChartItem xr7DataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


        BarChartItem xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, Tile.GREEN);
        BarChartItem xr7PlusDataGoal = new BarChartItem("Goal", 100, Tile.BACKGROUND);


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
                .titleAlignment(TextAlignment.CENTER)
                .build();

        Tile posPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(50.0)
                .build();


        //---------------------------------Creating the Bar Chart Items for Servers-----------------------------------
        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(s500Data, s500Goal, n3000Data, n3000Goal, mediaPlayer, mediaGoal)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        Tile serversPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(50.0)
                .build();


        //---------------------------------Creating the Bar Chart for Peripherals-----------------------------------
        peripherals = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(kiwi4Data, kiwi4Goal, kiwi25Data,kiwi25Goal, bumpBarData, bumpBarGoal, pantherEPC4Data,pantherEPC4Goal)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        Tile periphPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(50.0)
                .build();


        //---------------------------------Creating the Bar Chart for Optic-----------------------------------
        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5Data, optic5Goal, optic12Data, optic12Goal, opticKitsData,opticKitsGoal)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        Tile opticPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(50.0)
                .build();


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Build")
                .prefSize(384, 640)
                .barChartItems(xr5Data, xr5DataGoal, xr7Data,xr7DataGoal, xr7PlusData, xr7PlusDataGoal, nextGenDisplays, nextGenDisplaysGoal)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        Tile retailPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(50.0)
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

        posPercent.setAnimated(true);
        posPercent.setAnimationDuration(2);
        serversPercent.setAnimated(true);
        serversPercent.setAnimationDuration(2);
        retailPercent.setAnimated(true);
        retailPercent.setAnimationDuration(2);
        periphPercent.setAnimated(true);
        periphPercent.setAnimationDuration(2);
        opticPercent.setAnimated(true);
        opticPercent.setAnimationDuration(2);

        //---------------------------------Creating Color Changes for Percentage Goals----------------------------------




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

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
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
