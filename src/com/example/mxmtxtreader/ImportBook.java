package com.example.mxmtxtreader;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mxmtxtreader.file.FileService;

import android.os.Bundle;  
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.app.Activity; 

public class ImportBook extends Activity {

 
    
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.import_book);  
          
          String[] arrBook=new String[20];
          for(int i=0;i<20;i++){
        	  arrBook[i]="BOOK--"+i;
          }
        ListView lv=(ListView)findViewById(R.id.lstBook);
        
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