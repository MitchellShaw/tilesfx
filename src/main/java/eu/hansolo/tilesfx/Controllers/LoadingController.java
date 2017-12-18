package eu.hansolo.tilesfx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable
{
    @FXML
    private ImageView image;
    @FXML
    private ProgressIndicator loadingBar;
    @FXML
    private GridPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        pane.setCursor(Cursor.NONE);

    }
}
