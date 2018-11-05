package generator;

import java.util.ArrayList;

public class TestCase {
	private TestAppParameter tap;
	ArrayList<Event> eventSequence = new ArrayList<Event>();
	private int number;
	
	
	
	int getNumber() {
		return number;
	}
	void setNumber(int number) {
		this.number = number;
	}
	
	boolean isAllowed(Event e) {
		boolean ok=true;
		//verifica se l'evento e può essere aggiunto al test case
		//dato per scontato che e fa parte dell'elenco di eventi possibili definito in TestAppParameter stesso
		
		// per ogni Constraint c di tap
			for (Constraint c:tap.constraints) {
				// se il vincolo riguarda il mio evento
				if (c.eventAfter.equals(e)) {
					//verifico che l'evento eventBefore sia occorso meno volte di eventAfter in questo testCase
					if (count(c.eventBefore)<count(c.eventAfter))
						return false;
				}
			}
		return ok;
	}
	

	private int count(Event event) {
		//conta quanti eventi di quel tipo sono gia' nella sequenza di test
		int c=0;
		for (Event e:eventSequence)
			if (e.equals(event))
				c++;
		return c;
	}
	
	TestAppParameter getTap() {
		return tap;
	}
	void setTap(TestAppParameter tap) {
		this.tap = tap;
	}
}