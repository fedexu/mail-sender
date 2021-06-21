package com.fedexu.mailer.configuration;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
@AutoConfigureAfter(value = YamlSecretProperties.class)
public class BeanConfiguration {

    @Autowired
    private YamlSecretProperties yamlSecretProperties;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(3);
        return threadPoolTaskScheduler;
    }

    @Bean
    public SendGrid SendGrid() {
        return new SendGrid(yamlSecretProperties.getSENDGRID_API_KEY());
    }


}
