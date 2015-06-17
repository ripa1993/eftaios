package it.polimi.ingsw.cg_8.model.sectors.normal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Secure sector. When a player ends his turn in this sector he can safely
 * finish his turn with no noises.
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SecureSector extends NormalSector {
    /**
     * 
     */
    private static final long serialVersionUID = -8710341825606799472L;

    /**
     * Secure sector constructor, used to perform checks and validations.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     */
    public SecureSector(int x, int y) {
        super(x, y);
    }

    /**
     * Default constructor used in the XML parsing.
     */
    public SecureSector() {
        super();
    }

    @Override
    public String toString() {
        return "SS";
    }

}
