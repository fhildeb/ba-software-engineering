package sample.model;

import javafx.collections.ObservableList;
import sample.Helper;

/**
 * Ein Vokabelschatz, der aus unterschiedlichen Lektionen und Testables besteht.
 */
public class Vocabulary
{
  // Name des Vokabelschatzes
  private String name;

  // Liste der zugehörigen Lektionen
  private ObservableList<Unit> units;

  // Liste aller zugehörigen Testables
  private ObservableList<Testable> testables;

  /**
   * Neuen Vokabelschatz anlegen.
   *
   * @param name Name des Vokabelschatzes, in der Regel ist es der Name der Fremdsprache
   */
  public Vocabulary(String name)
  {
    this.name = name;
    units = Helper.newDeepObservableList();
    testables = Helper.newDeepObservableList();
  }

  /**
   * Name des Vokabelschatzes liefern.
   *
   * @return Name des Vokabelschatzes, in der Regel ist es der Name der Fremdsprache
   */
  public String getName()
  {
    return name;
  }

  /**
   * Name des Vokabelschatzes setzen.
   *
   * @param name Name des Vokabelschatzes, in der Regel ist es der Name der Fremdsprache
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Neue Lektion in diesem Vokabelschatz anlegen. Die Lektion ist dem Vokabelschatz automatisch zugeordnet.
   *
   * @param name Name der Lektion
   *
   * @return Instanz der neuangelegten Lektion.
   */
  public Unit newUnit(String name)
  {
    return new Unit(this, name);
  }

  /**
   * Neues Testable zu diesem Vokabelschatz hinzufügen. Ein Testable kann nur einmalig einem Vokabelschatz zugeordnet
   * werden. Andernfalls wird ein Fehler geworfen.
   *
   * @param t hinzuzufügendes Testable
   * @throws RuntimeException Falls das Testable bereits einem anderen Vokabelschatz zugeordnet war.
   */
  public void addTestable(Testable t)
  {
    if (t.getVocabulary() == null)
    {
      testables.add(t);
      t.setVocabulary(this);
    }
    else if (t.getVocabulary() == this)
    {
      testables.add(t);
    }
    else
    {
      throw new RuntimeException("Testable can only be added to one vocabulary.");
    }
  }

  /**
   * Liefert die Liste aller Lektionen dieses Vokabelschatzes.
   *
   * @return Liste aller Lektionen dieses Vokabelschatzes.
   */
  public ObservableList<Unit> getUnits()
  {
    return units;
  }

  /**
   * Liefert die Liste aller Testables dieses Vokabelschatzes.
   *
   * @return Liste aller Testables dieses Vokabelschatzes.
   */
  public ObservableList<Testable> getTestables()
  {
    return testables;
  }

}
