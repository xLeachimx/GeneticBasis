package geneticBasis.dna;

public enum BasePair{
	TA(1),
	AT(2),
	GC(3),
	CG(4),
	BLANK(5);
	private final int value;
	BasePair(int num){
		value = num;
	}
	
	public int getValue(){
		return value;
	}
}
