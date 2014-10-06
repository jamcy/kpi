package ua.kpi.eec.vml.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("config.properties")
public class ConfigurationProperties {

	@Autowired
	Environment env;

	public String getProperty(String name) {
		return env.getProperty(name);
	}

}
