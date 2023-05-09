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
        ImageView imageView = new ImageView(myContect);
        imageView.setImageResource(imagesArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));

        return imageView;
    }
}
