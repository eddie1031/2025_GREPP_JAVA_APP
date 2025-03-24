package io.eddie.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account extends BaseEntity {

    private int id;

    // 실제 로그인하기 위한 계정
    private String username;
    private String password;

    // 회원 실명
    private String name;
    private String email;

    private AuthType authType;

    private List<Post> postList = new ArrayList<>();

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Account(int id, String username, String password, String name, String email, AuthType authType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.authType = authType;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @Override
    public void stdout() {

        System.out.println(" === 회원 정보 === ");
        System.out.println("회원 계정 : " + username);
        System.out.println("회원 이메일 : " + email);
        System.out.println("회원 등급 : "+ this.authType.toString());
        System.out.println("회원 가입일 : " + createdAt);

    }

    public AuthType getAuthType() {
        return authType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
