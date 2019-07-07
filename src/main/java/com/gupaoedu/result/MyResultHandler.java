package com.gupaoedu.result;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qingshan
 * @Date: 2019/3/18 13:38
 * @Description: 咕泡学院，只为更好的你
 * 对返回的结果进行处理，最终得到自己想要的数据格式或类型
 * 可以自定义返回类型，在接口方法中传入，不需要定义
 */
public class MyResultHandler implements ResultHandler {
    @SuppressWarnings("rawtypes")
    private final Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext resultContext) {
        Map map = (Map) resultContext.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));
    }

    public Map getMappedResults() {
        return mappedResults;
    }
}
