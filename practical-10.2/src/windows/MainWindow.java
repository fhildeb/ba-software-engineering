package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pattern.Observable;
import pattern.ObservableSlider;

import java.io.IOException;

public class MainWindow
{

    @FXML
    private Slider slider;
    @FXML
    private Label ausgabeLabel;
    
    private Observable observable;
    
    @FXML
    public void initialize()
    {
    	observable = new ObservableSlider(slider);
    	observable.attach( (o, v) -> ausgabeLabel.setText(String.format("%06.2f", v)) );
    }

    @FXML
    private void onNewTextObserver(ActionEvent actionEvent)
    {
    	TextObserver t = TextObserver.create();
    	t.setObservable(observable);
    }

    @FXML
    private void onNewGraphicObserver(ActionEvent actionEvent)
    {
    	GraphicObserver g = GraphicObserver.create();
    	g.setObservable(observable);
    }

    @FXML
    private void onShowCounts(ActionEvent actionEvent)
    {
    	Alert msg = new Alert(Alert.AlertType.INFORMATION);
    	msg.setHeaderText("Observer Information");
    	msg.setContentText("You currently have "+ observable.countObservers()+"observers. ");
    	msg.showAndWait();
    }

    /*
       Statische Methode zum erzeugen eines neuen Textobservers.
    */
    public static MainWindow create(Stage s)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(TextObserver.class.getResource("MainWindow.fxml"));
            Parent root = loader.load();
            MainWindow controller = loader.getController();
            s.setTitle("Observer-Pattern");
            s.setScene(new Scene(root));
            s.setResizable(false);
            s.show();
            return controller;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
