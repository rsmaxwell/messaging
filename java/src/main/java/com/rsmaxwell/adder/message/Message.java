package com.rsmaxwell.adder.message;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public interface Message {

	void write(ByteBuffer buffer);

	void write(OutputStream out) throws IOException;
}
