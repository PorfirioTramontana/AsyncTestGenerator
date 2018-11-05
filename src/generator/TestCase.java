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
		//verifica se l'evento e può essere aggiunto al test case
		//dato per scontato che e fa parte dell'elenco di eventi possibili definito in TestAppParameter stesso
		
		// per ogni Constraint c di tap
			for (Constraint c:tap.constraints) {
				// se il vincolo riguarda il mio evento
				if (c.type.equalsIgnoreCase("IdemPot"))
					if (c.event1.equals(e))
						if (count(c.event1)>0)
							return false;
				if (c.type.equalsIgnoreCase("Balance")) {
					if (c.event2.equals(e)) 
						//verifico che l'evento event1 sia occorso esattamente una volta piu' di event2 in questo testCase
						if (count(c.event1)-count(c.event2)!=1)
							return false;
					if (c.event1.equals(e))
						//verifico che l'evento1 non si ripeta due volte consecutive senza un evento2
						if (count(c.event1)-count(c.event2)==1)
							return false;
				}
				if (c.type.equalsIgnoreCase("Precond"))
					if (c.event2.equals(e)) 
						//verifico che l'evento eventBefore sia occorso meno volte di eventAfter in questo testCase
						if (count(c.event1)==0)
							return false;	
				if (c.type.equalsIgnoreCase("Inclusion"))
					if(c.event1.equals(e))
						//verifico che l'evento event1 non sia all'interno di un ciclo event2-event3
						if (count(c.event2)!=count(c.event3))
							return false;
			}
		return true;
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