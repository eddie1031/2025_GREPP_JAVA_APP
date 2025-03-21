package io.eddie.sys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UriParserTest {

    @Test
    @DisplayName("그냥 테스트")
    void test1() throws Exception {

        String uri = "/controller/target?id=1";

        UriParser parser = new UriParser(uri);
        parser.parse(uri);

        Map<String, Object> params = parser.getParameters();

        String findId = (String) params.get("id");

        System.out.println("findId = " + findId);

    }

    @Test
    @DisplayName("요청 테스트")
    void request_test() throws Exception {

        String command = "/controller/target?id=1";
        int expectedId = 1;


        Request request = new Request(command);

        boolean isValid = request.isValid();
        System.out.println("isValid : " + (isValid == true));

        Integer paramValue = request.getValue("id", Integer.class);
        System.out.println("paramValue = " + (paramValue == expectedId));

    }

}