package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pattern.Observable;
import pattern.Observer;

import java.io.IOException;


public class TextObserver implements Observer
{

    @FXML
    private Button attachButton;
    @FXML
    private Button detachButton;
    @FXML
    private Label outputLabel;

    private Observable observable;
	public void setObservable(Observable observable) {
		this.observable = observable;
	}
    /*
        Statische Methode zum erzeugen eines neuen Textobservers.
    */
    public static TextObserver create()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(TextObserver.class.getResource("TextObserver.fxml"));
            Parent root = loader.load();
            TextObserver controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Text Observer");
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
		String text = String.format("Aktuelle Information: %06.2f", value);
		outputLabel.setText(text);
		
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
