package io.eddie.service;

import io.eddie.data.Account;
import io.eddie.data.Board;
import io.eddie.data.Post;
import io.eddie.repository.PostRepository;

public class PostService {

    private final AccountService accountService;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    public Post save(String title, String body, String username, Board board) {

        Account findAccount = accountService.findByUsername(username);

        return postRepository.save(title, body, findAccount, board);
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
