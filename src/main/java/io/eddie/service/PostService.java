package io.eddie.service;

import io.eddie.data.Post;
import io.eddie.repository.PostRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostService {

//    private PostRepository postRepository = new PostRepository();
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(String title, String body) {
        return postRepository.save(title, body);
    }

    public Post getById(int postId) {
        return postRepository.getById(postId);
    }

    public void update(int postId, String title, String body) {
        postRepository.update(postId, title, body);
    }

    public void removeById(int postId) {
        postRepository.removeById(postId);
    }



}
