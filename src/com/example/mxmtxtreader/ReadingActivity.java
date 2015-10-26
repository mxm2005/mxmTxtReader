package com.example.mxmtxtreader;

import java.io.IOException;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mxmtxtreader.file.FileService;
import com.example.mxmtxtreader.component.ReadingTextView;
import com.example.mxmtxtreader.util.viewmeasurement;
import com.example.mxmtxtreader.util.viewmeasurement.meaview;

@SuppressLint("ShowToast")
public class ReadingActivity extends BaseActivity implements OnClickListener {
	private String mBookAddr;
	private String encoding;

	private int mPageSize = 1200;
	private int mPageIndex = 1;
	private long mTotalCount = 0;
	private int mTotalPage = 0;
	ReadingTextView vContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_book);

		initView();
		// testScreen();
		InitData();
	}

	// Implement the OnClickListener callback
	public void onClick(View v) {
		// vContent.append("目前还差短信发送及人员配置");
		// Toast.makeText(getApplicationContext(),
		// ss,Toast.LENGTH_SHORT).show();
		if (v.getId() == R.id.txtNextChapter) {// next chapter
			mPageIndex++;
			GetPageContent(mBookAddr);
		}
		if (v.getId() == R.id.txtPreChapter) {// last chapter
			mPageIndex--;
			if (mPageIndex <= 0)
				mPageIndex = 1;
			GetPageContent(mBookAddr);
		}
	}

	void initView() {
		vContent = (ReadingTextView) findViewById(R.id.viewContent);
		TextView btnNext = (TextView) findViewById(R.id.txtNextChapter);
		btnNext.setOnClickListener(this);
		((TextView) findViewById(R.id.txtPreChapter)).setOnClickListener(this);

		mPageSize = getPageSize();
	}

	private void InitData() {
		Intent inite = getIntent();
		String bookName = inite.getStringExtra("book_name");
		mBookAddr = inite.getStringExtra("book_addr");
		try {
			encoding = FileService.GetEncoding(mBookAddr);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("ex", e.getMessage() + e.getCause() + e.getStackTrace());
		}
		GetPageContent(mBookAddr);

		TextView vCurChapter = (TextView) findViewById(R.id.txtCurChapter);
		vCurChapter.setText(bookName);
	}

	private void GetPageContent(String bookAddr) {
		try {
			String data = FileService.readPage(bookAddr, mPageSize, mPageIndex,
					encoding);
			vContent.setText(data);
		} catch (IOException e) {
			Log.e("ex", e.getMessage() + e.getCause() + e.getStackTrace());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	int getPageSize() {
		int size = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 100; i++) {
			sb.append("测");
		}
		vContent.setText(sb.toString());
		vContent.measure(0, 0);
		meaview v = viewmeasurement.measure(vContent, sb.toString());
		// v.width = vContent.getWidth();
		// v.height = vContent.getHeight();
		float[] arr = testScreen();
		v.width = arr[0];
		v.height = arr[1] - 22;
		Log.d("custom", "width--" + v.width + "\r\nheight--" + v.height);
		Log.d("custom", "parent width--" + 0 + "\r\nheight--" + v.height);
		// v = viewmeasurement.measure(vContent, tmp);

		float lineCount = v.width
				/ (vContent.getLineHeight() + vContent.getScaleX());
		float lineWords = v.height
				/ (vContent.getTextSize() + vContent.getScaleY());
		size = ((int) lineCount * (int) lineWords);
		return size;
	}

	float[] testScreen() {
		float[] reVal = new float[2];
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels;
		reVal[0] = width;
		reVal[1] = height;
		Log.d("custom", "screen:" + width + "--" + height);
		return reVal;
	}
}
