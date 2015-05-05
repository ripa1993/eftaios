package it.polimi.ingsw.cg_8.model.cards.characterCards;

import it.polimi.ingsw.cg_8.model.cards.Card;

public abstract class CharacterCard extends Card {
	
	private final String name;
	private final String nickname;
	private final String rank;
	
	public CharacterCard(String name, String nickname, String rank) {
		this.name = name;
		this.nickname = nickname;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return "CharacterCard [name=" + name + ", nickname=" + nickname
				+ ", rank=" + rank + "]";
	}
	
}
