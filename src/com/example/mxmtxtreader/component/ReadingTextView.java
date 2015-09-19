package com.example.mxmtxtreader.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ReadingTextView extends TextView {
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
		int height = getHeight();
		int lineHeight = getLineHeight();
		int linecount = height / lineHeight;

		float textSize = getTextSize();
		float linewords = getWidth() / textSize;
		return (int) (linecount * linewords);
	}
}