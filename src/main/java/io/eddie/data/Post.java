package io.eddie.data;

import java.time.LocalDate;

public class Post extends BaseEntity {

    private int id;

    private String title;
    private String body;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void stdout() {
        System.out.println("게시물 상세 정보 === ");
        System.out.println("게시물 번호 : " + this.getId());
        System.out.println("게시물 제목 : " + this.getTitle());
        System.out.println("게시물 내용 : " + this.getBody());
        System.out.println("게시물 생성일 : " + this.getCreatedAt());
        System.out.println("게시물 수정일 : " + this.getUpdatedAt());
    }


    public Post(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
