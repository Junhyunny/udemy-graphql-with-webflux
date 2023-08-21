
## Chapter 4. GraphQL - Types

* GraphQL은 다음과 같은 스칼라(scalar) 타입들을 지원한다.

| Type | Description |
|:-:|:-:|
| Int | Integer |
| Float | Float |
| String | String |
| Boolean | Boolean |
| ID | Some Unique ID(Serialized as String) / UUID / Long |

* GraphQL은 다음과 같은 컬렉션(collection) 타입을을 지원한다.

| Type | Example | Description |
|:-:|:-:|:-:|
| [type] | [Int] | List<Integer> |

* 다음과 같은 예시를 들 수 있다.
    * `#`을 사용하면 주석을 자성할 수 있다.

```
type Customer {
    id: ID
    name: String
    age: Int
    products: [Product]
}

type Product {
    id: ID
    description: String
    price: Int
}
```

* 느낌표(!)를 사용하면 Not nullable 항목들을 지정할 수 있다.

```
type Customer {
    id: ID! // never be null
    name: String! // never be null
    age: Int
    products: [Product]
}

type Product {
    id: ID! // never be null
    description: String
    price: Int
}
```

* enum 타입을 만들 수 있다.

```
enum Brand {
    HOMDA
    BMW
    TOYOTA
}

type Car {
    id: ID!
    year: Int
    brand: Brand
}
```

* 특별한 타입들이 존재한다.

| Type | Description |
|:-:|:-:|
| Query | GET |
| Mutation | POST / PUT / DELETE / PATCh |
| Subscription | SSE / WebSocket(for Streaming) |

```
type Query {
    ...
}
type Mutation {
    ...
}
type Subscription {
    ...
}
```

* 스키마라는 개념이 존재한다,
    * 데이터베이스 스키마와 유사하다.
    * 타입, 필드, 관계를 정의한다.

```
type Query {
    getCustomer: Customer
}

type Customer {
    id: ID
    name: String
    age: Int
    products: [Product]
}

type Product {
    id: ID
    description: String
    price: Int
}
```