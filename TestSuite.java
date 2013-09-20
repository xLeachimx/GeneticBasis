package geneticBasis.dna;


public class TestSuite {
	
	public boolean test(){
		boolean good = true;
		if(UniqueGenes()){
			System.out.println("Genes are unique");
		}
		else{
			System.out.println("Genes are not unique");
			good = false;
		}
		if(Reader()){
			System.out.println("Reader is in working order");
		}
		else{
			System.out.println("Reader is not in working order");
			good = false;
		}
		if(mating()){
			System.out.println("Mating system in order");
		}
		else{
			System.out.println("Problem with mating");
			good = false;
		}
		if(UniqueDNA()){
			System.out.println("DNA is unique");
		}
		else{
			System.out.println("DNA is not unique");
			good = false;
		}
		return good;
		
	}
	
	public static boolean UniqueGenes(){
		Gene one = new Gene(3);
		Gene two = new Gene(3);
		for(int i = 0;i < one.getSize();i++){
			if(one.getBasePairs()[i] != two.getBasePairs()[i]){
				return true;
			}
		}
		return false;
	}
	
	public static boolean Reader(){
		DNA test = new DNA(3,3);
		DNAReader read = new DNAReader();
		int[] output = read.read(test);
		if(output.length!=3){
			return false;
		}
		return true;
	}
	
	public static boolean mating(){
		DNA one = new DNA(3,3);
		DNA two = new DNA(3,3);
		DNA three = new DNA(DNA.mateStrand(one, two));
		if(!(one.equalTo(two))){
			if( (one.equalTo(three)) || (two.equalTo(three)))return false;
		}
		else{
			if(!(one.equalTo(three)))return false;
		}
		return true;
	}
	
	public static boolean UniqueDNA(){
		DNA one = new DNA(3,3);
		DNA two = new DNA(3,3);
		return !(one.equalTo(two));
	}
}
