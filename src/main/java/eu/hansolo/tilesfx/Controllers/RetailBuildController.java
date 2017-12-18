package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Messenger;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static javafx.scene.paint.Color.rgb;

public class RetailBuildController implements Initializable
{
    Tile logo;
    Tile clock;
    Tile dept;
    Tile stopLight;
    Tile daySince;

    Tile retailBuild;
    Tile retailPercent;
    Tile retailFTT;
    Tile retailQuant;

    Tile retailTest;
    Tile retailTestPercent;
    Tile retailTestFTT;
    Tile retailTestQuant;

    Tile message;

    Messenger messenger;

    ArrayList<Tile> tiles;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tiles = new ArrayList<>();

        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

        retailBuild = mainBuildController.getRetailBuild();

        retailPercent = mainBuildController.getRetailPercent();

        retailFTT = mainBuildController.getRetailFTT();

        retailTest = mainTestController.getRetailTest();


        retailTestPercent = mainTestController.getRetailTestPercent();

        retailTestFTT = mainTestController.getRetailFTT();


        final ImageView logoView = new ImageView();
        final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");
        logoView.setImage(logoImage);
        logoView.setFitHeight(270);
        logoView.setFitWidth(384);
        logoView.setPreserveRatio(true);

        HBox hbox = new HBox(logoView);
        hbox.setPrefWidth(384);
        hbox.setPrefHeight(270);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color:#54B948");

        Tool dataBaseTool = new Tool();

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

        ImageView stopView = new ImageView();
        final Image redImage = new Image("/Red Light.PNG");
        final Image yellowImage = new Image("/Yellow Light.PNG");
        final Image greenImage = new Image("/Green Light.PNG");

        if(counter <30)
        {
            stopView.setImage(redImage);
        }
        if(counter > 30 && counter <60)
        {
            stopView.setImage(yellowImage);
        }
        if(counter >= 60)
        {
            stopView.setImage(greenImage);
        }
        stopView.setFitHeight(270);
        stopView.setFitWidth(384);
        stopView.setPreserveRatio(true);

        HBox myBox = new HBox(stopView);
        myBox.setPrefWidth(384);
        myBox.setPrefHeight(270);
        myBox.setAlignment(Pos.CENTER);
        myBox.setStyle("-fx-background-color:#54B948");

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(384, 270)
                .title("Current Time")
                .titleAlignment(TextAlignment.CENTER)
                .locale(Locale.US)
                .backgroundColor(Color.valueOf("#54B948"))
                .running(true)
                .dateVisible(false)
                .roundedCorners(false)
                .textAlignment(TextAlignment.CENTER)
                .build();

