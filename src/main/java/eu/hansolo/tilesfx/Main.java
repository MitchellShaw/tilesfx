package eu.hansolo.tilesfx;

import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.MapTool;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.application.Application;
import javafx.application.Platform;
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
 * Created by Mitchell Shaw
 * Framework by hansolo
 * 2017-11-22.
 */
public class Main extends Application
{
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

    double posTotalGoalBuild;
    double posTotalGoalTest;
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

    double retailTotalGoalBuild;
    double retailTotalGoalTest;
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

    double serverGoalTotalBuild;
    double serverGoalTotalTest;
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

    double periphGoalTotalBuild;
    double periphGoalTotalTest;
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
    double optic5sGoalTest;
    double optic12sCurrentTest;
    double optic12sGoalTest;
    double cubCurrentTest;
    double cubGoalTest;
    double kitsCurrentTest;
    double kitsGoalTest;
    double printerCurrentTest;
    double printerGoalTest;

    double opticGoalTotalBuild;
    double opticGoalTotalTest;
    double opticGoalTotalStage;

    double opticCurrentTotalBuild;
    double opticCurrentTotalTest;
    double opticCurrentTotalStage;

    //---------------------------------Variables for Percentages --------------------------------------------------
    GoalTool goalTool;

    double posPercentTotalBuild;
    double retailPercentTotalBuild;
    double opticPercentTotalBuild;
    double serversPercentTotalBuild;
    double periphPercentTotalBuild;


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

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //---------------------------------Variables for Tiles----------------------------------------------------------
        FlowPane flowPane = new FlowPane();
         goalTool = new GoalTool();

        //---------------------------------Scheduled Executors for Updating Variables-----------------------------------
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        Tool dataBaseTool = new Tool();

        //---------------------------------Creating the Tools for the graphs--------------------------------------------
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

        ArrayList<String> mediaPlayerProdList = new ArrayList<>();
        mediaPlayerProdList.add("1656");
        mediaPlayerProdList.add("1930");

        //---------------------------------Scheduled Executors for Build Variables--------------------------------------
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

        //---------------------------------Scheduled Executors for Document Variables and Goal Set----------------------
        final CountDownLatch documentLatch = new CountDownLatch(1);

