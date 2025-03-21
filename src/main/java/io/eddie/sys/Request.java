package io.eddie.sys;

import java.util.Map;

public class Request {

    private UriParser parser;
    private Map<String, Object> parameters;

    public Request(String url) {
        this.parser = new UriParser(url);
        this.parameters = this.parser.getParameters();
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

    public String getTarget() {
        return this.parser.getTarget();
    }

    public String getControllerCode() {
        return this.parser.getControllerCode();
    }


}
