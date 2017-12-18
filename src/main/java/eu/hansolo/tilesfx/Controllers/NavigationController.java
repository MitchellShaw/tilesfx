package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.tools.Messenger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {
    @FXML
    private ImageView buildIcon;
    @FXML
    private ImageView testIcon;
    @FXML
    private ImageView stageIcon;
    @FXML
    private ImageView safetyIcon;
    @FXML
    private ImageView qualityIcon;
    @FXML
    private ImageView exitIcon;
    @FXML
    private GridPane pane;

    Messenger messenger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createActions();
//        Tooltip buildToolTip = new Tooltip("Build");
//        buildToolTip.setFont(Font.font("OpenSans"));
//        buildToolTip.setGraphic(null);
//        Tooltip testToolTip = new Tooltip("Testing");
//        testToolTip.setFont(Font.font("OpenSans"));
//        Tooltip stageToolTip = new Tooltip("Staging");
//        stageToolTip.setFont(Font.font("OpenSans"));
//        Tooltip safetyToolTip= new Tooltip("Safety");
//        safetyToolTip.setFont(Font.font("OpenSans"));
//        Tooltip qualityToolTip = new Tooltip("Quality");
//        qualityToolTip.setFont(Font.font("OpenSans"));
//
//        Tooltip.install(buildIcon,buildToolTip);
//        Tooltip.install(testIcon,testToolTip);
//        Tooltip.install(stageIcon,stageToolTip);
//        Tooltip.install(safetyIcon,safetyToolTip);
//        Tooltip.install(qualityIcon,qualityToolTip);
    }

    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            }
        });
        buildIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        buildIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        testIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        testIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        stageIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        stageIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        safetyIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        safetyIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        qualityIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        qualityIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        exitIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.HAND);
            }
        });
        exitIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setCursor(Cursor.DEFAULT);
            }
        });
        exitIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               System.exit(0);

            }
        });
        buildIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainBuildController buildController = messenger.getMainBuildController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainBuildScreen.fxml"));
                root.setController(buildController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);

            }
        });
        testIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainTestController testController = messenger.getMainTestController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
                root.setController(testController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);
            }
        });
        stageIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainStageController stageController = messenger.getMainStageController();

                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainStageScreen.fxml"));
                root.setController(stageController);
                GridPane buildPane = null;
                try {
                    buildPane = root.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene buildScene = new Scene(buildPane, 1920, 1080);
                Stage primaryStage = messenger.getPrimaryStage();
                primaryStage.setScene(buildScene);
            }
        });
//        safetyIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                MainTestController testController = messenger.getMainTestController();
//
//                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
//                root.setController(testController);
//                GridPane buildPane = null;
//                try {
//                    buildPane = root.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene buildScene = new Scene(buildPane, 1920, 1080);
//                Stage primaryStage = messenger.getPrimaryStage();
//                primaryStage.setScene(buildScene);
//            }
//        });
//        qualityIcon.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                MainTestController testController = messenger.getMainTestController();
//
//                FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
//                root.setController(testController);
//                GridPane buildPane = null;
//                try {
//                    buildPane = root.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene buildScene = new Scene(buildPane, 1920, 1080);
//                Stage primaryStage = messenger.getPrimaryStage();
//                primaryStage.setScene(buildScene);
//            }
//        });
    }
    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

}