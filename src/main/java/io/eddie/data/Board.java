package io.eddie.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Board extends BaseEntity {

    private int id;

    private String boardName;
    private String description;

    private List<Post> postList = new ArrayList<>();

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Board(int id, String boardName, String description) {
        this.id = id;
        this.boardName = boardName;
        this.description = description;

        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();

    }

    public void stdout() {
        System.out.println(" 글 번호 / 글 제목 / 작성일 ");
        for (Post post : postList) {
            System.out.printf("%s / %s / %s \n", post.getId(), post.getTitle(), post.getCreatedAt());
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
