package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

public class PostController implements ClickableActivity {

    ImageView like;
    private PostList postModel;
    private PostView postView;

    public PostController(PostList postModel, PostView postView) {
        this.postModel = postModel;
        this.postView = postView;
    }


    @Override
    public Context getContext() {
        return postView.getContext();
    }

    @Override
    public void onClick(int position) {
        like = postView.getLayout().findViewById(R.id.like);


        like.setOnClickListener(click -> postModel.update(position));

    }
}

