package com.myapp.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myapp.administrator.myapplication.activity.WebActivity;
import com.myapp.administrator.myapplication.model.Advert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class CarouselAdapter extends PagerAdapter {

    private List<Advert> list;
    private Context context;
    private List<View> views=new ArrayList<View>();
//    private LruPhoto lru;

    public CarouselAdapter(List<Advert> list, Context context){
        this.list=list;
        this.context=context;
//        lru = new LruPhoto(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        ImageView im=new ImageView(context);
        im.setTag(list.get(position).getLinkUrl());
        im.setImageResource(list.get(position).getTestDraw());
//        lru.loadBitmaps(im, list.get(position).getImageUrl());
//        im.setImageResource(list[position]);
        im.setScaleType(ImageView.ScaleType.CENTER_CROP);
        views.add(im);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        container.addView(im,layoutParams);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "touchPhoto");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://" + (String)v.getTag()));
//                context.startActivity(intent);
                WebActivity.actionActivity(context, (String) v.getTag());
//                WebActivity.actionActivity(context, "http://www.weibo.com");
            }
        });
        return im;
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        //((ViewPager)container).removeView(views.get(position));
    }

    //????????
    public void onDataChange(List<Advert> list)
    {
        this.list = list;
        this.notifyDataSetChanged();
    }
}

