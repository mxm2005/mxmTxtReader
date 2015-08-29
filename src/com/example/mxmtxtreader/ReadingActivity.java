package com.example.mxmtxtreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ReadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText("HELLO!");

		Intent inte = this.getIntent();
		String bookName = inte.getStringExtra("book_name");
		String bookAddr = inte.getStringExtra("book_addr");
		Log.i("---", bookName + "..." + bookAddr);
	}

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
		int id = item.getItemId();
		if (id == R.id.menu_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
