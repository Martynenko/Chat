package com.ArtyomMartynenko.chat;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ArtyomMartynenko.chat.AuthInfo;
import com.ArtyomMartynenko.chat.Parser.ParserException;

public class Parser {
	public static AuthInfo Auth(String jsonString) throws ParserException {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getString("status").equals("ok")) {
				return new AuthInfo(json.getString("token"), json.getString("nick"));
			} else {
				throw new ParserException("D'OH!!! Wrong Email or Password.");
			}
		} catch (JSONException e) {
			throw new ParserException(e);
		}

	}

	static class ParserException extends Exception {
		public final int errCode;

		ParserException(int errCode) {
			this.errCode = errCode;
		}

		ParserException(String message) {
			super(message);
			errCode = -1;
		}

		ParserException(Throwable t) {
			super(t);
			errCode = -1;
		}
	}
//
//	public static void getRooms(String jsonString, List<Room> list) throw ParserException {
//		try {
//        JSONObject json = new JSONObject(jsonString);
//		JSONArray	arr = json.getJSONArray("rooms");
//		for (int i = 0; i < arr.length(); ++i) {
//			JSONObject j = arr.getJSONObject(i);
//		}
//	}

}
