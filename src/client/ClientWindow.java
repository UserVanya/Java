package client;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientWindow extends JFrame {
	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 3443;
	private Socket clientSocket;
	private Scanner inMessage;
	private PrintWriter outMessage;
	private JTextField jtfMessage;
	private JTextField jtfName;
	private JTextArea jtaTextAreaMessage;
	private String clientName = "";
	public String getClientName() {
		return this.clientName;
	}

	private Map<String, String> clients = new HashMap<String, String>();
	private Map<String, JToggleButton> clientsButtons = new HashMap<String, JToggleButton>();

	public ClientWindow() {
		try {
			clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
			inMessage = new Scanner(clientSocket.getInputStream());
			outMessage = new PrintWriter(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(100, 100, 600, 500);
		setTitle("Client");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jtaTextAreaMessage = new JTextArea();
		jtaTextAreaMessage.setEditable(false);
		jtaTextAreaMessage.setLineWrap(true);
		
		JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
		add(jsp, BorderLayout.CENTER);
		JLabel jlNumberOfClients = new JLabel("Количество клиентов в чате: ");
		add(jlNumberOfClients, BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel(new BorderLayout());

		JPanel clientsPanel = new JPanel();
		add(clientsPanel, BorderLayout.EAST);
		BoxLayout clientsLayout = new BoxLayout(clientsPanel, BoxLayout.Y_AXIS);
		clientsPanel.setLayout(clientsLayout);

		add(bottomPanel, BorderLayout.SOUTH);
		JButton jbSendMessage = new JButton("Отправить");
		jtfMessage = new JTextField("Введите ваше сообщение: ");
		jtfName = new JTextField("Введите ваше имя: ");
		bottomPanel.add(jtfName, BorderLayout.WEST);
		JButton jbJoin = new JButton("Присоединиться");
		bottomPanel.add(jbJoin, BorderLayout.CENTER);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (inMessage.hasNext()) {
							String inMes = inMessage.nextLine();
							int startIndex = inMes.indexOf(':');
							String withoutLength = inMes.substring(inMes.indexOf(":") + 1);
							if (Integer.parseInt(inMes.substring(0, startIndex)) == 0) {
								sendTechnicalMsg(withoutLength, clientsPanel, jlNumberOfClients);
							}
							else {
								if (withoutLength.length() != clientName.length() + 2) {
									jtaTextAreaMessage.append(withoutLength);
									jtaTextAreaMessage.append("\n");
								}
							}
						}
					}
				} 
				catch (Exception e) {}
			}
		}).start();
		jbJoin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!jtfName.getText().trim().isEmpty() && !jtfName.getText().equals("Введите ваше имя: ")) {
					logIn();
					bottomPanel.remove(jbJoin);
					bottomPanel.add(jbSendMessage, BorderLayout.EAST);
					bottomPanel.add(jtfMessage, BorderLayout.CENTER);
					bottomPanel.updateUI();
					clientsPanel.updateUI();
				}
			}
		});
		jbSendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jtfMessage.getText().trim().isEmpty() && !jtfMessage.getText().equals("Введите ваше сообщение: ")) {
					sendMsg();
					jtfMessage.setText("Введите ваше сообщение: ");
				}
			}
		});
		jbJoin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateName();
			}
		});
		
		
		jtfMessage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateName();
				if(jtfMessage.getText().equals("Введите ваше сообщение: "))
					jtfMessage.setText("");
			}
		});
		jtaTextAreaMessage.addFocusListener(new FocusAdapter(){		
			@Override
			public void focusGained(FocusEvent e) {
				updateName();
				updateMessage();
			}
		});
		jtfName.addFocusListener(new FocusAdapter() {
			@Override

			public void focusGained(FocusEvent e) {
				updateMessage();
				if(jtfName.getText().equals("Введите ваше имя: "))
					jtfName.setText("");
			}
		});

		

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					if (!clientName.isEmpty() && clientName != "Введите ваше имя: ") {
						outMessage.println("0:" + clientSocket + ":" + clientName + ":Выход");
					} else {
						outMessage.println("0:Участник вышел из чата, так и не представившись!:Аноним");
					}
					outMessage.println("##session##end##");
					outMessage.flush();
					outMessage.close();
					inMessage.close();
					clientSocket.close();
				} catch (IOException exc) {}
			}
		});
		setVisible(true);
	}
	private void updateName() {
		if(jtfName.isEditable() && jtfName.getText().equals("")) {
			jtfName.setText("Введите ваше имя: ");
		}
	}
	private void updateMessage() {
		if(jtfMessage.getText().equals("")) {
			jtfMessage.setText("Введите ваше сообщение: ");
		}
	}


	private void sendTechnicalMsg(String withoutLength, JPanel clientsPanel, JLabel jlNumberOfClients) {

		String clientsInChat = "Клиентов в чате = ";
		String addNewClient = "Добавление";
		String removeClient = "Выход";
		String anonim = "Аноним";
		if (withoutLength.contains(clientsInChat)) {
			jlNumberOfClients.setText(withoutLength);
		} else if (withoutLength.contains(addNewClient)) {
			addClient(withoutLength, clientsPanel);
		} else if(withoutLength.contains(removeClient)) {
			removeClient(withoutLength, clientsPanel);
		} else if(withoutLength.contains(anonim)) {
			jtaTextAreaMessage.append(withoutLength.replace(":Аноним", ""));
		}
	}
	private void addClient (String msg, JPanel clientsPanel) {
		String tmp[] = msg.split(":");
		if(!clients.containsKey(tmp[0]) && tmp[1].length() != 0 && !tmp[0].equals(clientSocket.toString())) {
			clients.put(tmp[0], tmp[1]);
			JToggleButton tmpButton = new JToggleButton(tmp[1]);
			clientsButtons.put(tmp[0], tmpButton);	
			clientsButtons.get(tmp[0]).setMinimumSize(new Dimension(1000, 20));
			clientsButtons.get(tmp[0]).setMaximumSize(new Dimension(1000, 20));
			clientsButtons.get(tmp[0]).setPreferredSize(new Dimension(100, 20));
			clientsPanel.add(clientsButtons.get(tmp[0]));
			clientsPanel.updateUI();
			outMessage.println("0:" + clientSocket + ":" + clientName + ":Добавление");
			outMessage.flush();
			jtaTextAreaMessage.append(tmp[1] + " присоединился!");
			jtaTextAreaMessage.append("\n");
		}
	}
	private void removeClient (String msg, JPanel clientsPanel) {
		String tmp[] = msg.split(":");
		clientsPanel.remove(clientsButtons.get(tmp[0]));
		clients.remove(tmp[0]);
		clientsButtons.remove(tmp[0]);
		clientsPanel.updateUI();
		jtaTextAreaMessage.append(tmp[1] + " вышел из чата!");
		jtaTextAreaMessage.append("\n");
	}
	private void logIn() {
		jtfName.setEditable(false);
		clientName = jtfName.getText();
		jtaTextAreaMessage.append(clientName + " присоединился!");
		jtaTextAreaMessage.append("\n");
		outMessage.println("0:" + clientSocket.toString() + ":" + clientName + ":Добавление");
		sendEmptymsg();
	}
	// отправка сообщения
	public void sendMsg() {
		String messageStr = jtfName.getText() + ": " + jtfMessage.getText();
		String msg = messageStr;
		messageStr = makeFullmsg(messageStr);
		outMessage.println(messageStr);
		outMessage.flush();
		//jtaTextAreaMessage.append("msg.length() = " + msg.length() + ";\nclientName.length() + 1 = " +  (clientName.length() + 2));
		if (msg.length() != clientName.length() + 2) {
			jtaTextAreaMessage.append(msg);
			jtaTextAreaMessage.append("\n");
		}
		jtfMessage.setText("Введите ваше сообщение: ");
	}
	public void sendEmptymsg()
	{
		jtfMessage.setText("");
		sendMsg();
	}
	private String makeFullmsg(String messageStr) {
		messageStr = (messageStr.length() + 1) + ":" + messageStr;
		messageStr += "<";
		for (Map.Entry<String, JToggleButton> client : clientsButtons.entrySet()) {
			if(!client.getValue().isSelected()) {
				messageStr += client.getKey() + " and ";
			}
		}
		messageStr += ">";
		return messageStr;
	}
}
	
