package com.xiaomi.wemedia.test.cases.AccountTest;

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


public class Register {


    private static String URL = "http://10.38.164.248:8123/account/categories";
    private static String FILE = "/Users/wangmeng/code/we-media/src/test/rescources/AccountTest/register.json";

    @DataProvider(name ="buildData")
    public Object[][] buildData() {
        return HttpUtil.buildCasesData(FILE, "smoke");
    }

    @Test(dataProvider = "buildData")
    public  void registerTest(Map mapParams, JSONObject expectRessult){
        Response response = given()
                .contentType("application/json")
                .body(mapParams)
                .params(mapParams)
                .get(URL);

        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
        int meidaId = Integer.parseInt(mapResponse.get("data").toString());
        Assert.assertEquals(response.getStatusCode(),expectRessult.get("code"));


    }


}