package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Class used to store the messages sent by the server to the client; when a new
 * message is added, the GUI is notified so that the message can be displayed on
 * screen. Three different storages are used, depending on the type of message received.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ClientData extends Observable {
	/**
	 * List used to store {@link ResponseChat chat messages}.
	 */
	private List<ResponseChat> chat;
	/**
	 * List used to store {@link ResponseNoise noises}.
	 */
	private List<ResponseNoise> noise;
	/**
	 * List used to store {@link ResponsePrivate private messages}.
	 */
	private List<ResponsePrivate> privateMessages;

	public ClientData() {
		chat = new ArrayList<ResponseChat>();
		noise = new ArrayList<ResponseNoise>();
		privateMessages = new ArrayList<ResponsePrivate>();
	}

	/**
	 * Stores the response in the appropriate section.
	 * @param response The message sent by the server.
	 */
	public void storeResponse(ServerResponse response) {
		if (response instanceof ResponseChat) {
			chat.add((ResponseChat) response);
			setChanged();
			notifyObservers("Chat");
			return;
		}
		if (response instanceof ResponseNoise) {
			noise.add((ResponseNoise) response);
			setChanged();
			notifyObservers("Noise");
			return;
		}
		if (response instanceof ResponsePrivate) {
			privateMessages.add((ResponsePrivate) response);
			setChanged();
			notifyObservers("Private");
			return;
		}
		return;
	}

	public List<ResponseChat> getChat() {
		return chat;
	}

	public List<ResponseNoise> getNoise() {
		return noise;
	}

	public List<ResponsePrivate> getPrivateMessages() {
		return privateMessages;
	}

	/**
	 * Used to get the last message added it the list.
	 * @return the last {@link ResponseChat chat message}
	 */
	public ResponseChat getLastChat() {
		return chat.get(chat.size() - 1);
	}
	/**
	 * Used to get the last message added it the list.
	 * @return the last {@link ResponseNoise noise}
	 */
	public ResponseNoise getLastNoise() {
		return noise.get(noise.size() - 1);

	}
	/**
	 * Used to get the last message added it the list.
	 * @return the last {@link ResponsePrivate private message}
	 */
	public ResponsePrivate getLastPrivate() {
		return privateMessages.get(privateMessages.size() - 1);

	}

}
