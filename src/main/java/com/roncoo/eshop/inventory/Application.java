package com.roncoo.eshop.inventory;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.roncoo.eshop.inventory.listener.InitListener;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@MapperScan("com.roncoo.eshop.inventory.mapper")
public class Application {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource() {
		return new DataSource();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver
				.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}

	@Bean
	public JedisCluster JedisClusterFactory() {
		Set<HostAndPort> jedisculsterNodes = new HashSet<HostAndPort>();
		jedisculsterNodes.add(new HostAndPort("", 0));
		jedisculsterNodes.add(new HostAndPort("", 0));
		jedisculsterNodes.add(new HostAndPort("", 0));
		return new JedisCluster(jedisculsterNodes);
	}

	/**
	 * 注册监听器
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		ServletListenerRegistrationBean listenerBean 
		      = new ServletListenerRegistrationBean();
		listenerBean.setListener(new InitListener());
		return listenerBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
