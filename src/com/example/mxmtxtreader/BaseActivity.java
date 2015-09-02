package com.example.mxmtxtreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BaseActivity extends Activity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	private class myOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// String text = parent.getItemAtPosition(position)+"";
			// Log.i("tag",text);
			TextView vName = (TextView) v.findViewById(R.id.book_name);
			TextView vAddr = (TextView) v.findViewById(R.id.book_addr);
			Log.i("tag",vName.getText().toString()+"--"+vAddr.getText().toString());

			Intent inte = new Intent();
			inte.setClass(BaseActivity.this, ReadingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("book_name", vName.getText().toString());
			bundle.putString("book_addr", vAddr.getText().toString());
			inte.putExtras(bundle);
			startActivity(inte);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
