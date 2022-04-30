package com.rsmaxwell.adder.message;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class MessageHeader extends AbstractMessage {

	public static final int LENGTH = 2 * Integer.BYTES;
	private int type;
	private int length;

	public MessageHeader(int messageType, int messageLength) {
		this.type = messageType;
		this.length = messageLength;
	}

	public int type() {
		return type;
	}

	public int length() {
		return length;
	}

	@Override
	public void write(ByteBuffer buffer) {
		buffer.putInt(type);
		buffer.putInt(length);
	}

	@Override
	public void write(OutputStream out) throws IOException {

		byte[] bytes = new byte[LENGTH];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		write(buffer);

		out.write(bytes, 0, buffer.position());
	}
}
