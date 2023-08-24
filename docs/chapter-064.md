
## Chapter 64. GraphQL Interface - Schema Design

* Object는 다음과 같은 타입이 될 수 있다.
    * `Map<K, V>`
    * `Map<Map<...>>`
    * T
    * R
* GraphQL 타입 시스템은 인터페이스를 지원한다.

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

enum Brand {
    APPLE
    SAMSUNG
}

type Query {
    products: [Product]!
}
```