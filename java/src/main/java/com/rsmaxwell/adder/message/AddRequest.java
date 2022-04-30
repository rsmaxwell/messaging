package com.rsmaxwell.adder.message;

import java.nio.ByteBuffer;

public class AddRequest extends AbstractMessageRequest {

	public static final int TYPE = 3;
	private int number1;
	private int number2;

	public AddRequest(int number1, int number2) {
		this.number1 = number1;
		this.number2 = number2;
	}

	@Override
	public byte type() {
		return TYPE;
	}

	@Override
	public int length() {
		return 2 * Integer.BYTES;
	}

	@Override
	public void write(ByteBuffer buffer) {
		buffer.putInt(number1);
		buffer.putInt(number2);
	}

	@Override
	public MessageReply perform() {
		int result = number1 + number2;
		return new AddReply(result);
	}

	@Override
	public String toString() {
		return Integer.toString(number1) + " + " + Integer.toString(number2);
	}
}
