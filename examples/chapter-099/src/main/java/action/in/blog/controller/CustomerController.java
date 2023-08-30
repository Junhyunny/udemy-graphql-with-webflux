package action.in.blog.controller;

import action.in.blog.domain.CustomerDTO;
import action.in.blog.domain.CustomerNotFound;
import action.in.blog.exception.ApplicationErrors;
import action.in.blog.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<CustomerDTO> customers(DataFetchingEnvironment environment) {
        var callerId = environment.getGraphQlContext()
                .get("caller-id");
        System.out.println(callerId);
        return customerService.customers();
    }

    @QueryMapping
    public Mono<Object> customerById(@Argument int id) {
        return customerService.customerById(id)
                .cast(Object.class)
                .switchIfEmpty(
                        Mono.just(CustomerNotFound.create(id))
                );
    }

    @MutationMapping
    public Mono<CustomerDTO> createCustomer(@Argument CustomerDTO customer) {
        return Mono.just(customer)
                .filter(customerDTO -> customerDTO.getAge() >= 18)
                .flatMap(customerService::createCustomer)
                .switchIfEmpty(ApplicationErrors.mustGreaterThan18(customer));
    }
}
