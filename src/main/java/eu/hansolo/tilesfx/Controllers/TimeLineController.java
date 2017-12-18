package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.tools.Messenger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimeLineController implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private CheckComboBox checkComboBox;
    @FXML
    private TextField textField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;
    @FXML
    private Text assertText;

    Stage myStage;


    final String mainBuildScene = "Build Overview";
    final String mainTestScene = "Test Overview";
    final String mainStageScene = "Stage Overview";

    final String opticBuildScene = "Optic Build/Test";
    final String posBuildScene = "POS Build/Test";
    final String retailBuildScene = "Retail Build/Test";
    final String serversBuildScene = "Servers Build/Test";
    final String periphBuildScene = "Periph Build/Test";

    final String posStageScene = "POS/Servers Stage";
    final String retailStageScene = "Retail Stage";
    final String periphStageScene = "Periph Stage";

    Messenger messenger;

    Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> adding = new ArrayList<>();

        adding.add(mainBuildScene);
        adding.add(mainTestScene);
        adding.add(mainStageScene);

        adding.add(opticBuildScene);
        adding.add(posBuildScene);
        adding.add(retailBuildScene);
        adding.add(serversBuildScene);
        adding.add(periphBuildScene);

        adding.add(posStageScene);
        adding.add(retailStageScene);
        adding.add(periphStageScene);

        // create the data to show in the CheckComboBox
        final ObservableList<String> strings = FXCollections.observableArrayList(adding);

        checkComboBox.getItems().addAll(strings);

        createActions();
    }

    private void createActions()
    {
        checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(checkComboBox.getCheckModel().getCheckedItems());
            }
        });
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textField.getText().equals(""))
                {
                    ObservableList<String> boxes = FXCollections.observableArrayList(checkComboBox.getCheckModel().getCheckedItems());
                    if(boxes.size() == 0 || boxes.size() == 1)
                    {
                        assertText.setText("PLEASE SELECT AT LEAST TWO BOXES.");
                        assertText.setVisible(true);
                        textField.clear();
                        checkComboBox.getCheckModel().getCheckedItems().clear();
                    }
                    int convert = Integer.valueOf(textField.getText());
                    if (convert <= 0)
                    {
                        assertText.setText("VALUE MUST BE GREATER THAN 0.");
                        assertText.setVisible(true);
                        textField.clear();
                    }
                    if(boxes.size()>2 && convert > 0)
                    {
                        timeline = new Timeline();
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        ArrayList<KeyFrame> frames = new ArrayList<>();
                        Duration userDuration;
                        int temp = 0;
                        for(int i = 0;i < boxes.size();i++)
                        {
                            EventHandler myHandler = null;
                            userDuration = Duration.seconds(convert*i);
                            temp = convert*i;
                            if(boxes.get(i).equals("Build Overview"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
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
                                };
                            }
                            if(boxes.get(i).equals("Test Overview"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        MainTestController buildController = messenger.getMainTestController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/mainTestScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("Stage Overview"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
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
                                };
                            }
                            if(boxes.get(i).equals("Optic Build/Test"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        OpticBuildController buildController = messenger.getOpticBuildController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/opticBuildScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("POS Build/Test"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        POSBuildController buildController = messenger.getPosBuildController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/posBuildScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("Retail Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        RetailBuildController buildController = messenger.getRetailBuildController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/retailBuildScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("Servers Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        ServersBuildController buildController = messenger.getServersBuildController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/serversBuildScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("Periph Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        PeriphBuildController buildController = messenger.getPeriphBuildController();

                                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/periphBuildScreen.fxml"));
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
                                };
                            }
                            if(boxes.get(i).equals("POS/Servers Stage"))
                            {

                            }
                            if(boxes.get(i).equals("Periph Stage"))
                            {

                            }
                            if(boxes.get(i).equals("Retail Stage"))
                            {

                            }
                            userDuration = Duration.seconds(temp + convert);
                            KeyFrame tempFrame = new KeyFrame(userDuration,myHandler);
                            frames.add(tempFrame);
                        }
                        userDuration = Duration.seconds(convert);
                        KeyFrame buffer = new KeyFrame(userDuration);
                        frames.add(buffer);
                        timeline.getKeyFrames().addAll(frames);
                        System.out.println(frames);
                        timeline.play();

                        Stage closeMe = getStage();
                        closeMe.close();
                    }
                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Stage temp = getStage();
                temp.close();
            }
        });
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Stage getStage() {
        return myStage;
    }

    public void setStage(Stage myStage)
    {
        this.myStage = myStage;
    }
    public Timeline getTimeline() {
        return timeline;
    }
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}
