package io.eddie.controller;

import io.eddie.data.Board;
import io.eddie.data.Post;
import io.eddie.service.BoardService;
import io.eddie.service.PostService;
import io.eddie.sys.Request;

import java.util.Scanner;

public class PostController implements Controller {

    private final Scanner sc;

    private final PostService postService;
    private final BoardService boardService;

    public PostController(Scanner sc, PostService postService, BoardService boardService) {
        this.sc = sc;
        this.postService = postService;
        this.boardService = boardService;
    }

    @Override
    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {
            case "remove":
                try {

                    if ( !request.hasParam("postId") ) {
                        System.out.println("[400] 잘못된 요청입니다.");
                        return;
                    }

                    Integer postId = request.getValue("postId", Integer.class);

                    postService.removeById(postId);

                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            case "edit":
                try {

                    if ( !request.hasParam("postId") ) {
                        System.out.println("[400] 잘못된 요청입니다.");
                        return;
                    }

                    Integer postId = request.getValue("postId", Integer.class);

                    System.out.println("form body");
                    System.out.print("title : ");
                    String updateTitle = sc.nextLine().trim();

                    System.out.print("body : ");
                    String updateBody = sc.nextLine().trim();

                    postService.update(postId, updateTitle, updateBody);

                } catch ( NullPointerException e ) {
                    System.out.println("[404] 해당 게시물은 존재하지 않습니다!");
                }  catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다!");
                }
                break;
            case "view":
                try {

                    if ( !request.hasParam("postId")  ) {
                        System.out.println("[400] 잘못된 요청입니다!");
                        return;
                    }

                    Integer viewPostId = request.getValue("postId", Integer.class);

                    Post findPost = postService.getById(viewPostId);
                    findPost.stdout();

                } catch ( NullPointerException e ) {
                    System.out.println("[404] 해당 게시물은 존재하지 않습니다!");
                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            case "add":

                try {

                    if ( !request.hasParam("boardId") ) {
                        System.out.println("[400] 잘못된 요청입니다.");
                        return;
                    }

                    Integer boardId = request.getValue("boardId", Integer.class);

                    Board findBoard = boardService.getBoardById(boardId);

                    if ( findBoard == null ) {
                        System.out.println("[404] 해당 게시판은 존재하지 않습니다!");
                        return;
                    }

                    System.out.println("form body");
                    System.out.print("title : ");
                    String title = sc.nextLine().trim();

                    System.out.print("body : ");
                    String body = sc.nextLine().trim();

                    Post savedPost = postService.save(title, body);
                    boardService.putPostAtBoard(findBoard, savedPost);

                    System.out.println("[201] 게시물 작성을 완료했습니다!");

                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다!");
        }
    }

}
