package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BackgroundMusicThread implements Runnable {
	private Player player;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(BackgroundMusicThread.class);

	private void playMe() {
		try {

			File file = new File(Resource.SOUND_BACKGROUDN);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
			player.play();

		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}

	@Override
	public void run() {
		while (true) {
			playMe();
		}

	}

	// public static void main(String[] args){
	// (new BackgroundMusicThread()).run();
	// }
}
