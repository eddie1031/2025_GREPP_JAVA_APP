package io.eddie.controller;

import io.eddie.data.Post;
import io.eddie.sys.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostController {

    private Scanner sc = new Scanner(System.in);
    private int sequence = 0;
    private List<Post> postList = new ArrayList<>();

    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {
            case "remove":
                try {

                    System.out.println("게시물을 삭제합니다!");
                    System.out.print("게시물 번호 : ");
                    String targetId = sc.nextLine().trim();

                    int postId = Integer.parseInt(targetId);

                    Post findPost = postList.get(postId - 1);

                    if ( findPost != null ) {
                        postList.set(postId - 1, null);
                        System.out.println("성공적으로 삭제 되었습니다!");
                    }

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

                    Post findPost = postList.get(postId - 1);

                    System.out.print("제목 : ");
                    String updateTitle = sc.nextLine().trim();

                    System.out.print("내용 : ");
                    String updateBody = sc.nextLine().trim();

                    findPost.setTitle(updateTitle);
                    findPost.setBody(updateBody);
                    findPost.setUpdatedAt(LocalDate.now());

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

                    Post findPost = postList.get(targetId - 1);

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
                System.out.println("게시글을 작성합니다.");

                System.out.print("제목 : ");
                String title = sc.nextLine().trim();

                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                Post newPost = new Post(++sequence, title, body);
                postList.add(newPost);

                System.out.println(newPost.getId() + "번 게시물 작성을 완료했습니다!");

                break;
        }
    }

}
