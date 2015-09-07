/*
未完善的适配器
*/
package com.example.mxmtxtreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class bookAdapter extends BaseAdapter {
	Context mContext;
	LinearLayout linearLayout = null;
	LayoutInflater inflater;
	TextView tex;
	final int VIEW_TYPE = 2;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;

	public bookAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int index) {
		return new Object();
	}

	@Override
	public long getItemId(int index) {
		return 0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup vg) {
		return v;
	}

	// 每个convert view都会调用此方法，获得当前所需要的view样式
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		int p = position % 6;
		if (p == 0)
			return TYPE_1;
		else if (p < 3)
			return TYPE_2;
		else
			return TYPE_1;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return VIEW_TYPE;
	}
}
