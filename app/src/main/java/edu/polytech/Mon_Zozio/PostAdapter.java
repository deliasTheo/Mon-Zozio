package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends BaseAdapter {
    LayoutInflater inflter;
    private PostList model;
    private PostView postView;


    public PostAdapter(Context context, PostView postView) {
        inflter = (LayoutInflater.from(context));
        this.postView = postView;
    }

    @Override
    public int getCount() {
        return PostList.getInstance().size();
    }

    @Override
    public PostModel getItem(int i) {
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
        view.setOnClickListener(click -> postView.onClick(i));
        name.setText(PostList.getInstance().get(i).getName());
        position.setText(PostList.getInstance().get(i).getPosition());
        photoProfil.setImageResource(PostList.getInstance().get(i).getPhotoProfil());
        photoOiseau.setImageResource(PostList.getInstance().get(i).getPhotoPost());
        return view;
    }
    public void updateModel(PostList model) {
        this.model = model;

    }
    public void refresh(PostList model) {
        updateModel(model);
        Log.d("MonZozio", "listView refreshed with ==> " + model);
        //todo check team==TEAM1
        notifyDataSetChanged();     //refresh display
    }

}
