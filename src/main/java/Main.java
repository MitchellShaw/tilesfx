package main.java;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.java.eu.hansolo.tilesfx.Controllers.*;
import org.xml.sax.SAXException;
import main.java.eu.hansolo.tilesfx.tools.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;

import static java.time.temporal.ChronoUnit.DAYS;


/**
 * Created by Mitchell Shaw
 * Framework by hansolo
 * 2017-11-22.
 */
public class Main extends Application {
    //------------------------------------Variables Block---------------------------------------------------------------
    private Set<Thread> threadSet;
    private Thread[] threadArray;
    private double x = 0;
    private double y = 0;
    private boolean flag;
    private Messenger messenger;
    private long daysBetween;
    private int counter;
    private String useDate;

    private Thread.UncaughtExceptionHandler h;
    private long memoryBefore;
    private long memoryAfter;

    private long heapSize;
    private long heapMaxSize;
    private long heapFreeSize;

    private ArrayList<timeOrb> orbList;

    private HashMap<String, Integer> buildMap;
    //---------------------------------Variables for Map Creation for POS Database Call----------------------------
    private HashMap<String, Integer> posTestMap;
    private HashMap<String, Integer> posTestUserMap;
    private HashMap<String, Integer> posStageMap;
    private HashMap<String, Integer> posUserStageMap;
    //---------------------------------Variables for Map Creation for Retail Database Call-------------------------
    private HashMap<String, Integer> retailTestMap;
    private HashMap<String, Integer> retailTestUserMap;
    private HashMap<String, Integer> retailStageMap;
    private HashMap<String, Integer> retailUserStageMap;

    //---------------------------------Variables for Map Creation for Servers Database Call------------------------
    private HashMap<String, Integer> serversTestMap;
    private HashMap<String, Integer> serversTestUserMap;
    private HashMap<String, Integer> serversStageMap;
    private HashMap<String, Integer> serversUserStageMap;

    //---------------------------------Variables for Map Creation for Peripherals Database Call--------------------
    private HashMap<String, Integer> periphTestMap;
    private HashMap<String, Integer> periphTestUserMap;
    private HashMap<String, Integer> periphStageMap;
    private HashMap<String, Integer> periphUserStageMap;

    //---------------------------------Variables for Map Creation for Optic Database Call----------------------------
    private HashMap<String, Integer> opticBuildMap;
    private HashMap<String, Integer> opticTestMap;
    private HashMap<String, Integer> opticTestUserMap;

    //---------------------------------Variables for Map Creation for Document Reader---------------------------------
    private ArrayList<HashMap<String, Integer>> mapList;
    private ArrayList<HashMap<String, Integer>> stageMapList;

    private static String argument;

    private HashMap<String, Integer> lineMap;

    private FXMLLoader root;

    private Runtime rt;

    private LoadingController loadingController;
    private NavigationController navigationController;
    private TimeLineController timeLineController;
    private MainBuildController buildController;
    private MainTestController testController;
    private MainStageController stageController;
    private POSBuildController posBuildController;
    private RetailBuildController retailBuildController;
    private ServersBuildController serversBuildController;
    private PeriphBuildController periphBuildController;
    private OpticBuildController opticBuildController;
    private POSStageController posStageController;
    private RetailStageController retailStageController;
    private PeriphStageController periphStageController;
    private ServersStageController serversStageController;
    private posBuildOverviewController posBuildOverviewController;
    private retailBuildOverviewController retailBuildOverviewController;
    private periphBuildOverviewController periphBuildOverviewController;
    private serversBuildOverviewController serversBuildOverviewController;
    private opticBuildOverviewController opticBuildOverviewController;

    private nextGenDisplayLineController nextGenDisplayLineController;
    private nextGenLine1Controller nextGenLine1Controller;
    private nextGenLine2Controller nextGenLine2Controller;
    private nextGenLine3Controller nextGenLine3Controller;
    private nextGenLine4Controller nextGenLine4Controller;
    private nextGenLine5Controller nextGenLine5Controller;

    private opticLine1Controller opticLine1Controller;
    private opticLine2Controller opticLine2Controller;
    private opticLine3Controller opticLine3Controller;
    private opticLine4Controller opticLine4Controller;

    private periphLine1Controller periphLine1Controller;
    private periphLine2Controller periphLine2Controller;

    private posLine1Controller posLine1Controller;
    private posLine2Controller posLine2Controller;
    private posLine3Controller posLine3Controller;
    private posLine4Controller posLine4Controller;
    private posQuestController posQuestController;
    private posT1000Controller posT1000Controller;

    private serversLine1Controller serversLine1Controller;
    private serversLine2Controller serversLine2Controller;

    private periphTestUserController periphTestUserController;
    private posTestUserController posTestUserController;
    private retailTestUserController retailTestUserController;
    private serversTestUserController serversTestUserController;
    private opticTestUserController opticTestUserController;

    private qualityHomeController qualityHomeController;

    private String date;

    private void setMessenger(Methods _class)
    {
        _class.setMessenger(messenger);
    }

