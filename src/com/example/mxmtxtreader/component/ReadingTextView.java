package com.example.mxmtxtreader.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ReadingTextView extends TextView {
	public int iWidth;
	public int iHeith;

	public ReadingTextView(Context context) {
		super(context);
	}

	public ReadingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ReadingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public int getEstimatedLength() {
		int height = this.getMeasuredHeight(); // getHeight();
		int lineHeight = getLineHeight();
		int linecount = height / lineHeight;

		float textSize = getTextSize();
		float width = this.getPaint().measureText("≤‚ ‘");
		float linewords = width / textSize;
		return (int) (linecount * linewords);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		iWidth = widthMeasureSpec;
		iHeith = heightMeasureSpec;
	}
}