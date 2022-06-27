package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import sample.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Helper
{
  // Zufallsgenerator
  private static Random random = new Random();

  /**
   * Liefert einen zufälligen Wahrheitswert.
   *
   * @return zufälliger Wahrheitswert
   */
  public static boolean randomBoolean()
  {
    return random.nextBoolean();
  }

  /**
   * Liefert einen zufälligen Zahlenwert zwischen 0 und limit (exklusiv).
   *
   * @param limit Obere Grenze
   *
   * @return zufälligen Zahlenwert zwischen 0 und limit (exklusiv)
   */
  public static int randomNumber(int limit)
  {
    return random.nextInt(limit);
  }

  /**
   * Erzeugt aus einer Zeichenkette mit kommagetrennten Worten eine Liste dieser Worte.
   *
   * @param s Zeichenkette mit kommagetrennten Worten
   *
   * @return Liste der extrahierten Worte
   */
  public static List<String> split(String s)
  {
    return Arrays.asList(s.split("\\s*,\\s*"));
  }

  /**
   * Erzeugt aus einer Liste mit Worten eine Zeichenkette, in der die Worte durch Kommata getrennt werden.
   *
   * @param list Liste mit Worten
   *
   * @return Zeichenkette, in der die Worte durch Kommata getrennt werden
   */
  public static String join(List<String> list)
  {
    if (list != null)
    {
      return list.stream().collect(Collectors.joining(", "));
    }
    else
    {
      return "";
    }
  }

  /**
   * Fragt den Benutzer mit einem grafischen Dialog.
   *
   * @param title    Titel der Frage.
   * @param question Textlaut der Frage.
   * @param answer   Antwortvorgabe
   *
   * @return Antwort des Benutzers oder null.
   */
  public static String ask(String title, String question, String answer)
  {
    TextInputDialog dialog = new TextInputDialog(answer);
    dialog.setTitle(title);
    dialog.setHeaderText(question);
    dialog.showAndWait();
    return dialog.getResult();
  }

  /**
   * Fragt den Benutzer mit einem grafischen Dialog.
   *
   * @param title    Titel der Frage.
   * @param question Textlaut der Frage.
   *
   * @return Antwort des Benutzers oder null.
   */
  public static String ask(String title, String question)
  {
    return ask(title, question, "");
  }

  /**
   * Führt eine Leistungskontrollfrage mit dem Benutzer durch. Dabei wird die Frage per Eingabedialog angezeigt, die
   * Antwort abgefordert und überprüft. Der Benutzer erhält anschließend eine Rückmeldung, ob er richtig oder falsch
   * geantwortet hat.
   *
   * @param q
   */
  public static void test(Question q)
  {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Leistungskontrolle");
    dialog.setHeaderText(q.getText());
    dialog.showAndWait();
    String answer = dialog.getResult();
    if (q.check(answer))
    {
      new Alert(Alert.AlertType.CONFIRMATION, "Richtig!").showAndWait();
    }
    else
    {
      new Alert(Alert.AlertType.ERROR, "Falsch!").showAndWait();
    }
  }

  /**
   * Erzeugt eine neues Java-FX-ObservableList für beobachtbare Objekte, sodass auch Änderungen der Listenelemente
   * propagiert werden.
   *
   * @param <T> Generischer Inhaltstyp der Liste, muss selbst Observable sein
   *
   * @return Neu erzeugte Liste.
   */
  public static <T extends Observable> ObservableList<T> newDeepObservableList()
  {
    return FXCollections.observableArrayList(p -> new Observable[]{p});
  }

  /**
   * Erzeugt eine neues Java-FX-ObservableList
   *
   * @param <T> Generischer Inhaltstyp der Liste
   *
   * @return Neu erzeugte Liste.
   */
  public static <T> ObservableList<T> newObservableList()
  {
    return FXCollections.observableArrayList();
  }
}
