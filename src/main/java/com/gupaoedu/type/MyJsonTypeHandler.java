package com.gupaoedu.type;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author JAY
 * @Date 2019/7/6 10:13
 * @Description TODO
 **/
public class MyJsonTypeHandler extends BaseTypeHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(MyJsonTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        logger.info("i:" + i);
        logger.info("parameter:" + parameter);
        logger.info("jdbcType:" + jdbcType);
        //将List<Map<String, Object>> 转成字符串保存到数据库
        ps.setString(i,JSONObject.toJSON(parameter).toString());
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        //将varchar类型数据转成List<Map<String, Object>> 封装的java对象中
        return JSONObject.parseObject(string, ArrayList.class);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);

        //将varchar类型数据转成List<Map<String, Object>> 封装的java对象中
        return JSONObject.parseObject(string, ArrayList.class);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
