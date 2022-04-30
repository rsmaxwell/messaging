package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.rsmaxwell.adder.message.Message;
import com.rsmaxwell.adder.message.MessageHeader;

public class MessageHeaderHandler extends Handler {

	@Override
	public Message read(InputStream in, int length) throws IOException {
		byte[] bytes = readBytes(in, length);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		int messageType = buffer.getInt();
		int messageLength = buffer.getInt();
		return new MessageHeader(messageType, messageLength);
	}

	public MessageHeader readMessageHeader(InputStream in) throws IOException {
		return (MessageHeader) read(in, MessageHeader.LENGTH);
	}
}
