package it.polimi.ingsw.cg_8.model.cards.characterCards;

/**
 * Human character card
 * 
 * @author Simone
 *
 */
public class HumanCard extends CharacterCard {
	/**
	 * Constructor
	 * 
	 * @param name
	 *            human name
	 * @param nickname
	 *            human nickname
	 * @param rank
	 *            rank name
	 */
	public HumanCard(String name, String nickname, String rank) {
		super(name, nickname, rank);
	}

	@Override
	public String toString() {
		return "HumanCard [getName()=" + getName() + ", getNickname()="
				+ getNickname() + ", getRank()=" + getRank() + "]";
	}
}
