package main;

import javafx.application.Application;
import javafx.stage.Stage;
import windows.MainWindow;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MainWindow.create(primaryStage);
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
