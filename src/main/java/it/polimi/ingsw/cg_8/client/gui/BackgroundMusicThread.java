package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to handle the background music during the game. It loops
 * an mp3 file using the JLayer mp3 library
 * 
 * @author Simone
 * @version 1.0
 * @see <a href="http://www.javazoom.net/javalayer/javalayer.html">JLayer MP3
 *      Library</a>
 *
 */
public class BackgroundMusicThread implements Runnable {
	/**
	 * Mp3 player
	 */
	private Player player;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
	        .getLogger(BackgroundMusicThread.class);

	/**
	 * Empty constructor
	 */
	public BackgroundMusicThread() {

	}

	/**
	 * Starts the background music
	 */
	private void playMe() {
		try {

			File file = new File(Resource.SOUND_BACKGROUDN);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
			player.play();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
	}

	@Override
	public void run() {
		while (true) {
			playMe();
		}

	}

}
