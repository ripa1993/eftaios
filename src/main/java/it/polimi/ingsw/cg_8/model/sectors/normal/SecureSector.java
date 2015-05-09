package it.polimi.ingsw.cg_8.model.sectors.normal;

import it.polimi.ingsw.cg_8.model.sectors.SectorType;

public class SecureSector extends NormalSector {

	public SecureSector(int x, int y) {
		super(x,y);
	}

	@Override
	public SectorType getSectorType() {
		return SectorType.SECURE_SECTOR;
	}

}
