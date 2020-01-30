package com.example.activitytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class nogizaka46Adapter extends ArrayAdapter<Nogizaka46> {
    private int resourceId;
    public nogizaka46Adapter(Context context, int textViewResourceId, List<Nogizaka46> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Nogizaka46 nogizaka46 = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if(convertView == null ){
           view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
           viewHolder = new ViewHolder();
           viewHolder.objectImage = (ImageView) view.findViewById(R.id.nogizaka46_image);
           viewHolder.objectName = (TextView) view.findViewById(R.id.nogizaka46_name);
           view.setTag(viewHolder);//将ViewHolder 存储在View中
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.objectImage.setImageResource(nogizaka46.getImageId());
        viewHolder.objectName.setText(nogizaka46.getName());
        return view;
    }
    class ViewHolder{
        ImageView objectImage;
        TextView  objectName;
    }
}
