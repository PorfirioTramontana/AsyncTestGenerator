package generator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {
	String path;
	String fileName;
	GenericStrategy s;
	TestAppParameter tap;


	public Generator(Config conf) {
		//Setta eventuali parametri di generazione
		path=conf.outputPath;
		fileName=conf.fileName;
		s=conf.getS();
		tap=conf.getTap();
	}

	public void generate() throws IOException {
		// Algoritmo di generazione		

		//Apri file nel quale generare i test
		FileWriter fileWriter = new FileWriter(path+fileName);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	
		//Scrivi il preambolo
		addPreamble(printWriter);
		//se la strategia e' random
			if (s.getName().contentEquals("RANDOM")) {			
				Random rnd=new Random();
			// cicla sul numero di test previsti
				int tn; //test number
				for (tn=0;tn<s.getNumTest();tn++) {
				//scrivi il preambolo del singolo test
				addTestPreamble(printWriter,tn,"RANDOM");
				
				// crea testcase
				TestCase tc=new TestCase();
				tc.setTap(tap);
				// cicla sulla lunghezza prevista per i test
					int en; //event number
					for (en=0;en<s.getLength();en++) {
					//genera un evento nell'elenco di quelli proposti, verificando i vincoli	
						boolean ok;
						int evento;
						do{
							evento=rnd.nextInt(tap.events.size());
							ok=tc.isAllowed(tap.events.get(evento));
						} while(!ok);
						//aggiungi l'evento alla sequenza
						tc.eventSequence.add(tap.events.get(evento));						
						//scrivi l'evento nel file
						printWriter.println(tap.events.get(evento).getTestStatement());
					}
					//scrivi la parte finale del test
					printWriter.println("}");					
				}

			}
			//scrivi la parte finale del file di test
			addTestEnding(printWriter);
		//chiudi il file
	    printWriter.close();

				
	}
	
	private void addTestEnding(PrintWriter printWriter) {
		printWriter.println("@After");
		printWriter.println("public void tearDown() {");
		printWriter.println("Log.d(\"TEST\", \"End test\");");
		printWriter.println("}");
		printWriter.println("}");
		return;
	}

	private void addTestPreamble(PrintWriter printWriter, int tn, String string) {
		printWriter.println("@Test");
		printWriter.println("public void "+string+tn+"Test() throws InterruptedException {");
		return;
	}

	public void addPreamble(PrintWriter printWriter) {
		printWriter.println("package com.porfirio.orariprocida2011.activities;");
		printWriter.println("import android.support.test.filters.SdkSuppress;");
		printWriter.println("import android.support.test.runner.AndroidJUnit4;");
		printWriter.println("import android.util.Log;");		
		printWriter.println("import org.junit.After;");
		printWriter.println("import org.junit.BeforeClass;");
		printWriter.println("import org.junit.Test;");
		printWriter.println("import org.junit.runner.RunWith;");
		addSpecificImport(printWriter);
		printWriter.println("@RunWith(AndroidJUnit4.class)");
		printWriter.println("@SdkSuppress(minSdkVersion = 18)");
		printWriter.println("public class AsyncTest {");
		printWriter.println("@BeforeClass");
		printWriter.println("public static void startup() {");
		printWriter.println("GeneralEvent.setTime(GeneralEvent.NORMAL);");
		printWriter.println("}");
		
		return;
	}

	private void addSpecificImport(PrintWriter printWriter) {
		printWriter.println("import com.porfirio.orariprocida2011.tasks.DownloadMezziTask;");
		printWriter.println("import com.porfirio.orariprocida2011.tasks.LeggiMeteoTask;");
		printWriter.println("import it.unina.ptramont.utilityTest.GeneralEvent;");
		printWriter.println("import it.unina.ptramont.utilityTest.SpecificUIEvent;");
		return;
	}

}
