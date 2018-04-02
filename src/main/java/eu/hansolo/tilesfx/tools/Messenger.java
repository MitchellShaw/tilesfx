package main.java.eu.hansolo.tilesfx.tools;

import main.java.eu.hansolo.tilesfx.Controllers.LoadingController;
import main.java.eu.hansolo.tilesfx.Controllers.MainBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.MainStageController;
import main.java.eu.hansolo.tilesfx.Controllers.MainTestController;
import main.java.eu.hansolo.tilesfx.Controllers.NavigationController;
import main.java.eu.hansolo.tilesfx.Controllers.OpticBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.POSBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.POSStageController;
import main.java.eu.hansolo.tilesfx.Controllers.PeriphBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.RetailBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.RetailStageController;
import main.java.eu.hansolo.tilesfx.Controllers.ServersBuildController;
import main.java.eu.hansolo.tilesfx.Controllers.TimeLineController;
import main.java.eu.hansolo.tilesfx.Controllers.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.eu.hansolo.tilesfx.Controllers.PeriphStageController;
import main.java.eu.hansolo.tilesfx.Controllers.timeOrb;

import java.util.ArrayList;

public class Messenger {
    LoadingController loadingController;
    NavigationController navigationController;
    TimeLineController timeLineController;
    MainBuildController mainBuildController;
    MainTestController mainTestController;
    MainStageController mainStageController;

    POSBuildController posBuildController;
    RetailBuildController retailBuildController;
    ServersBuildController serversBuildController;
    PeriphBuildController periphBuildController;
    OpticBuildController opticBuildController;

    POSStageController posStageController;
    RetailStageController retailStageController;
    PeriphStageController periphStageController;
    ServersStageController serversStageController;

    posBuildOverviewController posBuildOverviewController;
    retailBuildOverviewController retailBuildOverviewController;
    serversBuildOverviewController serversBuildOverviewController;
    periphBuildOverviewController periphBuildOverviewController;
    opticBuildOverviewController opticBuildOverviewController;

    nextGenDisplayLineController nextGenDisplayLineController;
    nextGenLine1Controller nextGenLine1Controller;
    nextGenLine2Controller nextGenLine2Controller;
    nextGenLine3Controller nextGenLine3Controller;
    main.java.eu.hansolo.tilesfx.Controllers.nextGenLine4Controller nextGenLine4Controller;
    nextGenLine5Controller nextGenLine5Controller;

    opticLine1Controller opticLine1Controller;
    opticLine2Controller opticLine2Controller;
    opticLine3Controller opticLine3Controller;
    opticLine4Controller opticLine4Controller;

    periphLine1Controller periphLine1Controller;
    periphLine2Controller periphLine2Controller;

    posLine1Controller posLine1Controller;
    posLine2Controller posLine2Controller;
    main.java.eu.hansolo.tilesfx.Controllers.posLine3Controller posLine3Controller;
    posLine4Controller posLine4Controller;
    posQuestController posQuestController;
    posT1000Controller posT1000Controller;

    serversLine1Controller serversLine1Controller;
    serversLine2Controller serversLine2Controller;

    posTestUserController posTestUserController;
    retailTestUserController retailTestUserController;
    periphTestUserController periphTestUserController;
    main.java.eu.hansolo.tilesfx.Controllers.serversTestUserController serversTestUserController;
    main.java.eu.hansolo.tilesfx.Controllers.opticTestUserController opticTestUserController;

    qualityHomeController qualityHomeController;


    Scene navigationScene;
    Scene timelineScene;
    Scene mainBuild;
    Scene mainTest;
    Scene mainStage;
    Scene opticBuild;
    Scene periphBuild;
    Scene posBuild;
    Scene retailBuild;
    Scene serverBuild;
    Scene periphStage;
    Scene posStage;
    Scene retailStage;
    Scene serverStage;

    Scene posBuildOverview;
    Scene retailBuildOverview;
    Scene serversBuildOverview;
    Scene periphBuildOverview;
    Scene opticBuildOverview;

    Scene nextGenDisplay;
    Scene nextGenLine1;
    Scene nextGenLine2;
    Scene nextGenLine3;
    Scene nextGenLine4;
    Scene nextGenLine5;

    Scene opticLine1;
    Scene opticLine2;
    Scene opticLine3;
    Scene opticLine4;

