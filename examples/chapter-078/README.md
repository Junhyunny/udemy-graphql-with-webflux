## Client Request Schema

```graphql
query GetAll {
    customers {
        ...CustomerDTO
    }
}

query GetCustomerByID($id: ID!) {
    customerById(id: $id) {
        ...CustomerDTO
    }
}

query MultipleQueries($id: ID!) {
    customers {
        ...CustomerDTO
    }
    customerById(id: $id) {
        ...CustomerDTO
    }
}

mutation CreateCustomer($customer: CustomerInput!) {
    createCustomer(customer: $customer) {
        ...CustomerDTO
    }
}

mutation UpdateCustomer($id: ID!, $customer: CustomerInput!) {
    updateCustomer(id: $id, customer: $customer) {
        ...CustomerDTO
    }
}

mutation deleteCustomer($id: ID!) {
    deleteCustomer(id: $id) {
        id
        status
    }
}

mutation MultipleMutations($id: ID!, $customer: CustomerInput!) {
    createCustomer(customer: $customer) {
        ...CustomerDTO
    }
    updateCustomer(id: $id, customer: {name: "Jackson", age: 25, city: "Las Vegas"}) {
        ...CustomerDTO
    }
}

fragment CustomerDTO on Customer {
    id
    name
    age
    city
}
```

## Client Request Variable

```json
{
  "id": 6,
  "customer": {
    "name": "Obie",
    "age": 45,
    "city": "Detroit"
  }
}
```