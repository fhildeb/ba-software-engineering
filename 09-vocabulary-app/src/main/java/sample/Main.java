package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Unit;
import sample.model.Vocabulary;
import sample.model.testables.Word;
import sample.model.testables.SynonymGroup;

import java.util.Arrays;

public class Main extends Application
{

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource("Window.fxml"));
    primaryStage.setTitle("Vokabeltester");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
  }

  
  public static void testModel()
  {
    Vocabulary v = new Vocabulary("englisch");
    v.addTestable(new Word("Universität", "university"));
    Unit uAnimals = v.newUnit("Animals");
    uAnimals.addTestable(new Word("Katze", "cat"));
    uAnimals.addTestable(new Word("Hund", "dog"));
    Unit uSmalltalk = v.newUnit("Smalltalk");
    uSmalltalk.addTestable(new Word("Hallo", "Hello"));
    uSmalltalk.addTestable(new SynonymGroup(Arrays.asList("nice", "friendly", "delightful", "polite")));

    System.out.println("Vokabelschatz " + v.getName());
    v.getTestables().forEach(t -> System.out.println(" --> " + t));
    for (Unit u : v.getUnits())
    {
      System.out.println("Lektion " + u.getName());
      u.getTestables().forEach(t -> System.out.println(" --> " + t));
    }
  }
  

  public static void main(String[] args)
  {
    System.out.println("Programm startet");
    launch(args);
  }
}
