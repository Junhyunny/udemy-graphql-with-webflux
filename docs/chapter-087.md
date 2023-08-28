
## Chapter 87. Multiple Mutation

* `query` 요청은 상태를 변경하지 않기 때문에 동시에 수행된다.
    * 동시에 실행되기 때문에 수행되는 총 시간은 각 연산에서 가장 긴 사긴과 같다.

```graphqls
query MultipleQueries($id: ID!) {
  customers {
    ...CustomerDTO
  }
  customerById(id: $id) {
    ...CustomerDTO
  }
}
```

* `mutation` 요청은 상태를 변경하기 때문에 순차적으로 수행된다.
    * 순차적으로 실행되기 때문에 수행되는 총 시간은 각 연산에서 수행되는 시간을 더한 값과 같다.

```graphqls
mutation MultipleMutations($id: ID!, $customer: CustomerInput!) {
  createCustomer(customer: $customer) {
    ...CustomerDTO
  }
  updateCustomer(id: $id, customer: {name: "Jackson", age: 25, city: "Las Vegas"}) {
    ...CustomerDTO
  }
}
```