package it.polimi.ingsw.cg_8.model.map.creator;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.map.GalileiMapXML;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;

import java.io.File;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class GalileiCreatorXML extends MapCreator {
	/**
	 * Map that is going to be populated
	 */
	private final GameMap galileiMap;

	/**
	 * Constructor
	 */
	public GalileiCreatorXML() {
		super(new GalileiMapXML());
		galileiMap = this.getGm();
	}

	@Override
	protected GameMapSet sectorParser() {

		try {
			JAXBContext jc = JAXBContext.newInstance(GameMapSet.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			GameMapSet sectorSet = (GameMapSet) unmarshaller
					.unmarshal(new File(Resource.GALILEI_XML));

			return sectorSet;

		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
	}

	@Override
	public GameMap createMap() {
		GameMapSet sectorList = this.sectorParser();
		
		Iterator<Sector> iterator = sectorList.getSectorList().iterator();
		while (iterator.hasNext()) {
			Sector currentSector = iterator.next();
			int x = currentSector.getX();
			int y = currentSector.getY();
			addSector(new Coordinate(x, y), currentSector);
		}
		galileiMap.setAlienSpawn(sectorList.getAlienSpawnSector());
		galileiMap.setHumanSpawn(sectorList.getHumanSpawnSector());
		return galileiMap;
		
	}

}
