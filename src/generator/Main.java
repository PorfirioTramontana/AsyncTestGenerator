package generator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config conf=new Config(0);
		
		Generator gen=new Generator(conf);
		Strategy s=new Strategy();
		//setta parametri di strategy
		gen.generate(s);
		
	}

}
