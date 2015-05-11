package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

public class NormalNoise extends DangerousSectorCard implements NoiseCard {

	@Override
	public boolean hasToMakeFakeNoise() {	
		return false;
	}

	@Override
	public boolean hasToDrawItem() {
		return false;
	}
}
