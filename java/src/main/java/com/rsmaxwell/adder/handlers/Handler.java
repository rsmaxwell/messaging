package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.rsmaxwell.adder.message.Message;
import com.rsmaxwell.adder.message.MessageBody;

public abstract class Handler {

	public MessageBody readMessageBody(InputStream in, int length) throws IOException {
		return (MessageBody) read(in, length);
	}

	public byte[] readBytes(InputStream in, int length) throws IOException {
		byte[] bytes = new byte[length];
		in.read(bytes, 0, length);
		return bytes;
	}

	abstract public Message read(InputStream in, int length) throws IOException;

}
