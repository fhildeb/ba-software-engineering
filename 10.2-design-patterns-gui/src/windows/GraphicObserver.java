package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import pattern.Observable;
import pattern.Observer;

import java.io.IOException;


public class GraphicObserver implements Observer
{

    @FXML
    private Button attachButton;
    @FXML
    private Button detachButton;
    @FXML
    private ProgressBar outputBar;

    private Observable observable;
	public void setObservable(Observable observable) {
		this.observable = observable;
	}
    /*
        Statische Methode zum erzeugen eines neuen Grafikobservers.
    */
    public static GraphicObserver create()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(GraphicObserver.class.getResource("GraphicObserver.fxml"));
            Parent root = loader.load();
            GraphicObserver controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Graphic Observer");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            return controller;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public void update(Observable o, double value) {
		double percentage = value / 100;
		outputBar.setProgress(percentage);
	}
	
	@FXML
	private void onAttach(ActionEvent actionEvent)
	{
		observable.attach(this);
		
		attachButton.setDisable(true);
		detachButton.setDisable(false);
		
	}
	
	@FXML
	private void onDetach(ActionEvent actionEvent) {
		observable.detach(this);
		
		detachButton.setDisable(true);
		attachButton.setDisable(false);
	}
}
