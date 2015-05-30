package it.polimi.ingsw.cg_8.client;

public class Subscriber implements SubscriberInterface{

	private String playerName;


	
	public Subscriber(String playerName) {
		super();
		this.playerName = playerName;
	}

	
	
	
	/**
	 * @param msg is the message sent by the broker by invoking subscriber's remote interface
	 * the method simply prints the message received by the broker
	 */
	@Override
	public void publishMessage(String msg) {
		System.out.println("Subscriber-" + playerName + " received message: " + msg);
	}


}
