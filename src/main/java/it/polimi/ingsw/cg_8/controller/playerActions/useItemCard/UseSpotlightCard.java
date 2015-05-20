package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.noises.SpotlightNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Action that uses a spotlight card. It let's
 * 
 * @author Simone
 *
 */
public class UseSpotlightCard extends UseItemCard {
	/**
	 * Spotlight coordinates, including starting one
	 */
	private Set<Coordinate> spotlightTarget;
	/**
	 * List of players found in the target sectors
	 */
	private Set<Player> foundPlayers;
	/**
	 * Starting coordinate
	 */
	private Coordinate target;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            reference to the game
	 * @param coordinate
	 *            target coordinate
	 */
	public UseSpotlightCard(Model model, Coordinate coordinate) {
		super(model);
		this.target = coordinate;
		this.spotlightTarget = new HashSet<Coordinate>();
		this.foundPlayers = new HashSet<Player>();
	}

	/**
	 * Adds to {@link #spotlightTarget} the starting coordinate ({@link #target}
	 * ) and the sourrounding six
	 */
	private void findSpotlightTarget() {
		spotlightTarget.add(target);
		spotlightTarget.addAll(model.getMap()
				.getReachableCoordinates(target, 1));
	}

	/**
	 * Adds the players that are in {@link #spotlightTarget} in
	 * {@link #foundPlayers}
	 */
	private void findPlayers() {
		for (Player p : model.getPlayers()) {
			if (!p.getState().equals(PlayerState.DEAD)) {
				foundPlayers.add(p);
			}
		}
	}

	/**
	 * For every player in {@link #foundPlayers} generates a
	 * {@link SpotlightNoise} and adds it to the noise logger in model
	 */
	private void makeNoise() {
		Iterator<Player> it = foundPlayers.iterator();
		while (it.hasNext()) {
			Player currentPlayer = it.next();
			Noise spotlightNoise = new SpotlightNoise(model.getRoundNumber(),
					currentPlayer, currentPlayer.getLastPosition());
			model.getNoiseLogger().add(spotlightNoise);
		}
	}

	/**
	 * Getter for spotlight target
	 * 
	 * @return target coordinates
	 */
	public Set<Coordinate> getSpotlightTarget() {
		return spotlightTarget;
	}

	/**
	 * Getter for targeted players
	 * 
	 * @return targeted players
	 */
	public Set<Player> getFoundPlayers() {
		return foundPlayers;
	}

	@Override
	public void useCard() {
		findSpotlightTarget();
		findPlayers();
		makeNoise();

	}
}
