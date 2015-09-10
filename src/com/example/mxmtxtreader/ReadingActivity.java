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
	private int _pageSize = 0;
	private int _pageIndex = 0;
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
			FileInputStream os = new FileInputStream(bookAddr);
			String data = FileService.read(os);
			os.close();

			long len = 0;
			len = FileService.getCount(bookAddr);
			((TextView) findViewById(R.id.btnPreChapter)).setText(len + "");
			vContent.setText(data);
			System.out.println("Created file\n");
		} catch (IOException e) {
			System.out.print("Write Exception\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

}
