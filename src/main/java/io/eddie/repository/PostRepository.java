package io.eddie.repository;

import io.eddie.data.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private int sequence = 0;
    private List<Post> postList = new ArrayList<>();

    public int save(String title, String body) {
        Post newPost = new Post(++sequence, title, body);
        postList.add(newPost);

        return newPost.getId();
    }

    public Post getById(int postId) {
        return postList.get(postId - 1);
    }

    public void update(int postId, String title, String body) {

        Post findPost = postList.get(postId - 1);

        findPost.setTitle(title);
        findPost.setBody(body);

        findPost.setUpdatedAt(LocalDate.now());

    }

    public void removeById(int postId) {

        Post findPost = postList.get(postId - 1);

        if ( findPost != null ) {
            postList.set(postId - 1, null);
        }

    }


}
