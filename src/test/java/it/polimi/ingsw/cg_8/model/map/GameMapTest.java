package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.assertFalse;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;

import org.junit.Test;

public class GameMapTest {

	@Test
	public void equals() {
		MapCreator mc1 = new GalvaniCreator();
		GameMap map1 = mc1.createMap();
		MapCreator mc2 = new FermiCreator();
		GameMap map2 = mc2.createMap();
		assertFalse(map1.equals(map2));
	}

}
