package generator;

import java.util.ArrayList;

public class Event {
	private String name;
	private String task="";
	private String testStatement;
	//ArrayList<Constraint> listaVincoli;
	
	Event(String n, String t, String ts){
		name=n;
		task=t;
		testStatement=t;
	}
	
	boolean equals(Event e) {
		if (!(e.getName().equalsIgnoreCase(getName())))
			return false;
		if(!(e.task.equalsIgnoreCase(task)))
			return false;		
		return true;
	}

	String getTask() {
		return task;
	}

	void setTask(String task) {
		this.task = task;
	}

	String getTestStatement() {
		return testStatement;
	}

	void setTestStatement(String testStatement) {
		this.testStatement = testStatement;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
	
}
