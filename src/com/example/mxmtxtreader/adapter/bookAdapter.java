/*
ListView adapter for book list
 */
package com.example.mxmtxtreader.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mxmtxtreader.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class bookAdapter extends BaseAdapter {
	Context mContext;
	LayoutInflater inflater;
	final int VIEW_TYPE = 2;
	// txt file
	final int TYPE_1 = 0;
	// dir
	final int TYPE_2 = 1;
	ArrayList<HashMap<String, String>> lstBook = new ArrayList<HashMap<String, String>>();

	public bookAdapter(Context context, ArrayList<HashMap<String, String>> lst) {
		// TODO Auto-generated constructor stub
		mContext = context;
		inflater = LayoutInflater.from(mContext);
		this.lstBook = lst;
	}

	@Override
	public int getCount() {
		return lstBook.size();
	}

	@Override
	public Object getItem(int index) {
		return lstBook.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public int getItemViewType(int position) {
		HashMap<String, String> hm = lstBook.get(position);
		if (hm.get("book_name").endsWith(".txt"))
			return TYPE_1;
		else
			return TYPE_2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup vg) {
		HashMap<String, String> entity = lstBook.get(position);

		int type = getItemViewType(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			if (type == TYPE_1) {
				convertView = inflater.inflate(R.layout.book_item_imp, null);
				holder.chkBox = (CheckBox) convertView
						.findViewById(R.id.chkBook);
			} else {
				convertView = inflater
						.inflate(R.layout.book_item_imp_dir, null);
			}
			holder.book_name = (TextView) convertView
					.findViewById(R.id.book_name);
			holder.book_url = (TextView) convertView
					.findViewById(R.id.book_addr);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.book_name.setText(entity.get("book_name").toString());
		holder.book_url.setText(entity.get("book_addr").toString());

		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return VIEW_TYPE;
	}

	class ViewHolder {
		CheckBox chkBox;
		TextView book_name;
		TextView book_url;
	}
}
