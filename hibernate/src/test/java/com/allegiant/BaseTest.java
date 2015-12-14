package com.allegiant;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by 许勇进 on 2015-10-27.
 */
public class BaseTest {
    public Logger logger;
    public SessionFactory sessionFactory;

    public BaseTest() {
        LoggerContext context = Configurator.initialize("testlog", "classpath:conf/log4j2.xml");
        logger = context.getLogger("testlog");
        Configuration configuration = new Configuration();
        configuration.configure("conf/hibernate.cfg.xml");
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
}
