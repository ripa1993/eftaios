package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.HumanCard;
import it.polimi.ingsw.cg_8.model.decks.CharacterDeck;
import it.polimi.ingsw.cg_8.model.decks.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator of character deck
 * 
 * @author Simone
 * @version 1.0
 */
public class CharacterDeckCreator extends DeckCreator {
    /**
     * Creates a character deck
     * 
     * @param numPlayer
     *            number of players in the game [2..8]
     * @return a complete character deck
     */
    public CharacterDeck createDeck(int numPlayer) {
        CharacterDeck charDeck = new CharacterDeck();
        List<Card> aliens = new ArrayList<Card>();
        List<Card> humans = new ArrayList<Card>();

        humans.add(new HumanCard("Ennio Maria Dominoni", null, "The Captain"));
        humans.add(new HumanCard("Julia Niguloti", "Cabal", "The Pilot"));
        humans.add(new HumanCard("Silvano Porpora", null, "The Psychologist"));
        humans.add(new HumanCard("Tuccio Brendon", "Piri", "The Soldier"));
        aliens.add(new AlienCard("Piero Ceccarella", null, "The First Alien"));
        aliens.add(new AlienCard("Vittorio Martana", null, "The Second Alien"));
        aliens.add(new AlienCard("Maria Galbani", null, "The Third Alien"));
        aliens.add(new AlienCard("Paolo Landon", null, "The Fourth Alien"));

        for (int i = 0; i < Math.floor((double) numPlayer / 2); i++) {
            charDeck.addCard(humans.get(i));
        }

        for (int i = 0; i < Math.ceil((double) numPlayer / 2); i++) {
            charDeck.addCard(aliens.get(i));
        }

        return charDeck;
    }

    @Override
    public Deck createDeck() {
        return createDeck(8);
    }

}
