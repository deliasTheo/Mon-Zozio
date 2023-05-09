package edu.polytech.Mon_Zozio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends BaseAdapter {
    private ClickableActivity clickableActivity;

    LayoutInflater inflter;

    public PostAdapter(ClickableActivity clickableActivity) {
        inflter = (LayoutInflater.from(clickableActivity.getContext()));
        this.clickableActivity = clickableActivity;
    }

    @Override
    public int getCount() {
        return PostList.getInstance().size();
    }

    @Override
    public Object getItem(int i) {
        return PostList.getInstance().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView==null ? inflter.inflate(R.layout.activity_listview, null) : convertView;
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView position = (TextView) view.findViewById(R.id.position);
        ImageView photoProfil = (ImageView) view.findViewById(R.id.profilPhoto);
        ImageView photoOiseau = (ImageView) view.findViewById(R.id.postPhoto);
        view.setOnClickListener(click -> clickableActivity.onClick(i));
        name.setText(PostList.getInstance().get(i).getName());
        position.setText(PostList.getInstance().get(i).getPosition());
        photoProfil.setImageResource(PostList.getInstance().get(i).getPhotoProfil());
        photoOiseau.setImageResource(PostList.getInstance().get(i).getPhotoPost());
        return view;
    }
}
