package io.eddie.sys;

import java.util.HashMap;
import java.util.Map;

public class UriParser {

    private String URI;
    private String controllerCode;
    private String target;

    private boolean isValidUri = true;
    private Map<String, Object> parameters = new HashMap<>();

    public UriParser(String url) {
        this.URI = parse(url);
    }

    protected String parse(String uri) {

        if ( !uri.startsWith("/") ) {
            isValidUri = false;
            return uri;
        }

        String[] uriSplit = uri.split("\\?", 2);

        if ( uriSplit.length == 2 ) {
            setParameters(uriSplit[1]);
        }

        String[] uriFront = uriSplit[0].split("/");

        if ( uriFront.length != 3 ) {
            isValidUri = false;
            return uri;
        }

        controllerCode = uriFront[1];
        target = uriFront[2];

        return uri;

    }

    protected void setParameters(String uriPart) {

        try {
            if (uriPart.contains("&")) {

                String[] split = uriPart.split("&");

                for (String s : split) {

                    String[] parameterData = s.split("=", 2);

                    if (parameterData[1].equals("")) {
                        throw new IllegalArgumentException("잘못된 파라미터 값이 입력 되었습니다. URL을 확인해주세요");
                    }

                    parameters.put(parameterData[0], parameterData[1]);
                }


            } else {

                String[] split = uriPart.split("=", 2);

                if (split[1].equals("")) {
                    throw new IllegalArgumentException("잘못된 파라미터 값이 입력 되었습니다. URL을 확인해주세요");
                }

                parameters.put(split[0], split[1]);

            }
        } catch ( ArrayIndexOutOfBoundsException | IllegalArgumentException e ) {
            this.isValidUri = false;
        }

    }

    protected Map<String, Object> getParameters() {
        return parameters;
    }

    protected String getURI() {
        return URI;
    }

    protected String getControllerCode() {
        return controllerCode;
    }

    protected String getTarget() {
        return target;
    }

    protected boolean isValidUri() {
        return isValidUri;
    }

}
