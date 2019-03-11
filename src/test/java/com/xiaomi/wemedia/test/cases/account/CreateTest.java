package com.xiaomi.wemedia.test.cases.account;

import com.xiaomi.wemedia.test.util.HttpUtil;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author wangmeng
 * @date 19/3/6
 */


public class CreateTest {


//    private static String URL = "http://10.38.164.248:8123/api/account/categories";
    private static String URL = "http://10.232.27.231:8084/api/account";
    private static String FILE = "test/data/account/create.json";

    @DataProvider(name = "buildData")
    public Object[][] buildData() {
        return HttpUtil.buildCasesData(FILE, "smoke");
    }

    @Test(dataProvider = "buildData")
    public void createTest(Map mapParams, JSONObject expectRessult, JSONObject requestBody) {
        System.out.println("mapParams" + mapParams);
        Response response = given()
                .cookie("userId","120000")
                .contentType("application/json;charset=UTF-8")
                .body(mapParams.toString())
                .post(URL);

        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
//        int meidaId = Integer.parseInt(mapResponse.get("data").toString());
        Assert.assertEquals(mapResponse.get("code"), expectRessult.get("code"));

    }

    @Test
    public void test02(){
        String url2= "http://10.232.27.231:8084/api/account/categories";
        Response response = given()
                .cookie("userId","110000")
                .contentType("application/json;charset=UTF-8")
                .post(url2);

        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
//        int meidaId = Integer.parseInt(mapResponse.get("data").toString());
//        Assert.assertEquals(mapResponse.get("code"), expectRessult.get("code"));

    }


}
