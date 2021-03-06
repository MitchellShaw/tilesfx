import eu.hansolo.tilesfx.Controllers.*;
import eu.hansolo.tilesfx.tools.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    ArrayList<timeOrb> orbList;

    HashMap<String, Integer> buildMap;
    //---------------------------------Variables for Map Creation for POS Database Call----------------------------
    HashMap<String, Integer> posTestMap;
    HashMap<String, Integer> posStageMap;
    HashMap<String, Integer> posUserStageMap;
    //---------------------------------Variables for Map Creation for Retail Database Call-------------------------
    HashMap<String, Integer> retailTestMap;
    HashMap<String, Integer> retailStageMap;
    HashMap<String, Integer> retailUserStageMap;

    //---------------------------------Variables for Map Creation for Servers Database Call------------------------
    HashMap<String, Integer> serversTestMap;
    HashMap<String, Integer> serversStageMap;
    HashMap<String, Integer> serversUserStageMap;

    //---------------------------------Variables for Map Creation for Peripherals Database Call--------------------
    HashMap<String, Integer> periphTestMap;
    HashMap<String, Integer> periphStageMap;
    HashMap<String, Integer> periphUserStageMap;

    //---------------------------------Variables for Map Creation for Optic Database Call----------------------------
    HashMap<String, Integer> opticBuildMap;
    HashMap<String, Integer> opticTestMap;

    //---------------------------------Variables for Map Creation for Document Reader---------------------------------
    ArrayList<HashMap<String, Integer>> mapList;
    ArrayList<HashMap<String, Integer>> stageMapList;

    HashMap<String,Integer> lineMap;

    FXMLLoader root;

    LoadingController loadingController;
    NavigationController navigationController;
    TimeLineController timeLineController;
    MainBuildController buildController;
    MainTestController testController;
    MainStageController stageController;
    POSBuildController posBuildController;
    RetailBuildController retailBuildController;
    ServersBuildController serversBuildController;
    PeriphBuildController periphBuildController;
    OpticBuildController opticBuildController;
    POSStageController posStageController;
    RetailStageController retailStageController;
    PeriphStageController periphStageController;
    posBuildOverviewController posBuildOverviewController;
    retailBuildOverviewController retailBuildOverviewController;
    periphBuildOverviewController periphBuildOverviewController;
    serversBuildOverviewController serversBuildOverviewController;
    opticBuildOverviewController opticBuildOverviewController;

    nextGenDisplayLineController nextGenDisplayLineController;
    nextGenLine1Controller nextGenLine1Controller;
    nextGenLine2Controller nextGenLine2Controller;
    nextGenLine3Controller nextGenLine3Controller;
    nextGenLine4Controller nextGenLine4Controller;
    nextGenLine5Controller nextGenLine5Controller;

    opticLine1Controller opticLine1Controller;
    opticLine2Controller opticLine2Controller;
    opticLine3Controller opticLine3Controller;
    opticLine4Controller opticLine4Controller;

    periphLine1Controller periphLine1Controller;
    periphLine2Controller periphLine2Controller;

    posLine1Controller posLine1Controller;
    posLine2Controller posLine2Controller;
    posLine3Controller posLine3Controller;
    posLine4Controller posLine4Controller;
    posQuestController posQuestController;
    posT1000Controller posT1000Controller;

    serversLine1Controller serversLine1Controller;
    serversLine2Controller serversLine2Controller;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //---------------------------------Creating the Tools for the graphs--------------------------------------------
        Tool dataBaseTool = new Tool();

        MapTool mapTool = new MapTool();
        GoalTool goalTool = new GoalTool();

        //---------------------------------List Storage Area for Running Group Values ----------------------------------
        ArrayList<String> posBar1ProdList = new ArrayList<>();
        posBar1ProdList.add("7734");
        posBar1ProdList.add("7745");
        posBar1ProdList.add("7761");

        ArrayList<String> questProdList = new ArrayList<>();
        questProdList.add("7791");
        questProdList.add("7792");

        ArrayList<String> kiwi2XsProdList = new ArrayList<>();
        kiwi2XsProdList.add("1642");
        kiwi2XsProdList.add("1924");

        ArrayList<String> pantherEPC4sProdList = new ArrayList<>();
        pantherEPC4sProdList.add("1646");
        pantherEPC4sProdList.add("1651");

        ArrayList<String> xrProdList = new ArrayList<>();
        xrProdList.add("7701");
        xrProdList.add("7702");
        xrProdList.add("7703");

        ArrayList<String> nextGenProdList = new ArrayList<>();
        nextGenProdList.add("497");
        nextGenProdList.add("5985");

        ArrayList<String> s500ProdList = new ArrayList<>();
        s500ProdList.add("1611");
        s500ProdList.add("1612");

        ArrayList<String> mediaProdList = new ArrayList<>();
        mediaProdList.add("1656");
        mediaProdList.add("1657");

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

        messenger = new Messenger(loadingController, navigationController, timeLineController, buildController, testController, stageController, posBuildController, retailBuildController, serversBuildController, periphBuildController, opticBuildController, posStageController, retailStageController, periphStageController, primaryStage);

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

        root = new FXMLLoader(getClass().getResource("FXML/timeLine.fxml"));
        root.setController(timeLineController);
        GridPane timePane = root.load();
        Scene timeScene = new Scene(timePane, 800, 600);
        messenger.setTimelineScene(timeScene);

        root = new FXMLLoader(getClass().getResource("FXML/LoadingScreen.fxml"));
        root.setController(loadingController);
        GridPane loadPane = root.load();
        Scene loadingScene = new Scene(loadPane, resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());

        root = new FXMLLoader(getClass().getResource("FXML/NavigationScreen.fxml"));
        root.setController(navigationController);
        GridPane navigationPane = root.load();
        Scene navigationScene = new Scene(navigationPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        navigationScene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/LoadCSS.css").toExternalForm());
        messenger.setNavigationScene(navigationScene);

        root = new FXMLLoader(getClass().getResource("FXML/mainTestScreen.fxml"));
        root.setController(testController);
        GridPane testPane = root.load();
        Scene testScene = new Scene(testPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainTest(testScene);

        root = new FXMLLoader(getClass().getResource("FXML/mainStageScreen.fxml"));
        root.setController(stageController);
        GridPane stagePane = root.load();
        Scene stageScene = new Scene(stagePane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainStage(stageScene);

        root = new FXMLLoader(getClass().getResource("FXML/mainBuildScreen.fxml"));
        root.setController(buildController);
        GridPane buildPane = root.load();
        Scene buildScene = new Scene(buildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setMainBuild(buildScene);


        root = new FXMLLoader(getClass().getResource("FXML/opticBuildScreen.fxml"));
        root.setController(opticBuildController);
        GridPane opticBuildPane = root.load();
        Scene opticBuildScene = new Scene(opticBuildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setOpticBuild(opticBuildScene);

        root = new FXMLLoader(getClass().getResource("FXML/periphBuildScreen.fxml"));
        root.setController(periphBuildController);
        GridPane periphBuildPane = root.load();
        Scene periphBuildScene = new Scene(periphBuildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphBuild(periphBuildScene);

        root = new FXMLLoader(getClass().getResource("FXML/posBuildScreen.fxml"));
        root.setController(posBuildController);
        GridPane posBuildPane = root.load();
        Scene posBuildScene = new Scene(posBuildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosBuild(posBuildScene);

        root = new FXMLLoader(getClass().getResource("FXML/retailBuildScreen.fxml"));
        root.setController(retailBuildController);
        GridPane retailBuildPane = root.load();
        Scene retailBuildScene = new Scene(retailBuildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailBuild(retailBuildScene);

        root = new FXMLLoader(getClass().getResource("FXML/serversBuildScreen.fxml"));
        root.setController(serversBuildController);
        GridPane serversBuildPane = root.load();
        Scene serversBuildScene = new Scene(serversBuildPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServerBuild(serversBuildScene);

        root = new FXMLLoader(getClass().getResource("FXML/periphStageScreen.fxml"));
        root.setController(periphStageController);
        GridPane periphStagePane = root.load();
        Scene periphStageScene = new Scene(periphStagePane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphStage(periphStageScene);

        root = new FXMLLoader(getClass().getResource("FXML/posStageScreen.fxml"));
        root.setController(posStageController);
        GridPane posStagePane = root.load();
        Scene posStageScene = new Scene(posStagePane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosStage(posStageScene);

        root = new FXMLLoader(getClass().getResource("FXML/retailStageScreen.fxml"));
        root.setController(retailStageController);
        GridPane retailStagePane = root.load();
        Scene retailStageScene = new Scene(retailStagePane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailStage(retailStageScene);

        root = new FXMLLoader(getClass().getResource("FXML/posBuildOverview.fxml"));
        root.setController(posBuildOverviewController);
        GridPane posBuildOverviewPane = root.load();
        Scene posBuildOverviewScene = new Scene(posBuildOverviewPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPosBuildOverview(posBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("FXML/periphBuildOverview.fxml"));
        root.setController(periphBuildOverviewController);
        GridPane periphBuildOverviewPane = root.load();
        Scene periphBuildOverviewScene = new Scene(periphBuildOverviewPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setPeriphBuildOverview(periphBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("FXML/retailBuildOverview.fxml"));
        root.setController(retailBuildOverviewController);
        GridPane retailBuildOverviewPane = root.load();
        Scene retailBuildOverviewScene = new Scene(retailBuildOverviewPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setRetailBuildOverview(retailBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("FXML/serversBuildOverview.fxml"));
        root.setController(serversBuildOverviewController);
        GridPane serversBuildOverviewPane = root.load();
        Scene serversBuildOverviewScene = new Scene(serversBuildOverviewPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setServersBuildOverview(serversBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("FXML/opticBuildOverview.fxml"));
        root.setController(opticBuildOverviewController);
        GridPane opticBuildOverviewPane = root.load();
        Scene opticBuildOverviewScene = new Scene(opticBuildOverviewPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        messenger.setOpticBuildOverview(opticBuildOverviewScene);

        root = new FXMLLoader(getClass().getResource("FXML/serversLine1.fxml"));
        root.setController(serversLine1Controller);
        GridPane serversLine1Pane = root.load();
        Scene serversLine1Scene = new Scene(serversLine1Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        serversLine1Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setServersLine1(serversLine1Scene);

        root = new FXMLLoader(getClass().getResource("FXML/serversLine2.fxml"));
        root.setController(serversLine2Controller);
        GridPane serversLine2Pane = root.load();
        Scene serversLine2Scene = new Scene(serversLine2Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        serversLine2Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setServersLine2(serversLine2Scene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenDisplay.fxml"));
        root.setController(nextGenDisplayLineController);
        GridPane nextGenDisplayPane = root.load();
        Scene nextGenDisplayScene = new Scene(nextGenDisplayPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenDisplayScene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenDisplay(nextGenDisplayScene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenLine1.fxml"));
        root.setController(nextGenLine1Controller);
        GridPane nextGenLine1Pane = root.load();
        Scene nextGenLine1Scene = new Scene(nextGenLine1Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine1Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine1(nextGenLine1Scene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenLine2.fxml"));
        root.setController(nextGenLine2Controller);
        GridPane nextGenLine2Pane = root.load();
        Scene nextGenLine2Scene = new Scene(nextGenLine2Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine2Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine2(nextGenLine2Scene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenLine3.fxml"));
        root.setController(nextGenLine3Controller);
        GridPane nextGenLine3Pane = root.load();
        Scene nextGenLine3Scene = new Scene(nextGenLine3Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine3Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine3(nextGenLine3Scene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenLine4.fxml"));
        root.setController(nextGenLine4Controller);
        GridPane nextGenLine4Pane = root.load();
        Scene nextGenLine4Scene = new Scene(nextGenLine4Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine4Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine4(nextGenLine4Scene);

        root = new FXMLLoader(getClass().getResource("FXML/nextGenLine5.fxml"));
        root.setController(nextGenLine5Controller);
        GridPane nextGenLine5Pane = root.load();
        Scene nextGenLine5Scene = new Scene(nextGenLine5Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        nextGenLine5Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setNextGenLine5(nextGenLine5Scene);

        root = new FXMLLoader(getClass().getResource("FXML/opticLine1.fxml"));
        root.setController(opticLine1Controller);
        GridPane opticLine1Pane = root.load();
        Scene opticLine1Scene = new Scene(opticLine1Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine1Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setOpticLine1(opticLine1Scene);

        root = new FXMLLoader(getClass().getResource("FXML/opticLine2.fxml"));
        root.setController(opticLine2Controller);
        GridPane opticLine2Pane = root.load();
        Scene opticLine2Scene = new Scene(opticLine2Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine2Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setOpticLine2(opticLine2Scene);

        root = new FXMLLoader(getClass().getResource("FXML/opticLine3.fxml"));
        root.setController(opticLine3Controller);
        GridPane opticLine3Pane = root.load();
        Scene opticLine3Scene = new Scene(opticLine3Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine3Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setOpticLine3(opticLine3Scene);

        root = new FXMLLoader(getClass().getResource("FXML/opticLine4.fxml"));
        root.setController(opticLine4Controller);
        GridPane opticLine4Pane = root.load();
        Scene opticLine4Scene = new Scene(opticLine4Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        opticLine4Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setOpticLine4(opticLine4Scene);

        root = new FXMLLoader(getClass().getResource("FXML/periphLine1.fxml"));
        root.setController(periphLine1Controller);
        GridPane periphLine1Pane = root.load();
        Scene periphLine1Scene = new Scene(periphLine1Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        periphLine1Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPeriphLine1(periphLine1Scene);

        root = new FXMLLoader(getClass().getResource("FXML/periphLine2.fxml"));
        root.setController(periphLine2Controller);
        GridPane periphLine2Pane = root.load();
        Scene periphLine2Scene = new Scene(periphLine2Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        periphLine2Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPeriphLine2(periphLine2Scene);

        root = new FXMLLoader(getClass().getResource("FXML/posLine1.fxml"));
        root.setController(posLine1Controller);
        GridPane posLine1Pane = root.load();
        Scene posLine1Scene = new Scene(posLine1Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine1Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosLine1(posLine1Scene);

        root = new FXMLLoader(getClass().getResource("FXML/posLine2.fxml"));
        root.setController(posLine2Controller);
        GridPane posLine2Pane = root.load();
        Scene posLine2Scene = new Scene(posLine2Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine2Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosLine2(posLine2Scene);

        root = new FXMLLoader(getClass().getResource("FXML/posLine3.fxml"));
        root.setController(posLine3Controller);
        GridPane posLine3Pane = root.load();
        Scene posLine3Scene = new Scene(posLine3Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine3Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosLine3(posLine3Scene);

        root = new FXMLLoader(getClass().getResource("FXML/posLine1.fxml"));
        root.setController(posLine4Controller);
        GridPane posLine4Pane = root.load();
        Scene posLine4Scene = new Scene(posLine4Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posLine4Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosLine4(posLine4Scene);

        root = new FXMLLoader(getClass().getResource("FXML/posQuest.fxml"));
        root.setController(posQuestController);
        GridPane posQuestPane = root.load();
        Scene posQuestScene = new Scene(posQuestPane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posQuestScene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosQuest(posQuestScene);

        root = new FXMLLoader(getClass().getResource("FXML/posLine1.fxml"));
        root.setController(posT1000Controller);
        GridPane posT1000Pane = root.load();
        Scene posT1000Scene = new Scene(posT1000Pane,  resolutionizer.setPaneWidth(), resolutionizer.setPaneHeight());
        posT1000Scene.getStylesheets().add(getClass().getResource("eu/hansolo/tilesfx/BasicStyle.css").toExternalForm());
        messenger.setPosT1000(posT1000Scene);


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


                        System.out.println("\n***********Running Test Block.***********\n");
                        //---------------------------------Hosp Test-----------------------------------------------------------------------
                        posTestMap = dataBaseTool.hospTestDataBase();
                        testController.setP1x30CurrentTest(mapTool.getCurrentSingleValue("7743", posTestMap));
                        testController.setPosBar1Total(mapTool.getCurrentGroupValue(posBar1ProdList, posTestMap));
                        testController.setT1000sCurrentTest(mapTool.getCurrentSingleValue("7744", posTestMap));
                        testController.setQuestsCurrentTest(mapTool.getCurrentGroupValue(questProdList,posTestMap));

                        //---------------------------------Retail Test-----------------------------------------------------------------------
                        retailTestMap = dataBaseTool.retailTestDataBase();

                        testController.setRetailBar1Total(mapTool.getCurrentGroupValue(xrProdList,retailTestMap));
                        testController.setNextGenDisplayCurrentTest(mapTool.getCurrentGroupValue(nextGenProdList, retailTestMap));

                        //---------------------------------Server Test-----------------------------------------------------------------------
                        serversTestMap = dataBaseTool.serversTestDataBase();

                        testController.setServerBar1Total(mapTool.getCurrentGroupValue(s500ProdList, serversTestMap));
                        testController.setServerBar2Total(mapTool.getCurrentGroupValue(mediaProdList, serversTestMap));

                        //---------------------------------Periph Test-----------------------------------------------------------------------
                        periphTestMap = dataBaseTool.periphTestDataBase();

                        testController.setPeriphBar1Total(mapTool.getCurrentSingleValue("1635", periphTestMap));
                        testController.setPeriphBar2Total(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphTestMap));
                        testController.setPeriphBar3Total(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphTestMap));

                        //---------------------------------Optic Test----------------------------------------------------------------------
                        opticTestMap = dataBaseTool.opticTestDataBase();


                        testController.setOptic12sCurrentTest(mapTool.getCurrentSingleValue("6002", opticTestMap));
                        testController.setKitsCurrentTest(mapTool.getCurrentSingleValue("6003", opticTestMap));

                        System.out.println("\n***********Running Stage Block.***********\n");

                        //---------------------------------Hosp Staging-----------------------------------------------------------------------
                        posStageMap = dataBaseTool.hospStageDataBase();

                        posUserStageMap = dataBaseTool.hospStageDataBaseUsers();

                        stageController.setP1x30CurrentStage(mapTool.getCurrentSingleValue("7743", posStageMap));
                        stageController.setPosBar1Total(mapTool.getCurrentGroupValue(posBar1ProdList, posStageMap));
                        stageController.setT1000sCurrentStage(mapTool.getCurrentSingleValue("7744", posStageMap));
                        stageController.setQuestsCurrentStage(mapTool.getCurrentGroupValue(questProdList,posStageMap));

                        //---------------------------------Retail Stage-----------------------------------------------------------------------
                        retailStageMap = dataBaseTool.retailStageDataBase();
                        retailUserStageMap = dataBaseTool.retailStageDataBaseUsers();


                        stageController.setRetailBar1Total(mapTool.getCurrentGroupValue(xrProdList,retailStageMap));
                        stageController.setNextGenDisplayCurrentStage(mapTool.getCurrentGroupValue(nextGenProdList, retailStageMap));

                        //---------------------------------Servers Stage-----------------------------------------------------------------------
                        serversStageMap = dataBaseTool.serversStageDataBase();
                        //serversUserStageMap = dataBaseTool.serverStageDataBaseUsers();
                        stageController.setServerBar1Total(mapTool.getCurrentGroupValue(s500ProdList, serversStageMap));
                        stageController.setServerBar2Total(mapTool.getCurrentGroupValue(mediaProdList, serversStageMap));

                        //---------------------------------Periph Stage-----------------------------------------------------------------------
                        periphStageMap = dataBaseTool.periphStageDataBase();

                        periphUserStageMap = dataBaseTool.periphStageDataBaseUsers();

                        stageController.setPeriphBar1Total(mapTool.getCurrentSingleValue("1635", periphStageMap));
                        stageController.setPeriphBar2Total(mapTool.getCurrentGroupValue(kiwi2XsProdList, periphStageMap));
                        stageController.setPeriphBar3Total(mapTool.getCurrentGroupValue(pantherEPC4sProdList, periphStageMap));

                        System.out.println("\n***********Running Doc Block.***********\n");
                        mapList = dataBaseTool.documentReader();
                        stageMapList = dataBaseTool.stageDocumentReader();

                        buildController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                        testController.setP1x30GoalBuild(goalTool.getGoal(mapList.get(0), "7743"));
                        buildController.setPosBar1Goal(goalTool.getListGoal(mapList.get(0), posBar1ProdList));
                        testController.setPosBar1Goal(goalTool.getListGoal(mapList.get(0), posBar1ProdList));
                        buildController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));
                        testController.setT1000sGoalBuild(goalTool.getGoal(mapList.get(0), "7744"));
                        buildController.setQuestGoalBuild(goalTool.getListGoal(mapList.get(0),questProdList));
                        testController.setQuestGoalBuild(goalTool.getListGoal(mapList.get(0),questProdList));

                        stageController.setP1x30GoalStage(goalTool.getGoal(stageMapList.get(0), "7743"));
                        stageController.setPosBar1Goal(goalTool.getListGoal(stageMapList.get(0), posBar1ProdList));
                        stageController.setT1000sGoalStage(goalTool.getGoal(stageMapList.get(0), "7744"));
                        stageController.setQuestsGoalStage(goalTool.getListGoal(stageMapList.get(0),questProdList));


                        //---------------------------------Percent Calculation and Update for POS-------------------------------
                        buildController.setPosTotalGoalBuild(buildController.getP1x30GoalBuild() + buildController.getPosBar1Goal() + buildController.getT1000sGoalBuild()+buildController.getQuestGoalBuild());
                        buildController.setPosTotalCurrentBuild(buildController.getPosBar1Total() + buildController.getP1x30CurrentBuild() + buildController.getT1000sCurrentBuild()+buildController.getQuestCurrentBuild());

                        testController.setPosTotalCurrentTest(testController.getPosBar1Total() + testController.getP1x30CurrentTest() + testController.getT1000sCurrentTest()+testController.getQuestsCurrentTest());
                        testController.setPosTotalGoalBuild(buildController.getPosTotalGoalBuild());

                        stageController.setPosTotalGoalStage(stageController.getPosBar1Goal()+ stageController.getP1x30GoalStage() + stageController.getQuestsGoalStage() + stageController.getT1000sGoalStage());
                        stageController.setPosTotalCurrentStage(stageController.getPosBar1Total() + stageController.getP1x30CurrentStage() + stageController.getT1000sCurrentStage());

                        buildController.setPosPercentTotalBuild(goalTool.getPercentTotal(buildController.getPosTotalCurrentBuild(), buildController.getPosTotalGoalBuild()));
                        testController.setPosPercentTotalTest(goalTool.getPercentTotal(testController.getPosTotalCurrentTest(), testController.getPosTotalGoalBuild()));
                        stageController.setPosPercentTotalStage(goalTool.getPercentTotal(stageController.getPosTotalCurrentStage(), stageController.getPosTotalGoalStage()));

                        //---------------------------------Retail Build---------------------------------------------------------
                        buildController.setRetailBar1Goal(goalTool.getListGoal(mapList.get(4),xrProdList));
                        testController.setRetailBar1Goal(goalTool.getListGoal(mapList.get(4),xrProdList));
                        buildController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));
                        testController.setNextGenDisplayGoalsBuild(goalTool.getListGoal(mapList.get(4), nextGenProdList));

                        stageController.setRetailBar1Goal(goalTool.getListGoal(stageMapList.get(3),xrProdList));
                        stageController.setNextGenDisplayGoalsStage(goalTool.getListGoal(stageMapList.get(3), nextGenProdList));

                        //---------------------------------Percent Calculation and Update for Retail----------------------------
                        buildController.setRetailTotalGoalBuild(buildController.getRetailBar1Goal()+ buildController.getNextGenDisplayGoalsBuild());
                        buildController.setRetailTotalCurrentBuild(buildController.getRetailBar1Total() + buildController.getNextGenDisplayCurrentBuild());

                        testController.setRetailTotalCurrentTest(testController.getRetailBar1Total() + testController.getNextGenDisplayCurrentTest());
                        testController.setRetailTotalGoalBuild(buildController.getRetailTotalGoalBuild());

                        stageController.setRetailTotalGoalStage(stageController.getRetailBar1Goal() + stageController.getNextGenDisplayGoalsStage());
                        stageController.setRetailTotalCurrentStage(stageController.getRetailBar1Total()+ stageController.getNextGenDisplayCurrentStage());

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
                        buildController.setPeriphGoalTotalBuild(buildController.getPeriphBar1Goal()+buildController.getPeriphBar2Goal()+buildController.getPeriphBar3Goal());
                        buildController.setPeriphCurrentTotalBuild(buildController.getPeriphBar1Total()+buildController.getPeriphBar2Total()+buildController.getPeriphBar3Total());

                        testController.setPeriphCurrentTotalTest(testController.getPeriphBar1Total()+testController.getPeriphBar2Total()+testController.getPeriphBar3Total());
                        testController.setPeriphGoalTotalBuild(buildController.getPeriphGoalTotalBuild());

                        stageController.setPeriphGoalTotalStage(stageController.getPeriphBar1Goal()+stageController.getPeriphBar2Goal()+stageController.getPeriphBar3Goal());
                        stageController.setPeriphCurrentTotalStage(stageController.getPeriphBar1Total()+stageController.getPeriphBar2Total()+stageController.getPeriphBar3Total());


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

                        System.out.println("\n***********Running Line Block.**************\n");
                        lineMap = dataBaseTool.buildLineQuery();

                        retailBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG1",lineMap));
                        retailBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG2",lineMap));
                        retailBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG3",lineMap));
                        retailBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG4",lineMap));
                        retailBuildOverviewController.setLine5Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_NG5",lineMap));
                        retailBuildOverviewController.setLine6Total((int) mapTool.getCurrentSingleValue("MIDLAND.DISPLAY_NG1",lineMap));

                        posBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_B",lineMap));
                        posBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_E",lineMap));
                        posBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_D",lineMap));
                        posBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.POS_A",lineMap));
                        posBuildOverviewController.setLine5Total((int) mapTool.getCurrentSingleValue("MIDLAND.T1000",lineMap));
                        posBuildOverviewController.setLine6Total((int) mapTool.getCurrentSingleValue("MIDLAND.QUEST",lineMap));

                        periphBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.ED_A",lineMap));
                        periphBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.ED_B",lineMap));

                        opticBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_1",lineMap));
                        opticBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_2",lineMap));
                        opticBuildOverviewController.setLine3Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_3",lineMap));
                        opticBuildOverviewController.setLine4Total((int) mapTool.getCurrentSingleValue("MIDLAND.OPTIC_4",lineMap));

                        serversBuildOverviewController.setLine1Total((int) mapTool.getCurrentSingleValue("MIDLAND.SERVERS",lineMap));
                        serversBuildOverviewController.setLine2Total((int) mapTool.getCurrentSingleValue("MIDLAND.SERVERS_2",lineMap));

                        orbList = dataBaseTool.buildTimeQuery();
                        System.out.println("Made it");

                        messenger.setOrbList(orbList);

                        //---------------------------------This is some hacky shit-------------------------------------------
                        System.out.println("\n***********Dynamic Creation Block***********\n");

                        buildController.setOpticThrough(dataBaseTool.opticFTTDataBase());
                        buildController.setPeriphThrough(dataBaseTool.periphFTTDataBase());
                        buildController.setServersThrough(dataBaseTool.serversFTTDataBase());
                        buildController.setRetailThrough(dataBaseTool.retailFTTDataBase());
                        buildController.setPosThrough(dataBaseTool.hospFTTDataBase());

                        posStageController.setUserMap(posUserStageMap);
                        retailStageController.setUserMap(retailUserStageMap);
                        periphStageController.setUserMap(periphUserStageMap);

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

                        posBuildOverviewController.setUseDate(useDate);
                        retailBuildOverviewController.setUseDate(useDate);
                        serversBuildOverviewController.setUseDate(useDate);
                        periphBuildOverviewController.setUseDate(useDate);
                        opticBuildOverviewController.setUseDate(useDate);

                        serversLine1Controller.setUseDate(useDate);
                        serversLine2Controller.setUseDate(useDate);

                        nextGenDisplayLineController.setUseDate(useDate);
                        nextGenLine1Controller.setUseDate(useDate);
                        nextGenLine2Controller.setUseDate(useDate);
                        nextGenLine3Controller.setUseDate(useDate);
                        nextGenLine4Controller.setUseDate(useDate);
                        nextGenLine5Controller.setUseDate(useDate);

                        opticLine1Controller.setUseDate(useDate);
                        opticLine2Controller.setUseDate(useDate);
                        opticLine3Controller.setUseDate(useDate);
                        opticLine4Controller.setUseDate(useDate);

                        periphLine1Controller.setUseDate(useDate);
                        periphLine2Controller.setUseDate(useDate);

                        posLine1Controller.setUseDate(useDate);
                        posLine2Controller.setUseDate(useDate);
                        posLine3Controller.setUseDate(useDate);
                        posLine4Controller.setUseDate(useDate);
                        posQuestController.setUseDate(useDate);
                        posT1000Controller.setUseDate(useDate);

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

                        Platform.runLater( ()->posBuildOverviewController.refresh());
                        Platform.runLater( ()->retailBuildOverviewController.refresh());
                        Platform.runLater( ()->serversBuildOverviewController.refresh());
                        Platform.runLater( ()->periphBuildOverviewController.refresh());
                        Platform.runLater( ()->opticBuildOverviewController.refresh());

                        Platform.runLater( ()->serversLine1Controller.refresh());
                        Platform.runLater( ()->serversLine2Controller.refresh());

                        Platform.runLater( ()->nextGenLine1Controller.refresh());
                        Platform.runLater( ()->nextGenLine2Controller.refresh());
                        Platform.runLater( ()->nextGenLine3Controller.refresh());
                        Platform.runLater( ()->nextGenLine4Controller.refresh());
                        Platform.runLater( ()->nextGenLine5Controller.refresh());
                        Platform.runLater( ()->nextGenDisplayLineController.refresh());

                        Platform.runLater( ()->opticLine1Controller.refresh());
                        Platform.runLater( ()->opticLine2Controller.refresh());
                        Platform.runLater( ()->opticLine3Controller.refresh());
                        Platform.runLater( ()->opticLine4Controller.refresh());

                        Platform.runLater( ()->periphLine1Controller.refresh());
                        Platform.runLater( ()->periphLine2Controller.refresh());

                        Platform.runLater( ()->posLine1Controller.refresh());
                        Platform.runLater( ()->posLine2Controller.refresh());
                        Platform.runLater( ()->posLine3Controller.refresh());
                        Platform.runLater( ()->posLine4Controller.refresh());
                        Platform.runLater( ()->posQuestController.refresh());
                        Platform.runLater( ()->posT1000Controller.refresh());


                        if (primaryStage.getScene() == loadingScene)
                        {

                            Platform.runLater( ()->primaryStage.setScene(navigationScene));

                            System.out.println("Made it");

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

//    private ArrayList<Tile> getCharTiles (HashMap<String,Integer> userMap)
//    {
//        ArrayList<Tile> tiles = new ArrayList<>();
//
//        int i = 1;
//        for(Map.Entry<String,Integer> entry : userMap.entrySet())
//        {
//            String user = entry.getKey();
//            int total = entry.getValue();
//
//            String formatted = String.format("%02d",total);
//
//            Tile characterTile = TileBuilder.create()
//                    .skinType(Tile.SkinType.CHARACTER)
//                    .prefSize(384,216)
//                    .title(user)
//                    .roundedCorners(false)
//                    .titleAlignment(TextAlignment.CENTER)
//                    .description(formatted)
//                    .build();
//
//            tiles.add(characterTile);
//
//            characterTile = null;
//        }
//        tiles.sort(Comparator.comparing(Tile::getDescription));
//        Collections.reverse(tiles);
//
//        return tiles;
//    }
}