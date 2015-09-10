package com.example.mxmtxtreader.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileService {
	public static void save(OutputStream outstream, String content)
			throws IOException {
		outstream.write(content.getBytes());
		outstream.close();
	}

	public static String read(InputStream inStream) throws IOException {

		InputStreamReader read = new InputStreamReader(inStream);
		BufferedReader reader = new BufferedReader(read);
		String fileContent = "";
		String line;
		while ((line = reader.readLine()) != null) {
			fileContent += line;
		}
		read.close();

		return fileContent;
	}

	public static long getCount(String path) throws Exception {
		long reVal = -1;
		RandomAccessFile rf;
		try {
			rf = new RandomAccessFile(path, "r");
			reVal = rf.length();
		} catch (Exception ex) {
			throw ex;
		}
		return reVal;
	}

	public static ArrayList<String> readList(InputStream inStream)
			throws IOException {
		ArrayList<String> reLst = new ArrayList<String>();
		InputStreamReader read = new InputStreamReader(inStream);
		BufferedReader reader = new BufferedReader(read);

		String line;
		while ((line = reader.readLine()) != null) {
			reLst.add(line);
		}
		read.close();

		return reLst;
	}

	public String convertCodeAndGetText(String str_filepath) {

		File file = new File(str_filepath);
		BufferedReader reader;
		String text = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fis);
			in.mark(4);
			byte[] first3bytes = new byte[3];
			in.read(first3bytes);
			in.reset();
			if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB
					&& first3bytes[2] == (byte) 0xBF) {
				// utf-8
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			} else if (first3bytes[0] == (byte) 0xFF
					&& first3bytes[1] == (byte) 0xFE) {
				reader = new BufferedReader(
						new InputStreamReader(in, "unicode"));
			} else if (first3bytes[0] == (byte) 0xFE
					&& first3bytes[1] == (byte) 0xFF) {
				reader = new BufferedReader(new InputStreamReader(in,
						"utf-16be"));
			} else if (first3bytes[0] == (byte) 0xFF
					&& first3bytes[1] == (byte) 0xFF) {
				reader = new BufferedReader(new InputStreamReader(in,
						"utf-16le"));
			} else {
				reader = new BufferedReader(new InputStreamReader(in, "GBK"));
			}

			String str = reader.readLine();
			while (str != null) {
				text = text + str + "\n";
				str = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

}