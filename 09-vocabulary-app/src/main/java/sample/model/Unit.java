package sample.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import sample.Helper;

import java.util.LinkedList;
import java.util.List;

/**
 * Eine Lektion eines Vokabelschatzes. Sie kann unterschiedliche Testables enthalten.
 */
public class Unit implements Observable
{
  // Zugehöriger Vokabelschatz
  private Vocabulary vs;

  // Name der Lektion
  private String name;

  // Liste aller zugehörigen Testables
  private ObservableList<Testable> testables;

  /**
   * Anlegen einer neuen Lektion, die einem Vokabelschatz fest zugeordnet ist.
   *
   * @param vs   Zugehöriger Vokabelschatz
   * @param name Name der Lektion
   */
  public Unit(Vocabulary vs, String name)
  {
    this.name = name;
    testables = Helper.newDeepObservableList();
    this.vs = vs;
    vs.getUnits().add(this);
  }

  /**
   * Liefert den Namen der Lektion.
   *
   * @return Name der Lektion
   */
  public String getName()
  {
    return name;
  }

  /**
   * Setzt den Namen der Lektion.
   *
   * @param name Name der Lektion
   */
  public void setName(String name)
  {
    this.name = name;
    invalidate();
  }

  /**
   * Liefert die Liste aller Testables dieser Lektion.
   *
   * @return Liste aller Testables dieser Lektion.
   */
  public ObservableList<Testable> getTestables()
  {
    return testables;
  }

  /**
   * Neues Testable zu dieser Lektion hinzufügen. (Ein Testable kann nur einmalig einem Vokabelschatz zugeordnet werden.
   * Andernfalls wird ein Fehler geworfen.)
   *
   * @param t hinzuzufügendes Testable
   *
   * @throws RuntimeException Falls das Testable bereits einem anderen Vokabelschatz zugeordnet war.
   */
  public void addTestable(Testable t)
  {
    vs.addTestable(t);
    this.testables.add(t);
    invalidate();
  }

  /**
   * Entfernen eines Testables von dieser Lektion.
   *
   * @param t zu entfernendes Testable
   */
  public void removeTestable(Testable t)
  {
    testables.remove(t);
    invalidate();
  }

  /**
   * Darstellung der Lektion als Zeichenkette
   *
   * @return Darstellung der Lektion als Zeichenkette.
   */
  @Override
  public String toString()
  {
    return String.format("Lektion %s (%d)", name, testables.size());
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
  protected void invalidate()
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
