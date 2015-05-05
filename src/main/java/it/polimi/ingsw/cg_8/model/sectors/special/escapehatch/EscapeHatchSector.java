package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

import it.polimi.ingsw.cg_8.model.sectors.special.SpecialSector;

public class EscapeHatchSector extends SpecialSector {

	EscapeHatchBehaviour status;
	
	public EscapeHatchSector(int x, int y) {
		super(x, y);
		status = new NotUsedEHBehaviour();
	}
	
	public void setUsed(){
		this.status=new UsedEHBehaviour();
	}

}
