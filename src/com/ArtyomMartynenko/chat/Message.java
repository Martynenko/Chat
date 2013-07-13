package com.ArtyomMartynenko.chat;

public class Message {

	private String mSender;
	private String mReceiver;
	private String mText;

	public final long mTime;

	public String getSender() {
		return mSender;
	}

	public void setSender(String sender) {
		this.mSender = sender;
	}

	public String getReceiver() {
		return mReceiver;
	}

	public void setReceiver(String receiver) {
		this.mReceiver = receiver;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		this.mText = text;
	}
	
	public Message(String sender, String receiver, String text)
	{
		mSender= sender;
		mReceiver= receiver;
		mText= text;
		mTime= System.currentTimeMillis();
		
	}

}
