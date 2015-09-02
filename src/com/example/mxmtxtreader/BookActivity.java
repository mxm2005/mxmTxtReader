package com.example.mxmtxtreader;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mxmtxtreader.fragment.YesNoDialog;
import com.example.mxmtxtreader.file.FileService;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BookActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		ListView lv_books = (ListView) findViewById(R.id.lv_books);
		LoadBooks(lv_books);
		lv_books.setOnItemClickListener(new myOnItemClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	private class myOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// String text = parent.getItemAtPosition(position)+"";
			// Log.i("tag",text);
			TextView vName = (TextView) v.findViewById(R.id.book_name);
			TextView vAddr = (TextView) v.findViewById(R.id.book_addr);
			Log.i("tag", vName.getText().toString() + "--"
					+ vAddr.getText().toString());

			Intent inte = new Intent();
			inte.setClass(BookActivity.this, ReadingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("book_name", vName.getText().toString());
			bundle.putString("book_addr", vAddr.getText().toString());
			inte.putExtras(bundle);
			startActivity(inte);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent inite = new Intent();
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_openbook:
			inite.setClass(BookActivity.this, ImportBook.class);
			startActivity(inite);
			return true;
		case R.id.menu_import:
			inite.setClass(BookActivity.this, ImportBook.class);
			startActivity(inite);
			return true;
		case R.id.menu_settings:
			DialogFragment dialog = new YesNoDialog();
			Bundle args = new Bundle();
			args.putString("title", "test title");
			args.putString("message", "test messqge");
			dialog.setArguments(args);
			int YES_NO_CALL = 101;
			dialog.setTargetFragment(dialog, YES_NO_CALL);
			dialog.show(getFragmentManager(), "tag");
			return true;
		}
		// if (id == R.id.menu_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	// 加载图书列表
	private void LoadBooks(ListView lv) {

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
				R.layout.book_item, new String[] { "book_name", "book_addr" },
				new int[] { R.id.book_name, R.id.book_addr });
		lv.setAdapter(listAdapter);
	}
}
