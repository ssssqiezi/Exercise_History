package com.jjk.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.jjk.R;
import com.jjk.middleware.widgets.RecycleBin;

import java.util.ArrayList;



public class HomePageAdvertAdapter extends PagerAdapter {
	private ArrayList<View> mImagePageViewList;
	private boolean isInfiniteLoop;
	static final int IGNORE_ITEM_VIEW_TYPE = AdapterView.ITEM_VIEW_TYPE_IGNORE;
	private RecycleBin recycleBin;
	private int       viewType =0;
	private Context    mContext;



	public HomePageAdvertAdapter(Context context,ArrayList<View> list) {
		this.mImagePageViewList = list;
		recycleBin =  new RecycleBin();
		recycleBin.setViewTypeCount(1);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		//return Integer.MAX_VALUE;
		return isInfiniteLoop ? Integer.MAX_VALUE:mImagePageViewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {

		return super.getItemPosition(object);
	}

	@Override public final void destroyItem(ViewGroup container, int position, Object object) {
/*		View view = (View) object;
		container.removeView(view);
		if (viewType != IGNORE_ITEM_VIEW_TYPE) {
			recycleBin.addScrapView(view, position, viewType);
		}*/
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container,int position) {
/*		((ViewPager) container).addView(mImagePageViewList.get(position));
		return mImagePageViewList.get(position);*/
		//对ViewPager页号求模取出View列表中要显示的项

/*		View view = recycleBin.getScrapView(position, viewType);

		if (view == null) {
			position =getPosition(position);
			view = mImagePageViewList.get(position);
		}*/
		position=getPosition(position);
		View view =  mImagePageViewList.get(position);
		//View view =  mImagePageViewList.get(position);
		// the view is new view 所以不会出现同时引用操作的问题
		// the evidence
/*		ImageView view  = new ImageView(mContext);
		if(position==0){
			view.setImageResource(R.drawable.homepage_bannar_main);
		}else{
			view.setImageResource(R.drawable.homepage_bannar_nocancer);
		}*/

		//wrong operation
		//the following view may be removed from the parent in the next operation
		ViewParent vp =view.getParent();
		if (vp!=null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}


		container.addView(view);



		return view;
	}

	public HomePageAdvertAdapter setInfiniteLoop(boolean isInfiniteLoop) {
		this.isInfiniteLoop = isInfiniteLoop;
		return this;
	}

	private int getPosition(int position) {
		return isInfiniteLoop ? position % mImagePageViewList.size() : position;
	}

}
