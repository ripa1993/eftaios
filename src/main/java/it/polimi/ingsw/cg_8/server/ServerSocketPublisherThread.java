package it.polimi.ingsw.cg_8.server;

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
public class ServerSocketPublisherThread extends ServerPublisher implements Runnable {
	private Socket subscriber;
	private ObjectOutputStream output;
	private ConcurrentLinkedQueue<ServerResponse> buffer;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(ServerSocketPublisherThread.class);

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

					send(message);
					logger.debug("Message sent: "+message);
				} else {
					try {
						synchronized (buffer) {
							buffer.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e1) {
				logger.error("Cannot write object to output");
			}
		}
	}

	@Override
	public void dispatchMessage(ServerResponse message) {
		buffer.add(message);
		synchronized (buffer) {
			buffer.notify();
		}
	}

	private void send(ServerResponse message) throws IOException, SocketException {
		output.writeObject(message);
		output.flush();
	}

}
