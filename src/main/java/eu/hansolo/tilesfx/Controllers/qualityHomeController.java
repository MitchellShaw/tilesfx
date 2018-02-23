package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.eu.hansolo.tilesfx.Tile;
import main.java.eu.hansolo.tilesfx.TileBuilder;
import main.java.eu.hansolo.tilesfx.tools.Messenger;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class qualityHomeController implements Initializable
{
    Messenger messenger;

    @FXML
    private Tile posFTT;
    @FXML
    private Tile serversFTT;
    @FXML
    private Tile periphFTT;
    @FXML
    private Tile opticFTT;
    @FXML
    private Tile retailFTT;

    double opticThrough;
    double periphThrough;
    double serversThrough;
    double retailThrough;
    double posThrough;

    ImageView stopView = new ImageView();
    final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    final ImageView logoView = new ImageView();
    final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

    String useDate = "0";

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();
    ArrayList<Tile> tiles;

    DecimalFormat df = new DecimalFormat("#.0");
    DecimalFormat hundred = new DecimalFormat("#");

    @FXML
    private GridPane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        posFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.20), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("POS FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPosThrough()) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        retailFTT = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.20), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("Retail FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getRetailThrough()) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        serversFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.20), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("Servers FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getServersThrough()) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        periphFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.20), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .title("Periph FTT")
                .titleAlignment(TextAlignment.CENTER)
                .description(df.format(messenger.getMainBuildController().getPeriphThrough()) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        opticFTT = TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.20), messenger.getResolutionizer().setTileHeight(.25))
                .subText("FTT Rating")
                .titleAlignment(TextAlignment.CENTER)
                .title("Optic FTT")
                .description(df.format(messenger.getMainBuildController().getOpticThrough()) + "%")
                .animated(true)
                .animationDuration(3000)
                .roundedCorners(false)
                .build();

        if (posFTT.getDescription().equals("100.0")) {
            posFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough()) + "%");
        }
        if (retailFTT.getDescription().equals("100.0")) {
            retailFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough()) + "%");
        }

        if (serversFTT.getDescription().equals("100.0")) {
            serversFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough()) + "%");
        }

        if (periphFTT.getDescription().equals("100.0")) {
            periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough()) + "%");
        }

        if (opticFTT.getDescription().equals("100.0")) {
            opticFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough()) + "%");
        }

        pane.add(posFTT,0,0,1,1);
        pane.add(retailFTT,1,0,1,1);
        pane.add(serversFTT,2,0,1,1);
        pane.add(periphFTT,3,0,1,1);
        pane.add(opticFTT,4,0,1,1);

        tiles.add(posFTT);
        tiles.add(retailFTT);
        tiles.add(serversFTT);
        tiles.add(periphFTT);
        tiles.add(opticFTT);

        tilesListeners(tiles);
        createActions();
    }
    private void createActions()
    {

        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getNavigationScene());
                }
                if (event.getCode() == KeyCode.T && event.isControlDown()) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initStyle(StageStyle.UNDECORATED);

                    dialog.initOwner(messenger.getPrimaryStage());

                    dialog.setScene(messenger.getTimelineScene());
                    dialog.show();
                }
                if (event.getCode() == KeyCode.X && event.isControlDown()) {
                    Timeline temp = messenger.getTimeLineController().getTimeline();

                    if (temp.getStatus() == Animation.Status.RUNNING && temp != null) {
                        temp.stop();
                    }
                }
                if (event.getCode() == KeyCode.F4) {
                    messenger.getPrimaryStage().setIconified(true);
                }
                if (event.getCode() == KeyCode.F5) {
                    screenMove(messenger.getPrimaryStage(),allScreenBounds,screens);
                }
            }
        });
    }
    public void refresh()
    {
        Platform.runLater( () ->
        {
            if (posFTT.getDescription().equals("100.0")) {
                posFTT.setDescription(hundred.format(messenger.getMainBuildController().getPosThrough()) + "%");
            }
            else{
                posFTT.setDescription(df.format(messenger.getMainBuildController().getPosThrough())+ "%");
            }
            if (retailFTT.getDescription().equals("100.0")) {
                retailFTT.setDescription(hundred.format(messenger.getMainBuildController().getRetailThrough()) + "%");
            }
            else{
                retailFTT.setDescription(df.format(messenger.getMainBuildController().getRetailThrough())+ "%");
            }

            if (serversFTT.getDescription().equals("100.0")) {
                serversFTT.setDescription(hundred.format(messenger.getMainBuildController().getServersThrough()) + "%");
            }
            else{
                serversFTT.setDescription(df.format(messenger.getMainBuildController().getServersThrough())+ "%");
            }

            if (periphFTT.getDescription().equals("100.0")) {
                periphFTT.setDescription(hundred.format(messenger.getMainBuildController().getPeriphThrough()) + "%");
            }
            else{
                periphFTT.setDescription(df.format(messenger.getMainBuildController().getPeriphThrough())+ "%");
            }

            if (opticFTT.getDescription().equals("100.0")) {
                opticFTT.setDescription(hundred.format(messenger.getMainBuildController().getOpticThrough()) + "%");
            }
            else{
                opticFTT.setDescription(df.format(messenger.getMainBuildController().getOpticThrough())+ "%");
            }

        });
    }

    private void tilesListeners(ArrayList<Tile> tileList)
    {

        for(int i =0;i<tileList.size();i++)
        {
            tileList.get(i).setAnimated(true);
            tileList.get(i).setAnimationDuration(3000);

            tileList.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();

                }
            });
            int finalI = i;
            tileList.get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    tileList.get(finalI).getScene().getWindow().setX(event.getScreenX() - x);
                    tileList.get(finalI).getScene().getWindow().setY(event.getScreenY() - y);
                    if(tileList.get(finalI).getScene().getWindow().getX() < allScreenBounds.getMinX())
                    {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMinX());

                    }
                    if(tileList.get(finalI).getScene().getWindow().getX() > (allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth))
                    {
                        tileList.get(finalI).getScene().getWindow().setX(allScreenBounds.getMaxX()-messenger.getResolutionizer().screenWidth);
                    }
                }
            });
        }
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

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }
    public double getOpticThrough() {
        return opticThrough;
    }

    public void setOpticThrough(double opticThrough) {
        this.opticThrough = opticThrough;
    }

    public double getPeriphThrough() {
        return periphThrough;
    }

    public void setPeriphThrough(double periphThrough) {
        this.periphThrough = periphThrough;
    }

    public double getServersThrough() {
        return serversThrough;
    }

    public void setServersThrough(double serversThrough) {
        this.serversThrough = serversThrough;
    }

    public double getRetailThrough() {
        return retailThrough;
    }

    public void setRetailThrough(double retailThrough) {
        this.retailThrough = retailThrough;
    }

    public double getPosThrough() {
        return posThrough;
    }

    public void setPosThrough(double posThrough) {
        this.posThrough = posThrough;
    }

}
