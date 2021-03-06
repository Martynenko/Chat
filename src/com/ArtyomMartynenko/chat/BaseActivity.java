package com.ArtyomMartynenko.chat;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public abstract class BaseActivity extends Activity {
	protected ChatService mCore;
	private ServiceConnection mSrvConn;
	private Intent mIntent;

	protected void onCreate(Bundle b) {
		super.onCreate(b);
		mIntent = new Intent();
		mIntent.setClass(this, ChatService.class);
		startService(mIntent);
		mSrvConn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				finish();

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub

				mCore = ((ChatService.SelfBinder) service).srv;
			}
		};
		bindService(mIntent, mSrvConn, Service.BIND_AUTO_CREATE);
	}
	
	public void onDestroy(){
		unbindService(mSrvConn);
		super.onDestroy();
		
		
	}
	abstract protected void onConnectedToService();
	
	protected final void stopSystem(){
		stopService(mIntent);
		
	}
	
}
// showDialog(1);
//dismisDialog(1);
//public void showDialogWait(){
//	showDialog(1);
//}
//public Dialog on CreateDialog (int id){
//	if (1==id){
//		
//	}
//}
