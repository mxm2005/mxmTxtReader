package com.example.mxmtxtreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.example.mxmtxtreader.file.FileService;
import com.example.mxmtxtreader.util.iniconfig;
import com.example.mxmtxtreader.adapter.MultiLayoutSimpleAdapter;
import com.example.mxmtxtreader.adapter.bookAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ImportBook extends BaseActivity {
	TextView vNav;
	TextView vNofile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.import_book);

		vNav = (TextView) findViewById(R.id.txtNav);
		vNofile = (TextView) findViewById(R.id.txtNofile);

		// init btnImport onClick
		Button btnImport = (Button) findViewById(R.id.btnImport);
		btnImport.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int showTip = -1;
				ListView lv = (ListView) findViewById(R.id.lstBook);
				ArrayList<String> lstBook = new ArrayList<String>();

				int count = lv.getChildCount();
				String tmp = "";
				for (int i = 0; i < count; i++) {
					CheckBox chb = (CheckBox) lv.getChildAt(i).findViewById(
							R.id.chkBook);
					if (chb != null && chb.isChecked()) {
						tmp = ((TextView) lv.getChildAt(i).findViewById(
								R.id.book_name)).getText().toString()
								+ ","
								+ ((TextView) lv.getChildAt(i).findViewById(
										R.id.book_addr)).getText().toString();
						lstBook.add(tmp);
					}
				}

				showTip = btnImportClick(lstBook);

				// show result
				Toast.makeText(getApplicationContext(), showTip,
						Toast.LENGTH_SHORT).show();
//				startActivity(new Intent(getApplicationContext(),
//						BookActivity.class));
				finish();
			}

		});

		// init dir onClick
		ListView lvDir = (ListView) findViewById(R.id.lstBook);
		lvDir.setOnItemClickListener(dirClickListener);

		// init btnPreView onClick
		Button btnPreView = (Button) findViewById(R.id.btnPreView);
		LinearLayout lbtnPreView = (LinearLayout) findViewById(R.id.lbtnPreView);
		btnPreView.setOnClickListener(btnPreViewClick);
		lbtnPreView.setOnClickListener(btnPreViewClick);

		initDir();
	}

	private int btnImportClick(ArrayList<String> lstBook) {
		int showTip;
		// write to booklist.txt
		ArrayList<String> lstIni = new ArrayList<String>();
		ArrayList<String> lstOldBook = new ArrayList<String>();
		try {
			lstIni = FileService.readList(getResources().openRawResource(
					R.drawable.init));

			String path = iniconfig.getInstance().GetItem(lstIni,
					"book_list_path");
			path = getFilesDir() + File.separator + path;
			InputStream input = new FileInputStream(path);
			lstOldBook = FileService.readList(input);
			input.close();
			
			//merger array
			for (String str:lstBook) {
				if (!lstOldBook.contains(str))
					lstOldBook.add(str);
			}

			String ss = "";
			for (int i = 0; i < lstOldBook.size(); i++) {
					ss += lstOldBook.get(i);
				if (i != lstOldBook.size() - 1)
					ss += "\r\n";
			}

			BufferedWriter os = new BufferedWriter(new FileWriter(path, false));
			os.write(ss);
			os.close();

			showTip = R.string.show_ok;
			Log.i("btnImport", "---" + ss);
		} catch (Exception ex) {
			Log.i("exception", ex.getMessage() + ex.getCause());
			showTip = R.string.show_except;
		}
		return showTip;
	}

	OnItemClickListener dirClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View v, int arg1, long arg2) {
			TextView tv = (TextView) v.findViewById(R.id.book_addr);
			File f = new File(tv.getText().toString());
			if (f.isDirectory()) {
				File[] fs = f.listFiles();

				if (fs == null || fs.length == 0) {
					vNofile.setVisibility(View.VISIBLE);
				} else {
					vNofile.setVisibility(View.INVISIBLE);
				}
				initData(fs);
			}

			// init navigation bar
			vNav.setText(f.getAbsolutePath());
		}
	};

	OnClickListener btnPreViewClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String sPath = "";
			String oPath = vNav.getText().toString();

			if (TextUtils.lastIndexOf(oPath, '/') > 0) {
				sPath = oPath.substring(0, TextUtils.lastIndexOf(oPath, '/'));
				judgFile(sPath);
			}
		}
	};

	private void judgFile(String sPath) {
		vNav.setText(sPath);

		File f = new File(sPath);
		if (f.isDirectory()) {
			initData(f.listFiles());
			vNofile.setVisibility(View.INVISIBLE);
		} else {
			vNofile.setVisibility(View.VISIBLE);
		}
	}

	// load txt file list in the dir
	private void initData(File[] files) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();

		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				if (isValidFileOrDir(files[i])) {
					map1 = new HashMap<String, String>();
					map1.put("book_name", files[i].getName());
					map1.put("book_addr", files[i].getAbsolutePath());
					list.add(map1);
				}
			}
		}

		ListView lv = (ListView) findViewById(R.id.lstBook);
		// init adapter
		bookAdapter listAdapter = new bookAdapter(this, list);
		lv.setAdapter(listAdapter);
	}

	private void initDir() {
		File rootPath = Environment.getExternalStorageDirectory();
		judgFile(rootPath.getAbsolutePath());
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