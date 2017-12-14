import eu.hansolo.tilesfx.Controllers.*;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.fonts.Fonts;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import sun.util.resources.cldr.om.CurrencyNames_om;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;

import static java.time.temporal.ChronoUnit.DAYS;


/**
 * Created by Mitchell Shaw
 * Framework by hansolo
 * 2017-11-22.
 */
public class Main extends Application {
    //------------------------------------Variables Block---------------------------------------------------------------
    double x = 0;
    double y = 0;
    boolean flag;
    Messenger messenger;
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

        FlowGridPane posGridPane = new FlowGridPane(4,5);
        FlowGridPane retailGridPane = new FlowGridPane(4,5);
        FlowGridPane serversGridPane = new FlowGridPane(4,5);
        FlowGridPane periphGridPane = new FlowGridPane(4,5);

        //---------------------------------Creating the Tools for the graphs--------------------------------------------
        Tool dataBaseTool = new Tool();
        MapTool mapTool = new MapTool();
        GoalTool goalTool = new GoalTool();

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


        LoadingController loadingController = new LoadingController();
        NavigationController navigationController = new NavigationController();
        MainBuildController buildController = new MainBuildController();
        MainTestController testController = new MainTestController();
        MainStageController stageController = new MainStageController();
        POSBuildController posBuildController = new POSBuildController();
        RetailBuildController retailBuildController = new RetailBuildController();
        ServersBuildController serversBuildController = new ServersBuildController();
        PeriphBuildController periphBuildController = new PeriphBuildController();
        OpticBuildController opticBuildController = new OpticBuildController();

        messenger = new Messenger(loadingController,navigationController,buildController,testController,stageController,posBuildController,retailBuildController,serversBuildController,periphBuildController,opticBuildController,primaryStage);

        navigationController.setMessenger(messenger);
        buildController.setMessenger(messenger);
        testController.setMessenger(messenger);
        stageController.setMessenger(messenger);
        posBuildController.setMessenger(messenger);
        retailBuildController.setMessenger(messenger);
        serversBuildController.setMessenger(messenger);
        periphBuildController.setMessenger(messenger);
        opticBuildController.setMessenger(messenger);

        FXMLLoader root;

        root = new FXMLLoader(getClass().getResource("FXML/LoadingScreen.fxml"));
        root.setController(loadingController);
        GridPane loadPane = root.load();
        Scene loadingScene = new Scene(loadPane, 1920, 1080);

        root = new FXMLLoader(getClass().getResource("FXML/NavigationScreen.fxml"));
        root.setController(navigationController);
        GridPane navigationPane = root.load();
        Scene navigationScene = new Scene(navigationPane, 1920, 1080);

        root = new FXMLLoader(getClass().getResource("FXML/mainBuildScreen.fxml"));
        root.setController(buildController);
        GridPane buildPane = root.load();
        Scene buildScene = new Scene(buildPane, 1920, 1080);

        root = new FXMLLoader(getClass().getResource("FXML/mainTestScreen.fxml"));
        root.setController(testController);
        GridPane testPane = root.load();
        Scene testScene = new Scene(testPane, 1920, 1080);

