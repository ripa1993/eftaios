package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
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
	 * The output stream.
	 */
	private ObjectOutputStream output;
	/**
	 * The buffer where the messages are stored, before being sent to the
	 * player.
	 */
	private Queue<ServerResponse> buffer;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(ServerSocketPublisherThread.class);

	/**
	 * Creates a publisher for the current subscriber.
	 * 
	 * @param subscriber
	 */
	public ServerSocketPublisherThread(Socket subscriber) {
		this.buffer = new ConcurrentLinkedQueue<ServerResponse>();
		try {
			this.output = new ObjectOutputStream(subscriber.getOutputStream());
		} catch (IOException e) {
			LOGGER.error("Cannot open object output stream", e);
		}
		LOGGER.debug("Publisher thread created");
	}

	@Override
	public void run() {
		while (true) {
			try {
				ServerResponse message = buffer.poll();
				if (message != null) {
					if (message instanceof ResponseCard) {
						LOGGER.debug("response card asd" + message);
					}
					send(message);
					LOGGER.debug("Message sent: " + message);
				} else {

					synchronized (buffer) {
						buffer.wait();
					}
				}

			} catch (IOException e1) {
				LOGGER.error("Cannot write object to output", e1);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * Add a message to the buffer.
	 * 
	 * @param message
	 *            The message sent by the server.
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
	 * 
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
