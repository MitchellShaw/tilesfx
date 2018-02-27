package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
import java.util.*;

import static javafx.scene.paint.Color.rgb;

public class periphTestUserController extends Controller implements Initializable,Methods {
    Tile logo;
    Tile clock;
    Tile dept;
    Tile stopLight;
    Tile daySince;

    Tile user1;
    Tile user2;
    Tile user3;
    Tile user4;
    Tile user5;
    Tile user6;
    Tile user7;
    Tile user8;
    Tile user9;
    Tile user10;
    Tile user11;
    Tile user12;
    Tile user13;
    Tile user14;
    Tile user15;
    Tile user16;
    Tile user17;
    Tile user18;
    Tile user19;
    Tile user20;

    HashMap<String,Integer> userMap;
    LinkedHashMap<String,Integer> sortedByValue;

    Comparator<Map.Entry<String,Integer>> valueComparator;

    HBox hbox;
    HBox myBox;

    ArrayList<Tile> tileSort;

    double x = 0;
    double y = 0;

    ArrayList<Screen> screens = new ArrayList<>(Screen.getScreens());
    Bounds allScreenBounds = computeAllScreenBounds();

    ArrayList<Tile> users;

    Messenger messenger;

    ArrayList<Tile> tiles;

    String useDate = "0";

    ImageView stopView = new ImageView();
    ImageView logoView = new ImageView();

    final Image redImage = new Image("/eu/hansolo/tilesfx/Red Light.PNG");
    final Image yellowImage = new Image("/eu/hansolo/tilesfx/Yellow Light.PNG");
    final Image greenImage = new Image("/eu/hansolo/tilesfx/Green Light.PNG");
    final Image logoImage = new Image("/eu/hansolo/tilesfx/NCR Brand Block Logo JPG.jpg");

    MainStageController stageController;
    MainTestController testController;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tiles = new ArrayList<>();
        tileSort = new ArrayList<>();

        stageController = messenger.getMainStageController();
        testController = messenger.getMainTestController();

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
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
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .roundedCorners(false)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .roundedCorners(false)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        dept = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(Color.valueOf("#54B948"))
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .title("Periph Total Tested")
                .description(Double.toString(testController.getPeriphCurrentTotalTest()))
                .build();

        pane.add(logo, 0, 0, 1, 1);
        pane.add(clock, 0, 1, 1, 1);
        pane.add(dept, 0, 2, 1, 1);
        pane.add(stopLight, 0, 3, 1, 1);
        pane.add(daySince, 0, 4, 1, 1);

        user1 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user2 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user3 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user4 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user5 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user6 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user7 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user8 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user9 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user10 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user11 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user12 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user13 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user14 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user15 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user16 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user17 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user18 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user19 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        user20 = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .title("")
                .prefSize(messenger.getResolutionizer().setTileWidth(.2), messenger.getResolutionizer().setTileHeight(.2))
                .backgroundColor(rgb(42, 42, 42))
                .roundedCorners(false)
                .titleAlignment(TextAlignment.CENTER)
                .description("")
                .build();

        logoView.setImage(logoImage);
        logoView.setPreserveRatio(true);

        logoView.setFitHeight(logo.getPrefHeight());
        logoView.setFitWidth(logo.getPrefWidth());

        logo.setGraphic(logoView);

