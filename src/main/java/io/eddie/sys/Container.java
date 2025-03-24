package io.eddie.sys;

import io.eddie.controller.AccountController;
import io.eddie.controller.BoardController;
import io.eddie.controller.PostController;
import io.eddie.repository.AccountRepository;
import io.eddie.repository.BoardRepository;
import io.eddie.repository.PostRepository;
import io.eddie.service.AccountService;
import io.eddie.service.BoardService;
import io.eddie.service.PostService;

import java.util.Scanner;

public class Container {

    public static Scanner sc;

    public static PostRepository postRepository;
    public static BoardRepository boardRepository;
    public static AccountRepository accountRepository;

    public static PostService postService;
    public static BoardService boardService;
    public static AccountService accountService;

    public static PostController postController;
    public static BoardController boardController;
    public static AccountController accountController;

    static {

        sc = new Scanner(System.in);

        boardRepository = new BoardRepository();
        postRepository = new PostRepository();
        accountRepository = new AccountRepository();

        boardService = new BoardService(boardRepository);
        postService = new PostService(postRepository);
        accountService = new AccountService(accountRepository);

        boardController = new BoardController(sc, boardService);
        postController = new PostController(sc, postService, boardService);
        accountController = new AccountController(sc, accountService);

    }

}
