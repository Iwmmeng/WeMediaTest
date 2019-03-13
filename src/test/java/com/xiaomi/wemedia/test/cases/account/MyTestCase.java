package com.xiaomi.wemedia.test.cases.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyTestCase {

    @Test
    public void jsonTest() throws IOException {

        InputStream inputStream = MyTestCase.class.getClassLoader().getResource("test/data/account/create.json").openStream();
        JSONObject jsonObject = JSON.parseObject(inputStream, JSONObject.class);
        JSONObject smoke = jsonObject.getJSONArray("smoke").getJSONObject(0);
        Map<String,String> actualResult= new HashMap<>();
        actualResult.put("code","321");
        actualResult.put("msg","321");
        actualResult.put("data","321");
        smoke.put("actual_result",actualResult);
        FileUtils.write();
        System.out.println(jsonObject.toJSONString());
    }

}
