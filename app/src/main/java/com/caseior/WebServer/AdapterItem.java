package com.caseior.WebServer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

class AdapterItem extends BaseAdapter {
    List<pojoclassObj> items;
    Context ct;

    public AdapterItem(Context ct, List<pojoclassObj> items) {
        this.ct=ct;
    this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater li=LayoutInflater.from(ct);
        View v=li.inflate(R.layout.view_onlist,null);
        ImageView iv=v.findViewById(R.id.imgv);


        TextView tv1=v.findViewById(R.id.title);
        TextView des=v.findViewById(R.id.description);
        pojoclassObj pcO=items.get(i);
        tv1.setText(pcO.getTitle());
        des.setText(pcO.getDescption());
        Glide.with(ct).load(pcO.getImgUrl()).into(iv);


        return v;
    }

}
