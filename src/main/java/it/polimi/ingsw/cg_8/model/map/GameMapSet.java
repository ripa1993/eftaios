package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Return a set containing the various sector of a map, obtained by parsing an
 * XML document. The class also contains method used to get a set of the various
 * sectors of a given type.
 * 
 * @author Alberto Parravicini
 *
 */
@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameMapSet {

	/**
	 * Set of the sectors of a given map, divided by their type.
	 */
	@XmlElementWrapper(name = "sectorList")
	@XmlElements({
			@XmlElement(name = "safeSector", type = SecureSector.class),
			@XmlElement(name = "dangerousSector", type = DangerousSector.class),
			@XmlElement(name = "escapeHatchSector", type = EscapeHatchSector.class),
			@XmlElement(name = "humanSector", type = HumanSector.class),
			@XmlElement(name = "alienSector", type = AlienSector.class), })
	private Set<Sector> sectorSet;

	/**
	 * Return the set of the Sectors of the map.
	 * @return the Sectors of the map.
	 */
	public Set<Sector> getSectorList() {
		return this.sectorSet;
	}

	/**
	 * Return the set of the Safe Sectors of the map.
	 * @return the Safe Sectors of the map.
	 */
	public Set<Sector> getSafeSectors() {
		Set<Sector> safeSectorList = new HashSet<Sector>();
		for (Sector sec : this.sectorSet) {
			if (sec instanceof SecureSector) {
				safeSectorList.add(sec);
			}
		}
		return safeSectorList;
	}

	/**
	 * Return the set of the Dangerous Sectors of the map.
	 * @return the Dangerous Sectors of the map.
	 */
	public Set<Sector> getDangerousSectors() {
		Set<Sector> dangerousSectorList = new HashSet<Sector>();
		for (Sector sec : this.sectorSet) {
			if (sec instanceof DangerousSector) {
				dangerousSectorList.add(sec);
			}
		}
		return dangerousSectorList;
	}

	/**
	 * Return the set of the Escape Hatch Sectors of the map.
	 * @return the Escape Hatch Sectors of the map.
	 */
	public Set<Sector> getEscapeHatchSectors() {
		Set<Sector> escapeHatchSectorList = new HashSet<Sector>();
		for (Sector sec : this.sectorSet) {
			if (sec instanceof EscapeHatchSector) {
				escapeHatchSectorList.add(sec);
			}
		}
		return escapeHatchSectorList;
	}

	/**
	 * Return the set of the Human Spawn Sectors of the map.
	 * @return the Human Spawn Sectors of the map.
	 */
	public Sector getHumanSpawnSector() {
		Sector humanSector = new HumanSector();
		for (Sector sec : this.sectorSet) {
			if (sec instanceof HumanSector) {
				humanSector = sec;
			}
		}
		return humanSector;
	}

	/**
	 * Return the set of the Alien Spawn Sectors of the map.
	 * @return the Alien Spawn Sectors of the map.
	 */
	public Sector getAlienSpawnSector() {
		Sector alienSector = new AlienSector();
		for (Sector sec : this.sectorSet) {
			if (sec instanceof AlienSector) {
				alienSector = sec;
			}
		}
		return alienSector;
	}

	@Override
	public String toString() {
		return "SectorSet \n[" + sectorSet + "]";
	}

}
