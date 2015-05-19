package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.noises.SpotlightNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class UseSpotlightCard extends UseItemCard {

	private Set<Coordinate> spotlightTarget;
	private Set<Player> foundPlayers;
	private Model model;
	private Player player;

	public UseSpotlightCard(Model model) {
		this.spotlightTarget = new HashSet<Coordinate>();
		this.foundPlayers = new HashSet<Player>();
		this.model = model;
		this.player = model.getPlayers().get(model.getCurrentPlayer());
	}

	@Override
	public void useCard(Card card) {
		// TODO rimuove carta

		
	}

	/**
	 *
	 * Add to a list the players found by the spotlight, which illuminates a
	 * coordinate and its adjacent coordinates
	 * 
	 * @return foundPlayers a list of players found by the spotlight
	 */
	private void turnOnLights(Coordinate target) {
		spotlightTarget.add(target);
		spotlightTarget.addAll(model.getMap()
				.getReachableCoordinates(target, 1));

		for (Player p : model.getPlayers()) {
			if (spotlightTarget.contains(p.getLastPosition())) {
				foundPlayers.add(p);
				Noise spotlightNoise = new SpotlightNoise(
						model.getRoundNumber(), player,
						player.getLastPosition());
				// TODO: add spotlightNoise to noiseList
			}
		}
	}

	public Set<Coordinate> getSpotlightTarget(Coordinate target) {

		return spotlightTarget;
	}

	public Set<Player> getFoundPlayers() {
		return foundPlayers;
	}
}
