package it.polimi.ingsw.cg_8.controller.playeraction;

import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseDefenseCard;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.item.DefenseCard;
import it.polimi.ingsw.cg_8.model.noises.AttackNoise;
import it.polimi.ingsw.cg_8.model.noises.DefenseNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Hand;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * When the input handler gets an action attack, the
 * {@link Rules#validateAttack()} function is called. The attack function
 * instantiate this Class and uses its method to validate the attack.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */

public class Attack implements PlayerAction {

    /**
     * Current state of the game
     */
    private Model model;
    /**
     * The player who attacks
     */
    private Player attacker;
    /**
     * List of players in the same sector as the attacker.
     */
    private List<Player> playersInSector;
    /**
     * List of players who are killed by the attack
     */
    private List<Player> victims;
    /**
     * List of players who used a defense card.
     */
    private List<Player> survivors;

    /**
     * Constructor of the class
     * 
     * @param attacker
     */
    public Attack(Model model) {
        this.model = model;
        this.attacker = model.getCurrentPlayerReference();
        victims = new ArrayList<Player>();
        playersInSector = new ArrayList<Player>();
        survivors = new ArrayList<Player>();
    }

    /**
     * Attack the players in the attacker' sector, unless they are defended; the
     * attacker makes a noise, and so do the attacked players. If the attacker
     * is an {@link Alien} and a {@link Human} is killed, the alien is upgraded.
     * 
     * @param model
     */
    public void makeAttack() {

        List<Player> attackedPlayers = this.getPlayersInSector();

        for (Player p : attackedPlayers) {
            /**
             * For every attacked player, check, if they are human, if the can
             * defend themselves: if so, they use the shield card.
             */
            if (p.getCharacter() instanceof Human) {
                Hand heldCards = p.getHand();
                for (Card c : heldCards.getHeldCards()) {
                    if (c instanceof DefenseCard) {
                        UseDefenseCard.useCard(p);
                        heldCards.getHeldCards().remove(c);
                        DefenseNoise defenseNoise = new DefenseNoise(
                                model.getRoundNumber(), p,
                                attacker.getLastPosition());
                        survivors.add(p);
                        model.addNoise(defenseNoise);
                        break;
                    }
                }
            }
            if (!p.getCharacter().isDefendAllowed()) {
                this.killPlayer(p);
                if (attacker.getCharacter() instanceof Alien
                        && p.getCharacter() instanceof Human) {
                    ((Alien) attacker.getCharacter()).feedAlien();
                }
            } else {
                p.resetDecorations();
            }
        }
        Noise attackNoise = new AttackNoise(model.getRoundNumber(), attacker,
                attacker.getLastPosition());
        model.addNoise(attackNoise);
    }

    /**
     * @param model
     * @return playersInSector: the players in the same sector as the attacker.
     */
    public List<Player> getPlayersInSector() {
        Coordinate target = attacker.getLastPosition();

        List<Player> playerList = model.getPlayers();
        for (Player p : playerList) {
            if (p.getLastPosition().equals(target)
                    && p.getState().equals(PlayerState.ALIVE)
                    && !(model.getCurrentPlayerReference().equals(p))) {
                playersInSector.add(p);
            }
        }
        return playersInSector;
    }

    /**
     * Kills the player passed as input parameter
     * 
     * @param victim
     */
    public void killPlayer(Player victim) {
        victim.setDead();
        victims.add(victim);
    }

    /**
     * Get the players killed in this turn
     * 
     * @return victims
     */
    public List<Player> getVictims() {
        return victims;
    }

    /**
     * Get the player who survived to the attack thanks to a defense card.
     * Method used to notify the players.
     * 
     * @return survivors;
     */
    public List<Player> getSurvivor() {
        return survivors;
    }
}
