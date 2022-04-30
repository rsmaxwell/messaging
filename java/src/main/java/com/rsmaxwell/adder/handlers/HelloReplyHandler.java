package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.rsmaxwell.adder.message.HelloReply;
import com.rsmaxwell.adder.message.Message;

public class HelloReplyHandler extends MessageReplyHandler {

	@Override
	public Message read(InputStream in, int length) throws IOException {
		byte[] bytes = readBytes(in, length);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		int number = buffer.getInt();
		return new HelloReply(number);
	}
}
