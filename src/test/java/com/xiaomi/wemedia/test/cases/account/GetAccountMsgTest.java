package com.xiaomi.wemedia.test.cases.account;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @author wangmeng
 * @date 19/3/11
 */
public class GetAccountMsgTest {

    private static String URL = "http://10.232.27.231:8084/api/account/6";


    @Test
    public void getMsg(){
        Response response = given()
                .cookie("userId","150000")
                .contentType("application/json;charset=UTF-8")
                .param("detailed",true)
                .get(URL);

        response.prettyPrint();
    }


}
