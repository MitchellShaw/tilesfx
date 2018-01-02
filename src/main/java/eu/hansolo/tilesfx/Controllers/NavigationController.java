package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.tools.Messenger;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.stage.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createActions();
    }

    private void createActions()
    {
        Tooltip buildTool = new Tooltip("Build");
        Tooltip testTool = new Tooltip("Test");
        Tooltip stageTool = new Tooltip("Stage");
        Tooltip safetyTool = new Tooltip("Safety");
        Tooltip qualityTool = new Tooltip("Quality");

        Tooltip.install(buildIcon,buildTool);
        Tooltip.install(testIcon,testTool);
        Tooltip.install(stageIcon,stageTool);
        Tooltip.install(safetyIcon,safetyTool);
        Tooltip.install(qualityIcon,qualityTool);

        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F4)
                {
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setIconified(true);
                }
                if(event.getCode() == KeyCode.F5)
                {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
            }
        });
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                pane.getScene().getWindow().setX(event.getScreenX() - x);
                pane.getScene().getWindow().setY(event.getScreenY() - y);
                if(pane.getScene().getWindow().getX() < allScreenBounds.getMinX())
                {
                   pane.getScene().getWindow().setX(allScreenBounds.getMinX());

                }
                if(pane.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-1920))
                {
                    pane.getScene().getWindow().setX(allScreenBounds.getMaxX()-1920);
                }
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

    private Bounds computeAllScreenBounds() {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D screenBounds = screen.getBounds();
            if (screenBounds.getMinX() < minX) {
                minX = screenBounds.getMinX();
            }
            if (screenBounds.getMinY() < minY) {
                minY = screenBounds.getMinY();
            }
            if (screenBounds.getMaxX() > maxX) {
                maxX = screenBounds.getMaxX();
            }
            if (screenBounds.getMaxY() > maxY) {
                maxY = screenBounds.getMaxY();
            }
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }
    private void screenMove(Stage primaryStage, Bounds allScreenBounds, ArrayList<Screen> screens)
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
    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

}