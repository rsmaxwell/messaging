package com.rsmaxwell.adder.message;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public abstract class AbstractMessageBody extends AbstractMessage implements MessageBody {

	@Override
	public void write(OutputStream out) throws IOException {
		byte[] bytes = new byte[length()];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		write(buffer);
		out.write(bytes, 0, buffer.limit());
	}
}
