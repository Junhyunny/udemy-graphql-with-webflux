package action.in.blog.config;

import action.in.blog.domain.FruitDTO;
import graphql.schema.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static graphql.scalars.ExtendedScalars.Date;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver typeResolver) {
        return configurer -> configurer
                .scalar(Date)
                .type("Product", builder -> builder.typeResolver(typeResolver))
                ;
    }

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(FruitDTO.class, "Fruit");
        return resolver;
    }
}
