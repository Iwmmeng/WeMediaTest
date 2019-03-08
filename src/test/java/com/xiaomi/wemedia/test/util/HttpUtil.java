package com.xiaomi.wemedia.test.util;

import com.xiaomi.wemedia.test.common.SysConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangmeng
 * @date 19/3/6
 */
public class HttpUtil {



    public static Object[][] buildCasesData(String filePath, String caseType) {
        JSONObject json = getJsonDataOfFile(filePath);
        System.out.println(caseType);
        JSONArray dataJsonArray = json.getJSONArray(caseType);
        Object[][] datas = new Object[dataJsonArray.size()][3];

        for(int i = 0; i < dataJsonArray.size(); ++i) {
            JSONObject dataJson = JSONObject.fromObject(dataJsonArray.get(i));
            JSONObject paramJson = JSONObject.fromObject(dataJson.get("param"));
            HashMap params = (HashMap)JSONObject.toBean(paramJson, HashMap.class);
            JSONObject resultJson;
            if(dataJson.containsKey("request_body")) {
                resultJson = JSONObject.fromObject(dataJson.get("request_body"));
                datas[i][2] = resultJson.toString();
            }

            resultJson = JSONObject.fromObject(String.valueOf(dataJson.get("result")));
            datas[i][0] = params;
            datas[i][1] = resultJson;
        }

        return datas;
    }
    public static Map buildParamsFromJson(JSONObject dataJson, Boolean isSign) {

        JSONObject paramJson = JSONObject.fromObject(dataJson.get("param"));

        Map paramsMap = (Map) JSONObject.toBean(paramJson, TreeMap.class);
        if(null == paramsMap) {
            paramsMap = new HashMap();
        }

//        if(((Map)params).containsKey("userToken")) {
//            ((Map)params).put("userToken", SysConfig.getProperty("google.token"));
//        }

//        ((Map)params).put("version_code", "20180115");
//        ((Map)params).put("server_code", "100");
//        ((Map)params).put("timestamp", TIMESTAMP);
//        String genUrlParam = genUrlParam((Map)params, isSign);
        return paramsMap;
    }
    public static JSONObject getJsonDataOfFile(String filePath) {
//        String path = HttpUtil.class.getClassLoader().getResource(filePath).getPath();
//        System.out.println("Data OF file path"+path);
        String dataStr = SysConfig.readerFile(filePath);
        return JSONObject.fromObject(dataStr);
    }




}
