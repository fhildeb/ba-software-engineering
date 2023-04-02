package sample;

import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.model.Testable;
import sample.model.Unit;
import sample.model.Vocabulary;
import sample.model.testables.Antonym;
import sample.model.testables.SynonymGroup;
import sample.model.testables.Word;

public class Controller
{
	Vocabulary v;
	int indexGesamtListen = 0;
	@FXML
	private Label title;
	
	@FXML
	private ListView<Unit> units;
	
	@FXML
	private ListView<Testable> testables;
	
	public Controller() {
		
		//Neues Test-Vokabular
		this.v = new Vocabulary("Englisch");
		Unit uAnimals = v.newUnit("Animals");
		uAnimals.addTestable(new Word("Katze", "cat"));
		uAnimals.addTestable(new Word("Hund", "dog"));
		Unit uSmalltalk = v.newUnit("Smalltalk");
		uSmalltalk.addTestable(new Word("Hallo", "Hello"));
		uSmalltalk.addTestable(new SynonymGroup(Arrays.asList("nice", "friendly", "delightful", "polite")));
		
		// Testausgabe auf der Konsole
		/*  
		 	System.out.println("Vokabelschatz " + v.getName());
		    v.getTestables().forEach(t -> System.out.println(" --> " + t));
		    for (Unit u : v.getUnits())
		    {
		    	System.out.println("Lektion " + u.getName());
		      	u.getTestables().forEach(t -> System.out.println(" --> " + t));
		    }
		 */	
	}
	
	@FXML
	private void initialize()
	{
		title.setText(v.getName());
		units.itemsProperty().setValue(v.getUnits());
		
		ObjectExpression<Unit> selection = units.getSelectionModel().selectedItemProperty();
		testables.itemsProperty().bind(Bindings.select(selection, "Testables"));
	}
	
	@FXML
	private void addUnit(ActionEvent ae) 
	{
		String newunit = Helper.ask("Unit anlegen", "Name der Unit:");
		v.newUnit(newunit);
	}
	
	@FXML
	private void addWord(ActionEvent ae) 
	{
		String local = Helper.ask("Vokabel anlegen", "Wort auf deutsch?");
		String foreign = Helper.ask("Vokabel anlegen", "Wort auf "+ v.getName()+ "?");
		
		if(local != null && foreign != null) {
			addToCurrentUnit(new Word(local, foreign));
		}
	}
	
	private void addToCurrentUnit(Testable lg)
	{
		Unit current = units.getSelectionModel().getSelectedItem();
		if(current != null) {
			current.addTestable(lg);
		}
		else {
			v.addTestable(lg);
		}
	}
	
	@FXML
	private void addSynonym(ActionEvent ae) 
	{
		String wordString = Helper.ask("Synonym anlegen", "Tragen Sie die Synonyme \nkommagetrennt ein:");
		List<String> words = Helper.split(wordString);
		if(words != null && words.size() > 2) {
			addToCurrentUnit(new SynonymGroup(words));
		}
	}
	
	@FXML
	private void removeUnit(ActionEvent ae) 
	{
		Unit current = units.getSelectionModel().getSelectedItem();
		if(current != null)
		{
			v.getUnits().remove(current);
		}
	}
	
	@FXML
	private void removeTestable(ActionEvent ae) 
	{
		Testable currentt = testables.getSelectionModel().getSelectedItem();
		Unit currentu = units.getSelectionModel().getSelectedItem();
		if(currentt != null)
		{
			currentu.removeTestable(currentt);
		}
	}
	
	@FXML
	private void addAntonym() {
		String sunnySide = Helper.ask("Antonym anlegen", "Wort:");
		String darkSide = Helper.ask("Antonym anlegen", "Gegenteil des Wortes");
		
		if(sunnySide != null && darkSide != null) {
			addToCurrentUnit(new Antonym(sunnySide, darkSide));
		}
	}
	
	@FXML
	private void editTestable(ActionEvent ae) 
	{
		Testable current = testables.getSelectionModel().getSelectedItem();
		if(current instanceof Word) {
			String local = Helper.ask("Vokabel ändern", "Wort auf deutsch?");
			String foreign = Helper.ask("Vokabel ändern", "Wort auf "+ v.getName()+ "?");
			
			if(local != null && foreign != null) {
				((Word) current).setForeign(foreign);
				((Word) current).setLocal(local);
				((Word) current).invalidate();
			}
		}
		else if(current instanceof SynonymGroup) {
			String wordString = Helper.ask("Synonym ändern", "Tragen Sie die Synonyme \nkommagetrennt ein:");
			List<String> words = Helper.split(wordString);
			((SynonymGroup) current).setWords(words);
			((SynonymGroup) current).invalidate();
		}
		else if(current instanceof Antonym) {
			String sunnySide = Helper.ask("Antonym ändern", "Wort:");
			String darkSide = Helper.ask("Antonym ändern", "Gegenteil des Wortes:");
			
			if(sunnySide != null && darkSide != null) {
				((Antonym) current).setSunnySide(sunnySide);
				((Antonym) current).setDarkSide(darkSide);
				((Antonym) current).invalidate();
			}
		}
	}
	
	@FXML
	private void alleAnzeigen() {
		if(v.getUnits().size() > 0)
		{

			if(indexGesamtListen != 0) {
				v.getUnits().remove(indexGesamtListen);
			}
			v.newUnit("Gesamtliste");
			indexGesamtListen = v.getUnits().size()-1; 
			for(int i=0; i< v.getUnits().size()-1; i++) {
				for(int j=0; j < (v.getUnits().get(i).getTestables().size());j++) {
				v.getUnits().get(v.getUnits().size()-1).addTestable(v.getUnits().get(i).getTestables().get(j));
				}
			}
		}
		else {
			System.out.println("Keine Units vorhanden");
		}
	}
	
	@FXML
	private void test(MouseEvent me) 
	{
		if(me.getClickCount() == 2)
		{
			Testable t = testables.getSelectionModel().getSelectedItem();
			if(t != null) {
				Helper.test(t.createQuestion());
			}
		}
	}
}
