package com.myapp.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myapp.administrator.myapplication.R;


/**
 * Created by Administrator on 2015/8/18.
 */
public class WebActivity extends Activity {
    //�������ʱ������������
    public static void actionActivity(Context context, String url)
    {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        //����ַ
        webView = (WebView)findViewById(R.id.web_view);
        webView.loadUrl(getIntent().getStringExtra("url"));
        WebSettings websettings = webView.getSettings();
        websettings.setJavaScriptEnabled(true);     // Warning! You can have XSS vulnerabilities!
        websettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//���ݴ���ȥ�Ĳ������¼�����ҳ
                return true;//��ʾ����Ҫ����ϵͳ�������
            }
        });
    }
}
