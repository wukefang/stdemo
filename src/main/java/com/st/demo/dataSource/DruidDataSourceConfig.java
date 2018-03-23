package com.st.demo.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class DruidDataSourceConfig {

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "druid");
        reg.addInitParameter("loginPassword", "druid");
        reg.addInitParameter("logSlowSql", "true");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean(name = "writeDB")
    @Primary
    public DruidDataSource getWriteDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/wkf?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("0415");
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(30);
        druidDataSource.setMaxWait(5000);
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    @Bean(name = "readDB")
    public DruidDataSource getReadDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/wkf?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=true");
        druidDataSource.setUsername("wkf");
        druidDataSource.setPassword("0415");
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(30);
        druidDataSource.setMaxWait(5000);
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource getDynamicDataSource() throws SQLException {
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBType.READ.getType(),getReadDataSource());
        targetDataSources.put(DBType.WRITE.getType(),getWriteDataSource());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(getWriteDataSource());
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDynamicDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }

}
