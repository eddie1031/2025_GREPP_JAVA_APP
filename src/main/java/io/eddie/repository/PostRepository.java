package io.eddie.repository;

import io.eddie.data.Account;
import io.eddie.data.Board;
import io.eddie.data.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private int sequence = 0;
    private List<Post> postList = new ArrayList<>();

    public Post save(String title, String body, Account account, Board board) {
        Post newPost = new Post(++sequence, title, body);

        newPost.setAccount(account);
        newPost.setBoard(board);

        postList.add(newPost);

        return newPost;
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
