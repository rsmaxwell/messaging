package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.rsmaxwell.adder.message.MessageRequest;

public abstract class MessageRequestHandler extends Handler {

	public MessageRequest readMessageRequest(InputStream in, int length) throws IOException {
		return (MessageRequest) read(in, length);
	}
}
