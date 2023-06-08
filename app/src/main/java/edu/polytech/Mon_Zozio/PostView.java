package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/*
    L'adapter a besoin de connaitre la liste, cad le model pour afficher son contenu => transgression de mvc
 */

public class PostView implements Observer {
    private final String TAG = "MonZozio " + getClass().getSimpleName();
    private Controller controller;
    private ListView listview;
    private PostAdapter adapter;
    private Context context;


// la vu doit récup tout les éléments, et donc l'adapteur doit sortir de la vu


    public <T extends ViewGroup> PostView(ListView listview, Context context) {
        this.listview = listview;
        this.context = context;
        Log.d(TAG, "View is created" );
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Post> listPost = (ArrayList<Post>) o;

        adapter.notifyDataSetChanged();
        //adapter.setController(controller);
        Log.d(TAG, "listView refreshed with ==> " + listPost);
    }


    public void setController(Controller controller) {
        this.controller = controller;
        adapter = new PostAdapter(context, this); //carrefull, model is null !
        this.listview.setAdapter( adapter );
        adapter.setController(controller);
    }




    public ListView getLayout() {
        return listview;
    }

    public Context getContext() {
        return listview.getContext();
    }

}

class PostAdapter extends BaseAdapter {
    private final String TAG = "MonZozio " + getClass().getSimpleName();

    private LayoutInflater inflter;
    private PostView postView;
    private Controller controller;

    private ImageView profilPhoto;
    private TextView name;
    private TextView location;
    private ImageView postPhoto;
    private ImageView logoLike;
    private ImageView logoComment;
    private ImageView logoShare;

    public ImageView getProfilPhoto() {
        return profilPhoto;
    }

    public TextView getName() {
        return name;
    }

    public TextView getLocation() {
        return location;
    }

    public ImageView getPostPhoto() {
        return postPhoto;
    }

    public ImageView getLogoLike() {
        return logoLike;
    }

    public ImageView getLogoComment() {
        return logoComment;
    }

    public ImageView getLogoShare() {
        return logoShare;
    }

    public PostAdapter(Context context, PostView postView) {
        inflter = (LayoutInflater.from(context));
        this.postView = postView;
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public int getCount() {
        return PostModel.getInstance().size();
    }

    @Override
    public Post getItem(int i) {
        return PostModel.getInstance().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView==null ? inflter.inflate(R.layout.activity_listview, null) : convertView;
        this.name = (TextView) view.findViewById(R.id.name);
        this.location = (TextView) view.findViewById(R.id.position);
        this.profilPhoto = (ImageView) view.findViewById(R.id.profilPhoto);
        this.postPhoto= (ImageView) view.findViewById(R.id.postPhoto);
        this.logoComment= (ImageView) view.findViewById(R.id.comment);
        this.logoLike= (ImageView) view.findViewById(R.id.like);
        this.logoShare= (ImageView) view.findViewById(R.id.share);

        logoLike.setOnClickListener(click -> {
            Log.d(TAG, "CLick " + i + " isLiked : " + PostModel.getInstance().get(i).isLiked());
            controller.onClick(i);
        });

        this.name.setText(PostModel.getInstance().get(i).getName());
        this.location.setText(PostModel.getInstance().get(i).getPosition());
        this.profilPhoto.setImageResource(PostModel.getInstance().get(i).getPhotoProfil());
        this.postPhoto.setImageResource(PostModel.getInstance().get(i).getPhotoPost());
        this.logoLike.setImageResource(PostModel.getInstance().get(i).isLiked()? R.drawable.icon_likerouge : R.drawable.icon_like);
        return view;

    }




}


