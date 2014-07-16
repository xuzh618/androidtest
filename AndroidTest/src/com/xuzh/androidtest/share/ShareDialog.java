package com.xuzh.androidtest.share;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import com.xuzh.androidtest.R;

public class ShareDialog extends Dialog {

    private List<ShareEntity> list = new ArrayList<ShareEntity>();
    private ShareAdapter adapter;
    private GridView gridView;
    private Context mContent;

    public ShareDialog(Context context) {
        super(context);
        this.mContent = context;
    }

    public ShareDialog(Context context, int theme) {
        super(context, theme);
        this.mContent = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog_view);
        initViews();
        loadData();
    }

    private void initViews() {
        gridView = (GridView) findViewById(R.id.gv_share);
    }

    private void loadData() {
        list.add(new ShareEntity(R.drawable.ic_launcher, "微信"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "朋友圈"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "微博"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "手机QQ"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "人人"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "连我"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "来往"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "陌陌"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "来往"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "陌陌"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "来往"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "陌陌"));
        list.add(new ShareEntity(R.drawable.ic_launcher, "更多"));

        /** 找到adapter数据 */
        adapter = new ShareAdapter(mContent, list);
        gridView.setAdapter(adapter);//设置adapter
    }
}
