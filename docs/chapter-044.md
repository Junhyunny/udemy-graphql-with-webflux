
## Chapter 44. Directives - @Deprecated

* @deprecated 디렉티브를 사용하면 사용하지 않는 기능임을 표시할 수 있다.
* 서버에 정의된 스키마에 다음과 같이 작성할 수 있다.

```graphql
type Query {
    # use this to fetch all customers
    customersAll: [Customer]!

    # to fetch specific customer by id
    customerById(id: ID!): Customer

    # filter customers by name
    customersNameContains(name: String!): [Customer]

    # filter customers by age range
    customersByAgeRange(filter: AgeRangeFilter!): [Customer]!

    # filter customers by age range
    ageRange(filter: AgeRangeFilter!): [Customer]! @deprecated(reason: "Use customersByAgeRange")
}

input AgeRangeFilter {
    min: Int!
    max: Int!
}

type Customer {
    id: ID
    name: String
    age: Int
    city: String
}
```