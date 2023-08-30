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
        var resolver = new ClassNameTypeResolver();
        resolver.addMapping(CustomerDTO.class, "Customer");
        return resolver;
    }

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver typeResolver) {
        return c -> c.type(
                "CustomerResponse", builder -> builder.typeResolver(typeResolver)
        );
    }
}
