
## Chapter 37. Field Alias

* GraphQL 클라이언트는 데이터를 조회할 때 자신이 원하는 변수로 변경하여 데이터를 받을 수 있다.

```graphql
{
  myCustomers: customersAll {
    id
    name
    age
    city
  }
}
```

* 다음과 같은 응답을 받는다.

```json
{
  "data": {
    "myCustomers": [
      {
        "id": "1",
        "name": "Sam",
        "age": 12,
        "city": "Atlanta"
      },
      {
        "id": "2",
        "name": "Jake",
        "age": 15,
        "city": "Las vegas"
      },
      {
        "id": "3",
        "name": "Mike",
        "age": 17,
        "city": "Miami"
      },
      {
        "id": "4",
        "name": "John",
        "age": 20,
        "city": "Atlanta"
      }
    ],
  }
}
```