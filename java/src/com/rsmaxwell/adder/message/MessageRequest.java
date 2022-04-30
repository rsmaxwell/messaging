package com.rsmaxwell.adder.message;

public interface MessageRequest extends MessageBody {

	MessageReply perform();
}
