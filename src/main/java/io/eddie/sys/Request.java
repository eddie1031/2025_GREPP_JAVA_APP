package io.eddie.sys;

import java.util.Map;

public class Request {

    private UriParser parser;
    private Map<String, Object> parameters;

    private final String AUTH_STATUS_KEY_NAME = "logonMember";

    public Request(String url) {
        this.parser = new UriParser(url);
        this.parameters = this.parser.getParameters();
    }

    private Object getSessionAttribute(String key) {
        Session session = Container.session;
        return session.getAttribute(key);
    }

    private void setSessionAttribute(String key, Object value) {
        Session session = Container.session;
        session.setAttribute(key, value);
    }

    private void removeSessionAttribute(String key) {
        Session session = Container.session;
        session.removeAttribute(key);
    }

    public boolean isLogon() {
        return hasSessionAttribute(AUTH_STATUS_KEY_NAME);
    }

    public String getOriginUrl() {
        return parser.getURI();
    }

    public boolean hasSessionAttribute(String key) {
        Session session = Container.session;
        return session.hasAttribute(key);
    }

    public void signIn(String username) {
        setSessionAttribute(AUTH_STATUS_KEY_NAME, username);
    }

    public void signOut() {
        removeSessionAttribute(AUTH_STATUS_KEY_NAME);
    }

    public String getLogonUsername() {
        return (String) getSessionAttribute(AUTH_STATUS_KEY_NAME);
    }

    public boolean isValid() {
        return this.parser.isValidUri();
    }

    public <T> T getValue(String key, Class<T> cls) {

        Object value = parameters.get(key);

        if ( cls ==  Integer.class ) {
            return cls.cast(Integer.parseInt(value.toString()));
        } else if ( cls == Long.class ) {
            return cls.cast(Long.parseLong(value.toString()));
        } else if ( cls == Boolean.class ) {
            return cls.cast(Boolean.parseBoolean(value.toString()));
        }

        return cls.cast(value);

    }

    public boolean hasParam(String key) {
        return parameters.get(key) != null;
    }

    public String getTarget() {
        return this.parser.getTarget();
    }

    public String getControllerCode() {
        return this.parser.getControllerCode();
    }


}
