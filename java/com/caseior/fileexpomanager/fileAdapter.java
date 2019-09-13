package com.caseior.fileexpomanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

class fileAdapter extends BaseAdapter {
    private List<File> files;
    private Context context;

    public fileAdapter(Context context, List<File> files)
    {
        this.context=context;
        this.files=files;

    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int i) {
        return files.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li= LayoutInflater.from(context);
        View v=li.inflate(R.layout.listrowfiles,null);
        TextView tv=v.findViewById(R.id.textView);
        tv.setText(files.get(i).getName());
        ImageView iv=v.findViewById(R.id.imageView);
        if(files.get(i).isDirectory())
        {
            iv.setImageResource(R.drawable.folder);
        }
        else
            iv.setImageResource(R.drawable.file);
        return v;
    }
}
