
## Chapter 41. Using Variable

* 오퍼레이션에서 사용하는 변수를 지정할 수 있다.
* `$` 키워드를 붙이면 변수로 사용할 수 있다.

```graphql
query CustomerByID($id: ID! = 1) {
  customerById(id: $id) {
    ...CustomerDetails
  }
}

query CustomersByAgeRange {
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

* 해당 요청 메세지를 보낼 때 아래처럼 변수 정보를 함께 전달한다.

```json
{
   "query":"query CustomerByID($id: ID! = 1) {\n  customerById(id: $id) {\n    ...CustomerDetails\n  }\n}\n\nquery CustomersByAgeRange {\n  kids: customersByAgeRange(filter: {min: 0, max: 10}) {\n    ...CustomerDetails\n  }\n  adults: customersByAgeRange(filter: {min: 10, max: 50}) {\n    ...CustomerDetails\n  }\n  seniors: customersByAgeRange(filter: {min: 50, max: 100}) {\n    ...CustomerDetails\n  }\n}\n\nfragment CustomerDetails on Customer {\n  id\n  name\n  age\n  city\n}",
   "variables":{
      "id":3
   },
   "operationName":"CustomerByID"
}
```
