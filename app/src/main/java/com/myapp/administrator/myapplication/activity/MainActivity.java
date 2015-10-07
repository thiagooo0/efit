package com.myapp.administrator.myapplication.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.administrator.myapplication.R;
import com.myapp.administrator.myapplication.fragment.BodyCheckFragment;
import com.myapp.administrator.myapplication.fragment.CoachFragment;
import com.myapp.administrator.myapplication.fragment.HealthyFragment;
import com.myapp.administrator.myapplication.fragment.MainPagerFragment;
import com.myapp.administrator.myapplication.fragment.OfflineActivityFragment;


public class MainActivity extends Activity implements OnClickListener{
//    private PagerSlidingTabStrip pagerSlidingTabStrip;
//    private ViewPager viewPager;
//    private Button baidu_map;
    //界面的各种控件
    private FrameLayout fragmentLayout;//内容页
    private TextView appTitle;//标题
    private ImageView presonalPage;//个人页面
    private ImageView weatherIcon;//天气图标
    private TextView weatherText;//天气文字
    private ImageView healthy;//健康信息
    private ImageView coash;//教练
    private ImageView efit;//efit主页
    private ImageView bodyCheck;//身体监测
    private ImageView offlineActivity;//线下活动

    //管理fragment
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    //fragment
    private MainPagerFragment mainPagerFragment;//首页
    private CoachFragment coachFragment;//约教练
    private HealthyFragment healthyFragment;//健康信息
    private OfflineActivityFragment offlineActivityFragment;//线下活动
    private BodyCheckFragment bodyCheckFragment;//身体监测

    //那个页面
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //设置控件
        setView();

        //打开那个碎片
        Intent intent = getIntent();
        setPage(intent.getIntExtra("page", 3));
    }

    private void setView()
    {
        //初始化控件
        fragmentLayout = (FrameLayout)findViewById(R.id.fragment_layout);//内容页
        appTitle = (TextView)findViewById(R.id.app_title);//标题
        presonalPage = (ImageView)findViewById(R.id.presonal_page);//个人页面
        weatherIcon = (ImageView)findViewById(R.id.weather_icon);//天气图标
        weatherText = (TextView)findViewById(R.id.weather_text);//天气文字
        healthy = (ImageView)findViewById(R.id.healthy);//健康信息
        coash = (ImageView)findViewById(R.id.coach);//教练
        efit = (ImageView)findViewById(R.id.efit);//efit主页
        bodyCheck = (ImageView)findViewById(R.id.body_check);//身体监测
        offlineActivity = (ImageView)findViewById(R.id.offline_activity);//线下活动

        //初始化fragment
        fragmentManager=getFragmentManager();


        //添加监听器
        presonalPage.setOnClickListener(this);
        healthy.setOnClickListener(this);
        coash.setOnClickListener(this);
        efit.setOnClickListener(this);
        bodyCheck.setOnClickListener(this);
        offlineActivity.setOnClickListener(this);
    }

    //设置页面
    private void setPage(int i)
    {
        Log.d("test", "MainActivity.setPage:" + i);
        if(page == i)
        {
            return;
        }
        page = i;
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (page)
        {
            case 1:
            {
                if(healthyFragment == null)
                {
                    Log.d("test", "MainActivity.setPage:健康信息 opening new");
                    healthyFragment = new HealthyFragment();
                    transaction.add(R.id.fragment_layout, healthyFragment);
                }
                else
                {
                    transaction.show(healthyFragment);
                }
                break;
            }
            default:
            {

            }
            case 2:
            {
                Log.d("test", "MainActivity.setPage:约教练 opening");

                if(coachFragment == null)
                {
                    Log.d("test", "MainActivity.setPage:约教练 opening new");
                    coachFragment = new CoachFragment();
                    transaction.add(R.id.fragment_layout, coachFragment);
                }
                else
                {
                    transaction.show(coachFragment);
                }
//                transaction.replace(R.id.fragment_layout, coachFragment);
//                transaction.commit();
                break;
            }
            case 3:
            {
                Log.d("test", "MainActivity.setPage:首页 opening");
                if(mainPagerFragment == null)
                {
                    mainPagerFragment = new MainPagerFragment();
                    transaction.add(R.id.fragment_layout, mainPagerFragment);
                }
                else
                {
                    transaction.show(mainPagerFragment);
                }
//                transaction.replace(R.id.fragment_layout, mainPagerFragment);
                break;
            }
            case 4:
            {
                if(bodyCheckFragment == null)
                {
                    bodyCheckFragment = new BodyCheckFragment();
                    transaction.add(R.id.fragment_layout, bodyCheckFragment);
                }
                else
                {
                    transaction.show(bodyCheckFragment);
                }
                break;
            }
            case 5:
            {
                if(offlineActivityFragment == null)
                {
                    offlineActivityFragment = new OfflineActivityFragment();
                    transaction.add(R.id.fragment_layout, offlineActivityFragment);
                }
                else
                {
                    transaction.show(offlineActivityFragment);
                }
                break;
            }
        }
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //隐藏碎片
    private void hideFragment(FragmentTransaction transaction)
    {
        if(coachFragment != null)
            transaction.hide(coachFragment);
        if(mainPagerFragment != null)
            transaction.hide(mainPagerFragment);
        if(healthyFragment != null)
            transaction.hide(healthyFragment);
        if(bodyCheckFragment != null)
            transaction.hide(bodyCheckFragment);
        if(offlineActivityFragment != null)
            transaction.hide(offlineActivityFragment);
//        switch(page)
//        {
//            case R.id.healthy:
//            {
////                transaction.hide(fragment)
//                break;
//            }
//            case R.id.coach:
//            {
//                if(coachFragment != null)
//                    transaction.hide(coachFragment);
//                break;
//            }
//            case R.id.efit:
//            {
//                if(mainPagerFragment != null)
//                    transaction.hide(mainPagerFragment);
//                break;
//            }
//            case R.id.body_check:
//            {
//                setPage(4);
//                break;
//            }
//            case R.id.offline_activity:
//            {
//                setPage(5);
//                break;
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.presonal_page:
            {
                Intent intent = new Intent(MainActivity.this, PresonalMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.healthy:
            {
                setPage(1);
                break;
            }
            case R.id.coach:
            {
                setPage(2);
                break;
            }
            case R.id.efit:
            {
                setPage(3);
                break;
            }
            case R.id.body_check:
            {
                setPage(4);
                break;
            }
            case R.id.offline_activity:
            {
                setPage(5);
                break;
            }
        }
    }
}
