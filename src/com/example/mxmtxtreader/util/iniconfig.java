package com.example.mxmtxtreader.util;

import java.util.ArrayList;

public class iniconfig {
	private static iniconfig _instance;

	private iniconfig() {
	}

	public static iniconfig getInstance() {

		if (_instance == null) {
			_instance = new iniconfig();
		}
		return _instance;
	}

	public String GetItem(ArrayList<String> lines, String key) {
		String reVal = "";
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).startsWith(key + ":"))
				reVal = lines.get(i).replaceAll(key + ":", "");
		}
		return reVal;
	}

}