        System.out.println("\nBegin Document Read.\n");
        executorService.scheduleAtFixedRate(() ->
        {
            try {
                System.out.println("Running Doc Block.\n");
                mapList = dataBaseTool.documentReader();

                p1532GoalBuild = goalTool.getGoal(mapList.get(0),"7734");
                p1x30GoalBuild = goalTool.getGoal(mapList.get(0),"7743");
                p1x35GoalBuild = goalTool.getListGoal(mapList.get(0),p1x35ProdList);
                t1000sGoalBuild = goalTool.getGoal(mapList.get(0),"7744");

                //---------------------------------Percent Calculation and Update for POS-------------------------------
                posTotalGoalBuild = p1532GoalBuild + p1x30GoalBuild + p1x35GoalBuild + t1000sGoalBuild;
                posTotalCurrentBuild = p1532CurrentBuild + p1x35CurrentBuild + p1x35CurrentBuild + t1000sCurrentBuild;
                posPercentTotalBuild = goalTool.getPercentTotal(posTotalCurrentBuild,posTotalGoalBuild);

                //---------------------------------Retail Build---------------------------------------------------------
                xr7GoalBuild = goalTool.getGoal(mapList.get(4),"7702");
                xr7PlusGoalBuild = goalTool.getGoal(mapList.get(4),"7703");
                xr5GoalBuild = goalTool.getGoal(mapList.get(4),"7701");
                nextGenDisplayGoalsBuild = goalTool.getListGoal(mapList.get(4),nextGenProdList);

                //---------------------------------Percent Calculation and Update for Retail----------------------------
                retailTotalGoalBuild = xr7GoalBuild + xr7PlusGoalBuild + xr5GoalBuild + nextGenDisplayGoalsBuild;
                retailTotalCurrentBuild = xr7CurrentBuild + xr7PlusCurrentBuild + xr5CurrentBuild + nextGenDisplayCurrentBuild;
                retailPercentTotalBuild = goalTool.getPercentTotal(retailTotalCurrentBuild,retailTotalGoalBuild);

                //---------------------------------Servers Build--------------------------------------------------------
                mediaPlayerGoalBuild = goalTool.getListGoal(mapList.get(1),mediaPlayerProdList);
                n3000GoalBuild = goalTool.getGoal(mapList.get(1),"1657");
                s500GoalBuild = goalTool.getGoal(mapList.get(1),"1611");

                //---------------------------------Percent Calculation and Update for Servers---------------------------
                serverGoalTotalBuild = mediaPlayerGoalBuild + n3000GoalBuild + s500GoalBuild;
                serverCurrentBuild = mediaPlayerCurrentBuild + n3000CurrentBuild + s500CurrentBuild;
                serversPercentTotalBuild = goalTool.getPercentTotal(serverCurrentBuild,serverGoalTotalBuild);

                //---------------------------------Periph Build---------------------------------------------------------
                kiwi4sGoalBuild = goalTool.getGoal(mapList.get(3),"1924");
                kiwi2XsGoalBuild = goalTool.getListGoal(mapList.get(3),kiwi2XsProdList);
                bumpBarsGoalBuild = goalTool.getGoal(mapList.get(3),"1635");
                pantherEPC4sGoalBuild = goalTool.getListGoal(mapList.get(3),pantherEPC4sProdList);

                //---------------------------------Percent Calculation and Update for Periph----------------------------
                periphGoalTotalBuild = kiwi4sGoalBuild + kiwi2XsGoalBuild + bumpBarsGoalBuild + pantherEPC4sGoalBuild;
                periphCurrentTotalBuild = kiwi4sCurrentBuild + kiwi2XsCurrentBuild + bumpBarsCurrentBuild + pantherEPC4sCurrentBuild;
                periphPercentTotalBuild = goalTool.getPercentTotal(periphCurrentTotalBuild,periphGoalTotalBuild);

                //---------------------------------Optic Build----------------------------------------------------------
                optic5sGoalBuild = goalTool.getGoal(mapList.get(2),"6001");
                optic12sGoalBuild = goalTool.getGoal(mapList.get(2),"6002");
                kitsGoalBuild = goalTool.getGoal(mapList.get(2),"6003");

                //---------------------------------Percent Calculation and Update for Optic-----------------------------
                opticGoalTotalBuild = optic5sGoalBuild + optic12sGoalBuild + kitsGoalBuild;
                opticCurrentTotalBuild = optic5sCurrentBuild + optic12sCurrentBuild + kitsCurrentBuild;
                opticPercentTotalBuild = goalTool.getPercentTotal(opticCurrentTotalBuild,opticGoalTotalBuild);

                //---------------------------------Hosp Test------------------------------------------------------------

                p1532GoalTest = goalTool.getGoal(mapList.get(0),"7734");
                p1x30GoalTest = goalTool.getGoal(mapList.get(0),"7743");
                p1x35GoalTest = goalTool.getListGoal(mapList.get(0),p1x35ProdList);
                t1000sGoalTest = goalTool.getGoal(mapList.get(0),"7744");

                posTotalGoalTest = p1532GoalTest + p1x30GoalTest + p1x35GoalTest + t1000sGoalTest;

                //---------------------------------Retail Test----------------------------------------------------------
                xr7GoalTest = goalTool.getGoal(mapList.get(4),"7702");
                xr7PlusGoalTest = goalTool.getGoal(mapList.get(4),"7703");
                xr5GoalTest = goalTool.getGoal(mapList.get(4),"7701");
                nextGenDisplayGoalsTest = goalTool.getListGoal(mapList.get(4),nextGenProdList);

                retailTotalGoalTest = xr7GoalTest + xr7PlusGoalTest + xr5GoalTest + nextGenDisplayGoalsTest;

                //---------------------------------Servers Test---------------------------------------------------------
                mediaPlayerCurrentTest= goalTool.getListGoal(mapList.get(1),mediaPlayerProdList);
                n3000GoalTest = goalTool.getGoal(mapList.get(1),"1657");
                s500GoalTest =  goalTool.getGoal(mapList.get(1),"1611");

                serverGoalTotalTest = mediaPlayerGoalTest + n3000GoalTest + s500GoalTest;

                //---------------------------------Periph Test----------------------------------------------------------
                kiwi4sGoalTest = goalTool.getGoal(mapList.get(3),"1924");
                kiwi2XsGoalTest = goalTool.getListGoal(mapList.get(3),kiwi2XsProdList);
                bumpBarsGoalTest = goalTool.getGoal(mapList.get(3),"1635");
                pantherEPC4sGoalTest = goalTool.getListGoal(mapList.get(3),pantherEPC4sProdList);

                periphGoalTotalTest = kiwi4sGoalTest + kiwi2XsGoalTest+ bumpBarsGoalTest + pantherEPC4sGoalTest;

                //---------------------------------Optic Test-----------------------------------------------------------
                optic5sGoalTest = goalTool.getGoal(mapList.get(2),"6001");
                optic12sGoalTest = goalTool.getGoal(mapList.get(2),"6002");
                kitsGoalTest = goalTool.getGoal(mapList.get(2),"6003");

                opticGoalTotalTest = optic5sGoalTest + optic12sGoalTest + kitsGoalTest;

                documentLatch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

        }, 0, 10, TimeUnit.SECONDS);
        documentLatch.await();

//      //---------------------------------Scheduled Executors for Test Variables---------------------------------------
//      final CountDownLatch testLatch = new CountDownLatch(1);
//
//        System.out.println("\nBegin Test Queries.\n");
//        executorService.scheduleAtFixedRate(() ->
//        {
//            try
//            {
//                System.out.println("Running Test Block.\n");
//                //---------------------------------Hosp Test-----------------------------------------------------------------------
//                posTestMap = dataBaseTool.hospTestDataBase();
//
//                p1532CurrentTest = mapTool.getCurrentSingleValue("7734", posTestMap);
//                p1x30CurrentTest = mapTool.getCurrentSingleValue("7743", posTestMap);
//                p1x35CurrentTest = mapTool.getCurrentGroupValue(p1x35ProdList, posTestMap);
//                t1000sCurrentTest = mapTool.getCurrentSingleValue("7744", posTestMap);
//
//                //---------------------------------Retail Test-----------------------------------------------------------------------
//                retailTestMap = dataBaseTool.retailTestDataBase();
//
//                xr7CurrentTest = mapTool.getCurrentSingleValue("7702", retailTestMap);
//                xr7PlusCurrentTest = mapTool.getCurrentSingleValue("7703", retailTestMap);
//                xr5CurrentTest = mapTool.getCurrentSingleValue("7701", retailTestMap);
//                nextGenDisplayCurrentTest = mapTool.getCurrentGroupValue(nextGenProdList, retailTestMap);
//
//                //---------------------------------Server Test-----------------------------------------------------------------------
//                serversTestMap = dataBaseTool.serversTestDataBase();
//
//
//                mediaPlayerCurrentTest = mapTool.getCurrentGroupValue(mediaPlayerProdList, serversTestMap);
//                n3000CurrentTest = mapTool.getCurrentSingleValue("1657", serversTestMap);
//                s500CurrentTest = mapTool.getCurrentGroupValue(s500ProdList, serversTestMap);
//
//                //---------------------------------Periph Test-----------------------------------------------------------------------
//                periphTestMap = dataBaseTool.periphTestDataBase();
//
//
//                kiwi4sCurrentTest = mapTool.getCurrentSingleValue("1924", periphTestMap);
//                kiwi2XsCurrentTest = mapTool.getCurrentGroupValue(kiwi2XsProdList, periphTestMap);
//                bumpBarsCurrentTest = mapTool.getCurrentSingleValue("1635", periphTestMap);
//                pantherEPC4sCurrentTest = mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphTestMap);
//
//                //---------------------------------Optic Test----------------------------------------------------------------------
//                opticTestMap = dataBaseTool.opticTestDataBase();
//
//
//                optic5sCurrentTest = mapTool.getCurrentSingleValue("6001", opticTestMap);
//                optic12sCurrentTest = mapTool.getCurrentSingleValue("6002", opticTestMap);
//                kitsCurrentTest = mapTool.getCurrentSingleValue("6003", opticTestMap);
//
//                testLatch.countDown();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }, 0, 300, TimeUnit.SECONDS);
//        testLatch.await();
//
//      //---------------------------------Scheduled Executors for Stage Variables--------------------------------------
//      final CountDownLatch stageLatch = new CountDownLatch(1);
//
//        System.out.println("\nBegin Stage Queries.\n");
//        executorService.scheduleAtFixedRate(() ->
//        {
//            try
//            {
//                System.out.println("Running Stage Block.\n");
//
//                //---------------------------------Hosp Staging-----------------------------------------------------------------------
//                posStageMap = dataBaseTool.hospStageDataBase();
//
//                p1532CurrentStage = mapTool.getCurrentSingleValue("7734", posStageMap);
//                p1x30CurrentStage = mapTool.getCurrentSingleValue("7743", posStageMap);
//                p1x35CurrentStage = mapTool.getCurrentGroupValue(p1x35ProdList, posStageMap);
//                t1000sCurrentStage = mapTool.getCurrentSingleValue("7744", posStageMap);
//
//                //---------------------------------Retail Stage-----------------------------------------------------------------------
//                retailStageMap = dataBaseTool.retailStageDataBase();
//
//                xr7CurrentStage = mapTool.getCurrentSingleValue("7702", retailBuildMap);
//                xr7PlusCurrentStage = mapTool.getCurrentSingleValue("7703", retailBuildMap);
//                xr5CurrentStage = mapTool.getCurrentSingleValue("7701", retailBuildMap);
//                nextGenDisplayCurrentStage = mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap);
//
//                //---------------------------------Servers Stage-----------------------------------------------------------------------
//                serversStageMap = dataBaseTool.serversStageDataBase();
//
//                mediaPlayerCurrentStage = mapTool.getCurrentGroupValue(mediaPlayerProdList, serversStageMap);
//                n3000CurrentStage = mapTool.getCurrentSingleValue("1657", serversStageMap);
//                s500CurrentStage = mapTool.getCurrentGroupValue(s500ProdList, serversStageMap);
//
//                //---------------------------------Periph Stage-----------------------------------------------------------------------
//                periphStageMap = dataBaseTool.periphStageDataBase();
//
//                kiwi4sCurrentStage = mapTool.getCurrentSingleValue("1924",periphStageMap);
//                kiwi2XsCurrentStage = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphStageMap);
//                bumpBarsCurrentStage = mapTool.getCurrentSingleValue("1635",periphStageMap);
//                pantherEPC4sCurrentStage = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphStageMap);
//
//
//                stageLatch.countDown();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }, 0, 300, TimeUnit.SECONDS);
//        stageLatch.await();

        //---------------------------------Creating the Bar Chart Items for POS-----------------------------------------
        BarChartItem p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, p1x35GoalBuild, Tile.RED);


