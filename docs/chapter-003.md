
## Chapter 3.  GraphQL - Introduction

* What is wrong with REST?
    * 모든 데이터를 조회한다.
    * 필요한 데이터만 사용하지 않는다.
    * 예를 들어 customer, order, product 서비스가 존재한다고 가정한다.
    * 사용자 정보를 모두 조회한다. (아이디, 이름, 이메일, etc)
    * 사용자 정보에서 사용자 ID만 추출하여 주문 정보를 조회한다.
    * 주문 정보에서 상품 ID만 추출해서 상품 정보를 조회한다.
    * 오버 페칭(over fetching)이 발생한다.
* GraphQL
    * 클라이언트는 GraphQL 서버로 쿼리를 던진다.
    * GraphQL 서버는 응답을 만들어 클라이언트에게 전달한다.
    * API를 위한 질의 언어(query language)이다.
    * 타입을 강력하게 지원한다.
    * 타입 시스템을 사용해 백엔드 개발자들이 API 엔드포인트를 노출할 수 있게 한다. 
    * 타입 시스템을 사용해 데이터 구조가 어떻게 구성되어있는지 통신한다.
    * 클라이언트가 API를 탐색하고 구체적인 데이터를 요청하는데 유연함을 제공한다.
    * 오버 페칭(over-fetching)/언더 페칭(under-fetching) 문제를 해결한다.
