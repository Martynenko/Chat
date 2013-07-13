package com.ArtyomMartynenko.chat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.ArtyomMartynenko.chat.R;
import com.ArtyomMartynenko.chat.Room;
import com.ArtyomMartynenko.chat.RoomsAdapter;
import com.ArtyomMartynenko.chat.RoomsActivity;
import com.ArtyomMartynenko.chat.Room.Status;

public class RoomsActivity extends BaseActivity {
	private RoomsAdapter mAdapter;
	public final static List<Room> sRooms = new ArrayList<Room>();
	static {
		sRooms.add(new Room("Main Chat").setPeopleCount(9).setStatus(Room.Status.ok));
		sRooms.add(new Room("Pogrammers Chat").setPeopleCount(3).setStatus(Room.Status.ok));
		sRooms.add(new Room("LOL Chat").setPeopleCount(0).setStatus(Room.Status.banned));
	}
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_rooms);
	    
	    ListView listview=(ListView)findViewById(R.id.list);
	    
	    final String [] items = new String[] {"One","Two","Three","Four"};
	    mAdapter = new RoomsAdapter(this,sRooms);
	    listview.setAdapter(mAdapter);
	    listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long itemId) {
				// TODO Auto-generated method stub
				//Toast.makeText(rooms_activity.this, items[position], Toast.LENGTH_SHORT).show();
			}
		});
	    listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View v,
					int position, long itemId) {
			//	Toast.makeText(rooms_activity.this, items[position]+"LONGPRESSS", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.rooms_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item ){
		switch(item.getItemId()){
		case R.id.menu_rooms_add:
			showDialog(1);
			return true;
		default:	
			return super.onOptionsItemSelected(item);
		}
	}
	public Dialog onCreateDialog(int id){
		if(1==id){
			return newAddRoomDialog();
		}
		return super.onCreateDialog(id);
	}
	private Dialog newAddRoomDialog(){
		AlertDialog.Builder builder;
		
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Adding new room");
		final View view = LayoutInflater.from(this).inflate(R.layout.dialog_room_add, null);
		builder.setView(view);
		builder.setPositiveButton("Submit", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO auto-generated method stub
				String name= ((EditText)view.findViewById(R.id.dialog_rooms_add_name)).getText().toString();
				if (name==null || name.trim().length()==0){
					//ошибка о вводе имени
					Toast.makeText(RoomsActivity.this, "Please enter room name",Toast.LENGTH_SHORT).show();
				}
				else{
					String desc= ((EditText)view.findViewById(R.id.dialog_rooms_add_description)).getText().toString();
					if (desc==null || desc.trim().length()==0){
						Toast.makeText(RoomsActivity.this, "Please enter room desciption", Toast.LENGTH_SHORT);
					}
					else {
						Room r = new Room(name);
						r.setPeopleCount(1).setStatus(Status.inside);
						sRooms.add(r);
						mAdapter.notifyDataSetChanged();
					}
				}
			}
		});
		builder.setNegativeButton("Cancel", null);
		return builder.create();
	}
	@Override
	protected void onConnectedToService() {
		// TODO Auto-generated method stub
		
	}

}