        root = new FXMLLoader(getClass().getResource("FXML/mainStageScreen.fxml"));
        root.setController(stageController);
        GridPane stagePane = root.load();
        Scene stageScene = new Scene(stagePane, 1920, 1080);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(loadingScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

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

                        buildController.setP1532CurrentBuild(mapTool.getCurrentSingleValue("7734", posBuildMap));
                        buildController.setP1x30CurrentBuild(mapTool.getCurrentSingleValue("7743", posBuildMap));
                        buildController.setP1x35CurrentBuild(mapTool.getCurrentGroupValue(p1x35ProdList, posBuildMap));
                        buildController.setT1000sCurrentBuild(mapTool.getCurrentSingleValue("7744", posBuildMap));

                        //---------------------------------Retail Build-------------------------------------------------
                        retailBuildMap = dataBaseTool.retailBuildDataBase();

                        buildController.setXr7CurrentBuild(mapTool.getCurrentSingleValue("7702", retailBuildMap));
                        buildController.setXr7PlusCurrentBuild(mapTool.getCurrentSingleValue("7703", retailBuildMap));
                        buildController.setXr5CurrentBuild(mapTool.getCurrentSingleValue("7701", retailBuildMap));
                        buildController.setNextGenDisplayCurrentBuild(mapTool.getCurrentGroupValue(nextGenProdList, retailBuildMap));

                        //---------------------------------Servers Build------------------------------------------------
                        serversBuildMap = dataBaseTool.serversBuildDataBase();

                        buildController.setMediaPlayerCurrentBuild(mapTool.getCurrentSingleValue("1656", serversBuildMap));
                        buildController.setN3000CurrentBuild(mapTool.getCurrentSingleValue("1657", serversBuildMap));
                        buildController.setS500CurrentBuild(mapTool.getCurrentGroupValue(s500ProdList, serversBuildMap));

                        //---------------------------------Periph Build-------------------------------------------------
                        periphBuildMap = dataBaseTool.periphBuildDataBase();

                        buildController.setKiwi4sCurrentBuild(mapTool.getCurrentSingleValue("1924", periphBuildMap));
                        buildController.setKiwi2XsCurrentBuild(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphBuildMap));
                        buildController.setBumpBarsCurrentBuild(mapTool.getCurrentSingleValue("1635", periphBuildMap));
                        buildController.setPantherEPC4sCurrentBuild(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphBuildMap));

                        //---------------------------------Optic Build--------------------------------------------------
                        opticBuildMap = dataBaseTool.opticBuildDataBase();

                        buildController.setOptic5sCurrentBuild(mapTool.getCurrentSingleValue("6001", opticBuildMap));
                        buildController.setOptic12sCurrentBuild(mapTool.getCurrentSingleValue("6002", opticBuildMap));
                        buildController.setKitsCurrentBuild(mapTool.getCurrentSingleValue("6003", opticBuildMap));

                        System.out.println("\n***********Running Test Block.***********\n");
                        //---------------------------------Hosp Test-----------------------------------------------------------------------
                        posTestMap = dataBaseTool.hospTestDataBase();

                        testController.setP1532CurrentTest(mapTool.getCurrentSingleValue("7734", posTestMap));
                        testController.setP1x30CurrentTest(mapTool.getCurrentSingleValue("7743", posTestMap));
                        testController.setP1x35CurrentTest(mapTool.getCurrentGroupValue(p1x35ProdList, posTestMap));
                        testController.setT1000sCurrentTest(mapTool.getCurrentSingleValue("7744", posTestMap));

                        //---------------------------------Retail Test-----------------------------------------------------------------------
                        retailTestMap = dataBaseTool.retailTestDataBase();

                        testController.setXr7CurrentTest(mapTool.getCurrentSingleValue("7702", retailTestMap));
                        testController.setXr7PlusCurrentTest(mapTool.getCurrentSingleValue("7703", retailTestMap));
                        testController.setXr5CurrentTest(mapTool.getCurrentSingleValue("7701", retailTestMap));
                        testController.setNextGenDisplayCurrentTest(mapTool.getCurrentGroupValue(nextGenProdList, retailTestMap));

                        //---------------------------------Server Test-----------------------------------------------------------------------
                        serversTestMap = dataBaseTool.serversTestDataBase();


                        testController.setMediaPlayerCurrentTest(mapTool.getCurrentSingleValue("1656", serversTestMap));
                        testController.setN3000CurrentTest(mapTool.getCurrentSingleValue("1657", serversTestMap));
                        testController.setS500CurrentTest(mapTool.getCurrentGroupValue(s500ProdList, serversTestMap));

                        //---------------------------------Periph Test-----------------------------------------------------------------------
                        periphTestMap = dataBaseTool.periphTestDataBase();


                        testController.setKiwi4sCurrentTest(mapTool.getCurrentSingleValue("1924", periphTestMap));
                        testController.setKiwi2XsCurrentTest(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphTestMap));
                        testController.setBumpBarsCurrentTest(mapTool.getCurrentSingleValue("1635", periphTestMap));
                        testController.setPantherEPC4sCurrentTest(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphTestMap));

                        //---------------------------------Optic Test----------------------------------------------------------------------
                        opticTestMap = dataBaseTool.opticTestDataBase();


                        testController.setOptic5sCurrentTest(mapTool.getCurrentSingleValue("6001", opticTestMap));
                        testController.setOptic12sCurrentTest(mapTool.getCurrentSingleValue("6002", opticTestMap));
                        testController.setKitsCurrentTest(mapTool.getCurrentSingleValue("6003", opticTestMap));

                        System.out.println("\n***********Running Stage Block.***********\n");

                        //---------------------------------Hosp Staging-----------------------------------------------------------------------
                        posStageMap = dataBaseTool.hospStageDataBase();

                        posUserStageMap = dataBaseTool.hospStageDataBaseUsers();

                        stageController.setP1532CurrentStage(mapTool.getCurrentSingleValue("7734", posStageMap));
                        stageController.setP1x30CurrentStage(mapTool.getCurrentSingleValue("7743", posStageMap));
                        stageController.setP1x35CurrentStage(mapTool.getCurrentGroupValue(p1x35ProdList, posStageMap));
                        stageController.setT1000sCurrentStage(mapTool.getCurrentSingleValue("7744", posStageMap));

                        //---------------------------------Retail Stage-----------------------------------------------------------------------
                        retailStageMap = dataBaseTool.retailStageDataBase();
                        retailUserStageMap = dataBaseTool.retailStageDataBaseUsers();


                        stageController.setXr7CurrentStage(mapTool.getCurrentSingleValue("7702", retailStageMap));
                        stageController.setXr7PlusCurrentStage(mapTool.getCurrentSingleValue("7703", retailStageMap));
                        stageController.setXr5CurrentStage(mapTool.getCurrentSingleValue("7701", retailStageMap));
                        stageController.setNextGenDisplayCurrentStage(mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap));

                        //---------------------------------Servers Stage-----------------------------------------------------------------------
                        serversStageMap = dataBaseTool.serversStageDataBase();

                        serversUserStageMap = dataBaseTool.serverStageDataBaseUsers();


                        stageController.setMediaPlayerCurrentStage(mapTool.getCurrentSingleValue("1656", serversStageMap));
                        stageController.setN3000CurrentStage(mapTool.getCurrentSingleValue("1657", serversStageMap));
                        stageController.setS500CurrentStage(mapTool.getCurrentGroupValue(s500ProdList, serversStageMap));

                        //---------------------------------Periph Stage-----------------------------------------------------------------------
                        periphStageMap = dataBaseTool.periphStageDataBase();

                        periphUserStageMap = dataBaseTool.periphStageDataBaseUsers();

                        stageController.setKiwi4sCurrentStage(mapTool.getCurrentSingleValue("1924", periphStageMap));
                        stageController.setKiwi2XsCurrentStage(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphStageMap));
                        stageController.setBumpBarsCurrentStage(mapTool.getCurrentSingleValue("1635", periphStageMap));
                        stageController.setPantherEPC4sCurrentStage(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphStageMap));

                        System.out.println("\n***********Running Doc Block.***********\n");
                        mapList = dataBaseTool.documentReader();
                        stageMapList = dataBaseTool.stageDocumentReader();



                        buildController.setP1532GoalBuild(goalTool.getGoal(mapList.get(0), "7734"));
                        testController.setP1532GoalBuild(goalTool.getGoal(mapList.get(0), "7734"));
                        buildController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                        testController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                        buildController.setP1x35GoalBuild(goalTool.getListGoal(mapList.get(0), p1x35ProdList));
                        testController.setP1x35GoalBuild(goalTool.getListGoal(mapList.get(0), p1x35ProdList));
                        buildController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));
                        testController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));

                        stageController.setP1532GoalStage(goalTool.getGoal(stageMapList.get(0), "7734"));
                        stageController.setP1x30GoalStage(goalTool.getGoal(stageMapList.get(0), "7743"));
                        stageController.setP1x35GoalStage(goalTool.getListGoal(stageMapList.get(0), p1x35ProdList));
                        stageController.setT1000sGoalStage(goalTool.getGoal(stageMapList.get(0), "7744"));


                        //---------------------------------Percent Calculation and Update for POS-------------------------------
                        buildController.setPosTotalGoalBuild(buildController.getP1532GoalBuild() + buildController.getP1x30GoalBuild() + buildController.getP1x35GoalBuild() + buildController.getT1000sGoalBuild());
                        buildController.setPosTotalCurrentBuild(buildController.getP1532CurrentBuild() + buildController.getP1x35CurrentBuild() + buildController.getP1x30CurrentBuild() + buildController.getT1000sCurrentBuild());

                        testController.setPosTotalCurrentTest(testController.getP1532CurrentTest() + testController.getP1x35CurrentTest() + testController.getP1x30CurrentTest() + testController.getT1000sCurrentTest());

                        stageController.setPosTotalGoalStage(stageController.getP1532GoalStage() + stageController.getP1x30GoalStage() + stageController.getP1x35GoalStage() + stageController.getT1000sGoalStage());
                        stageController.setPosTotalCurrentStage(stageController.getP1532CurrentStage() + stageController.getP1x35CurrentStage() + stageController.getP1x30CurrentStage() + stageController.getT1000sCurrentStage());

                        buildController.setPosPercentTotalBuild(goalTool.getPercentTotal(buildController.getPosTotalCurrentBuild(), buildController.getPosTotalGoalBuild()));
                        testController.setPosPercentTotalTest(goalTool.getPercentTotal(testController.getPosTotalCurrentTest(), testController.getPosTotalGoalBuild()));
                        stageController.setPosPercentTotalStage(goalTool.getPercentTotal(stageController.getPosTotalCurrentStage(), stageController.getPosTotalGoalStage()));

                        //---------------------------------Retail Build---------------------------------------------------------
                        buildController.setXr7GoalBuild(goalTool.getGoal(mapList.get(4), "7702"));
                        buildController.setXr7PlusGoalBuild(goalTool.getGoal(mapList.get(4), "7703"));
                        buildController.setXr5GoalBuild(goalTool.getGoal(mapList.get(4), "7701"));
                        buildController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));

                        stageController.setXr7GoalStage(goalTool.getGoal(stageMapList.get(3), "7702"));
                        stageController.setXr7PlusGoalStage(goalTool.getGoal(stageMapList.get(3), "7703"));
                        stageController.setXr5GoalStage(goalTool.getGoal(stageMapList.get(3), "7701"));
                        stageController.setNextGenDisplayGoalsStage(goalTool.getListGoal(stageMapList.get(3), nextGenProdList));

                        //---------------------------------Percent Calculation and Update for Retail----------------------------
                        buildController.setRetailTotalGoalBuild(buildController.getXr7GoalBuild() + buildController.getXr7PlusGoalBuild() + buildController.getXr5GoalBuild() + buildController.getNextGenDisplayGoalsBuild());
                        buildController.setRetailTotalCurrentBuild(buildController.getXr7CurrentBuild() + buildController.getXr7PlusCurrentBuild() + buildController.getXr5CurrentBuild() + buildController.getNextGenDisplayCurrentBuild());

                        testController.setRetailTotalCurrentTest(testController.getXr7CurrentTest() + testController.getXr7PlusCurrentTest() + testController.getXr5CurrentTest() + testController.getNextGenDisplayCurrentTest());

                        stageController.setRetailTotalGoalStage(stageController.getXr7GoalStage() + stageController.getXr7PlusGoalStage() + stageController.getXr5GoalStage() + stageController.getNextGenDisplayGoalsStage());
                        stageController.setRetailTotalCurrentStage(stageController.getXr7CurrentStage() + stageController.getXr7PlusCurrentStage() + stageController.getXr5CurrentStage() + stageController.getNextGenDisplayCurrentStage());

                        buildController.setRetailPercentTotalBuild(goalTool.getPercentTotal(buildController.getRetailTotalCurrentBuild(), buildController.getRetailTotalGoalBuild()));
                        testController.setRetailPercentTotalTest(goalTool.getPercentTotal(testController.getRetailTotalCurrentTest(), testController.getRetailTotalGoalBuild()));
                        stageController.setRetailPercentTotalStage(goalTool.getPercentTotal(stageController.getRetailTotalCurrentStage(),stageController.getRetailTotalGoalStage()));

                        //---------------------------------Servers Build--------------------------------------------------------
                        buildController.setMediaPlayerGoalBuild(goalTool.getGoal(mapList.get(1), "1656"));
                        buildController.setN3000GoalBuild(goalTool.getGoal(mapList.get(1), "1657"));
                        buildController.setS500GoalBuild(goalTool.getGoal(mapList.get(1), "1611"));

                        stageController.setMediaPlayerGoalStage(goalTool.getGoal(stageMapList.get(1), "1656"));
                        stageController.setN3000GoalStage(goalTool.getGoal(stageMapList.get(1), "1657"));
                        stageController.setS500GoalStage(goalTool.getGoal(stageMapList.get(1), "1611"));

                        //---------------------------------Percent Calculation and Update for Servers---------------------------
                        buildController.setServerGoalTotalBuild(buildController.getMediaPlayerGoalBuild() + buildController.getN3000GoalBuild() + buildController.getS500GoalBuild());
                        buildController.setServerCurrentBuild(buildController.getMediaPlayerCurrentBuild() + buildController.getN3000CurrentBuild() + buildController.getS500CurrentBuild());

                        testController.setServerCurrentTest(testController.getMediaPlayerCurrentTest() + testController.getN3000CurrentTest() + testController.getS500CurrentTest());

                        stageController.setServerGoalTotalStage(stageController.getMediaPlayerGoalStage() + stageController.getN3000GoalStage() + stageController.getS500GoalStage());
                        stageController.setServerCurrentStage(stageController.getMediaPlayerCurrentStage() + stageController.getN3000CurrentStage() + stageController.getS500CurrentStage());


                        buildController.setServersPercentTotalBuild(goalTool.getPercentTotal(buildController.getServerCurrentBuild(),buildController.getServerGoalTotalBuild()));
                        testController.setServersPercentTotalTest(goalTool.getPercentTotal(testController.getServerCurrentTest(), buildController.getServerGoalTotalBuild()));
                        stageController.setServersPercentTotalStage(goalTool.getPercentTotal(stageController.getServerCurrentStage(), stageController.getServerGoalTotalStage()));

                        //---------------------------------Periph Build---------------------------------------------------------
                        buildController.setKiwi4sGoalBuild(goalTool.getGoal(mapList.get(3), "1924"));
                        buildController.setKiwi2XsGoalBuild(goalTool.getListGoal(mapList.get(3), kiwi2XsProdList));
                        buildController.setBumpBarsGoalBuild(goalTool.getGoal(mapList.get(3), "1635"));
                        buildController.setPantherEPC4sGoalBuild(goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList));

                        stageController.setKiwi4sGoalStage(goalTool.getGoal(stageMapList.get(2), "1924"));
                        stageController.setKiwi2XsGoalStage(goalTool.getListGoal(stageMapList.get(2), kiwi2XsProdList));
                        stageController.setBumpBarsGoalStage(goalTool.getGoal(stageMapList.get(2), "1635"));
                        stageController.setPantherEPC4sGoalStage(goalTool.getListGoal(stageMapList.get(2), pantherEPC4sProdList));

                        //---------------------------------Percent Calculation and Update for Periph----------------------------
                        buildController.setPeriphGoalTotalBuild(buildController.getKiwi4sGoalBuild() + buildController.getKiwi2XsGoalBuild() + buildController.getBumpBarsGoalBuild() + buildController.getPantherEPC4sGoalBuild());
                        buildController.setPeriphCurrentTotalBuild(buildController.getKiwi4sCurrentBuild() + buildController.getKiwi2XsCurrentBuild() + buildController.getBumpBarsCurrentBuild() + buildController.getPantherEPC4sCurrentBuild());

                        testController.setPeriphCurrentTotalTest(testController.getKiwi4sCurrentTest() + testController.getKiwi2XsCurrentTest() + testController.getBumpBarsCurrentTest() + testController.getPantherEPC4sCurrentTest());

                        stageController.setPeriphGoalTotalStage(stageController.getKiwi4sGoalStage() + stageController.getKiwi2XsGoalStage() +stageController.getBumpBarsGoalStage() + stageController.getPantherEPC4sGoalStage());
                        stageController.setPeriphCurrentTotalStage(stageController.getKiwi4sCurrentStage() + stageController.getKiwi2XsCurrentStage() + stageController.getBumpBarsCurrentStage() + stageController.getPantherEPC4sCurrentStage());


                        buildController.setPeriphPercentTotalBuild(goalTool.getPercentTotal(buildController.getPeriphCurrentTotalBuild(), buildController.getPeriphGoalTotalBuild()));
                        testController.setPeriphPercentTotalTest(goalTool.getPercentTotal(testController.getPeriphCurrentTotalTest(), buildController.getPeriphGoalTotalBuild()));
                        stageController.setPeriphPercentTotalStage(goalTool.getPercentTotal(stageController.getPeriphCurrentTotalStage(), stageController.getPeriphGoalTotalStage()));

                        //---------------------------------Optic Build----------------------------------------------------------
                        buildController.setOptic5sGoalBuild(goalTool.getGoal(mapList.get(2), "6001"));
                        buildController.setOptic12sGoalBuild(goalTool.getGoal(mapList.get(2), "6002"));
                        buildController.setKitsGoalBuild(goalTool.getGoal(mapList.get(2), "6003"));

                        //---------------------------------Percent Calculation and Update for Optic-----------------------------
                        buildController.setOpticGoalTotalBuild(buildController.getOptic5sGoalBuild() + buildController.getOptic12sGoalBuild() + buildController.getKitsGoalBuild());
                        buildController.setOpticCurrentTotalBuild(buildController.getOptic5sCurrentBuild() + buildController.getOptic12sCurrentBuild() + buildController.getKitsCurrentBuild());

                        testController.setOpticCurrentTotalTest(testController.getOptic5sCurrentTest() + testController.getOptic12sCurrentTest() + testController.getKitsCurrentTest());


                        buildController.setOpticPercentTotalBuild(goalTool.getPercentTotal(buildController.getOpticCurrentTotalBuild(), buildController.getOpticGoalTotalBuild()));
                        testController.setOpticPercentTotalTest(goalTool.getPercentTotal(testController.getOpticCurrentTotalTest(), buildController.getOpticGoalTotalBuild()));

                        buildController.refresh();
                        testController.refresh();
                        stageController.refresh();
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

                        if(primaryStage.getScene() == loadingScene)
                        {
                            System.out.println("HELP");
                            Platform.runLater(()-> primaryStage.setScene(navigationScene));
                            flag = false;
                        }


                        return null;
                    }
                };
            }
        };

        //---------------------------------Creating Animations for Graphs-----------------------------------------------


        //--------------------------------Scheduled State Params--------------------------------------------------------
        ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
        Bounds allScreenBounds = computeAllScreenBounds();

        buildVariables.setPeriod(Duration.seconds(10));

        buildVariables.setRestartOnFailure(true);

        buildVariables.start();

        //---------------------------------General Tile Handlers--------------------------------------------------------
