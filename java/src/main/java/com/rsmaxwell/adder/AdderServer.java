package com.rsmaxwell.adder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.rsmaxwell.adder.handlers.AddRequestHandler;
import com.rsmaxwell.adder.handlers.HelloRequestHandler;
import com.rsmaxwell.adder.handlers.MessageHeaderHandler;
import com.rsmaxwell.adder.handlers.MessageRequestHandler;
import com.rsmaxwell.adder.message.AddRequest;
import com.rsmaxwell.adder.message.HelloRequest;
import com.rsmaxwell.adder.message.MessageHeader;
import com.rsmaxwell.adder.message.MessageReply;
import com.rsmaxwell.adder.message.MessageRequest;

public class AdderServer {

	private static Map<Integer, MessageRequestHandler> handlers = new HashMap<Integer, MessageRequestHandler>();

	static {
		handlers.put(HelloRequest.TYPE, new HelloRequestHandler());
		handlers.put(AddRequest.TYPE, new AddRequestHandler());
	}

	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: java AdderServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				OutputStream out = clientSocket.getOutputStream();
				InputStream in = clientSocket.getInputStream();) {

			MessageHeaderHandler headerHandler = new MessageHeaderHandler();
			MessageHeader header = headerHandler.readMessageHeader(in);
			while (header != null) {
				MessageRequestHandler handler = handlers.get(header.type());
				MessageRequest request = handler.readMessageRequest(in, header.length());
				System.out.println(request.getClass().getSimpleName() + ": " + request);
				MessageReply reply = request.perform();
				System.out.println(reply.getClass().getSimpleName() + ": " + reply);

				header = new MessageHeader(reply.type(), reply.length());
				header.write(out);
				reply.write(out);

				header = headerHandler.readMessageHeader(in);
			}

		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