    Scene periphLine1;
    Scene periphLine2;

    Scene posLine1;
    Scene posLine2;
    Scene posLine3;

    Scene posLine4;
    Scene posQuest;
    Scene posT1000;

    Scene serversLine1;
    Scene serversLine2;

    Scene periphTestUser;
    Scene posTestUser;
    Scene retailTestUser;
    Scene serversTestUser;
    Scene opticTestUser;

    Scene qualityHome;

    Stage primaryStage;

    Resolutionizer resolutionizer;

    ArrayList<timeOrb> orbList;

    public Messenger(LoadingController loadingController, NavigationController navigationController, TimeLineController timeLineController, MainBuildController mainBuildController, MainTestController mainTestController, MainStageController mainStageController, POSBuildController posBuildController, RetailBuildController retailBuildController, ServersBuildController serversBuildController, PeriphBuildController periphBuildController, OpticBuildController opticBuildController, POSStageController posStageController, RetailStageController retailStageController, PeriphStageController periphStageController, Stage primaryStage) {
        this.loadingController = loadingController;
        this.navigationController = navigationController;
        this.timeLineController = timeLineController;
        this.mainStageController = mainStageController;
        this.mainBuildController = mainBuildController;
        this.mainTestController = mainTestController;
        this.posBuildController = posBuildController;
        this.retailBuildController = retailBuildController;
        this.serversBuildController = serversBuildController;
        this.periphBuildController = periphBuildController;
        this.opticBuildController = opticBuildController;
        this.posStageController = posStageController;
        this.retailStageController = retailStageController;
        this.periphStageController = periphStageController;
        this.primaryStage = primaryStage;
    }

    public TimeLineController getTimeLineController() {
        return timeLineController;
    }
    public void setTimeLineController(TimeLineController timeLineController)
    {
        this.timeLineController = timeLineController;
    }

    public LoadingController getLoadingController() {
        return loadingController;
    }

    public void setLoadingController(LoadingController loadingController) {
        this.loadingController = loadingController;
    }

    public NavigationController getNavigationController() {
        return navigationController;
    }

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public MainStageController getMainStageController() {
        return mainStageController;
    }

    public void setMainStageController(MainStageController mainStageController) {
        this.mainStageController = mainStageController;
    }

    public MainBuildController getMainBuildController() {
        return mainBuildController;
    }

    public void setMainBuildController(MainBuildController mainBuildController) {
        this.mainBuildController = mainBuildController;
    }

    public MainTestController getMainTestController() {
        return mainTestController;
    }

    public void setMainTestController(MainTestController mainTestController) {
        this.mainTestController = mainTestController;
    }

    public POSBuildController getPosBuildController() {
        return posBuildController;
    }

    public void setPosBuildController(POSBuildController posBuildController) {
        this.posBuildController = posBuildController;
    }

    public RetailBuildController getRetailBuildController() {
        return retailBuildController;
    }

    public void setRetailBuildController(RetailBuildController retailBuildController) {
        this.retailBuildController = retailBuildController;
    }

    public ServersBuildController getServersBuildController() {
        return serversBuildController;
    }

    public void setServersBuildController(ServersBuildController serversBuildController) {
        this.serversBuildController = serversBuildController;
    }

    public PeriphBuildController getPeriphBuildController() {
        return periphBuildController;
    }

    public void setPeriphBuildController(PeriphBuildController periphBuildController) {
        this.periphBuildController = periphBuildController;
    }

    public OpticBuildController getOpticBuildController() {
        return opticBuildController;
    }

    public void setOpticBuildController(OpticBuildController opticBuildController) {
        this.opticBuildController = opticBuildController;
    }

    public POSStageController getPosStageController() {
        return posStageController;
    }

    public void setPosStageController(POSStageController posStageController) {
        this.posStageController = posStageController;
    }
    public RetailStageController getRetailStageController() {
        return retailStageController;
    }

    public void setRetailStageController(RetailStageController retailStageController) {
        this.retailStageController = retailStageController;
    }

    public PeriphStageController getPeriphStageController() {
        return periphStageController;
    }

