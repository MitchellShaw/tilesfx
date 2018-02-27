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

public class qualityHomeController extends Controller implements Initializable,Methods
{
    private Messenger messenger;

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

    private double opticThrough;
    private double periphThrough;
    private double serversThrough;
    private double retailThrough;
    private double posThrough;

    private ImageView stopView = new ImageView();
    private final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    private final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    private final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");

    private final ImageView logoView = new ImageView();
    private final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

    private String useDate = "0";

    private double x = 0;
    private double y = 0;

    private ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    private Bounds allScreenBounds = computeAllScreenBounds();
    private ArrayList<Tile> tiles;

    private DecimalFormat df = new DecimalFormat("#.0");
    private DecimalFormat hundred = new DecimalFormat("#");

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

        super.tilesListeners(tiles,messenger);
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






    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String getUseDate() {
        return useDate;
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

    @Override
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

}
