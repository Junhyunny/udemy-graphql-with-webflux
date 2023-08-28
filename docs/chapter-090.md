
## Chapter 90. Summary

* @QueryMapping - GET Method
* @MutationMapping - POST / PUT / DELETE Method
* 응답으로 null 값을 받는 것은 리소스가 존재하지 않다는 의미이다.
* 한 번의 오퍼레이션(operation)으로 여러 번의 mutation 기능을 수행할 수 있다.
    * 순차적으로 수행한다.
* Query, Mutation은 혼합해서 사용하지 못한다.
* GraphQL Spec
    * Query Type - 필수
        * 스키마(schema) 파일에 반드시 명시되어 있어야 한다.
    * Mutation Type - 옵션
    * Subscription Type - 옵션
