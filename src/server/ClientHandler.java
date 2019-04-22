package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.net.InetAddress;

public class ClientHandler implements Runnable {
	public Socket getSocket() {
		return this.clientSocket;
	}
	private Server server;
	private PrintWriter outMessage;
	private Scanner inMessage;
	private static final String HOST = "localhost";
	private static final int PORT = 3443;
	private Socket clientSocket = null;
	private static int clients_count = 0;
	private String name = "";
	public ClientHandler(Socket socket, Server server) {
		try {
			clients_count++;
			this.server = server;
			this.clientSocket = socket;
			this.outMessage = new PrintWriter(socket.getOutputStream());
			this.inMessage = new Scanner(socket.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (true) {
				// сервер отправляет сообщение
				server.sendMessageToAllClients("0:Клиентов в чате = " + clients_count);
				break;
			}

			while (true) {
				// Если от клиента пришло сообщение
				if (inMessage.hasNext()) {

					String clientMessage = inMessage.nextLine();
					System.out.println(clientMessage);
					
					if (clientMessage.equalsIgnoreCase("##session##end##")) {
						break;
					}
					if(clientMessage.charAt(0) != '0') {
						String tmp = clientMessage.substring(clientMessage.lastIndexOf('<') + 1, clientMessage.length() - 1);
						String []sockets = tmp.split(" and ");
						clientMessage = clientMessage.substring(0, clientMessage.lastIndexOf('<'));
						server.sendMessageToClients(sockets, clientMessage);
						Thread.sleep(100);
						continue;
					}
					
					server.sendMessageToAllClients(clientMessage);
				}
				Thread.sleep(100);
			}
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		finally {
			this.close();
		}
	}

	public void sendMsg(String msg) {
		try {
			outMessage.println(msg);
			outMessage.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void close() {
		server.removeClient(this);
		clients_count--;
		server.sendMessageToAllClients("0:Клиентов в чате = " + clients_count);
	}
}
