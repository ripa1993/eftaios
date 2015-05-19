package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class UseSpotlightCard extends UseItemCard {

	private Set<Coordinate> spotlightTarget;
	private Set<Player> foundPlayers;

	public UseSpotlightCard() {
		this.spotlightTarget = new HashSet<Coordinate>();
		this.foundPlayers = new HashSet<Player>();
	}

	@Override
	public void useCard(Card card) {
		// TODO rimuove carta

	}

	private void turnOnLights(Coordinate coordinate) {
		/*
		 * TODO:
		 * - ottieni i 7 settori bersaglio
		 * - ottieni i giocatori nei 7 settori
		 * - aggiungi alla lista dei rumori le persone trovate (spotlight noise)
		 * 
		 */
	}

	public Set<Coordinate> getSpotlightTarget() {
		return spotlightTarget;
	}

	public Set<Player> getFoundPlayers() {
		return foundPlayers;
	}
}
