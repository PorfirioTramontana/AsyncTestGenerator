package generator;

import java.util.ArrayList;

public class Constraint {
	Event eventBefore; //evento che deve verificarsi prima
	Event eventAfter;
	
	public Constraint(Event e1, Event e2) {
		eventBefore=e1;
		eventAfter=e2;
	}
	
}
