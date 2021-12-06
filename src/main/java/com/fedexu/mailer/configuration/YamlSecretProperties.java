package com.fedexu.mailer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:secret.yaml", factory = YamlPropertySourceFactory.class)
public class YamlSecretProperties {
    private String SENDGRID_API_KEY;
    private String TOEMAIL;
    private String FROMEMAIL;
}
