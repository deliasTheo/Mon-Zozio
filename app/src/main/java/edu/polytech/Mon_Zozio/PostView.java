package edu.polytech.Mon_Zozio;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.View.inflate;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PostView extends ConstraintLayout {
    private ImageView likeButton;
    private PostModel postModel;
    private PostController postController;

    public PostView(Context context) {
        super(context);
        init();
    }

    public PostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PostView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setPostModel(PostModel postModel) {
        this.postModel = postModel;
        postController = new PostController(postModel);
        updateView();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, this);
        likeButton = findViewById(R.id.like);
        likeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postModel.isLiked()) {
                    postController.unlikePost();
                } else {
                    postController.likePost();
                }
                updateView();
            }
        });
    }

    private void updateView() {
        if (postModel.isLiked()) {
            likeButton.setImageResource(R.drawable.icon_likerouge);
        } else {
            likeButton.setImageResource(R.drawable.icon_like);
        }
    }
}

