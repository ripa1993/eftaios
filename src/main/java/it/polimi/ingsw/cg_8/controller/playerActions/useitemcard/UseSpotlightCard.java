package it.polimi.ingsw.cg_8.controller.playerActions.useitemcard;

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
 * @version 1.0
 */
public class UseSpotlightCard extends UseItemCard {

    /**
     * Adds to {@link #spotlightTarget} the starting coordinate ({@link #target}
     * ) and the surrounding six
     */
    private static Set<Coordinate> findSpotlightTarget(Model model,
            Coordinate target) {
        Set<Coordinate> spotlightTarget = new HashSet<Coordinate>();
        spotlightTarget.add(target);
        spotlightTarget.addAll(model.getMap().getConnectedCoordinates(target));
        return spotlightTarget;
    }

    /**
     * Adds the players that are in {@link #spotlightTarget} in
     * {@link #foundPlayers}
     */
    private static Set<Player> findPlayers(Model model, Set<Coordinate> target) {
        Set<Player> foundPlayers = new HashSet<Player>();
        for (Player p : model.getPlayers()) {
            if (!p.getState().equals(PlayerState.DEAD)
                    && target.contains(p.getLastPosition())) {

                foundPlayers.add(p);

            }
        }
        return foundPlayers;
    }

    /**
     * For every player in {@link #foundPlayers} generates a
     * {@link SpotlightNoise} and adds it to the noise logger in model
     */
    private static void makeNoise(Model model, Set<Player> foundPlayers) {
        Iterator<Player> it = foundPlayers.iterator();
        while (it.hasNext()) {
            Player currentPlayer = it.next();
            Noise spotlightNoise = new SpotlightNoise(model.getRoundNumber(),
                    currentPlayer, currentPlayer.getLastPosition());
            model.addNoise(spotlightNoise);
        }
    }

    /**
     * Spots players in the targeted location
     */
    public static Set<Player> useCard(Model model, Coordinate coordinate) {
        Set<Coordinate> target = findSpotlightTarget(model, coordinate);
        Set<Player> foundPlayers = findPlayers(model, target);
        makeNoise(model, foundPlayers);

        // might be needed in future
        return foundPlayers;

    }
}
