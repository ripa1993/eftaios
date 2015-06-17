package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.playeraction.Attack;
import it.polimi.ingsw.cg_8.controller.playeraction.AttackValidator;
import it.polimi.ingsw.cg_8.controller.playeraction.EndTurn;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.item.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.item.DefenseCard;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class AttackTest {
    Model model;
    Player currentPlayer;

    @Before
    public void init() throws NotAValidMapException,
            GameAlreadyRunningException, EmptyDeckException {
        model = new Model(GameMapName.FERMI);
        model.addPlayer("a");
        model.addPlayer("b");
        model.initGame();
        System.out.println(model.getCurrentPlayerReference());

        currentPlayer = model.getCurrentPlayerReference();

        if (currentPlayer.getCharacter() instanceof Alien) {
            EndTurn.endTurn(model);
            currentPlayer = model.getCurrentPlayerReference();
        }

        model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

        EndTurn.endTurn(model);

        model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

    }

    @Test
    public void validateAttackTest() {
        assertTrue(AttackValidator.validateAttack(model));
    }

    @Test
    public void attackSuccessfulTest() {
        Attack attack = new Attack(model);

        attack.makeAttack();

        assertTrue((attack.getVictims().get(0)).getCharacter() instanceof Human);

    }

    @Test
    public void attackSuccessfulHumanTest() {
        EndTurn.endTurn(model);
        model.getCurrentPlayerReference().getHand()
                .addItemCard(new AttackCard());
        Attack attack = new Attack(model);
        attack.makeAttack();
        assertTrue((attack.getVictims().get(0)).getCharacter() instanceof Alien);
    }

    @Test
    public void attackUnsuccessfulHumanDefendTest() {
        EndTurn.endTurn(model);
        model.getCurrentPlayerReference().getHand()
                .addItemCard(new DefenseCard());
        EndTurn.endTurn(model);
        Attack attack = new Attack(model);

        attack.makeAttack();

        assertTrue((attack.getSurvivor().get(0)).getCharacter() instanceof Human);

    }

}
