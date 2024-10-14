package ru.practicum.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.StatRestTemplateClient;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class EWMServiceAppConfig {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

/*    @Bean
    public StatsClient statsClient(RestTemplateBuilder builder) {
        return new StatsClient(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:9090"))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build());
    }*/

    @Bean
    public StatRestTemplateClient statRestTemplateClient(RestTemplateBuilder builder) {
        return new StatRestTemplateClient(builder);
    }
}
