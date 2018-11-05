package generator;

public class RandomStrategy extends GenericStrategy {
	private int length;
	
	RandomStrategy(int l,int n){
		setName("RANDOM");
		setLength(l);
		setNumTest(n);
	}
	
	RandomStrategy(){
		setName("RANDOM");
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	void setLength(int length) {
		this.length = length;
	}

}
