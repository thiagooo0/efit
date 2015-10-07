package com.myapp.administrator.myapplication.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.myapp.administrator.myapplication.R;
import com.myapp.administrator.myapplication.model.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class BaiduMapActivity extends Activity{
    private MapView mMapView;
    private BaiduMap mBaiduMap;
//    定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
//    BitmapDescriptor mCurrentMarker;

    //初始化全局bitmap信息，不用的时候，及时recycle
    BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
    //BitmapDescriptor bdGround = BitmapDescriptorFactory
    //        .fromResource(R.drawable.ground_overlay);

//    图层标记建筑物
    private Marker[] mMarker;
    List<Building> buildings=new ArrayList<Building>();

    boolean isFirstLoc = true;// 是否第一次定位


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.d("test", "BaiduMapActivity.Oncreate-loading1");
        super.onCreate(savedInstanceState);
        Log.d("test", "BaiduMapActivity.Oncreate-loading2");
        //在使用sdk各组件之前，初始化context信息，传入applicationContext,该方法要在setContentView之前实现
        SDKInitializer.initialize(getApplicationContext());
//        SDKInitializer.initialize(this.getApplication());
        setContentView(R.layout.activity_map);

        setView();
        setCovering();
        setViewListener();
    }

    private void setView(){
        mCurrentMode = LocationMode.NORMAL;
        mMapView=(MapView)findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        //卫星地图
        //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); //设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }
    //设置覆盖物
    private void setCovering(){
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
        initOverlay();

    }
    //设置覆盖物的信息
    public void initOverlay(){
        //即系json。。进行标注地图
        //这里预先设置数据
        Building building1=new Building();
        building1.setBuildingId(1);
        building1.setBuildingName("my home");
        building1.setLatitude(23.040958);
        building1.setLongitude(113.320835);
        buildings.add(building1);
        Building building2=new Building();
        building2.setBuildingId(2);
        building2.setBuildingName("广州南站");
        building2.setLatitude(22.996187);
        building2.setLongitude(113.275693);
        buildings.add(building2);
        //设置zIndex，跟buildings的排列顺序相同
        int i=0;
        mMarker=new Marker[buildings.size()];
        for(Building b:buildings){
            //经度纬度
            LatLng latLng = new LatLng(b.getLatitude(), b.getLongitude());
            //设置覆盖物的坐标，图标，zIndex
            OverlayOptions overlayOptions = new MarkerOptions().position(latLng).icon(bd).zIndex(i);
            //���б��
            mMarker[i] = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            i++;
        }

        /*
        //地理范围，4个方位的范围，设置为中国的大致范围
        LatLng southwest = new LatLng(20, 70);
        LatLng northeast = new LatLng(53, 137);
        LatLng northwest = new LatLng(49, 72);
        LatLng southeast = new LatLng(17, 121);
        //构造地理范围
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(northeast).include(southwest)
                .include(southeast).include(northwest).build();
        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds).image(bdGround).transparency(0.8f);
        mBaiduMap.addOverlay(ooGround);
        */

        //设置地图中心点
        //MapStatusUpdate u = MapStatusUpdateFactory
        //        .newLatLng(bounds.getCenter());
        //mBaiduMap.setMapStatus(u);
    }

    private void setViewListener() {
        //设置market的点击事件
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(BaiduMapActivity.this,buildings.get(marker.getZIndex()).getBuildingName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不再处理新接受的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此次设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onResume() {
        // activity恢复是，恢复地图控件
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // activity暂停时，暂停地图控件
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
        mMapView = null;
        bd.recycle();
        //bdGround.recycle();
        super.onDestroy();
    }
}
