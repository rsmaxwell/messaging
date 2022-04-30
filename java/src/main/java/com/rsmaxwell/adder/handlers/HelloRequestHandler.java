package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.rsmaxwell.adder.message.HelloRequest;
import com.rsmaxwell.adder.message.Message;

public class HelloRequestHandler extends MessageRequestHandler {

	@Override
	public Message read(InputStream in, int length) throws IOException {
		byte[] bytes = readBytes(in, length);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		int bytelength = buffer.getInt();

		byte[] namebytes = new byte[bytelength];
		buffer.get(namebytes, 0, bytelength);

		String name = new String(namebytes, StandardCharsets.UTF_8);
		return new HelloRequest(name);
	}
}
