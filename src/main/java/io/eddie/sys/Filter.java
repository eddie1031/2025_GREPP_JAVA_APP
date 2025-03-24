package io.eddie.sys;

import io.eddie.config.AccessUrlConfig;
import io.eddie.data.Account;
import io.eddie.data.AuthType;
import io.eddie.data.UrlAuthType;
import io.eddie.service.AccountService;

public class Filter {

    private final Request request;
    private final AccountService accountService;

    public Filter(Request request) {
        this.request = request;
        accountService = Container.accountService;
    }

    public boolean isValidRequest() {

        String originUrl = request.getOriginUrl();
        UrlAuthType urlAuthType = sorting(originUrl);
        boolean isLogon = request.isLogon();

        if ( urlAuthType == UrlAuthType.ANONYMOUS ) {
            if ( isLogon ) {
                return false;
            }
        } else if ( urlAuthType == UrlAuthType.ADMIN ) {

            if ( !isLogon ) {
                return false;
            }

            Account findUser = accountService.findByUsername(request.getLogonUsername());

            if ( findUser.getAuthType() != AuthType.ADMIN ) {
                return false;
            }

        } else if ( urlAuthType == UrlAuthType.HAS_AUTH ) {
            if ( !isLogon ) {
                return false;
            }
        }

        return true;
    }

    private UrlAuthType sorting(String url) {

        if ( isAnonymous(url) ) {
            return UrlAuthType.ANONYMOUS;
        }

        if ( isNeedAdminAuth(url) ) {
            return UrlAuthType.ADMIN;
        }

        if ( isNeedSignIn(url) ) {
            return UrlAuthType.HAS_AUTH;
        }

        return UrlAuthType.PERMIT_ALL;
    }

    private boolean isNeedSignIn(String url) {

        String[] targetList = AccessUrlConfig.needSignInUrlList;

        for (String target : targetList) {
            if ( url.equals(target)) {
                return true;
            }
        }

        return false;
    }

    private boolean isNeedAdminAuth(String url) {

        String[] targetUrl = AccessUrlConfig.hasAdminAuthUrlList;

        for (String target : targetUrl) {
            if ( url.equals(target) ) {
                return true;
            }
        }

        return false;
    }

    private boolean isAnonymous(String url) {

        String[] targetList = AccessUrlConfig.anonymousUrlList;

        for (String target : targetList) {
            if ( url.equals(target) ) {
                return true;
            }
        }

        return false;
    }

}
