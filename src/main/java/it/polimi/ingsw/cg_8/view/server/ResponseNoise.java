package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

public class ResponseNoise implements Serializable, ServerResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -539924760536211551L;
	private final Noise noise;

	public ResponseNoise(Noise noise) {
		this.noise = noise;
	}
	
	public Noise getNoise(){
		return noise;
	}

	@Override
	public String toString() {
		return noise.toString();
	}
}
