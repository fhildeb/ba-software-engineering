package main;

public class Singleton {

	public static Singleton getInstance() {
		return new Singleton();
	}
	
	private Singleton() {
		
	}
}
