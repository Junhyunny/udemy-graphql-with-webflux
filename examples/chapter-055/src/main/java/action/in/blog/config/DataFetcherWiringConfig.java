package action.in.blog.config;

import action.in.blog.service.CustomerOrderDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@RequiredArgsConstructor
@Configuration
public class DataFetcherWiringConfig {

    private final CustomerOrderDataFetcher dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return configurer -> configurer.type(
                "Query",
                builder -> builder.dataFetcher("customers", dataFetcher)
        );
    }
}
