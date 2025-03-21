package io.eddie.sys;

import io.eddie.controller.BoardController;
import io.eddie.controller.PostController;

import java.util.Scanner;

public class Application {

    private String domain;

    private Scanner sc = new Scanner(System.in);
    private boolean programStatus = true;

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

        PostController postController = new PostController();
        BoardController boardController = new BoardController();

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
