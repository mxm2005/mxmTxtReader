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
import com.example.mxmtxtreader.component.ReadingTextView;

@SuppressLint("ShowToast")
public class ReadingActivity extends BaseActivity {
	private int mPageSize = 0;
	private int mPageIndex = 1;
	private long mTotalCount = 0;
	private long mTotalPage = 0;

	private ReadingTextView vContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		initView();
		InitData();
	}

	void initView() {
		vContent = (ReadingTextView) findViewById(R.id.viewContent);
		vContent=new ReadingTextView(this);
		mPageSize = getPageSize();
	}

	private void InitData() {
		Intent inite = getIntent();
		String bookName = inite.getStringExtra("book_name");
		String bookAddr = inite.getStringExtra("book_addr");
		Log.i("tag", "exec here now");
		try {
			FileInputStream os = new FileInputStream(bookAddr);
			

			mTotalCount = FileService.getCount(bookAddr);
			//mTotalPage = mTotalCount / mPageSize;
			
			String data = FileService.read(os);
			os.close();

			((TextView) findViewById(R.id.btnPreChapter)).setText(mTotalCount
					+ "");
			vContent.setText(data);

			Log.d("custom", "totalW---" + mTotalCount + "--totalP---"
					+ mTotalPage + "--size---" + mPageSize);

		} catch (IOException e) {
			System.out.print("Write Exception\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

	int getPageSize() {
//		int size = 0;
//		int lineCount = vContent.getHeight() / vContent.getLineHeight();
//		float lineWords = vContent.getWidth() / vContent.getTextSize();
//		size = lineCount * ((int) lineWords);
//		return size;
		return vContent.getEstimatedLength();
	}

}
