package sample.model.testables;

import sample.Helper;
import sample.model.AbstractTestable;
import sample.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine Synonymgruppe aus mindestens zwei Worten mit gleicher Bedeutung.
 */
public class SynonymGroup extends AbstractTestable
{
	private List<String> words;
	
	public SynonymGroup(List<String> words) {
		if(words.size() < 2) {
			System.out.println("Weniger als 2 Wörter in SynonymGroup");
		}
		
		this.words = words;
	}
	
	public List<String> getWords(){
		return this.words;
	}
	
	public void setWords(List<String> words) {
		this.words = words;
	}
	
	public Question createQuestion() {
		int randNumber = Helper.randomNumber(words.size());
				
		// Zielsprache
		String language;
		// Vokabelteil in Quellsprache
		String word;
		// Liste für Antworten
		List<String> answers = new ArrayList<>();

		language = getVocabulary().getName();
		word = words.get(randNumber);
		for(int i=0; i<words.size();i++)
		{
			if(i == randNumber)
				i = i+1;
			answers.add(words.get(i));
		}
		
		// Formulierung des Fragetextes
		String text = String.format("Nennen sie ein Synonym zu %s auf %s?", word, language);
		// Konstruktion des Frage-Objektes
		return new Question(text, answers);
		
	}
	
	public String toString() {
		String wordsList = Helper.join(words);
		return String.format("Synonym: %s ", wordsList);
	}
}
