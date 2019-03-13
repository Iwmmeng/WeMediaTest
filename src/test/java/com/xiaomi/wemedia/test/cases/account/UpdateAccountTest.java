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
 * @date 19/3/11
 */
public class UpdateAccountTest {
    int id;
    private static String URL = "http://10.232.27.231:8084/api/account/7";
    private static String FILE = "test/data/account/update.json";

    private static String URLAccount = "http://10.232.27.231:8084/api/account/";
    private static String FILEAccount = "test/data/account/create.json";

    @DataProvider(name = "buildData")
    public Object[][] buildData() {
        return HttpUtil.buildCasesData(FILEAccount, "smoke");
    }
    @DataProvider(name = "updateData")
    public Object[][] updateData() {
        return HttpUtil.buildCasesData(FILE, "smoke");
    }



    @Test(dataProvider = "buildData")
    public void genId(Map mapParams, JSONObject expectRessult, String requestBody) {
        Response response = given()
                .cookie("userId","100006")
                .contentType("application/json;charset=UTF-8")
                .body(requestBody)
                .post(URLAccount);

        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
        Assert.assertEquals(mapResponse.get("code"), expectRessult.get("code"));
        id = Integer.parseInt(mapResponse.get("data").toString());

    }



    @Test(dataProvider = "updateData") // ,dependsOnMethods = "genId"
    public void updateTest(Map mapParams, JSONObject expectRessult, String requestBody) {
        Response response = given()
                .cookie("userId","100006")
                .contentType("application/json;charset=UTF-8")
                .body(requestBody)
                .post(URL);
        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
//        int meidaId = Integer.parseInt(mapResponse.get("data").toString());
        Assert.assertEquals(mapResponse.get("code"), expectRessult.get("code"));

    }
}