    public static void main(String[] args)
    {
        System.out.println(args.length + " number of arguments.");
        if(args.length >0)
        {
            argument = args[0];
        }
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //---------------------------------Creating the Tools for the graphs--------------------------------------------
        rt = Runtime.getRuntime();
        Tool dataBaseTool = new Tool();

        MapTool mapTool = new MapTool();
        GoalTool goalTool = new GoalTool();

        //---------------------------------List Storage Area for Running Group Values ----------------------------------
        //posBar1ProdList.add("7734");
        ArrayList<String> posBar1ProdList = new ArrayList<>(Arrays.asList("7734", "7745", "7761"));
        /*posBar1ProdList.add("7745");
        posBar1ProdList.add("7761");*/

        ArrayList<String> questProdList = new ArrayList<>(Arrays.asList("7791", "7792"));
        /*questProdList.add("7791");
        questProdList.add("7792");*/

        ArrayList<String> kiwi2XsProdList = new ArrayList<>(Arrays.asList("1642", "1924"));
        /*kiwi2XsProdList.add("1642");
        kiwi2XsProdList.add("1924");*/

        ArrayList<String> pantherEPC4sProdList = new ArrayList<>(Arrays.asList("1646", "1651"));
        /*pantherEPC4sProdList.add("1646");
        pantherEPC4sProdList.add("1651");*/

        ArrayList<String> xrProdList = new ArrayList<>(Arrays.asList("7701","7702","7703"));
        /*xrProdList.add("7701");
        xrProdList.add("7702");
        xrProdList.add("7703");*/

        ArrayList<String> nextGenProdList = new ArrayList<>(Arrays.asList("497","5985"));
        /*nextGenProdList.add("497");
        nextGenProdList.add("5985");*/

        ArrayList<String> s500ProdList = new ArrayList<>(Arrays.asList("1611","1612"));
        /*s500ProdList.add("1611");
        s500ProdList.add("1612");*/

        ArrayList<String> mediaProdList = new ArrayList<>(Arrays.asList("1656","1657"));
        /*mediaProdList.add("1656");
        mediaProdList.add("1657");*/

        loadingController = new LoadingController();
        navigationController = new NavigationController();
        timeLineController = new TimeLineController();
        buildController = new MainBuildController();
        testController = new MainTestController();
        stageController = new MainStageController();
        posBuildController = new POSBuildController();
        retailBuildController = new RetailBuildController();
        serversBuildController = new ServersBuildController();
        periphBuildController = new PeriphBuildController();
        opticBuildController = new OpticBuildController();
        posStageController = new POSStageController();
        retailStageController = new RetailStageController();
        periphStageController = new PeriphStageController();
        serversStageController = new ServersStageController();

        Resolutionizer resolutionizer = new Resolutionizer();

        posBuildOverviewController = new posBuildOverviewController();
        retailBuildOverviewController = new retailBuildOverviewController();
        serversBuildOverviewController = new serversBuildOverviewController();
        periphBuildOverviewController = new periphBuildOverviewController();
        opticBuildOverviewController = new opticBuildOverviewController();

        nextGenDisplayLineController = new nextGenDisplayLineController();
        nextGenLine1Controller = new nextGenLine1Controller();
        nextGenLine2Controller = new nextGenLine2Controller();
        nextGenLine3Controller = new nextGenLine3Controller();
        nextGenLine4Controller = new nextGenLine4Controller();
        nextGenLine5Controller = new nextGenLine5Controller();

        opticLine1Controller = new opticLine1Controller();
        opticLine2Controller = new opticLine2Controller();
        opticLine3Controller = new opticLine3Controller();
        opticLine4Controller = new opticLine4Controller();

        periphLine1Controller = new periphLine1Controller();
        periphLine2Controller = new periphLine2Controller();

        posLine1Controller = new posLine1Controller();
        posLine2Controller = new posLine2Controller();
        posLine3Controller = new posLine3Controller();
        posLine4Controller = new posLine4Controller();
        posQuestController = new posQuestController();
        posT1000Controller = new posT1000Controller();

        serversLine1Controller = new serversLine1Controller();
        serversLine2Controller = new serversLine2Controller();

        periphTestUserController = new periphTestUserController();
        serversTestUserController = new serversTestUserController();
        retailTestUserController = new retailTestUserController();
        opticTestUserController = new opticTestUserController();
        posTestUserController = new posTestUserController();

        qualityHomeController = new qualityHomeController();

        ArrayList<Controller> controllers = new ArrayList<>();

        controllers.add(loadingController);
        controllers.add(navigationController);
        controllers.add(timeLineController);
        controllers.add(buildController);
        controllers.add(testController);
        controllers.add(stageController);
        controllers.add(posBuildController);
        controllers.add(retailBuildController);
        controllers.add(serversBuildController);
        controllers.add(periphBuildController);
        controllers.add(opticBuildController);
        controllers.add(posStageController);
        controllers.add(retailStageController);
        controllers.add(periphStageController);
        controllers.add(serversStageController);
        controllers.add(posBuildOverviewController);
        controllers.add(retailBuildOverviewController);
        controllers.add(periphBuildOverviewController);
        controllers.add(serversBuildOverviewController);
        controllers.add(opticBuildOverviewController);
        controllers.add(nextGenDisplayLineController);
        controllers.add(nextGenLine1Controller);
        controllers.add(nextGenLine2Controller);
        controllers.add(nextGenLine3Controller);
        controllers.add(nextGenLine4Controller);
        controllers.add(nextGenLine5Controller);
        controllers.add(opticLine1Controller);
        controllers.add(opticLine2Controller);
        controllers.add(opticLine3Controller);
        controllers.add(opticLine4Controller);
        controllers.add(periphLine1Controller);
        controllers.add(periphLine2Controller);
        controllers.add(posLine1Controller);
        controllers.add(posLine2Controller);
        controllers.add(posLine3Controller);
        controllers.add(posLine4Controller);
        controllers.add(posQuestController);
        controllers.add(posT1000Controller);
        controllers.add(serversLine1Controller);
        controllers.add(serversLine2Controller);
        controllers.add(periphTestUserController);
        controllers.add(posTestUserController);
        controllers.add(retailTestUserController);
        controllers.add(serversTestUserController);
        controllers.add(opticTestUserController);
        controllers.add(qualityHomeController);


        messenger = new Messenger(loadingController, navigationController, timeLineController, buildController, testController, stageController, posBuildController, retailBuildController, serversBuildController, periphBuildController, opticBuildController, posStageController, retailStageController, periphStageController, primaryStage);

        Controller controller = new Controller();

        controller.setMessenger(messenger);

        messenger.setPosBuildOverviewController(posBuildOverviewController);
        messenger.setRetailBuildOverviewController(retailBuildOverviewController);
        messenger.setServersBuildOverviewController(serversBuildOverviewController);
        messenger.setPeriphBuildOverviewController(periphBuildOverviewController);
        messenger.setOpticBuildOverviewController(opticBuildOverviewController);

        messenger.setServersLine1Controller(serversLine1Controller);

        messenger.setResolutionizer(resolutionizer);

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
        serversStageController.setMessenger(messenger);
        loadingController.setMessenger(messenger);
        posBuildOverviewController.setMessenger(messenger);
        retailBuildOverviewController.setMessenger(messenger);
        serversBuildOverviewController.setMessenger(messenger);
        periphBuildOverviewController.setMessenger(messenger);
        opticBuildOverviewController.setMessenger(messenger);

        nextGenDisplayLineController.setMessenger(messenger);
        nextGenLine1Controller.setMessenger(messenger);
        nextGenLine2Controller.setMessenger(messenger);
        nextGenLine3Controller.setMessenger(messenger);
        nextGenLine4Controller.setMessenger(messenger);
        nextGenLine5Controller.setMessenger(messenger);

        opticLine1Controller.setMessenger(messenger);
        opticLine2Controller.setMessenger(messenger);
        opticLine3Controller.setMessenger(messenger);
        opticLine4Controller.setMessenger(messenger);

        periphLine1Controller.setMessenger(messenger);
        periphLine2Controller.setMessenger(messenger);

        posLine1Controller.setMessenger(messenger);
        posLine2Controller.setMessenger(messenger);
        posLine3Controller.setMessenger(messenger);
        posLine4Controller.setMessenger(messenger);
        posQuestController.setMessenger(messenger);
        posT1000Controller.setMessenger(messenger);

        serversLine1Controller.setMessenger(messenger);
        serversLine2Controller.setMessenger(messenger);

        periphTestUserController.setMessenger(messenger);
        serversTestUserController.setMessenger(messenger);
        opticTestUserController.setMessenger(messenger);
        retailTestUserController.setMessenger(messenger);
        posTestUserController.setMessenger(messenger);

        qualityHomeController.setMessenger(messenger);

        root = new FXMLLoader(this.getClass().getResource("/FXML/timeLine.fxml"));
        root.setController(timeLineController);
        GridPane timePane = root.load();
        Scene timeScene = new Scene(timePane, 800, 600);
        timeScene.getStylesheets().add(getClass().getResource("/timeLine.css").toExternalForm());
        messenger.setTimelineScene(timeScene);

        root = new FXMLLoader(getClass().getResource("/FXML/LoadingScreen.fxml"));
        root.setController(loadingController);
        GridPane loadPane = root.load();
        Scene loadingScene = new Scene(loadPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());

        root = new FXMLLoader(getClass().getResource("/FXML/NavigationScreen.fxml"));
        root.setController(navigationController);
        GridPane navigationPane = root.load();
        Scene navigationScene = new Scene(navigationPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        navigationScene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNavigationScene(navigationScene);

        root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
        root.setController(testController);
        GridPane testPane = root.load();
        Scene testScene = new Scene(testPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainTest(testScene);

        root = new FXMLLoader(getClass().getResource("/FXML/mainStageScreen.fxml"));
        root.setController(stageController);
        GridPane stagePane = root.load();
        Scene stageScene = new Scene(stagePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainStage(stageScene);

        root = new FXMLLoader(getClass().getResource("/FXML/mainBuildScreen.fxml"));
        root.setController(buildController);
        GridPane buildPane = root.load();
        Scene buildScene = new Scene(buildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainBuild(buildScene);


        root = new FXMLLoader(getClass().getResource("/FXML/opticBuildScreen.fxml"));
        root.setController(opticBuildController);
        GridPane opticBuildPane = root.load();
        Scene opticBuildScene = new Scene(opticBuildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setOpticBuild(opticBuildScene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphBuildScreen.fxml"));
        root.setController(periphBuildController);
        GridPane periphBuildPane = root.load();
        Scene periphBuildScene = new Scene(periphBuildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphBuild(periphBuildScene);

        root = new FXMLLoader(getClass().getResource("/FXML/posBuildScreen.fxml"));
        root.setController(posBuildController);
        GridPane posBuildPane = root.load();
        Scene posBuildScene = new Scene(posBuildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosBuild(posBuildScene);

        root = new FXMLLoader(getClass().getResource("/FXML/retailBuildScreen.fxml"));
        root.setController(retailBuildController);
        GridPane retailBuildPane = root.load();
        Scene retailBuildScene = new Scene(retailBuildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailBuild(retailBuildScene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversBuildScreen.fxml"));
        root.setController(serversBuildController);
        GridPane serversBuildPane = root.load();
        Scene serversBuildScene = new Scene(serversBuildPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServerBuild(serversBuildScene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphStageScreen.fxml"));
        root.setController(periphStageController);
        GridPane periphStagePane = root.load();
        Scene periphStageScene = new Scene(periphStagePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphStage(periphStageScene);

        root = new FXMLLoader(getClass().getResource("/FXML/posStageScreen.fxml"));
        root.setController(posStageController);
        GridPane posStagePane = root.load();
        Scene posStageScene = new Scene(posStagePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosStage(posStageScene);

        root = new FXMLLoader(getClass().getResource("/FXML/retailStageScreen.fxml"));
        root.setController(retailStageController);
        GridPane retailStagePane = root.load();
        Scene retailStageScene = new Scene(retailStagePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailStage(retailStageScene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversStageScreen.fxml"));
        root.setController(serversStageController);
        GridPane serversStagePane = root.load();
        Scene serversStageScene = new Scene(serversStagePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServerStage(serversStageScene);

        root = new FXMLLoader(getClass().getResource("/FXML/posBuildOverview.fxml"));
        root.setController(posBuildOverviewController);
        GridPane posBuildOverviewPane = root.load();
        Scene posBuildOverviewScene = new Scene(posBuildOverviewPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosBuildOverview(posBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphBuildOverview.fxml"));
        root.setController(periphBuildOverviewController);
        GridPane periphBuildOverviewPane = root.load();
        Scene periphBuildOverviewScene = new Scene(periphBuildOverviewPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphBuildOverview(periphBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("/FXML/retailBuildOverview.fxml"));
        root.setController(retailBuildOverviewController);
        GridPane retailBuildOverviewPane = root.load();
        Scene retailBuildOverviewScene = new Scene(retailBuildOverviewPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailBuildOverview(retailBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversBuildOverview.fxml"));
        root.setController(serversBuildOverviewController);
        GridPane serversBuildOverviewPane = root.load();
        Scene serversBuildOverviewScene = new Scene(serversBuildOverviewPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServersBuildOverview(serversBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticBuildOverview.fxml"));
        root.setController(opticBuildOverviewController);
        GridPane opticBuildOverviewPane = root.load();
        Scene opticBuildOverviewScene = new Scene(opticBuildOverviewPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setOpticBuildOverview(opticBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversLine1.fxml"));
        root.setController(serversLine1Controller);
        GridPane serversLine1Pane = root.load();
        Scene serversLine1Scene = new Scene(serversLine1Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        serversLine1Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setServersLine1(serversLine1Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversLine2.fxml"));
        root.setController(serversLine2Controller);
        GridPane serversLine2Pane = root.load();
        Scene serversLine2Scene = new Scene(serversLine2Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        serversLine2Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setServersLine2(serversLine2Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenDisplay.fxml"));
        root.setController(nextGenDisplayLineController);
        GridPane nextGenDisplayPane = root.load();
        Scene nextGenDisplayScene = new Scene(nextGenDisplayPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenDisplayScene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenDisplay(nextGenDisplayScene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenLine1.fxml"));
        root.setController(nextGenLine1Controller);
        GridPane nextGenLine1Pane = root.load();
        Scene nextGenLine1Scene = new Scene(nextGenLine1Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine1Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine1(nextGenLine1Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenLine2.fxml"));
        root.setController(nextGenLine2Controller);
        GridPane nextGenLine2Pane = root.load();
        Scene nextGenLine2Scene = new Scene(nextGenLine2Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine2Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine2(nextGenLine2Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenLine3.fxml"));
        root.setController(nextGenLine3Controller);
        GridPane nextGenLine3Pane = root.load();
        Scene nextGenLine3Scene = new Scene(nextGenLine3Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine3Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine3(nextGenLine3Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenLine4.fxml"));
        root.setController(nextGenLine4Controller);
        GridPane nextGenLine4Pane = root.load();
        Scene nextGenLine4Scene = new Scene(nextGenLine4Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine4Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine4(nextGenLine4Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/nextGenLine5.fxml"));
        root.setController(nextGenLine5Controller);
        GridPane nextGenLine5Pane = root.load();
        Scene nextGenLine5Scene = new Scene(nextGenLine5Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine5Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine5(nextGenLine5Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticLine1.fxml"));
        root.setController(opticLine1Controller);
        GridPane opticLine1Pane = root.load();
        Scene opticLine1Scene = new Scene(opticLine1Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine1Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setOpticLine1(opticLine1Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticLine2.fxml"));
        root.setController(opticLine2Controller);
        GridPane opticLine2Pane = root.load();
        Scene opticLine2Scene = new Scene(opticLine2Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine2Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setOpticLine2(opticLine2Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticLine3.fxml"));
        root.setController(opticLine3Controller);
        GridPane opticLine3Pane = root.load();
        Scene opticLine3Scene = new Scene(opticLine3Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine3Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setOpticLine3(opticLine3Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticLine4.fxml"));
        root.setController(opticLine4Controller);
        GridPane opticLine4Pane = root.load();
        Scene opticLine4Scene = new Scene(opticLine4Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine4Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setOpticLine4(opticLine4Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphLine1.fxml"));
        root.setController(periphLine1Controller);
        GridPane periphLine1Pane = root.load();
        Scene periphLine1Scene = new Scene(periphLine1Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        periphLine1Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPeriphLine1(periphLine1Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphLine2.fxml"));
        root.setController(periphLine2Controller);
        GridPane periphLine2Pane = root.load();
        Scene periphLine2Scene = new Scene(periphLine2Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        periphLine2Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPeriphLine2(periphLine2Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/posLine1.fxml"));
        root.setController(posLine1Controller);
        GridPane posLine1Pane = root.load();
        Scene posLine1Scene = new Scene(posLine1Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine1Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosLine1(posLine1Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/posLine2.fxml"));
        root.setController(posLine2Controller);
        GridPane posLine2Pane = root.load();
        Scene posLine2Scene = new Scene(posLine2Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine2Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosLine2(posLine2Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/posLine3.fxml"));
        root.setController(posLine3Controller);
        GridPane posLine3Pane = root.load();
        Scene posLine3Scene = new Scene(posLine3Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine3Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosLine3(posLine3Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/posLine1.fxml"));
        root.setController(posLine4Controller);
        GridPane posLine4Pane = root.load();
        Scene posLine4Scene = new Scene(posLine4Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine4Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosLine4(posLine4Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/posQuest.fxml"));
        root.setController(posQuestController);
        GridPane posQuestPane = root.load();
        Scene posQuestScene = new Scene(posQuestPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posQuestScene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosQuest(posQuestScene);

        root = new FXMLLoader(getClass().getResource("/FXML/posLine1.fxml"));
        root.setController(posT1000Controller);
        GridPane posT1000Pane = root.load();
        Scene posT1000Scene = new Scene(posT1000Pane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posT1000Scene.getStylesheets().add(getClass().getResource("/BasicStyle.css").toExternalForm());
        messenger.setPosT1000(posT1000Scene);

        root = new FXMLLoader(getClass().getResource("/FXML/periphTestUsers.fxml"));
        root.setController(periphTestUserController);
        GridPane periphTestUserPane = root.load();
        Scene periphTestUserScene = new Scene(periphTestUserPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphTestUser(periphTestUserScene);

        root = new FXMLLoader(getClass().getResource("/FXML/posTestUsers.fxml"));
        root.setController(posTestUserController);
        GridPane posTestUserPane = root.load();
        Scene posTestUserScene = new Scene(posTestUserPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosTestUser(posTestUserScene);

        root = new FXMLLoader(getClass().getResource("/FXML/retailTestUsers.fxml"));
        root.setController(retailTestUserController);
        GridPane retailTestUserPane = root.load();
        Scene retailTestUserScene = new Scene(retailTestUserPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailTestUser(retailTestUserScene);

        root = new FXMLLoader(getClass().getResource("/FXML/opticTestUsers.fxml"));
        root.setController(opticTestUserController);
        GridPane opticTestUserPane = root.load();
        Scene opticTestUserScene = new Scene(opticTestUserPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setOpticTestUser(opticTestUserScene);

        root = new FXMLLoader(getClass().getResource("/FXML/serversTestUsers.fxml"));
        root.setController(serversTestUserController);
        GridPane serversTestUserPane = root.load();
        Scene serversTestUserScene = new Scene(serversTestUserPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServersTestUser(serversTestUserScene);

        root = new FXMLLoader(getClass().getResource("/FXML/qualityHome.fxml"));
        root.setController(qualityHomeController);
        GridPane qualityHomePane = root.load();
        Scene qualityHomeScene = new Scene(qualityHomePane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setQualityHome(qualityHomeScene);

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
                        try {
                            //System.out.println("\n***********Running Build Block.***********\n");


                            //---------------------------------Hosp Build---------------------------------------------------
                            heapSize = Runtime.getRuntime().totalMemory();
                            heapMaxSize = Runtime.getRuntime().maxMemory();
                            heapFreeSize = Runtime.getRuntime().freeMemory();

//                            System.out.println("\n");
//                            System.out.println("Current heap size: "+heapSize);
//                            System.out.println("Max heap size: "+heapMaxSize);
//                            System.out.println("Free heap size: "+heapFreeSize);

                            //logger.info("Current heap size: "+heapSize);

                            buildMap = dataBaseTool.buildQuery();

                            buildController.setPosBar1Total(mapTool.getCurrentGroupValue(posBar1ProdList, buildMap));
                            buildController.setP1x30CurrentBuild(mapTool.getCurrentSingleValue("7743", buildMap));
                            buildController.setT1000sCurrentBuild(mapTool.getCurrentSingleValue("7744", buildMap));
                            buildController.setQuestCurrentBuild(mapTool.getCurrentGroupValue(questProdList, buildMap));
                            //---------------------------------Retail Build-------------------------------------------------
                            buildController.setRetailBar1Total(mapTool.getCurrentGroupValue(xrProdList, buildMap));
                            buildController.setNextGenDisplayCurrentBuild(mapTool.getCurrentGroupValue(nextGenProdList, buildMap));
                            //---------------------------------Servers Build------------------------------------------------
                            buildController.setServerBar1Total(mapTool.getCurrentGroupValue(s500ProdList, buildMap));
                            buildController.setServerBar2Total(mapTool.getCurrentGroupValue(mediaProdList, buildMap));
                            //---------------------------------Periph Build-------------------------------------------------
                            buildController.setPeriphBar1Total(mapTool.getCurrentSingleValue("1635", buildMap));
                            buildController.setPeriphBar2Total(mapTool.getCurrentGroupValue(kiwi2XsProdList, buildMap));
                            buildController.setPeriphBar3Total(mapTool.getCurrentGroupValue(pantherEPC4sProdList, buildMap));
                            //---------------------------------Optic Build--------------------------------------------------
                            buildController.setOptic5sCurrentBuild(mapTool.getCurrentSingleValue("6001", buildMap));
                            buildController.setOptic12sCurrentBuild(mapTool.getCurrentSingleValue("6002", buildMap));
                            buildController.setKitsCurrentBuild(mapTool.getCurrentSingleValue("6003", buildMap));


                            //System.out.println("\n***********Running Test Block.***********\n");
                            //---------------------------------Hosp Test-----------------------------------------------------------------------
                            posTestMap = dataBaseTool.hospTestDataBase();

                            posTestUserMap = dataBaseTool.hospTestDatabaseUsers();

                            posTestUserController.setUserMap(posTestUserMap);

                            testController.setP1x30CurrentTest(mapTool.getCurrentSingleValue("7743", posTestMap));
                            testController.setPosBar1Total(mapTool.getCurrentGroupValue(posBar1ProdList, posTestMap));
                            testController.setT1000sCurrentTest(mapTool.getCurrentSingleValue("7744", posTestMap));
                            testController.setQuestsCurrentTest(mapTool.getCurrentGroupValue(questProdList, posTestMap));


                            //---------------------------------Retail Test-----------------------------------------------------------------------
                            retailTestMap = dataBaseTool.retailTestDataBase();

                            retailTestUserMap = dataBaseTool.retailTestDatabaseUsers();

                            retailTestUserController.setUserMap(retailTestUserMap);

                            testController.setRetailBar1Total(mapTool.getCurrentGroupValue(xrProdList, retailTestMap));
                            testController.setNextGenDisplayCurrentTest(mapTool.getCurrentGroupValue(nextGenProdList, retailTestMap));

                            //---------------------------------Server Test-----------------------------------------------------------------------
                            serversTestMap = dataBaseTool.serversTestDataBase();

                            serversTestUserMap = dataBaseTool.serversTestDatabaseUsers();

                            serversTestUserController.setUserMap(serversTestUserMap);

                            testController.setServerBar1Total(mapTool.getCurrentGroupValue(s500ProdList, serversTestMap));
                            testController.setServerBar2Total(mapTool.getCurrentGroupValue(mediaProdList, serversTestMap));

                            //---------------------------------Periph Test-----------------------------------------------------------------------
                            periphTestMap = dataBaseTool.periphTestDataBase();

                            periphTestUserMap = dataBaseTool.periphTestDatabaseUsers();

                            testController.setPeriphBar1Total(mapTool.getCurrentSingleValue("1635", periphTestMap));
                            testController.setPeriphBar2Total(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphTestMap));
                            testController.setPeriphBar3Total(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphTestMap));

                            periphTestUserController.setUserMap(periphTestUserMap);

                            //---------------------------------Optic Test----------------------------------------------------------------------
                            opticTestMap = dataBaseTool.opticTestDataBase();

                            opticTestUserMap = dataBaseTool.opticTestDatabaseUsers();

                            opticTestUserController.setUserMap(opticTestUserMap);

                            testController.setOptic12sCurrentTest(mapTool.getCurrentSingleValue("6002", opticTestMap));
                            testController.setKitsCurrentTest(mapTool.getCurrentSingleValue("6003", opticTestMap));


                            //System.out.println("\n***********Running Stage Block.***********\n");

                            //---------------------------------Hosp Staging-----------------------------------------------------------------------
                            posStageMap = dataBaseTool.hospStageDataBase();

                            posUserStageMap = dataBaseTool.hospStageDataBaseUsers();

                            stageController.setP1x30CurrentStage(mapTool.getCurrentSingleValue("7743", posStageMap));
                            stageController.setPosBar1Total(mapTool.getCurrentGroupValue(posBar1ProdList, posStageMap));
                            stageController.setT1000sCurrentStage(mapTool.getCurrentSingleValue("7744", posStageMap));
                            stageController.setQuestsCurrentStage(mapTool.getCurrentGroupValue(questProdList, posStageMap));

                            //---------------------------------Retail Stage-----------------------------------------------------------------------
                            retailStageMap = dataBaseTool.retailStageDataBase();
                            retailUserStageMap = dataBaseTool.retailStageDataBaseUsers();


                            stageController.setRetailBar1Total(mapTool.getCurrentGroupValue(xrProdList, retailStageMap));
                            stageController.setNextGenDisplayCurrentStage(mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap));

                            //---------------------------------Servers Stage-----------------------------------------------------------------------
                            serversStageMap = dataBaseTool.serversStageDataBase();
                            serversUserStageMap = dataBaseTool.serverStageDataBaseUsers();

                            stageController.setServerBar1Total(mapTool.getCurrentGroupValue(s500ProdList, serversStageMap));
                            stageController.setServerBar2Total(mapTool.getCurrentGroupValue(mediaProdList, serversStageMap));

                            //---------------------------------Periph Stage-----------------------------------------------------------------------
                            periphStageMap = dataBaseTool.periphStageDataBase();

                            periphUserStageMap = dataBaseTool.periphStageDataBaseUsers();

                            stageController.setPeriphBar1Total(mapTool.getCurrentSingleValue("1635", periphStageMap));
                            stageController.setPeriphBar2Total(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphStageMap));
                            stageController.setPeriphBar3Total(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphStageMap));

                            //System.out.println("\n***********Running Doc Block.***********\n");
                            mapList = dataBaseTool.documentReader();
                            stageMapList = dataBaseTool.stageDocumentReader();

                            buildController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                            testController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                            buildController.setPosBar1Goal(goalTool.getListGoal(mapList.get(0), posBar1ProdList));
                            testController.setPosBar1Goal(goalTool.getListGoal(mapList.get(0), posBar1ProdList));
                            buildController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));
                            testController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));
                            buildController.setQuestGoalBuild(goalTool.getListGoal(mapList.get(0), questProdList));
                            testController.setQuestGoalBuild(goalTool.getListGoal(mapList.get(0), questProdList));

                            stageController.setP1x30GoalStage(goalTool.getGoal(stageMapList.get(0), "7743"));
                            stageController.setPosBar1Goal(goalTool.getListGoal(stageMapList.get(0), posBar1ProdList));
                            stageController.setT1000sGoalStage(goalTool.getGoal(stageMapList.get(0), "7744"));
                            stageController.setQuestsGoalStage(goalTool.getListGoal(stageMapList.get(0), questProdList));


                            //---------------------------------Percent Calculation and Update for POS-------------------------------
                            buildController.setPosTotalGoalBuild(buildController.getP1x30GoalBuild() + buildController.getPosBar1Goal() + buildController.getT1000sGoalBuild() + buildController.getQuestGoalBuild());
                            buildController.setPosTotalCurrentBuild(buildController.getPosBar1Total() + buildController.getP1x30CurrentBuild() + buildController.getT1000sCurrentBuild() + buildController.getQuestCurrentBuild());

                            testController.setPosTotalCurrentTest(testController.getPosBar1Total() + testController.getP1x30CurrentTest() + testController.getT1000sCurrentTest() + testController.getQuestsCurrentTest());
                            testController.setPosTotalGoalBuild(buildController.getPosTotalGoalBuild());

                            stageController.setPosTotalGoalStage(stageController.getPosBar1Goal() + stageController.getP1x30GoalStage() + stageController.getQuestsGoalStage() + stageController.getT1000sGoalStage());
                            stageController.setPosTotalCurrentStage(stageController.getPosBar1Total() + stageController.getP1x30CurrentStage() + stageController.getT1000sCurrentStage());

                            buildController.setPosPercentTotalBuild(goalTool.getPercentTotal(buildController.getPosTotalCurrentBuild(), buildController.getPosTotalGoalBuild()));
                            testController.setPosPercentTotalTest(goalTool.getPercentTotal(testController.getPosTotalCurrentTest(), testController.getPosTotalGoalBuild()));
                            stageController.setPosPercentTotalStage(goalTool.getPercentTotal(stageController.getPosTotalCurrentStage(), stageController.getPosTotalGoalStage()));

                            //---------------------------------Retail Build---------------------------------------------------------
                            buildController.setRetailBar1Goal(goalTool.getListGoal(mapList.get(4), xrProdList));
                            testController.setRetailBar1Goal(goalTool.getListGoal(mapList.get(4), xrProdList));
                            buildController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));
                            testController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));

                            stageController.setRetailBar1Goal(goalTool.getListGoal(stageMapList.get(3), xrProdList));
                            stageController.setNextGenDisplayGoalsStage(goalTool.getListGoal(stageMapList.get(3), nextGenProdList));

                            //---------------------------------Percent Calculation and Update for Retail----------------------------
                            buildController.setRetailTotalGoalBuild(buildController.getRetailBar1Goal() + buildController.getNextGenDisplayGoalsBuild());
                            buildController.setRetailTotalCurrentBuild(buildController.getRetailBar1Total() + buildController.getNextGenDisplayCurrentBuild());

                            testController.setRetailTotalCurrentTest(testController.getRetailBar1Total() + testController.getNextGenDisplayCurrentTest());
                            testController.setRetailTotalGoalBuild(buildController.getRetailTotalGoalBuild());

                            stageController.setRetailTotalGoalStage(stageController.getRetailBar1Goal() + stageController.getNextGenDisplayGoalsStage());
                            stageController.setRetailTotalCurrentStage(stageController.getRetailBar1Total() + stageController.getNextGenDisplayCurrentStage());

                            buildController.setRetailPercentTotalBuild(goalTool.getPercentTotal(buildController.getRetailTotalCurrentBuild(), buildController.getRetailTotalGoalBuild()));
                            testController.setRetailPercentTotalTest(goalTool.getPercentTotal(testController.getRetailTotalCurrentTest(), testController.getRetailTotalGoalBuild()));
                            stageController.setRetailPercentTotalStage(goalTool.getPercentTotal(stageController.getRetailTotalCurrentStage(), stageController.getRetailTotalGoalStage()));

                            //---------------------------------Servers Build--------------------------------------------------------
                            buildController.setServerBar1Goal(goalTool.getListGoal(mapList.get(1), s500ProdList));
                            testController.setServerBar1Goal(goalTool.getListGoal(mapList.get(1), s500ProdList));

                            buildController.setServerBar2Goal(goalTool.getListGoal(mapList.get(1), mediaProdList));
                            testController.setServerBar2Goal(goalTool.getListGoal(mapList.get(1), mediaProdList));

                            stageController.setServerBar1Goal(goalTool.getListGoal(stageMapList.get(1), s500ProdList));
                            stageController.setServerBar2Goal(goalTool.getListGoal(stageMapList.get(1), mediaProdList));

                            //---------------------------------Percent Calculation and Update for Servers---------------------------
                            buildController.setServerGoalTotalBuild(buildController.getServerBar1Goal() + buildController.getServerBar2Goal());
                            buildController.setServerCurrentBuild(buildController.getServerBar1Total() + buildController.getServerBar2Total());

                            testController.setServerCurrentTest(testController.getServerBar1Total() + testController.getServerBar2Total());
                            testController.setServerGoalTotalBuild(buildController.getServerGoalTotalBuild());

                            stageController.setServerGoalTotalStage(stageController.getServerBar1Goal() + stageController.getServerBar2Goal());
                            stageController.setServerCurrentStage(stageController.getServerBar1Total() + stageController.getServerBar2Total());

                            buildController.setServersPercentTotalBuild(goalTool.getPercentTotal(buildController.getServerCurrentBuild(), buildController.getServerGoalTotalBuild()));
                            testController.setServersPercentTotalTest(goalTool.getPercentTotal(testController.getServerCurrentTest(), buildController.getServerGoalTotalBuild()));
                            stageController.setServersPercentTotalStage(goalTool.getPercentTotal(stageController.getServerCurrentStage(), stageController.getServerGoalTotalStage()));

                            //---------------------------------Periph Build---------------------------------------------------------
                            buildController.setPeriphBar1Goal(goalTool.getGoal(mapList.get(3), "1635"));
                            testController.setPeriphBar1Goal(goalTool.getGoal(mapList.get(3), "1635"));
                            buildController.setPeriphBar2Goal(goalTool.getListGoal(mapList.get(3), kiwi2XsProdList));
                            testController.setPeriphBar2Goal(goalTool.getListGoal(mapList.get(3), kiwi2XsProdList));
                            buildController.setPeriphBar3Goal(goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList));
                            testController.setPeriphBar3Goal(goalTool.getListGoal(mapList.get(3), pantherEPC4sProdList));

                            stageController.setPeriphBar1Goal(goalTool.getGoal(stageMapList.get(2), "1635"));
                            stageController.setPeriphBar2Goal(goalTool.getListGoal(stageMapList.get(2), kiwi2XsProdList));
                            stageController.setPeriphBar3Goal(goalTool.getListGoal(stageMapList.get(2), pantherEPC4sProdList));

                            //---------------------------------Percent Calculation and Update for Periph----------------------------
                            buildController.setPeriphGoalTotalBuild(buildController.getPeriphBar1Goal() + buildController.getPeriphBar2Goal() + buildController.getPeriphBar3Goal());
                            buildController.setPeriphCurrentTotalBuild(buildController.getPeriphBar1Total() + buildController.getPeriphBar2Total() + buildController.getPeriphBar3Total());

                            testController.setPeriphCurrentTotalTest(testController.getPeriphBar1Total() + testController.getPeriphBar2Total() + testController.getPeriphBar3Total());
                            testController.setPeriphGoalTotalBuild(buildController.getPeriphGoalTotalBuild());

                            stageController.setPeriphGoalTotalStage(stageController.getPeriphBar1Goal() + stageController.getPeriphBar2Goal() + stageController.getPeriphBar3Goal());
                            stageController.setPeriphCurrentTotalStage(stageController.getPeriphBar1Total() + stageController.getPeriphBar2Total() + stageController.getPeriphBar3Total());

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

                            //System.out.println("\n***********Running Line Block.**************\n");

                            lineMap = dataBaseTool.buildLineQuery();

                            retailBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG1", lineMap));
                            retailBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG2", lineMap));
                            retailBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG3", lineMap));
                            retailBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG4", lineMap));
                            retailBuildOverviewController.setLine5Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG5", lineMap));
                            retailBuildOverviewController.setLine6Total((int) mapTool.getCurrentSingleValue("MIDLAND.DISPLAY_NG1", lineMap));

                            posBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_B", lineMap));
                            posBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_E", lineMap));
                            posBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_D", lineMap));
                            posBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_A", lineMap));
                            posBuildOverviewController.setLine5Total((int) mapTool.getCurrentSingleValue("MIDLAND.T1000", lineMap));
                            posBuildOverviewController.setLine6Total((int) mapTool.getCurrentSingleValue("MIDLAND.QUEST", lineMap));

                            periphBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.ED_A", lineMap));
                            periphBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.ED_B", lineMap));

                            opticBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_1", lineMap));
                            opticBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_2", lineMap));
                            opticBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_3", lineMap));
                            opticBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_4", lineMap));

                            serversBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.SERVERS", lineMap));
                            serversBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.SERVERS_2", lineMap));

                            orbList = dataBaseTool.buildTimeQuery();

                            messenger.setOrbList(orbList);

                            //---------------------------------This is some hacky shit-------------------------------------------
                            //System.out.println("\n***********Dynamic Creation Block***********\n");

                            buildController.setOpticThrough(dataBaseTool.opticFTTDataBase());
                            buildController.setPeriphThrough(dataBaseTool.periphFTTDataBase());
                            buildController.setServersThrough(dataBaseTool.serversFTTDataBase());
                            buildController.setRetailThrough(dataBaseTool.retailFTTDataBase());
                            buildController.setPosThrough(dataBaseTool.hospFTTDataBase());

                            posStageController.setUserMap(posUserStageMap);
                            retailStageController.setUserMap(retailUserStageMap);
                            periphStageController.setUserMap(periphUserStageMap);
                            serversStageController.setUserMap(serversUserStageMap);

                            date = null;
                            try {
                                date = dataBaseTool.incidentReader();
                            } catch (IOException | ParserConfigurationException | SAXException e) {
                                e.printStackTrace();
                            }

                            LocalDate currentDate = LocalDate.now();
                            LocalDate incidentDate = LocalDate.parse(date);

                            daysBetween = DAYS.between(incidentDate, currentDate);

                            counter = Math.toIntExact(daysBetween);

                            useDate = Integer.toString(counter);

                            for(int i = 0; i < controllers.size();i++)
                            {
                                controllers.get(i).setUseDate(useDate);
                            }

                            Platform.runLater(() ->
                            {
                                buildController.refresh();
                                testController.refresh();
                                stageController.refresh();
                                opticBuildController.refresh();
                                retailBuildController.refresh();
                                serversBuildController.refresh();
                                periphBuildController.refresh();
                                posBuildController.refresh();
                                periphStageController.refresh();
                                posStageController.refresh();
                                retailStageController.refresh();
                                serversStageController.refresh();
                                posBuildOverviewController.refresh();
                                retailBuildOverviewController.refresh();
                                serversBuildOverviewController.refresh();
                                periphBuildOverviewController.refresh();
                                opticBuildOverviewController.refresh();
                                serversLine1Controller.refresh();
                                serversLine2Controller.refresh();
                                nextGenLine1Controller.refresh();
                                nextGenLine2Controller.refresh();
                                nextGenLine3Controller.refresh();
                                nextGenLine4Controller.refresh();
                                nextGenLine5Controller.refresh();
                                nextGenDisplayLineController.refresh();
                                opticLine1Controller.refresh();
                                opticLine2Controller.refresh();
                                opticLine3Controller.refresh();
                                opticLine4Controller.refresh();
                                periphLine1Controller.refresh();
                                periphLine2Controller.refresh();
                                posLine1Controller.refresh();
                                posLine2Controller.refresh();
                                posLine3Controller.refresh();
                                posLine4Controller.refresh();
                                posQuestController.refresh();
                                posT1000Controller.refresh();
                                periphTestUserController.refresh();
                                posTestUserController.refresh();
                                retailTestUserController.refresh();
                                serversTestUserController.refresh();
                                opticTestUserController.refresh();
                                qualityHomeController.refresh();
                            });


                            if (primaryStage.getScene() == loadingScene)
                            {
                                Platform.runLater(() -> primaryStage.setScene(navigationScene));
                                flag = false;

                                 threadSet = Thread.getAllStackTraces().keySet();
                                 threadArray = threadSet.toArray(new Thread[threadSet.size()]);

                                for(int i = 0; i <threadSet.size();i++)
                                {
                                    threadArray[i].setUncaughtExceptionHandler(h);
                                }

                            }

                            System.out.println("\n");
                            System.out.println("Running garbage collector.");
                            System.out.println("Free memory before: " +rt.freeMemory()/1000000);
                            rt.gc();
                            System.out.println("Free memory after: "+rt.freeMemory()/1000000);

                        }catch(OutOfMemoryError e)
                        {
                            //logger.info(e.toString());
                            rt.exec("\\\\susmid8000\\d\\jre-9.0.4\\bin\\java.exe -XX:+UseG1GC -Xmx512m -jar \"\\\\SUSMID8000\\d\\Metrics Dashboard\\Metrics Dashboard V2\\app\\Floor Version (Java 9)\\Metrics Dashboard Version 2.jar\"" + argument +"\n" +
                                    "pause");
                            System.exit(0);
                         }
                         catch(Exception e)
                         {
                             //logger.info(e.toString());
                             System.out.println("Exception: "+e);
                         }
                        return null;
                    }
                };
            }
        };

        //--------------------------------Scheduled State Params--------------------------------------------------------
        buildVariables.setPeriod(Duration.seconds(15));

        buildVariables.setRestartOnFailure(true);

        buildVariables.start();

        h = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable ex) {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                System.out.println(timeStamp);
                System.out.println("Uncaught exception: " + ex);
                ex.printStackTrace();
                System.out.println("Restarting app...");
                try {
                    rt.exec("\\\\susmid8000\\d\\jre-9.0.4\\bin\\java.exe -XX:+UseG1GC -Xmx512m -jar \"\\\\SUSMID8000\\d\\Metrics Dashboard\\Metrics Dashboard V2\\app\\Floor Version (Java 9)\\Metrics Dashboard Version 2.jar\"\n" +
                            "pause\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        };
    }
}