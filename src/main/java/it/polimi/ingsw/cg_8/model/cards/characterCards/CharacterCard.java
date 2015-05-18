package it.polimi.ingsw.cg_8.model.cards.characterCards;

import it.polimi.ingsw.cg_8.model.cards.Card;

/**
 * Abstract character card, it is extended by {@link AlienCard} and
 * {@link HumanCard}
 * 
 * @author Simone
 *
 */
public abstract class CharacterCard extends Card {
	/**
	 * Character name
	 */
	private final String name;
	/**
	 * Character nickname
	 */
	private final String nickname;
	/**
	 * Character rank
	 */
	private final String rank;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            character name
	 * @param nickname
	 *            character nickname
	 * @param rank
	 *            character rank
	 */
	public CharacterCard(String name, String nickname, String rank) {
		this.name = name;
		this.nickname = nickname;
		this.rank = rank;
	}

	/**
	 * Getter
	 * 
	 * @return character name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter
	 * 
	 * @return character nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Getter
	 * 
	 * @return character rank
	 */
	public String getRank() {
		return rank;
	}

	@Override
	public String toString() {
		if (this.nickname != null) {
			return "Character: " + name + ", " + "\"" + nickname + "\""
					+ ", as " + rank + "\n";
		} else
			return "Character: " + name + ", as " + rank + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharacterCard other = (CharacterCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}

}
