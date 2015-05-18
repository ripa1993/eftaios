package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;
/**
 * Player has to make a fake noise, decoration
 * @author Simone
 *
 */
public class FakeNoise extends NoiseDecorator {
	/**
	 * Constructor
	 * @param decoratedNoise
	 */
	public FakeNoise(NoiseCard  decoratedNoise) {
		super(decoratedNoise);
	}
	
	@Override
	public boolean hasToMakeFakeNoise() {
		return true;
	}
	
	@Override
	public String toString(){
		if(decoratedNoise.hasToDrawItem()){
			return "Fake noise with item";
		}
		return "Fake noise with no item";
	}
}
