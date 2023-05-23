package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context myContect;
    public int[] imagesArray = {
        R.drawable.zozio_1, R.drawable.zozio_2, R.drawable.zozio_3, R.drawable.zozio_3, R.drawable.zozio_4,
            R.drawable.zozio_5, R.drawable.zozio_6, R.drawable.zozio_7, R.drawable.zozio_8, R.drawable.zozio_9
    };

    public ImageAdapter(Context myContect) {
        this.myContect = myContect;
        this.imagesArray= new int[]{
                R.drawable.zozio_1, R.drawable.zozio_2, R.drawable.zozio_3, R.drawable.zozio_3, R.drawable.zozio_4,
                R.drawable.zozio_5, R.drawable.zozio_6, R.drawable.zozio_7, R.drawable.zozio_8, R.drawable.zozio_9
        };
    }

    @Override
    public int getCount() {
        return imagesArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imagesArray[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(myContect);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //imageView.setImageResource(imagesArray[position]);
            imageView.setPadding(16,16,16,16);
            //return imageView;
        }else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imagesArray[position]);
        return imageView;
    }
}