package org.oxerr.ipyy.client.spring.boot.autoconfigure;

import java.lang.reflect.InvocationTargetException;

import org.apache.http.HttpHost;
import org.oxerr.ipyy.client.core.IPYYClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ IPYYClient.class })
@EnableConfigurationProperties(IPYYClientProperties.class)
public class IPYYClientAutoConfiguration {

	private IPYYClientProperties properties;

	@Autowired
	public IPYYClientAutoConfiguration(IPYYClientProperties properties) {
		this.properties = properties;
	}

	@Bean
	@ConditionalOnClass(name = { "org.oxerr.ipyy.client.xml.IPYYXMLClient" })
	@ConditionalOnMissingBean(name = "org.oxerr.ipyy.client.xml.IPYYXMLClient")
	public IPYYClient ipyyXmlClient() {
		return this.createClient("org.oxerr.ipyy.client.xml.IPYYXMLClient");
	}

	@Bean
	@ConditionalOnClass(name = { "org.oxerr.ipyy.client.json.IPYYJsonClient" })
	@ConditionalOnMissingBean(name = "org.oxerr.ipyy.client.json.IPYYJsonClient")
	public IPYYClient ipyyJsonClient() {
		return this.createClient("org.oxerr.ipyy.client.json.IPYYJsonClient");
	}

	private IPYYClient createClient(String className) {
		try {
			return this.newClient(className);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private IPYYClient newClient(String className)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		IPYYClient client = (IPYYClient) Class.forName(className)
			.getConstructor(String.class, String.class)
			.newInstance(
				this.properties.getAccount(),
				this.properties.getPassword()
			);
		client.setConnectTimeout(this.properties.getConnectTimeout());
		client.setSocketTimeout(this.properties.getSocketTimeout());
		if (this.properties.getProxy() != null
			&& this.properties.getProxy().length() > 0) {
			client.setProxy(HttpHost.create(this.properties.getProxy()));
		}
		return client;
	}

}
