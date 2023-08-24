
## Chapter 75. Operation Caching - Demo

* GraphQL 엔진은 내부적으로 쿼리에 대한 parsing, validation 작업을 수행한다.
* PreparsedDocumentProvider 빈을 만들면서 parsing, validation 로직에 대한 캐싱을 추가할 수 있다.

```java
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
```

* GraphQL 클라이언트 측에서도 파라미터를 사용하도록 쿼리를 작성해야지 정상적으로 캐싱이 가능하다.
* 아래 쿼리는 파라미터가 바뀔 때마다 캐싱이 수행되지 않는다.

```graphqls
{
  sayHello(name: "Jua")
}
```

* 아래 쿼리는 파라미터는 고정이고 변수의 값만 바뀌기 때문에 캐싱이 정상적으로 수행된다.

```graphqls
query Operation($name: String! = "Junhyunny") {
  sayHello(name: $name)
}
```

* 변수를 사용하면 정상적으로 캐시가 되지만, 만약 일부 클라이언트가 값을 바꿔가면서 요청을 보내면 정상적으로 캐싱이 되지 않는다.
* cache 맵에 계속 데이터가 추가되므로 out of memory 에러가 발생할 수 있다.
* `caffein` 이라는 라이브러리를 추천한다.