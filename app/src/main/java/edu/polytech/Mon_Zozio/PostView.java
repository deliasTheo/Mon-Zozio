package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class PostView implements Observer {
    private final String TAG = "Mon_Zozio " + getClass().getSimpleName();
    private boolean modelCreated = false;

    private ClickableActivity controller;
    private ListView listview;
    private PostAdapter adapter;
    private ImageView like;


    public <T extends ViewGroup> PostView(ListView listview, Context context) {

        this.listview = listview;

        adapter = new PostAdapter(context, this); //carrefull, model is null !
        this.listview.setAdapter( adapter );
        Log.d(TAG, "View is created" );
    }

    @Override
    public void update(Observable observable, Object o) {
        PostModel modelPost = (PostModel) o;
        PostList model = (PostList) observable;
        if (!modelCreated) {        //fist time only
            adapter.updateModel(model);
            ListView listView = listview.findViewById(R.id.postListView);
            listView.setAdapter( adapter );
            modelCreated = true;
        }
        else {
            adapter.refresh(model);
        }
        Log.d("Monzozio", "update: " + modelPost);
        like = listview.findViewById(R.id.like);
        like.setImageResource(modelPost.isLiked() ? R.drawable.icon_likerouge : R.drawable.icon_like);


        Log.d(TAG, "View update with ==> " + model);

    }


    public void setListener(ClickableActivity controller) {
        this.controller = controller;
    }


    public void onClick(int i) {
        controller.onClick(i);
    }

    public ListView getLayout() {
        return listview;
    }


    public Context getContext() {
        return listview.getContext();
    }

}

