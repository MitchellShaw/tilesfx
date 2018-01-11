package eu.hansolo.tilesfx.tools;

import eu.hansolo.tilesfx.Controllers.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


    Stage primaryStage;

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

}



