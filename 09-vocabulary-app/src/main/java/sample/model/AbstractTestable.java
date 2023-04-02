package sample.model;

import javafx.beans.InvalidationListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Basisklasse für Testables. Sie implementiert die Basisfunktion zum Zuordnen eines Vokabelschatzes und zum
 * Anbinden von Beobachtern.
 */
public abstract class AbstractTestable implements Testable
{
  /* * * * * * * * * * * * * * * * * * *
   * Zuordnung zu einem Vokabelschatz  *
   * * * * * * * * * * * * * * * * * * */

  // Zugehöriger Vokabelschatz
  private Vocabulary vs;

  /**
   * Setzen des zugehörigen Vokabelschatzes. Ein Testable darf nur einmal zu einem Vokabelschatz zugeordnet werden.
   * Andernfalls wird ein entsprechender Fehler geworfen.
   *
   * @param v zugehöriger Vokabelschatz
   *
   * @throws RuntimeException Falls das Testable bereits einem anderen Vokabelschatz zugeordnet war.
   */
  @Override
  public void setVocabulary(Vocabulary v)
  {
    if (vs != null || v == null)
    {
      throw new RuntimeException("Deletion and Reset are not allowed");
    }
    else if (v.getTestables().contains(this))
    {
      this.vs = v;
    }
    else
    {
      throw new RuntimeException("Vocabulary can only be set when Testable has been added first.");
    }
  }

  /**
   * Liefert den zugeordneten Vokabelschatz.
   *
   * @return zugeordneter Vokabelschatz
   */
  @Override
  public Vocabulary getVocabulary()
  {
    return vs;
  }

  /* * * * * * * * * * * * * * * * * * * * * *
   * Prinzipielle Anbindung von Beobachtern  *
   * * * * * * * * * * * * * * * * * * * * * */

  // Liste von Beobachtern
  private List<InvalidationListener> listeners = new LinkedList<>();

  /**
   * Hinzufügen eines Beobachters
   *
   * @param listener neues Beobachter
   */
  @Override
  public void addListener(InvalidationListener listener)
  {
    listeners.add(listener);
  }

  /**
   * Entfernen eines Beobachters
   *
   * @param listener zu entfernender Beobachter
   */
  @Override
  public void removeListener(InvalidationListener listener)
  {
    listeners.remove(listener);
  }

  /**
   * Interne Methode zum Bekanntmachen einer Änderung. Die Änderung wird dann allen Beobachtern mitgeteilt.
   */
  public void invalidate()
  {
    for (InvalidationListener listener : listeners)
    {
      try
      {
        listener.invalidated(this);
      }
      catch (RuntimeException ex)
      {
      }
    }
  }
}
