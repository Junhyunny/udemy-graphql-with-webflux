
## Chapter 13. How GraphQL QueryMapping Works?

* 여러 개의 엔드 포인트로 요청이 들어오는 경우 동시적(혹은 병렬적)으로 해당 요청을 처리한다.
* GraphQL을 사용하는 경우 모든 요청은 POST 메소드를 사용한다. 
* 아래와 같은 요청이 오면 각 엔드 포인트 별로 병렬적(parallel)하게 비즈니스 로직이 수행된다.
    * API 요청을 3번 수행할 것을 한번에 수행한다.
    * 네트워크 트래픽이 간소화된다.
    * 동시에 작업이 수행되므로 병렬적으로 수행되므로 속도 측면에서 빠르다.

```
{
  random
  sayHello
  sayHelloTo(name: "Junhyunny")
}
```

* 리액티브 프로그래밍에서 다음과 같이 동작하는 것과 동일하다.

```java
Mono<String> sayHello();
Mono<String> sayHelloTo(String name);
Mono<Integer> random();

return Mono.zip(
    sayHello(),
    sayHelloTo("Junhyunny"),
    random()
);
```