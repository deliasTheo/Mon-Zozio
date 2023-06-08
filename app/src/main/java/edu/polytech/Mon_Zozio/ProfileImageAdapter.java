package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileImageAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> images;
    private WindowSizeClass width;



    public ProfileImageAdapter(Context context, Resources resources, AppCompatActivity activity) {
        this.context = context;
        this.images = new ArrayList<>();
        width = WindowSizeClass.computeWindowSizeClasses(resources, activity)[1];

    }

    public void addImage(int resourceId) {
        images.add(resourceId);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            if(width==WindowSizeClass.EXPANDED){
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));
            }else{
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        int resourceId = images.get(position);
        imageView.setImageResource(resourceId);

        return imageView;
    }
    public void setImage(int height, int width){
        ImageView imageView;
        imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(width,height));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


    }
}
