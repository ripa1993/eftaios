package it.polimi.ingsw.cg_8;

import java.io.File;
import java.io.InputStream;

/**
 * Container for references to resource files
 * 
 * @author Simone
 * @version 1.0
 */
public class Resource {
	/**
	 * Reference to Fermi map image
	 */
	public static final String IMG_FERMI_MAP = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "fermi.png";
	/**
	 * Reference to Galvani map image
	 */
	public static final String IMG_GALVANI_MAP = "resources"
			+ File.separatorChar + "images" + File.separatorChar
			+ "galvani.png";
	/**
	 * Reference to Galilei map image
	 */
	public static final String IMG_GALILEI_MAP = "resources"
			+ File.separatorChar + "images" + File.separatorChar
			+ "galilei.png";
	/**
	 * Reference to default background image
	 */
	public static final String IMG_BACKGROUND_PATTERN = "resources"
			+ File.separatorChar + "images" + File.separatorChar
			+ "background_pattern.png";
	/**
	 * Reference to default background image
	 */
	public static final String IMG_DEFAULT_BACKGROUND = "resources"
			+ File.separatorChar + "images" + File.separatorChar
			+ "default_background.png";
	/**
	 * Alien attack 1
	 */
	public static final String SOUND_ALIEN_ATTACK_1 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "alien_attack_1.wav";
	/**
	 * Alien attack 2
	 */
	public static final String SOUND_ALIEN_ATTACK_2 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "alien_attack_2.wav";
	/**
	 * Alien attack 3
	 */
	public static final String SOUND_ALIEN_ATTACK_3 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "alien_attack_3.wav";
	/**
	 * Death
	 */
	public static final String SOUND_DEATH = "resources" + File.separatorChar
			+ "sounds" + File.separatorChar + "death.wav";
	/**
	 * Escape hatch
	 */
	public static final String SOUND_ESCAPE_HATCH = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "escape_hatch.wav";
	/**
	 * Human Attack 1
	 */
	public static final String SOUND_HUMAN_ATTACK_1 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "human_attack_1.wav";
	/**
	 * Human attack 2
	 */
	public static final String SOUND_HUMAN_ATTACK_2 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "human_attack_2.wav";
	/**
	 * Human attack 3
	 */
	public static final String SOUND_HUMAN_ATTACK_3 = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "human_attack_3.wav";
	/**
	 * Noise
	 */
	public static final String SOUND_MOVEMENT_NOISE = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar + "noise.wav";
	/**
	 * Notification
	 */
	public static final String SOUND_NOTIFICATION = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "notification.wav";
	/**
	 * Shield
	 */
	public static final String SOUND_DEFENSE = "resources" + File.separatorChar
			+ "sounds" + File.separatorChar + "shield.wav";
	/**
	 * Spotlight
	 */
	public static final String SOUND_SPOTLIGHT = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "spotlight.wav";
	/**
	 * Teleport
	 */
	public static final String SOUND_TELEPORT = "resources"
			+ File.separatorChar + "sounds" + File.separatorChar
			+ "teleport.wav";
	/**
	 * Bonus track
	 */
	public static final String SOUND_BONUS = "resources" + File.separatorChar
			+ "sounds" + File.separatorChar + "bonus.wav";
	/**
	 * Adrenaline card
	 */
	public static final String IMG_ADRENALINE = "resources"
			+ File.separatorChar + "images" + File.separatorChar + "card"
			+ File.separatorChar + "adrenaline.png";
	/**
	 * attack card
	 */
	public static final String IMG_ATTACK = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "attack.png";
	/**
	 * defense card
	 */
	public static final String IMG_DEFENSE = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "defense.png";
	/**
	 * escape green card
	 */
	public static final String IMG_ESCAPE_GREEN = "resources"
			+ File.separatorChar + "images" + File.separatorChar + "card"
			+ File.separatorChar + "escape_green.png";
	/**
	 * escape red card
	 */
	public static final String IMG_ESCAPE_RED = "resources"
			+ File.separatorChar + "images" + File.separatorChar + "card"
			+ File.separatorChar + "escape_red.png";
	/**
	 * item card
	 */
	public static final String IMG_ITEM = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "item_icon.png";
	/**
	 * noise in any sector card
	 */
	public static final String IMG_NOISE_IN_ANY_SECTOR = "resources"
			+ File.separatorChar + "images" + File.separatorChar + "card"
			+ File.separatorChar + "noise_in_any_sector.png";
	/**
	 * noise in your sector card
	 */
	public static final String IMG_NOISE_IN_YOUR_SECTOR = "resources"
			+ File.separatorChar + "images" + File.separatorChar + "card"
			+ File.separatorChar + "noise_in_your_sector.png";
	/**
	 * sedatives card
	 */
	public static final String IMG_SEDATIVES = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "sedatives.png";
	/**
	 * silence card
	 */
	public static final String IMG_SILENCE = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "silence.png";
	/**
	 * spotlight card
	 */
	public static final String IMG_SPOTLIGHT = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "spotlight.png";
	/**
	 * teleport card
	 */
	public static final String IMG_TELEPORT = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "card" + File.separatorChar
			+ "teleport.png";
	/**
	 * titillium bold upright
	 */
	public static final String FONT_TITILLIUM_BOLD_UPRIGHT = "resources"
			+ File.separatorChar + "fonts" + File.separatorChar
			+ "Titillium-BoldUpright.otf";
	/**
	 * titillium bold upright
	 */
	public static final String FONT_TITILLIUM_SEMIBOLD_UPRIGHT = "resources"
			+ File.separatorChar + "fonts" + File.separatorChar
			+ "Titillium-SemiboldUpright.otf";
	/**
	 * Mr jones font
	 */
	public static final String FONT_MRJONES = "resources" + File.separatorChar
			+ "fonts" + File.separatorChar + "MrJonesBook.otf";

	/**
	 * alien 1
	 */
	public static final String IMG_ALIEN_1 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "alien_1.png";
	/**
	 * alien 2
	 */
	public static final String IMG_ALIEN_2 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "alien_2.png";
	/**
	 * alien 3
	 */
	public static final String IMG_ALIEN_3 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "alien_3.png";
	/**
	 * alien 4
	 */
	public static final String IMG_ALIEN_4 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "alien_4.png";
	/**
	 * human 1
	 */
	public static final String IMG_HUMAN_1 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "human_1.png";
	/**
	 * human 2
	 */
	public static final String IMG_HUMAN_2 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "human_2.png";
	/**
	 * human 3
	 */
	public static final String IMG_HUMAN_3 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "human_3.png";
	/**
	 * human 4
	 */
	public static final String IMG_HUMAN_4 = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "human_4.png";
	/**
	 * human 4
	 */
	public static final String IMG_UNKNOWN_CHAR = "resources" + File.separatorChar
			+ "images" + File.separatorChar + "player" + File.separatorChar
			+ "unknown_char.png";
}
