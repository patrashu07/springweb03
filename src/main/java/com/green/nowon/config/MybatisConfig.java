package com.green.nowon.config;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration // 클래스에 적용 내부메소드에다가 @Bean이라는거 적용시킬수가있다 . @Bean으로 spring container 에 넣어서 저장을해서
@MapperScan(value ="com.green.nowon")				// 넣엇다썻다 한다.
public class MybatisConfig {
	// 스프링 에서는 sqlSessionFactory 객체를 sqlSessionFactoryBean 객체를 통해서 생성
	//
	@Autowired
	ApplicationContext ac;
	
	
	
	@Bean 
	SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfiguration(mybatisConfig());//mybatis camell에대한 표기법
		//member-mapper.xml등록
		//classpath: == /src/main/resources
		//classpath:mapper/**/*-이름2 =mapper 의 모든폴더에대상 이름2에해당하는 전부 
		String locationPattern ="classpath:mapper/member-mapper.xml";
		Resource[] mapperLocations =ac.getResources(locationPattern);
		//Resource... mapperLocations = Resource[] mapperLocations 
		//똑같은표현이다. 배열기호(...)는 파라미터에서만 사용가능 
		factoryBean.setMapperLocations(mapperLocations);//xml 표기 배열로 
		System.out.println("SqlSessionFactoryBean>>>>>>>>>>>>>>>>>>>적용");
		return factoryBean.getObject();
	}
	
	

	@Bean
	DataSource dataSource() {

		System.out.println("dataSource>>>>>>>>>>>>적용");
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	HikariConfig hikariConfig() {
		// TODO Auto-generated method stub
		System.out.println("hikariConfig>>>>>>>>>>>>적용");
		return new HikariConfig();
	}

	
	
	@Bean(name = "mybatisConfiguration")//bean
	@ConfigurationProperties(prefix = "mybatis.configuration") //여러개를 가지고올때 용이함 
	org.apache.ibatis.session.Configuration mybatisConfig() {
		// TODO Auto-generated method stub
		System.out.println("mybatis.configuration.map-underscore-to-camel-case");
		return new org.apache.ibatis.session.Configuration();
	}

	//sqlsessionTemplate -> mapper 구현할건데 세션닫기 세션 열기등등 이모든걸이게 관리해준다 dao도 
	//mybatis의 핵심 
	SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	
	
}