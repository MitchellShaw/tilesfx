package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NavigationController extends Controller implements Initializable,Methods
{
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
    private ImageView logo;
    @FXML
    private GridPane pane;
    @FXML
    private Label label;
    @FXML
    private HBox textBox;

    private Messenger messenger;

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        messenger.getResolutionizer().setImageHeight(logo);
        messenger.getResolutionizer().setImageWidth(logo);

        messenger.getResolutionizer().setImageHeight(buildIcon);
        messenger.getResolutionizer().setImageWidth(buildIcon);

        messenger.getResolutionizer().setImageHeight(testIcon);
        messenger.getResolutionizer().setImageWidth(testIcon);

        messenger.getResolutionizer().setImageHeight(stageIcon);
        messenger.getResolutionizer().setImageWidth(stageIcon);

        messenger.getResolutionizer().setImageHeight(safetyIcon);
        messenger.getResolutionizer().setImageWidth(safetyIcon);

        messenger.getResolutionizer().setImageHeight(qualityIcon);
        messenger.getResolutionizer().setImageWidth(qualityIcon);

        messenger.getResolutionizer().setImageHeight(exitIcon);
        messenger.getResolutionizer().setImageWidth(exitIcon);

        messenger.getResolutionizer().setLabelSize(label);

//        logo.fitWidthProperty().bind(pane.widthProperty());
//        pane.setCenterShape(true);
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
                if(pane.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth))
                {
                    pane.getScene().getWindow().setX(allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth);
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
        exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               System.exit(0);

            }
        });
        buildIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                messenger.getPrimaryStage().setScene(messenger.getMainBuild());
            }
        });
        testIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getMainTest());
            }
        });
        stageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getMainStage());
            }
        });
        qualityIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messenger.getPrimaryStage().setScene(messenger.getQualityHome());
            }
        });
    }



    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

}