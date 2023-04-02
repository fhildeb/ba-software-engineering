package sample.model;

import java.util.List;

/**
 * Eine einfache Leistungskontrollfrage mit Fragetext und als richtig erkannten Antwortmöglichkeiten.
 */
public class Question
{
  // Fragetext
  private String text;

  // Liste als richtig erkannter Antworten
  private List<String> answers;

  /**
   * Erzeugen einer neuen Leistungskontrollfrage.
   *
   * @param text    Fragetext
   * @param answers Liste als richtig erkannter Antworten
   */
  public Question(String text, List<String> answers)
  {
    this.text = text;
    this.answers = answers;
  }

  /**
   * Liefert den Fragetext.
   *
   * @return Fragetext
   */
  public String getText()
  {
    return text;
  }

  /**
   * Prüft, ob eine Antwort auf die Frage richtig ist.
   *
   * @param antwort zu prüfende Antwort
   *
   * @return Prüfergebnis als Wahrheitswert.
   */
  public boolean check(String antwort)
  {
    return answers.contains(antwort);
  }
}
