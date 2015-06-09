package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Server response used in order to comunicate Noises to the clients
 * 
 * @author Simone
 *
 */
public class ResponseNoise implements Serializable, ServerResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -539924760536211551L;
	/**
	 * Noise to be sent
	 */
	private final Noise noise;

	/**
	 * Builds a new server response with the given noise
	 * 
	 * @param noise
	 *            noise to be communicated
	 */
	public ResponseNoise(Noise noise) {
		this.noise = noise;
	}

	/**
	 * 
	 * @return the communicated noise
	 */
	public Noise getNoise() {
		return noise;
	}

	@Override
	public String toString() {
		return noise.toString();
	}
}
