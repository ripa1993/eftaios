package it.polimi.ingsw.cg_8.model.map.creator;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.map.GalileiMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creator for the map "Galilei". Given a new empty Galilei map, this class
 * allows to populate the map with the right coordinates, by parsing an XML
 * document representing the map itself.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class GalileiCreator extends MapCreator {

	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(GalileiCreator.class);

	/**
	 * Constructor
	 */
	public GalileiCreator() {
		super(new GalileiMap());

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
			LOGGER.error(e.getMessage(), e);
			return new GameMapSet();
		}
	}



}
