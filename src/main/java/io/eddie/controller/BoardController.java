package io.eddie.controller;

import io.eddie.data.Board;
import io.eddie.data.Post;
import io.eddie.service.BoardService;
import io.eddie.sys.Request;

import java.util.List;
import java.util.Scanner;

public class BoardController implements Controller{

    private final Scanner sc;
    private final BoardService boardService;

    public BoardController(Scanner sc, BoardService boardService) {
        this.sc = sc;
        this.boardService = boardService;
    }

    @Override
    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {

            case "add":
                System.out.println("form body");
                System.out.print("boardName : ");
                String createBoardName = sc.nextLine().trim();
                System.out.print("description : ");
                String createBoardDesc = sc.nextLine().trim();

                boardService.createBoard(createBoardName, createBoardDesc);

                System.out.println("[201] 게시판이 성공적으로 생성되었습니다.");
                break;
            case "edit":

                if ( !request.hasParam("boardId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                Integer updateBoardId = request.getValue("boardId", Integer.class);

                System.out.println("form body:");
                System.out.print("boardName : ");
                String updateBoardName = sc.nextLine().trim();
                System.out.print("description : ");
                String updateBoardDesc = sc.nextLine().trim();

                boardService.updateBoard(updateBoardId, updateBoardName, updateBoardDesc);
                break;
            case "remove":

                if ( !request.hasParam("boardId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                Integer removeBoardId = request.getValue("boardId", Integer.class);

                boolean result = boardService.removeBoardById(removeBoardId);

                if ( result ) {
                    System.out.println("성공적으로 게시판이 삭제 되었습니다!");
                } else {
                    System.out.println("게시판 삭제에 실패하였습니다. 다시 시도해주세요.");
                }
                break;
            case "view":

                if ( !request.hasParam("boardId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                Integer getBoardId = request.getValue("boardId", Integer.class);

                Board findBoard = boardService.getBoardById(getBoardId);

                if ( findBoard == null ) {
                    System.out.println("해당 게시판은 존재하지 않습니다.");
                    break;
                }

                List<Post> postList = findBoard.getPostList();

                if (postList.isEmpty()) {
                    System.out.println("아직 작성된 글이 없네요, 첫 글의 주인공이 되어보세요!");
                } else {
                    findBoard.stdout();
                }
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다!");
        }

    }


}
