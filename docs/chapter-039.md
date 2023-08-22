
## Chapter 039. Fragments

* 동일한 데이터 구조는 fragment 키워드를 통해 재사용할 수 있다.

```graphql
{
  kids: customersByAgeRange(filter: {min: 0, max: 10}) {
    ...CustomerDetails
  }
  adults: customersByAgeRange(filter: {min: 10, max: 50}) {
    ...CustomerDetails
  }
  seniors: customersByAgeRange(filter: {min: 50, max: 100}) {
    ...CustomerDetails
  }
}

fragment CustomerDetails on Customer {
  id
  name
  age
  city
}
```

* 다음과 같은 응답을 받는다.

```json
{
  "data": {
    "kids": [],
    "adults": [
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
    "seniors": []
  }
}
```