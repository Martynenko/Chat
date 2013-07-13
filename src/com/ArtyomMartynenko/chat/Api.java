package com.ArtyomMartynenko.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.ArtyomMartynenko.chat.AuthInfo;
import com.ArtyomMartynenko.chat.Parser;
import com.ArtyomMartynenko.chat.Person;
import com.ArtyomMartynenko.chat.Api.ApiException;

public class Api {

	private AuthInfo mAuthInfo;
	private boolean isAuth = false;

	public void reg(Person p) {
		connect(BASE_URL+"reg?" + p.mEmail + "&pass=" + p.mPass + "&sex=" + p.mSex + "&age=" + p.mAge + "&nick=" + p.mNickname);

	}

	public void auth(String email, String pass) throws ApiException {
		if (isAuth == false) {

			try {
				String resp = connect(BASE_URL +"auth?email=" + email + "&pass=" + pass);
				mAuthInfo = Parser.Auth(resp);
				isAuth = true;
			} catch (Exception e) {
				throw new ApiException(e);

			}
		} else {
			throw new ApiException("Already logged in!");
		}
	}

	public static String connect(String url) {

		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			// Examine the response status
			Log.i("Praeda", response.getStatusLine().toString());

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				// now you have the string representation of the HTML request
				instream.close();
				return result;
			}
			throw new RuntimeException();

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	class ApiException extends Exception {
		ApiException(Throwable t) {
			super(t);
		}

		ApiException(String t) {
			super(t);
		}
	}

	private static final String BASE_URL = "http://10.2.1.38:6606/";
	
//	public List<Room> getRooms(){
//		connect (BASE_URL+"rooms?token="+mAuthInfo.mToken);
//		List<Room>list = new ArrayList<Room>
//		String jsonResp=connect("http://10.2.1.10:6606/");
//		Parser.getRooms(jsonResp,list);
//		return list;
//		}
}
