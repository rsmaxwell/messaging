package com.rsmaxwell.adder.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.rsmaxwell.adder.message.MessageReply;

public abstract class MessageReplyHandler extends Handler {

	public MessageReply readMessageReply(InputStream in, int length) throws IOException {
		return (MessageReply) read(in, length);
	}
}
