package io.eddie.controller;

import io.eddie.data.Board;
import io.eddie.data.Post;
import io.eddie.service.BoardService;
import io.eddie.service.PostService;
import io.eddie.sys.Request;

import java.util.Scanner;

public class PostController implements Controller{

    private Scanner sc = new Scanner(System.in);

//    private PostService postService = new PostService();
//    private BoardService boardService = new BoardService();

    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @Override
    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {
            case "remove":
                try {

                    System.out.println("게시물을 삭제합니다!");
                    System.out.print("게시물 번호 : ");
                    String targetId = sc.nextLine().trim();

                    int postId = Integer.parseInt(targetId);
                    postService.removeById(postId);
                    System.out.println("성공적으로 삭제 되었습니다!");

                } catch ( NullPointerException e ) {
                    System.out.println("해당 게시물은 존재하지 않습니다!");
                } catch ( IndexOutOfBoundsException e ) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException  e) {
                    System.out.println("게시물 번호는 정수로 입력하여 주세요 :)");
                } catch ( Exception e ) {
                    System.out.println("알 수 없는 오류가 발생했습니다!");
                    e.printStackTrace();
                }

                break;
            case "edit":
                try {

                    System.out.println("게시물을 수정합니다!");
                    System.out.print("게시물 번호 : ");
                    String targetId = sc.nextLine().trim();

                    int postId = Integer.parseInt(targetId);

                    System.out.print("제목 : ");
                    String updateTitle = sc.nextLine().trim();

                    System.out.print("내용 : ");
                    String updateBody = sc.nextLine().trim();

                    postService.update(postId, updateTitle, updateBody);

                    System.out.println("게시물 수정이 완료되었습니다!");

                } catch ( NullPointerException e ) {
                    System.out.println("해당 게시물은 존재하지 않습니다!");
                } catch ( IndexOutOfBoundsException e ) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException  e) {
                    System.out.println("게시물 번호는 정수로 입력하여 주세요 :)");
                } catch ( Exception e ) {
                    System.out.println("알 수 없는 오류가 발생했습니다!");
                    e.printStackTrace();
                }
                break;
            case "view":
                try {
                    System.out.println("게시물을 조회합니다");
                    System.out.println("게시물 번호는 양의 정수로 입력해주세요");

                    System.out.print("게시물 번호 : ");
                    String targetIdStr = sc.nextLine().trim();
                    int targetId = Integer.parseInt(targetIdStr);

                    Post findPost = postService.getById(targetId);

                    System.out.println("게시물 번호 : " + findPost.getId());
                    System.out.println("게시물 제목 : " + findPost.getTitle());
                    System.out.println("게시물 내용 : " + findPost.getBody());
                    System.out.println("게시물 생성일 : " + findPost.getCreatedAt());

                } catch ( NullPointerException e ) {
                    System.out.println("해당 게시물은 존재하지 않습니다!");
                } catch ( IndexOutOfBoundsException e ) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException  e) {
                    System.out.println("게시물 번호는 정수로 입력하여 주세요 :)");
                } catch ( Exception e ) {
                    System.out.println("알 수 없는 오류가 발생했습니다!");
                    e.printStackTrace();
                }
                break;
            case "add":

                try {
                    System.out.println("게시판을 선택합니다!");
                    System.out.print("게시판 번호를 정수로 입력해주세요 : ");
                    String selectBoardId = sc.nextLine().trim();

                    int boardId = Integer.parseInt(selectBoardId);

                    Board findBoard = boardService.getBoardById(boardId);

                    if ( findBoard == null ) {
                        System.out.println("해당 게시판은 존재하지 않습니다!");
                        return;
                    }

                    System.out.println(findBoard.getBoardName() + " 게시판이 선택되었습니다!");

                    System.out.println("게시글을 작성합니다.");

                    System.out.print("제목 : ");
                    String title = sc.nextLine().trim();

                    System.out.print("내용 : ");
                    String body = sc.nextLine().trim();

                    // 구조 변경
                    // id

                    Post savedPost = postService.save(title, body);

//                findBoard.getPostList().add(savedPost);
                    boardService.putPostAtBoard(findBoard, savedPost);

                    System.out.println(savedPost.getId() + "번 게시물 작성을 완료했습니다!");
                } catch ( Exception e ) {
                    System.out.println("요청을 확인해주세요!");
                }
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다!");
        }
    }

}