        logo  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(384,270)
                .roundedCorners(false)
                .graphic(hbox)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(384,270)
                .roundedCorners(false)
                .graphic(myBox)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 270)
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        Tile filler1  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(384,270)
                .roundedCorners(false)
                .build();
        Tile filler2  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(384,270)
                .roundedCorners(false)
                .build();
        Tile filler3  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(384,270)
                .roundedCorners(false)
                .build();
        Tile filler4  = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(rgb(42, 42, 42))
                .prefSize(384,270)
                .roundedCorners(false)
                .build();

        pane.add(retailBuild,1,0,1,2);
        pane.add(retailPercent,2,0,1,1);
        pane.add(retailFTT,3,0,1,1);
        pane.add(retailTest,1,2,1,2);
        pane.add(retailTestPercent,2,2,1,1);
        pane.add(retailTestFTT,3,2,1,1);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(stopLight,0,2,1,1);
        pane.add(daySince,0,3,1,1);
        pane.add(filler1,2,1,1,1);
        pane.add(filler2,3,1,1,1);
        pane.add(filler3,2,3,1,1);
        pane.add(filler4,3,3,1,1);

        tiles.add(retailBuild);
        tiles.add(retailPercent);
        tiles.add(retailFTT);
        tiles.add(retailTest);
        tiles.add(retailTestPercent);
        tiles.add(retailTestFTT);
        tiles.add(logo);
        tiles.add(clock);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(filler1);
        tiles.add(filler2);
        tiles.add(filler3);
        tiles.add(filler4);

        createActions();

    }
    private void createActions()
    {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE)
                {
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
                if(event.getCode() == KeyCode.T && event.isControlDown())
                {
                    TimeLineController timeLineController = messenger.getTimeLineController();

                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initStyle(StageStyle.UNDECORATED);

                    dialog.initOwner(messenger.getPrimaryStage());

                    FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/timeLine.fxml"));

                    root.setController(timeLineController);
                    GridPane buildPane = null;
                    try {
                        buildPane = root.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene buildScene = new Scene(buildPane, 800, 600);

                    timeLineController.setStage(dialog);

                    dialog.setScene(buildScene);
                    dialog.show();
                }
                if(event.getCode() == KeyCode.X && event.isControlDown())
                {
                    TimeLineController timeLineController = messenger.getTimeLineController();

                    Timeline temp = timeLineController.getTimeline();

                    if(temp.getStatus() == Animation.Status.RUNNING && temp != null)
                    {
                        temp.stop();
                    }
                }
            }
        });
    }
    private Bounds computeAllScreenBounds() {
        double minX = Double.POSITIVE_INFINITY ;
        double minY = Double.POSITIVE_INFINITY ;
        double maxX = Double.NEGATIVE_INFINITY ;
        double maxY = Double.NEGATIVE_INFINITY ;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D screenBounds = screen.getBounds();
            if (screenBounds.getMinX() < minX) {
                minX = screenBounds.getMinX();
            }
            if (screenBounds.getMinY() < minY) {
                minY = screenBounds.getMinY() ;
            }
            if (screenBounds.getMaxX() > maxX) {
                maxX = screenBounds.getMaxX();
            }
            if (screenBounds.getMaxY() > maxY) {
                maxY = screenBounds.getMaxY() ;
            }
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }
    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
    public Tile getLogo() {
        return logo;
    }

    public void setLogo(Tile logo) {
        this.logo = logo;
    }

    public Tile getClock() {
        return clock;
    }

    public void setClock(Tile clock) {
        this.clock = clock;
    }

    public Tile getDept() {
        return dept;
    }

    public void setDept(Tile dept) {
        this.dept = dept;
    }

    public Tile getStopLight() {
        return stopLight;
    }

    public void setStopLight(Tile stopLight) {
        this.stopLight = stopLight;
    }

    public Tile getDaySince() {
        return daySince;
    }

    public void setDaySince(Tile daySince) {
        this.daySince = daySince;
    }

    public Tile getRetailBuild() {
        return retailBuild;
    }

    public void setRetailBuild(Tile retailBuild) {
        this.retailBuild = retailBuild;
    }

    public Tile getRetailPercent() {
        return retailPercent;
    }

    public void setRetailPercent(Tile retailPercent) {
        this.retailPercent = retailPercent;
    }

    public Tile getRetailFTT() {
        return retailFTT;
    }

    public void setRetailFTT(Tile retailFTT) {
        this.retailFTT = retailFTT;
    }

    public Tile getRetailQuant() {
        return retailQuant;
    }

    public void setRetailQuant(Tile retailQuant) {
        this.retailQuant = retailQuant;
    }

    public Tile getRetailTest() {
        return retailTest;
    }

    public void setRetailTest(Tile retailTest) {
        this.retailTest = retailTest;
    }

    public Tile getRetailTestPercent() {
        return retailTestPercent;
    }

    public void setRetailTestPercent(Tile retailTestPercent) {
        this.retailTestPercent = retailTestPercent;
    }

    public Tile getRetailTestFTT() {
        return retailTestFTT;
    }

    public void setRetailTestFTT(Tile retailTestFTT) {
        this.retailTestFTT = retailTestFTT;
    }

    public Tile getRetailTestQuant() {
        return retailTestQuant;
    }

    public void setRetailTestQuant(Tile retailTestQuant) {
        this.retailTestQuant = retailTestQuant;
    }

    public Tile getMessage() {
        return message;
    }

    public void setMessage(Tile message) {
        this.message = message;
    }
}
