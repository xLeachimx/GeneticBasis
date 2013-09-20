package geneticBasis.dna;

import java.util.Calendar;
import java.util.Random;


public class DNAReader {
	private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis());
	private int start;
	private OperatorBasePair[] opers;
	
	public int getStart(){
		return start;
	}
	
	public OperatorBasePair[] getOpers(){
		return opers;
	}
	
	public DNAReader(){
		start = rand.nextInt(100)+1;
		opers = new OperatorBasePair[BasePair.values().length];
		for(int i = 0;i < BasePair.values().length;i++){
			opers[i] = new OperatorBasePair(BasePair.values()[i]);
		}
	}
	
	public DNAReader(DNAReader copy){
		start = copy.getStart();
		opers = copy.getOpers();
	}
	
	//reads a full DNA strand and reports back the number that result
	public int[] read(DNA info){
		int[] results = new int[info.getStrandSize()];
		for(int i = 0;i < info.getStrandSize();i++){
			int temp = start;
			for(int a = 0;a < info.getGene(i).getSize();a++){
				temp = opers[info.getGene(i).getBasePairs()[a].getValue()].run(temp);
			}
			results[i] = temp;
		}
		return results;
	}
}
