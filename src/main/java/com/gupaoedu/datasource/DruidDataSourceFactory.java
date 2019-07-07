package com.gupaoedu.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: qingshan
 * @Date: 2019/3/15 00:44
 * @Description: 咕泡学院，只为更好的你
 * 自定义Druid数据源，全部使用默认配置
 */

public class DruidDataSourceFactory implements DataSourceFactory {
    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(this.props.getProperty("url"));
        dds.setPassword(this.props.getProperty("password"));
        dds.setUsername(this.props.getProperty("username"));
        dds.setDriverClassName(this.props.getProperty("driver"));
        try {
            dds.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }
}