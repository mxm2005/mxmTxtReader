package com.example.mxmtxtreader.util;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class viewmeasurement {
	public static meaview measure(TextView textview, String text) {
		meaview v = new meaview();
		View parent = (View) textview.getParent();
		final int widthSpec = View.MeasureSpec.makeMeasureSpec(
				parent.getMeasuredWidth() - parent.getPaddingLeft()
						- parent.getPaddingRight(), View.MeasureSpec.AT_MOST);

		final int heightSpec = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		textview.measure(widthSpec, heightSpec);

		// textview.setEllipsize(where)
		v.width = textview.getPaint().measureText(text);
//		v.width = textview.getMeasuredWidth();
		v.height = textview.getMeasuredHeight();
		return v;
	}

	public static class meaview {
		public float width;
		public float height;
	}
}
