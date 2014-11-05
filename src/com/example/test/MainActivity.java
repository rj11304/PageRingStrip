package com.example.test;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewPager page = (ViewPager) this.findViewById(R.id.page);
		PageRingStrip strip = (PageRingStrip) this.findViewById(R.id.strip);
		MyAdapter adapter = new MyAdapter();
		page.setAdapter(adapter);
		strip.setItemMargin(5, 5, 0, 0);
		strip.setPage(page);
	}
	
	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			View view  = View.inflate(MainActivity.this, R.layout.page, null);
			TextView text = (TextView) view.findViewById(R.id.text);
			text.setText(position+"");
			container.addView(view);
			return view;
		}
		
	}

}
