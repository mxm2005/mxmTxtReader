package com.example.mxmtxtreader;

import com.example.mxmtxtreader.fragment.YesNoDialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent inite = new Intent();
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_openbook:
			inite.setClass(this.getApplicationContext(), ImportBook.class);
			startActivity(inite);
			return true;
		case R.id.menu_import:
			inite.setClass(this.getApplicationContext(), ImportBook.class);
			startActivity(inite);
			return true;
		case R.id.menu_settings:
			DialogFragment dialog = new YesNoDialog();
			Bundle args = new Bundle();
			args.putString("title", "test title");
			args.putString("message", "test messqge");
			dialog.setArguments(args);
			int YES_NO_CALL = 101;
			dialog.setTargetFragment(dialog, YES_NO_CALL);
			dialog.show(getFragmentManager(), "tag");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
