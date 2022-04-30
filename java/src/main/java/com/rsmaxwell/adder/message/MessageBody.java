package com.rsmaxwell.adder.message;

public interface MessageBody extends Message {

	int length();

	byte type();
}
