package org.oxerr.ipyy.client.spring.boot.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.oxerr.ipyy.client.json.IPYYJsonClient;
import org.oxerr.ipyy.client.xml.IPYYXMLClient;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class IPYYClientAutoConfigurationTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
		.withConfiguration(AutoConfigurations.of(IPYYClientAutoConfiguration.class));

	@Test
	void test() {
		this.contextRunner
			.withPropertyValues("org.oxerr.ipyy.client.account=123")
			.withClassLoader(new FilteredClassLoader(IPYYJsonClient.class))
			.run((context) -> {
				assertThat(context).hasSingleBean(IPYYXMLClient.class);
			});
		this.contextRunner
			.withPropertyValues("org.oxerr.ipyy.client.account=123")
			.withClassLoader(new FilteredClassLoader(IPYYXMLClient.class))
			.run((context) -> {
				assertThat(context).hasSingleBean(IPYYJsonClient.class);
			});
	}

}
