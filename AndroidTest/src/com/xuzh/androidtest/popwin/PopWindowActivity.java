package com.xuzh.androidtest.popwin;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.xuzh.androidtest.R;

public class PopWindowActivity extends Activity implements DialogSpinner.OnItemSelectedListener {

    private DialogSpinner citySpinner;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_win_view);

        mContext = getApplicationContext();

        initViews();
    }

    private ActionBar mActionBar;

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mActionBar = getActionBar();
        mActionBar.setTitle("弹窗");
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
        citySpinner = (DialogSpinner) findViewById(R.id.sp_city);

        String[] cities = { "北京", "武行", "黑龙江", "广州", "深圳", "珠海", "荆州", "香港", "澳门", "长春", "天津" };
        List<String> data = new ArrayList<String>();
        for (String s : cities) {
            data.add(s);
        }
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_dropdown_item, data);

        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(View view, int position) {
        if (view == null)
            return;
        if (view.getId() == R.id.sp_city) {
            String city = (String) citySpinner.getSelectedItem();
            Toast.makeText(mContext, "您选择了" + city, Toast.LENGTH_SHORT).show();
        } else {
            //TODO
        }

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
