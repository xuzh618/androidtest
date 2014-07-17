package com.xuzh.androidtest.service;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xuzh.androidtest.R;

public class ServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);

        initViews();

        Log.d("ServiceActivity", "process id == " + Process.myPid());//线程id
    }

    private ActionBar mActionBar;

    @SuppressLint("NewApi")
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mActionBar = getActionBar();
        mActionBar.setTitle("服务");
        mActionBar.show();
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /*** notice:启动服务后由binder服务，只有同时满足停止服务和unbinder服务后才能destroy该Service ***/
    private void initViews() {
        /*** 启动服务 ***/
        findViewById(R.id.btn_service_start).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ServiceActivity.this, MyService.class);
                startService(it);
                Toast.makeText(ServiceActivity.this, "启动服务！", Toast.LENGTH_LONG).show();
            }
        });
        /*** 停止服务 ***/
        findViewById(R.id.btn_service_stop).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ServiceActivity.this, MyService.class);
                stopService(it);
                Toast.makeText(ServiceActivity.this, "停止服务！", Toast.LENGTH_LONG).show();
            }
        });
        /*** binder服务 ***/
        findViewById(R.id.btn_service_binder).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 绑定服务
                Intent it = new Intent(ServiceActivity.this, MyService.class);
                bindService(it, mConnection, BIND_AUTO_CREATE);
                Toast.makeText(ServiceActivity.this, "binder服务！", Toast.LENGTH_LONG).show();
            }
        });
        /*** un binder服务 ***/
        findViewById(R.id.btn_service_unbinder).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 断开binder
                if (isBinder) {
                    unbindService(mConnection);
                    isBinder = false;
                    Toast.makeText(ServiceActivity.this, "unbinder服务！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ServiceActivity.this, "需要先binder然后才能unbinder服务！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*** 通过 自定义binder 实现activity 与service之间的通讯 ***/
    private MyService.MyBinder myBinder = null;
    /*** 通过 AIDL binder 实现activity 与service之间的通讯 建议 ***/
    private MyAIDLService myBinderAIDL = null;
    private boolean isBinder = false;//绑定的标志位
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开绑定
            isBinder = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 执行binder的相关方法
            //            myBinder = (MyService.MyBinder) service;
            //            myBinder.onStartDownLoad();
            myBinderAIDL = MyAIDLService.Stub.asInterface(service);//跨进程通信
            try {
                myBinderAIDL.toUpperCase("abcd");
                myBinderAIDL.plus(3, 3);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isBinder = true;
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mConnection != null && isBinder) {
            unbindService(mConnection);
            mConnection = null;
        }
    }

}
