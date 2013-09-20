package geneticBasis.dna;

import java.util.Calendar;
import java.util.Random;

public class Gene {
	private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis());
	private BasePair[] storage;
	private int size;
	private boolean mutatable;
	
	//accessors
	public int getSize(){
		return size;
	}
	
	public BasePair[] getBasePairs(){
		return storage;
	}
	
	public boolean isMutatable(){
		return mutatable;
	}
	
	public boolean equalTo(Gene compare){
		if(size != compare.getSize())return false;
		for(int i = 0;i < size;i++){
			if(compare.getBasePairs()[i].getValue() != storage[i].getValue()){
				return false;
			}
		}
		return true;
	}
	
	//Mutation utilities
	
	//inserts a sequence at the designated area
	public void insertPairs(Gene pairSequence,int where){
		int tempSize = size;
		BasePair[] tempStore = new BasePair[size+pairSequence.getSize()];
		int a = 0;
		for(int i = 0;i < tempSize;i++){
			if(i == where){
				for(a = 0;a < pairSequence.getSize();a++){
					tempStore[i+a] = pairSequence.getBasePairs()[a];
				}
			}
			else{
				tempStore[i+a] = storage[i];
			}
		}
		storage = new BasePair[tempStore.length];
		for(int i = 0;i < storage.length;i++){
			storage[i] = tempStore[i];
		}
	}
	
	//causes a flip mutation in specified gene
	public void switchBasePair(int where){
		if(storage[where] == BasePair.AT){
			storage[where] = BasePair.TA;
		}
		else if(storage[where] == BasePair.TA){
			storage[where] = BasePair.AT;
		}
		else if(storage[where] == BasePair.CG){
			storage[where] = BasePair.GC;
		}
		else if(storage[where] == BasePair.GC){
			storage[where] = BasePair.CG;
		}
	}
	
	public Gene transfer(Gene from){
		storage = new BasePair[from.getSize()];
		size = from.getSize();
		for(int i = 0;i < size;i++){
			storage[i] = from.getBasePairs()[i];
		}
		return this;
	}
	
	public void mutateGene(int InsertPer,int SwitchPer){
		if(mutatable == false)return;
		for(int i = 0;i < size;i++){
			int chance = rand.nextInt(100)+1;
			if(chance <= InsertPer){
				insertPairs(new Gene(3),rand.nextInt(size));
			}
			chance = rand.nextInt(100)+1;
			if(chance <= SwitchPer){
				switchBasePair(rand.nextInt(size));
			}
		}
	}
	
	//sets whether or not the gene can be mutated
	public void setMutatable(boolean m){
		mutatable = m;
	}
	
	//constructors
	public Gene(){
		storage = new BasePair[1];
		size = 0;
		mutatable = true;
	}
	
	public Gene(int numPairs){
		generate(numPairs);
		mutatable = true;
	}
	
	public Gene(BasePair[] l){
		size = l.length;
		storage = new BasePair[size];
		for(int i = 0;i < size;i++){
			storage[i] = l[i];
		}
		mutatable = true;
	}
	
	public Gene(Gene copy){
		transfer(copy);
		mutatable = copy.isMutatable();
	}
	
	//utility functions
	private void generate(int num){
		if(size != num){
			storage = new BasePair[num];
			size = num;
		}
		for(int i = 0;i < size;i++){
			storage[i] = randomPair();
		}
	}
	
	private BasePair randomPair(){
		int choice = rand.nextInt(4)+1;
		switch(choice){
			case 1:
				return BasePair.TA;
			case 2:
				return BasePair.AT;
			case 3:
				return BasePair.CG;
			case 4:
				return BasePair.GC;
			default:
				return BasePair.BLANK;
		}
	}
}
