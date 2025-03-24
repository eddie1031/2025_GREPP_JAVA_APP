package io.eddie.config;

public class AccessUrlConfig {

    // 로그인을 하지 않아야만 접근 가능한 URL 목록
    public static String[] anonymousUrlList = {
            "/accounts/signin",
            "/accounts/signup"
    };

    // 관리자만 접근 가능한 URL 목록
    public static String[] hasAdminAuthUrlList = {
            "/boards/add",
            "/boards/edit",
            "/boards/remove"
    };

    // 로그인을 하여야만 접근 가능한 URL 목록
    public static String[] needSignInUrlList = {
            "/posts/add",
            "/posts/edit",
            "/posts/remove",
            "/accounts/edit",
            "/accounts/remove",
            "/accounts/signout"
    };

}
