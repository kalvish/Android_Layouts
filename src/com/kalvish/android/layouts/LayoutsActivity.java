package com.kalvish.android.layouts;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author kalan
 *
 */

public class LayoutsActivity extends Activity {
	
	static ArrayList<String> listData;
	ViewHolderTop holder;
	ListView listView;
	MyListAdapter myListAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Initialize and add data to the array
        listData = new ArrayList<String>();
        listData.add("item 1 of the list");
        listData.add("item 2 of the list");
        listData.add("item 3 of the list");
        listData.add("item 4 of the list");
        listData.add("item 5 of the list");
        listData.add("item 6 of the list");
        listData.add("item 7 of the list");
        
        
        //Lets start inflating the layout we adding to the top of the list.
        LayoutInflater layoutInflater = LayoutInflater.from(this);
		View top_layout = layoutInflater.inflate(R.layout.top_layout, null);
		
		//Here we identify the elements of the top layout.
		holder = new ViewHolderTop();
		holder.top_TextView = (TextView) top_layout
				.findViewById(R.id.textView1);
		holder.top_Button = (Button) top_layout
				.findViewById(R.id.button1);
		
		//Here adds a onclick listener to the top view button
		holder.top_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Here is how we change data in top layout
				holder.top_TextView.setText("Top Layout Text Changed");
			}
		});
		
		//Here we identify our list view
		listView = (ListView) findViewById(R.id.listView1);
		
		//Before setting the list data adapter to the list view, we set its top layout
		//Note  : Set headers before setting list adapter to the list.
		listView.addHeaderView(top_layout);
		
		//Here we initialize our list data adapter
		myListAdapter = new MyListAdapter(this);
		
		//Here we set list adapter to the list
		listView.setAdapter(myListAdapter);
		
    }
    /**
     * @description This is the holder of items in the top layout. Currently we have a text view 
     * and a button
     * @author kalan
     *
     */
    static class ViewHolderTop {
		TextView top_TextView;
		Button top_Button;
	}
    
    /**
     * @description A list adapter for the sample list.
     * @author kalan
     *
     */
    private static class MyListAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyListAdapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.list_row, null);
				holder = new ViewHolder();
				holder.textView = (TextView) convertView
						.findViewById(R.id.textView1);

				convertView.setTag(holder);
			} else {				
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textView.setText(listData.get(position));
			return convertView;
		}

		static class ViewHolder {
			TextView textView;
		}
	}
}