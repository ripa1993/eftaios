package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.assertFalse;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreatorXML;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreatorXML;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;

import org.junit.Test;

public class GameMapTest {

	@Test
	public void equals() {
		MapCreator mc1 = new GalvaniCreatorXML();
		GameMap map1 = mc1.createMap();
		MapCreator mc2 = new FermiCreatorXML();
		GameMap map2 = mc2.createMap();
		assertFalse(map1.equals(map2));
	}

}
