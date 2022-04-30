package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.rsmaxwell.adder.message.AddRequest;
import com.rsmaxwell.adder.message.Message;

public class AddRequestHandler extends MessageRequestHandler {

	@Override
	public Message read(InputStream in, int length) throws IOException {
		byte[] bytes = readBytes(in, length);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		int messageType = buffer.getInt();
		int messageLength = buffer.getInt();
		return new AddRequest(messageType, messageLength);
	}
}
