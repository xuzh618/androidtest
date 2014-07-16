package com.xuzh.androidtest.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.xuzh.androidtest.R;
import com.xuzh.androidtest.editchange.EditTextChangeActivity;
import com.xuzh.androidtest.popwin.PopWindowActivity;
import com.xuzh.androidtest.service.ServiceActivity;
import com.xuzh.androidtest.share.ShareActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        String value = getMetaData("xuzhaohu");
        Log.d("xuzhaohu", value);

    }

    /*** xml中的metadata获取 ***/
    private String getMetaData(String key) {
        String value = "";
        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(key);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    private void initViews() {
        /*** 弹窗 ***/
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jumpToActivity(PopWindowActivity.class);
            }
        });
        /*** 输入框监听 ***/
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jumpToActivity(EditTextChangeActivity.class);
            }
        });
        /*** 分享框 ***/
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jumpToActivity(ShareActivity.class);
            }
        });
        /*** 服务 ***/
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jumpToActivity(ServiceActivity.class);
            }
        });
    }

    private void jumpToActivity(Class<?> cls) {
        Intent it = new Intent(MainActivity.this, cls);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
