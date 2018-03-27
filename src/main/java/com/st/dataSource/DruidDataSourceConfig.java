package com.st.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.st.properties.DataSourceProperty;
import com.st.properties.DruidProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class DruidDataSourceConfig {

    @Bean
    public DruidProperty getDruidProperty(){
        return new DruidProperty();
    }
    @Bean
    public DataSourceProperty getDataSourceProperty(){
        return new DataSourceProperty();
    }



    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(getDruidProperty().getUrlMapping());
        reg.addInitParameter("loginUsername", getDruidProperty().getLoginUsername());
        reg.addInitParameter("loginPassword", getDruidProperty().getLoginPassword());
        reg.addInitParameter("logSlowSql", getDruidProperty().getLogSlowSql());
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(getDruidProperty().getUrlPatterns());
        filterRegistrationBean.addInitParameter("exclusions", getDruidProperty().getExclusions());
        filterRegistrationBean.addInitParameter("profileEnable", getDruidProperty().getProfileEnable());
        return filterRegistrationBean;
    }

    @Bean(name = "writeDB")
    @Primary
    public DruidDataSource getWriteDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(getDataSourceProperty().getWrite().getDriverClass());
        druidDataSource.setUrl(getDataSourceProperty().getWrite().getUrl());
        druidDataSource.setUsername(getDataSourceProperty().getWrite().getUsername());
        druidDataSource.setPassword(getDataSourceProperty().getWrite().getPassword());
        druidDataSource.setInitialSize(getDataSourceProperty().getWrite().getInitialSize());
        druidDataSource.setMaxActive(getDataSourceProperty().getWrite().getMaxActive());
        druidDataSource.setMaxWait(getDataSourceProperty().getWrite().getMaxWait());
        druidDataSource.setFilters(getDataSourceProperty().getWrite().getFilters());
        return druidDataSource;
    }

    @Bean(name = "readDB")
    public DruidDataSource getReadDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(getDataSourceProperty().getRead().getDriverClass());
        druidDataSource.setUrl(getDataSourceProperty().getRead().getUrl());
        druidDataSource.setUsername(getDataSourceProperty().getRead().getUsername());
        druidDataSource.setPassword(getDataSourceProperty().getRead().getPassword());
        druidDataSource.setInitialSize(getDataSourceProperty().getRead().getInitialSize());
        druidDataSource.setMaxActive(getDataSourceProperty().getRead().getMaxActive());
        druidDataSource.setMaxWait(getDataSourceProperty().getRead().getMaxWait());
        druidDataSource.setFilters(getDataSourceProperty().getRead().getFilters());
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
    public DataSourceTransactionManager getDataSourceTransactionManager() throws SQLException {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(getDynamicDataSource());
        return dataSourceTransactionManager;
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
