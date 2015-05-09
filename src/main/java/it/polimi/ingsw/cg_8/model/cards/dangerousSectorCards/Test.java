package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

public class Test {

	 public static void main (String[] args) {
		 
		 NoiseCard c1 = new NormalNoise();
		 System.out.println(c1.getDescription());
		 
		 NoiseCard c2 = new FakeNoise(new NormalNoise());
		 System.out.println(c2.getDescription());
		 
		 NoiseCard c3 = new NoiseWithItem(new NormalNoise());
		 System.out.println(c3.getDescription());
		 
		 NoiseCard c4 = new NoiseWithItem(new FakeNoise(new NormalNoise()));  // It does work
		 System.out.println(c4.getDescription());
		 
		 NoiseCard c5 = new FakeNoise(new NoiseWithItem(new NormalNoise()));  // Doesn't work!
		 System.out.println(c5.getDescription());
	 }
}
