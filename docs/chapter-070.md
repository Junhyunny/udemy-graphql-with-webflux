
## Chapter 70. GraphQL Union - Schema Design

* union 키워드를 사용하면 서로 다른 타입의 결과를 하나의 결과로 추상화할 수 있다.

```graphqls
type Query {
    search: [Result]!
}

union Result = Fruit | Electronics | Book

type Fruit {
    description: String
    expiryDate: String
}

type Electronics {
    id: ID!
    description: String
    price: Int
    brand: Brand
}

type Book {
    title: String
    author: String
}

enum Brand {
    APPLE
    SAMSUNG
}
```