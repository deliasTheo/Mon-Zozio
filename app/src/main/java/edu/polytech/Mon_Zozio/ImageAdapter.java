package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    public int[] imagesArray;
    LayoutInflater inflter;


    public ImageAdapter(Context c, int[] imagesArray) {
        this.mContext = c;
        this.imagesArray=imagesArray;
    }

    @Override
    public int getCount() {
        return imagesArray.length;
    }

    @Override
    public Object getItem(int position) {
        //return imagesArray[position];
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //imageView.setImageResource(imagesArray[position]);
            imageView.setPadding(8,8,8,8);
            //return imageView;
        }else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imagesArray[position]);
        return imageView;
    }
}
