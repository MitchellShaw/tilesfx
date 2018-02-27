package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadingController extends Controller implements Initializable,Methods
{
    @FXML
    private ImageView image;
    @FXML
    private ProgressIndicator loadingBar;
    @FXML
    private GridPane pane;
    @FXML
    private ImageView logo;

    @FXML
    private Label label;

    @FXML
    private HBox progressBox;

    @FXML
    private HBox imageHBox;

    @FXML
    private VBox imageVBox;

    double x = 0;
    double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();

    private Messenger messenger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        messenger.getResolutionizer().setImageHeight(image);
        messenger.getResolutionizer().setImageWidth(image);

        messenger.getResolutionizer().setLabelSize(label);

        messenger.getResolutionizer().setLoader(loadingBar);
        createActions();
    }

    private void createActions() {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F4) {
                    Stage primaryStage = messenger.getPrimaryStage();
                    primaryStage.setIconified(true);
                }
                if (event.getCode() == KeyCode.F5) {
                    screenMove(messenger.getPrimaryStage(), allScreenBounds, screens);
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
            public void handle(MouseEvent event) {
                pane.getScene().getWindow().setX(event.getScreenX() - x);
                pane.getScene().getWindow().setY(event.getScreenY() - y);
                if (pane.getScene().getWindow().getX() < allScreenBounds.getMinX()) {
                    pane.getScene().getWindow().setX(allScreenBounds.getMinX());

                }
                if (pane.getScene().getWindow().getX() > (allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth))
                {
                    pane.getScene().getWindow().setX(allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth);
                }
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
