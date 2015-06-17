package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class contains a set of the various sector of a map, obtained by parsing
 * an XML document. It works as an adapter between the result of the parsing (a
 * set of sectors) and the actual representation of the map in the model, i.e an
 * Hashmap<Coordinate, Sector>, which it is easier to manipulate and analyze.
 * 
 * @author Alberto Parravicini
 * @version 1.0
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
     * 
     * @return the Sectors of the map.
     */
    public Set<Sector> getSectorList() {
        return this.sectorSet;
    }

    @Override
    public String toString() {
        return "SectorSet \n[" + sectorSet + "]";
    }

}
