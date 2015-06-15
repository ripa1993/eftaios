package it.polimi.ingsw.cg_8;

import java.io.File;

/**
 * Container for references to resource files
 * 
 * @author Simone
 * @version 1.0
 */
public class Resource {
	/**
	 * images literal
	 */
	private static final String IMAGES_LITERAL = "images";
	/**
	 * resources literal
	 */
	private static final String RESOURCES_LITERAL = "resources";
	/**
	 * resources literal
	 */
	private static final String MAPS_LITERAL = "maps";	
	/**
	 * XML Maps folder
	 */
	private static final String MAPS_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + MAPS_LITERAL + File.separatorChar;
	/**
	 * Images folder
	 */
	private static final String IMAGES_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + IMAGES_LITERAL + File.separatorChar;
	/**
	 * Sound folder
	 */
	private static final String SOUND_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + "sounds" + File.separatorChar;
	/**
	 * Card folder
	 */
	private static final String CARD_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + IMAGES_LITERAL + File.separatorChar + "card"
			+ File.separatorChar;
	/**
	 * Font folder
	 */
	private static final String FONT_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + "fonts" + File.separatorChar;
	/**
	 * Player folder
	 */
	private static final String PLAYER_FOLDER = RESOURCES_LITERAL
			+ File.separatorChar + IMAGES_LITERAL + File.separatorChar
			+ "player" + File.separatorChar;
	/**
	 * Reference to Fermi map image
	 */
	public static final String IMG_FERMI_MAP = IMAGES_FOLDER + "fermi.png";
	/**
	 * Reference to Galvani map image
	 */
	public static final String IMG_GALVANI_MAP = IMAGES_FOLDER + "galvani.png";
	/**
	 * Reference to Galilei map image
	 */
	public static final String IMG_GALILEI_MAP = IMAGES_FOLDER + "galilei.png";
	/**
	 * Default no map image
	 */
	public static final String IMG_MAP_BG = IMAGES_FOLDER + "map_bg_def.png";
	/**
	 * Reference to default background image
	 */
	public static final String IMG_BACKGROUND_PATTERN = IMAGES_FOLDER
			+ "background_pattern.png";
	/**
	 * Reference to default background image
	 */
	public static final String IMG_DEFAULT_BACKGROUND = IMAGES_FOLDER
			+ "default_background.png";
	/**
	 * Alien attack 1
	 */
	public static final String SOUND_ALIEN_ATTACK_1 = SOUND_FOLDER
			+ "alien_attack_1.wav";
	/**
	 * Alien attack 2
	 */
	public static final String SOUND_ALIEN_ATTACK_2 = SOUND_FOLDER
			+ "alien_attack_2.wav";
	/**
	 * Alien attack 3
	 */
	public static final String SOUND_ALIEN_ATTACK_3 = SOUND_FOLDER
			+ "alien_attack_3.wav";
	/**
	 * Death
	 */
	public static final String SOUND_DEATH = SOUND_FOLDER + "death.wav";
	/**
	 * Escape hatch
	 */
	public static final String SOUND_ESCAPE_HATCH = SOUND_FOLDER
			+ "escape_hatch.wav";
	/**
	 * Human Attack 1
	 */
	public static final String SOUND_HUMAN_ATTACK_1 = SOUND_FOLDER
			+ "human_attack_1.wav";
	/**
	 * Human attack 2
	 */
	public static final String SOUND_HUMAN_ATTACK_2 = SOUND_FOLDER
			+ "human_attack_2.wav";
	/**
	 * Human attack 3
	 */
	public static final String SOUND_HUMAN_ATTACK_3 = SOUND_FOLDER
			+ "human_attack_3.wav";
	/**
	 * Noise
	 */
	public static final String SOUND_MOVEMENT_NOISE = SOUND_FOLDER
			+ "noise.wav";
	/**
	 * Notification
	 */
	public static final String SOUND_NOTIFICATION = SOUND_FOLDER
			+ "notification.wav";
	/**
	 * Shield
	 */
	public static final String SOUND_DEFENSE = SOUND_FOLDER + "shield.wav";
	/**
	 * Spotlight
	 */
	public static final String SOUND_SPOTLIGHT = SOUND_FOLDER + "spotlight.wav";
	/**
	 * Teleport
	 */
	public static final String SOUND_TELEPORT = SOUND_FOLDER + "teleport.wav";
	/**
	 * Bonus track
	 */
	public static final String SOUND_BONUS = SOUND_FOLDER + "bonus.wav";

