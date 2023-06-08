package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

public class PostController implements Controller {
    private final String TAG = "MonZozio "+getClass().getSimpleName();

    ImageView like;
    private PostModel postModel;
    private PostView postView;

    public PostController(PostModel postModel, PostView postView) {
        this.postModel = postModel;
        this.postView = postView;
    }


    @Override
    public Context getContext() {
        return postView.getContext();
    }

    @Override
    public void onClick(int position) {
        Log.d(TAG, "position = "+ position);
        postModel.setLike(position);

    }
}

