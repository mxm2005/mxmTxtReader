package com.example.mxmtxtreader;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mxmtxtreader.file.FileService;

@SuppressLint("ShowToast")
public class ReadingActivity extends BaseActivity implements OnClickListener {
	private int _pageSize = 1200;
	private int _pageIndex = 1;
	private long _totalCount = 0;
	TextView vContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		vContent = (TextView) findViewById(R.id.viewContent);
		InitData();
		TextView btnNext = (TextView) findViewById(R.id.txtNextChapter);
		btnNext.setOnClickListener(this);
	}

	// Implement the OnClickListener callback
	public void onClick(View v) {
		vContent.append("目前还差短信发送及人员配置");
		// Toast.makeText(getApplicationContext(),
		// ss,Toast.LENGTH_SHORT).show();

		InitScreen();
	}

	private void InitData() {
		Intent inite = getIntent();
		String bookName = inite.getStringExtra("book_name");
		String bookAddr = inite.getStringExtra("book_addr");
		Log.i("tag", "exec here now");

		try {
			String enCoding = FileService.GetEncoding(bookAddr);
			String data = FileService.readPage(bookAddr, _pageSize, _pageIndex,
					enCoding);
			long len = 0;
			len = FileService.getCount(bookAddr);
			((TextView) findViewById(R.id.txtPreChapter)).setText(len + "");
			vContent.setText(data);

			InitScreen();
		} catch (IOException e) {
			System.out.print("Write Exception\n");
			Log.e("ex", e.getMessage() + e.getCause() + e.getStackTrace());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

	private void InitScreen() {
		Layout l = vContent.getLayout();
		if (l != null) {
			int lines = l.getLineCount();
			Log.i("line", ""+lines);
			
			if (lines > 0)
				if (l.getEllipsisCount(lines - 1) > 0)
					Log.d("screen", "Text is ellipsized");
		}
	}

}
