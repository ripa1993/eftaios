package it.polimi.ingsw.cg_8.model.sectors.special;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import it.polimi.ingsw.cg_8.model.sectors.Sector;

/**
 * Abstract special sector class, it is extended by
 * {@link it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector
 * EscapeHatchSector} and
 * {@link it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.SpawnSector
 * SpawnSector}
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SpecialSector extends Sector {
    /**
	 * 
	 */
    private static final long serialVersionUID = 7154800729832222232L;

    /**
     * Constructor for {@link SpecialSector}, used to make checks and
     * validations.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     */
    public SpecialSector(int x, int y) {
        super(x, y);
    }

    /**
     * Default constructor, used in the XML parsing.
     */
    public SpecialSector() {
        super();
    }

    @Override
    public String toString() {
        return "SpecialSector";
    }

}
