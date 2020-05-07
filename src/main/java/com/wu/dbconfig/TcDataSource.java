package com.wu.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author:zps
 * @Date:2018/9/17
 */
@Configuration
@MapperScan(basePackages = "fundway.brainapp.trafficControl.business.TC.mapper", sqlSessionTemplateRef  = "tcSqlSessionTemplate")
public class TcDataSource {

    @Bean(name = "tcData")
    @ConfigurationProperties(prefix = "spring.datasource.tc") // application.properteis中对应属性的前缀
    @Primary
    public DataSource tcData() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tcSqlSessionFactory")
    @Primary
    public SqlSessionFactory tcSqlSessionFactory(@Qualifier("tcData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/TC/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "tcTransactionManager")
    @Primary
    public DataSourceTransactionManager sentinelTransactionManager(@Qualifier("tcData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tcSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate tcSqlSessionTemplate(@Qualifier("tcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
