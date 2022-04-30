package com.rsmaxwell.adder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.rsmaxwell.adder.handlers.AddReplyHandler;
import com.rsmaxwell.adder.handlers.HelloReplyHandler;
import com.rsmaxwell.adder.handlers.MessageHeaderHandler;
import com.rsmaxwell.adder.handlers.MessageReplyHandler;
import com.rsmaxwell.adder.message.AddReply;
import com.rsmaxwell.adder.message.AddRequest;
import com.rsmaxwell.adder.message.HelloReply;
import com.rsmaxwell.adder.message.HelloRequest;
import com.rsmaxwell.adder.message.MessageHeader;
import com.rsmaxwell.adder.message.MessageReply;
import com.rsmaxwell.adder.message.MessageRequest;

public class AdderClient {

	private static Map<Integer, MessageReplyHandler> handlers = new HashMap<Integer, MessageReplyHandler>();

	static {
		handlers.put(HelloReply.TYPE, new HelloReplyHandler());
		handlers.put(AddReply.TYPE, new AddReplyHandler());
	}

	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println("Usage: java AdderClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (Socket kkSocket = new Socket(hostName, portNumber);
				OutputStream out = kkSocket.getOutputStream();
				InputStream in = kkSocket.getInputStream();
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

			MessageRequest request = new HelloRequest("Richard");
			System.out.println(request.getClass().getSimpleName() + ": " + request);
			MessageHeader header = new MessageHeader(request.type(), request.length());
			header.write(out);
			request.write(out);

			MessageHeaderHandler headerHandler = new MessageHeaderHandler();
			header = headerHandler.readMessageHeader(in);
			while (header != null) {
				MessageReplyHandler handler = handlers.get(header.type());
				MessageReply reply = handler.readMessageReply(in, header.length());
				System.out.println(reply.getClass().getSimpleName() + ": " + reply.toString());

				String userInput = stdIn.readLine();
				String[] words = userInput.split(" ");
				if (words.length != 2) {
					System.out.println("Unexpected user input");
					break;
				}

				String word1 = words[0];
				int number1 = 0;
				try {
					number1 = Integer.parseInt(word1);
				} catch (NumberFormatException e) {
					System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
					break;
				}

				String word2 = words[0];
				int number2 = 0;
				try {
					number2 = Integer.parseInt(word2);
				} catch (NumberFormatException e) {
					System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
					break;
				}

				request = new AddRequest(number1, number2);
				header = new MessageHeader(request.type(), request.length());
				header.write(out);
				request.write(out);
				System.out.println(request.getClass().getSimpleName() + ": " + request);

				header = headerHandler.readMessageHeader(in);
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}