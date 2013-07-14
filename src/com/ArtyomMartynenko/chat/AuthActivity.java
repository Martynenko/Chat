package com.ArtyomMartynenko.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.ArtyomMartynenko.chat.Api.ApiException;
import com.ArtyomMartynenko.chat.R.menu;

public class AuthActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_auth);
		findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickEnter(v);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auth, menu);
		return true;
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	//
	// switch (item.getItemId()) {
	// case R.id.forget_Pass:
	// Toast.makeText(this, "Forgot your password? Try agane",
	// Toast.LENGTH_SHORT).show();
	// return true;
	// case R.id.aboutApp:
	// Intent i = new Intent(this, menu.class);
	// startActivity(i);
	// // AlertDialog ad = new AlertDialog.Builder(this).
	//
	// return true;
	// case R.id.quit:
	// new Handler().postDelayed(new Runnable() {
	//
	// @Override
	// public void run() {
	// quit();
	// }
	// }, 2000);
	// default:
	// return super.onOptionsItemSelected(item);
	// }
	// }

	private void quit() {
		finish();
	}

	public void onClickEnter(View view) {
		EditText Email = (EditText) findViewById(R.id.email);
		EditText Password = (EditText) findViewById(R.id.password);

		mCore.getApi().auth(Email.getText().toString(), Password.getText().toString(), new Api.AuthCallback() {

			@Override
			public void onAuthCallbackSucces() {
				// TODO Auto-generated method stub
				Intent i = new Intent(AuthActivity.this, RoomsActivity.class);
				startActivity(i);
				finish();

			}

			@Override
			public void onAuthCallbackFail(String message) {
				// TODO Auto-generated method stub

				Toast.makeText(AuthActivity.this, message, Toast.LENGTH_LONG).show();
			}
		});
		// Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onConnectedToService() {
		// TODO Auto-generated method stub

	}
}