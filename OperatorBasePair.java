package geneticBasis.dna;

import java.util.Calendar;
import java.util.Random;

//simple expression of a basepair paired with an operator for the reader

public class OperatorBasePair {
	private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis());
	private BasePair base;
	private Operator oper;
	private int[] inputs;
	
	public BasePair getBase(){
		return base;
	}
	
	public Operator getOper(){
		return oper;
	}
	
	public int[] getInputs(){
		return inputs;
	}
	
	
	OperatorBasePair(BasePair pair){
		base = pair;
		oper = Operator.values()[rand.nextInt(Operator.values().length)];
		inputs = new int[oper.getNumInputs()];
		for(int i = 1;i < inputs.length;i++){
			inputs[i] = rand.nextInt(100)+1;
		}
	}
	
	OperatorBasePair(OperatorBasePair copy){
		base = copy.getBase();
		oper = copy.getOper();
		inputs = copy.getInputs();
	}
	
	public int run(int value){
		inputs[0] = value;
		return oper.run(inputs);
	}
}
