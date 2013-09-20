import java.util.ArrayList;

import geneticBasis.dna.TestSuite;


public class Main {

	public static void main(String[] args) {
		TestSuite ts = new TestSuite();
		boolean perfect = true;
		ArrayList<Integer> bad = new ArrayList<Integer>();
		for(int i = 0;i < 100;i++){
			System.out.println(i);
			if(ts.test() == false){
				perfect = false;
				bad.add(i);
			}
		}
		System.out.println(perfect);
		for(int i = 0;i < bad.size();i++){
			System.out.println(bad.get(i));
		}

	}

}
