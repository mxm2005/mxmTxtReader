package com.example.mxmtxtreader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.mxmtxtreader.file.FileService;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ImportBook extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.import_book);

		initDir();
	}
	
	//load txt file list in the dir
	private void initData(File[] files) {
		String[] arrBook = new String[20];
		for (int i = 0; i < 20; i++) {
			arrBook[i] = "BOOK--" + i;
		}
		ListView lv = (ListView) findViewById(R.id.lstBook);

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();

		ArrayList<String> lstBook = new ArrayList<String>();
		try {
			lstBook = FileService.readList(this.getResources().openRawResource(
					R.drawable.booklist));
		} catch (Exception ex) {
			Log.i("exception", ex.getMessage() + ex.getCause());
		}

		for (int i = 0; i < lstBook.size(); i++) {
			map1 = new HashMap<String, String>();
			String[] a = lstBook.get(i).split(",");
			map1.put("book_name", a[0]);
			map1.put("book_addr", a[1]);
			list.add(map1);
		}

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.book_item_imp,
				new String[] { "book_name", "book_addr" }, new int[] {
						R.id.book_name, R.id.book_addr });
		lv.setAdapter(listAdapter);
	}

	private void initDir() {
		File rootPath=Environment.getExternalStorageDirectory();
		LinearLayout lay=(LinearLayout)findViewById(R.id.lay);
		TextView tv=new TextView(this);
		tv.setText(rootPath.getAbsolutePath());
		lay.addView(tv);
		
		if(rootPath.isDirectory()){
			initData(rootPath.listFiles());
		}
		else{
			TextView vNofile=(TextView)findViewById(R.id.txtNofile);
			vNofile.setVisibility(View.VISIBLE);
		}
	}
}