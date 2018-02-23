package main.java.eu.hansolo.tilesfx.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import main.java.eu.hansolo.tilesfx.tools.Messenger;
import org.controlsfx.control.CheckComboBox;

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

    final String posLineOverview = "POS Build Lines";
    final String retailLineOverview = "Retail Build Lines";
    final String serversLineOverview = "Servers Build Lines";
    final String periphLineOverview = "Periph Build Lines";
    final String opticLineOverview = "Optic Build Lines";

    final String posLine1 = "POS Line 1";
    final String posLine2 = "POS Line 2";
    final String posLine3 = "POS Line 3";
    final String posLine4 = "POS Line 4";
    final String posQuest = "POS Quest";
    final String posT1000 = "POS T1000";

    final String nextGenLine1 = "Retail Line 1";
    final String nextGenLine2 = "Retail Line 2";
    final String nextGenLine3 = "Retail Line 3";
    final String nextGenLine4 = "Retail Line 4";
    final String nextGenLine5 = "Retail Line 5";
    final String nextGenDisplay = "Retail Display";

    final String serversLine1 = "Servers Line 1";
    final String serversLine2 = "Servers Line 2";

    final String periphLine1 = "Periph Line 1";
    final String periphLine2 = "Periph Line 2";

    final String opticLine1 = "Optic Line 1";
    final String opticLine2 = "Optic Line 2";
    final String opticLine3 = "Optic Line 3";
    final String opticLine4 = "Optic Line 4";

    final String posTestUser = "POS Test by User";
    final String retailTestUser = "Retail Test by User";
    final String periphTestUser = "Periph Test by User";
    final String serversTestUser = "Servers Test by User";
    final String opticTestUser = "Optic Test by User";

    final String selectAll = "Select All";

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

        adding.add(posLineOverview);
        adding.add(retailLineOverview);
        adding.add(serversLineOverview);
        adding.add(periphLineOverview);
        adding.add(opticLineOverview);

        adding.add(posLine1);
        adding.add(posLine2);
        adding.add(posLine3);
        adding.add(posLine4);
        adding.add(posQuest);
        adding.add(posT1000);
        adding.add(nextGenLine1);
        adding.add(nextGenLine2);
        adding.add(nextGenLine3);
        adding.add(nextGenLine4);
        adding.add(nextGenLine5);
        adding.add(nextGenDisplay);
        adding.add(serversLine1);
        adding.add(serversLine2);
        adding.add(periphLine1);
        adding.add(periphLine2);
        adding.add(opticLine1);
        adding.add(opticLine2);
        adding.add(opticLine3);
        adding.add(opticLine4);

        adding.add(posTestUser);
        adding.add(retailTestUser);
        adding.add(periphTestUser);
        adding.add(serversTestUser);
        adding.add(opticTestUser);

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
                    if(boxes.size()>=2 && convert > 0)
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
                            if(boxes.get(i).equals("POS Build Lines"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosBuildOverview());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Build Lines"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getRetailBuildOverview());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Servers Build Lines"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getServersBuildOverview());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Periph Build Lines"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphBuildOverview());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Build Lines"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticBuildOverview());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Line 1"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosLine1());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Line 2"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosLine2());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Line 3"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosLine3());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Line 4"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosLine4());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Quest"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosQuest());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS T1000"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosT1000());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Line 1"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenLine1());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Line 2"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenLine2());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Line 3"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenLine3());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Line 4"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenLine4());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Line 5"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenLine5());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Display"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getNextGenDisplay());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Servers Line 1"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getServersLine1());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Servers Line 2"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getServersLine2());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Periph Line 1"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphLine1());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Periph Line 2"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphLine2());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Line 1"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticLine1());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Line 2"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticLine2());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Line 3"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticLine3());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Line 4"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticLine4());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("POS Test by User"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPosTestUser());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Retail Test by User"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getRetailTestUser());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Servers Test by User"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getServersTestUser());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Periph Test by User"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getPeriphTestUser());

                                    }
                                };

                            }
                            if(boxes.get(i).equals("Optic Test by User"))
                            {
                                myHandler = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        messenger.getPrimaryStage().setScene(messenger.getOpticTestUser());

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
