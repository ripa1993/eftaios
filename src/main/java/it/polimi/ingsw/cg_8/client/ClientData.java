package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ClientData extends Observable {
	private List<ResponseChat> chat;
	private List<ResponseNoise> noise;
	private List<ResponsePrivate> privateMessages;
	
	
	public ClientData(){
		chat = new ArrayList<ResponseChat>();
		noise = new ArrayList<ResponseNoise>();
		privateMessages = new ArrayList<ResponsePrivate>();	
	}
	
	public void storeResponse(ServerResponse response){
		if(response instanceof ResponseChat){
			chat.add((ResponseChat) response);
			setChanged();
			notifyObservers("Chat");
			return;
		}
		if(response instanceof ResponseNoise){
			noise.add((ResponseNoise) response);
			setChanged();
			notifyObservers("Noise");
			return;
		}
		if(response instanceof ResponsePrivate){
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

	public ResponseChat getLastChat() {
		return chat.get(chat.size()-1);
	}

	public ResponseNoise getLastNoise() {
		return noise.get(noise.size()-1);
		
	}

	public ResponsePrivate getLastPrivate() {
		return privateMessages.get(privateMessages.size()-1);
		
	}
	
	
}
