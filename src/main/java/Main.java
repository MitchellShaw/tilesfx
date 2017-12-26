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
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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

        FlowGridPane posGridPane = new FlowGridPane(4, 5);
        FlowGridPane retailGridPane = new FlowGridPane(4, 5);
        FlowGridPane serversGridPane = new FlowGridPane(4, 5);
        FlowGridPane periphGridPane = new FlowGridPane(4, 5);

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
        TimeLineController timeLineController = new TimeLineController();
        MainBuildController buildController = new MainBuildController();
        MainTestController testController = new MainTestController();
        MainStageController stageController = new MainStageController();
        POSBuildController posBuildController = new POSBuildController();
        RetailBuildController retailBuildController = new RetailBuildController();
        ServersBuildController serversBuildController = new ServersBuildController();
        PeriphBuildController periphBuildController = new PeriphBuildController();
        OpticBuildController opticBuildController = new OpticBuildController();
        POSStageController posStageController = new POSStageController();
        RetailStageController retailStageController = new RetailStageController();
        PeriphStageController periphStageController = new PeriphStageController();

        messenger = new Messenger(loadingController, navigationController, timeLineController, buildController, testController, stageController, posBuildController, retailBuildController, serversBuildController, periphBuildController, opticBuildController, posStageController, retailStageController, periphStageController, primaryStage);

        timeLineController.setMessenger(messenger);
        navigationController.setMessenger(messenger);
        buildController.setMessenger(messenger);
        testController.setMessenger(messenger);
        stageController.setMessenger(messenger);
        posBuildController.setMessenger(messenger);
        retailBuildController.setMessenger(messenger);
        serversBuildController.setMessenger(messenger);
        periphBuildController.setMessenger(messenger);
        opticBuildController.setMessenger(messenger);
        posStageController.setMessenger(messenger);
        retailStageController.setMessenger(messenger);
        periphStageController.setMessenger(messenger);

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

                        //serversUserStageMap = dataBaseTool.serverStageDataBaseUsers();


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
                        testController.setPosTotalGoalBuild(buildController.getPosTotalGoalBuild());

                        stageController.setPosTotalGoalStage(stageController.getP1532GoalStage() + stageController.getP1x30GoalStage() + stageController.getP1x35GoalStage() + stageController.getT1000sGoalStage());
                        stageController.setPosTotalCurrentStage(stageController.getP1532CurrentStage() + stageController.getP1x35CurrentStage() + stageController.getP1x30CurrentStage() + stageController.getT1000sCurrentStage());

                        buildController.setPosPercentTotalBuild(goalTool.getPercentTotal(buildController.getPosTotalCurrentBuild(), buildController.getPosTotalGoalBuild()));
                        testController.setPosPercentTotalTest(goalTool.getPercentTotal(testController.getPosTotalCurrentTest(), testController.getPosTotalGoalBuild()));
                        stageController.setPosPercentTotalStage(goalTool.getPercentTotal(stageController.getPosTotalCurrentStage(), stageController.getPosTotalGoalStage()));

                        //---------------------------------Retail Build---------------------------------------------------------
                        buildController.setXr7GoalBuild(goalTool.getGoal(mapList.get(4), "7702"));
                        testController.setXr7GoalBuild(goalTool.getGoal(mapList.get(4), "7702"));
                        buildController.setXr7PlusGoalBuild(goalTool.getGoal(mapList.get(4), "7703"));
                        testController.setXr7PlusGoalBuild(goalTool.getGoal(mapList.get(4), "7703"));
                        buildController.setXr5GoalBuild(goalTool.getGoal(mapList.get(4), "7701"));
                        testController.setXr5GoalBuild(goalTool.getGoal(mapList.get(4), "7701"));
                        buildController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));
                        testController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));

                        stageController.setXr7GoalStage(goalTool.getGoal(stageMapList.get(3), "7702"));
                        stageController.setXr7PlusGoalStage(goalTool.getGoal(stageMapList.get(3), "7703"));
                        stageController.setXr5GoalStage(goalTool.getGoal(stageMapList.get(3), "7701"));
                        stageController.setNextGenDisplayGoalsStage(goalTool.getListGoal(stageMapList.get(3), nextGenProdList));

                        //---------------------------------Percent Calculation and Update for Retail----------------------------
                        buildController.setRetailTotalGoalBuild(buildController.getXr7GoalBuild() + buildController.getXr7PlusGoalBuild() + buildController.getXr5GoalBuild() + buildController.getNextGenDisplayGoalsBuild());
                        buildController.setRetailTotalCurrentBuild(buildController.getXr7CurrentBuild() + buildController.getXr7PlusCurrentBuild() + buildController.getXr5CurrentBuild() + buildController.getNextGenDisplayCurrentBuild());

                        testController.setRetailTotalCurrentTest(testController.getXr7CurrentTest() + testController.getXr7PlusCurrentTest() + testController.getXr5CurrentTest() + testController.getNextGenDisplayCurrentTest());
                        testController.setRetailTotalGoalBuild(buildController.getRetailTotalGoalBuild());
                        stageController.setRetailTotalGoalStage(stageController.getXr7GoalStage() + stageController.getXr7PlusGoalStage() + stageController.getXr5GoalStage() + stageController.getNextGenDisplayGoalsStage());
                        stageController.setRetailTotalCurrentStage(stageController.getXr7CurrentStage() + stageController.getXr7PlusCurrentStage() + stageController.getXr5CurrentStage() + stageController.getNextGenDisplayCurrentStage());

                        buildController.setRetailPercentTotalBuild(goalTool.getPercentTotal(buildController.getRetailTotalCurrentBuild(), buildController.getRetailTotalGoalBuild()));
                        testController.setRetailPercentTotalTest(goalTool.getPercentTotal(testController.getRetailTotalCurrentTest(), testController.getRetailTotalGoalBuild()));
                        stageController.setRetailPercentTotalStage(goalTool.getPercentTotal(stageController.getRetailTotalCurrentStage(), stageController.getRetailTotalGoalStage()));

                        //---------------------------------Servers Build--------------------------------------------------------
                        buildController.setMediaPlayerGoalBuild(goalTool.getGoal(mapList.get(1), "1656"));
                        testController.setMediaPlayerGoalBuild(goalTool.getGoal(mapList.get(1), "1656"));
                        buildController.setN3000GoalBuild(goalTool.getGoal(mapList.get(1), "1657"));
                        testController.setN3000GoalBuild(goalTool.getGoal(mapList.get(1), "1657"));
                        buildController.setS500GoalBuild(goalTool.getGoal(mapList.get(1), "1611"));
                        testController.setS500GoalBuild(goalTool.getGoal(mapList.get(1), "1611"));

                        stageController.setMediaPlayerGoalStage(goalTool.getGoal(stageMapList.get(1), "1656"));
                        stageController.setN3000GoalStage(goalTool.getGoal(stageMapList.get(1), "1657"));
                        stageController.setS500GoalStage(goalTool.getGoal(stageMapList.get(1), "1611"));

                        //---------------------------------Percent Calculation and Update for Servers---------------------------
                        buildController.setServerGoalTotalBuild(buildController.getMediaPlayerGoalBuild() + buildController.getN3000GoalBuild() + buildController.getS500GoalBuild());
                        buildController.setServerCurrentBuild(buildController.getMediaPlayerCurrentBuild() + buildController.getN3000CurrentBuild() + buildController.getS500CurrentBuild());

                        testController.setServerCurrentTest(testController.getMediaPlayerCurrentTest() + testController.getN3000CurrentTest() + testController.getS500CurrentTest());
                        testController.setServerGoalTotalBuild(buildController.getServerGoalTotalBuild());

                        stageController.setServerGoalTotalStage(stageController.getMediaPlayerGoalStage() + stageController.getN3000GoalStage() + stageController.getS500GoalStage());
                        stageController.setServerCurrentStage(stageController.getMediaPlayerCurrentStage() + stageController.getN3000CurrentStage() + stageController.getS500CurrentStage());


                        buildController.setServersPercentTotalBuild(goalTool.getPercentTotal(buildController.getServerCurrentBuild(), buildController.getServerGoalTotalBuild()));
                        testController.setServersPercentTotalTest(goalTool.getPercentTotal(testController.getServerCurrentTest(), buildController.getServerGoalTotalBuild()));
                        stageController.setServersPercentTotalStage(goalTool.getPercentTotal(stageController.getServerCurrentStage(), stageController.getServerGoalTotalStage()));

                        //---------------------------------Periph Build---------------------------------------------------------
                        buildController.setKiwi4sGoalBuild(goalTool.getGoal(mapList.get(3), "1924"));
                        testController.setKiwi4sGoalBuild(goalTool.getGoal(mapList.get(3), "1924"));
                        buildController.setKiwi2XsGoalBuild(goalTool.getListGoal(mapList.get(3), kiwi2XsProdList));
                        testController.setKiwi2XsGoalBuild(goalTool.getListGoal(mapList.get(3), kiwi2XsProdList));
                        buildController.setBumpBarsGoalBuild(goalTool.getGoal(mapList.get(3), "1635"));
                        testController.setBumpBarsGoalBuild(goalTool.getGoal(mapList.get(3), "1635"));
                        buildController.setPantherEPC4sGoalBuild(goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList));
                        testController.setPantherEPC4sGoalBuild(goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList));

                        stageController.setKiwi4sGoalStage(goalTool.getGoal(stageMapList.get(2), "1924"));
                        stageController.setKiwi2XsGoalStage(goalTool.getListGoal(stageMapList.get(2), kiwi2XsProdList));
                        stageController.setBumpBarsGoalStage(goalTool.getGoal(stageMapList.get(2), "1635"));
                        stageController.setPantherEPC4sGoalStage(goalTool.getListGoal(stageMapList.get(2), pantherEPC4sProdList));

                        //---------------------------------Percent Calculation and Update for Periph----------------------------
                        buildController.setPeriphGoalTotalBuild(buildController.getKiwi4sGoalBuild() + buildController.getKiwi2XsGoalBuild() + buildController.getBumpBarsGoalBuild() + buildController.getPantherEPC4sGoalBuild());
                        buildController.setPeriphCurrentTotalBuild(buildController.getKiwi4sCurrentBuild() + buildController.getKiwi2XsCurrentBuild() + buildController.getBumpBarsCurrentBuild() + buildController.getPantherEPC4sCurrentBuild());

                        testController.setPeriphCurrentTotalTest(testController.getKiwi4sCurrentTest() + testController.getKiwi2XsCurrentTest() + testController.getBumpBarsCurrentTest() + testController.getPantherEPC4sCurrentTest());
                        testController.setPeriphGoalTotalBuild(buildController.getPeriphGoalTotalBuild());

                        stageController.setPeriphGoalTotalStage(stageController.getKiwi4sGoalStage() + stageController.getKiwi2XsGoalStage() + stageController.getBumpBarsGoalStage() + stageController.getPantherEPC4sGoalStage());
                        stageController.setPeriphCurrentTotalStage(stageController.getKiwi4sCurrentStage() + stageController.getKiwi2XsCurrentStage() + stageController.getBumpBarsCurrentStage() + stageController.getPantherEPC4sCurrentStage());


                        buildController.setPeriphPercentTotalBuild(goalTool.getPercentTotal(buildController.getPeriphCurrentTotalBuild(), buildController.getPeriphGoalTotalBuild()));
                        testController.setPeriphPercentTotalTest(goalTool.getPercentTotal(testController.getPeriphCurrentTotalTest(), buildController.getPeriphGoalTotalBuild()));
                        stageController.setPeriphPercentTotalStage(goalTool.getPercentTotal(stageController.getPeriphCurrentTotalStage(), stageController.getPeriphGoalTotalStage()));

                        //---------------------------------Optic Build----------------------------------------------------------
                        buildController.setOptic5sGoalBuild(goalTool.getGoal(mapList.get(2), "6001"));
                        testController.setOptic5sGoalBuild(goalTool.getGoal(mapList.get(2), "6001"));
                        buildController.setOptic12sGoalBuild(goalTool.getGoal(mapList.get(2), "6002"));
                        testController.setOptic12sGoalBuild(goalTool.getGoal(mapList.get(2), "6002"));
                        buildController.setKitsGoalBuild(goalTool.getGoal(mapList.get(2), "6003"));
                        testController.setKitsGoalBuild(goalTool.getGoal(mapList.get(2), "6003"));

                        //---------------------------------Percent Calculation and Update for Optic-----------------------------
                        buildController.setOpticGoalTotalBuild(buildController.getOptic5sGoalBuild() + buildController.getOptic12sGoalBuild() + buildController.getKitsGoalBuild());
                        buildController.setOpticCurrentTotalBuild(buildController.getOptic5sCurrentBuild() + buildController.getOptic12sCurrentBuild() + buildController.getKitsCurrentBuild());

                        testController.setOpticCurrentTotalTest(testController.getOptic12sCurrentTest());
                        testController.setOpticGoalTotalBuild(buildController.getOpticGoalTotalBuild());

                        buildController.setOpticPercentTotalBuild(goalTool.getPercentTotal(buildController.getOpticCurrentTotalBuild(), buildController.getOpticGoalTotalBuild()));
                        testController.setOpticPercentTotalTest(goalTool.getPercentTotal(testController.getOpticCurrentTotalTest(), buildController.getOpticGoalTotalBuild()));

                        //---------------------------------This is some hacky shit-------------------------------------------
                        System.out.println("\n***********Dynamic Creation Block***********\n");

                        buildController.setOpticThrough(dataBaseTool.opticFTTDataBase());
                        buildController.setPeriphThrough(dataBaseTool.periphFTTDataBase());
                        buildController.setServersThrough(dataBaseTool.serversFTTDataBase());
                        buildController.setRetailThrough(dataBaseTool.retailFTTDataBase());
                        buildController.setPosThrough(dataBaseTool.hospFTTDataBase());


                        posStageController.setUsers(getCharTiles(posUserStageMap));
                        retailStageController.setUsers(getCharTiles(retailUserStageMap));
                        periphStageController.setUsers(getCharTiles(periphUserStageMap));

                        String date = null;
                        try {
                            date = dataBaseTool.incidentReader();
                        } catch (IOException | ParserConfigurationException | SAXException e) {
                            e.printStackTrace();
                        }

                        LocalDate currentDate = LocalDate.now();
                        LocalDate incidentDate = LocalDate.parse(date);

                        long daysBetween = DAYS.between(incidentDate, currentDate);

                        int counter = Math.toIntExact(daysBetween);

                        String useDate = Integer.toString(counter);

                        opticBuildController.setUseDate(useDate);
                        retailBuildController.setUseDate(useDate);
                        serversBuildController.setUseDate(useDate);
                        periphBuildController.setUseDate(useDate);
                        posBuildController.setUseDate(useDate);

                        posStageController.setUseDate(useDate);
                        periphStageController.setUseDate(useDate);
                        retailStageController.setUseDate(useDate);


                        Platform.runLater(() -> buildController.refresh());
                        Platform.runLater(() -> testController.refresh());
                        Platform.runLater(() -> stageController.refresh());

                        Platform.runLater( ()->opticBuildController.refresh());
                        Platform.runLater( ()->retailBuildController.refresh());
                        Platform.runLater( ()->serversBuildController.refresh());
                        Platform.runLater( ()->periphBuildController.refresh());
                        Platform.runLater( ()->posBuildController.refresh());

                        Platform.runLater( ()->periphStageController.refresh());
                        Platform.runLater( ()->posStageController.refresh());
                        Platform.runLater( ()->retailStageController.refresh());


                        if (primaryStage.getScene() == loadingScene) {
                            Platform.runLater(() -> primaryStage.setScene(navigationScene));
                            flag = false;
                        }

                        System.out.println("Help");
                        System.gc();
                        System.out.println("GC Done");

                        return null;
                    }
                };
            }
        };

        //--------------------------------Scheduled State Params--------------------------------------------------------
        buildVariables.setPeriod(Duration.seconds(10));

        buildVariables.setRestartOnFailure(true);

        buildVariables.start();
    }

    private ArrayList<Tile> getCharTiles (HashMap<String,Integer> userMap)
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
                    .prefSize(384,216)
                    .title(user)
                    .roundedCorners(false)
                    .titleAlignment(TextAlignment.CENTER)
                    .description(formatted)
                    .build();

            tiles.add(characterTile);
        }
        tiles.sort(Comparator.comparing(Tile::getDescription));
        Collections.reverse(tiles);


        return tiles;
    }
}