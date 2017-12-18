package eu.hansolo.tilesfx.tools;

import eu.hansolo.tilesfx.Controllers.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Messenger {
    LoadingController loadingController;
    NavigationController navigationController;
    MainBuildController mainBuildController;
    MainTestController mainTestController;
    MainStageController mainStageController;
    POSBuildController posBuildController;
    RetailBuildController retailBuildController;
    ServersBuildController serversBuildController;
    PeriphBuildController periphBuildController;
    OpticBuildController opticBuildController;
    TimeLineController timeLineController;
    Stage primaryStage;

    public Messenger(LoadingController loadingController, NavigationController navigationController,TimeLineController timeLineController, MainBuildController mainBuildController, MainTestController mainTestController,  MainStageController mainStageController, POSBuildController posBuildController, RetailBuildController retailBuildController, ServersBuildController serversBuildController, PeriphBuildController periphBuildController, OpticBuildController opticBuildController, Stage primaryStage) {
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}



