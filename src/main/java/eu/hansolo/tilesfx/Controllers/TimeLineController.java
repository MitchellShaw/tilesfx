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
                                    public void handle(Event event)
                                    {
                                        messenger.getPrimaryStage().setScene(messenger.getMainBuild());
                                    }
                                };
                            }
                            if(boxes.get(i).equals("Test Overview"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getMainTest());

                                    }
                                };
                            }
                            if(boxes.get(i).equals("Stage Overview"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event)
                                    {
                                        messenger.getPrimaryStage().setScene(messenger.getMainStage());
                                    }
                                };
                            }
                            if(boxes.get(i).equals("Optic Build/Test"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticBuild());
                                    }
                                };
                            }
                            if(boxes.get(i).equals("POS Build/Test"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosBuild());

                                    }
                                };
                            }
                            if(boxes.get(i).equals("Retail Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getRetailBuild());
                                    }
                                };
                            }
                            if(boxes.get(i).equals("Servers Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event)
                                    {
                                        messenger.getPrimaryStage().setScene(messenger.getServerBuild());
                                    }
                                };
                            }
                            if(boxes.get(i).equals("Periph Build/Test"))
                            {
                                 myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphBuild());

                                    }
                                };
                            }
                            if(boxes.get(i).equals("POS/Servers Stage"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosStage());

                                    }
                                };
                            }
                            if(boxes.get(i).equals("Periph Stage"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphStage());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Stage"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getRetailStage());

                                    }
                                };

                            }
                            userDuration = Duration.seconds(temp + convert);
                            KeyFrame tempFrame = new KeyFrame(userDuration,myHandler);
                            frames.add(tempFrame);
                        }
                        userDuration = Duration.seconds(convert);
                        KeyFrame buffer = new KeyFrame(userDuration);
                        frames.add(buffer);
                        timeline.getKeyFrames().addAll(frames);
                        timeline.play();

                        Window closeMe = messenger.getTimelineScene().getWindow();
                        closeMe.hide();
                    }
                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Window closeMe = messenger.getTimelineScene().getWindow();
                closeMe.hide();
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
