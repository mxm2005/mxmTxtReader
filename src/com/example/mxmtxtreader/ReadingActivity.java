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
public class ReadingActivity extends BaseActivity {
	private int _pageSize = 900;
	private int _pageIndex = 1;
	private long _totalCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		InitData();
	}

	private void InitData() {
		Intent inite = getIntent();
		String bookName = inite.getStringExtra("book_name");
		String bookAddr = inite.getStringExtra("book_addr");
		Log.i("tag", "exec here now");
		TextView vContent = (TextView) findViewById(R.id.viewContent);
		try {
			String enCoding = FileService.GetEncoding(bookAddr);
			String data = FileService.readPage(bookAddr, _pageSize, _pageIndex,
					enCoding);
			vContent.setText(data);
		} catch (IOException e) {
			System.out.print("Write Exception\n");
			Log.e("ex", e.getMessage() + e.getCause() + e.getStackTrace());
		}

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

}
