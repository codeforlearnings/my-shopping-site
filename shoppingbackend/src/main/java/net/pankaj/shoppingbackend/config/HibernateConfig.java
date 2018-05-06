package net.pankaj.shoppingbackend.config;

import java.util.Properties;

import javax.activation.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"net.pankaj.shoppingbackend.dto"})
@EnableTransactionManagement

public class HibernateConfig {
	// change the below based on the DBMS you choose
private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/myshoppingsite"; 
private final static String DATABASE_DRIVER="org.h2.Driver";
private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
private final static String DATABASE_USERNAME="pankaj";
private final static String DATABASE_PASSWORD="";

// dataSource bean will be available			
			
@Bean
public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	
	//providing the database connection information
	
	dataSource.setDriverClassName(DATABASE_DRIVER);
	dataSource.setUrl(DATABASE_USERNAME);
	dataSource.setPassword(DATABASE_PASSWORD);
	return (DataSource) dataSource;
}
		
//sessionFactory bean will be available	
@Bean
public SessionFactory getSessionFactory(DataSource dataSource) {
	
LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder((javax.sql.DataSource) dataSource);

builder.addProperties(getHibernateProperties());
builder.scanPackages("net.pankaj.shoppingbackend.dto");


return builder.buildSessionFactory();
}

// all the hibernate properties will be return in this methad


private Properties getHibernateProperties() {
	Properties properties = new Properties();
	properties.put("hibernate.dialect",DATABASE_DIALECT);
	properties.put("hibernate.show_sql","true");

	properties.put("hibernate.format_sql","true");


	return properties;
}
@Bean
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
return transactionManager;

}

}