        BarChartItem p1532Data = new BarChartItem("P1532", p1532CurrentBuild, p1532GoalBuild,  Tile.GREEN);


        BarChartItem p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, p1x30GoalBuild, Tile.BLUE);


        BarChartItem t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, t1000sGoalBuild, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        BarChartItem n3000Data = new BarChartItem("N3000", n3000CurrentBuild, n3000GoalBuild, Tile.RED);

        BarChartItem s500Data = new BarChartItem("S500", s500CurrentBuild, s500GoalBuild, Tile.BLUE);


        BarChartItem mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, mediaPlayerGoalBuild, Tile.GREEN);

        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, kiwi4sGoalBuild, Tile.BLUE);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentBuild, kiwi2XsCurrentBuild, Tile.RED);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, bumpBarsGoalBuild, Tile.GREEN);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, pantherEPC4sGoalBuild, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, optic12sGoalBuild, Tile.RED);


        BarChartItem optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);

        BarChartItem opticKitsData = new BarChartItem("Optic Kits", kitsCurrentBuild, kitsGoalBuild, Tile.GREEN);

        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        BarChartItem xr5Data = new BarChartItem("7701", xr5CurrentBuild,xr5GoalBuild,Tile.BLUE);

        BarChartItem xr7Data = new BarChartItem("7702", xr7CurrentBuild, xr7GoalBuild,Tile.RED);

        BarChartItem xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, xr7PlusGoalBuild, Tile.GREEN);


        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.YELLOW);

        //---------------------------------Scheduled Service for Updating Bars------------------------------------------
        final CountDownLatch updateLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() ->
                {
                    //---------------------------------Update the POS Units---------------------------------------------
                    p1x35Data.setValue(p1x35CurrentBuild);
                    p1x35Data.setMaxValue(p1x35GoalBuild);

                    p1532Data.setValue(p1532CurrentBuild);
                    p1532Data.setMaxValue(p1532GoalBuild);

                    p1x30Data.setValue(p1x30CurrentBuild);
                    p1x30Data.setMaxValue(p1x30GoalBuild);

                    t1000Data.setValue(t1000sCurrentBuild);
                    t1000Data.setMaxValue(t1000sGoalBuild);


                    //---------------------------------Update the Server Units------------------------------------------
                    n3000Data.setValue(n3000CurrentBuild);
                    n3000Data.setMaxValue(n3000GoalBuild);

                    s500Data.setValue(s500CurrentBuild);
                    s500Data.setMaxValue(s500GoalBuild);

                    mediaPlayer.setValue(mediaPlayerCurrentBuild);
                    mediaPlayer.setMaxValue(mediaPlayerGoalBuild);

                    //---------------------------------Updating the Peripheral Units------------------------------------
                    kiwi4Data.setValue(kiwi4sCurrentBuild);
                    kiwi4Data.setMaxValue(kiwi4sGoalBuild);

                    kiwi25Data.setValue(kiwi2XsCurrentBuild);
                    kiwi25Data.setMaxValue(kiwi2XsGoalBuild);

                    bumpBarData.setValue(bumpBarsCurrentBuild);
                    bumpBarData.setMaxValue(bumpBarsGoalBuild);

                    pantherEPC4Data.setValue(pantherEPC4sCurrentBuild);
                    pantherEPC4Data.setMaxValue(pantherEPC4sGoalBuild);

                    //---------------------------------Updating the Optic Units------------------------------------------
                    optic5Data.setValue(optic5sCurrentBuild);
                    optic5Data.setMaxValue(optic5sGoalBuild);

                    optic12Data.setValue(optic12sCurrentBuild);
                    optic12Data.setMaxValue(optic12sGoalBuild);

                    opticKitsData.setValue(kitsCurrentBuild);
                    opticKitsData.setMaxValue(kitsGoalBuild);

                    //---------------------------------Updating the Retail Units----------------------------------------
                    xr5Data.setValue(xr5CurrentBuild);
                    xr5Data.setMaxValue(xr5GoalBuild);

                    xr7Data.setValue(xr7CurrentBuild);
                    xr7Data.setMaxValue(xr7GoalBuild);

                    xr7PlusData.setValue(xr7PlusCurrentBuild);
                    xr7PlusData.setMaxValue(xr7PlusGoalBuild);

                    nextGenDisplays.setValue(nextGenDisplayCurrentBuild);
                    nextGenDisplays.setMaxValue(nextGenDisplayGoalsBuild);

                    updateLatch.countDown();
                }, 0, 10, TimeUnit.SECONDS);

        updateLatch.await();

        //---------------------------------Creating the Tiles for POS---------------------------------------------------
        pos = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Point of Sales Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(p1x30Data, p1x35Data, p1532Data, t1000Data)
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
                .value(posPercentTotalBuild)
                .build();

        //---------------------------------Creating the Tiles for Servers-----------------------------------------------
        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(s500Data, n3000Data, mediaPlayer)
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
                .value(serversPercentTotalBuild)
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

        Tile periphPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit("\u0025")
                .animated(true)
                .value(periphPercentTotalBuild)
                .build();

        //---------------------------------Creating the Tiles for Optic-------------------------------------------------
        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5Data, optic12Data, opticKitsData)
                .maxValue(opticGoalTotalBuild)
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
                .value(opticPercentTotalBuild)
                .build();

        //---------------------------------Creating the Tiles for Retail------------------------------------------------
        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Build")
                .prefSize(384, 640)
                .barChartItems(xr5Data,xr7Data, xr7PlusData, nextGenDisplays)
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
                .value(retailPercentTotalBuild)
                .build();

        //---------------------------------Creating Animations for Graphs-----------------------------------------------
        pos.setAnimated(true);
        pos.setAnimationDuration(3);
        servers.setAnimated(true);
        servers.setAnimationDuration(3);
        retail.setAnimated(true);
        retail.setAnimationDuration(3);
        peripherals.setAnimated(true);
        peripherals.setAnimationDuration(3);
        optic.setAnimated(true);
        optic.setAnimationDuration(3);

        posPercent.setAnimated(true);
        posPercent.setAnimationDuration(3);
        serversPercent.setAnimated(true);
        serversPercent.setAnimationDuration(3);
        retailPercent.setAnimated(true);
        retailPercent.setAnimationDuration(3);
        periphPercent.setAnimated(true);
        periphPercent.setAnimationDuration(3);
        opticPercent.setAnimated(true);
        opticPercent.setAnimationDuration(3);

        //---------------------------------Scheduled Executor for Percent Change----------------------------------------
        final CountDownLatch percentLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate( () ->
                {
        //---------------------------------Creating Color Changes for POS Dial------------------------------------------
        posPercent.setValue(posPercentTotalBuild);

        if(Double.compare(posPercentTotalBuild,60) < 0)
        {
            posPercent.setBarColor(Tile.RED);
        }
        if(Double.compare(posPercentTotalBuild,90) < 0 && Double.compare(posPercentTotalBuild ,60) > 0)
        {
            posPercent.setBarColor(Tile.YELLOW);
        }
        if(Double.compare(posPercentTotalBuild,90) > 0)
        {
            posPercent.setBarColor(Tile.GREEN);
        }

        //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
        serversPercent.setValue(serversPercentTotalBuild);
        if(Double.compare(serversPercentTotalBuild,60) < 0)
        {
            serversPercent.setBarColor(Tile.RED);
        }
        if(Double.compare(serversPercentTotalBuild,90) < 0 && Double.compare(serversPercentTotalBuild ,60) > 0)
        {
            serversPercent.setBarColor(Tile.YELLOW);
        }
        if(Double.compare(serversPercentTotalBuild,90) > 0)
        {
            serversPercent.setBarColor(Tile.GREEN);
        }

        //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
        periphPercent.setValue(periphPercentTotalBuild);

        if(Double.compare(periphPercentTotalBuild,60) < 0)
        {
            periphPercent.setBarColor(Tile.RED);
        }
        if(Double.compare(periphPercentTotalBuild,90) < 0 && Double.compare(periphPercentTotalBuild ,60) > 0)
        {
            periphPercent.setBarColor(Tile.YELLOW);
        }
        if(Double.compare(periphPercentTotalBuild,90) > 0)
        {
            periphPercent.setBarColor(Tile.GREEN);
        }

        //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
        opticPercent.setValue(opticPercentTotalBuild);

        if(Double.compare(opticPercentTotalBuild,60) < 0)
        {
            opticPercent.setBarColor(Tile.RED);
        }
        if(Double.compare(opticPercentTotalBuild,90) < 0 && Double.compare(opticPercentTotalBuild ,60) > 0)
        {
            opticPercent.setBarColor(Tile.YELLOW);
        }
        if(Double.compare(opticPercentTotalBuild,90) > 0)
        {
            opticPercent.setBarColor(Tile.GREEN);
        }

        //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
        retailPercent.setValue(retailPercentTotalBuild);

         if(Double.compare(retailPercentTotalBuild,60) < 0)
        {
            retailPercent.setBarColor(Tile.RED);
        }
        if(Double.compare(retailPercentTotalBuild,90) < 0 && Double.compare(retailPercentTotalBuild ,60) > 0)
        {
            retailPercent.setBarColor(Tile.YELLOW);
        }
        if(Double.compare(retailPercentTotalBuild,90) > 0)
        {
            retailPercent.setBarColor(Tile.GREEN);
        }

        percentLatch.countDown();
        },0,12, TimeUnit.SECONDS);
        percentLatch.await();

        //---------------------------------Platform Creation------------------------------------------------------------
        flowPane.getChildren().addAll(pos,retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent, periphPercent,opticPercent);

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

        //---------------------------------Commented Tiles--------------------------------------------------------------
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