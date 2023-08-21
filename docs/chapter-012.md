
## Chapter 12. How GraphQL is different from REST?

* 여러 엔드포인트(endpoint)에 동시에 요청을 보낼 수 있다.

```graphqls
{
  random
  sayHello
  sayHelloTo(name: "Junhyunny")
}
```

```json
{
  "data": {
    "random": 25,
    "sayHello": "Hello World",
    "sayHelloTo": "Hello World Junhyunny"
  }
}
```