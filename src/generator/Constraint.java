package generator;

import java.util.ArrayList;

public class Constraint {
	String type;
	Event event1; 
	Event event2;
	Event event3;
	
	public Constraint(String t,Event e1) {
		type=t;
		event1=e1;
		event2=null;
		event3=null;
	}
	
	public Constraint(String t,Event e1, Event e2) {
		type=t;
		event1=e1;
		event2=e2;
		event3=null;
	}
	
	public Constraint(String t,Event e1, Event e2, Event e3) {
		type=t;
		event1=e1;
		event2=e2;
		event3=e3;
	}
	
	
	
}
