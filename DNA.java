package geneticBasis.dna;

import java.util.Calendar;
import java.util.Random;

public class DNA {
	private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis());
	private Gene[] strand;
	
	//accessors
	
	public Gene getGene(int index){
		if(index >= 0 && index < strand.length){
			return strand[index];
		}
		return new Gene();
	}
	
	public Gene[] getGenes(){
		return strand;
	}
	
	public int getStrandSize(){
		return strand.length;
	}
	
	public boolean equalTo(DNA compare){
		if(compare.getStrandSize()!=strand.length){
			return false;
		}
		for(int i = 0;i < strand.length;i++){
			if(!(compare.getGenes()[i].equalTo(strand[i]))){
				return false;
			}
		}
		return true;
	}
	//mutators
	
	public void transfer(DNA copy){
		strand = new Gene[copy.getStrandSize()];
		for(int i = 0;i < copy.getStrandSize();i++){
			strand[i] = new Gene(copy.getGene(i));
		}
	}
	
	public void addGene(Gene add){
		Gene[] temp = new Gene[strand.length+1];
		for(int i = 0;i < strand.length;i++){
			temp[i] = new Gene(strand[i]);
		}
		temp[strand.length] = new Gene(add);
		strand = new Gene[temp.length];
		for(int i = 0;i < temp.length;i++){
			strand[i] = new Gene(temp[i]);
		}
	}
	
	public void removeGene(int index){
		if(index < 0 || index >= strand.length)return;
		Gene[] temp = new Gene[strand.length-1];
		int c = 0;
		for(int i = 0;i < strand.length;i++){
			if(i!=index){
				temp[c] = new Gene(strand[i]);
				c++;
			}
		}
		strand = new Gene[temp.length];
		for(int i = 0;i < temp.length;i++){
			strand[i] = new Gene(temp[i]);
		}
	}
	
	public void lockDNA(){
		for(int i = 0;i < strand.length;i++){
			strand[i].setMutatable(false);
		}
	}
	
	public void unlockDNA(){
		for(int i = 0;i < strand.length;i++){
			strand[i].setMutatable(true);
		}
	}
	
	//mutation
	public void mutate(int InsertPer,int SwitchPer){
		for(int i = 0;i < strand.length;i++){
			strand[i].mutateGene(InsertPer, SwitchPer);
		}
	}
	
	//constructors
	public DNA(){
		strand = new Gene[1];
		strand[0] = new Gene();
	}
	
	public DNA(int numGenes,int geneSize){
		strand = new Gene[numGenes];
		for(int i = 0;i < numGenes;i++){
			strand[i] = new Gene(geneSize);
		}
	}
	
	public DNA(Gene[] copy){
		strand = new Gene[copy.length];
		for(int i = 0;i < copy.length;i++){
			strand[i] = new Gene(copy[i]);
		}
	}
	
	public DNA(DNA copy){
		transfer(copy);
	}
	
	//mating utilities
	
	/*mates two strands together
	 * Slight advantage given to one over two
	 * Larger strand is considered dominant and so size of returned strand is that of the larger arguement
	 */
	public static DNA mateStrand(DNA one,DNA two){
		if(one.getStrandSize() <= 0 && two.getStrandSize() <= 0)return new DNA();
		int numGenes = 1;
		if(one.getStrandSize() >= two.getStrandSize()){
			numGenes = one.getStrandSize();
		}
		else{
			numGenes = two.getStrandSize();
		}
		Gene[] combination = new Gene[numGenes];
		for(int i = 0;i < numGenes;i++){
			if(i >= one.getStrandSize()){
				combination[i] = new Gene(two.getGene(i));
			}
			else if(i >= two.getStrandSize()){
				combination[i] = new Gene(one.getGene(i));
			}
			else{
				int choice = rand.nextInt(100)+1;
				if(choice >= 50){
					combination[i] = new Gene(one.getGene(i));
				}
				else{
					combination[i] = new Gene(two.getGene(i));
				}
			}
		}
		DNA result = new DNA(combination);
		return result;
	}
}
