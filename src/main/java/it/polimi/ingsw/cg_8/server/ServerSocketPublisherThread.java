package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Implmentation of a publisher thread, it sends objects to a specific
 * subscriber
 * 
 * @author Simone
 *
 */
public class ServerSocketPublisherThread implements Runnable {
	private Socket subscriber;
	private ObjectOutputStream output;
	private ConcurrentLinkedQueue<ServerResponse> buffer;

	public ServerSocketPublisherThread(Socket subscriber) {
		this.subscriber = subscriber;
		this.buffer = new ConcurrentLinkedQueue<ServerResponse>();
		try {
			this.output = new ObjectOutputStream(subscriber.getOutputStream());
		} catch (IOException e) {
			System.err.println("Cannot open object output stream");
		}
		System.out.println("Publisher thread created");
	}

	@Override
	public void run() {
		while (true) {
			try {
				ServerResponse message = buffer.poll();
				if (message != null)

					send(message);

				else {
					try {
						synchronized (buffer) {
							buffer.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e1) {
				System.err.println("Cannot write object to output");
			}
		}
	}

	public void dispatchMessage(ServerResponse message) {
		buffer.add(message);
		synchronized (buffer) {
			buffer.notify();
		}
	}

	private void send(ServerResponse message) throws IOException {
		output.writeObject(message);
		output.flush();
	}

}
