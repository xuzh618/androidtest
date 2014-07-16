package com.xuzh.androidtest.share;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.xuzh.androidtest.R;

public class ShareActivity extends Activity {

    private Button mBtnShare;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_view);

        initViews();

    }

    private ActionBar mActionBar;

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mActionBar = getActionBar();
        mActionBar.setTitle("分享");
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

    private void initViews() {
        mBtnShare = (Button) findViewById(R.id.btn_share_dialog);

        mBtnShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO
                onShareTo();
            }
        });
    }

    private void onShareTo() {

        shareDialog = new ShareDialog(ShareActivity.this);
        shareDialog.setTitle("分享到");
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */

        Window sharewindow = shareDialog.getWindow();
        Display d = getWindowManager().getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = sharewindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
        sharewindow.setAttributes(p);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return null;
            }
        };

        shareDialog.show();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
