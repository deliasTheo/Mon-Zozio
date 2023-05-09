package edu.polytech.Mon_Zozio;

public class PostController {
    private PostModel postModel;

    public PostController(PostModel postModel) {
        this.postModel = postModel;
    }

    public void likePost() {
        postModel.setLiked(true);
    }

    public void unlikePost() {
        postModel.setLiked(false);
    }
}

