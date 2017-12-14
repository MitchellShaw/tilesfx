package eu.hansolo.tilesfx.Controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.Messenger;
import eu.hansolo.tilesfx.tools.Tool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static javafx.scene.paint.Color.rgb;

public class POSBuildController implements Initializable
{
    Tile logo;
    Tile clock;
    Tile dept;
    Tile stopLight;
    Tile daySince;

    Tile posBuild;
    Tile posPercent;
    Tile posFTT;
    Tile posQuant;

    Tile posTest;
    Tile posTestPercent;
    Tile posTestFTT;
    Tile posTestQuant;

    Tile message;

    Messenger messenger;

    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        MainBuildController mainBuildController = messenger.getMainBuildController();
        MainTestController mainTestController = messenger.getMainTestController();

        posBuild = mainBuildController.getPosBuild();

        posPercent = mainBuildController.getPosPercent();

        posFTT = mainBuildController.getPosFTT();

        posTest = mainTestController.getPosTest();


        posTestPercent = mainTestController.getPosTestPercent();

        posTestFTT = mainTestController.getPosFTT();

        pane.add(posTestFTT,3,3,1,1);

        final ImageView logoView = new ImageView();
        final Image logoImage = new Image("/NCR Brand Block Logo JPG.jpg");
        logoView.setImage(logoImage);
        logoView.setFitHeight(217);
        logoView.setFitWidth(384);
        logoView.setPreserveRatio(true);

        HBox hbox = new HBox(logoView);
        hbox.setPrefWidth(384);
        hbox.setPrefHeight(217);
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
        stopView.setFitHeight(217);
        stopView.setFitWidth(480);
        stopView.setPreserveRatio(true);

        HBox myBox = new HBox(stopView);
        myBox.setPrefWidth(384);
        myBox.setPrefHeight(217);
        myBox.setAlignment(Pos.CENTER);
        myBox.setStyle("-fx-background-color:#54B948");

        clock = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .prefSize(384, 217)
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
                .prefSize(384,217)
                .roundedCorners(false)
                .graphic(hbox)
                .build();

        stopLight = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.valueOf("#54B948"))
                .prefSize(384,217)
                .roundedCorners(false)
                .graphic(myBox)
                .build();

        daySince = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(384, 217)
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Days Since Last Safety Incident")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description(useDate)
                .build();

        dept = TileBuilder.create()
                .skinType(Tile.SkinType.CHARACTER)
                .prefSize(384,217)
                .backgroundColor(Color.valueOf("#54B948"))
                .title("Department")
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .description("POS")
                .build();
        Tile filler = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(651,217)
                .backgroundColor(rgb(42, 42, 42))
                .titleAlignment(TextAlignment.CENTER)
                .roundedCorners(false)
                .build();


        pane.add(posBuild,1,0,1,2);
        pane.add(posPercent,2,0,1,1);
        pane.add(posFTT,3,0,1,1);
        pane.add(posTest,1,3,1,2);
        pane.add(posTestPercent,2,3,1,1);
        //pane.add(posTestFTT,3,3,1,1);
        pane.add(logo,0,0,1,1);
        pane.add(clock,0,1,1,1);
        pane.add(dept,0,2,1,1);
        pane.add(stopLight,0,3,1,1);
        pane.add(daySince,0,4,1,1);
        pane.add(filler,1,2,3,1);

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

    public Tile getPosBuild() {
        return posBuild;
    }

    public Tile getPosPercent() {
        return posPercent;
    }

    public Tile getPosFTT() {
        return posFTT;
    }

    public Tile getPosQuant() {
        return posQuant;
    }

    public Tile getPosTest() {
        return posTest;
    }

    public Tile getPosTestPercent() {
        return posTestPercent;
    }

    public Tile getPosTestFTT() {
        return posTestFTT;
    }

    public Tile getPosTestQuant() {
        return posTestQuant;
    }

    public Tile getMessage() {
        return message;
    }
}
