package generator;

import java.util.ArrayList;

public class TestAppParameter {
	String name;
	ArrayList<Event> events=new ArrayList<Event>();
	ArrayList<Constraint> constraints=new ArrayList<Constraint>();
	
	TestAppParameter(String s){
		name=s;
	}
	
	Event getEvent (String name,String task) {
		for (Event e:events)
			if (e.getName().equalsIgnoreCase(name) && e.getTask().equalsIgnoreCase(task))
				return e;
		return null;
	}
	
}
