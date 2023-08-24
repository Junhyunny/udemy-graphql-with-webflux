
## Chapter 66. GraphQL Interface - Demo

* GraphQL에서 인터페이스를 사용한 추상화 응답을 반환했을 때 클라이언트 측에서도 형변환을 통해 원하는 결과를 추출할 수 있다.
    * GraphQL 형변환은 on 키워드를 사용한다.

```graphqls
{
  products {
    id
    price
    description
    type: __typename
    ... on Fruit {
      expiryDate
    }
    ... on Electronics {
      brand
    }
    ... on Book {
      author
    }
  }
}
```

* 다음과 같은 결과를 얻는다.

```json
{
  "data": {
    "products": [
      {
        "id": "7d64e292-515a-4f84-bc1e-033a0ff3cef7",
        "price": 1000,
        "description": "Banana",
        "type": "Fruit",
        "expiryDate": "2023-08-24"
      },
      {
        "id": "72de601c-893a-4852-b32d-c719bcf20a29",
        "price": 400000,
        "description": "Macbook",
        "type": "Electronics",
        "brand": "APPLE"
      },
      {
        "id": "f3dff862-64db-48b3-8f81-b5a909150661",
        "price": 32000,
        "description": "Clean Code",
        "type": "Book",
        "author": "Bob"
      }
    ]
  }
}
```