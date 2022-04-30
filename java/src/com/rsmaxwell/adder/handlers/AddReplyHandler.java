package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.rsmaxwell.adder.message.AddReply;
import com.rsmaxwell.adder.message.Message;

public class AddReplyHandler extends MessageReplyHandler {

	@Override
	public Message read(InputStream in, int length) throws IOException {
		byte[] bytes = readBytes(in, length);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		int number = buffer.getInt();
		return new AddReply(number);
	}
}