    public void setPeriphStageController(PeriphStageController periphStageController) {
        this.periphStageController = periphStageController;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getMainBuild() {
        return mainBuild;
    }

    public void setMainBuild(Scene mainBuild) {
        this.mainBuild = mainBuild;
    }

    public Scene getMainTest() {
        return mainTest;
    }

    public void setMainTest(Scene mainTest) {
        this.mainTest = mainTest;
    }

    public Scene getMainStage() {
        return mainStage;
    }

    public void setMainStage(Scene mainStage) {
        this.mainStage = mainStage;
    }

    public Scene getOpticBuild() {
        return opticBuild;
    }

    public void setOpticBuild(Scene opticBuild) {
        this.opticBuild = opticBuild;
    }

    public Scene getPeriphBuild() {
        return periphBuild;
    }

    public void setPeriphBuild(Scene periphBuild) {
        this.periphBuild = periphBuild;
    }

    public Scene getPosBuild() {
        return posBuild;
    }

    public void setPosBuild(Scene posBuild) {
        this.posBuild = posBuild;
    }

    public Scene getRetailBuild() {
        return retailBuild;
    }

    public void setRetailBuild(Scene retailBuild) {
        this.retailBuild = retailBuild;
    }

    public Scene getServerBuild() {
        return serverBuild;
    }

    public void setServerBuild(Scene serverBuild) {
        this.serverBuild = serverBuild;
    }

    public Scene getPeriphStage() {
        return periphStage;
    }

    public void setPeriphStage(Scene periphStage) {
        this.periphStage = periphStage;
    }

    public Scene getPosStage() {
        return posStage;
    }

    public void setPosStage(Scene posStage) {
        this.posStage = posStage;
    }

    public Scene getRetailStage() {
        return retailStage;
    }

    public void setRetailStage(Scene retailStage) {
        this.retailStage = retailStage;
    }

    public Scene getServerStage() {
        return serverStage;
    }

    public void setServerStage(Scene serverStage) {
        this.serverStage = serverStage;
    }

    public Scene getNavigationScene() {
        return navigationScene;
    }

    public void setNavigationScene(Scene navigationScene) {
        this.navigationScene = navigationScene;
    }

    public Scene getTimelineScene() {
        return timelineScene;
    }

    public void setTimelineScene(Scene timelineScene) {
        this.timelineScene = timelineScene;
    }

    public Resolutionizer getResolutionizer() {
        return resolutionizer;
    }

    public void setResolutionizer(Resolutionizer resolutionizer) {
        this.resolutionizer = resolutionizer;
    }

    public posBuildOverviewController getPosBuildOverviewController() {
        return posBuildOverviewController;
    }

    public void setPosBuildOverviewController(main.java.eu.hansolo.tilesfx.Controllers.posBuildOverviewController posBuildOverviewController) {
        this.posBuildOverviewController = posBuildOverviewController;
    }

    public Scene getPosBuildOverview() {
        return posBuildOverview;
    }

    public void setPosBuildOverview(Scene posBuildOverview) {
        this.posBuildOverview = posBuildOverview;
    }

    public retailBuildOverviewController getRetailBuildOverviewController() {
        return retailBuildOverviewController;
    }

    public void setRetailBuildOverviewController(main.java.eu.hansolo.tilesfx.Controllers.retailBuildOverviewController retailBuildOverviewController) {
        this.retailBuildOverviewController = retailBuildOverviewController;
    }

    public serversBuildOverviewController getServersBuildOverviewController() {
        return serversBuildOverviewController;
    }

    public void setServersBuildOverviewController(main.java.eu.hansolo.tilesfx.Controllers.serversBuildOverviewController serversBuildOverviewController) {
        this.serversBuildOverviewController = serversBuildOverviewController;
    }

    public periphBuildOverviewController getPeriphBuildOverviewController() {
        return periphBuildOverviewController;
    }

    public void setPeriphBuildOverviewController(main.java.eu.hansolo.tilesfx.Controllers.periphBuildOverviewController periphBuildOverviewController) {
        this.periphBuildOverviewController = periphBuildOverviewController;
    }

    public opticBuildOverviewController getOpticBuildOverviewController() {
        return opticBuildOverviewController;
    }

    public void setOpticBuildOverviewController(main.java.eu.hansolo.tilesfx.Controllers.opticBuildOverviewController opticBuildOverviewController) {
        this.opticBuildOverviewController = opticBuildOverviewController;
    }

    public Scene getRetailBuildOverview() {
        return retailBuildOverview;
    }

    public void setRetailBuildOverview(Scene retailBuildOverview) {
        this.retailBuildOverview = retailBuildOverview;
    }

    public Scene getServersBuildOverview() {
        return serversBuildOverview;
    }

    public void setServersBuildOverview(Scene serversBuildOverview) {
        this.serversBuildOverview = serversBuildOverview;
    }

    public Scene getPeriphBuildOverview() {
        return periphBuildOverview;
    }

    public void setPeriphBuildOverview(Scene periphBuildOverview) {
        this.periphBuildOverview = periphBuildOverview;
    }

    public Scene getOpticBuildOverview() {
        return opticBuildOverview;
    }

    public void setOpticBuildOverview(Scene opticBuildOverview) {
        this.opticBuildOverview = opticBuildOverview;
    }

    public serversLine1Controller getServersLine1Controller() {
        return serversLine1Controller;
    }

    public void setServersLine1Controller(serversLine1Controller serversLine1Controller) {
        this.serversLine1Controller = serversLine1Controller;
    }

    public Scene getServersLine1() {
        return serversLine1;
    }

    public void setServersLine1(Scene serversLine1) {
        this.serversLine1 = serversLine1;
    }

    public ArrayList<timeOrb> getOrbList() {
        return orbList;
    }

    public void setOrbList(ArrayList<timeOrb> orbList) {
        this.orbList = orbList;
    }

    public Scene getNextGenDisplay() {
        return nextGenDisplay;
    }

    public void setNextGenDisplay(Scene nextGenDisplay) {
        this.nextGenDisplay = nextGenDisplay;
    }

    public Scene getNextGenLine1() {
        return nextGenLine1;
    }

    public void setNextGenLine1(Scene nextGenLine1) {
        this.nextGenLine1 = nextGenLine1;
    }

    public Scene getNextGenLine2() {
        return nextGenLine2;
    }

    public void setNextGenLine2(Scene nextGenLine2) {
        this.nextGenLine2 = nextGenLine2;
    }

    public Scene getNextGenLine3() {
        return nextGenLine3;
    }

    public void setNextGenLine3(Scene nextGenLine3) {
        this.nextGenLine3 = nextGenLine3;
    }

    public Scene getNextGenLine4() {
        return nextGenLine4;
    }

    public void setNextGenLine4(Scene nextGenLine4) {
        this.nextGenLine4 = nextGenLine4;
    }

    public Scene getNextGenLine5() {
        return nextGenLine5;
    }

    public void setNextGenLine5(Scene nextGenLine5) {
        this.nextGenLine5 = nextGenLine5;
    }

    public Scene getOpticLine1() {
        return opticLine1;
    }

    public void setOpticLine1(Scene opticLine1) {
        this.opticLine1 = opticLine1;
    }

    public Scene getOpticLine2() {
        return opticLine2;
    }

    public void setOpticLine2(Scene opticLine2) {
        this.opticLine2 = opticLine2;
    }

    public Scene getOpticLine3() {
        return opticLine3;
    }

    public void setOpticLine3(Scene opticLine3) {
        this.opticLine3 = opticLine3;
    }

    public Scene getOpticLine4() {
        return opticLine4;
    }

    public void setOpticLine4(Scene opticLine4) {
        this.opticLine4 = opticLine4;
    }

    public Scene getPeriphLine1() {
        return periphLine1;
    }

    public void setPeriphLine1(Scene periphLine1) {
        this.periphLine1 = periphLine1;
    }

    public Scene getPeriphLine2() {
        return periphLine2;
    }

    public void setPeriphLine2(Scene periphLine2) {
        this.periphLine2 = periphLine2;
    }

    public Scene getPosLine1() {
        return posLine1;
    }

    public void setPosLine1(Scene posLine1) {
        this.posLine1 = posLine1;
    }

    public Scene getPosLine2() {
        return posLine2;
    }

    public void setPosLine2(Scene posLine2) {
        this.posLine2 = posLine2;
    }

    public Scene getPosLine3() {
        return posLine3;
    }

    public void setPosLine3(Scene posLine3) {
        this.posLine3 = posLine3;
    }

    public Scene getPosLine4() {
        return posLine4;
    }

    public void setPosLine4(Scene posLine4) {
        this.posLine4 = posLine4;
    }

    public Scene getPosQuest() {
        return posQuest;
    }

    public void setPosQuest(Scene posQuest) {
        this.posQuest = posQuest;
    }

    public Scene getPosT1000() {
        return posT1000;
    }

    public void setPosT1000(Scene posT1000) {
        this.posT1000 = posT1000;
    }

    public Scene getServersLine2() {
        return serversLine2;
    }

    public void setServersLine2(Scene serversLine2) {
        this.serversLine2 = serversLine2;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenDisplayLineController getNextGenDisplayLineController() {
        return nextGenDisplayLineController;
    }

    public void setNextGenDisplayLineController(main.java.eu.hansolo.tilesfx.Controllers.nextGenDisplayLineController nextGenDisplayLineController) {
        this.nextGenDisplayLineController = nextGenDisplayLineController;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenLine1Controller getNextGenLine1Controller() {
        return nextGenLine1Controller;
    }

    public void setNextGenLine1Controller(main.java.eu.hansolo.tilesfx.Controllers.nextGenLine1Controller nextGenLine1Controller) {
        this.nextGenLine1Controller = nextGenLine1Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenLine2Controller getNextGenLine2Controller() {
        return nextGenLine2Controller;
    }

    public void setNextGenLine2Controller(main.java.eu.hansolo.tilesfx.Controllers.nextGenLine2Controller nextGenLine2Controller) {
        this.nextGenLine2Controller = nextGenLine2Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenLine3Controller getNextGenLine3Controller() {
        return nextGenLine3Controller;
    }

    public void setNextGenLine3Controller(main.java.eu.hansolo.tilesfx.Controllers.nextGenLine3Controller nextGenLine3Controller) {
        this.nextGenLine3Controller = nextGenLine3Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenLine4Controller getNextGenLine4Controller() {
        return nextGenLine4Controller;
    }

    public void setNextGenLine4Controller(main.java.eu.hansolo.tilesfx.Controllers.nextGenLine4Controller nextGenLine4Controller) {
        this.nextGenLine4Controller = nextGenLine4Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.nextGenLine5Controller getNextGenLine5Controller() {
        return nextGenLine5Controller;
    }

    public void setNextGenLine5Controller(main.java.eu.hansolo.tilesfx.Controllers.nextGenLine5Controller nextGenLine5Controller) {
        this.nextGenLine5Controller = nextGenLine5Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.opticLine1Controller getOpticLine1Controller() {
        return opticLine1Controller;
    }

    public void setOpticLine1Controller(main.java.eu.hansolo.tilesfx.Controllers.opticLine1Controller opticLine1Controller) {
        this.opticLine1Controller = opticLine1Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.opticLine2Controller getOpticLine2Controller() {
        return opticLine2Controller;
    }

    public void setOpticLine2Controller(main.java.eu.hansolo.tilesfx.Controllers.opticLine2Controller opticLine2Controller) {
        this.opticLine2Controller = opticLine2Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.opticLine3Controller getOpticLine3Controller() {
        return opticLine3Controller;
    }

    public void setOpticLine3Controller(main.java.eu.hansolo.tilesfx.Controllers.opticLine3Controller opticLine3Controller) {
        this.opticLine3Controller = opticLine3Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.opticLine4Controller getOpticLine4Controller() {
        return opticLine4Controller;
    }

    public void setOpticLine4Controller(main.java.eu.hansolo.tilesfx.Controllers.opticLine4Controller opticLine4Controller) {
        this.opticLine4Controller = opticLine4Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.periphLine1Controller getPeriphLine1Controller() {
        return periphLine1Controller;
    }

    public void setPeriphLine1Controller(main.java.eu.hansolo.tilesfx.Controllers.periphLine1Controller periphLine1Controller) {
        this.periphLine1Controller = periphLine1Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.periphLine2Controller getPeriphLine2Controller() {
        return periphLine2Controller;
    }

    public void setPeriphLine2Controller(main.java.eu.hansolo.tilesfx.Controllers.periphLine2Controller periphLine2Controller) {
        this.periphLine2Controller = periphLine2Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posLine1Controller getPosLine1Controller() {
        return posLine1Controller;
    }

    public void setPosLine1Controller(main.java.eu.hansolo.tilesfx.Controllers.posLine1Controller posLine1Controller) {
        this.posLine1Controller = posLine1Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posLine2Controller getPosLine2Controller() {
        return posLine2Controller;
    }

    public void setPosLine2Controller(main.java.eu.hansolo.tilesfx.Controllers.posLine2Controller posLine2Controller) {
        this.posLine2Controller = posLine2Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posLine3Controller getPosLine3Controller() {
        return posLine3Controller;
    }

    public void setPosLine3Controller(main.java.eu.hansolo.tilesfx.Controllers.posLine3Controller posLine3Controller) {
        this.posLine3Controller = posLine3Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posLine4Controller getPosLine4Controller() {
        return posLine4Controller;
    }

    public void setPosLine4Controller(main.java.eu.hansolo.tilesfx.Controllers.posLine4Controller posLine4Controller) {
        this.posLine4Controller = posLine4Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posQuestController getPosQuestController() {
        return posQuestController;
    }

    public void setPosQuestController(main.java.eu.hansolo.tilesfx.Controllers.posQuestController posQuestController) {
        this.posQuestController = posQuestController;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.posT1000Controller getPosT1000Controller() {
        return posT1000Controller;
    }

    public void setPosT1000Controller(main.java.eu.hansolo.tilesfx.Controllers.posT1000Controller posT1000Controller) {
        this.posT1000Controller = posT1000Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.serversLine2Controller getServersLine2Controller() {
        return serversLine2Controller;
    }

    public void setServersLine2Controller(main.java.eu.hansolo.tilesfx.Controllers.serversLine2Controller serversLine2Controller) {
        this.serversLine2Controller = serversLine2Controller;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.periphTestUserController getPeriphTestUserController() {
        return periphTestUserController;
    }

    public void setPeriphTestUserController(main.java.eu.hansolo.tilesfx.Controllers.periphTestUserController periphTestUserController) {
        this.periphTestUserController = periphTestUserController;
    }

    public Scene getPeriphTestUser() {
        return periphTestUser;
    }

    public void setPeriphTestUser(Scene periphTestUser) {
        this.periphTestUser = periphTestUser;
    }
    public main.java.eu.hansolo.tilesfx.Controllers.posTestUserController getPosTestUserController() {
        return posTestUserController;
    }

    public void setPosTestUserController(main.java.eu.hansolo.tilesfx.Controllers.posTestUserController posTestUserController) {
        this.posTestUserController = posTestUserController;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.retailTestUserController getRetailTestUserController() {
        return retailTestUserController;
    }

    public void setRetailTestUserController(main.java.eu.hansolo.tilesfx.Controllers.retailTestUserController retailTestUserController) {
        this.retailTestUserController = retailTestUserController;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.serversTestUserController getServersTestUserController() {
        return serversTestUserController;
    }

    public void setServersTestUserController(main.java.eu.hansolo.tilesfx.Controllers.serversTestUserController serversTestUserController) {
        this.serversTestUserController = serversTestUserController;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.opticTestUserController getOpticTestUserController() {
        return opticTestUserController;
    }

    public void setOpticTestUserController(main.java.eu.hansolo.tilesfx.Controllers.opticTestUserController opticTestUserController) {
        this.opticTestUserController = opticTestUserController;
    }

    public Scene getPosTestUser() {
        return posTestUser;
    }

    public void setPosTestUser(Scene posTestUser) {
        this.posTestUser = posTestUser;
    }

    public Scene getRetailTestUser() {
        return retailTestUser;
    }

    public void setRetailTestUser(Scene retailTestUser) {
        this.retailTestUser = retailTestUser;
    }

    public Scene getServersTestUser() {
        return serversTestUser;
    }

    public void setServersTestUser(Scene serversTestUser) {
        this.serversTestUser = serversTestUser;
    }

    public Scene getOpticTestUser() {
        return opticTestUser;
    }

    public void setOpticTestUser(Scene opticTestUser) {
        this.opticTestUser = opticTestUser;
    }

    public main.java.eu.hansolo.tilesfx.Controllers.qualityHomeController getQualityHomeController() {
        return qualityHomeController;
    }

    public void setQualityHomeController(main.java.eu.hansolo.tilesfx.Controllers.qualityHomeController qualityHomeController) {
        this.qualityHomeController = qualityHomeController;
    }

    public Scene getQualityHome() {
        return qualityHome;
    }

    public void setQualityHome(Scene qualityHome) {
        this.qualityHome = qualityHome;
    }

    public ServersStageController getServersStageController() {
        return serversStageController;
    }

    public void setServersStageController(ServersStageController serversStageController) {
        this.serversStageController = serversStageController;
    }

}



