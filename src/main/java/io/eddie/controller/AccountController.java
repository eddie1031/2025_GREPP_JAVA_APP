package io.eddie.controller;

import io.eddie.data.Account;
import io.eddie.service.AccountService;
import io.eddie.sys.Request;

import java.util.Scanner;

public class AccountController implements Controller{

    private final Scanner sc;
    private final AccountService accountService;

    public AccountController(Scanner sc, AccountService accountService) {
        this.sc = sc;
        this.accountService = accountService;
    }

    @Override
    public void requestHandle(Request request) {

        switch ( request.getTarget() ) {
            case "signin":
                System.out.println("form body");

                System.out.print("username: ");
                String signInUsername = sc.nextLine().trim();
                System.out.print("password: ");
                String signInPassword = sc.nextLine().trim();

                boolean result = accountService.signIn(request, signInUsername, signInPassword);

                if ( result ) {
                    System.out.println("[200] 성공적으로 로그인 되었습니다!");
                } else {
                    System.out.println("[400] 잘못된 요청입니다.");
                }

                break;
            case "signout":
                accountService.signOut(request);
                System.out.println("[200] 성공적으로 로그아웃 되었습니다!");

                break;
            case "signup":
                System.out.println("form body");

                System.out.print("username : ");
                String username = sc.nextLine().trim();
                System.out.print("password : ");
                String password = sc.nextLine().trim();
                System.out.print("name : ");
                String name = sc.nextLine().trim();
                System.out.print("email : ");
                String email = sc.nextLine().trim();

                accountService.save(username, password, name, email);
                System.out.println("[201] 계정이 성공적으로 생성되었습니다!");
                break;
            case "detail":
                if ( !request.hasParam("accountId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                try {

                    Integer getAccountId = request.getValue("accountId", Integer.class);
                    Account findAccount = accountService.getById(getAccountId);

                    findAccount.stdout();

                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            case "edit":

                if ( !request.hasParam("accountId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                try {

                    Integer updateAccountId = request.getValue("accountId", Integer.class);

                    System.out.println("form body");
                    System.out.print("password : ");
                    String updatePassword = sc.nextLine().trim();
                    System.out.print("email : ");
                    String updateEmail = sc.nextLine().trim();

                    accountService.update(updateAccountId, updatePassword, updateEmail);

                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            case "remove":
                if ( !request.hasParam("accountId") ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }

                try {

                    Integer accountId = request.getValue("accountId", Integer.class);

                    if ( !accountService.remove(accountId) ) {
                        System.out.println("[404] 해당 계정은 찾을 수 없습니다.");
                    } else {
                        System.out.println("[204] 계정을 성공적으로 삭제했습니다!");
                    }

                } catch ( Exception e ) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                break;
            default:
                System.out.println("[400] 잘못된 요청입니다.");

        }

    }



}
