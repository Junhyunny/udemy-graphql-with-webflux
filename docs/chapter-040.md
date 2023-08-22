
## Chapter 40. Operation Name

* 여러 개의 오퍼레이션을 동시에 정의해서 사용할 수 있다.
* `operatinName`이라는 필드에 쿼리 바디 메세지에 함께 전달하면 해당 쿼리 실행 결과를 반환한다.

* 다음과 같이 질의문을 정의할 수 있다.

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

* 두 오퍼레이션 중 `CustomerByID`을 실행하고 싶은 경우 다음과 같이 메세지를 보낸다.
    * `operationName` 키에 `CustomerByID` 값이 함꼐 전달된다.

```json
{
   "query":"query CustomerByID {\n  customerById(id: 1) {\n    ...CustomerDetails\n  }\n}\n\nquery CustomersByAgeRange {\n  kids: customersByAgeRange(filter: {min: 0, max: 10}) {\n    ...CustomerDetails\n  }\n  adults: customersByAgeRange(filter: {min: 10, max: 50}) {\n    ...CustomerDetails\n  }\n  seniors: customersByAgeRange(filter: {min: 50, max: 100}) {\n    ...CustomerDetails\n  }\n}\n\nfragment CustomerDetails on Customer {\n  id\n  name\n  age\n  city\n}",
   "operationName":"CustomerByID"
}
```
