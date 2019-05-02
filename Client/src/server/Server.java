package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static final int PORT = 3443;
	private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	public Server() {
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT, 100, InetAddress.getLocalHost());
			System.out.println("Сервер запущен!");
			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println(serverSocket);
				ClientHandler client = new ClientHandler(clientSocket, this);
				clients.add(client);
				new Thread(client).start();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				clientSocket.close();
				System.out.println("Сервер остановлен");
				serverSocket.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void sendMessageToAllClients(String msg) {
		for (ClientHandler o : clients) {
			o.sendMsg(msg);
		}
	}
	public void sendMessageToClients(String[] sockets, String clientMessage) {
		for (int i = 0; i < sockets.length; i++) {
			sockets[i] = sockets[i].replace("Socket[addr=", "").replace(",port=", " ").replace(",localport=", " ").replace("]", "").replace("localhost", "");
			String []socketAddr = sockets[i].split(" ");
			for (ClientHandler o : clients) {
				String sClient = o.getSocket().toString().replace("Socket[addr=", "").replace(",port=", " ").replace(",localport=", " ").replace("]", "");
				String []sClientAddr = sClient.split(" ");
				System.out.println("sClientAddr: " + sClient);
				System.out.println("socketAdd: " + sockets[i]);
				if (socketAddr[0].equals(sClientAddr[0]) && socketAddr[2].equals(sClientAddr[1]) && socketAddr[1].equals(sClientAddr[2])) {
					o.sendMsg(clientMessage);
				}

			}
		}
	}
	public void removeClient(ClientHandler client) {
		clients.remove(client);
	}
}
