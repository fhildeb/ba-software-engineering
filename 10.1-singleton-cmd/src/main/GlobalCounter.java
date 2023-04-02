package main;

public class GlobalCounter
{
	/**
	* Statischer Anteil der Klasse
	* Verwaltung der Singleton-Instanz
	*/
	
	// Die eine Instanz der Zaehlerklasse
	private static GlobalCounter instance;
	
	// Herausgabe der einen Instanz der Zaehlerklasse
	public static GlobalCounter getInstance()
	{
		if(instance == null)
		{
			instance = new GlobalCounter();
		}
		return instance;
	}
	
	/**
	* Normaler Anteil der Klasse
	* Implementierung des Zaehlers
	*/
	
	// Interner Zaehlwert
	private int value;
	
	// Privater Konstruktor, von außen kann keine Instanz angelegt werden
	private GlobalCounter()
	{
		value = 0;
	}
	
	// Rueckgabe des Zaehlwertes
	public int getValue()
	{
		return value;
	}
	
	// Erhoehung des Zaehlers um einen bestimmten Wert 
	public void count(int amount)
	{
		value += amount;
	}
}

