package it.polimi.ingsw.cg_8.model.map.creator;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.map.FermiMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creator for the map "Fermi". Given a new empty Fermi map, this class allows
 * to populate the map with the right coordinates, by parsing an XML document
 * representing the map itself.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class FermiCreator extends MapCreator {

	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(FermiCreator.class);

	/**
	 * Constructor
	 */
	public FermiCreator() {
		super(new FermiMap());

	}

	@Override
	protected GameMapSet sectorParser() {

		try {
			JAXBContext jc = JAXBContext.newInstance(GameMapSet.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			return (GameMapSet) unmarshaller.unmarshal(new File(
					Resource.FERMI_XML));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new GameMapSet();
		}
	}

}
