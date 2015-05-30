package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.server.GameRoom;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Thread created by the client when initialized. Connects the client to the
 * server, through RMI.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ClientRMI implements Runnable {

	/**
	 * Identifies the client and it's assigned by the server after the first
	 * successful connection; before the assignment it is defaulted at 0.
	 */
	private int clientID = 0;

	/**
	 * Identifies the client (and the player), it is chosen by the player at the
	 * start of the game and displayed to the other players.
	 */
	private String playerName;

	private Scanner stdin;

	private Subscriber subscriber;

	public ClientRMI(String playerName, Scanner stdin) {

		this.playerName = playerName;
		this.stdin = stdin;
		subscriber = new Subscriber(playerName);
	}

	// se rmi faccio lookup registry. sul registry avrò subscribe (prendo un
	// player id) send name (invio il nome al server) e makeMove (invio
	// clientAction)
	// c'è pure write to all e write to player, usate dal controller per mandare
	// messaggi tramite dispatch, collocato sul server
	// fanno uso di publish message, da reimplementare sul client come remote
	// method.
	// non ho problemi di sicurezza perchè write to... non sono remote method,
	// non fanno override della broker interface
	//
	@Override
	public void run() {
		System.out.println("Contacting the broker...");

		try {

			Registry registry = LocateRegistry.getRegistry(7777);

			GameRoom broker = (GameRoom) registry.lookup("gameRoom");

			// subscriber exports its own remote interface SubscriberInterface
			// so that it can
			// receive invocations from remote brokers.
			// Then it invokes the subscribe remote method of the BrokerInterface
			// and passes its
			// own remote interface as a parameter.
			broker.subscribe((SubscriberInterface) UnicastRemoteObject
					.exportObject(subscriber, 0));

			broker.subscribeToGame(this.clientID);
			
			
		} catch (NotBoundException | RemoteException e) {
			e.printStackTrace();
		}

	}
}
