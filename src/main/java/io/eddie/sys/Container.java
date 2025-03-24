package io.eddie.sys;

import io.eddie.controller.BoardController;
import io.eddie.controller.PostController;
import io.eddie.repository.BoardRepository;
import io.eddie.repository.PostRepository;
import io.eddie.service.BoardService;
import io.eddie.service.PostService;

import java.util.Scanner;

public class Container {

    public static Scanner sc;

    public static PostRepository postRepository;
    public static BoardRepository boardRepository;

    public static PostService postService;
    public static BoardService boardService;

    public static PostController postController;
    public static BoardController boardController;

    static {

        sc = new Scanner(System.in);

        boardRepository = new BoardRepository();
        postRepository = new PostRepository();

        boardService = new BoardService(boardRepository);
        postService = new PostService(postRepository);

        boardController = new BoardController(boardService);
        postController = new PostController(postService, boardService);

    }

}
