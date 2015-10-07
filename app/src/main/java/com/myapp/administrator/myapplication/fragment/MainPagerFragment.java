package com.myapp.administrator.myapplication.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.myapp.administrator.myapplication.R;
import com.myapp.administrator.myapplication.adapter.CarouselAdapter;
import com.myapp.administrator.myapplication.model.Advert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * e-fit首页
 * Created by Administrator on 2015/8/29.
 */
public class MainPagerFragment extends Fragment {

    private String getDataUrl = "http://13631272494.xicp.net:11144/BPH_DataManage/FirstPage_Lunbo.action";
    private ViewPager viewpager;//
    private CarouselAdapter carouselAdapter;
    private View view;//װ����fragment
    private ViewGroup viewGroup;//
    private ImageView[]tips;//
    private int imageSelected=0;//
    private ScheduledExecutorService scheduled;//
    private boolean flag=false;//
    //    int [] images=new int[]{R.drawable.carousel_1,R.drawable.carousel_2,R.drawable.carousel_3,R.drawable.carousel_4};
    private List<Advert> list;
    private int scrollSize;//hats this

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_main_pager_layout, container, false);

        setData();
        setView();
        setAdapter();
        setTips();
        setViewListener();
        scrollSize = list.size();

        //
        viewpager.setFocusable(true);
        viewpager.setFocusableInTouchMode(true);
        viewpager.requestFocus();
        return view;
    }

    private void setData()
    {
        list = new ArrayList();
        list.add(new Advert(R.drawable.advert1, "http://www.baidu.com"));
        list.add(new Advert(R.drawable.advert2, "http://www.weibo.com"));
    }

    public void setView(){
        viewpager=(ViewPager)view.findViewById(R.id.vp_carousel);
        viewGroup = (ViewGroup)view.findViewById(R.id.view_group);
    }

    public void setAdapter(){

        Log.d("test", "AquariusFragment.onCreateView.setAdapter.list==null: " + String.valueOf(list == null));
        carouselAdapter=new CarouselAdapter(list,getActivity());
        viewpager.setAdapter(carouselAdapter);

    }
    //�����ʾ��
    public void setTips() {
        scrollSize=list.size();
        tips = new ImageView[list.size()];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.point_selected);
            } else {
                tips[i].setBackgroundResource(R.drawable.point_unselected);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            viewGroup.addView(imageView, layoutParams);
        }
    }

    public void setViewListener(){
        //����viewpager����ʾ��ı�
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                imageSelected = position;
                for (int i = 0; i < tips.length; i++) {
                    if (i == position) {
                        tips[i].setBackgroundResource(R.drawable.point_selected);
                    } else {
                        tips[i].setBackgroundResource(R.drawable.point_unselected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        //MainActivity_.btn_setMenu.setVisibility(View.VISIBLE);
        scheduled= Executors.newSingleThreadScheduledExecutor();
        scheduled.scheduleAtFixedRate(new ScrollTask(), 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        //MainActivity_.btn_setMenu.setVisibility(View.GONE);
        scheduled.shutdown();
        super.onStop();
    }



    private class ScrollTask implements Runnable{
        @Override
        public void run() {
            handler.obtainMessage().sendToTarget();

        }
    }

    // �л���ǰ��ʾ��ͼƬ

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Log.d("test", "AquariusFragment.carouselAdapter.getCount:: " +carouselAdapter.getCount());
            imageSelected=(imageSelected+1)%carouselAdapter.getCount();
            Log.i("scrollSize",scrollSize+"");
            Log.i("imageSelected",imageSelected+"");
            viewpager.setCurrentItem(imageSelected);//
        };
    };
}
