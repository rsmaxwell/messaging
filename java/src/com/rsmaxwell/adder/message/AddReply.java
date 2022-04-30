package com.rsmaxwell.adder.message;

import java.nio.ByteBuffer;

public class AddReply extends AbstractMessageReply {

	public static final int TYPE = 4;
	private int number;

	public AddReply(int number) {
		this.number = number;
	}

	@Override
	public byte type() {
		return TYPE;
	}

	@Override
	public int length() {
		return Integer.BYTES;
	}

	@Override
	public void write(ByteBuffer buffer) {
		buffer.putInt(number);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}
}