	/**
	 * background music
	 */
	public static final String SOUND_BACKGROUDN = SOUND_FOLDER
			+ "Inside_an_Alien_Spaceship.mp3";
	/**
	 * Adrenaline card
	 */
	public static final String IMG_ADRENALINE = CARD_FOLDER + "adrenaline.png";
	/**
	 * attack card
	 */
	public static final String IMG_ATTACK = CARD_FOLDER + "attack.png";
	/**
	 * defense card
	 */
	public static final String IMG_DEFENSE = CARD_FOLDER + "defense.png";
	/**
	 * escape green card
	 */
	public static final String IMG_ESCAPE_GREEN = CARD_FOLDER
			+ "escape_green.png";
	/**
	 * escape red card
	 */
	public static final String IMG_ESCAPE_RED = CARD_FOLDER + "escape_red.png";
	/**
	 * item card
	 */
	public static final String IMG_ITEM = CARD_FOLDER + "item_icon.png";
	/**
	 * noise in any sector card
	 */
	public static final String IMG_NOISE_IN_ANY_SECTOR = CARD_FOLDER
			+ "noise_in_any_sector.png";
	/**
	 * noise in your sector card
	 */
	public static final String IMG_NOISE_IN_YOUR_SECTOR = CARD_FOLDER
			+ "noise_in_your_sector.png";
	/**
	 * sedatives card
	 */
	public static final String IMG_SEDATIVES = CARD_FOLDER + "sedatives.png";
	/**
	 * silence card
	 */
	public static final String IMG_SILENCE = CARD_FOLDER + "silence.png";
	/**
	 * spotlight card
	 */
	public static final String IMG_SPOTLIGHT = CARD_FOLDER + "spotlight.png";
	/**
	 * teleport card
	 */
	public static final String IMG_TELEPORT = CARD_FOLDER + "teleport.png";
	/**
	 * titillium bold upright
	 */
	public static final String FONT_TITILLIUM_BOLD_UPRIGHT = FONT_FOLDER
			+ "Titillium-BoldUpright.otf";
	/**
	 * titillium bold upright
	 */
	public static final String FONT_TITILLIUM_SEMIBOLD_UPRIGHT = FONT_FOLDER
			+ "Titillium-SemiboldUpright.otf";
	/**
	 * Mr jones font
	 */
	public static final String FONT_MRJONES = FONT_FOLDER + "MrJonesBook.otf";

	/**
	 * alien 1
	 */
	public static final String IMG_ALIEN_1 = PLAYER_FOLDER + "alien_1.png";
	/**
	 * alien 2
	 */
	public static final String IMG_ALIEN_2 = PLAYER_FOLDER + "alien_2.png";
	/**
	 * alien 3
	 */
	public static final String IMG_ALIEN_3 = PLAYER_FOLDER + "alien_3.png";
	/**
	 * alien 4
	 */
	public static final String IMG_ALIEN_4 = PLAYER_FOLDER + "alien_4.png";
	/**
	 * human 1
	 */
	public static final String IMG_HUMAN_1 = PLAYER_FOLDER + "human_1.png";
	/**
	 * human 2
	 */
	public static final String IMG_HUMAN_2 = PLAYER_FOLDER + "human_2.png";
	/**
	 * human 3
	 */
	public static final String IMG_HUMAN_3 = PLAYER_FOLDER + "human_3.png";
	/**
	 * human 4
	 */
	public static final String IMG_HUMAN_4 = PLAYER_FOLDER + "human_4.png";
	/**
	 * human 4
	 */
	public static final String IMG_UNKNOWN_CHAR = PLAYER_FOLDER
			+ "unknown_char.png";
	/**
	 * human blue overlay
	 */
	public static final String IMG_HUMAN_OVERLAY = IMAGES_FOLDER
			+ "human_item_overlay.png";
	/**
	 * alien pink overlay
	 */
	public static final String IMG_ALIEN_OVERLAY = IMAGES_FOLDER
			+ "alien_item_overlay.png";
	/**
	 * turn background/border
	 */
	public static final String IMG_TURN_BG = IMAGES_FOLDER + "turn_border.png";
	/**
	 * title
	 */
	public static final String IMG_ART_TITLE = IMAGES_FOLDER + "title.png";
	/**
	 * art human
	 */
	public static final String IMG_ART_HUMAN = IMAGES_FOLDER + "art_human.png";
	/**
	 * art map
	 */
	public static final String IMG_ART_MAP = IMAGES_FOLDER + "art_map.png";
	/**
	 * art alien
	 */
	public static final String IMG_ART_ALIEN = IMAGES_FOLDER + "art_alien.png";
	/**
	 * XML document of FERMI
	 */
	public static final String FERMI_XML = MAPS_FOLDER + "FERMI.xml";
	/**
	 * XML document of GALILEI
	 */
	public static final String GALILEI_XML = MAPS_FOLDER + "GALILEI.xml";
	/**
	 * XML document of FERMI
	 */
	public static final String GALVANI_XML = MAPS_FOLDER + "GALVANI.xml";
}
