package kg.geektech.todo.presenter.home;

import com.like.LikeButton;

public interface OnLikeListener {
        void liked(LikeButton likeButton);
        void unLiked(LikeButton likeButton);
    }

