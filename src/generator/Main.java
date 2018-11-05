package generator;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Config conf=new Config(0);
		
		//configurazione strumento
		Generator gen=new Generator(conf);
		//setta parametri di strategy
		GenericStrategy s=new RandomStrategy();
		s.setNumTest(10);
		s.setLength(10);
		
		//parametri dipendenti da app
		TestAppParameter tap=new TestAppParameter("UnoProcidaResidenteAsync");
		tap.events.add(new Event("EnableTask","DownloadMezziTask","GeneralEvent.start(DownloadMezziTask.sem);"));
		tap.events.add(new Event("FinishTask","DownloadMezziTask","GeneralEvent.finish(DownloadMezziTask.sem);"));
		tap.events.add(new Event("EnableTask","LeggiMeteoTask","GeneralEvent.start(LeggiMeteoTask.sem);"));
		tap.events.add(new Event("FinishTask","LeggiMeteoTask","GeneralEvent.finish(LeggiMeteoTask.sem);"));
		tap.events.add(new Event("StartApp","","GeneralEvent.startApp(\"com.porfirio.orariprocida2011\");"));
		tap.events.add(new Event("PauseApp","","GeneralEvent.pause();"));
		tap.events.add(new Event("ResumeApp","","GeneralEvent.resume();"));
		tap.events.add(new Event("ExecuteUserEvent","AggiornaOrariDaWeb","SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);"));
		tap.events.add(new Event("ExecuteUserEvent","LeggiMeteo","SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);"));

		//constraints generali
		//tap.constraints.add(new Constraint().addEventBefore(e))
		
		gen.generate(s,tap);
	}

}
