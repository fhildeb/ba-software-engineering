package sample.model;

import javafx.beans.Observable;

/**
 * Schnittstelle für ein prüfbares Objekt. Prüfbar heißt hier, dass das Objekt zu einem Vokabelschatz zugehörig ist und
 * dass von ihm eine Leistungskontrollfrage abgeleitet werden kann.
 */
public interface Testable extends Observable
{
  /**
   * Setzen des zugehörigen Vokabelschatzes. Ein Testable darf nur einmal zu einem Vokabelschatz zugeordnet werden.
   * Andernfalls wird ein entsprechender Fehler geworfen.
   *
   * @param v zugehöriger Vokabelschatz
   *
   * @throws RuntimeException Falls das Testable bereits einem anderen Vokabelschatz zugeordnet war.
   */
  void setVocabulary(Vocabulary v);

  /**
   * Liefert den zugeordneten Vokabelschatz.
   *
   * @return zugeordneter Vokabelschatz
   */
  Vocabulary getVocabulary();

  /**
   * Erzeugt eine Leistungskontrollfrage aus dem Testable.
   *
   * @return passende Leistungskontrollfrage
   */
  Question createQuestion();

  /**
   * Darstellung des Testables als Zeichenkette
   *
   * @return Darstellung des Testables als Zeichenkette.
   */
  String toString();
}
