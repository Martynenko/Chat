package com.ArtyomMartynenko.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter{
	private final Context mC;
	private final List<Message> mL;
	MessageAdapter(Context c, List <Message> l){
		mC=c;
		mL=l;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mL.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		
		return  mL.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View maskedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Message item = mL.get(position);
		if(maskedView==null){
			maskedView=LayoutInflater.from(mC).inflate(R.layout.message_row, null);
		}
		if(position%2==1){
			maskedView.setBackgroundColor(0x66999999);
		}
		else{
			maskedView.setBackgroundColor(0x66555555);
		}
		((TextView)maskedView.findViewById(R.id.message_row_nick)).setText(item.getSender());
		((TextView)maskedView.findViewById(R.id.message_row_time)).setText("time: "+item.mTime);
		((TextView)maskedView.findViewById(R.id.message_row_msg)).setText(item.getText());
		
		return maskedView;
	}

}
