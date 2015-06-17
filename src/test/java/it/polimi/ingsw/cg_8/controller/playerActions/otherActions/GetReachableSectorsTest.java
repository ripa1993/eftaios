package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.controller.playeraction.other.GetReachableSectors;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GetReachableSectorsTest {
    Model model;

    @Before
    public void init() throws NotAValidMapException,
            GameAlreadyRunningException, EmptyDeckException {
        model = new Model(GameMapName.FERMI);
        model.addPlayer("a");
        model.addPlayer("b");
        model.initGame();
    }

    @Test
    public void test() {
        Player player = model.getCurrentPlayerReference();
        Set<Coordinate> output = GetReachableSectors.getReachableSectors(model,
                player);
        Set<Coordinate> reachableAlien = new HashSet<Coordinate>();
        reachableAlien.add(new Coordinate(11, 7));
        reachableAlien.add(new Coordinate(11, 6));
        reachableAlien.add(new Coordinate(10, 7));
        reachableAlien.add(new Coordinate(12, 7));
        Set<Coordinate> reachableHuman = new HashSet<Coordinate>();
        reachableHuman.add(new Coordinate(10, 10));
        reachableHuman.add(new Coordinate(11, 10));
        reachableHuman.add(new Coordinate(12, 10));
        if (player.getCharacter() instanceof Alien) {
            assertEquals(reachableAlien, output);
        } else {
            assertEquals(reachableHuman, output);
        }

    }

}
