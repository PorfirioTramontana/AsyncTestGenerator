package generator;

import java.util.ArrayList;

public class Config {
	String inputPath;
	String outputPath;
 
	public Config(int i) {
		if (i==0) {
			//Configurazione di default
			inputPath="C:\\Users\\Porfirio\\Documents\\AsyncTestGenerator\\input";
			outputPath="C:\\Users\\Porfirio\\Documents\\AsyncTestGenerator\\output";
		}
			
	}

}
