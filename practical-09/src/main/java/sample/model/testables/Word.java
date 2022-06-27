package sample.model.testables;

import sample.Helper;
import sample.model.AbstractTestable;
import sample.model.Question;
import java.util.ArrayList;
import java.util.List;

/**
 * Normale Vokalbel aus einem deutschsprachigen und einem fremdsprachigen Wortteil.
 */
public class Word extends AbstractTestable
{
	private String local;
	private String foreign;
	
	public Word(String local, String foreign) {
		this.local = local;
		this.foreign = foreign;
	};
	
	public String getLocal() {
		return this.local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getForeign() {
		return this.foreign;
	}
	
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	
	public Question createQuestion()
	{
		// Zielsprache
		String language;
		// Vokabelteil in Quellsprache
		String word;
		// Liste für genau eine Antwort
		List<String> answers = new ArrayList<>();
		// Zufällige Auswahl der Richtung
		if (Helper.randomBoolean())
		{
		// Deutsch --> Fremdsprache
		language = getVocabulary().getName();
		word = local;
		answers.add(foreign);
		}
		else
		{ 
		// Fremdsprache --> Deutsch
		language = "deutsch";
		word = foreign;
		answers.add(local);
		}
		// Formulierung des Fragetextes
		String text = String.format("Wie heißt das Wort %s auf %s?", word, language);
		// Konstruktion des Frage-Objektes
		return new Question(text, answers);
	}
	
	public String toString() {
		return String.format("Vokabel: %s / %s", local, foreign);
	}
}