//        ArrayList<Tile> tileList = new ArrayList<>();
//
//        tileList.add(pos);
//        tileList.add(posPercent);
//        tileList.add(retail);
//        tileList.add(retailPercent);
//        tileList.add(servers);
//        tileList.add(serversPercent);
//        tileList.add(peripherals);
//        tileList.add(periphPercent);
//        tileList.add(optic);
//        tileList.add(opticPercent);
//
//        tileList.add(posBuildFocused);
//        tileList.add(posPercentFocused);
//        tileList.add(posTestFocused);
//        tileList.add(posPercentTestFocused);
//        tileList.add(retailBuildFocused);
//        tileList.add(retailPercentFocused);
//        tileList.add(serversBuildFocused);
//        tileList.add(serversPercentFocused);
//        tileList.add(periphBuildFocused);
//        tileList.add(periphPercentFocused);
//        tileList.add(opticBuildFocused);
//        tileList.add(opticPercentFocused);
//
//
//        tileList.add(posTest);
//        tileList.add(posPercentTest);
//        tileList.add(retailTest);
//        tileList.add(retailPercentTest);
//        tileList.add(serversTest);
//        tileList.add(serversPercentTest);
//        tileList.add(peripheralsTest);
//        tileList.add(periphPercentTest);
//        tileList.add(opticTest);
//        tileList.add(opticPercentTest);
//
//        tileList.add(posTestFocused);
//        tileList.add(posPercentTestFocused);
//        tileList.add(retailTestFocused);
//        tileList.add(retailPercentTestFocused);
//        tileList.add(serversTestFocused);
//        tileList.add(serversPercentTestFocused);
//        tileList.add(periphTestFocused);
//        tileList.add(periphPercentTestFocused);
//        tileList.add(opticTestFocused);
//        tileList.add(opticPercentTestFocused);
//
//
//        tileList.add(posStage);
//        tileList.add(posPercentStage);
//        tileList.add(retailStage);
//        tileList.add(retailPercentStage);
//        tileList.add(serversStage);
//        tileList.add(serversPercentStage);
//        tileList.add(peripheralsStage);
//        tileList.add(periphPercentStage);
//
//        tileList.add(posStageFocused);
//        tileList.add(posPercentStageFocused);
//        tileList.add(retailStageFocused);
//        tileList.add(retailPercentStageFocused);
//        tileList.add(serversStageFocused);
//        tileList.add(serversPercentStageFocused);
//        tileList.add(periphStageFocused);
//        tileList.add(periphPercentStageFocused);
//
//
//        for(int i =0;i<tileList.size();i++)
//        {
//            Tile temp = tileList.get(i);
//
//            temp.setAnimated(true);
//            temp.setAnimationDuration(3000);
//
//            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    x = event.getSceneX();
//                    y = event.getSceneY();
//
//                }
//            });
//            tileList.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    temp.setBorderColor(Tile.GRAY);
//                    PauseTransition idle = new PauseTransition(Duration.millis(1000));
//                    temp.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
//                        temp.setCursor(Cursor.HAND);
//                        idle.playFromStart();
//                        temp.setBorderColor(Tile.GRAY);
//                    });
//                    idle.setOnFinished(e ->
//                    {
//                        temp.setCursor(Cursor.NONE);
//                        temp.setBorderColor(Color.TRANSPARENT);
//                    });
//                }
//            });
//            tileList.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    temp.setBorderColor(Color.TRANSPARENT);
//                }
//            });
//            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    temp.getScene().getWindow().setX(event.getScreenX() - x);
//                    temp.getScene().getWindow().setY(event.getScreenY() - y);
//                    if(temp.getScene().getWindow().getX() < allScreenBounds.getMinX())
//                    {
//                        temp.getScene().getWindow().setX(allScreenBounds.getMinX());
//
//                    }
//                    if(temp.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-1920))
//                    {
//                        temp.getScene().getWindow().setX(allScreenBounds.getMaxX()-1920);
//                    }
//                }
//            });
//        }
//
//        //---------------------------------Unique Tile Handlers---------------------------------------------------------
//
//
//        //---------------------------------Scene and Pane Creation------------------------------------------------------
//        flowPane.getChildren().addAll(pos,retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent, periphPercent,opticPercent);
//        flowPaneTest.getChildren().addAll(posTest,retailTest, serversTest, peripheralsTest, opticTest, posPercentTest, retailPercentTest,serversPercentTest, periphPercentTest,opticPercentTest);
//        flowPaneStage.getChildren().addAll(posStage,retailStage, serversStage, peripheralsStage, posPercentStage, retailPercentStage,serversPercentStage, periphPercentStage);
//        flowPanePOSBuildOrTestFocus.getChildren().addAll(posBuildFocused,posTestFocused,posPercentFocused,posPercentTestFocused);
//        flowPaneRetailBuildOrTestFocus.getChildren().addAll(retailBuildFocused,retailTestFocused,retailPercentFocused,retailPercentTestFocused);
//        flowPaneServersBuildOrTestFocus.getChildren().addAll(serversBuildFocused,serversTestFocused,serversPercentFocused,serversPercentTestFocused);
//        flowPanePeriphBuildOrTestFocus.getChildren().addAll(periphBuildFocused,periphTestFocused,periphPercentFocused,periphPercentTestFocused);
//        flowPaneOpticBuildOrTestFocus.getChildren().addAll(opticBuildFocused,opticTestFocused,opticPercentFocused,opticPercentTestFocused);
//        flowPanePOSStageFocus.getChildren().addAll(posStageFocused);
//        flowPaneRetailStageFocus.getChildren().addAll(retailStageFocused);
//        flowPaneServersStageFocus.getChildren().addAll(serversStageFocused);
//        flowPanePeriphStageFocus.getChildren().addAll(periphStageFocused);
//
//
//
//        ArrayList<FlowPane> flowList = new ArrayList<>();
//
//        flowList.add(flowPane);
//        flowList.add(flowPaneTest);
//        flowList.add(flowPaneStage);
//        flowList.add(flowPanePOSBuildOrTestFocus);
//        flowList.add(flowPaneRetailBuildOrTestFocus);
//        flowList.add(flowPaneServersBuildOrTestFocus);
//        flowList.add(flowPanePeriphBuildOrTestFocus);
//        flowList.add(flowPaneOpticBuildOrTestFocus);
//        flowList.add(flowPanePOSStageFocus);
//        flowList.add(flowPaneRetailStageFocus);
//        flowList.add(flowPaneServersStageFocus);
//        flowList.add(flowPanePeriphStageFocus);
//
//        for(int i = 0; i < flowList.size();i++)
//        {
//            flowList.get(i).setStyle("-fx-background-color: rgb(42, 42, 42)");
//            flowList.get(i).setPrefSize(1920, 1080);
//        }
//
//        Scene buildScene = new Scene(flowPane);
//        Scene testScene = new Scene (flowPaneTest);
//        Scene stageScene = new Scene (flowPaneStage);
//        Scene posBuildOrTestScene = new Scene(flowPanePOSBuildOrTestFocus);
//        Scene retailBuildOrTestScene = new Scene(flowPaneRetailBuildOrTestFocus);
//        Scene serversBuildOrTestScene = new Scene(flowPaneServersBuildOrTestFocus);
//        Scene periphBuildOrTestScene = new Scene(flowPanePeriphBuildOrTestFocus);
//        Scene opticBuildOrTestScene = new Scene(flowPaneOpticBuildOrTestFocus);
//        Scene posStageScene = new Scene(flowPanePOSStageFocus);
//        Scene retailStageScene = new Scene(flowPaneRetailStageFocus);
//        Scene serversStageScene = new Scene(flowPaneServersStageFocus);
//        Scene periphStageScene = new Scene(flowPanePeriphStageFocus);
//        Scene posUserScene = new Scene(posGridPane);
//        Scene retailUserScene = new Scene(retailGridPane);
//        Scene serversUserScene = new Scene(serversGridPane);
//        Scene periphUserScene = new Scene(periphGridPane);
//
//        final Scene[] previousScene = new Scene[1];
//
//        ArrayList<Scene> sceneList = new ArrayList<>();
//        sceneList.add(buildScene);
//        sceneList.add(testScene);
//        sceneList.add(stageScene);
//        //sceneList.add(clockOrQualityScene);
//
//        //---------------------------------General Scene Handlers-------------------------------------------------------
//        int previous = 0;
//        int next = 0;
//        for(int i = 0; i<sceneList.size();i++)
//        {
//            if(i != 0 && (i+1) < sceneList.size())
//            {
//                previous = i-1;
//                next = i + 1;
//            }
//            if(i == 0 && (i+1) <sceneList.size())
//            {
//                next = i + 1;
//            }
//            if(i == sceneList.size()-1)
//            {
//                previous = i-1;
//            }
//            int finalPrevious = previous;
//            int finalNext = next;
//            sceneList.get(i).setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    if(event.getCode() == KeyCode.LEFT)
//                    {
//                        primaryStage.setScene(sceneList.get(finalPrevious));
//                    }
//                    if(event.getCode() == KeyCode.RIGHT)
//                    {
//                        primaryStage.setScene(sceneList.get(finalNext));
//                    }
//                    if (event.getCode() == KeyCode.F4) {
//                        primaryStage.setIconified(true);
//                    }
//                    if (event.getCode() == KeyCode.F5) {
//                        screenMove(primaryStage,allScreenBounds,screens);
//                    }
//                }
//            });
//        }
//
//        //---------------------------------Unique Scene Handlers--------------------------------------------------------
//        pos.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = buildScene;
//                primaryStage.setScene(posBuildOrTestScene);
//            }
//        });
//        posTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = testScene;
//                primaryStage.setScene(posBuildOrTestScene);
//            }
//        });
//        posStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = stageScene;
//                primaryStage.setScene(posUserScene);
//                posUserScene.setCursor(Cursor.NONE);
//            }
//        });
//        posBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//            }
//        });
//        posStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        posUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//
//        retail.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = buildScene;
//                primaryStage.setScene(retailBuildOrTestScene);
//            }
//        });
//        retailTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = testScene;
//                primaryStage.setScene(retailBuildOrTestScene);
//            }
//        });
//        retailStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = stageScene;
//                primaryStage.setScene(retailUserScene);
//                retailUserScene.setCursor(Cursor.NONE);
//            }
//        });
//        retailBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        retailStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        retailUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//
//        servers.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = buildScene;
//                primaryStage.setScene(serversBuildOrTestScene);
//            }
//        });
//        serversTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = testScene;
//                primaryStage.setScene(serversBuildOrTestScene);
//            }
//        });
//        serversStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = stageScene;
//                primaryStage.setScene(serversUserScene);
//                serversUserScene.setCursor(Cursor.NONE);
//            }
//        });
//        serversBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        serversStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        serversUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//
//        peripherals.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = buildScene;
//                primaryStage.setScene(periphBuildOrTestScene);
//            }
//        });
//        peripheralsTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = testScene;
//                primaryStage.setScene(periphBuildOrTestScene);
//            }
//        });
//        peripheralsStage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = stageScene;
//                primaryStage.setScene(periphUserScene);
//                periphUserScene.setCursor(Cursor.NONE);
//            }
//        });
//        periphBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        periphStageScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//        periphUserScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
//
//        optic.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = buildScene;
//                primaryStage.setScene(opticBuildOrTestScene);
//            }
//        });
//        opticTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                previousScene[0] = testScene;
//                primaryStage.setScene(opticBuildOrTestScene);
//            }
//        });
//        opticBuildOrTestScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event)
//            {
//                if (event.getCode() == KeyCode.F5) {
//                    screenMove(primaryStage,allScreenBounds,screens);
//                }
//                if(event.getCode() == KeyCode.ESCAPE)
//                {
//                    primaryStage.setScene(previousScene[0]);
//                }
//                if(event.getCode() == KeyCode.F4)
//                {
//                    primaryStage.setIconified(true);
//                }
//
//            }
//        });
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