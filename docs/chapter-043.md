
## Chapter 43. Directives - @include / @skip

* @include / @skip 디렉티브를 통해 오퍼레이션의 특정 기능을 사용할 것인지 여부를 결정할 수 있다.
* 다음과 같이 사용한다.

```graphql
query CustomerByAgeRange(
  $kid: AgeRangeFilter!, 
  $adult: AgeRangeFilter!, 
  $senior: AgeRangeFilter!, 
  $includeKids: Boolean!, 
  $includeAdults: Boolean!, 
  $includeSeniors: Boolean!
) {
  kids: customersByAgeRange(filter: $kid) @include(if: $includeKids) {
    ...CustomerDetails
  }
  adults: customersByAgeRange(filter: $adult) @include(if: $includeAdults) {
    ...CustomerDetails
  }
  seniors: customersByAgeRange(filter: $senior) @include(if: $includeSeniors) {
    ...CustomerDetails
  }
}

fragment CustomerDetails on Customer {
  id
  name
  city
  age
}
```

* 위 쿼리는 변수를 사용해 질의하였으므로 아래와 같은 변수가 필요하다.

```json
{
  "kid": {
    "min": 0,
    "max": 10
  },
  "adult": {
    "min": 10,
    "max": 60
  },
  "senior": {
    "min": 60,
    "max": 80
  },
  "includeKids": true,
  "includeAdults": true,
  "includeSeniors": false
}
```