package action.in.blog.config;

import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class OperationCachingConfig {

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(PreparsedDocumentProvider documentProvider) {
        return customizer -> customizer.configureGraphQl(
                builder -> builder.preparsedDocumentProvider(documentProvider)
        );
    }

    @Bean
    public PreparsedDocumentProvider documentProvider() {
        // 성능 향상을 위한 parsing, validation 로직 재사용
        final Map<String, PreparsedDocumentEntry> cache = new ConcurrentHashMap<>();
        return (executionInput, parseAndValidateFunction) ->
                cache.computeIfAbsent(
                        executionInput.getQuery(),
                        (key) -> parseAndValidateFunction.apply(executionInput)
                );
    }
}
