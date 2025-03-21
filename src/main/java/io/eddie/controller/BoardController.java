package io.eddie.controller;

import io.eddie.data.Board;
import io.eddie.data.Post;
import io.eddie.service.BoardService;
import io.eddie.sys.Request;

import java.util.List;
import java.util.Scanner;

public class BoardController {

    private Scanner sc = new Scanner(System.in);
    private BoardService boardService = new BoardService();

    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {

            case "add":
                System.out.println("게시판을 생성합니다!");

                System.out.print("게시판 이름 : ");
                String createBoardName = sc.nextLine().trim();
                System.out.print("게시판 설명 : ");
                String createBoardDesc = sc.nextLine().trim();

                int createdBoardId = boardService.createBoard(createBoardName, createBoardDesc);

                System.out.println(createdBoardId + "번 게시판이 생성 되었습니다!");
                break;
            case "edit":
                System.out.println("게시판을 수정합니다!");
                System.out.println("게시판 번호를 정수(양의정수)를 입력해주세요.");

                System.out.print("게시판 번호 : ");
                String updateId = sc.nextLine().trim();

                int updateBoardId = Integer.parseInt(updateId);

                System.out.print("게시판 제목 : ");
                String updateBoardName = sc.nextLine().trim();
                System.out.print("게시판 설명 : ");
                String updateBoardDesc = sc.nextLine().trim();

                boardService.updateBoard(updateBoardId, updateBoardName, updateBoardDesc);
                break;
            case "remove":
                System.out.println("게시판을 삭제합니다!");
                System.out.println("게시판 번호를 정수(양의정수)를 입력해주세요.");

                System.out.print("게시판 번호 : ");
                String removeId = sc.nextLine().trim();

                int removeBoardId = Integer.parseInt(removeId);

                boolean result = boardService.removeBoardById(removeBoardId);

                if ( result ) {
                    System.out.println("성공적으로 게시판이 삭제 되었습니다!");
                } else {
                    System.out.println("게시판 삭제에 실패하였습니다. 다시 시도해주세요.");
                }
                break;
            case "view":

                System.out.println("게시판을 조회합니다.");
                System.out.println("게시판 이름을 입력해주세요!");
                System.out.print("게시판 이름 : ");
                String boardName = sc.nextLine().trim();

                Board findBoard = boardService.getBoardByName(boardName);

                if ( findBoard == null ) {
                    System.out.println("해당 게시판은 존재하지 않습니다.");
                    break;
                }

                List<Post> postList = findBoard.getPostList();

                if (postList.isEmpty()) {
                    System.out.println("아직 작성된 글이 없네요, 첫 글의 주인공이 되어보세요!");
                } else {
                    System.out.println(" 글 번호 / 글 제목 / 작성일 ");
                    for (Post post : postList) {
                        System.out.printf("%s / %s / %s \n", post.getId(), post.getTitle(), post.getCreatedAt());
                    }
                }
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다!");
        }

    }


}
