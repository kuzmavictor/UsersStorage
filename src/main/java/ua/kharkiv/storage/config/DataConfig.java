package ua.kharkiv.storage.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "ua.kharkiv.storage",
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager")
public class DataConfig {

    private static final String PROP_DATABASE_CANADA_DRIVER = "db.canada.driver-class-name";
    private static final String PROP_DATABASE_CANADA_PASSWORD = "db.canada.password";
    private static final String PROP_DATABASE_CANADA_USERNAME = "db.canada.username";
    private static final String PROP_DATABASE_CANADA_URL = "db.canada.url";

    private static final String PROP_DATABASE_USA_DRIVER = "db.usa.driver-class-name";
    private static final String PROP_DATABASE_USA_PASSWORD = "db.usa.password";
    private static final String PROP_DATABASE_USA_USERNAME = "db.usa.username";
    private static final String PROP_DATABASE_USA_URL = "db.usa.url";
    public static final String ENTITY = "ua.kharkiv.storage.entity";

    private static final String PROP_HIBERNATE_HBM2DLL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Resource
    private Environment env;

    @Primary
    @Bean(name = "canadaDataSource")

    public DataSource canadaDataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_CANADA_DRIVER));
        config.setJdbcUrl(env.getRequiredProperty(PROP_DATABASE_CANADA_URL));
        config.setUsername(env.getRequiredProperty(PROP_DATABASE_CANADA_USERNAME));
        config.setPassword(env.getRequiredProperty(PROP_DATABASE_CANADA_PASSWORD));

        return new HikariDataSource(config);
    }

    @Bean(name = "usaDataSource")
    public DataSource usaDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_USA_DRIVER));
        config.setJdbcUrl(env.getRequiredProperty(PROP_DATABASE_USA_URL));
        config.setUsername(env.getRequiredProperty(PROP_DATABASE_USA_USERNAME));
        config.setPassword(env.getRequiredProperty(PROP_DATABASE_USA_PASSWORD));

        return new HikariDataSource(config);
    }

    @Bean("liquibase")
    public SpringLiquibase canadaLiquibase(
            @Qualifier("canadaDataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/canada/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);

        return liquibase;
    }

    @Bean
    @DependsOn("liquibase")
    public SpringLiquibase usaLiquibase(
            @Qualifier("usaDataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/usa/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);

        return liquibase;
    }

    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataBaseType.CANADA, canadaDataSource());
        targetDataSources.put(DataBaseType.USA, usaDataSource());

        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(canadaDataSource());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);

        return multiRoutingDataSource;
    }

    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(multiRoutingDataSource());
        em.setPackagesToScan(ENTITY);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties());

        return em;
    }

    @Bean(name = "multiTransactionManager")
    public PlatformTransactionManager multiTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(multiEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean(name = "dbSessionFactory")
    public LocalSessionFactoryBean dbSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(multiRoutingDataSource());
        sessionFactoryBean.setPackagesToScan(ENTITY);
        sessionFactoryBean.setHibernateProperties(jpaProperties());

        return sessionFactoryBean;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROP_HIBERNATE_HBM2DLL_AUTO,
                               env.getRequiredProperty(PROP_HIBERNATE_HBM2DLL_AUTO));
        properties.setProperty(PROP_HIBERNATE_DIALECT,
                               env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.setProperty(PROP_HIBERNATE_SHOW_SQL,
                               env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));

        return properties;
    }
}
