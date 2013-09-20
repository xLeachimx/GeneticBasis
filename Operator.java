package geneticBasis.dna;

//a simple little way to represent operators
//note: this can be extended to serve a greater number of operators

public enum Operator {
	Add(0,2),
	Subtract(1,2),
	Mtultiply(2,2),
	Divide(3,2);
	
	final int value;
	final int numIn;
	final int numValues;
	Operator(int num,int inputs){
		value = num;
		numIn = inputs;
		numValues = 4;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getNumValues(){
		return numValues;
	}
	
	public int getNumInputs(){
		return numIn;
	}
	
	public int run(int[] inputs){
		if(inputs.length != numIn)return 0;
		switch(value){
		case 0:
			return inputs[0]+inputs[1];
		case 1:
			return inputs[0]-inputs[1];
		case 2:
			return inputs[0]*inputs[1];
		case 3:
			return (int)(inputs[0]/inputs[1]);
		default:
			break;
		}
		return 0;
	}

}
