package io.eddie.sys;

import io.eddie.controller.BoardController;
import io.eddie.controller.PostController;
import io.eddie.repository.BoardRepository;
import io.eddie.repository.PostRepository;
import io.eddie.service.BoardService;
import io.eddie.service.PostService;

import java.util.Scanner;

public class Application {

    private String domain;

    private Scanner sc = new Scanner(System.in);
    private boolean programStatus = true;

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

        BoardRepository boardRepository = new BoardRepository();
        PostRepository postRepository = new PostRepository();

        BoardService boardService = new BoardService(boardRepository);
        PostService postService = new PostService(postRepository);

        BoardController boardController = new BoardController(boardService);
        PostController postController = new PostController(postService, boardService);

        while( programStatus ) {

            String line = "https://" + domain;

            System.out.print(line);
            String command = sc.nextLine().trim();

            if ( command.equals(".exit") ) {
                System.out.println("Application is exited by admin");
                break;
            }

            Request request = new Request(command);

            if ( !request.isValid() ) {
                System.out.println("잘못된 형식의 입력입니다!");
                continue;
            }

            switch ( request.getControllerCode() ) {
                case "posts":
                    postController.requestHandle(request);
                    break;
                case "boards":
                    boardController.requestHandle(request);
                    break;
                default:
                    System.out.println("존재하지 않는 명령어");
            }

        }
    }

}
