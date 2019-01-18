package generator;

import java.util.ArrayList;

public class Config {
	String inputPath;
	String outputPath;
	String fileName;
	String app;
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
				
		//setta parametri di strategy
		setS(new RandomStrategy());
		getS().setNumTest(parseInt);
		getS().setLength(parseInt2);
		
		outputPath=".\\";
		fileName=getS().getName()+"Test";
		
		setConfig();

	}

	
	public boolean setConfig() {
		
			//parametri dipendenti da app
			setTap(new TestAppParameter("UnoProcidaResidenteAsync"));
			getTap().events.add(new Event("EnableStartTask","DownloadMezziTask","GeneralEvent.enableStart(DownloadMezziTask.sem);"));
			getTap().events.add(new Event("EnableFinishTask","DownloadMezziTask","GeneralEvent.enableFinish(DownloadMezziTask.sem);"));
			getTap().events.add(new Event("EnableStartTask","LeggiMeteoTask","GeneralEvent.enableStart(LeggiMeteoTask.sem);"));
			getTap().events.add(new Event("EnableFinishTask","LeggiMeteoTask","GeneralEvent.enableFinish(LeggiMeteoTask.sem);"));
			getTap().events.add(new Event("StartApp","","GeneralEvent.startApp(\"com.porfirio.orariprocida2011\");"));
			getTap().events.add(new Event("PauseApp","","GeneralEvent.pause();"));
			getTap().events.add(new Event("ResumeApp","","GeneralEvent.resume();"));
			getTap().events.add(new Event("StopApp","","GeneralEvent.stop();"));
			getTap().events.add(new Event("RestartApp","","GeneralEvent.restart();"));			
			getTap().events.add(new Event("ExecuteUserEvent","AggiornaOrariDaWeb","SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);"));
			getTap().events.add(new Event("ExecuteUserEvent","LeggiMeteo","SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);"));

			//constraints generali
			//Constraint di bilanciamento: il secondo può avvenire solo se il primo è avvenuto esattamente una volta di più 
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("EnableStartTask","DownloadMezziTask"),getTap().getEvent("EnableFinishTask","DownloadMezziTask")));
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("EnableStartTask","LeggiMeteoTask"),getTap().getEvent("EnableFinishTask","LeggiMeteoTask")));
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("PauseApp",""),getTap().getEvent("ResumeApp","")));
			getTap().constraints.add(new Constraint("Balance",getTap().getEvent("StopApp",""),getTap().getEvent("RestartApp","")));
			
			//Constraint di precondizione: il secondo evento può avvenire solo se il primo e' avvenuto almeno una volta
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("ExecuteUserEvent","AggiornaOrariDaWeb")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("ExecuteUserEvent","LeggiMeteo")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("PauseApp","")));
			getTap().constraints.add(new Constraint("Precond",getTap().getEvent("StartApp",""),getTap().getEvent("StopApp","")));
			//pleonastici gli altri: il vincolo di bilancio stabilisce che sono successivi ad eventi aventi StartApp come precedente
			
			//Constraint di idempotenza: l'evento può avvenire una sola volta per testcase
			getTap().constraints.add(new Constraint("IdemPot",getTap().getEvent("StartApp","")));
			
			//Constraint di inclusione: l'evento non puo'avvenire in mezzo agli altri due eventi specificati
			//un evento utente non può avvenire con app in pausa o stopped
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","AggiornaOrariDaWeb"),getTap().getEvent("PauseApp",""),getTap().getEvent("ResumeApp","")));
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","LeggiMeteo"),getTap().getEvent("PauseApp",""),getTap().getEvent("ResumeApp","")));
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","AggiornaOrariDaWeb"),getTap().getEvent("StopApp",""),getTap().getEvent("RestartApp","")));
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("ExecuteUserEvent","LeggiMeteo"),getTap().getEvent("StopApp",""),getTap().getEvent("RestartApp","")));
			//Non si può stoppare un app mentre è in pausa (non si può ruotare se non è sullo schermo)
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("StopApp","LeggiMeteo"),getTap().getEvent("PauseApp",""),getTap().getEvent("ResumeApp","")));
			//Non si può mettere in pausa una app stopped (è già passata per la pausa)
			getTap().constraints.add(new Constraint("Inclusion",getTap().getEvent("PauseApp","LeggiMeteo"),getTap().getEvent("StopApp",""),getTap().getEvent("RestartApp","")));			
			//pleonastico impedire StartApp dopo pause o stop
			
			
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
