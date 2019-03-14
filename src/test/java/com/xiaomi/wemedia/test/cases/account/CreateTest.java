package com.xiaomi.wemedia.test.cases.account;

import com.xiaomi.wemedia.test.base.TestBase;
import com.xiaomi.wemedia.test.util.HttpUtil;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author wangmeng
 * @date 19/3/6
 */


public class CreateTest extends TestBase {


    private static String URL = "http://10.232.27.231:8084/api/account/";
    private static String FILE = "test/data/account/create.json";
    private static String MIID;
    private static Cookie miidCookie ;
    private static Cookies cookies ;

    @DataProvider(name = "buildData")
    public Object[][] buildData() {
        return HttpUtil.buildCasesData(FILE, "smoke");
    }


    @BeforeMethod
    public void innitMiid(){
        MIID = String.valueOf(System.currentTimeMillis());
        miidCookie = new Cookie.Builder("userId", MIID).build();
//        mediaIdCookie = new Cookie.Builder("mediaAccountId", "").build();
        cookies = new Cookies(miidCookie);



    }

    @Test(dataProvider = "buildData")
    public void createTest(Map mapParams, JSONObject expectRessult, String requestBody) {
        Response response = given()
                .contentType("application/json;charset=UTF-8")
                .cookies(cookies)
                .body(requestBody)
                .post(URL);

        response.prettyPrint();
        Map mapResponse = response.body().as(HashMap.class);
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
