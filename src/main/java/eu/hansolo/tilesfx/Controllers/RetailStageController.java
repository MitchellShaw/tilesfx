package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Messenger;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class RetailStageController implements Initializable {
    Tile logo;
    Tile clock;
    Tile dept;
    Tile stopLight;
    Tile daySince;

    ArrayList<Tile> users;

    Messenger messenger;

    ArrayList<Tile> tiles;

    String useDate;

    ImageView stopView = new ImageView();

    final Image redImage = new Image("/Red Light.PNG");
    final Image yellowImage = new Image("/Yellow Light.PNG");
    final Image greenImage = new Image("/Green Light.PNG");

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tiles = new ArrayList<>();

        final ImageView logoView = new ImageView();
        final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");
        logoView.setImage(logoImage);
        logoView.setFitHeight(216);
        logoView.setFitWidth(384);
        logoView.setPreserveRatio(true);

        HBox hbox = new HBox(logoView);
        hbox.setPrefWidth(384);
        hbox.setPrefHeight(216);
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

        useDate = Integer.toString(counter);

        if (counter < 30) {
            stopView.setImage(redImage);
        }
        if (counter > 30 && counter < 60) {
            stopView.setImage(yellowImage);
        }
        if (counter >= 60) {
            stopView.setImage(greenImage);
        }
        stopView.setFitHeight(216);
        stopView.setFitWidth(384);
        stopView.setPreserveRatio(true);

        HBox myBox = new HBox(stopView);
        myBox.setPrefWidth(384);
        myBox.setPrefHeight(216);
        myBox.setAlignment(Pos.CENTER);
        myBox.setStyle("-fx-background-color:#54B948");

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(384, 216)
                .title("Current Time")
                .titleAlignment(TextAlignment.CENTER)
                .locale(Locale.US)
                .backgroundColor(Color.valueOf("#54B948"))
                .running(true)
                .dateVisible(false)
                .roundedCorners(false)
                .textAlignment(TextAlignment.CENTER)
                .build();

        logo = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(384, 216)
                .roundedCorners(false)
                .graphic(hbox)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(384, 216)
                .roundedCorners(false)
                .graphic(myBox)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 216)
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        dept = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 216)
                .backgroundColor(Color.valueOf("#54B948"))
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description("Retail")
                .build();

        pane.add(logo, 0, 0, 1, 1);
        pane.add(clock, 0, 1, 1, 1);
        pane.add(dept, 0, 2, 1, 1);
        pane.add(stopLight, 0, 3, 1, 1);
        pane.add(daySince, 0, 4, 1, 1);


        tiles.add(logo);
        tiles.add(clock);
        tiles.add(stopLight);
        tiles.add(daySince);

        createActions();
        refresh();

    }

    private void createActions()
    {

        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    MainStageController buildController = messenger.getMainStageController();

                    FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainStageScreen.fxml"));
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
                if (event.getCode() == KeyCode.T && event.isControlDown()) {
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
                if (event.getCode() == KeyCode.X && event.isControlDown()) {
                    TimeLineController timeLineController = messenger.getTimeLineController();

                    Timeline temp = timeLineController.getTimeline();

                    if (temp.getStatus() == Animation.Status.RUNNING && temp != null) {
                        temp.stop();
                    }
                }
            }
        });
    }
    public void refresh()
    {
        Platform.runLater ( () ->
        {
            if(pane != null)
            {
                ArrayList<Tile> temp = getUsers();
                ObservableList<Node> children = pane.getChildren();
                ArrayList<Node> deletion = new ArrayList<>();

                for (int i = 0; i < temp.size(); i++) {
                    int column = 0;
                    int row = 0;

                    if (i == 0 || i == 4 || i == 8 || i == 12 || i == 16) {
                        column = 1;
                    }
                    if (i == 1 || i == 5 || i == 9 || i == 13 || i == 17) {
                        column = 2;
                    }
                    if (i == 2 || i == 6 || i == 10 || i == 14 || i == 18) {
                        column = 3;
                    }
                    if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {
                        column = 4;
                    }
                    if (i >= 0 && i < 4) {
                        row = 0;
                    }
                    if (i >= 4 && i < 8) {
                        row = 1;
                    }
                    if (i >= 8 && i < 12) {
                        row = 2;
                    }
                    if (i >= 12 && i < 16) {
                        row = 3;
                    }
                    if (i > 16) {
                        row = 4;
                    }
                    for(Node node : children)
                    {
                        if (pane.getRowIndex(node) == row && pane.getColumnIndex(node) == column)
                        {
                            System.out.println("Deleting old retail data.");
                            deletion.add(node);
                        }
                    }
                    pane.add(temp.get(i), column, row);
                }
                children.removeAll(deletion);
            }

            if(daySince != null)
            {
                daySince.setDescription(useDate);
            }

            if (Integer.parseInt(useDate) < 30) {
                stopView.setImage(redImage);
            }
            if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
                stopView.setImage(yellowImage);
            }
            if (Integer.parseInt(useDate) >= 60) {
                stopView.setImage(greenImage);
            }
            stopView.setFitHeight(216);
            stopView.setFitWidth(384);
            stopView.setPreserveRatio(true);

            HBox myBox = new HBox(stopView);
            myBox.setPrefWidth(384);
            myBox.setPrefHeight(216);
            myBox.setAlignment(Pos.CENTER);
            myBox.setStyle("-fx-background-color:#54B948");

            if(stopLight != null)
            {
                stopLight.setGraphic(myBox);
            }



        });
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

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Tile getLogo() {
        return logo;
    }

    public Tile getClock() {
        return clock;
    }

    public Tile getDept() {
        return dept;
    }

    public Tile getStopLight() {
        return stopLight;
    }

    public Tile getDaySince() {
        return daySince;
    }

    public ArrayList<Tile> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Tile> users) {
        this.users = users;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

}