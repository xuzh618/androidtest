package com.xuzh.androidtest.share;

import java.util.ArrayList;
import java.util.List;

import com.xuzh.androidtest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private List<ShareEntity> list = new ArrayList<ShareEntity>();

    public ShareAdapter(Context mContext, List<ShareEntity> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ShareEntity entity = list.get(position);
        if (entity == null)
            return null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.share_item, null);

            viewHolder = new ViewHolder();
            viewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.share_icon);
            viewHolder.mTvName = (TextView) convertView.findViewById(R.id.share_name);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mIvIcon.setImageResource(entity.getAppIcon());
        viewHolder.mTvName.setText(entity.getAppName());

        return convertView;
    }

    class ViewHolder {
        public ImageView mIvIcon;
        public TextView mTvName;
    }
}
