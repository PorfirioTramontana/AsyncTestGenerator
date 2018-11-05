package generator;

import java.util.ArrayList;

public class Config {
	String inputPath;
	String outputPath;
	String fileName;
	String app;
	String strategyType;
	private GenericStrategy s;
	private TestAppParameter tap;
	private boolean valid=false;
 
//	public Config(String c) {
//		if (c.equalsIgnoreCase("UnoProcidaresidenteRandom1010")) {
//			//Configurazione di default
//			//inputPath="C:\\Users\\Porfirio\\Documents\\AsyncTestGenerator\\input\\";
//			//outputPath="C:\\Users\\Porfirio\\Documents\\AsyncTestGenerator\\output\\";
//			outputPath=".\\";
//			fileName="randomTest.java";
//			
//			//setta parametri di strategy
//			setS(new RandomStrategy());
//			getS().setNumTest(10);
//			getS().setLength(10);
//		}
//			
//	}

	public Config(String string, String string2, int parseInt, int parseInt2) {
		outputPath=".\\";
		fileName="randomTest.java";
		
		//setta parametri di strategy
		setS(new RandomStrategy());
		getS().setNumTest(parseInt);
		getS().setLength(parseInt2);
		
		setConfig();

	}

	
	public boolean setConfig() {
		
			//parametri dipendenti da app
			setTap(new TestAppParameter("UnoProcidaResidenteAsync"));
			getTap().events.add(new Event("EnableTask","DownloadMezziTask","GeneralEvent.start(DownloadMezziTask.sem);"));
			getTap().events.add(new Event("FinishTask","DownloadMezziTask","GeneralEvent.finish(DownloadMezziTask.sem);"));
			getTap().events.add(new Event("EnableTask","LeggiMeteoTask","GeneralEvent.start(LeggiMeteoTask.sem);"));
			getTap().events.add(new Event("FinishTask","LeggiMeteoTask","GeneralEvent.finish(LeggiMeteoTask.sem);"));
			getTap().events.add(new Event("StartApp","","GeneralEvent.startApp(\"com.porfirio.orariprocida2011\");"));
			getTap().events.add(new Event("PauseApp","","GeneralEvent.pause();"));
			getTap().events.add(new Event("ResumeApp","","GeneralEvent.resume();"));
			getTap().events.add(new Event("ExecuteUserEvent","AggiornaOrariDaWeb","SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);"));
			getTap().events.add(new Event("ExecuteUserEvent","LeggiMeteo","SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);"));

			//constraints generali
			//Constraint di bilanciamento: il secondo può avvenire solo se il primo è avvenuto esattamente una volta di più 
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("EnableTask","DownloadMezziTask"),getTap().getEvent("FinishTask","DownloadMezziTask")));
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("EnableTask","LeggiMeteoTask"),getTap().getEvent("FinishTask","LeggiMeteoTask")));
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("PauseApp",""),getTap().getEvent("ResumeApp","")));
			
			//Constraint di precondizione: il secondo evento può avvenire solo se il primo e' avvenuto almeno una volta
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("ExecuteUserEvent","AggiornaOrariDaWeb")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("ExecuteUserEvent","LeggiMeteo")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("FinishTask","DownloadMezziTask")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("FinishTask","LeggiMeteoTask")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("PauseApp","")));
			
			//Constraint di idempotenza: l'evento può avvenire una sola volta per testcase
			getTap().constraints.add(new Constraint("IdemPot",getTap().getEvent("StartApp","")));
			
			//Constraint di inclusione: l'evento non puo'avvenire in mezzo agli altri due eventi specificati
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","AggiornaOrariDaWeb"),getTap().getEvent("ResumeApp",""),getTap().getEvent("PauseApp","")));
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","LeggiMeteo"),getTap().getEvent("ResumeApp",""),getTap().getEvent("PauseApp","")));
			
			valid=true;
			return valid;

		
	}
	
	GenericStrategy getS() {
		return s;
	}

	void setS(GenericStrategy s) {
		this.s = s;
	}

	TestAppParameter getTap() {
		return tap;
	}

	void setTap(TestAppParameter tap) {
		this.tap = tap;
	}

	public boolean valid() {		
		return valid;
	}

}
