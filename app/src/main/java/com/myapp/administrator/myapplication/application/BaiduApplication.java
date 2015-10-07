package com.myapp.administrator.myapplication.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class BaiduApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}

}