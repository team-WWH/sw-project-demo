package com.example.wwh;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.wwh.Mapper")  // 扫描指定包中的 Mapper 接口
@EnableTransactionManagement  // 启用事务管理
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        // 配置数据源
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/wwh")  // 更改为实际数据库地址
                .username("root")  // 数据库用户名
                .password("zhuiluo0")  // 数据库密码
                .driverClassName("com.mysql.cj.jdbc.Driver")  // MySQL 驱动
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 配置 SqlSessionFactory
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        // 配置 SqlSessionTemplate，用于执行 SQL 操作
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        // 配置事务管理器
        return new DataSourceTransactionManager(dataSource);
    }
}

