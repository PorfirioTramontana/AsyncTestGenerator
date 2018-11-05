package generator;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Config conf=null;
		String c="";		
		if (args.length==4) 
			conf=new Config(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]));
		else if (args.length==1) {
			if (args[0].equalsIgnoreCase("UnoProcidaresidenteRandom"))
				conf=new Config("UnoProcidaresidenteRandom","RANDOM",Integer.parseInt(args[2]),Integer.parseInt(args[3]));
			else if (args[0].equalsIgnoreCase("UnoProcidaresidenteRandom1010"))
				conf=new Config("UnoProcidaresidenteRandom","RANDOM",10,10);
		}			
		else System.exit(1);

		//configurazione strumento	
		if (conf.valid()) {
			Generator gen=new Generator(conf);				
			gen.generate();
		}
		else
			System.out.println("Unknown configuration");
	}

}
