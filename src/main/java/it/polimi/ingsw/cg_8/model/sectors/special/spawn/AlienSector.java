package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import it.polimi.ingsw.cg_8.model.sectors.SectorType;

public class AlienSector extends SpawnSector {

	public AlienSector(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SectorType getSectorType() {
		return SectorType.ALIEN_SECTOR;
	}

}
