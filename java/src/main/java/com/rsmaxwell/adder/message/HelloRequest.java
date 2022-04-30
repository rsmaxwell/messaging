package com.rsmaxwell.adder.message;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HelloRequest extends AbstractMessageRequest {

	public static final int TYPE = 1;
	private byte[] name;

	public HelloRequest(String name) {
		this.name = name.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public byte type() {
		return TYPE;
	}

	@Override
	public int length() {
		return Integer.BYTES + name.length;
	}

	@Override
	public void write(ByteBuffer buffer) {
		buffer.putInt(name.length);
		buffer.put(name);
	}

	@Override
	public MessageReply perform() {
		return new HelloReply(0);
	}

	@Override
	public String toString() {
		return new String(name);
	}
}
