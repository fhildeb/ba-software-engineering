package pattern;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable
{
	private List<Observer> observers;

	protected Observable()
	{
		observers = new LinkedList<>();
	}
	
	public void attach(Observer o)
	{
		observers.add(o);
	}
	
	public void detach(Observer o)
	{
		observers.remove(o);
	}
	
	public int countObservers()
	{
		return observers.size();
	}
	
	protected void fireUpdate(double value)
	{
		for (Observer o : observers)
		{
			o.update(this, value);
		}
	}
}
