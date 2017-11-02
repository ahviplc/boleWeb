package com.baorenai.bole.config;

import java.util.Properties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.baorenai.bole.mapper")
public class MyBatisConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("bole.jdbc.driverClassName"));
        props.put("url", env.getProperty("bole.jdbc.url"));
        props.put("username", env.getProperty("bole.jdbc.username"));
        props.put("password", env.getProperty("bole.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源
        fb.setTypeAliasesPackage("com.baorenai.bole.model");// 指定基类包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));//指定xml文件位置
        return fb.getObject();
    }
}