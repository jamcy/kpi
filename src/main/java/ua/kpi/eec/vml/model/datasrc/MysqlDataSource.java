package ua.kpi.eec.vml.model.datasrc;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ua.kpi.eec.vml.common.ConfigurationProperties;

public class MysqlDataSource {
	private static MysqlDataSource instance = null;

	private SessionFactory factory = null;

	private MysqlDataSource() {
		try {
			Configuration configuration = new Configuration();
			configuration.setNamingStrategy(new ImprovedNamingStrategy());
			//File config = new File(ConfigurationProperties.getRootDir() + "hibernate.cfg.xml");
			//configuration.configure(config);
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			this.factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SessionFactory getFactory() {
		return factory;
	}

	/**
	 * Singleton pattern implementation.
	 * 
	 * @return
	 */
	public static MysqlDataSource getInstance() {
		if (instance == null) {
			instance = new MysqlDataSource();
		}
		return instance;
	}
}
