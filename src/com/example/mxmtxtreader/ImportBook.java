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

	// load txt file list in the dir
	private void initData(File[] files) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> lstDir = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				map1 = new HashMap<String, String>();
				map1.put("book_name", files[i].getName());
				map1.put("book_addr", files[i].getAbsolutePath());
				lstDir.add(map1);
			} else {
				String fileName = files[i].getName().toLowerCase();
				if (fileName.endsWith(".txt")) {
					map1 = new HashMap<String, String>();
					map1.put("book_name", files[i].getName());
					map1.put("book_addr", files[i].getAbsolutePath());
					list.add(map1);
				}
			}
		}

		ListView lv = (ListView) findViewById(R.id.lstBook);
		ListView lvDir = (ListView) findViewById(R.id.lstDir);
		//init list txt files
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.book_item_imp,
				new String[] { "book_name", "book_addr" }, new int[] {
						R.id.book_name, R.id.book_addr });
		lv.setAdapter(listAdapter);
		//init list dir
		SimpleAdapter lstDirAdapter = new SimpleAdapter(this, lstDir,
				R.layout.book_item_imp_dir,
				new String[] { "book_name", "book_addr" }, new int[] {
						R.id.book_name, R.id.book_addr });
		lvDir.setAdapter(lstDirAdapter);
	}

	private void initDir() {
		File rootPath = Environment.getExternalStorageDirectory();
		LinearLayout lay = (LinearLayout) findViewById(R.id.lay);
		TextView tv = new TextView(this);
		tv.setText(rootPath.getAbsolutePath());
		lay.addView(tv);

		if (rootPath.isDirectory()) {
			initData(rootPath.listFiles());
		} else {
			TextView vNofile = (TextView) findViewById(R.id.txtNofile);
			vNofile.setVisibility(View.VISIBLE);
		}
	}

	/* 检查是否为合法的文件名，或者是否为路径 */
	private boolean isValidFileOrDir(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String fileName = file.getName().toLowerCase();
			if (fileName.endsWith(".txt")) {
				return true;
			}
		}
		return false;
	}
}