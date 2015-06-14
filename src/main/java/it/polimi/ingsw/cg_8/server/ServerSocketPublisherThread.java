package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implmentation of a publisher thread, it sends objects to a specific
 * subscriber
 * 
 * @author Simone
 *
 */
public class ServerSocketPublisherThread extends ServerPublisher implements
		Runnable {
	/**
	 * The socket of the player.
	 */
	private Socket subscriber;
	/**
	 * The output stream.
	 */
	private ObjectOutputStream output;
	/**
	 * The buffer where the messages are stored, before being sent to the
	 * player.
	 */
	private ConcurrentLinkedQueue<ServerResponse> buffer;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(ServerSocketPublisherThread.class);

	/**
	 * Creates a publisher for the current subscriber.
	 * @param subscriber
	 */
	public ServerSocketPublisherThread(Socket subscriber) {
		this.subscriber = subscriber;
		this.buffer = new ConcurrentLinkedQueue<ServerResponse>();
		try {
			this.output = new ObjectOutputStream(subscriber.getOutputStream());
		} catch (IOException e) {
			logger.error("Cannot open object output stream");
		}
		logger.debug("Publisher thread created");
	}

	@Override
	public void run() {
		while (true) {
			try {
				ServerResponse message = buffer.poll();
				if (message != null) {
					if (message instanceof ResponseCard) {
						System.out.println("response card asd" + message);
					}
					send(message);
					logger.debug("Message sent: " + message);
				} else {
					try {
						synchronized (buffer) {
							buffer.wait();
						}
					} catch (InterruptedException e) {
						logger.error(e.getMessage());
					}
				}
			} catch (IOException e1) {
				logger.error("Cannot write object to output");
			}
		}
	}

	/**
	 * Add a message to the buffer.
	 * @param message The message sent by the server.
	 */
	@Override
	public void dispatchMessage(ServerResponse message) {
		buffer.add(message);
		synchronized (buffer) {
			buffer.notify();
		}
	}
	
	/**
	 * Send a message to the player.
	 * @param message
	 * @throws IOException
	 * @throws SocketException
	 */
	private void send(ServerResponse message) throws IOException,
			SocketException {
		output.writeObject(message);
		output.flush();
	}

}
