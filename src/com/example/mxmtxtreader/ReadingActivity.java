package com.example.mxmtxtreader;

import java.io.FileInputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mxmtxtreader.file.FileService;


@SuppressLint("ShowToast")
public class ReadingActivity extends Activity {
	private int _pageSize = 0;
	private int _pageIndex = 0;
	private long _totalCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		InitData();
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

	private void InitData() {
		Intent inite = getIntent();
		String bookName = inite.getStringExtra("book_name");
		String bookAddr = inite.getStringExtra("book_addr");
		Log.i("tag", "exec here now");
		TextView vContent = (TextView) findViewById(R.id.viewContent);
		try {
			FileInputStream os = new FileInputStream(bookAddr);			
			String data = FileService.read(os);
			os.close();
			vContent.setText(data);
			System.out.println("Created file\n");
		} catch (IOException e) {
			System.out.print("Write Exception\n");
		}

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

}
