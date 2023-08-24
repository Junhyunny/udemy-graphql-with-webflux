package action.in.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static graphql.scalars.ExtendedScalars.*;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return configurer -> configurer
                .scalar(GraphQLLong)
                .scalar(GraphQLByte)
                .scalar(GraphQLShort)
                .scalar(GraphQLBigDecimal)
                .scalar(GraphQLBigInteger)
                .scalar(Date)
                .scalar(LocalTime)
                .scalar(DateTime)
                .scalar(Object)
                ;
    }
}