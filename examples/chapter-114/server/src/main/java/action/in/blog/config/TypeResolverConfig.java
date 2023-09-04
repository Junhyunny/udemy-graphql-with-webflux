package action.in.blog.config;

import action.in.blog.domain.CustomerDTO;
import graphql.schema.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class TypeResolverConfig {

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(CustomerDTO.class, "Customer");
        return resolver;
    }

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver resolver) {
        return configurer -> configurer.type("CustomerResponse", builder -> builder.typeResolver(resolver));
    }
}
