
## Chapter 67. Type Resolver Configuration

* 스키마에 정의한 타입 이름과 클래스 이름은 반드시 매칭되어야 한다.
* 다음과 같은 스키마가 있다.

```graphqls
scalar Date

interface Product {
    id: ID
    description: String
    price: Int
}

type Fruit implements Product {
    id: ID
    description: String
    price: Int
    expiryDate: Date
}

type Electronics implements Product {
    id: ID
    description: String
    price: Int
    brand: Brand
}

type Book implements Product {
    id: ID
    description: String
    price: Int
    author: String
}

enum Brand {
    APPLE
    SAMSUNG
}

type Query {
    products: [Product]!
}
```

* Fruit 클래스 이름을 FruitDTO로 변경한 후 데이터를 요청하면 다음과 같은 에러가 발생한다.

```json
{
  "errors": [
    {
      "message": "Can't resolve '/products[0]'. Abstract type 'Product' must resolve to an Object type at runtime for field '[Product].products'. Could not determine the exact type of 'Product'",
      "path": [
        "products",
        0
      ],
      "extensions": {
        "classification": "DataFetchingException"
      }
    }
  ],
  "data": {
    "products": [
      null,
      {
        "id": "94af35c5-eda9-409b-a74c-1804ab3309aa",
        "price": 400000,
        "description": "Macbook",
        "type": "Electronics",
        "brand": "APPLE"
      },
      {
        "id": "ea326968-5fbd-4b33-bcbe-981465d0da26",
        "price": 32000,
        "description": "Clean Code",
        "type": "Book",
        "author": "Bob"
      }
    ]
  }
}
```

* 백엔드 서버에서 다음과 같은 예외 처리 스택 트레이스가 출력된다.

```
2023-08-24T15:13:51.906+09:00  WARN 62788 --- [ctor-http-nio-2] n.graphql.execution.ExecutionStrategy    : Can't resolve '/products[0]'. Abstract type 'Product' must resolve to an Object type at runtime for field '[Product].products'. Could not determine the exact type of 'Product'

graphql.execution.UnresolvedTypeException: Could not determine the exact type of 'Product'
	at graphql.execution.ResolveType.resolveAbstractType(ResolveType.java:64) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ResolveType.resolveTypeForInterface(ResolveType.java:53) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ResolveType.resolveType(ResolveType.java:39) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ExecutionStrategy.resolveType(ExecutionStrategy.java:723) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ExecutionStrategy.completeValue(ExecutionStrategy.java:470) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ExecutionStrategy.completeValueForList(ExecutionStrategy.java:576) ~[graphql-java-20.2.jar:na]
	at graphql.execution.ExecutionStrategy.completeValueForList(ExecutionStrategy.java:530) ~[graphql-java-20.2.jar:na]
```

* 다음과 같은 타입 리졸버(type resolver)를 추가하여 문제를 해결할 수 있다.

```java
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
```