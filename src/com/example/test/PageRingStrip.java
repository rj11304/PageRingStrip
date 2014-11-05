package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PageRingStrip extends LinearLayout{

	private ViewPager page;
	private int size;
	private int itemRightMargin;
	private int itemLeftMargin;
	private int itemTopMargin;
	private int itemBottomMargin;
	private int itemDefaultColor = Color.GREEN;
	private int itemStatColor = Color.BLUE;
	private OnPageChangeListener pageChangeListener;
	
	public PageRingStrip(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		size = (int) (context.getResources().getDisplayMetrics().density * 10);
		// TODO Auto-generated constructor stub
	}
	
	public PageRingStrip(Context context, AttributeSet attrs){
		super(context,attrs);
		size = (int) (context.getResources().getDisplayMetrics().density * 10);
	}

	public PageRingStrip(Context context){
		super(context);
		size = (int) (context.getResources().getDisplayMetrics().density * 10);
	}
	
	public void setPage(ViewPager page){
		this.page = page;
		initItems();
	}
	
	public void setItemSzie(int size){
		this.size = size;
		initItems();
	}
	
	public void setItemMargin(int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		this.itemLeftMargin = leftMargin;
		this.itemRightMargin = rightMargin;
		this.itemTopMargin = topMargin;
		this.itemBottomMargin = bottomMargin;
		initItems();
	}
	
	private void initItems(){
		if(page == null || page.getAdapter() == null) return;
		this.removeAllViews();
		for(int i = 0;i < page.getAdapter().getCount();i++){
			View child = getView(i);
			this.addView(child);
			LayoutParams params = new LayoutParams(size,size);
			params.leftMargin = this.itemLeftMargin;
			params.rightMargin = this.itemRightMargin;
			params.topMargin = this.itemTopMargin;
			params.bottomMargin = this.itemBottomMargin;
			child.setLayoutParams(params);
		}
		page.setOnPageChangeListener(listener);
		int position = page.getCurrentItem();
		checked(position);
		this.invalidate();
	}
	
	protected View getView(int position){
		Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(itemDefaultColor);
		paint.setAntiAlias(true);
		canvas.drawCircle(100, 100, 100, paint);
		ImageView img = new ImageView(getContext());
		img.setImageBitmap(bitmap);
		return img;
	}
	
	protected void checked(int position){
		if(page.getAdapter() == null) return;
		for(int i = 0;i < page.getAdapter().getCount();i++){
			ImageView img = (ImageView) this.getChildAt(i);
			if(i == position){
				img.setColorFilter(itemStatColor);
			}else{
				img.setColorFilter(itemDefaultColor);
			}
		}
	}
	
	private android.support.v4.view.ViewPager.OnPageChangeListener listener = new android.support.v4.view.ViewPager.OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			if(pageChangeListener != null){
				pageChangeListener.onPageScrollStateChanged(arg0);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			if(pageChangeListener != null){
				pageChangeListener.onPageScrolled(arg0, arg1, arg2);
			}
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			checked(arg0);
			if(pageChangeListener != null){
				pageChangeListener.onPageSelected(arg0);
			}
		}
		
	};
	
	public interface OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0);
		public void onPageScrolled(int arg0, float arg1, int arg2);
		public void onPageSelected(int arg0);
	}
	
	public void setOnPageChangeListener(OnPageChangeListener listener){
		this.pageChangeListener = listener;
	}
}
