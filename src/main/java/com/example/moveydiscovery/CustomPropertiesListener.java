package com.example.moveydiscovery;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Base64;
import java.util.Map;
import java.util.Properties;

public class CustomPropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private final ConfigClient configClient = new ConfigClient();

    private Map<String, String> sources;

    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties props = new Properties();
        sources = configClient.getSources("user", "sccs");
        props.put("spring.rabbitmq.password", getSecret("spring.rabbitmq.password"));
        props.put("spring.rabbitmq.username", getSecret("spring.rabbitmq.username"));
        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
    }

    private String getSecret(String key) {
        return new String(Base64.getDecoder().decode(sources.get(key)));
    }
}