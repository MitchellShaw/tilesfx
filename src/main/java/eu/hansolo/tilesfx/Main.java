package eu.hansolo.tilesfx;

import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.MapTool;
import eu.hansolo.tilesfx.tools.GoalTool;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    Tile opticStage;
    Tile retailStage;
    Tile posPercentStage;
    Tile serversPercentStage;
    Tile periphPercentStage;
    Tile opticPercentStage;
    Tile retailPercentStage;

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
        FlowPane flowPaneTest = new FlowPane();
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
        System.out.println("Begin Build Queries.\n");

        ScheduledService buildVariables = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        System.out.println("Running Build Block.\n");

                        //---------------------------------Hosp Build---------------------------------------------------
                        posBuildMap = dataBaseTool.hospBuildDataBase();

                        p1532CurrentBuild = mapTool.getCurrentSingleValue("7734",posBuildMap);
                        p1x30CurrentBuild = mapTool.getCurrentSingleValue("7743",posBuildMap);
                        p1x35CurrentBuild = mapTool.getCurrentGroupValue(p1x35ProdList,posBuildMap);
                        t1000sCurrentBuild = mapTool.getCurrentSingleValue("7744",posBuildMap);

                        //---------------------------------Retail Build-------------------------------------------------
                        retailBuildMap = dataBaseTool.retailBuildDataBase();

                        xr7CurrentBuild = mapTool.getCurrentSingleValue("7702",retailBuildMap);
                        xr7PlusCurrentBuild = mapTool.getCurrentSingleValue("7703",retailBuildMap);
                        xr5CurrentBuild = mapTool.getCurrentSingleValue("7701",retailBuildMap);
                        nextGenDisplayCurrentBuild = mapTool.getCurrentGroupValue(nextGenProdList,retailBuildMap);

                        //---------------------------------Servers Build------------------------------------------------
                        serversBuildMap = dataBaseTool.serversBuildDataBase();

                        mediaPlayerCurrentBuild = mapTool.getCurrentGroupValue(mediaPlayerProdList,serversBuildMap);
                        n3000CurrentBuild = mapTool.getCurrentSingleValue("1657",serversBuildMap);
                        s500CurrentBuild = mapTool.getCurrentGroupValue(s500ProdList,serversBuildMap);

                        //---------------------------------Periph Build-------------------------------------------------
                        periphBuildMap = dataBaseTool.periphBuildDataBase();

                        kiwi4sCurrentBuild = mapTool.getCurrentSingleValue("1924",periphBuildMap);
                        kiwi2XsCurrentBuild = mapTool.getCurrentGroupValue(kiwi2XsProdList,periphBuildMap);
                        bumpBarsCurrentBuild = mapTool.getCurrentSingleValue("1635",periphBuildMap);
                        pantherEPC4sCurrentBuild = mapTool.getCurrentGroupValue(pantherEPC4sProdList,periphBuildMap);

                        //---------------------------------Optic Build--------------------------------------------------
                        opticBuildMap = dataBaseTool.opticBuildDataBase();
                        optic5sCurrentBuild =  mapTool.getCurrentSingleValue("6001",opticBuildMap);
                        optic12sCurrentBuild = mapTool.getCurrentSingleValue("6002",opticBuildMap);
                        kitsCurrentBuild = mapTool.getCurrentSingleValue("6003",opticBuildMap);
                        return null;
                    }
                };
            }
        };

        //---------------------------------Scheduled Executors for Test Variables---------------------------------------
        System.out.println("\nBegin Test Queries.\n");

        ScheduledService testVariables = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
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
                        return null;
                    }
                };
            }
        };

        //---------------------------------Scheduled Executors for Stage Variables--------------------------------------

        System.out.println("\nBegin Stage Queries.\n");

        ScheduledService stageVariables = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        System.out.println("Running Stage Block.\n");
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
                        return null;
                    }
                };
            }
        };

        //---------------------------------Scheduled Executors for Document Variables and Goal Set----------------------
        System.out.println("\nBegin Document Read.\n");

        ScheduledService docReader = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        System.out.println("Running Doc Block.\n");
                        mapList = dataBaseTool.documentReader();

                        p1532GoalBuild = goalTool.getGoal(mapList.get(0),"7734");
                        p1x30GoalBuild = goalTool.getGoal(mapList.get(0),"7743");
                        p1x35GoalBuild = goalTool.getListGoal(mapList.get(0),p1x35ProdList);
                        t1000sGoalBuild = goalTool.getGoal(mapList.get(0),"7744");

                        //---------------------------------Percent Calculation and Update for POS-------------------------------
                        posTotalGoalBuild = p1532GoalBuild + p1x30GoalBuild + p1x35GoalBuild + t1000sGoalBuild;
                        posTotalCurrentBuild = p1532CurrentBuild + p1x35CurrentBuild + p1x30CurrentBuild + t1000sCurrentBuild;

                        posTotalCurrentTest = p1532CurrentTest+ p1x35CurrentTest + p1x30CurrentTest + t1000sCurrentTest;

                        posTotalGoalStage = p1532GoalStage + p1x30GoalStage + p1x35GoalStage + t1000sGoalStage;
                        posTotalCurrentStage = p1532CurrentStage + p1x35CurrentStage + p1x30CurrentStage + t1000sCurrentStage;

                        posPercentTotalBuild = goalTool.getPercentTotal(posTotalCurrentBuild,posTotalGoalBuild);
                        posPercentTotalTest = goalTool.getPercentTotal(posTotalCurrentTest,posTotalGoalBuild);
                        posPercentTotalStage = goalTool.getPercentTotal(posTotalCurrentStage ,posTotalGoalStage );

                        //---------------------------------Retail Build---------------------------------------------------------
                        xr7GoalBuild = goalTool.getGoal(mapList.get(4),"7702");
                        xr7PlusGoalBuild = goalTool.getGoal(mapList.get(4),"7703");
                        xr5GoalBuild = goalTool.getGoal(mapList.get(4),"7701");
                        nextGenDisplayGoalsBuild = goalTool.getListGoal(mapList.get(4),nextGenProdList);

                        //---------------------------------Percent Calculation and Update for Retail----------------------------
                        retailTotalGoalBuild = xr7GoalBuild + xr7PlusGoalBuild + xr5GoalBuild + nextGenDisplayGoalsBuild;
                        retailTotalCurrentBuild = xr7CurrentBuild + xr7PlusCurrentBuild + xr5CurrentBuild + nextGenDisplayCurrentBuild;

                        retailTotalCurrentTest = xr7CurrentTest+ xr7PlusCurrentTest + xr5CurrentTest + nextGenDisplayCurrentTest;

                        retailTotalGoalStage = xr7GoalStage + xr7PlusGoalStage + xr5GoalStage + nextGenDisplayGoalsStage;
                        retailTotalCurrentStage = xr7CurrentStage + xr7PlusCurrentStage + xr5CurrentStage + nextGenDisplayCurrentStage;

                        retailPercentTotalBuild = goalTool.getPercentTotal(retailTotalCurrentBuild,retailTotalGoalBuild);
                        retailPercentTotalTest = goalTool.getPercentTotal(retailTotalCurrentTest,retailTotalGoalBuild);
                        retailPercentTotalStage = goalTool.getPercentTotal(retailTotalCurrentStage,retailTotalGoalStage);

                        //---------------------------------Servers Build--------------------------------------------------------
                        mediaPlayerGoalBuild = goalTool.getListGoal(mapList.get(1),mediaPlayerProdList);
                        n3000GoalBuild = goalTool.getGoal(mapList.get(1),"1657");
                        s500GoalBuild = goalTool.getGoal(mapList.get(1),"1611");

                        //---------------------------------Percent Calculation and Update for Servers---------------------------
                        serverGoalTotalBuild = mediaPlayerGoalBuild + n3000GoalBuild + s500GoalBuild;
                        serverCurrentBuild = mediaPlayerCurrentBuild + n3000CurrentBuild + s500CurrentBuild;

                        serverCurrentTest = mediaPlayerCurrentTest + n3000CurrentTest + s500CurrentTest;

                        serverGoalTotalStage = mediaPlayerGoalStage + n3000GoalStage + s500GoalStage;
                        serverCurrentStage = mediaPlayerCurrentStage + n3000CurrentStage + s500CurrentStage;


                        serversPercentTotalBuild = goalTool.getPercentTotal(serverCurrentBuild,serverGoalTotalBuild);
                        serversPercentTotalTest = goalTool.getPercentTotal(serverCurrentTest,serverGoalTotalBuild);
                        serversPercentTotalStage = goalTool.getPercentTotal(serverCurrentStage,serverGoalTotalStage);

                        //---------------------------------Periph Build---------------------------------------------------------
                        kiwi4sGoalBuild = goalTool.getGoal(mapList.get(3),"1924");
                        kiwi2XsGoalBuild = goalTool.getListGoal(mapList.get(3),kiwi2XsProdList);
                        bumpBarsGoalBuild = goalTool.getGoal(mapList.get(3),"1635");
                        pantherEPC4sGoalBuild = goalTool.getListGoal(mapList.get(3),pantherEPC4sProdList);

                        //---------------------------------Percent Calculation and Update for Periph----------------------------
                        periphGoalTotalBuild = kiwi4sGoalBuild + kiwi2XsGoalBuild + bumpBarsGoalBuild + pantherEPC4sGoalBuild;
                        periphCurrentTotalBuild = kiwi4sCurrentBuild + kiwi2XsCurrentBuild + bumpBarsCurrentBuild + pantherEPC4sCurrentBuild;

                        periphCurrentTotalTest = kiwi4sCurrentTest + kiwi2XsCurrentTest + bumpBarsCurrentTest + pantherEPC4sCurrentTest;

                        periphGoalTotalStage = kiwi4sGoalStage  + kiwi2XsGoalStage  + bumpBarsGoalStage  + pantherEPC4sGoalStage ;
                        periphCurrentTotalStage  = kiwi4sCurrentStage  + kiwi2XsCurrentStage  + bumpBarsCurrentStage  + pantherEPC4sCurrentStage ;


                        periphPercentTotalBuild = goalTool.getPercentTotal(periphCurrentTotalBuild,periphGoalTotalBuild);
                        periphPercentTotalTest = goalTool.getPercentTotal(periphCurrentTotalTest,periphGoalTotalBuild);
                        periphPercentTotalStage = goalTool.getPercentTotal(periphCurrentTotalStage,periphGoalTotalStage);

                        //---------------------------------Optic Build----------------------------------------------------------
                        optic5sGoalBuild = goalTool.getGoal(mapList.get(2),"6001");
                        optic12sGoalBuild = goalTool.getGoal(mapList.get(2),"6002");
                        kitsGoalBuild = goalTool.getGoal(mapList.get(2),"6003");

                        //---------------------------------Percent Calculation and Update for Optic-----------------------------
                        opticGoalTotalBuild = optic5sGoalBuild + optic12sGoalBuild + kitsGoalBuild;
                        opticCurrentTotalBuild = optic5sCurrentBuild + optic12sCurrentBuild + kitsCurrentBuild;

                        opticCurrentTotalTest = optic5sCurrentTest + optic12sCurrentTest + kitsCurrentTest;


                        opticPercentTotalBuild = goalTool.getPercentTotal(opticCurrentTotalBuild,opticGoalTotalBuild);
                        opticPercentTotalTest = goalTool.getPercentTotal(opticCurrentTotalTest,opticGoalTotalBuild);
                        return null;
                    }
                };
            }
        };
        
        //---------------------------------Creating the Bar Chart Items for POS-----------------------------------------
                BarChartItem p1x35Data = new BarChartItem("P1X35", p1x35CurrentBuild, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataTest = new BarChartItem("P1X35", p1x35CurrentTest, p1x35GoalBuild, Tile.RED);
        BarChartItem p1x35DataStage = new BarChartItem("P1X35", p1x35CurrentStage, p1x35GoalStage, Tile.RED);


        BarChartItem p1532Data = new BarChartItem("P1532", p1532CurrentBuild, p1532GoalBuild,  Tile.GREEN);
        BarChartItem p1532DataTest = new BarChartItem("P1532", p1532CurrentTest, p1532GoalBuild,  Tile.GREEN);
        BarChartItem p1532DataStage = new BarChartItem("P1532", p1532CurrentStage, p1532GoalBuild,  Tile.GREEN);


        BarChartItem p1x30Data = new BarChartItem("P1X30", p1x30CurrentBuild, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataTest = new BarChartItem("P1X30", p1x30CurrentTest, p1x30GoalBuild, Tile.BLUE);
        BarChartItem p1x30DataStage = new BarChartItem("P1X30", p1x30CurrentStage, p1x30GoalStage, Tile.BLUE);


        BarChartItem t1000Data = new BarChartItem("T1000", t1000sCurrentBuild, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataTest = new BarChartItem("T1000", t1000sCurrentTest, t1000sGoalBuild, Tile.YELLOW);
        BarChartItem t1000DataStage = new BarChartItem("T1000", t1000sCurrentStage, t1000sGoalStage, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        BarChartItem n3000Data = new BarChartItem("N3000", n3000CurrentBuild, n3000GoalBuild, Tile.RED);
        BarChartItem n3000Test = new BarChartItem("N3000", n3000CurrentTest, n3000GoalBuild, Tile.RED);
        BarChartItem n3000Stage = new BarChartItem("N3000", n3000CurrentStage, n3000GoalStage, Tile.RED);

        BarChartItem s500Data = new BarChartItem("S500", s500CurrentBuild, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataTest = new BarChartItem("S500", s500CurrentTest, s500GoalBuild, Tile.BLUE);
        BarChartItem s500DataStage = new BarChartItem("S500", s500CurrentStage, s500GoalStage, Tile.BLUE);


        BarChartItem mediaPlayer = new BarChartItem("Media Player", mediaPlayerCurrentBuild, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerTest = new BarChartItem("Media Player", mediaPlayerCurrentTest, mediaPlayerGoalBuild, Tile.GREEN);
        BarChartItem mediaPlayerStage = new BarChartItem("Media Player", mediaPlayerCurrentStage, mediaPlayerGoalStage, Tile.GREEN);

        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", kiwi4sCurrentBuild, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataTest = new BarChartItem("Kiwi 4", kiwi4sCurrentTest, kiwi4sGoalBuild, Tile.BLUE);
        BarChartItem kiwi4DataStage = new BarChartItem("Kiwi 4", kiwi4sCurrentStage, kiwi4sGoalStage, Tile.BLUE);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentBuild, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataTest = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentTest, kiwi2XsGoalBuild, Tile.RED);
        BarChartItem kiwi25DataStage = new BarChartItem("Kiwi 2/2.5", kiwi2XsCurrentStage, kiwi2XsGoalStage, Tile.RED);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", bumpBarsCurrentBuild, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataTest = new BarChartItem("Bumpbar", bumpBarsCurrentTest, bumpBarsGoalBuild, Tile.GREEN);
        BarChartItem bumpBarDataStage = new BarChartItem("Bumpbar", bumpBarsCurrentStage, bumpBarsGoalBuild, Tile.GREEN);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentBuild, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Test = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentTest, pantherEPC4sGoalBuild, Tile.YELLOW);
        BarChartItem pantherEPC4Stage = new BarChartItem("Panther/EPC4", pantherEPC4sCurrentStage, pantherEPC4sGoalStage, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", optic12sCurrentBuild, optic12sGoalBuild, Tile.RED);
        BarChartItem optic12DataTest = new BarChartItem("Optic 12", optic12sCurrentTest, optic12sGoalBuild, Tile.RED);


        BarChartItem optic5Data = new BarChartItem("Optic 5", optic5sCurrentBuild, optic5sGoalBuild, Tile.BLUE);
        BarChartItem optic5DataTest = new BarChartItem("Optic 5", optic5sCurrentTest, optic5sGoalBuild, Tile.BLUE);

        BarChartItem opticKitsData = new BarChartItem("Optic Kits", kitsCurrentBuild, kitsGoalBuild, Tile.GREEN);
        BarChartItem opticKitsDataTest = new BarChartItem("Optic Kits", kitsCurrentTest, kitsGoalBuild, Tile.GREEN);

        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        BarChartItem xr5Data = new BarChartItem("7701", xr5CurrentBuild,xr5GoalBuild,Tile.BLUE);
        BarChartItem xr5DataTest = new BarChartItem("7701", xr5CurrentTest,xr5GoalBuild,Tile.BLUE);
        BarChartItem xr5DataStage = new BarChartItem("7701", xr5CurrentStage,xr5GoalStage,Tile.BLUE);

        BarChartItem xr7Data = new BarChartItem("7702", xr7CurrentBuild, xr7GoalBuild,Tile.RED);
        BarChartItem xr7DataTest = new BarChartItem("7702", xr7CurrentTest, xr7GoalBuild,Tile.RED);
        BarChartItem xr7DataStage = new BarChartItem("7702", xr7CurrentStage, xr7GoalStage,Tile.RED);

        BarChartItem xr7PlusData = new BarChartItem("7703", xr7PlusCurrentBuild, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataTest = new BarChartItem("7703", xr7PlusCurrentTest, xr7PlusGoalBuild, Tile.GREEN);
        BarChartItem xr7PlusDataStage = new BarChartItem("7703", xr7PlusCurrentStage, xr7PlusGoalStage, Tile.GREEN);


        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentBuild, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysTest = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentTest, nextGenDisplayGoalsBuild, Tile.YELLOW);
        BarChartItem nextGenDisplaysStage = new BarChartItem("Next Gen Displays", nextGenDisplayCurrentStage, nextGenDisplayGoalsStage, Tile.YELLOW);

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

        posPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentBuild)+"/"+Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentBuild)+"/"+Double.toString(posTotalGoalBuild))
                .value(posPercentTotalBuild)
                .build();

        posTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Point of Sales Test")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(p1x30DataTest, p1x35DataTest, p1532DataTest, t1000DataTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        posPercentTest = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(posTotalCurrentTest)+"/"+Double.toString(posTotalGoalBuild))
                .animated(true)
                .subText(Double.toString(posTotalCurrentTest)+"/"+Double.toString(posTotalGoalBuild))
                .value(posPercentTotalTest)
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
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentBuild)+"/"+Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentBuild)+"/"+Double.toString(serverGoalTotalBuild))
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
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(serverCurrentTest)+"/"+Double.toString(serverGoalTotalBuild))
                .animated(true)
                .subText(Double.toString(serverCurrentTest)+"/"+Double.toString(serverGoalTotalBuild))
                .value(serversPercentTotalTest)
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
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalBuild)+"/"+Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalBuild)+"/"+Double.toString(periphGoalTotalBuild))
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
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(periphCurrentTotalTest)+"/"+Double.toString(periphGoalTotalBuild))
                .subText(Double.toString(periphCurrentTotalTest)+"/"+Double.toString(periphGoalTotalBuild))
                .animated(true)
                .value(periphPercentTotalTest)
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

        opticPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalBuild)+"/"+Double.toString(opticGoalTotalBuild))
                .subText(Double.toString(opticCurrentTotalBuild))
                .animated(true)
                .value(opticPercentTotalBuild)
                .build();

        opticTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Test")
                .roundedCorners(true)
                .prefSize(384, 640)
                .barChartItems(optic5DataTest, optic12DataTest, opticKitsDataTest)
                .maxValue(opticGoalTotalBuild)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        opticPercentTest = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(opticCurrentTotalTest)+"/"+Double.toString(opticGoalTotalBuild))
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
                .barChartItems(xr5Data,xr7Data, xr7PlusData, nextGenDisplays)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercent = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentBuild)+"/"+Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentBuild))
                .animated(true)
                .value(retailPercentTotalBuild)
                .build();

        retailTest = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(true)
                .title("Retail Test")
                .prefSize(384, 640)
                .barChartItems(xr5DataTest,xr7DataTest, xr7PlusDataTest, nextGenDisplaysTest)
                .decimals(0)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        retailPercentTest = TileBuilder.create()
                .prefSize(384,440)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .textAlignment(TextAlignment.CENTER)
                .text("Percentage to Goal")
                .unit(Double.toString(retailTotalCurrentTest)+"/"+Double.toString(retailTotalGoalBuild))
                .subText(Double.toString(retailTotalCurrentTest)+"/"+Double.toString(retailTotalGoalBuild))
                .animated(true)
                .value(retailPercentTotalTest)
                .build();

        //---------------------------------Creating Animations for Graphs-----------------------------------------------
         pos.setAnimated(true);
        pos.setAnimationDuration(3000);
        servers.setAnimated(true);
        servers.setAnimationDuration(3000);
        retail.setAnimated(true);
        retail.setAnimationDuration(3000);
        peripherals.setAnimated(true);
        peripherals.setAnimationDuration(3000);
        optic.setAnimated(true);
        optic.setAnimationDuration(3000);

        posPercent.setAnimated(true);
        posPercent.setAnimationDuration(3000);
        serversPercent.setAnimated(true);
        serversPercent.setAnimationDuration(3000);
        retailPercent.setAnimated(true);
        retailPercent.setAnimationDuration(3000);
        periphPercent.setAnimated(true);
        periphPercent.setAnimationDuration(3000);
        opticPercent.setAnimated(true);
        opticPercent.setAnimationDuration(3000);

        posTest.setAnimated(true);
        posTest.setAnimationDuration(3000);
        serversTest.setAnimated(true);
        serversTest.setAnimationDuration(3000);
        retailTest.setAnimated(true);
        retailTest.setAnimationDuration(3000);
        peripheralsTest.setAnimated(true);
        peripheralsTest.setAnimationDuration(3000);
        opticTest.setAnimated(true);
        opticTest.setAnimationDuration(3000);

        posPercentTest.setAnimated(true);
        posPercentTest.setAnimationDuration(3000);
        serversPercentTest.setAnimated(true);
        serversPercentTest.setAnimationDuration(3000);
        retailPercentTest.setAnimated(true);
        retailPercentTest.setAnimationDuration(3000);
        periphPercentTest.setAnimated(true);
        periphPercentTest.setAnimationDuration(3000);
        opticPercentTest.setAnimated(true);
        opticPercentTest.setAnimationDuration(3000);

        //---------------------------------Scheduled Executor for Build & Percent Change--------------------------------
        final CountDownLatch percentLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate( () ->
                {
        p1x35Data.setValue(p1x35CurrentBuild);
        p1x35DataTest.setValue(p1x35CurrentTest);

        p1532Data.setValue(p1532CurrentBuild);
        p1532DataTest.setValue(p1532CurrentTest);

        p1x30Data.setValue(p1x30CurrentBuild);
        p1x30DataTest.setValue(p1x30CurrentTest);

        t1000Data.setValue(t1000sCurrentBuild);
        t1000DataTest.setValue(t1000sCurrentTest);

        //---------------------------------Creating the Bar Chart Items for Servers-------------------------------------
        n3000Data.setValue(n3000CurrentBuild);
        n3000Test.setValue(n3000CurrentTest);

        s500Data.setValue(s500CurrentBuild);
        s500DataTest.setValue(s500CurrentTest);

        mediaPlayer.setValue(mediaPlayerCurrentBuild);
        mediaPlayerTest.setValue(mediaPlayerCurrentTest);

        //---------------------------------Creating the Bar Chart Items for Peripherals---------------------------------
        kiwi4Data.setValue(kiwi4sCurrentBuild);
        kiwi4DataTest.setValue(kiwi4sCurrentTest);

        kiwi25Data.setValue(kiwi2XsCurrentBuild);
        kiwi25DataTest.setValue(kiwi2XsCurrentTest);

        bumpBarData.setValue(bumpBarsCurrentBuild);
        bumpBarDataTest.setValue(bumpBarsCurrentTest);

        pantherEPC4Data.setValue(pantherEPC4sCurrentBuild);
        pantherEPC4Test.setValue(pantherEPC4sCurrentTest);

        //---------------------------------Creating the Bar Chart Items for Optic---------------------------------------
        optic12Data.setValue(optic12sCurrentBuild);
        optic12DataTest.setValue(optic12sCurrentTest);

        optic5Data.setValue(optic5sCurrentBuild);
        optic5DataTest.setValue(optic5sCurrentTest);

        opticKitsData.setValue(kitsCurrentBuild);
        opticKitsDataTest.setValue(kitsCurrentTest);

        //---------------------------------Creating the Bar Chart Items for Retail--------------------------------------
        xr5Data.setValue(xr5CurrentBuild);
        xr5DataTest.setValue(xr5CurrentTest);

        xr7Data.setValue(xr7CurrentBuild);
        xr7DataTest.setValue(xr7CurrentTest);

        xr7PlusData.setValue(xr7PlusCurrentBuild);
        xr7PlusDataTest.setValue(xr7PlusCurrentTest);

        nextGenDisplays.setValue(nextGenDisplayCurrentBuild);
        nextGenDisplaysTest.setValue(nextGenDisplayCurrentTest);

        //---------------------------------Creating Color Changes for POS Dial------------------------------------------
        posPercent.setValue(posPercentTotalBuild);
        posPercentTest.setValue(posPercentTotalTest);


        if(Double.compare(posPercentTotalBuild,60) < 0)
        {
            posPercent.setBarColor(Tile.RED);
            posPercent.setUnit((Double.toString(posTotalCurrentBuild)+"/"+Double.toString(posTotalGoalBuild)));
        }
        if(Double.compare(posPercentTotalBuild,90) < 0 && Double.compare(posPercentTotalBuild ,60) > 0)
        {
            posPercent.setBarColor(Tile.YELLOW);
            posPercent.setUnit((Double.toString(posTotalCurrentBuild)+"/"+Double.toString(posTotalGoalBuild)));
        }
        if(Double.compare(posPercentTotalBuild,90) > 0)
        {
            posPercent.setBarColor(Tile.GREEN);
            posPercent.setUnit((Double.toString(posTotalCurrentBuild)+"/"+Double.toString(posTotalGoalBuild)));
        }


        if(Double.compare(posPercentTotalTest,60) < 0)
        {
            posPercentTest.setBarColor(Tile.RED);
            posPercentTest.setUnit((Double.toString(posTotalCurrentTest) + "/" + Double.toString(posTotalGoalBuild)));
        }
        if(Double.compare(posPercentTotalTest,90) < 0 && Double.compare(posPercentTotalTest ,60) > 0)
        {
            posPercentTest.setBarColor(Tile.YELLOW);
            posPercentTest.setUnit((Double.toString(posTotalCurrentTest)+"/"+Double.toString(posTotalGoalBuild)));
        }
        if(Double.compare(posPercentTotalTest,90) > 0)
        {
            posPercentTest.setBarColor(Tile.GREEN);
            posPercentTest.setUnit((Double.toString(posTotalCurrentTest)+"/"+Double.toString(posTotalGoalBuild)));
        }

        //---------------------------------Creating Color Changes for Servers Dial--------------------------------------
        serversPercent.setValue(serversPercentTotalBuild);
        serversPercentTest.setValue(serversPercentTotalTest);

        if(Double.compare(serversPercentTotalBuild,60) < 0)
        {
            serversPercent.setBarColor(Tile.RED);
            serversPercent.setUnit((Double.toString(serverCurrentBuild)+"/"+Double.toString(serverGoalTotalBuild)));
        }
        if(Double.compare(serversPercentTotalBuild,90) < 0 && Double.compare(serversPercentTotalBuild ,60) > 0)
        {
            serversPercent.setBarColor(Tile.YELLOW);
            serversPercent.setUnit((Double.toString(serverCurrentBuild)+"/"+Double.toString(serverGoalTotalBuild)));
        }
        if(Double.compare(serversPercentTotalBuild,90) > 0)
        {
            serversPercent.setBarColor(Tile.GREEN);
            serversPercent.setUnit((Double.toString(serverCurrentBuild)+"/"+Double.toString(serverGoalTotalBuild)));
        }

        if(Double.compare(serversPercentTotalTest,60) < 0)
        {
            serversPercentTest.setBarColor(Tile.RED);
            serversPercentTest.setUnit((Double.toString(serverCurrentTest)+"/"+Double.toString(serverGoalTotalBuild)));
        }
        if(Double.compare(serversPercentTotalTest,90) < 0 && Double.compare(serversPercentTotalBuild ,60) > 0)
        {
            serversPercentTest.setBarColor(Tile.YELLOW);
            serversPercentTest.setUnit((Double.toString(serverCurrentTest)+"/"+Double.toString(serverGoalTotalBuild)));
        }
        if(Double.compare(serversPercentTotalTest,90) > 0)
        {
            serversPercentTest.setBarColor(Tile.GREEN);
            serversPercentTest.setUnit((Double.toString(serverCurrentTest)+"/"+Double.toString(serverGoalTotalBuild)));
        }

        //---------------------------------Creating Color Changes for Periph Dial---------------------------------------
        periphPercent.setValue(periphPercentTotalBuild);
        periphPercentTest.setValue(periphPercentTotalTest);

        if(Double.compare(periphPercentTotalBuild,60) < 0)
        {
            periphPercent.setBarColor(Tile.RED);
            periphPercent.setUnit(Double.toString(periphCurrentTotalBuild)+"/"+Double.toString(periphGoalTotalBuild));
        }
        if(Double.compare(periphPercentTotalBuild,90) < 0 && Double.compare(periphPercentTotalBuild ,60) > 0)
        {
            periphPercent.setBarColor(Tile.YELLOW);
            periphPercent.setUnit(Double.toString(periphCurrentTotalBuild)+"/"+Double.toString(periphGoalTotalBuild));
        }
        if(Double.compare(periphPercentTotalBuild,90) > 0)
        {
            periphPercent.setBarColor(Tile.GREEN);
            periphPercent.setUnit(Double.toString(periphCurrentTotalBuild)+"/"+Double.toString(periphGoalTotalBuild));
        }

        if(Double.compare(periphPercentTotalTest,60) < 0)
        {
            periphPercentTest.setBarColor(Tile.RED);
            periphPercentTest.setUnit(Double.toString(periphCurrentTotalTest)+"/"+Double.toString(periphGoalTotalBuild));
        }
        if(Double.compare(periphPercentTotalTest,90) < 0 && Double.compare(periphPercentTotalTest ,60) > 0)
        {
            periphPercentTest.setBarColor(Tile.YELLOW);
            periphPercentTest.setUnit(Double.toString(periphCurrentTotalTest)+"/"+Double.toString(periphGoalTotalBuild));
        }
        if(Double.compare(periphPercentTotalTest,90) > 0)
        {
            periphPercentTest.setBarColor(Tile.GREEN);
            periphPercentTest.setUnit(Double.toString(periphCurrentTotalTest)+"/"+Double.toString(periphGoalTotalBuild));
        }

        //---------------------------------Creating Color Changes for Optic Dial----------------------------------------
        opticPercent.setValue(opticPercentTotalBuild);
        opticPercentTest.setValue(opticPercentTotalTest);

        if(Double.compare(opticPercentTotalBuild,60) < 0)
        {
            opticPercent.setBarColor(Tile.RED);
            opticPercent.setUnit(Double.toString(opticCurrentTotalBuild)+"/"+Double.toString(opticGoalTotalBuild));
        }
        if(Double.compare(opticPercentTotalBuild,90) < 0 && Double.compare(opticPercentTotalBuild ,60) > 0)
        {
            opticPercent.setBarColor(Tile.YELLOW);
            opticPercent.setUnit(Double.toString(opticCurrentTotalBuild)+"/"+Double.toString(opticGoalTotalBuild));
        }
        if(Double.compare(opticPercentTotalBuild,90) > 0)
        {
            opticPercent.setBarColor(Tile.GREEN);
            opticPercent.setUnit(Double.toString(opticCurrentTotalBuild)+"/"+Double.toString(opticGoalTotalBuild));
        }


        if(Double.compare(opticPercentTotalTest,60) < 0)
        {
            opticPercentTest.setBarColor(Tile.RED);
            opticPercentTest.setUnit(Double.toString(opticCurrentTotalTest)+"/"+Double.toString(opticGoalTotalBuild));
        }
        if(Double.compare(opticPercentTotalTest,90) < 0 && Double.compare(opticPercentTotalTest ,60) > 0)
        {
            opticPercentTest.setBarColor(Tile.YELLOW);
            opticPercentTest.setUnit(Double.toString(opticCurrentTotalTest)+"/"+Double.toString(opticGoalTotalBuild));
        }
        if(Double.compare(opticPercentTotalTest,90) > 0)
        {
            opticPercentTest.setBarColor(Tile.GREEN);
            opticPercentTest.setUnit(Double.toString(opticCurrentTotalTest)+"/"+Double.toString(opticGoalTotalBuild));
        }

        //---------------------------------Creating Color Changes for Retail Dial---------------------------------------
        retailPercent.setValue(retailPercentTotalBuild);
        retailPercentTest.setValue(retailPercentTotalTest);

         if(Double.compare(retailPercentTotalBuild,60) < 0)
        {
            retailPercent.setBarColor(Tile.RED);
            retailPercent.setUnit(Double.toString(retailTotalCurrentBuild)+"/"+Double.toString(retailTotalGoalBuild));
        }
        if(Double.compare(retailPercentTotalBuild,90) < 0 && Double.compare(retailPercentTotalBuild ,60) > 0)
        {
            retailPercent.setBarColor(Tile.YELLOW);
            retailPercent.setUnit(Double.toString(retailTotalCurrentBuild)+"/"+Double.toString(retailTotalGoalBuild));
        }
        if(Double.compare(retailPercentTotalBuild,90) > 0)
        {
            retailPercent.setBarColor(Tile.GREEN);
            retailPercent.setUnit(Double.toString(retailTotalCurrentBuild)+"/"+Double.toString(retailTotalGoalBuild));
        }


        if(Double.compare(retailPercentTotalTest,60) < 0)
        {
            retailPercentTest.setBarColor(Tile.RED);
            retailPercentTest.setUnit(Double.toString(retailTotalCurrentTest)+"/"+Double.toString(retailTotalGoalBuild));
        }
        if(Double.compare(retailPercentTotalTest,90) < 0 && Double.compare(retailPercentTotalBuild ,60) > 0)
        {
            retailPercentTest.setBarColor(Tile.YELLOW);
            retailPercentTest.setUnit(Double.toString(retailTotalCurrentTest)+"/"+Double.toString(retailTotalGoalBuild));
        }
        if(Double.compare(retailPercentTotalTest,90) > 0)
        {
            retailPercentTest.setBarColor(Tile.GREEN);
            retailPercentTest.setUnit(Double.toString(retailTotalCurrentTest)+"/"+Double.toString(retailTotalGoalBuild));
        }

        percentLatch.countDown();
        },0,12, TimeUnit.SECONDS);
        percentLatch.await();

        //---------------------------------Platform Creation------------------------------------------------------------
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

        for(int i =0;i<tileList.size();i++)
        {
            Tile temp = tileList.get(i);
            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();

                }
            });
            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    temp.getScene().getWindow().setX(event.getScreenX() - x);
                    temp.getScene().getWindow().setY(event.getScreenY() - y);
                }
            });
        }

        flowPane.getChildren().addAll(pos,retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent, periphPercent,opticPercent);
        flowPaneTest.getChildren().addAll(posTest,retailTest, serversTest, peripheralsTest, opticTest, posPercentTest, retailPercentTest,serversPercentTest, periphPercentTest,opticPercentTest);

        flowPane.setStyle("-fx-background-color: rgb(42, 42, 42)");
        flowPane.setPrefSize(1920,1080);
        flowPaneTest.setStyle("-fx-background-color: rgb(42, 42, 42)");
        flowPaneTest.setPrefSize(1920,1080);
        Scene scene = new Scene(flowPane);
        Scene scene1 = new Scene (flowPaneTest);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    primaryStage.setMaximized(true);
                    primaryStage.setFullScreenExitHint("");
                }
                if(event.getCode() == KeyCode.RIGHT)
                {
                    if(primaryStage.isMaximized())
                    {
                        flag = true;
                    }
                    else{
                        flag = false;
                    }
                    primaryStage.setScene(scene1);

                    if(flag = true)
                    {
                        primaryStage.setMaximized(true);
                    }
                }
            }
        });
        scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F4)
                {
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    primaryStage.setMaximized(true);
                }
                if(event.getCode() == KeyCode.LEFT)
                {
                    if(primaryStage.isMaximized())
                    {
                        flag = true;
                    }
                    else{
                        flag = false;
                    }
                    primaryStage.setScene(scene);
                    if(flag = true)
                    {
                        primaryStage.setMaximized(true);
                    }

                }
            }
        });
        primaryStage.initStyle(StageStyle.UNDECORATED);
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


  ScheduledService myService = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        readMe myReader = new readMe(sessionFactory);
                        try {
                            System.out.println("Starting read.");
                            myReader.reader();
                            System.out.println("Parsed.");
                            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                            System.out.println(timeStamp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
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