        if (Integer.parseInt(useDate) < 30) {
            stopView.setImage(redImage);
        }
        if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
            stopView.setImage(yellowImage);
        }
        if (Integer.parseInt(useDate) >= 60) {
            stopView.setImage(greenImage);
        }

        stopView.setFitWidth(stopLight.getPrefWidth());
        stopView.setFitHeight(stopLight.getPrefHeight());

        stopView.setPreserveRatio(true);

        if (Integer.parseInt(useDate) < 30) {
            stopView.setImage(redImage);
        }
        if (Integer.parseInt(useDate) > 30 && Integer.parseInt(useDate) < 60) {
            stopView.setImage(yellowImage);
        }
        if (Integer.parseInt(useDate) >= 60) {
            stopView.setImage(greenImage);
        }

        stopView.setFitWidth(stopLight.getPrefWidth());
        stopView.setFitHeight(stopLight.getPrefHeight());

        stopView.setPreserveRatio(true);

        stopLight.setGraphic(stopView);

        pane.add(user1, 1, 0, 1, 1);
        pane.add(user2, 2, 0, 1, 1);
        pane.add(user3, 3, 0, 1, 1);
        pane.add(user4, 4, 0, 1, 1);

        pane.add(user5, 1, 1, 1, 1);
        pane.add(user6, 2, 1, 1, 1);
        pane.add(user7, 3, 1, 1, 1);
        pane.add(user8, 4, 1, 1, 1);

        pane.add(user9, 1, 2, 1, 1);
        pane.add(user10, 2, 2, 1, 1);
        pane.add(user11, 3, 2, 1, 1);
        pane.add(user12, 4, 2, 1, 1);

        pane.add(user13, 1, 3, 1, 1);
        pane.add(user14, 2, 3, 1, 1);
        pane.add(user15, 3, 3, 1, 1);
        pane.add(user16, 4, 3, 1, 1);

        pane.add(user17, 1, 4, 1, 1);
        pane.add(user18, 2, 4, 1, 1);
        pane.add(user19, 3, 4, 1, 1);
        pane.add(user20, 4, 4, 1, 1);

        tileSort.add(user1);
        tileSort.add(user2);
        tileSort.add(user3);
        tileSort.add(user4);
        tileSort.add(user5);
        tileSort.add(user6);
        tileSort.add(user7);
        tileSort.add(user8);
        tileSort.add(user9);
        tileSort.add(user10);
        tileSort.add(user11);
        tileSort.add(user12);
        tileSort.add(user13);
        tileSort.add(user14);
        tileSort.add(user15);
        tileSort.add(user16);
        tileSort.add(user17);
        tileSort.add(user18);
        tileSort.add(user19);
        tileSort.add(user20);


        tiles.add(logo);
        tiles.add(stopLight);
        tiles.add(daySince);
        tiles.add(dept);

        tiles.add(user1);
        tiles.add(user2);
        tiles.add(user3);
        tiles.add(user4);
        tiles.add(user5);
        tiles.add(user6);
        tiles.add(user7);
        tiles.add(user8);
        tiles.add(user9);
        tiles.add(user10);
        tiles.add(user11);
        tiles.add(user12);
        tiles.add(user13);
        tiles.add(user14);
        tiles.add(user15);
        tiles.add(user16);
        tiles.add(user17);
        tiles.add(user18);
        tiles.add(user19);
        tiles.add(user20);


        createActions();
        refresh();
        if(pane !=null)
        {
            tilesListeners(tiles);
        }

    }

    private void createActions()
    {

        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE)
                {
                    messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());
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
    public void userTiles()
    {
        valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int value1 = o1.getValue();
                int value2 = o2.getValue();
                if(value1 > value2)
                {
                    return 1;
                }
                if(value1 < value2)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        };

        Set<Map.Entry<String,Integer>> entries = userMap.entrySet();

        List<Map.Entry<String,Integer>> listOfEntries = new ArrayList<>(entries);

        Collections.sort(listOfEntries,valueComparator);
        Collections.reverse(listOfEntries);

        sortedByValue = new LinkedHashMap<>(listOfEntries.size());

        for(Map.Entry<String, Integer> entry : listOfEntries)
        {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        Set<Map.Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
    }
    public void refresh()
    {
        Platform.runLater ( () ->
        {
            int total = 0;
            if(userMap != null)
            {
                userTiles();
                Set<String> keySet = sortedByValue.keySet();
                ArrayList<String> keyList = new ArrayList<>(keySet);

                Collection<Integer> values = sortedByValue.values();
                ArrayList<Integer> valueList = new ArrayList<>(values);
                int temp = 0;

                for(int i = 0; i < valueList.size();i++)
                {
                    temp = temp + valueList.get(i);
                }
                total = temp;

                for(int i = 0; i <userMap.size();i++)
                {
                    tileSort.get(i).setTitle(keyList.get(i));
                    tileSort.get(i).setDescription(Integer.toString(valueList.get(i)));
                }
                for(int i = userMap.size();i<tileSort.size();i++)
                {
                    tileSort.get(i).setTitle("");
                    tileSort.get(i).setDescription("");
                }
            }
            if(dept !=null)
            {
                dept.setDescription(String.valueOf(total));
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

            myBox = new HBox(stopView);
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

    public HashMap<String, Integer> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, Integer> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

}