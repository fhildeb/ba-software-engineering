package sample.model.testables;

import java.util.ArrayList;
import java.util.List;

import sample.Helper;
import sample.model.AbstractTestable;
import sample.model.Question;

public class Antonym extends AbstractTestable {
	
	private String sunnySide;
	private String darkSide;
	
	public Antonym(String sunnySide, String darkSide) {
		this.sunnySide = sunnySide;
		this.darkSide = darkSide;
	};
	
	public String getSunnySide() {
		return this.sunnySide;
	}
	
	public void setSunnySide(String sunnySide) {
		this.sunnySide = sunnySide;
	}
	
	public String getDarkSide() {
		return this.darkSide;
	}
	
	public void setDarkSide(String darkSide) {
		this.darkSide = darkSide;
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
		language = getVocabulary().getName();
		if (Helper.randomBoolean())
		{
		word = sunnySide;
		answers.add(darkSide);
		}
		else
		{ 
		word = darkSide;
		answers.add(sunnySide);
		}
		// Formulierung des Fragetextes
		String text = String.format("Wie heißt das Gegenteil von %s auf %s?", word, language);
		// Konstruktion des Frage-Objektes
		return new Question(text, answers);
	}
	
	public String toString() {
		return String.format("Antonym: %s / %s", sunnySide, darkSide);
	}
}
