package io.eddie.sys;

import io.eddie.controller.Controller;

import java.util.Scanner;

public class Application {

    private final Scanner sc = Container.sc;

    private String domain;
    private boolean programStatus = true;

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

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

            Controller controller = getController(request.getControllerCode());

            if ( controller != null ) {
                controller.requestHandle(request);
            } else {
                System.out.println("잘못된 요청입니다.");
            }


        }
    }

    public Controller getController(String controllerCode) {

        switch ( controllerCode ) {
            case "posts":
                return Container.postController;
            case "boards":
                return Container.boardController;
            case "accounts":
                return Container.accountController;
            default:
                return null;
        }

    }


}
