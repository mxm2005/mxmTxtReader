package com.example.mxmtxtreader.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileService {
	public static void save(OutputStream outstream, String content)
			throws IOException {
		outstream.write(content.getBytes());
		outstream.close();
	}

	public static String read(InputStream inStream) throws IOException {

		InputStreamReader read = new InputStreamReader(inStream, "gbk");
		BufferedReader reader = new BufferedReader(read);
		String fileContent = "";
		String line;
		while ((line = reader.readLine()) != null) {
			fileContent += line;
		}
		read.close();

		return fileContent;
	}

	public static ArrayList<String> readList(InputStream inStream)
			throws IOException {
		ArrayList<String> reLst = new ArrayList<String>();
		InputStreamReader read = new InputStreamReader(inStream, "gbk");
		BufferedReader reader = new BufferedReader(read);

		String line;
		while ((line = reader.readLine()) != null) {
			reLst.add(line);
		}
		read.close();

		return reLst;
	}
}